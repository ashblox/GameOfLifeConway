package com.zipcodeconway;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ConwayGameOfLifeTest {

    @Test
    public void runTest1() {
        Integer[][] start = {
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0}};
        Integer[][] expected = {
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0}};
        ConwayGameOfLife sim = new ConwayGameOfLife(5, start);
        Integer[][] results = sim.simulate(10);

        assertTrue(java.util.Arrays.deepEquals(results, expected));
    }

    @Test
    public void runTest2() {
        Integer[][] start = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}};
        Integer[][] expected = {
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0}};
        ConwayGameOfLife sim = new ConwayGameOfLife(5, start);
        Integer[][] results = sim.simulate(9);
        assertTrue(java.util.Arrays.deepEquals(results, expected));
    }

    @Test
    public void testFindNeighbors() {
        Integer[][] arr = {
                {0, 0, 1},
                {1, 1, 0},
                {0, 0, 0}};
        ConwayGameOfLife conwayGameOfLife = new ConwayGameOfLife(3, arr);
        List<Integer> expected = new ArrayList<>(Arrays.asList(0, 0, 1, 1, 0, 0, 0, 0));

        List<Integer> actual = conwayGameOfLife.findNeighbors(1, 1, arr);

        for (int i = 0; i < expected.size(); i++) {
            System.out.println(actual.get(i));
            Assert.assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void testFindNeighbors1() {
        Integer[][] arr = {
                {0, 0, 1},
                {1, 1, 0},
                {0, 0, 0}};

        ConwayGameOfLife conwayGameOfLife = new ConwayGameOfLife(3, arr);
        List<Integer> expected = new ArrayList<>(Arrays.asList(0, 1, 0, 1, 1, 0, 0, 0));

        List<Integer> actual = conwayGameOfLife.findNeighbors(1, 2, arr);

        for (int i = 0; i < expected.size(); i++) {
            System.out.println(actual.get(i));
            Assert.assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void testFindNeighbors2() {
        Integer[][] arr = {
                {0, 0, 1},
                {1, 1, 0},
                {0, 0, 0}};

        ConwayGameOfLife conwayGameOfLife = new ConwayGameOfLife(3, arr);
        List<Integer> expected = new ArrayList<>(Arrays.asList(0, 1, 0, 1, 1, 0, 0, 0));

        List<Integer> actual = conwayGameOfLife.findNeighbors(1, 2, arr);

        for (int i = 0; i < expected.size(); i++) {
            System.out.println(actual.get(i));
            Assert.assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void testFindNeighbors3() {
        Integer[][] arr = {
                {1, 0, 1},
                {0, 1, 1},
                {0, 1, 0}};
        ConwayGameOfLife conwayGameOfLife = new ConwayGameOfLife(3, arr);
        List<Integer> expected = new ArrayList<>(Arrays.asList(1, 1, 0, 1, 1, 0, 0, 1));

        List<Integer> actual = conwayGameOfLife.findNeighbors(1, 0, arr);

        for (int i = 0; i < expected.size(); i++) {
            System.out.println(actual.get(i));
            Assert.assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void testCopyAndZeroOut() {
        Integer[][] current = {
                {0, 0, 1},
                {1, 1, 0},
                {0, 0, 0}};
        Integer[][] next = {
                {0, 1, 0},
                {0, 1, 0},
                {0, 0, 0}};
        Integer[][] copyofnext = {
                {0, 1, 0},
                {0, 1, 0},
                {0, 0, 0}};
        Integer[][] expected = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}};
        ConwayGameOfLife conwayGameOfLife = new ConwayGameOfLife(3, current);

        conwayGameOfLife.copyAndZeroOut(next, current);

        Assert.assertArrayEquals("current should be a copy of next", current, copyofnext);

        Assert.assertArrayEquals(expected, next);
    }

    @Test
    public void testCountAlive() {
        int expected = 2;
        Integer[][] arr = {
                {0, 0, 1},
                {1, 1, 0},
                {0, 0, 0}};
        ConwayGameOfLife conwayGameOfLife = new ConwayGameOfLife(3, arr);

        int actual = conwayGameOfLife.countAlive(conwayGameOfLife.findNeighbors(1, 1, arr));

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testIsAlive() {
        int expected = 1;
        Integer[][] arr = {
                {0, 0, 1},
                {1, 1, 0},
                {0, 0, 0}};
        ConwayGameOfLife conwayGameOfLife = new ConwayGameOfLife(3, arr);

        int actual = conwayGameOfLife.isAlive(1, 1, arr);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testIsAlive1() {
        int expected = 0;
        Integer[][] arr = {
                {0, 1, 1},
                {1, 1, 0},
                {0, 0, 0}};
        ConwayGameOfLife conwayGameOfLife = new ConwayGameOfLife(3, arr);

        int actual = conwayGameOfLife.isAlive(1, 2, arr);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testIsAlive2() {
        int expected = 0;
        Integer[][] arr = {
                {0, 1, 1},
                {1, 1, 0},
                {0, 0, 0}};
        ConwayGameOfLife conwayGameOfLife = new ConwayGameOfLife(3, arr);

        int actual = conwayGameOfLife.isAlive(2, 1, arr);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSimulate() {
        Integer[][] arr = {
                {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

        Integer[][] expected = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        ConwayGameOfLife conwayGameOfLife = new ConwayGameOfLife(15, arr);


        Integer[][] actual = conwayGameOfLife.simulate(5);

        Assert.assertArrayEquals(expected, actual);
    }
}

