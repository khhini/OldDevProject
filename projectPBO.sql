-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 17, 2019 at 09:29 PM
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
('PK-190403-01', '2019-04-03', 'sabun 2 bungkus', 20000, NULL),
('PK-190530-01', '2019-05-30', 'Listrik', 453000, NULL),
('PK-190605-01', '2019-06-05', 'Pembayaran Wifi', 481000, NULL),
('PK-190607-01', '2019-06-07', 'test', 10000, NULL),
('PS-190521-01', '2019-05-30', 'cucian 5.0 kg Atas Nama kiki', NULL, 25000),
('PS-190521-02', '2019-06-18', 'cucian javax.swing.JTextField[,108,91,122x27,layout=javax.swing.plaf.basic.BasicTextUI$UpdateHandler,alignmentX=0.0,alignmentY=0.0,border=javax.swing.plaf.synth.SynthBorder@483943b6,flags=288,maximumSize=,minimumSize=,preferredSize=,caretColor=,disabledTextColor=DerivedColor(color=142,143,145 parent=nimbusDisabledText offsets=0.0,0.0,0.0,0 pColor=142,143,145,editable=true,margin=javax.swing.plaf.InsetsUIResource[top=0,left=0,bottom=0,right=0],selectedTextColor=DerivedColor(color=255,255,255 parent=nimbusSelectedText offsets=0.0,0.0,0.0,0 pColor=255,255,255,selectionColor=DerivedColor(color=57,105,138 parent=nimbusSelectionBackground offsets=0.0,0.0,0.0,0 pColor=57,105,138,columns=0,columnWidth=0,command=,horizontalAlignment=LEADING] kg Atas Nama Kaka', NULL, 6000),
('PS-190527-02', '2019-06-17', 'cucian 5.0 kg Atas Nama juan', NULL, 25000),
('PS-190528-01', '2019-06-17', 'cucian javax.swing.JTextField[,108,91,122x27,layout=javax.swing.plaf.basic.BasicTextUI$UpdateHandler,alignmentX=0.0,alignmentY=0.0,border=javax.swing.plaf.synth.SynthBorder@5734587,flags=288,maximumSize=,minimumSize=,preferredSize=,caretColor=,disabledTextColor=DerivedColor(color=142,143,145 parent=nimbusDisabledText offsets=0.0,0.0,0.0,0 pColor=142,143,145,editable=true,margin=javax.swing.plaf.InsetsUIResource[top=0,left=0,bottom=0,right=0],selectedTextColor=DerivedColor(color=255,255,255 parent=nimbusSelectedText offsets=0.0,0.0,0.0,0 pColor=255,255,255,selectionColor=DerivedColor(color=57,105,138 parent=nimbusSelectionBackground offsets=0.0,0.0,0.0,0 pColor=57,105,138,columns=0,columnWidth=0,command=,horizontalAlignment=LEADING] kg Atas Nama bedil', NULL, 5000),
('PS-190528-02', '2019-06-17', 'cucian 4.0 kg Atas Nama da', NULL, 20000),
('PS-190617-01', '2019-06-18', 'cucian javax.swing.JTextField[,108,91,122x27,layout=javax.swing.plaf.basic.BasicTextUI$UpdateHandler,alignmentX=0.0,alignmentY=0.0,border=javax.swing.plaf.synth.SynthBorder@138e3916,flags=288,maximumSize=,minimumSize=,preferredSize=,caretColor=,disabledTextColor=DerivedColor(color=142,143,145 parent=nimbusDisabledText offsets=0.0,0.0,0.0,0 pColor=142,143,145,editable=true,margin=javax.swing.plaf.InsetsUIResource[top=0,left=0,bottom=0,right=0],selectedTextColor=DerivedColor(color=255,255,255 parent=nimbusSelectedText offsets=0.0,0.0,0.0,0 pColor=255,255,255,selectionColor=DerivedColor(color=57,105,138 parent=nimbusSelectionBackground offsets=0.0,0.0,0.0,0 pColor=57,105,138,columns=0,columnWidth=0,command=,horizontalAlignment=LEADING] kg Atas Nama Kiki', NULL, 12500);

-- --------------------------------------------------------

--
-- Table structure for table `pesanan`
--

CREATE TABLE `pesanan` (
  `notaPesanan` varchar(12) NOT NULL,
  `namaPemesan` varchar(20) DEFAULT NULL,
  `jumlah` int(3) DEFAULT NULL,
  `tipePemesanan` varchar(10) DEFAULT NULL,
  `harga` int(6) DEFAULT NULL,
  `tanggalAntar` date DEFAULT NULL,
  `tanggalSelesai` date DEFAULT NULL,
  `catatan` text,
  `status` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pesanan`
--

INSERT INTO `pesanan` (`notaPesanan`, `namaPemesan`, `jumlah`, `tipePemesanan`, `harga`, `tanggalAntar`, `tanggalSelesai`, `catatan`, `status`) VALUES
('PS-190521-01', 'kiki', 5, 'kg', 5000, '2019-05-21', '2019-05-23', 'tidak perlu\n', 'Diambil'),
('PS-190521-02', 'Kaka', 3, 'kg', 6000, '2019-05-21', '2019-05-22', 'iya', 'Diambil'),
('PS-190521-03', 'kiki', 5, 'item', 10000, '2019-05-21', '2019-05-23', 'tidak', 'Selesai'),
('PS-190521-04', 'Kiki', 5, 'item', 10000, '2019-05-21', '2019-05-23', 'tidak', 'Selesai'),
('PS-190523-01', 'abdi', 2, 'kg', 5000, '2019-05-23', '2019-05-25', '-', 'Selesai'),
('PS-190523-02', 'ditya', 3, 'kg', 6000, '2019-05-23', '2019-05-24', '-', 'Selesai'),
('PS-190527-01', 'bucin', 1, 'kg', 5000, '2019-05-27', '2019-05-29', 'ABC\n', 'Selesai'),
('PS-190527-02', 'juan', 5, 'kg', 5000, '2019-05-27', '2019-05-30', 'Mau cepat', 'Diambil'),
('PS-190528-01', 'bedil', 3, 'kg', 5000, '2019-05-28', '2019-05-30', 'pengen pakai molto', 'Diambil'),
('PS-190528-02', 'da', 4, 'kg', 5000, '2019-05-28', '2019-05-31', 'segera selesaikan, jika tidak saya marah', 'Diambil'),
('PS-190616-01', 'Martin', 5, 'kg', 6000, '2019-06-16', '2019-06-17', 'Cepat sekarang cucikan', 'Proses'),
('PS-190617-01', 'Kiki', 3, 'kg', 12500, '2019-06-17', '2019-06-19', 'Sudah', 'Diambil'),
('PS-190617-02', 'Kiki', 5, 'kg', 25500, '2019-06-17', '2019-06-19', 'Selesaikan', 'Waiting'),
('PS-190618-01', 'Winny', 4, 'kg', 20000, '2019-06-18', '2019-06-20', 'tidak', 'Selesai');

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
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
