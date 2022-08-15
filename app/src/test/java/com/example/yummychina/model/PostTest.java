package com.example.yummychina.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

//This class test all the methods in Post class
public class PostTest {

    Post test;

    @Before
    public void setUp() throws Exception {
        test = new Post("1", "John", "www.image.com", "storytest", "titletest");
    }
    @Test
    public void testGetPostId() {
        assertEquals("1",test.getPostId());
    }

    @Test
    public void testSetPostId() {
        test.setPostId("2");
        assertEquals("2",test.getPostId());
    }

    @Test
    public void testGetFromWhom() {
        assertEquals("John",test.getFromWhom());
    }

    @Test
    public void testSetFromWhom() {
        test.setFromWhom("John1");
        assertEquals("John1",test.getFromWhom());
    }

    @Test
    public void testGetImageLink() {
        assertEquals("www.image.com",test.getImageLink());
    }

    @Test
    public void testSetImageLink() {
        test.setImageLink("www.image1.com");
        assertEquals("www.image1.com",test.getImageLink());
    }

    @Test
    public void testGetStory() {
        assertEquals("storytest",test.getStory());
    }

    @Test
    public void testSetStory() {
        test.setStory("storytest1");
        assertEquals("storytest1",test.getStory());
    }

    @Test
    public void testGetDescription() {
        assertEquals("titletest",test.getDescription());
    }

    @Test
    public void testSetDescription() {
        test.setDescription("titletest1");
        assertEquals("titletest1",test.getDescription());
    }
}