package com.example.yummychina.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * This class test all the methods in comment class
 */
public class CommentTest {

    Comment test;
    Date date;

    @Before
    public void setUp() throws Exception {
        date = new Date(20220803);
        test = new Comment("John","comment",date);
    }

    @Test
    public void getFromWhom() {
        assertEquals("John", test.getFromWhom());
    }

    @Test
    public void setFromWhom() {
        test.setFromWhom("John1");
        assertEquals("John1", test.getFromWhom());
    }

    @Test
    public void getContent() {
        assertEquals("comment", test.getContent());
    }

    @Test
    public void setContent() {
        test.setContent("comment1");
        assertEquals("comment1", test.getContent());
    }

    @Test
    public void getDate() {
        assertEquals(date, test.getDate());
    }

    @Test
    public void setDate() {
        test.setDate(new Date(20230803));
        assertEquals(new Date(20230803), test.getDate());
    }

    @Test
    public void getDateToString() {
        assertEquals("1969-12-31 at 21:37:00 PST", test.getDateToString());

    }
}