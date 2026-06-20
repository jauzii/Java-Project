package com.example.koleksi.controller;

import com.example.koleksi.model.Koleksi;
import com.example.koleksi.service.KoleksiService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ConcurrentModel;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DashboardControllerTest {

    @Mock
    KoleksiService koleksiService;

    @Test
    void dashboardPopulatesModel() {
        DashboardController ctrl = new DashboardController(koleksiService);

        when(koleksiService.totalValue()).thenReturn(100);
        when(koleksiService.countByStatus("Dijual")).thenReturn(1L);
        when(koleksiService.countByStatus("Terjual")).thenReturn(2L);
        when(koleksiService.findAll()).thenReturn(List.of(new Koleksi()));
        Koleksi expensive = new Koleksi();
        when(koleksiService.findMostExpensive()).thenReturn(Optional.of(expensive));

        ConcurrentModel model = new ConcurrentModel();
        String view = ctrl.dashboard(model);

        assertEquals("dashboard", view);
        assertEquals(100, model.getAttribute("totalValue"));
        assertEquals(1L, model.getAttribute("countDijual"));
        assertEquals(2L, model.getAttribute("countTerjual"));
        assertEquals(1L, model.getAttribute("totalKoleksi"));
        assertSame(expensive, model.getAttribute("mostExpensive"));
    }
}
