package com.example.koleksi;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;

import static org.mockito.ArgumentMatchers.eq;

class KoleksiAppApplicationTest {

    @Test
    void mainInvokesSpringApplicationRun() {
        try (MockedStatic<SpringApplication> springApplication = Mockito.mockStatic(SpringApplication.class)) {
            String[] args = {"--test"};

            KoleksiAppApplication.main(args);

            springApplication.verify(() -> SpringApplication.run(eq(KoleksiAppApplication.class), eq(args)));
        }
    }
}
