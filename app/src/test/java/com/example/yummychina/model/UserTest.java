package com.example.yummychina.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class used to test the User class' methods
 */
public class UserTest {
    User test;

    @Before
    public void setUp() throws Exception {
        test = new User("John", "JJJ","test@mail.com","1234");
    }

    /**
     * Tests getName method
     */
    @Test
    public void getName() {
        assertEquals("John", test.getName());
    }

    /**
     * Test SetName method
     */
    @Test
    public void setName() {
        test.setName("Smith");
        assertEquals("Smith", test.getName());

    }

    /**
     * Test get User name
     */
    @Test
    public void getUsername() {
        assertEquals("JJJ", test.getUsername());
    }

    /**
     * Test set User name
     */
    @Test
    public void setUsername() {
        test.setUsername("SSS");
        assertEquals("SSS", test.getUsername());
    }

    /**
     * Test get Email
     */
    @Test
    public void getEmail() {
        assertEquals("test@mail.com", test.getEmail());
    }

    /**
     * Test set Email
     */
    @Test
    public void setEmail() {
        test.setEmail("test1@mail.com");
        assertEquals("test1@mail.com", test.getEmail());
    }

    /**
     * Test get Phone number
     */
    @Test
    public void getPhoneNo() {
        assertEquals("1234", test.getPhoneNo());
    }

    /**
     * Test set Phone number
     */
    @Test
    public void setPhoneNo() {
        test.setPhoneNo("11111");
        assertEquals("11111", test.getPhoneNo());
    }
}