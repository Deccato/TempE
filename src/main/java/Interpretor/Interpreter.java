package Interpretor;

import Expressions.*;

import java.util.ArrayList;
import java.util.List;

public class Interpreter {

    public static Expression interpret(String s) {
        StringBuilder tmp = new StringBuilder();

        BlockExpression expressions = new BlockExpression();

        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '@') {
                if (tmp.length() != 0) {
                    expressions.addExpression(new TextExpression(tmp.toString()));
                    tmp = new StringBuilder();
                }

                String block = getBlocName(s, i);

                int j = block.length();
                int to = j;

                if (block.equals("if") || block.equals("for")) {

                    BlockParameters parameters = getBlockParameters(s, i + j);

                    int begin = s.indexOf('{', i), end = getClosingParents(begin, s);

                    if (block.toString().equals("if")) {

                        int pos[] = findElse(s, end);

                        int eBegin = pos[0], eEnd = pos[1];

                        expressions.addExpression(new IfExpression(parameters.getLeftSide(), parameters.getRightSide(),
                                parameters.getOperator(), interpret(s.substring(begin + 1, end)),
                                (eBegin == -1 ? null : interpret(s.substring(eBegin + 1, eEnd)))));

                        i = (eEnd == -1 ? end : eEnd);
                        continue;
                    }
                    if (block.toString().equals("for")) {
                        expressions.addExpression(new ForExpression(parameters.getLeftSide(), parameters.getRightSide(), interpret(s.substring(begin + 1, end))));
                        i = end;
                        continue;
                    }
                } else {
                    j = i + j + 1;
                    List<String> indexes = new ArrayList<>();
                    while (j < s.length() && s.charAt(j) == '[') {
                        int k = s.indexOf(']', j);
                        indexes.add(s.substring(j + 1, k));
                        j = k + 1;
                    }
                    expressions.addExpression(new DataExpression(block.toString(), indexes));
                    i = j - 1;
                }
            } else {
                tmp.append(s.charAt(i));
            }
        }

        if (tmp.length() != 0) {
            expressions.addExpression(new TextExpression(tmp.toString()));
            tmp = new StringBuilder();
        }

        return expressions;
    }

    private static String getBlocName(String s, int i) {
        StringBuilder block = new StringBuilder();
        int j = 1;
        while (i + j < s.length()) {
            if (!((s.charAt(i + j) >= 'a' && s.charAt(i + j) <= 'z') || (s.charAt(i + j) >= 'A' && s.charAt(i + j) <= 'Z'))) {
                break;
            }
            block.append(s.charAt(i + j));
            ++j;
        }
        return block.toString();
    }

    private static BlockParameters getBlockParameters(String s, int i) {

        while (i < s.length() && s.charAt(i) != '(') ++i;
        ++i;

        StringBuilder operator = new StringBuilder();
        StringBuilder before = new StringBuilder();
        StringBuilder after = new StringBuilder();

        boolean flag = false;

        while (i < s.length() && s.charAt(i) != ')') {
            if (s.charAt(i) == ' ') {
                ++i;
                continue;
            }
            if (s.charAt(i) == '=' || s.charAt(i) == '!' || s.charAt(i) == ':') {
                flag = true;
                operator.append(s.charAt(i));
            } else {
                if (flag) {
                    after.append(s.charAt(i));
                } else {
                    before.append(s.charAt(i));
                }
            }
            ++i;
        }
        return new BlockParameters(before.toString(), after.toString(), operator.toString());
    }

    private static int[] findElse(String s, int end) {
        int elsePosition = s.indexOf("else", end);
        if (elsePosition != -1) {
            int p = end + 1;
            while (p < elsePosition && s.charAt(p) == ' ') ++p;
            if (p == elsePosition) {
                int eBegin = s.indexOf('{', elsePosition);
                return new int[]{eBegin, getClosingParents(eBegin, s)};
            }
        }
        return new int[]{-1, -1};
    }

    private static int getClosingParents(int pos, String s) {
        int c = 1;
        for (int i = pos + 1; i < s.length(); ++i) {
            if (s.charAt(i) == '{') ++c;
            if (s.charAt(i) == '}') --c;
            if (c == 0) return i;
        }
        return -1;
    }

}
