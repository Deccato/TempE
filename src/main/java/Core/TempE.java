package Core;

import Interpretor.Interpreter;

import java.util.Map;

public class TempE {

    private TempE() {
    }

    public static String process(String s, Map<String, Object> data) throws Exception {
        return Interpreter.interpret(s).interpret(new Context(data));
    }

}
