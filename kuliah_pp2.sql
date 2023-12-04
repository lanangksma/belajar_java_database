-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Dec 04, 2023 at 12:15 PM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kuliah_pp2`
--

-- --------------------------------------------------------

--
-- Table structure for table `biodata`
--

CREATE TABLE `biodata` (
  `id` varchar(255) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `no_hp` char(20) NOT NULL,
  `jenis_kelamin` varchar(20) NOT NULL,
  `wna` varchar(40) NOT NULL,
  `alamat` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `biodata`
--

INSERT INTO `biodata` (`id`, `nama`, `no_hp`, `jenis_kelamin`, `wna`, `alamat`) VALUES
('e278877d-887f-4def-8a4d-77e376619e97', 'Fergusson', '920192019201', 'Laki-laki', 'WNA', 'Londong Inggris'),
('fbd2daa0-0434-46ad-9f40-311d327129b5', 'Myriad Von Betrice', '0219374286481', 'Perempuan', 'WNI', 'Midroute, Gilaudres'),
('92e1c4c7-8e23-479a-b88a-210888f21955', 'Lanang', '0912901837812', 'Laki-laki', 'WNI', 'Bandung, Indonesia'),
('3de5c60a-1e26-413d-a0fc-03c1339eeae9', 'Alex Sander', '081281928', 'Laki-laki', 'WNA', 'Grinder, Maliker');

-- --------------------------------------------------------

--
-- Table structure for table `jenis_member`
--

CREATE TABLE `jenis_member` (
  `id` varchar(255) NOT NULL,
  `nama` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `jenis_member`
--

INSERT INTO `jenis_member` (`id`, `nama`) VALUES
('00ee5b2d-2edd-4f5c-8be5-0d1f172196f6', 'Tets 2'),
('03cd1ad3-0c90-4611-ab28-16bd52a1edf9', 'Test'),
('4671531b-1009-4d30-957f-afb2d89e8250', 'dad'),
('883c7b84-b99b-40f7-9deb-e131c1b3ea81', 'Halo'),
('c6800d1d-3217-4e46-a6be-71b1c1aa7e17', '123'),
('de3c372a-156d-4f8f-84dd-d27ffc8205c9', 'delete please help'),
('f3b6bfa3-b9e3-4198-a27f-791eaf56543e', 'delete');

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE `member` (
  `id` varchar(255) NOT NULL,
  `jenis_member_id` varchar(255) DEFAULT NULL,
  `nama` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`id`, `jenis_member_id`, `nama`) VALUES
('01ea4ac8-cd6b-4114-8f08-4eb1396afded', '00ee5b2d-2edd-4f5c-8be5-0d1f172196f6', 'a'),
('0d2703dc-57b7-4c32-8c3c-62937ebe5d95', '03cd1ad3-0c90-4611-ab28-16bd52a1edf9', 'Aku gak tau lagi'),
('6dbff945-6728-4ec8-a666-dca49626e7d0', 'de3c372a-156d-4f8f-84dd-d27ffc8205c9', 'Oh shit here we go again'),
('8f1af843-e2ab-4099-a425-ea37fdf8a127', '883c7b84-b99b-40f7-9deb-e131c1b3ea81', 'Gas'),
('94b2a38a-4985-49d7-a5f9-c1261b83d332', '03cd1ad3-0c90-4611-ab28-16bd52a1edf9', 'Alex');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `jenis_member`
--
ALTER TABLE `jenis_member`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
