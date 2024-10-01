	-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 12, 2020 at 09:43 AM
-- Server version: 10.1.32-MariaDB
-- PHP Version: 7.2.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

drop database lapakin;
create database lapakin;
use lapakin;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_lapakin`
--

-- --------------------------------------------------------

--
-- Table structure for table `t_admin`
--

CREATE TABLE `t_admin` (
  `id` varchar(12) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` text,
  `nama` varchar(30) DEFAULT NULL,
  `no_hp` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_admin`
--

INSERT INTO `t_admin` (`id`, `email`, `password`, `nama`, `no_hp`) VALUES
('A001', 'admin@lapakin.com', md5('admin'), 'Admin', '082351175926');

-- --------------------------------------------------------

--
-- Table structure for table `t_det_transaksi`
--

CREATE TABLE `t_det_transaksi` (
  `no_transaksi` varchar(10) DEFAULT NULL,
  `id_produk` varchar(10) DEFAULT NULL,
  `jumlah` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_det_transaksi`
--

INSERT INTO `t_det_transaksi` (`no_transaksi`, `id_produk`, `jumlah`) VALUES
('T001', 'P001', 5),
('T002', 'P002', 3),
('T003', 'P002', 4),
('T005', 'P007', 6),
('T003', 'P002', 6),
('T005', 'P009', 1),
('T006', 'P001', 2),
('T004', 'P009', 4),
('T003', 'P002', 5),
('T005', 'P007', 10);

-- --------------------------------------------------------

--
-- Table structure for table `t_kategori`
--

CREATE TABLE `t_kategori` (
  `id` varchar(5) NOT NULL,
  `kategori` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_kategori`
--

INSERT INTO `t_kategori` (`id`, `kategori`) VALUES
('K001', 'Minuman'),
('K002', 'Beras'),
('K003', 'Minyak'),
('K004', 'Sabun'),
('K005', 'Snack'),
('K006', 'Deterjen'),
('K007', 'Lotion'),
('K008', 'Powder'),
('K009', 'Parfum'),
('K010', 'Deodorant');

-- --------------------------------------------------------

--
-- Table structure for table `t_member`
--

CREATE TABLE `t_member` (
  `id` varchar(12) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` text NOT NULL,
  `nama` varchar(25) DEFAULT NULL,
  `alamat` text,
  `no_hp` varchar(13) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_member`
--

INSERT INTO `t_member` (`id`, `email`, `password`, `nama`, `alamat`, `no_hp`) VALUES
('M001', 'maryabatubara@gmail.com',md5('user'), 'Marya Batubara', 'Jl. Rowosari No.2', '085312345678'),
('M002', 'debtya@gmail.com', md5('user'), 'Debtya Vanni', 'Jl. Yosudarso No.12 Rumbai', '082154567324'),
('M003', 'carda@gmail.com', md5('user'), 'Carda Zebua', 'Jl. Patria Sari No.5 Rumbai', '082254672345'),
('M004', 'martin@gmail.com', md5('user'), 'Martin Novandra', 'Jl. Riau Ujung No.23', '081235456786'),
('M005', 'winli@gmail.com', md5('user'), 'Winli', 'Jl. Durian No.11', '081145769834'),
('M006', 'eurico@gmail.com',md5('user'), 'Eurico', 'Jl. Merdeka No.56 Pekanbaru', '089824759682'),
('M007', 'cristin@gmail.com', md5('user'), 'Cristin', 'Jl. Paus No.12 Rumbai', '087734759682'),
('M008', 'gohar@gmail.com', md5('user'), 'Gohar Tamba', 'Jl. Rowosari No.31 Rumbai', '085311744920'),
('M009', 'iqbal@gmail.com', md5('user'), 'Iqbal Maulana', 'Jl. Nangka No. 154 Pekanbaru', '082311678492'),
('M010', 'widya@gmail.com', md5('user'), 'Widya', 'Jl. Budi Sari No.5 Rumbai', '087724525096');

-- --------------------------------------------------------

--
-- Table structure for table `t_produk`
--

CREATE TABLE `t_produk` (
  `id_produk` varchar(10) NOT NULL,
  `id_toko` varchar(12) DEFAULT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `deskripsi` text,
  `stok` int(4) DEFAULT NULL,
  `harga` int(8) DEFAULT NULL,
  `satuan` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_produk`
--

INSERT INTO `t_produk` (`id_produk`, `id_toko`, `nama`, `deskripsi`, `stok`, `harga`, `satuan`) VALUES
('P0011', 'T001', 'Beras Berastagi', 'Enak, Pulen dan Harum', 15, 150000, 'pcs'),
('P0012', 'T001', 'Garam Nelayan', 'Asli Asinnya', 30, 3000, 'pcs'),
('P0013', 'T001', 'Gula Putih', 'Asli dari Tebu', 50, 10000, 'kg'),
('P0014', 'T001', 'Rinso Deterjen', 'Bersihkan noda membandel', 20, 15000, 'pcs'),
('P0015', 'T001', 'Citra Body Lotion', 'Harum dan melindungi kulit', 25, 12000, 'pcs'),
('P0016', 'T001', 'Bravas XOX Man', 'Harum dan tahan lama', 10, 100000, 'pcs'),
('P0017', 'T001', 'Lifeboy', 'Bersih dan wangi', 30, 5000, 'pcs'),
('P0018', 'T001', 'Bimoli', 'Membuat masakan jadi renyah dan matang sempurna', 25, 15000, 'kg'),
('P0019', 'T001', 'Choky-choky', 'Coklatnya Meleleh', 15, 30000, 'pack'),
('P0110', 'T001', 'Rexona Men', 'Jaga aroma ketiakmu saat beraktivitas', 20, 13000, 'pcs'),
('P0021', 'T001', 'Beras Berastagi', 'Enak, Pulen dan Harum', 15, 150000, 'pcs'),
('P0022', 'T002', 'Garam Nelayan', 'Asli Asinnya', 30, 3000, 'pcs'),
('P0023', 'T002', 'Gula Putih', 'Asli dari Tebu', 50, 10000, 'kg'),
('P0024', 'T002', 'Rinso Deterjen', 'Bersihkan noda membandel', 20, 15000, 'pcs'),
('P0025', 'T002', 'Citra Body Lotion', 'Harum dan melindungi kulit', 25, 12000, 'pcs'),
('P0026', 'T002', 'Bravas XOX Man', 'Harum dan tahan lama', 10, 100000, 'pcs'),
('P0027', 'T002', 'Lifeboy', 'Bersih dan wangi', 30, 5000, 'pcs'),
('P0028', 'T002', 'Bimoli', 'Membuat masakan jadi renyah dan matang sempurna', 25, 15000, 'kg'),
('P0029', 'T002', 'Choky-choky', 'Coklatnya Meleleh', 15, 30000, 'pack'),
('P0210', 'T002', 'Rexona Men', 'Jaga aroma ketiakmu saat beraktivitas', 20, 13000, 'pcs'),
('P001', 'T001', 'Beras Berastagi', 'Enak, Pulen dan Harum', 15, 150000, 'pcs'),
('P002', 'T002', 'Garam Nelayan', 'Asli Asinnya', 30, 3000, 'pcs'),
('P003', 'T010', 'Gula Putih', 'Asli dari Tebu', 50, 10000, 'kg'),
('P004', 'T006', 'Rinso Deterjen', 'Bersihkan noda membandel', 20, 15000, 'pcs'),
('P005', 'T003', 'Citra Body Lotion', 'Harum dan melindungi kulit', 25, 12000, 'pcs'),
('P006', 'T008', 'Bravas XOX Man', 'Harum dan tahan lama', 10, 100000, 'pcs'),
('P007', 'T009', 'Lifeboy', 'Bersih dan wangi', 30, 5000, 'pcs'),
('P008', 'T004', 'Bimoli', 'Membuat masakan jadi renyah dan matang sempurna', 25, 15000, 'kg'),
('P009', 'T005', 'Choky-choky', 'Coklatnya Meleleh', 15, 30000, 'pack'),
('P010', 'T007', 'Rexona Men', 'Jaga aroma ketiakmu saat beraktivitas', 20, 13000, 'pcs');

-- --------------------------------------------------------

--
-- Table structure for table `t_toko`
--

CREATE TABLE `t_toko` (
  `id` varchar(12) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` text NOT NULL,
  `nama` varchar(25) DEFAULT NULL,
  `pemilik` varchar(25) DEFAULT NULL,
  `deskripsi` text,
  `alamat` text,
  `no_hp` varchar(13) DEFAULT NULL,
  `koordinat` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_toko`
--

INSERT INTO `t_toko` (`id`, `email`, `password`, `nama`, `pemilik`, `deskripsi`, `alamat`, `no_hp`, `koordinat`) VALUES
('T001', 'sukamaju@gmail.com', md5('toko'), 'Suka Maju', 'Andreas', 'Murah dan Terjamin', 'Jl. Pangeran No.101 Pekanbaru', '081278964324', NULL),
('T002', 'jayabaru@gmail.com', md5('toko'), 'Jaya Baru', 'Jaya Nasution', 'Diskon Besar-besaran', 'Jl. Yosudarso No.55 Rumbai', '082351175926', NULL),
('T003', 'gasterus@gmail.com', md5('toko'), 'Gas Terus', 'Firman Sanjaya', 'Lengkap dan Murah', 'Jl. Yosudarso No.80 Rumbai', '082154097658', NULL),
('T004', 'berkat@gmail.com', md5('toko'), 'UD Berkat', 'Berkat Sitorus', 'Bebas belanja kapanpun', 'Jl. Umbansari Atas No.9 Rumbai ', '081149867580', NULL),
('T005', 'abadi@gmail.com', md5('toko'), 'UD Abadi ', 'Abadi ', 'Murah Murah Murah', 'Jl. Pramuka No.72 Panam', '085234856029', NULL),
('T006', 'daunsejahtera@gmail.com', md5('toko'), 'UD Sejahtera', 'Udin Slamet', 'Berkualitas', 'Jl. Berdikari No.44 Pekanbaru', '082356829304', NULL),
('T007', 'setia@gmail.com',md5('toko'), 'Setia Jaya', 'Fong Xiu', 'Terjangkau, kalau tidak percaya cek Toko Sebelah', 'Jl. Sudirman No.140 Pekanbaru', '081246872098', NULL),
('T008', 'sinar@gmail.com', md5('toko'), 'Sinar Jaya', 'Wong Xiau', 'Dilihat-lihat dulu', 'Jl. Setiabudi No.97 Pekanbaru', '082357650789', NULL),
('T009', 'mandiri@gmail.com', md5('toko'), 'UD Mandiri', 'Jerome Polim Sijabat', 'Murah dan Terjamin', 'Jl. Rindu No.67 Pekanbaru', '082316789342', NULL),
('T010', 'serbaada@gmail.com', md5('toko'), 'Serba Ada', 'Wawan', 'Semua ada, Serba aada', 'Jl. Paus No.54 Rumbai', '082248620927', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `t_transaksi`
--

CREATE TABLE `t_transaksi` (
  `no_transaksi` varchar(10) NOT NULL,
  `id_toko` varchar(12) DEFAULT NULL,
  `id_member` varchar(12) DEFAULT NULL,
  `tgl_transaksi` date DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `t_transaksi`
--

INSERT INTO `t_transaksi` (`no_transaksi`, `id_toko`, `id_member`, `tgl_transaksi`, `status`) VALUES
('T001', 'T001', 'M001', '2020-01-05', 'Selesai'),
('T002', 'T002', 'M002', '2020-01-04', 'Proses'),
('T003', 'T003', 'M002', '2020-01-06', 'Proses'),
('T004', 'T001', 'M005', '2020-01-08', 'Selesai'),
('T005', 'T004', 'M010', '2020-01-01', 'Selesai'),
('T006', 'T004', 'M004', '2020-01-07', 'Selesai'),
('T007', 'T002', 'M003', '2020-01-01', 'Proses'),
('T008', 'T008', 'M003', '2020-01-14', 'Selesai'),
('T009', 'T004', 'M006', '2020-01-01', 'Proses'),
('T010', 'T001', 'M003', '2020-01-08', 'Selesai');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `t_admin`
--
ALTER TABLE `t_admin`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `t_det_transaksi`
--
ALTER TABLE `t_det_transaksi`
  ADD KEY `no_transaksi` (`no_transaksi`),
  ADD KEY `id_produk` (`id_produk`);

--
-- Indexes for table `t_kategori`
--
ALTER TABLE `t_kategori`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `t_member`
--
ALTER TABLE `t_member`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `t_produk`
--
ALTER TABLE `t_produk`
  ADD PRIMARY KEY (`id_produk`),
  ADD KEY `id_toko` (`id_toko`);

--
-- Indexes for table `t_toko`
--
ALTER TABLE `t_toko`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `t_transaksi`
--
ALTER TABLE `t_transaksi`
  ADD PRIMARY KEY (`no_transaksi`),
  ADD KEY `id_member` (`id_member`),
  ADD KEY `id_toko` (`id_toko`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `t_det_transaksi`
--
ALTER TABLE `t_det_transaksi`
  ADD CONSTRAINT `t_det_transaksi_ibfk_1` FOREIGN KEY (`no_transaksi`) REFERENCES `t_transaksi` (`no_transaksi`),
  ADD CONSTRAINT `t_det_transaksi_ibfk_2` FOREIGN KEY (`id_produk`) REFERENCES `t_produk` (`id_produk`);

--
-- Constraints for table `t_produk`
--
ALTER TABLE `t_produk`
  ADD CONSTRAINT `t_produk_ibfk_1` FOREIGN KEY (`id_toko`) REFERENCES `t_toko` (`id`),
  ADD CONSTRAINT `t_produk_ibfk_2` FOREIGN KEY (`id_toko`) REFERENCES `t_toko` (`id`);

--
-- Constraints for table `t_transaksi`
--
ALTER TABLE `t_transaksi`
  ADD CONSTRAINT `t_transaksi_ibfk_1` FOREIGN KEY (`id_toko`) REFERENCES `t_toko` (`id`),
  ADD CONSTRAINT `t_transaksi_ibfk_2` FOREIGN KEY (`id_member`) REFERENCES `t_member` (`id`),
  ADD CONSTRAINT `t_transaksi_ibfk_3` FOREIGN KEY (`id_toko`) REFERENCES `t_toko` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
