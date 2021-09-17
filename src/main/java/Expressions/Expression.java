package Expressions;

import Core.Context;

public interface Expression {
    public String interpret(Context context) throws Exception;

}
