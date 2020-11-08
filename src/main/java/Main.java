import helper.LexerHelper;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println(LexerHelper.analyze("!break"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
