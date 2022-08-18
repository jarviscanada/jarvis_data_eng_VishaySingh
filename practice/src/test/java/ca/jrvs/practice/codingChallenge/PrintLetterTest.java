package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class PrintLetterTest {

    private PrintLetter printLetter = new PrintLetter();

    @Test
    public void printLetterNum() {
        assertEquals("a1b2c3e5e5", printLetter.printLetterNum("abcee"));
    }
}