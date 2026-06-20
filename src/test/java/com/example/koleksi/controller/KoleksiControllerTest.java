package com.example.koleksi.controller;

import com.example.koleksi.model.Koleksi;
import com.example.koleksi.repository.KategoriRepository;
import com.example.koleksi.service.KoleksiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.ui.ExtendedModelMap;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class KoleksiControllerTest {

    private KoleksiService koleksiService;
    private KategoriRepository kategoriRepository;
    private KoleksiController controller;

    @BeforeEach
    void setUp() {
        koleksiService = mock(KoleksiService.class);
        kategoriRepository = mock(KategoriRepository.class);
        controller = new KoleksiController(koleksiService, kategoriRepository);
    }

    @Test
    void listReturnsViewAndModel() {
        when(koleksiService.findAll()).thenReturn(List.of(new Koleksi()));
        when(kategoriRepository.findAll()).thenReturn(List.of());
        Model model = new ExtendedModelMap();
        String view = controller.list(null, null, model);
        assertEquals("koleksi/list", view);
        assertTrue(model.containsAttribute("items"));
        assertTrue(model.containsAttribute("categories"));
    }

    @Test
    void addFormPreparesModel() {
        when(kategoriRepository.findAll()).thenReturn(List.of());
        Model model = new ExtendedModelMap();
        String view = controller.addForm(model);
        assertEquals("koleksi/form", view);
        assertTrue(model.containsAttribute("koleksi"));
    }

    @Test
    void saveRedirects() {
        Koleksi k = new Koleksi();
        String view = controller.save(k);
        verify(koleksiService).save(k);
        assertEquals("redirect:/koleksi", view);
    }

    @Test
    void editFormAddsKoleksiWhenPresent() {
        Koleksi k = new Koleksi();
        when(koleksiService.findById(5L)).thenReturn(Optional.of(k));
        when(kategoriRepository.findAll()).thenReturn(List.of());
        Model model = new ExtendedModelMap();
        String view = controller.editForm(5L, model);
        assertEquals("koleksi/form", view);
        assertTrue(model.containsAttribute("koleksi"));
    }

    @Test
    void deleteRedirects() {
        String view = controller.delete(7L);
        verify(koleksiService).deleteById(7L);
        assertEquals("redirect:/koleksi", view);
    }
}
