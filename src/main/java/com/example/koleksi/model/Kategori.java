package com.example.koleksi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "kategori")
public class Kategori {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_kategori")
    private Long idKategori;

    @Column(name = "nama_kategori", nullable = false, length = 50)
    private String namaKategori;

    public Kategori() {}

    public Long getIdKategori() { return idKategori; }
    public void setIdKategori(Long idKategori) { this.idKategori = idKategori; }
    public String getNamaKategori() { return namaKategori; }
    public void setNamaKategori(String namaKategori) { this.namaKategori = namaKategori; }
}
