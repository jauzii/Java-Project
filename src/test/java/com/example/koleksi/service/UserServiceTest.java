package com.example.koleksi.service;

import com.example.koleksi.model.User;
import com.example.koleksi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    void loadUserByUsernameFound() {
        User u = new User();
        u.setUsername("alice");
        u.setPassword("pass");
        when(userRepository.findByUsername("alice")).thenReturn(Optional.of(u));
        UserDetails d = userService.loadUserByUsername("alice");
        assertEquals("alice", d.getUsername());
        assertEquals("pass", d.getPassword());
    }

    @Test
    void loadUserByUsernameNotFound() {
        when(userRepository.findByUsername("bob")).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("bob"));
    }

    @Test
    void saveDelegates() {
        User u = new User();
        when(userRepository.save(u)).thenReturn(u);
        assertSame(u, userService.save(u));
    }
}
