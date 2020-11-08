package entity;

import helper.LexerHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class FiniteStateMachineTest {

    @Test
    public void testBool() throws IOException {
        String aTrue = LexerHelper.analyze("true");
        Assertions.assertEquals("bool", aTrue);

        String aFalse = LexerHelper.analyze("false");
        Assertions.assertEquals("bool", aFalse);
    }

    @Test
    public void testId() throws IOException {
        String a = LexerHelper.analyze("a");
        Assertions.assertEquals("id", a);

        String b = LexerHelper.analyze("b");
        Assertions.assertEquals("id", b);

        String brk = LexerHelper.analyze("break");
        Assertions.assertNotEquals("id", brk);

        String brck = LexerHelper.analyze("breack");
        Assertions.assertEquals("id", brck);
    }

    @Test
    public void testKeyword() throws IOException {
        String brk = LexerHelper.analyze("break");
        Assertions.assertEquals("keyword", brk);

        String whl = LexerHelper.analyze("while");
        Assertions.assertEquals("keyword", whl);
    }

    @Test
    public void testNumber() throws IOException {
        String number1 = LexerHelper.analyze("123");
        Assertions.assertEquals("number", number1);

        String number2 = LexerHelper.analyze("2.2e5");
        Assertions.assertEquals("number", number2);

        String number3 = LexerHelper.analyze("0");
        Assertions.assertEquals("number", number3);

        String number4 = LexerHelper.analyze("15.0");
        Assertions.assertEquals("number", number4);
    }

    @Test
    public void testOperator() throws IOException {
        String oper1 = LexerHelper.analyze("=");
        Assertions.assertEquals("operator", oper1);

        String oper2 = LexerHelper.analyze(">");
        Assertions.assertEquals("operator", oper2);

        String oper3 = LexerHelper.analyze("&&");
        Assertions.assertEquals("operator", oper3);

        String oper4 = LexerHelper.analyze("!");
        Assertions.assertEquals("operator", oper4);

        String oper5 = LexerHelper.analyze("-");
        Assertions.assertEquals("operator", oper5);

        String oper6 = LexerHelper.analyze("<=");
        Assertions.assertEquals("operator", oper6);

        String oper7 = LexerHelper.analyze("/");
        Assertions.assertEquals("operator", oper7);
    }

    @Test
    public void testType() throws IOException {
        String intType = LexerHelper.analyze("int");
        Assertions.assertEquals("type", intType);

        String doubleType = LexerHelper.analyze("double");
        Assertions.assertEquals("type", doubleType);

        String booleanType = LexerHelper.analyze("boolean");
        Assertions.assertEquals("type", booleanType);

    }

}