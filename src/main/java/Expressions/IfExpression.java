package Expressions;

import Core.Context;

public class IfExpression implements Expression {

    private final String argumentLeftSide;
    private final String argumentRightSide;
    private final String operator;
    private final Expression trueCase;
    private final Expression falseCase;

    public IfExpression(String argumentLeftSide, String argumentRightSide, String operator, Expression trueCase, Expression falseCase) {
        this.argumentLeftSide = argumentLeftSide;
        this.argumentRightSide = argumentRightSide;
        this.operator = operator;
        this.trueCase = trueCase;
        this.falseCase = falseCase;
    }

    @Override
    public String interpret(Context context) throws Exception {
        boolean condition = true;
        switch (operator.toString()) {
            case "==":
                condition = (context.getValue(argumentLeftSide).equals(argumentRightSide));
                break;
            case "!=":
                condition = !(context.getValue(argumentLeftSide).equals(argumentRightSide));
                break;
        }

        return (condition ? trueCase.interpret(context) : (falseCase != null ? falseCase.interpret(context) : ""));
    }
}
