package helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.FiniteStateMachine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LexerHelper {
    private static List<Path> lexers;

    static {
        try {
            lexers = Files.walk(Path.of("D:\\Projects\\MyFSM\\src\\main\\resources\\lexer")).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map.Entry<String, String> analyze(String str, int skip) throws IOException {
        String resultLexer = "";
        String resultClass = "";
        int resultRank = 999;
        ObjectMapper mapper = new ObjectMapper();
        for (Path lexer : lexers) {
            if (!Files.isDirectory(lexer)) {
                FiniteStateMachine[] lexerFsmList = mapper.readValue(Files.readString(lexer), FiniteStateMachine[].class);
                for (FiniteStateMachine lexerFsm : lexerFsmList) {
                    Map.Entry<Boolean, Integer> check = lexerFsm.check(str, skip);
                    if (check.getKey()) {
                        String newLexer = str.substring(skip, skip + check.getValue());
                        if (resultLexer.length() < newLexer.length()) {
                            resultLexer = newLexer;
                            resultRank = lexerFsm.getRank();
                            resultClass = lexer.toFile().getName().split("\\.")[0];
                        } else if (resultLexer.length() == newLexer.length() && resultRank > lexerFsm.getRank()) {
                            resultLexer = newLexer;
                            resultRank = lexerFsm.getRank();
                            resultClass = lexer.toFile().getName().split("\\.")[0];
                        }
                    }
                }

            }
        }
        return Map.entry(resultLexer, resultClass);
    }

    public static List<Map.Entry<String, String>> analyzeAll(String str) throws IOException {
        List<Map.Entry<String, String>> result = new ArrayList<>();
        int i = 0;
        while (i < str.length()) {
            Map.Entry<String, String> analyzeResult = analyze(str, i);
            i += analyzeResult.getKey().length();
            result.add(analyzeResult);
        }
        return result;
    }

    public static String analyzeForTesting(String str) throws IOException {
        return analyze(str, 0).getValue();
    }
}
