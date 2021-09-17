package Core;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Context {

    private final Map<String, Stack<Object>> data = new HashMap<>();

    public Context(Map<String, Object> data) {
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            if (!this.data.containsKey(entry.getKey())) {
                this.data.put(entry.getKey(), new Stack<>());
            }
            this.data.get(entry.getKey()).push(entry.getValue());
        }
    }

    public Object getValue(String name) {
        return data.get(name).peek();
    }

    public void addValue(String name, Object value) {
        if (!data.containsKey(name)) {
            data.put(name, new Stack<>());
        }
        data.get(name).push(value);
    }

    public void deleteValue(String name) {
        data.get(name).pop();
    }

}
