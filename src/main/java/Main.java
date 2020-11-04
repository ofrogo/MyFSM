import entity.FiniteStateMachine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println(lexerAnalyze("_123de"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String lexerAnalyze(String str) throws IOException {
        List<Path> lexers = Files.walk(Path.of("D:\\Projects\\MyFSM\\src\\main\\resources\\lexer")).collect(Collectors.toList());
        String resultLexer = "";
        String result = "";
        for (Path lexer : lexers) {
            if (!Files.isDirectory(lexer)) {
                FiniteStateMachine lexerFsm = new FiniteStateMachine(Files.readString(lexer));
                Map.Entry<Boolean, Integer> check = lexerFsm.check(str, 0);
                if (check.getKey() && check.getValue().equals(str.length()) && resultLexer.length() < str.length()) {
                    resultLexer = str;
                    result = lexer.toFile().getName().split("\\.")[0];
                }
            }
        }
        return result;

    }
}
