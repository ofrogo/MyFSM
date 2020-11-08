package helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.FiniteStateMachine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LexerHelper {
    private static  List<Path> lexers;

    static {
        try {
            lexers = Files.walk(Path.of("D:\\Projects\\MyFSM\\src\\main\\resources\\lexer")).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String analyze(String str) throws IOException {
        String resultLexer = "";
        String resultClass = "";
        int resultRank = 999;
        ObjectMapper mapper = new ObjectMapper();
        for (Path lexer : lexers) {
            if (!Files.isDirectory(lexer)) {
                FiniteStateMachine[] lexerFsmList = mapper.readValue(Files.readString(lexer), FiniteStateMachine[].class);
                for (FiniteStateMachine lexerFsm : lexerFsmList) {
                    Map.Entry<Boolean, Integer> check = lexerFsm.check(str, 0);
                    if (check.getKey() && check.getValue().equals(str.length())) {
                        if (resultLexer.length() < str.length()) {
                            resultLexer = str;
                            resultRank = lexerFsm.getRank();
                            resultClass = lexer.toFile().getName().split("\\.")[0];
                        } else if (resultLexer.length() == str.length() && resultRank > lexerFsm.getRank()){
                            resultLexer = str;
                            resultRank = lexerFsm.getRank();
                            resultClass = lexer.toFile().getName().split("\\.")[0];
                        }
                    }
                }

            }
        }
        return resultClass;

    }
}
