package entity;

import helper.LexerHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

class FiniteStateMachineTest {

    @Test
    public void testAnalyzeAll() throws IOException {
        List<Map.Entry<String, String>> analyzeAll = LexerHelper.analyzeAll("int a = 10;");
        Assertions.assertEquals("type", analyzeAll.get(0).getValue());
        Assertions.assertEquals("int", analyzeAll.get(0).getKey());
        Assertions.assertEquals("whitespace", analyzeAll.get(1).getValue());
        Assertions.assertEquals(" ", analyzeAll.get(1).getKey());
        Assertions.assertEquals("id", analyzeAll.get(2).getValue());
        Assertions.assertEquals("a", analyzeAll.get(2).getKey());
        Assertions.assertEquals("whitespace", analyzeAll.get(3).getValue());
        Assertions.assertEquals(" ", analyzeAll.get(3).getKey());
        Assertions.assertEquals("operator", analyzeAll.get(4).getValue());
        Assertions.assertEquals("=", analyzeAll.get(4).getKey());
        Assertions.assertEquals("whitespace", analyzeAll.get(5).getValue());
        Assertions.assertEquals(" ", analyzeAll.get(5).getKey());
        Assertions.assertEquals("number", analyzeAll.get(6).getValue());
        Assertions.assertEquals("10", analyzeAll.get(6).getKey());
        Assertions.assertEquals("spec", analyzeAll.get(7).getValue());
        Assertions.assertEquals(";", analyzeAll.get(7).getKey());
    }

    @Test
    public void testBool() throws IOException {
        String aTrue = LexerHelper.analyzeForTesting("true");
        Assertions.assertEquals("bool", aTrue);

        String aFalse = LexerHelper.analyzeForTesting("false");
        Assertions.assertEquals("bool", aFalse);
    }

    @Test
    public void testId() throws IOException {
        String a = LexerHelper.analyzeForTesting("a");
        Assertions.assertEquals("id", a);

        String b = LexerHelper.analyzeForTesting("b");
        Assertions.assertEquals("id", b);

        String brk = LexerHelper.analyzeForTesting("break");
        Assertions.assertNotEquals("id", brk);

        String brck = LexerHelper.analyzeForTesting("breack");
        Assertions.assertEquals("id", brck);
    }

    @Test
    public void testKeyword() throws IOException {
        String brk = LexerHelper.analyzeForTesting("break");
        Assertions.assertEquals("keyword", brk);

        String whl = LexerHelper.analyzeForTesting("while");
        Assertions.assertEquals("keyword", whl);
    }

    @Test
    public void testNumber() throws IOException {
        String number1 = LexerHelper.analyzeForTesting("123");
        Assertions.assertEquals("number", number1);

        String number2 = LexerHelper.analyzeForTesting("2.2e5");
        Assertions.assertEquals("number", number2);

        String number3 = LexerHelper.analyzeForTesting("0");
        Assertions.assertEquals("number", number3);

        String number4 = LexerHelper.analyzeForTesting("15.0");
        Assertions.assertEquals("number", number4);
    }

    @Test
    public void testOperator() throws IOException {
        String oper1 = LexerHelper.analyzeForTesting("=");
        Assertions.assertEquals("operator", oper1);

        String oper2 = LexerHelper.analyzeForTesting(">");
        Assertions.assertEquals("operator", oper2);

        String oper3 = LexerHelper.analyzeForTesting("&&");
        Assertions.assertEquals("operator", oper3);

        String oper4 = LexerHelper.analyzeForTesting("!");
        Assertions.assertEquals("operator", oper4);

        String oper5 = LexerHelper.analyzeForTesting("-");
        Assertions.assertEquals("operator", oper5);

        String oper6 = LexerHelper.analyzeForTesting("<=");
        Assertions.assertEquals("operator", oper6);

        String oper7 = LexerHelper.analyzeForTesting("/");
        Assertions.assertEquals("operator", oper7);
    }

    @Test
    public void testType() throws IOException {
        String intType = LexerHelper.analyzeForTesting("int");
        Assertions.assertEquals("type", intType);

        String doubleType = LexerHelper.analyzeForTesting("double");
        Assertions.assertEquals("type", doubleType);

        String booleanType = LexerHelper.analyzeForTesting("boolean");
        Assertions.assertEquals("type", booleanType);

    }

    @Test
    public void testSpec() throws IOException {
        String spec1 = LexerHelper.analyzeForTesting(";");
        Assertions.assertEquals("spec", spec1);

        String spec2 = LexerHelper.analyzeForTesting("(");
        Assertions.assertEquals("spec", spec2);

        String spec3 = LexerHelper.analyzeForTesting(")");
        Assertions.assertEquals("spec", spec3);

        String spec4 = LexerHelper.analyzeForTesting("{");
        Assertions.assertEquals("spec", spec4);

        String spec5 = LexerHelper.analyzeForTesting("}");
        Assertions.assertEquals("spec", spec5);
    }

    @Test
    public void testWhitespace() throws IOException {
        String ws1 = LexerHelper.analyzeForTesting(" ");
        Assertions.assertEquals("whitespace", ws1);

        String ws2 = LexerHelper.analyzeForTesting("    ");
        Assertions.assertEquals("whitespace", ws2);

        String ws3 = LexerHelper.analyzeForTesting("\n");
        Assertions.assertEquals("whitespace", ws3);
    }

}