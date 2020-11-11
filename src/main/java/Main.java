import helper.LexerHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            String lexerJava = "int a = 123;\n" +
                    "double b = 2.2e5;\n" +
                    "boolean break = false;\n" +
                    "\n" +
                    "while (b > a && !break) {\n" +
                    "  b = b - a\n" +
                    "  if (b <= 0 && a > 0) {\n" +
                    "    break;\n" +
                    "  }\n" +
                    "  a = a / 15.0;\n" +
                    "}";
            List<Map.Entry<String, String>> result = LexerHelper.analyzeAll(lexerJava);
            result.forEach(ss -> System.out.println("< " + (ss.getKey().trim().isEmpty() ? "\\s" : ss.getKey()) + " :: " + ss.getValue() + " >"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
