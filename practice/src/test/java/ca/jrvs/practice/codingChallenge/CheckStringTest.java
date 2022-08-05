package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class CheckStringTest {
    private CheckString checkStr = new CheckString();

    @Test
    public void isDigitAscii() {
        assertTrue(checkStr.isDigitAscii("123"));
        assertTrue(checkStr.isDigitRegex("123"));
        assertTrue(checkStr.isDigitApi("123"));

        assertFalse(checkStr.isDigitAscii("12,3"));
        assertFalse(checkStr.isDigitApi("12,3"));
        assertFalse(checkStr.isDigitRegex("12,3"));
    }
}