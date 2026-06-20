-- Create database and tables for Koleksi app
CREATE DATABASE IF NOT EXISTS koleksi_db;
USE koleksi_db;

CREATE TABLE IF NOT EXISTS user (
  id_user INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS kategori (
  id_kategori INT AUTO_INCREMENT PRIMARY KEY,
  nama_kategori VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS koleksi (
  id_koleksi INT AUTO_INCREMENT PRIMARY KEY,
  nama_barang VARCHAR(100) NOT NULL,
  harga_beli INT,
  harga_jual INT,
  kondisi VARCHAR(20),
  tanggal_beli DATE,
  status_barang VARCHAR(20),
  id_kategori INT,
  id_user INT,
  FOREIGN KEY (id_kategori) REFERENCES kategori(id_kategori),
  FOREIGN KEY (id_user) REFERENCES user(id_user)
);

-- Sample categories
INSERT IGNORE INTO kategori (id_kategori, nama_kategori) VALUES (1, 'Gundam'), (2, 'Action Figure'), (3, 'Nendoroid'), (4, 'Diecast');

-- Sample user (password placeholder: replace with hashed password in production)
INSERT IGNORE INTO user (id_user, username, password) VALUES (1, 'admin', 'adminpass');

-- Sample koleksi
INSERT IGNORE INTO koleksi (id_koleksi, nama_barang, harga_beli, harga_jual, kondisi, tanggal_beli, status_barang, id_kategori, id_user) VALUES
 (1, 'RX-78-2 MG', 700000, NULL, 'Baru', '2023-06-01', 'Dikoleksi', 1, 1),
 (2, 'Luffy Gear 5', 350000, 500000, 'Bekas', '2024-01-15', 'Dijual', 2, 1),
 (3, 'Gundam Exia', 850000, 1000000, 'Baru', '2022-11-12', 'Terjual', 1, 1);
