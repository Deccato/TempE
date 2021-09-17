package Expressions;

import Core.Context;

public class ForExpression implements Expression {

    private final String iterableName;
    private final String iteratorName;
    private final Expression body;

    public ForExpression(String iteratorName, String iterableName, Expression body) {
        this.iteratorName = iteratorName;
        this.iterableName = iterableName;
        this.body = body;
    }

    @Override
    public String interpret(Context context) throws Exception {
        StringBuilder result = new StringBuilder();
        if (!(context.getValue(iterableName.toString()) instanceof Iterable)) {
            throw new Exception("Object have to implement iterable");
        }
        for (Object val : (Iterable<?>) context.getValue(iterableName.toString())) {
            context.addValue(iteratorName.toString(), val);
            result.append(body.interpret(context));
            context.deleteValue(iteratorName.toString());
        }

        return result.toString();
    }
}
