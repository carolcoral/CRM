-- phpMyAdmin SQL Dump
-- version 4.7.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: 2018-11-10 01:34:11
-- 服务器版本： 5.6.35
-- PHP Version: 7.1.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `OnlineExam`
--
CREATE DATABASE IF NOT EXISTS `OnlineExam` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `OnlineExam`;

-- --------------------------------------------------------

--
-- 表的结构 `exam`
--

DROP TABLE IF EXISTS `exam`;
CREATE TABLE IF NOT EXISTS `exam` (
  `exam_id` int(20) NOT NULL AUTO_INCREMENT,
  `exam_name` varchar(20) NOT NULL,
  `grade_id` int(20) NOT NULL,
  `user_id` int(20) NOT NULL,
  `exam_addtime` date DEFAULT NULL,
  `test_ids` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`exam_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 插入之前先把表清空（truncate） `exam`
--

TRUNCATE TABLE `exam`;
-- --------------------------------------------------------

--
-- 表的结构 `grade`
--

DROP TABLE IF EXISTS `grade`;
CREATE TABLE IF NOT EXISTS `grade` (
  `grade_id` int(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(20) NOT NULL,
  `grade` varchar(7) NOT NULL,
  PRIMARY KEY (`grade_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 插入之前先把表清空（truncate） `grade`
--

TRUNCATE TABLE `grade`;
-- --------------------------------------------------------

--
-- 表的结构 `test`
--

DROP TABLE IF EXISTS `test`;
CREATE TABLE IF NOT EXISTS `test` (
  `test_id` int(50) NOT NULL AUTO_INCREMENT,
  `testContent` varchar(2000) NOT NULL,
  `testAnswer` varchar(2000) DEFAULT NULL,
  `testDiffic` int(1) NOT NULL,
  `test_addtime` datetime DEFAULT NULL,
  PRIMARY KEY (`test_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

--
-- 插入之前先把表清空（truncate） `test`
--

TRUNCATE TABLE `test`;
--
-- 转存表中的数据 `test`
--

INSERT INTO `test` (`test_id`, `testContent`, `testAnswer`, `testDiffic`, `test_addtime`) VALUES
(1, '1+1', '2', 3, '2018-01-02 00:00:00'),
(2, '2+1', '3', 3, '2018-01-02 17:10:15'),
(3, '2+3', '5', 3, '2018-01-02 17:10:15'),
(4, '2+4', '6', 3, '2018-01-02 17:10:15'),
(5, '2+5', '7', 3, '2018-01-02 17:10:15'),
(6, '2+6', '8', 3, '2018-01-02 17:10:15'),
(7, '2+2', '4', 3, '2018-01-02 17:10:15'),
(8, '3+1', '4', 3, '2018-01-02 17:11:14'),
(9, '4+2', '6', 3, '2018-01-02 17:11:14'),
(10, '5+2', '7', 3, '2018-01-02 17:11:14'),
(11, '6+2', '8', 3, '2018-01-02 17:11:14'),
(12, '7+2', '9', 3, '2018-01-02 17:11:14'),
(13, '1+1', '2', 3, '2018-01-02 00:00:00'),
(14, '2+1', '3', 3, '2018-01-02 17:10:15'),
(15, '2+3', '5', 3, '2018-01-02 17:10:15'),
(16, '2+4', '6', 3, '2018-01-02 17:10:15'),
(17, '2+5', '7', 3, '2018-01-02 17:10:15'),
(18, '2+6', '8', 3, '2018-01-02 17:10:15'),
(19, '2+2', '4', 3, '2018-01-02 17:10:15'),
(20, '3+1', '4', 3, '2018-01-02 17:11:14'),
(21, '4+2', '6', 3, '2018-01-02 17:11:14'),
(22, '5+2', '7', 3, '2018-01-02 17:11:14'),
(23, '6+2', '8', 3, '2018-01-02 17:11:14'),
(24, '7+2', '9', 3, '2018-01-02 17:11:14');

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL,
  `user_passwd` varchar(20) NOT NULL,
  `user_addtime` datetime NOT NULL,
  `isManager` tinyint(1) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- 插入之前先把表清空（truncate） `user`
--

TRUNCATE TABLE `user`;
--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`user_id`, `user_name`, `user_passwd`, `user_addtime`, `isManager`) VALUES
(0, 'admin', 'admin', '2018-01-02 00:00:00', 1),
(1, 'user', 'user', '2018-01-02 00:00:00', 0),
(6, 'test', 'test', '2018-01-02 00:00:00', 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
