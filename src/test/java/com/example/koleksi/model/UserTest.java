package com.example.koleksi.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getterAndSetterMethodsShouldWork() {
        User user = new User();
        user.setIdUser(99L);
        user.setUsername("alice");
        user.setPassword("secret123");

        assertEquals(99L, user.getIdUser());
        assertEquals("alice", user.getUsername());
        assertEquals("secret123", user.getPassword());
    }
}
