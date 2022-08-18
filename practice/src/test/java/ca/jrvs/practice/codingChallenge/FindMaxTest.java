package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class FindMaxTest {

    private FindMax findMax = new FindMax();

    @Test
    public void findMax() {
        int[] arr = { 2, 1, 15, 12};
        assertEquals(15, findMax.findMax(arr));
        assertEquals(15, findMax.findMaxStream(arr));
        assertEquals(15, findMax.findMaxApi(arr));
    }
}