-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 09, 2026 at 12:51 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `customer_feedback_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `description` text NOT NULL,
  `title` varchar(255) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `feedback`
--

INSERT INTO `feedback` (`id`, `created_at`, `description`, `title`, `updated_at`, `user_id`) VALUES
(1, '2026-07-09 12:53:37.000000', 'Product\r\nyghgjhjh', 'Good gvhb', '2026-07-09 16:01:14.000000', 4),
(2, '2026-07-09 15:32:10.000000', 'aman123\r\n', 'aman', '2026-07-09 15:32:14.000000', 3);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('ADMIN','USER') NOT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `full_name`, `password`, `role`, `username`) VALUES
(2, 'sheru@jfroce.com', 'Shreyash', '$2a$10$K3u8qxC7ok4wPwzdS.3bO.7I5NIqRDsOxjIrNuwUnoWP1npMBt5nW', 'USER', 'sheru'),
(3, 'aman@mail.com', 'Aman Seth', '$2a$10$Psm7iB0K.aywI0wsfXos9u6DqVszvhLSaClkSiFVng3P0bwu7tcXa', 'USER', 'aman'),
(4, 'rohan@jforce.com', 'Rohan Gupta', '$2a$10$XgeLYcoYyXoW7iVdz3iqYuOmcUPZOfVGludzknmI8TEdk0mHkDP9u', 'USER', 'rohan'),
(5, 'admin@jforce.com', 'Admin', '$2a$10$CddUm08rLhEr4x3aCEuIDurtyr46JddcVU88Cnl4PFny8qjoXMQZ6', 'ADMIN', 'admin'),
(6, 'yash@warik.com', 'Yash Warik', '$2a$10$vzggamQriN3SpFB5ZZiVteDHfNf2velkXML3YbBj1KE2/oQdot7vq', 'USER', 'warik');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKkfpy3yr8gaddnnd11oru7k19s` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`),
  ADD UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `feedback`
--
ALTER TABLE `feedback`
  ADD CONSTRAINT `FKpwwmhguqianghvi1wohmtsm8l` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
