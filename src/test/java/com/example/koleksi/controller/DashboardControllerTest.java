package com.example.koleksi.controller;

import com.example.koleksi.service.KoleksiService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ConcurrentModel;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DashboardControllerTest {

    @Mock
    KoleksiService koleksiService;

    @Test
    void dashboard_guestPath_returnsEmptyData() {
        // Di luar Spring context, SecurityContextHolder = null
        // -> isUserLoggedIn() = false -> semua data dikosongkan
        DashboardController ctrl = new DashboardController(koleksiService);
        ConcurrentModel model = new ConcurrentModel();

        String view = ctrl.dashboard(model, null);

        assertEquals("dashboard", view);
        assertEquals(false, model.getAttribute("isLoggedIn"));
        assertEquals(0, model.getAttribute("totalValue"));
        assertEquals(0L, model.getAttribute("countDijual"));
        assertEquals(0L, model.getAttribute("countTerjual"));
        assertEquals(0L, model.getAttribute("totalKoleksi"));
        assertNull(model.getAttribute("mostExpensive"));
        assertNull(model.getAttribute("needLoginWarning"));
    }

    @Test
    void dashboard_needLoginParam_setsWarningFlag() {
        DashboardController ctrl = new DashboardController(koleksiService);
        ConcurrentModel model = new ConcurrentModel();

        // Simulasi redirect dari SecurityConfig saat guest akses halaman terlarang
        ctrl.dashboard(model, "");

        assertEquals(true, model.getAttribute("needLoginWarning"));
    }
}