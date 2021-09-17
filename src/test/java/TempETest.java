import Core.TempE;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TempETest {
    @Test
    public void process_var() {
        String pattern = "Hello, @name";
        String result = "Hello, Vasya";

        Map<String, Object> data = new HashMap<>();
        data.put("name", "Vasya");

        try {
            Assertions.assertEquals(result, TempE.process(pattern, data));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void process_if() throws Exception {
        String pattern = "Hello, @if (name == Vasya) {Hi} else {DO}";
        String result = "Hello, Hi";

        Map<String, Object> data = new HashMap<>();
        data.put("name", "Vasya");

        Assertions.assertEquals(result, TempE.process(pattern, data));

    }

    @Test
    public void process_for() throws Exception {
        String pattern = "Hello, @for (name : names) {@name\n}";
        String result = "Hello, Vasya\nKolia\n";

        Map<String, Object> data = new HashMap<>();
        data.put("names", Arrays.asList("Vasya", "Kolia"));


        Assertions.assertEquals(result, TempE.process(pattern, data));

    }

    @Test
    public void process_innerFor() throws Exception {
        String pattern = "Hello, @for (name : names) { @for (name : names) {@name } @name \n }";
        String result = "Hello,  Vasya Kolia  Vasya \n  Vasya Kolia  Kolia \n ";

        Map<String, Object> data = new HashMap<>();
        data.put("names", Arrays.asList("Vasya", "Kolia"));

        Assertions.assertEquals(result, TempE.process(pattern, data));

    }
    @Test
    public void process_array_list() throws Exception {
        String pattern = "@names[0] @names[1]";
        String result = "Vasya Kolia";

        Map<String, Object> data = new HashMap<>();
        data.put("names", Arrays.asList("Vasya", "Kolia"));

        Assertions.assertEquals(result, TempE.process(pattern, data));
    }
    @Test
    public void process_array_map() throws Exception {
        String pattern = "@date[p][t] @date[p][m]";
        String result = "2004 2005";

        Map<String, Object> data = new HashMap<>();
        Map<String, Map<String, String>> tmp = new HashMap<>();
        Map<String, String> tmp1 = new HashMap<>();
        tmp1.put("t", "2004");
        tmp1.put("m", "2005");
        tmp.put("p", tmp1);
        data.put("date", tmp);
        Assertions.assertEquals(result, TempE.process(pattern, data));
    }

}
