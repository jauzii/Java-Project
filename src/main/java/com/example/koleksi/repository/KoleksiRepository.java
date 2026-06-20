package com.example.koleksi.repository;

import com.example.koleksi.model.Koleksi;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface KoleksiRepository extends JpaRepository<Koleksi, Long> {
    List<Koleksi> findByNamaBarangContainingIgnoreCase(String namaBarang);
    List<Koleksi> findByStatusBarang(String statusBarang);
}
