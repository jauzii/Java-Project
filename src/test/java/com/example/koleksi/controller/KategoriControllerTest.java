package com.example.koleksi.controller;

import com.example.koleksi.model.Kategori;
import com.example.koleksi.repository.KategoriRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ConcurrentModel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SuppressWarnings("null")
class KategoriControllerTest {

    @Mock
    KategoriRepository kategoriRepository;

    @Test
    void listAndAddFormAndSave() {
        KategoriController ctrl = new KategoriController(kategoriRepository);
        when(kategoriRepository.findAll()).thenReturn(java.util.Collections.emptyList());

        ConcurrentModel model = new ConcurrentModel();
        String listView = ctrl.list(model);
        assertEquals("kategori/list", listView);
        assertNotNull(model.getAttribute("categories"));

        ConcurrentModel model2 = new ConcurrentModel();
        String formView = ctrl.addForm(model2);
        assertEquals("kategori/form", formView);
        assertTrue(model2.getAttribute("kategori") instanceof Kategori);

        Kategori k = new Kategori();
        String redirect = ctrl.save(k);
        assertEquals("redirect:/kategori", redirect);

        ArgumentCaptor<Kategori> cap = ArgumentCaptor.forClass(Kategori.class);
        verify(kategoriRepository).save(cap.capture());
    }
}