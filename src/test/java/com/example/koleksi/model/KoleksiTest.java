package com.example.koleksi.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class KoleksiTest {

    @Test
    void getterAndSetterMethodsShouldWork() {
        Koleksi entity = new Koleksi();
        Kategori kategori = new Kategori();
        User user = new User();

        entity.setIdKoleksi(42L);
        entity.setNamaBarang("Lukisan");
        entity.setHargaBeli(100);
        entity.setHargaJual(150);
        entity.setKondisi("Bagus");
        entity.setTanggalBeli(LocalDate.of(2026, 6, 10));
        entity.setStatusBarang("Terjual");
        entity.setKategori(kategori);
        entity.setUser(user);

        assertEquals(42L, entity.getIdKoleksi());
        assertEquals("Lukisan", entity.getNamaBarang());
        assertEquals(100, entity.getHargaBeli());
        assertEquals(150, entity.getHargaJual());
        assertEquals("Bagus", entity.getKondisi());
        assertEquals(LocalDate.of(2026, 6, 10), entity.getTanggalBeli());
        assertEquals("Terjual", entity.getStatusBarang());
        assertSame(kategori, entity.getKategori());
        assertSame(user, entity.getUser());
    }
}
