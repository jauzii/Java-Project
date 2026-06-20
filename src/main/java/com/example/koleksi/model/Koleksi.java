package com.example.koleksi.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "koleksi")
public class Koleksi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_koleksi")
    private Long idKoleksi;

    @Column(name = "nama_barang", nullable = false, length = 100)
    private String namaBarang;

    @Column(name = "harga_beli")
    private Integer hargaBeli;

    @Column(name = "harga_jual")
    private Integer hargaJual;

    @Column(length = 20)
    private String kondisi;

    @Column(name = "tanggal_beli")
    private LocalDate tanggalBeli;

    @Column(name = "status_barang", length = 20)
    private String statusBarang;

    @ManyToOne
    @JoinColumn(name = "id_kategori")
    private Kategori kategori;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public Koleksi() {}

    public Long getIdKoleksi() { return idKoleksi; }
    public void setIdKoleksi(Long idKoleksi) { this.idKoleksi = idKoleksi; }
    public String getNamaBarang() { return namaBarang; }
    public void setNamaBarang(String namaBarang) { this.namaBarang = namaBarang; }
    public Integer getHargaBeli() { return hargaBeli; }
    public void setHargaBeli(Integer hargaBeli) { this.hargaBeli = hargaBeli; }
    public Integer getHargaJual() { return hargaJual; }
    public void setHargaJual(Integer hargaJual) { this.hargaJual = hargaJual; }
    public String getKondisi() { return kondisi; }
    public void setKondisi(String kondisi) { this.kondisi = kondisi; }
    public LocalDate getTanggalBeli() { return tanggalBeli; }
    public void setTanggalBeli(LocalDate tanggalBeli) { this.tanggalBeli = tanggalBeli; }
    public String getStatusBarang() { return statusBarang; }
    public void setStatusBarang(String statusBarang) { this.statusBarang = statusBarang; }
    public Kategori getKategori() { return kategori; }
    public void setKategori(Kategori kategori) { this.kategori = kategori; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
