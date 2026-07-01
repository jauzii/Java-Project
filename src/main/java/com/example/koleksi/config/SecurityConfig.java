package com.example.koleksi.config;

import com.example.koleksi.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
public class SecurityConfig {
    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @SuppressWarnings("deprecation")
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Dashboard & root BISA diakses guest (tanpa login).
                        // DashboardController sendiri yang nanti membedakan
                        // apakah user login atau tidak, lalu menampilkan
                        // data asli atau data kosong/dummy.
                        .requestMatchers("/", "/dashboard", "/login", "/css/**", "/js/**", "/images/**").permitAll()
                        // Semua route lain (koleksi, kategori, laporan, profile) WAJIB login
                        .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider())
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                // KUNCI UTAMA: kalau guest coba akses halaman yang butuh login,
                // JANGAN lempar ke /login. Lempar balik ke /dashboard dengan
                // parameter ?needLogin supaya dashboard bisa nampilin alert
                // peringatan, bukan langsung paksa login.
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(
                                new LoginUrlAuthenticationEntryPoint("/dashboard?needLogin")
                        )
                )
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}