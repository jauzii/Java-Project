package com.example.koleksi.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KategoriTest {

    @Test
    void getterAndSetterMethodsShouldWork() {
        Kategori kategori = new Kategori();
        kategori.setIdKategori(7L);
        kategori.setNamaKategori("Seni");

        assertEquals(7L, kategori.getIdKategori());
        assertEquals("Seni", kategori.getNamaKategori());
    }
}
