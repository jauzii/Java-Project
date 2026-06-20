package com.example.koleksi.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthControllerTest {

    @Test
    void loginReturnsView() {
        AuthController c = new AuthController();
        assertEquals("login", c.login());
    }
}
