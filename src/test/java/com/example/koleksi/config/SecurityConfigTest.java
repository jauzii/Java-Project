package com.example.koleksi.config;

import com.example.koleksi.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SecurityConfigTest {

    @Mock
    UserService userService;

    @Test
    void beansAreCreated() {
        SecurityConfig cfg = new SecurityConfig(userService);

        assertNotNull(cfg.passwordEncoder());
        assertTrue(cfg.passwordEncoder() instanceof BCryptPasswordEncoder);

        DaoAuthenticationProvider provider = cfg.authenticationProvider();
        assertNotNull(provider);
        assertEquals(DaoAuthenticationProvider.class, provider.getClass());
    }
}
