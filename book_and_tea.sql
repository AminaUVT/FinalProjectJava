drop database if exists final_project;

create database final_project;

use final_project;

-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 09, 2022 at 06:39 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `book_and_tea`
--

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins`
(
    `user_id`  int(4)      NOT NULL,
    `username` varchar(50) NOT NULL,
    `password` varchar(50) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books`
(
    `book_id` int(9)      NOT NULL,
    `title`   varchar(60) NOT NULL,
    `author`  varchar(40) NOT NULL,
    `price`   float(40)   NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers`
(
    `user_id`  int(4)      NOT NULL,
    `username` varchar(50) NOT NULL,
    `password` varchar(50) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `orderitems`
--

CREATE TABLE `orderitems`
(
    `orderitem_id` int(6)         NOT NULL,
    `order_id`     int(6)         NOT NULL,
    `product_id`   int(6)         NOT NULL,
    `customer_id`  int(6)         NOT NULL,
    `created_at`   date           NOT NULL,
    `quantity`     int(3)         NOT NULL,
    `unit_price`   decimal(10, 0) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `teas`
--

CREATE TABLE `teas`
(
    `tea_id` int(6)         NOT NULL,
    `brand`  varchar(30)        NOT NULL,
    `flavor` varchar(70)    NOT NULL,
    `price`  decimal(10, 2) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admins`
--
ALTER TABLE `admins`
    ADD PRIMARY KEY (`user_id`);
ALTER TABLE admins
    MODIFY user_id INTEGER NOT NULL AUTO_INCREMENT;

--
-- Indexes for table `books`
--
ALTER TABLE `books`
    ADD PRIMARY KEY (`book_id`);
ALTER TABLE books
    MODIFY book_id INTEGER NOT NULL AUTO_INCREMENT;

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
    ADD PRIMARY KEY (`user_id`);
ALTER TABLE customers
    MODIFY user_id INTEGER NOT NULL AUTO_INCREMENT;

--
-- Indexes for table `orderitems`
--
ALTER TABLE `orderitems`
    ADD PRIMARY KEY AUTO_INCREMENT (`orderitem_id`);
ALTER TABLE `orderitems`
    MODIFY orderitem_id INTEGER NOT NULL AUTO_INCREMENT;

--
-- Indexes for table `teas`
--
ALTER TABLE `teas`
    ADD PRIMARY KEY (`tea_id`);
COMMIT;
ALTER TABLE teas
    MODIFY tea_id INTEGER NOT NULL AUTO_INCREMENT;

/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;


use final_project;

INSERT INTO admins(`username`, `password`)
VALUES ('admin', 'admin');
INSERT INTO customers(`username`, `password`)
VALUES ('bogdan', 'bogdan');
INSERT INTO customers(`username`, `password`)
VALUES ('amina', 'amina');
INSERT INTO customers(`username`, `password`)
VALUES ('grim', 'grim');

INSERT INTO `final_project`.`books` (`book_id`, `title`, `author`, `price`)
VALUES ('101', 'The Diary of a Young Girl', 'Anne Frank', '12.99');
INSERT INTO `final_project`.`books` (`book_id`, `title`, `author`, `price`)
VALUES ('102', 'Becoming', 'Michelle Obama', '15');
INSERT INTO `final_project`.`books` (`book_id`, `title`, `author`, `price`)
VALUES ('103', 'I Know Why the Caged Bird Sings ', 'Maya Angelou', '12.49');
INSERT INTO `final_project`.`books` (`book_id`, `title`, `author`, `price`)
VALUES ('104', 'The Illustraded Long Walk to Freedom', 'Nelson Mandela', '20.99');
INSERT INTO `final_project`.`books` (`book_id`, `title`, `author`, `price`)
VALUES ('105', 'The Autobiography of Malcolm X', 'Malcolm X', '20');
INSERT INTO `final_project`.`books` (`book_id`, `title`, `author`, `price`)
VALUES ('106', 'The Autobiography of Benjamin Franklin', 'Benjamin Franklin', '12');
INSERT INTO `final_project`.`books` (`book_id`, `title`, `author`, `price`)
VALUES ('107', 'A Moveable Forest', 'Ernest Hemingway', '10');
INSERT INTO `final_project`.`books` (`book_id`, `title`, `author`, `price`)
VALUES ('108', 'Dreams From My Father', 'Barack Obama', '29');
INSERT INTO `final_project`.`books` (`book_id`, `title`, `author`, `price`)
VALUES ('109', 'La Vida Loca', 'Luis J. Rodriguez', '20');
INSERT INTO `final_project`.`books` (`book_id`, `title`, `author`, `price`)
VALUES ('110', 'Open', 'Andre Agassi', '14');

INSERT INTO `final_project`.`teas` (`tea_id`, `brand`, `flavor`, `price`) VALUES ('1001', 'Tazo', 'Herbal Tea', '1.99');
INSERT INTO `final_project`.`teas` (`tea_id`, `brand`, `flavor`, `price`) VALUES ('1002', 'Big Heart Teas', 'Mint', '0.89');
INSERT INTO `final_project`.`teas` (`tea_id`, `brand`, `flavor`, `price`) VALUES ('1003', 'Imperial', 'Raspberry', '3.99');
INSERT INTO `final_project`.`teas` (`tea_id`, `brand`, `flavor`, `price`) VALUES ('1004', 'Imperial', 'Ginger and Mint', '5');
INSERT INTO `final_project`.`teas` (`tea_id`, `brand`, `flavor`, `price`) VALUES ('1005', 'Big Heart Teas', 'Orange Peels', '0.49');

INSERT INTO `orderitems` VALUES (10,101,101,1,'2023-01-02',1,12),
                                (11,101,1001,1,'2023-01-08',2,2),
                                (12,101,102,1,'2023-01-05',3,13),
                                (13,102,103,2,'2023-01-02',9,14),
                                (14,102,1001,2,'2023-01-05',10,1.29),
                                (15,103,1002,3,'2023-01-01',2,1.34),
                                (16,103,104,3,'2023-01-08',3,25);


-- create views:
CREATE OR REPLACE VIEW customers_orders
AS
SELECT
    *
FROM
    customers
        INNER JOIN
    orderitems ON customers.user_id = orderitems.customer_id;

CREATE OR REPLACE VIEW orderitem_price
AS
SELECT
    unit_price,
    quantity,
    unit_price * quantity as "Total Price"
FROM
    orderitems;

CREATE OR REPLACE VIEW cheapest_teas
AS
SELECT
    brand, flavor, price
FROM
    teas
ORDER BY price;
