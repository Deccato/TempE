package Expressions;

import Core.Context;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DataExpression implements Expression {

    private final String accessibleObjectName;
    private final List<String> indexes;

    public DataExpression(String accessibleObjectName, List<String> indexes) {
        this.accessibleObjectName = accessibleObjectName;
        this.indexes = indexes;
    }

    @Override
    public String interpret(Context context) throws Exception {
        Object value = context.getValue(accessibleObjectName);
        for (Object i : indexes) {
            if (value instanceof List) {
                value = ((List) value).get(Integer.parseInt(i.toString()));
            }
            if (value instanceof Map) {
                value = ((Map) value).get(i.toString());
            }
        }
        return value.toString();
    }
}
