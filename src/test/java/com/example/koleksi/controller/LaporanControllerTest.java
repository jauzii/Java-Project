package com.example.koleksi.controller;

import com.example.koleksi.model.Koleksi;
import com.example.koleksi.service.KoleksiService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ConcurrentModel;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LaporanControllerTest {

    @Mock
    KoleksiService koleksiService;

    @Test
    void laporanCalculatesTotalKeuntungan() {
        LaporanController ctrl = new LaporanController(koleksiService);

        Koleksi k1 = new Koleksi();
        k1.setHargaBeli(10);
        k1.setHargaJual(15);
        Koleksi k2 = new Koleksi();
        k2.setHargaBeli(5);
        k2.setHargaJual(7);

        when(koleksiService.findByStatus("Terjual")).thenReturn(List.of(k1, k2));

        ConcurrentModel model = new ConcurrentModel();
        String view = ctrl.laporan(model);

        assertEquals("laporan", view);
        assertEquals( (15-10) + (7-5), model.getAttribute("totalKeuntungan"));
        assertEquals(List.of(k1, k2), model.getAttribute("terjual"));
    }
}
