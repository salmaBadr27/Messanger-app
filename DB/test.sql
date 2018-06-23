-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 23, 2018 at 04:53 PM
-- Server version: 10.1.32-MariaDB
-- PHP Version: 7.2.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- Table structure for table `messages`
--

CREATE TABLE `messages` (
  `Msg_Id` varchar(100) NOT NULL,
  `Msg_body` text NOT NULL,
  `sender` varchar(100) NOT NULL,
  `receiver` varchar(100) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `messages`
--

INSERT INTO `messages` (`Msg_Id`, `Msg_body`, `sender`, `receiver`, `created_at`) VALUES
('2207e082-2eae-47a9-8e79-eee6361e7543', 'bngrb el timestamp', 'amira', 'mmmm', '2018-06-22 20:49:18'),
('36f297db-0975-40ae-9709-e4c6ad4ef458', 'bngrb el timestamp tany', 'amira', 'mmmm', '2018-06-22 21:03:39'),
('4ed5cda3-6271-4622-906e-f2a961275e3c', 'bngrb el timestamp talt', 'amira', 'mmmm', '2018-06-22 21:05:17'),
('761620ff-1b83-4107-8fe4-15f31d4709b7', 'bngrb el timestamp rab3', 'amira', 'mmmm', '2018-06-22 21:15:04'),
('a831006e-18af-4210-8147-e63d13bb031d\r\n', 'bbb', 'salma', 'amira', '2018-06-22 20:13:56'),
('c5806889-c5ba-4af5-ba2a-90d049b2772e', 'hi', 'amira', 'salma', '2018-06-22 20:13:56'),
('nnncldppdddpdpdpdp', 'bbbb', 'mmmm', 'amira', '2018-06-22 20:13:56');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` varchar(100) NOT NULL,
  `User_Name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `E_mail` varchar(50) NOT NULL,
  `Mobile` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `User_Name`, `password`, `E_mail`, `Mobile`) VALUES
('7aa5b089-59ba-476c-bf8f-ee7203df04f1', 'amira', '123', 'mervat_mersal@hotmail.com', '01228335028'),
('6da7516e-0de0-4ac8-a701-5ad1d619fd93', 'badr4', '444', 'beauty_saloma@hotmail.com', '01285507936'),
('c5806889-c5ba-4af5-ba2a-90d049b2772e', 'mervat', '123', 'mervat_mersal@hotmail.com', '01228335028'),
('d4607b8b-3ac4-4792-9113-0d52bb8721b9', 'mmmm', '123', 'beauty_saloma@hotmail.com', '0122258*9963'),
('6c2f4dd3-522d-4089-b020-744cd72a0a45', 'moner', 'king', 'beauty_saloma@hotmail.com', '0122258*9963'),
('7a93a08c-0b2f-4ced-9917-2f4c9fecd15c', 'noha', 'sala7', 'm', '0122258*9963'),
('a831006e-18af-4210-8147-e63d13bb031d', 'salma', '123', 'beauty_saloma@hotmail.com', '01285507936'),
('cbb4a092-6563-485e-b218-3beea60d94b3', 'yasser', '456', 'yasser@hotmail.com', '0122258*9963');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`Msg_Id`(20)) USING BTREE,
  ADD KEY `sender` (`sender`),
  ADD KEY `receiver` (`receiver`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`User_Name`),
  ADD UNIQUE KEY `User_Name` (`User_Name`),
  ADD UNIQUE KEY `User_Name_2` (`User_Name`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `messages`
--
ALTER TABLE `messages`
  ADD CONSTRAINT `messages_ibfk_1` FOREIGN KEY (`sender`) REFERENCES `user` (`User_Name`),
  ADD CONSTRAINT `messages_ibfk_2` FOREIGN KEY (`receiver`) REFERENCES `user` (`User_Name`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
