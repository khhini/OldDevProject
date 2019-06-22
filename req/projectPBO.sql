-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 22, 2019 at 10:26 AM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `projectPBO`
--

-- --------------------------------------------------------

--
-- Table structure for table `laporan`
--

CREATE TABLE `laporan` (
  `notaLaporan` varchar(12) NOT NULL,
  `tanggal` date NOT NULL,
  `deskripsi` text,
  `pengeluaran` int(11) DEFAULT NULL,
  `pemasukan` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `laporan`
--

INSERT INTO `laporan` (`notaLaporan`, `tanggal`, `deskripsi`, `pengeluaran`, `pemasukan`) VALUES
('PS-190622-01', '2019-06-22', 'Pesanan Tanggal2019-06-22 Atas Nama Kiki', NULL, 70000),
('PS-190622-02', '2019-06-22', 'Pesanan Tanggal2019-06-22 Atas Nama Abdi', NULL, 25000),
('PS-190622-03', '2019-06-22', 'Pesanan Tanggal2019-06-22 Atas Nama Adit', NULL, 120000);

-- --------------------------------------------------------

--
-- Table structure for table `pesanan`
--

CREATE TABLE `pesanan` (
  `notaPesanan` varchar(12) NOT NULL,
  `namaPemesan` varchar(20) DEFAULT NULL,
  `tanggalAntar` date DEFAULT NULL,
  `tanggalSelesai` date DEFAULT NULL,
  `total` int(11) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pesanan`
--

INSERT INTO `pesanan` (`notaPesanan`, `namaPemesan`, `tanggalAntar`, `tanggalSelesai`, `total`, `status`) VALUES
('PS-190622-01', 'Kiki', '2019-06-22', '2019-06-24', 70000, 'Diambil'),
('PS-190622-02', 'Abdi', '2019-06-22', '2019-06-24', 25000, 'Diambil'),
('PS-190622-03', 'Adit', '2019-06-22', '2019-06-24', 120000, 'Diambil'),
('PS-190622-04', NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `subpesanan`
--

CREATE TABLE `subpesanan` (
  `noSubPesanan` varchar(14) NOT NULL,
  `notaPesanan` varchar(12) DEFAULT NULL,
  `jumlah` float DEFAULT NULL,
  `jenis` varchar(20) DEFAULT NULL,
  `harga` int(6) DEFAULT NULL,
  `catatan` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `subpesanan`
--

INSERT INTO `subpesanan` (`noSubPesanan`, `notaPesanan`, `jumlah`, `jenis`, `harga`, `catatan`) VALUES
('PS-190622-0101', 'PS-190622-01', 12, 'kg', 60000, 'Pisahkan Pakaian Putih'),
('PS-190622-0102', 'PS-190622-01', 1, 'item', 10000, 'Hilangkan Noda'),
('PS-190622-0201', 'PS-190622-02', 5, 'kg', 25000, 'Pakai Rinso'),
('PS-190622-0301', 'PS-190622-03', 12, 'item', 120000, 'Cuci satu satu');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `laporan`
--
ALTER TABLE `laporan`
  ADD PRIMARY KEY (`notaLaporan`);

--
-- Indexes for table `pesanan`
--
ALTER TABLE `pesanan`
  ADD PRIMARY KEY (`notaPesanan`);

--
-- Indexes for table `subpesanan`
--
ALTER TABLE `subpesanan`
  ADD PRIMARY KEY (`noSubPesanan`),
  ADD KEY `notaPesanan` (`notaPesanan`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `subpesanan`
--
ALTER TABLE `subpesanan`
  ADD CONSTRAINT `subpesanan_ibfk_1` FOREIGN KEY (`notaPesanan`) REFERENCES `pesanan` (`notaPesanan`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
