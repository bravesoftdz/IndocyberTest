-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Inang: 127.0.0.1
-- Waktu pembuatan: 03 Okt 2014 pada 18.42
-- Versi Server: 5.5.32
-- Versi PHP: 5.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Basis data: `pointofsales`
--
CREATE DATABASE IF NOT EXISTS `pointofsales` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `pointofsales`;

-- --------------------------------------------------------

--
-- Struktur dari tabel `invoice`
--

CREATE TABLE IF NOT EXISTS `invoice` (
  `ID_INVOICE` bigint(20) NOT NULL AUTO_INCREMENT,
  `INVOICE_NO` varchar(10) DEFAULT NULL,
  `TRANS_DATE` timestamp NULL DEFAULT NULL,
  `CUSTOMER` varchar(50) DEFAULT NULL,
  `TOTAL` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`ID_INVOICE`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;


--
-- Struktur dari tabel `invoicedetails`
--

CREATE TABLE IF NOT EXISTS `invoicedetails` (
  `ID_DETAILS` bigint(20) NOT NULL AUTO_INCREMENT,
  `ID_PRODUCT` bigint(20) DEFAULT NULL,
  `INVOICE_NO` varchar(10) NOT NULL,
  `PRODUCT_NAME` varchar(30) DEFAULT NULL,
  `PRICE` decimal(10,0) DEFAULT NULL,
  `QTY` int(11) DEFAULT NULL,
  `SUB_TOTAL` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`ID_DETAILS`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;


--
-- Struktur dari tabel `log`
--

CREATE TABLE IF NOT EXISTS `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(50) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `product`
--

CREATE TABLE IF NOT EXISTS `product` (
  `ID_PRODUCT` bigint(20) NOT NULL AUTO_INCREMENT,
  `PRODUCT_NAME` varchar(30) DEFAULT NULL,
  `PRICE` decimal(10,0) DEFAULT NULL,
  `STOCK` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_PRODUCT`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data untuk tabel `product`
--

INSERT INTO `product` (`ID_PRODUCT`, `PRODUCT_NAME`, `PRICE`, `STOCK`) VALUES
(1, 'Pop Mie', '4500', 100),
(2, 'Indomie', '2000', 200),
(3, 'Sarimie', '2000', 150),
(4, 'Luwak White Coffee', '1500', 300),
(5, 'Susu Ultra', '4000', 100),
(6, 'Roma Kelapa', '8000', 200);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
