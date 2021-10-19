package Expressions;

import Core.Context;

import java.util.ArrayList;
import java.util.List;

public class BlockExpression implements Expression {

    private List<Expression> expressionList = new ArrayList<>();

    public BlockExpression(List<Expression> expressionList) {
        this.expressionList = expressionList;
    }

    public BlockExpression() {
    }

    public void addExpression(Expression expression) {
        expressionList.add(expression);
    }

    @Override
    public String interpret(Context context) throws Exception {
        StringBuilder result = new StringBuilder();
        for (Expression expression : expressionList) {
            result.append(expression.interpret(context));
        }
        return result.toString();
    }
}
