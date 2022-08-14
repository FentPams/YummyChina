package com.example.yummychina.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class test all the method in TimeHorneredfod
 */
public class TimeHorneredFoodTest {

    TimeHorneredFood test;

    @Before
    public void setUp() throws Exception {
        test = new TimeHorneredFood("test","1.0",1);
    }



    @Test
    public void testGetName() {
        assertEquals("test",test.getName());
    }

    @Test
    public void testSetName() {
        test.setName("testName");
        assertEquals("testName",test.getName());
    }

    @Test
    public void testGetPrice() {
        assertEquals("1.0",test.getPrice());
    }

    @Test
    public void testSetPrice() {
        test.setPrice("2.0");
        assertEquals("2.0",test.getPrice());
    }

    @Test
    public void testGetImageUrl() {
        assertEquals(1, test.getImageUrl(),0.01);
    }

    @Test
    public void testSetImageUrl() {
        test.setImageUrl(2);
        assertEquals(2, test.getImageUrl(),0.01);
    }
}