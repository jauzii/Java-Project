package com.example.koleksi.service.impl;

import com.example.koleksi.model.Koleksi;
import com.example.koleksi.repository.KoleksiRepository;
import com.example.koleksi.service.KoleksiService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@SuppressWarnings("null")
public class KoleksiServiceImpl implements KoleksiService {

    private final KoleksiRepository repo;

    public KoleksiServiceImpl(KoleksiRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Koleksi> findAll() { return repo.findAll(); }

    @Override
    public Optional<Koleksi> findById(Long id) { return repo.findById(id); }

    @Override
    public Koleksi save(Koleksi koleksi) { return repo.save(koleksi); }

    @Override
    public void deleteById(Long id) { repo.deleteById(id); }

    @Override
    public List<Koleksi> searchByName(String q) { return repo.findByNamaBarangContainingIgnoreCase(q); }

    @Override
    public List<Koleksi> findByStatus(String status) { return repo.findByStatusBarang(status); }

    @Override
    public int totalValue() {
        return repo.findAll().stream()
                .mapToInt(k -> k.getHargaBeli() == null ? 0 : k.getHargaBeli())
                .sum();
    }

    @Override
    public long countByStatus(String status) {
        return repo.findByStatusBarang(status).size();
    }

    @Override
    public Optional<Koleksi> findMostExpensive() {
        return repo.findAll().stream()
                .filter(k -> k.getHargaBeli() != null)
                .max(Comparator.comparingInt(Koleksi::getHargaBeli));
    }
}