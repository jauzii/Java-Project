package com.example.koleksi.service;

import com.example.koleksi.model.Koleksi;

import java.util.List;
import java.util.Optional;

public interface KoleksiService {
    List<Koleksi> findAll();
    Optional<Koleksi> findById(Long id);
    Koleksi save(Koleksi koleksi);
    void deleteById(Long id);
    List<Koleksi> searchByName(String q);
    List<Koleksi> findByStatus(String status);
    int totalValue();
    long countByStatus(String status);
    Optional<Koleksi> findMostExpensive();
}
