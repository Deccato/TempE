package Expressions;

import Core.Context;

public class TextExpression implements Expression {
    String text;

    public TextExpression(String text) {
        this.text = text;
    }

    @Override
    public String interpret(Context context) throws Exception {
        return text;
    }
}
