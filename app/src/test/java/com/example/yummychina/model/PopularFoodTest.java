package com.example.yummychina.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This test class test all the method in Popularfood
 */
public class PopularFoodTest {

    PopularFood test;

    @Before
    public void setUp() throws Exception {
       test = new PopularFood("test","1.0",1, "4.5", "test1");
    }


    @Test
    public void testGetRating() {
        assertEquals("4.5", test.getRating());
    }

    @Test
    public void testSetRating() {
        test.setRating("5.0");
        assertEquals("5.0",test.getRating());
    }

    @Test
    public void getRestorantname() {
        assertEquals("test1",test.getRestorantname());
    }

    @Test
    public void testSetRestorantname() {
        test.setRestorantname("test2");
        assertEquals("test2",test.getRestorantname());

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