package com.example.koleksi.service.impl;

import com.example.koleksi.model.Koleksi;
import com.example.koleksi.repository.KoleksiRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class KoleksiServiceImplTest {

    private KoleksiRepository repo;
    private KoleksiServiceImpl service;

    @BeforeEach
    void setUp() {
        repo = mock(KoleksiRepository.class);
        service = new KoleksiServiceImpl(repo);
    }

    @Test
    void findAllDelegates() {
        when(repo.findAll()).thenReturn(List.of(new Koleksi()));
        List<Koleksi> all = service.findAll();
        assertEquals(1, all.size());
        verify(repo).findAll();
    }

    @Test
    void findByIdDelegates() {
        Koleksi k = new Koleksi();
        when(repo.findById(1L)).thenReturn(Optional.of(k));
        Optional<Koleksi> r = service.findById(1L);
        assertTrue(r.isPresent());
        assertSame(k, r.get());
    }

    @Test
    void saveDelegates() {
        Koleksi k = new Koleksi();
        when(repo.save(k)).thenReturn(k);
        assertSame(k, service.save(k));
    }

    @Test
    void deleteByIdDelegates() {
        doNothing().when(repo).deleteById(2L);
        service.deleteById(2L);
        verify(repo).deleteById(2L);
    }

    @Test
    void totalValueHandlesNulls() {
        Koleksi a = new Koleksi(); a.setHargaBeli(100);
        Koleksi b = new Koleksi(); b.setHargaBeli(null);
        when(repo.findAll()).thenReturn(Arrays.asList(a, b));
        assertEquals(100, service.totalValue());
    }

    @Test
    void countByStatusUsesRepo() {
        Koleksi a = new Koleksi();
        when(repo.findByStatusBarang("OK")).thenReturn(List.of(a, a));
        assertEquals(2L, service.countByStatus("OK"));
    }

    @Test
    void findMostExpensiveReturnsMax() {
        Koleksi a = new Koleksi(); a.setHargaBeli(10);
        Koleksi b = new Koleksi(); b.setHargaBeli(30);
        Koleksi c = new Koleksi(); c.setHargaBeli(null);
        when(repo.findAll()).thenReturn(Arrays.asList(a, b, c));
        Optional<Koleksi> r = service.findMostExpensive();
        assertTrue(r.isPresent());
        assertEquals(30, r.get().getHargaBeli());
    }

    @Test
    void searchByNameDelegates() {
        when(repo.findByNamaBarangContainingIgnoreCase("x")).thenReturn(List.of());
        assertNotNull(service.searchByName("x"));
        verify(repo).findByNamaBarangContainingIgnoreCase("x");
    }

    @Test
    void findByStatusDelegates() {
        when(repo.findByStatusBarang("S")).thenReturn(List.of());
        assertNotNull(service.findByStatus("S"));
        verify(repo).findByStatusBarang("S");
    }
}
