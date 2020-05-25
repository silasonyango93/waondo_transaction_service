-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 25, 2020 at 06:25 PM
-- Server version: 5.7.26
-- PHP Version: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `waondo`
--

-- --------------------------------------------------------

--
-- Table structure for table `academic_class_levels`
--

CREATE TABLE `academic_class_levels` (
  `AcademicClassLevelId` int(11) NOT NULL,
  `AcademicClassLevelName` varchar(9000) NOT NULL,
  `HierachyCode` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `academic_class_levels`
--

INSERT INTO `academic_class_levels` (`AcademicClassLevelId`, `AcademicClassLevelName`, `HierachyCode`) VALUES
(1, 'Form One', 1);

-- --------------------------------------------------------

--
-- Table structure for table `access_privileges`
--

CREATE TABLE `access_privileges` (
  `AccessPrivilegeId` int(11) NOT NULL,
  `AccessPrivilegeDescription` varchar(200) NOT NULL,
  `AccessPrivilegeCode` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `access_privileges`
--

INSERT INTO `access_privileges` (`AccessPrivilegeId`, `AccessPrivilegeDescription`, `AccessPrivilegeCode`) VALUES
(1, 'Login', 1),
(2, 'Register a student', 2),
(3, 'Correct a student\'s personal details', 3),
(4, 'Register a fee installment', 4),
(5, 'Correct a fee payment', 5),
(6, 'Delete a student', 6);

-- --------------------------------------------------------

--
-- Table structure for table `actual_terms`
--

CREATE TABLE `actual_terms` (
  `TermId` int(11) NOT NULL,
  `TermIterationId` int(11) DEFAULT NULL,
  `TermStartDate` date NOT NULL,
  `TermEndDate` date NOT NULL,
  `Year` year(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `actual_terms`
--

INSERT INTO `actual_terms` (`TermId`, `TermIterationId`, `TermStartDate`, `TermEndDate`, `Year`) VALUES
(3, 1, '2018-01-01', '2018-03-31', 2018),
(4, 2, '2018-05-01', '2018-07-31', 2018),
(5, 3, '2018-08-01', '2018-11-30', 2018),
(6, 1, '2019-01-01', '2019-03-31', 2019),
(7, 2, '2019-05-01', '2019-07-31', 2019),
(8, 3, '2019-09-01', '2019-11-30', 2019),
(9, 2, '2020-04-01', '2020-07-31', 2020);

-- --------------------------------------------------------

--
-- Table structure for table `actual_weeks`
--

CREATE TABLE `actual_weeks` (
  `ActualWeekId` int(11) NOT NULL,
  `TermId` int(11) NOT NULL,
  `WeekIterationId` int(11) NOT NULL,
  `WeekStartDate` date NOT NULL,
  `WeekEndDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `actual_weeks`
--

INSERT INTO `actual_weeks` (`ActualWeekId`, `TermId`, `WeekIterationId`, `WeekStartDate`, `WeekEndDate`) VALUES
(1, 6, 1, '2019-01-01', '2019-01-06');

-- --------------------------------------------------------

--
-- Table structure for table `carry_forwards`
--

CREATE TABLE `carry_forwards` (
  `CarryFowardId` int(11) NOT NULL,
  `StudentId` int(11) NOT NULL,
  `CarryForwardAmount` int(11) NOT NULL,
  `DateCarriedForward` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `carry_forwards`
--

INSERT INTO `carry_forwards` (`CarryFowardId`, `StudentId`, `CarryForwardAmount`, `DateCarriedForward`) VALUES
(0, 26, 0, '2020-05-01 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `classes`
--

CREATE TABLE `classes` (
  `ClassId` int(11) NOT NULL,
  `LotId` int(11) NOT NULL,
  `ClassStreamId` int(11) NOT NULL,
  `RegisteredDate` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `classes`
--

INSERT INTO `classes` (`ClassId`, `LotId`, `ClassStreamId`, `RegisteredDate`) VALUES
(1, 1, 1, '2020-04-24 12:33:37');

-- --------------------------------------------------------

--
-- Table structure for table `class_fee_structures`
--

CREATE TABLE `class_fee_structures` (
  `ClassFeeStructureId` int(11) NOT NULL,
  `FeeStructureId` int(11) NOT NULL,
  `AcademicClassLevelId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `class_fee_structures`
--

INSERT INTO `class_fee_structures` (`ClassFeeStructureId`, `FeeStructureId`, `AcademicClassLevelId`) VALUES
(1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `class_fee_structure_breakdown`
--

CREATE TABLE `class_fee_structure_breakdown` (
  `ClassFeeStructureBreakDownId` int(11) NOT NULL,
  `ClassFeeStructureId` int(11) NOT NULL,
  `StudentResidenceId` int(11) NOT NULL,
  `TermIterationId` int(11) NOT NULL,
  `FeeAmount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `class_fee_structure_breakdown`
--

INSERT INTO `class_fee_structure_breakdown` (`ClassFeeStructureBreakDownId`, `ClassFeeStructureId`, `StudentResidenceId`, `TermIterationId`, `FeeAmount`) VALUES
(4, 1, 2, 2, 30000),
(5, 1, 1, 2, 20000),
(6, 1, 1, 1, 20000),
(7, 1, 1, 3, 5000),
(8, 1, 2, 1, 5000),
(9, 1, 2, 3, 15000);

-- --------------------------------------------------------

--
-- Table structure for table `class_fee_structure_components`
--

CREATE TABLE `class_fee_structure_components` (
  `ClassFeeStructureComponentId` int(11) NOT NULL,
  `ClassFeeStructureId` int(11) NOT NULL,
  `FeeComponentId` int(11) NOT NULL,
  `FeeComponentRatio` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `class_fee_structure_components`
--

INSERT INTO `class_fee_structure_components` (`ClassFeeStructureComponentId`, `ClassFeeStructureId`, `FeeComponentId`, `FeeComponentRatio`) VALUES
(1, 1, 1, 30),
(2, 1, 2, 20),
(3, 1, 3, 20);

-- --------------------------------------------------------

--
-- Table structure for table `class_streams`
--

CREATE TABLE `class_streams` (
  `ClassStreamId` int(11) NOT NULL,
  `ClassStreamName` varchar(9000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `class_streams`
--

INSERT INTO `class_streams` (`ClassStreamId`, `ClassStreamName`) VALUES
(1, 'Yellow');

-- --------------------------------------------------------

--
-- Table structure for table `correction_descriptions`
--

CREATE TABLE `correction_descriptions` (
  `CorrectionDescriptionId` int(11) NOT NULL,
  `CorrectionDescription` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `correction_descriptions`
--

INSERT INTO `correction_descriptions` (`CorrectionDescriptionId`, `CorrectionDescription`) VALUES
(1, 'Wrong student paid for');

-- --------------------------------------------------------

--
-- Table structure for table `fee_components`
--

CREATE TABLE `fee_components` (
  `FeeComponentId` int(11) NOT NULL,
  `FeeComponentDescription` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `fee_components`
--

INSERT INTO `fee_components` (`FeeComponentId`, `FeeComponentDescription`) VALUES
(1, 'Administration'),
(2, 'PE'),
(3, 'Lunch Scheme'),
(4, 'RMI');

-- --------------------------------------------------------

--
-- Table structure for table `fee_corrections`
--

CREATE TABLE `fee_corrections` (
  `FeeCorrectionId` int(11) NOT NULL,
  `SessionLogId` int(11) NOT NULL,
  `UserSessionActivityId` int(11) NOT NULL,
  `CorrectionDescriptionId` int(11) NOT NULL,
  `StudentId` int(11) NOT NULL,
  `PreviousTermBalance` int(11) NOT NULL,
  `PreviousAnnualBalance` int(11) NOT NULL,
  `PreviousTotal` int(11) NOT NULL,
  `NextTermBalance` int(11) NOT NULL,
  `NextAnnualBalance` int(11) NOT NULL,
  `NextTotal` int(11) NOT NULL,
  `CorrectionDate` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fee_corrections`
--

INSERT INTO `fee_corrections` (`FeeCorrectionId`, `SessionLogId`, `UserSessionActivityId`, `CorrectionDescriptionId`, `StudentId`, `PreviousTermBalance`, `PreviousAnnualBalance`, `PreviousTotal`, `NextTermBalance`, `NextAnnualBalance`, `NextTotal`, `CorrectionDate`) VALUES
(0, 156, 161, 1, 26, 0, 0, 0, 0, 0, 0, '2020-05-01 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `fee_statements`
--

CREATE TABLE `fee_statements` (
  `FeeStatementId` int(11) NOT NULL,
  `StudentId` int(11) NOT NULL,
  `CurrentYearTotal` int(11) DEFAULT '0',
  `AlternateTotal` int(11) NOT NULL,
  `CurrentTermBalance` int(11) DEFAULT '0',
  `AnnualBalance` int(11) DEFAULT '0',
  `StudentWorth` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fee_statements`
--

INSERT INTO `fee_statements` (`FeeStatementId`, `StudentId`, `CurrentYearTotal`, `AlternateTotal`, `CurrentTermBalance`, `AnnualBalance`, `StudentWorth`) VALUES
(7, 20, 0, 0, 20000, 25000, 0),
(8, 21, 25300, 13300, 4700, 19700, 25300),
(9, 22, 34000, 34000, -14000, -43000, 34000),
(10, 23, 10000, 10000, 10000, 5000, 10000),
(11, 24, 10000, 10000, 10000, 5000, 10000),
(12, 25, 10000, 10000, 10000, 15000, 10000);

-- --------------------------------------------------------

--
-- Table structure for table `fee_structures`
--

CREATE TABLE `fee_structures` (
  `FeeStructureId` int(11) NOT NULL,
  `UserId` int(11) NOT NULL,
  `FeeStructureDescription` varchar(200) NOT NULL,
  `DateCreated` datetime NOT NULL,
  `IsCurrentFeeStructure` int(11) NOT NULL,
  `IsProspect` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `fee_structures`
--

INSERT INTO `fee_structures` (`FeeStructureId`, `UserId`, `FeeStructureDescription`, `DateCreated`, `IsCurrentFeeStructure`, `IsProspect`) VALUES
(1, 14, '2018 Fee Structure', '2020-05-05 20:32:17', 1, 0),
(2, 14, '2019 Fee Structure', '2020-05-05 20:41:41', 0, 0),
(3, 14, '2020 Fee Structure', '2020-05-05 20:41:57', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `gender`
--

CREATE TABLE `gender` (
  `GenderId` int(11) NOT NULL,
  `GenderDescription` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gender`
--

INSERT INTO `gender` (`GenderId`, `GenderDescription`) VALUES
(1, 'Male'),
(2, 'Female');

-- --------------------------------------------------------

--
-- Table structure for table `installments`
--

CREATE TABLE `installments` (
  `InstallmentId` int(11) NOT NULL,
  `StudentId` int(11) NOT NULL,
  `InstallmentAmount` int(11) NOT NULL,
  `InstallmentDate` datetime NOT NULL,
  `IsCarryForward` int(11) NOT NULL,
  `SessionLogId` int(11) NOT NULL,
  `UserSessionActivityId` int(11) NOT NULL,
  `InstallmentYear` year(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `installments`
--

INSERT INTO `installments` (`InstallmentId`, `StudentId`, `InstallmentAmount`, `InstallmentDate`, `IsCarryForward`, `SessionLogId`, `UserSessionActivityId`, `InstallmentYear`) VALUES
(0, 26, 0, '2020-05-01 00:00:00', 0, 156, 161, 2020),
(2, 18, 23000, '2020-05-24 22:54:40', 0, 249, 258, 2020),
(3, 22, 34000, '2020-05-24 22:57:02', 0, 250, 260, 2020),
(4, 23, 10000, '2020-05-25 14:43:09', 0, 253, 265, 2020),
(5, 24, 10000, '2020-05-25 14:49:17', 0, 254, 268, 2020),
(6, 25, 10000, '2020-05-25 15:02:21', 0, 255, 271, 2020),
(7, 21, 12000, '2020-05-01 19:44:47', 0, 256, 273, 2020),
(8, 22, 18000, '2020-05-24 19:48:10', 0, 257, 275, 2020),
(9, 22, 14000, '2020-05-01 20:44:31', 0, 258, 277, 2020),
(11, 22, 22000, '2020-05-25 21:08:30', 0, 259, 279, 2020),
(12, 21, 13300, '2020-05-25 21:10:52', 0, 260, 281, 2020);

-- --------------------------------------------------------

--
-- Table structure for table `lots`
--

CREATE TABLE `lots` (
  `LotId` int(11) NOT NULL,
  `LotDescriptionId` int(11) NOT NULL,
  `AcademicClassLevelId` int(11) NOT NULL,
  `RegisteredDate` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lots`
--

INSERT INTO `lots` (`LotId`, `LotDescriptionId`, `AcademicClassLevelId`, `RegisteredDate`) VALUES
(1, 1, 1, '2020-04-24 11:49:21');

-- --------------------------------------------------------

--
-- Table structure for table `lot_descriptions`
--

CREATE TABLE `lot_descriptions` (
  `LotDescriptionId` int(11) NOT NULL,
  `LotDescription` varchar(9000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lot_descriptions`
--

INSERT INTO `lot_descriptions` (`LotDescriptionId`, `LotDescription`) VALUES
(1, 'Class of 2012'),
(2, 'Class of 2020');

-- --------------------------------------------------------

--
-- Table structure for table `month`
--

CREATE TABLE `month` (
  `MonthId` int(11) NOT NULL,
  `Month` varchar(200) NOT NULL,
  `Sync` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `month`
--

INSERT INTO `month` (`MonthId`, `Month`, `Sync`) VALUES
(1, 'February', 'Not Synchronised');

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `RoleId` int(11) NOT NULL,
  `RoleDescription` varchar(9000) NOT NULL,
  `RoleCode` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`RoleId`, `RoleDescription`, `RoleCode`) VALUES
(1, 'Admin', 1),
(2, 'Bursar', 2);

-- --------------------------------------------------------

--
-- Table structure for table `session_activities`
--

CREATE TABLE `session_activities` (
  `SessionActivityId` int(11) NOT NULL,
  `SessionActivityDescription` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `session_activities`
--

INSERT INTO `session_activities` (`SessionActivityId`, `SessionActivityDescription`) VALUES
(1, 'Logged into the system'),
(2, 'Registered a student'),
(3, 'Corrected a student\'s personal details'),
(4, 'Registered a fee installment'),
(5, 'Corrected a fee payment'),
(6, 'Deleted a student');

-- --------------------------------------------------------

--
-- Table structure for table `session_logs`
--

CREATE TABLE `session_logs` (
  `SessionLogId` int(11) NOT NULL,
  `UserId` int(11) NOT NULL,
  `SessionStartDate` datetime NOT NULL,
  `SessionEndDate` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `session_logs`
--

INSERT INTO `session_logs` (`SessionLogId`, `UserId`, `SessionStartDate`, `SessionEndDate`) VALUES
(156, 14, '2020-05-20 19:26:39', '2020-05-20 19:28:32'),
(157, 14, '2020-05-20 19:28:36', '2020-05-20 19:58:19'),
(158, 14, '2020-05-20 19:59:22', '2020-05-20 20:02:39'),
(159, 14, '2020-05-20 20:02:42', '2020-05-20 20:06:35'),
(160, 14, '2020-05-20 20:06:38', '2020-05-22 18:48:38'),
(161, 14, '2020-05-20 20:10:04', '2020-05-22 18:48:38'),
(162, 14, '2020-05-22 18:53:39', '2020-05-22 18:54:01'),
(163, 14, '2020-05-22 18:54:05', '2020-05-22 20:10:06'),
(164, 14, '2020-05-22 20:30:32', '2020-05-22 20:31:22'),
(165, 14, '2020-05-22 20:31:23', '2020-05-22 20:33:00'),
(166, 14, '2020-05-22 20:33:02', '2020-05-22 20:34:02'),
(167, 14, '2020-05-22 20:34:04', '2020-05-22 20:39:10'),
(168, 14, '2020-05-22 20:37:14', '2020-05-22 20:39:10'),
(169, 14, '2020-05-22 20:38:27', '2020-05-22 20:39:10'),
(170, 14, '2020-05-22 20:39:14', '2020-05-22 20:41:25'),
(171, 14, '2020-05-22 20:41:28', '2020-05-22 20:47:48'),
(172, 14, '2020-05-22 20:45:10', '2020-05-22 20:47:48'),
(173, 14, '2020-05-22 20:55:55', '2020-05-22 20:56:26'),
(174, 14, '2020-05-22 20:56:27', '2020-05-22 20:56:56'),
(175, 14, '2020-05-22 20:56:57', '2020-05-22 20:57:33'),
(176, 14, '2020-05-22 20:57:35', '2020-05-22 21:00:23'),
(177, 14, '2020-05-22 21:00:04', '2020-05-22 21:00:23'),
(178, 14, '2020-05-22 21:00:25', '2020-05-22 21:17:08'),
(179, 14, '2020-05-22 21:08:53', '2020-05-22 21:17:08'),
(180, 14, '2020-05-22 21:13:50', '2020-05-22 21:17:08'),
(181, 14, '2020-05-22 21:15:26', '2020-05-22 21:17:08'),
(182, 14, '2020-05-22 21:17:12', '2020-05-22 22:06:50'),
(183, 14, '2020-05-22 21:44:15', '2020-05-22 22:06:50'),
(184, 14, '2020-05-22 22:18:49', '2020-05-22 22:20:02'),
(185, 14, '2020-05-22 22:21:12', '2020-05-22 22:23:47'),
(186, 14, '2020-05-22 22:23:51', '2020-05-22 22:31:31'),
(187, 14, '2020-05-22 22:31:34', '2020-05-22 22:43:44'),
(188, 14, '2020-05-22 23:40:51', '2020-05-22 23:41:47'),
(189, 14, '2020-05-22 23:46:14', '2020-05-22 23:47:23'),
(190, 14, '2020-05-22 23:48:58', '2020-05-22 23:56:33'),
(191, 14, '2020-05-23 11:07:28', '2020-05-23 11:08:28'),
(192, 14, '2020-05-23 11:08:30', '2020-05-23 11:47:59'),
(193, 14, '2020-05-23 11:48:01', '2020-05-23 11:58:28'),
(194, 14, '2020-05-23 12:12:47', '2020-05-23 12:18:25'),
(195, 14, '2020-05-23 12:18:27', '2020-05-23 12:19:37'),
(196, 14, '2020-05-23 12:19:41', '2020-05-23 12:34:17'),
(197, 14, '2020-05-23 12:21:42', '2020-05-23 12:34:17'),
(198, 14, '2020-05-23 12:34:57', '2020-05-23 12:36:02'),
(199, 14, '2020-05-23 12:36:03', '2020-05-23 12:37:37'),
(200, 14, '2020-05-23 12:37:38', '2020-05-23 12:42:44'),
(201, 14, '2020-05-23 12:40:31', '2020-05-23 12:42:44'),
(202, 14, '2020-05-23 12:42:46', '2020-05-23 13:01:31'),
(203, 14, '2020-05-23 12:44:07', '2020-05-23 13:01:31'),
(204, 14, '2020-05-23 12:47:05', '2020-05-23 13:01:31'),
(205, 14, '2020-05-23 13:01:33', '2020-05-23 13:02:28'),
(206, 14, '2020-05-23 13:02:47', '2020-05-23 13:03:38'),
(207, 14, '2020-05-23 13:03:40', '2020-05-23 13:06:48'),
(208, 14, '2020-05-23 13:06:52', '2020-05-23 13:49:17'),
(209, 14, '2020-05-23 13:18:23', '2020-05-23 13:49:17'),
(210, 14, '2020-05-23 13:49:19', '2020-05-23 14:42:01'),
(211, 14, '2020-05-23 14:52:00', '2020-05-23 14:52:49'),
(212, 14, '2020-05-23 14:52:51', '2020-05-23 14:53:14'),
(213, 14, '2020-05-23 14:53:15', '2020-05-23 14:58:14'),
(214, 14, '2020-05-23 15:05:32', '2020-05-23 15:06:56'),
(215, 14, '2020-05-23 15:06:59', '2020-05-23 15:09:15'),
(216, 14, '2020-05-23 15:10:32', '2020-05-23 15:16:08'),
(217, 14, '2020-05-23 15:16:28', '2020-05-23 15:43:37'),
(218, 14, '2020-05-23 15:19:06', '2020-05-23 15:43:37'),
(219, 14, '2020-05-23 15:23:49', '2020-05-23 15:43:37'),
(220, 14, '2020-05-23 15:43:38', '2020-05-23 15:46:24'),
(221, 14, '2020-05-23 15:49:41', '2020-05-23 15:50:05'),
(222, 14, '2020-05-23 15:50:07', '2020-05-23 15:50:49'),
(223, 14, '2020-05-23 15:50:50', '2020-05-23 15:51:58'),
(224, 14, '2020-05-23 15:51:59', '2020-05-23 15:53:38'),
(225, 14, '2020-05-23 15:53:38', '2020-05-23 15:54:00'),
(226, 14, '2020-05-23 15:54:01', '2020-05-23 15:55:48'),
(227, 14, '2020-05-23 15:56:16', '2020-05-23 15:56:52'),
(228, 14, '2020-05-23 15:56:53', '2020-05-23 16:04:42'),
(229, 14, '2020-05-23 16:04:43', '2020-05-23 16:08:52'),
(230, 14, '2020-05-23 16:07:01', '2020-05-23 16:08:52'),
(231, 14, '2020-05-23 16:08:54', '2020-05-23 16:14:02'),
(232, 14, '2020-05-23 16:14:10', '2020-05-23 16:15:38'),
(233, 14, '2020-05-23 16:15:40', '2020-05-23 16:15:57'),
(234, 14, '2020-05-23 16:16:00', '2020-05-23 16:16:18'),
(235, 14, '2020-05-23 16:16:21', '2020-05-23 16:22:57'),
(236, 14, '2020-05-23 16:26:59', '2020-05-23 16:30:15'),
(237, 14, '2020-05-23 16:30:16', '2020-05-23 16:33:15'),
(238, 14, '2020-05-23 16:33:17', '2020-05-23 16:39:32'),
(239, 14, '2020-05-23 16:41:17', '2020-05-23 16:46:42'),
(240, 14, '2020-05-23 16:50:35', '2020-05-23 16:51:54'),
(241, 14, '2020-05-23 16:51:56', '2020-05-23 16:58:20'),
(242, 14, '2020-05-23 16:58:21', '2020-05-23 17:00:26'),
(243, 14, '2020-05-23 17:00:27', '2020-05-23 17:42:24'),
(244, 14, '2020-05-23 17:06:38', '2020-05-23 17:42:24'),
(245, 14, '2020-05-23 17:42:28', '2020-05-23 17:45:26'),
(246, 14, '2020-05-23 17:45:27', '2020-05-24 22:33:34'),
(247, 14, '2020-05-23 17:47:13', '2020-05-24 22:33:34'),
(248, 14, '2020-05-24 22:33:59', '2020-05-24 22:35:51'),
(249, 14, '2020-05-24 22:54:22', '2020-05-24 22:59:35'),
(250, 14, '2020-05-24 22:56:40', '2020-05-24 22:59:35'),
(251, 14, '2020-05-24 22:59:38', '2020-05-25 14:42:18'),
(252, 14, '2020-05-25 14:29:34', '2020-05-25 14:42:18'),
(253, 14, '2020-05-25 14:42:49', '2020-05-25 15:01:30'),
(254, 14, '2020-05-25 14:47:17', '2020-05-25 15:01:30'),
(255, 14, '2020-05-25 15:01:32', '2020-05-25 19:43:20'),
(256, 14, '2020-05-25 19:43:42', '2020-05-25 20:44:03'),
(257, 14, '2020-05-25 19:47:49', '2020-05-25 20:44:03'),
(258, 14, '2020-05-25 20:44:10', '2020-05-25 20:44:59'),
(259, 14, '2020-05-25 21:08:13', '2020-05-25 21:09:53'),
(260, 14, '2020-05-25 21:10:28', '2020-05-25 21:12:07');

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `StudentId` int(11) NOT NULL,
  `AdmissionNo` varchar(200) NOT NULL,
  `StudentName` varchar(200) DEFAULT NULL,
  `GenderId` int(11) NOT NULL,
  `StudentDOB` date NOT NULL,
  `StudentResidenceId` int(11) NOT NULL,
  `ClassId` int(11) DEFAULT NULL,
  `AdmissionDate` datetime NOT NULL,
  `ProfPicName` varchar(900) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`StudentId`, `AdmissionNo`, `StudentName`, `GenderId`, `StudentDOB`, `StudentResidenceId`, `ClassId`, `AdmissionDate`, `ProfPicName`) VALUES
(18, '8032', 'Silas Onyango', 1, '2020-05-07', 1, 1, '2020-05-20 19:59:58', 'male_student.png'),
(19, '630', 'Silas Onyango', 1, '2020-05-20', 1, 1, '2020-05-20 20:03:21', 'male_student.png'),
(20, '8033', 'Silas Onyango', 1, '2020-05-20', 1, 1, '2020-05-20 20:07:03', 'male_student.png'),
(21, '8034', 'Zain Konyango', 1, '2020-05-30', 2, 1, '2020-05-20 20:10:27', 'male_student.png'),
(22, '7878', 'Ezekiel Ombima', 1, '2020-05-07', 1, 1, '2020-05-22 23:49:59', 'male_student.png'),
(23, '9000', 'Ken Aruba', 1, '2020-05-01', 1, 1, '2020-05-25 14:30:38', 'male_student.png'),
(24, '7070', 'Nana Gechaga', 2, '2020-05-01', 1, 1, '2020-05-25 14:47:48', 'female_student.png'),
(25, '6060', 'Mudanyi Kiyangu', 2, '2020-05-01', 1, 1, '2020-05-25 15:02:03', 'female_student.png'),
(26, '0', 'ADMIN_STUDENT', 1, '2020-05-01', 1, 1, '2020-05-01 00:00:00', 'male_student.png');

-- --------------------------------------------------------

--
-- Table structure for table `student_fee_components`
--

CREATE TABLE `student_fee_components` (
  `StudentFeeComponentId` int(11) NOT NULL,
  `StudentId` int(11) NOT NULL,
  `ClassFeeStructureComponentId` int(11) NOT NULL,
  `ComponentFeeAmount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `student_fee_components`
--

INSERT INTO `student_fee_components` (`StudentFeeComponentId`, `StudentId`, `ClassFeeStructureComponentId`, `ComponentFeeAmount`) VALUES
(19, 20, 1, 0),
(20, 20, 2, 0),
(21, 20, 3, 0),
(22, 21, 1, 0),
(23, 21, 2, 0),
(24, 21, 3, 0),
(25, 22, 1, 0),
(26, 22, 2, 0),
(27, 22, 3, 0),
(28, 23, 1, 0),
(29, 23, 2, 0),
(30, 23, 3, 0),
(31, 24, 1, 0),
(32, 24, 2, 0),
(33, 24, 3, 0),
(34, 25, 1, 0),
(35, 25, 2, 0),
(36, 25, 3, 0);

-- --------------------------------------------------------

--
-- Table structure for table `student_registration`
--

CREATE TABLE `student_registration` (
  `StudentRegistrationId` int(11) NOT NULL,
  `SessionLogId` int(11) NOT NULL,
  `UserSessionActivityId` int(11) NOT NULL,
  `StudentId` int(11) NOT NULL,
  `StudentRegistrationDate` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_registration`
--

INSERT INTO `student_registration` (`StudentRegistrationId`, `SessionLogId`, `UserSessionActivityId`, `StudentId`, `StudentRegistrationDate`) VALUES
(2, 160, 166, 20, '2020-05-20 20:07:03'),
(3, 161, 168, 21, '2020-05-20 20:10:27'),
(4, 190, 198, 22, '2020-05-22 23:49:59'),
(5, 252, 263, 23, '2020-05-25 14:30:38'),
(6, 254, 267, 24, '2020-05-25 14:47:48'),
(7, 255, 270, 25, '2020-05-25 15:02:03');

-- --------------------------------------------------------

--
-- Table structure for table `student_residence`
--

CREATE TABLE `student_residence` (
  `StudentResidenceId` int(11) NOT NULL,
  `StudentResidenceDescription` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_residence`
--

INSERT INTO `student_residence` (`StudentResidenceId`, `StudentResidenceDescription`) VALUES
(1, 'Boarder'),
(2, 'Day Scholar');

-- --------------------------------------------------------

--
-- Table structure for table `term_iterations`
--

CREATE TABLE `term_iterations` (
  `TermIterationId` int(11) NOT NULL,
  `TermIterationDescription` varchar(9000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `term_iterations`
--

INSERT INTO `term_iterations` (`TermIterationId`, `TermIterationDescription`) VALUES
(1, 'Term One'),
(2, 'Term Two'),
(3, 'Term Three');

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `TransactionId` int(11) NOT NULL,
  `SessionLogId` int(11) NOT NULL,
  `UserSessionActivityId` int(11) NOT NULL,
  `TransactionDescriptionId` int(11) NOT NULL,
  `StudentId` int(11) NOT NULL,
  `InstallmentId` int(11) DEFAULT NULL,
  `CarryFowardId` int(11) DEFAULT NULL,
  `FeeCorrectionId` int(11) DEFAULT NULL,
  `PreviousTermBalance` int(11) NOT NULL,
  `PreviousAnnualBalance` int(11) NOT NULL,
  `PreviousTotal` int(11) NOT NULL,
  `NextTermBalance` int(11) NOT NULL,
  `NextAnnualBalance` int(11) NOT NULL,
  `NextTotal` int(11) NOT NULL,
  `TransactionDate` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`TransactionId`, `SessionLogId`, `UserSessionActivityId`, `TransactionDescriptionId`, `StudentId`, `InstallmentId`, `CarryFowardId`, `FeeCorrectionId`, `PreviousTermBalance`, `PreviousAnnualBalance`, `PreviousTotal`, `NextTermBalance`, `NextAnnualBalance`, `NextTotal`, `TransactionDate`) VALUES
(4, 260, 281, 1, 21, 12, 0, 0, 18000, 33000, 12000, 4700, 19700, 25300, '2020-05-25 21:10:52');

-- --------------------------------------------------------

--
-- Table structure for table `transaction_descriptions`
--

CREATE TABLE `transaction_descriptions` (
  `TransactionDescriptionId` int(11) NOT NULL,
  `TransactionDescription` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaction_descriptions`
--

INSERT INTO `transaction_descriptions` (`TransactionDescriptionId`, `TransactionDescription`) VALUES
(1, 'Register a fee installment'),
(2, 'Fee correction'),
(3, 'An end of term carry forward'),
(4, 'An end of year carry forward');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `UserId` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `GenderId` int(11) NOT NULL,
  `EncryptedPassword` varchar(9000) NOT NULL,
  `RegisteredDate` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`UserId`, `Name`, `Email`, `GenderId`, `EncryptedPassword`, `RegisteredDate`) VALUES
(14, 'Silas Onyango', 'silas.onyango93@gmail.com', 1, '$2a$10$UZivdFuxJ7hQBLosU.1TSOjg4j84dHZHFcp865mL4tjNqpDn7rCcC', '2020-04-18 08:54:09'),
(15, 'Hazel Afandi', 'hazel@gmail.com', 2, '$2a$10$sJ1/JzruYT0lObcxGf4mGOPyZCEk3ZP6f6V.Mu5A8CiOeNrklFEVC', '2020-04-19 14:23:02'),
(16, 'Ezekiel Ombima', 'ezekiel@gmail.com', 1, '$2a$10$mNCrph9358v4p3mO2my4T.18BZJ70Xu8/L1BdGMr7.2n8z5JqxdyO', '2020-04-22 18:24:55');

-- --------------------------------------------------------

--
-- Table structure for table `user_access_privileges`
--

CREATE TABLE `user_access_privileges` (
  `UserAccessPrivilegeId` int(11) NOT NULL,
  `UserRoleId` int(11) NOT NULL,
  `AccessPrivilegeId` int(11) NOT NULL,
  `PermisionStatus` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_access_privileges`
--

INSERT INTO `user_access_privileges` (`UserAccessPrivilegeId`, `UserRoleId`, `AccessPrivilegeId`, `PermisionStatus`) VALUES
(29, 19, 1, 1),
(30, 19, 2, 1),
(31, 19, 3, 0),
(32, 19, 4, 0),
(33, 19, 5, 0),
(34, 19, 6, 0),
(35, 20, 1, 1),
(36, 20, 2, 1),
(37, 20, 3, 0),
(38, 20, 4, 1),
(39, 20, 5, 0),
(40, 20, 6, 0),
(41, 21, 1, 0),
(42, 21, 2, 0),
(43, 21, 3, 0),
(44, 21, 4, 0),
(45, 21, 5, 0),
(46, 21, 6, 0),
(47, 22, 1, 0),
(48, 22, 2, 0),
(49, 22, 3, 0),
(50, 22, 4, 0),
(51, 22, 5, 0),
(52, 22, 6, 0),
(53, 23, 1, 0),
(54, 23, 2, 0),
(55, 23, 3, 0),
(56, 23, 4, 0),
(57, 23, 5, 0),
(58, 23, 6, 0),
(59, 24, 1, 0),
(60, 24, 2, 0),
(61, 24, 3, 0),
(62, 24, 4, 0),
(63, 24, 5, 0),
(64, 24, 6, 0);

-- --------------------------------------------------------

--
-- Table structure for table `user_roles`
--

CREATE TABLE `user_roles` (
  `UserRoleId` int(11) NOT NULL,
  `UserId` int(11) NOT NULL,
  `RoleId` int(11) NOT NULL,
  `ConfirmationStatus` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_roles`
--

INSERT INTO `user_roles` (`UserRoleId`, `UserId`, `RoleId`, `ConfirmationStatus`) VALUES
(19, 14, 1, 1),
(20, 14, 2, 1),
(21, 15, 1, 0),
(22, 15, 2, 0),
(23, 16, 1, 0),
(24, 16, 2, 0);

-- --------------------------------------------------------

--
-- Table structure for table `user_session_activities`
--

CREATE TABLE `user_session_activities` (
  `UserSessionActivityId` int(11) NOT NULL,
  `SessionLogId` int(11) NOT NULL,
  `SessionActivityId` int(11) NOT NULL,
  `SessionActivityDate` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_session_activities`
--

INSERT INTO `user_session_activities` (`UserSessionActivityId`, `SessionLogId`, `SessionActivityId`, `SessionActivityDate`) VALUES
(161, 156, 1, '2020-05-20 19:26:39'),
(162, 157, 1, '2020-05-20 19:28:36'),
(163, 158, 1, '2020-05-20 19:59:22'),
(164, 159, 1, '2020-05-20 20:02:42'),
(165, 160, 1, '2020-05-20 20:06:38'),
(166, 160, 2, '2020-05-20 20:07:03'),
(167, 161, 1, '2020-05-20 20:10:04'),
(168, 161, 2, '2020-05-20 20:10:27'),
(169, 162, 1, '2020-05-22 18:53:39'),
(170, 163, 1, '2020-05-22 18:54:05'),
(171, 164, 1, '2020-05-22 20:30:32'),
(172, 165, 1, '2020-05-22 20:31:23'),
(173, 166, 1, '2020-05-22 20:33:02'),
(174, 167, 1, '2020-05-22 20:34:04'),
(175, 168, 1, '2020-05-22 20:37:14'),
(176, 169, 1, '2020-05-22 20:38:27'),
(177, 170, 1, '2020-05-22 20:39:14'),
(178, 171, 1, '2020-05-22 20:41:28'),
(179, 172, 1, '2020-05-22 20:45:10'),
(180, 173, 1, '2020-05-22 20:55:55'),
(181, 174, 1, '2020-05-22 20:56:27'),
(182, 175, 1, '2020-05-22 20:56:57'),
(183, 176, 1, '2020-05-22 20:57:35'),
(184, 177, 1, '2020-05-22 21:00:04'),
(185, 178, 1, '2020-05-22 21:00:25'),
(186, 179, 1, '2020-05-22 21:08:53'),
(187, 180, 1, '2020-05-22 21:13:50'),
(188, 181, 1, '2020-05-22 21:15:26'),
(189, 182, 1, '2020-05-22 21:17:12'),
(190, 183, 1, '2020-05-22 21:44:15'),
(191, 184, 1, '2020-05-22 22:18:49'),
(192, 185, 1, '2020-05-22 22:21:12'),
(193, 186, 1, '2020-05-22 22:23:51'),
(194, 187, 1, '2020-05-22 22:31:34'),
(195, 188, 1, '2020-05-22 23:40:51'),
(196, 189, 1, '2020-05-22 23:46:14'),
(197, 190, 1, '2020-05-22 23:48:58'),
(198, 190, 2, '2020-05-22 23:49:59'),
(199, 191, 1, '2020-05-23 11:07:28'),
(200, 192, 1, '2020-05-23 11:08:30'),
(201, 193, 1, '2020-05-23 11:48:01'),
(202, 194, 1, '2020-05-23 12:12:47'),
(203, 195, 1, '2020-05-23 12:18:27'),
(204, 196, 1, '2020-05-23 12:19:41'),
(205, 197, 1, '2020-05-23 12:21:42'),
(206, 198, 1, '2020-05-23 12:34:57'),
(207, 199, 1, '2020-05-23 12:36:03'),
(208, 200, 1, '2020-05-23 12:37:38'),
(209, 201, 1, '2020-05-23 12:40:31'),
(210, 202, 1, '2020-05-23 12:42:46'),
(211, 203, 1, '2020-05-23 12:44:07'),
(212, 204, 1, '2020-05-23 12:47:05'),
(213, 205, 1, '2020-05-23 13:01:33'),
(214, 206, 1, '2020-05-23 13:02:47'),
(215, 207, 1, '2020-05-23 13:03:40'),
(216, 208, 1, '2020-05-23 13:06:52'),
(217, 209, 1, '2020-05-23 13:18:23'),
(218, 210, 1, '2020-05-23 13:49:19'),
(219, 211, 1, '2020-05-23 14:52:00'),
(220, 212, 1, '2020-05-23 14:52:51'),
(221, 213, 1, '2020-05-23 14:53:15'),
(222, 214, 1, '2020-05-23 15:05:32'),
(223, 215, 1, '2020-05-23 15:06:59'),
(224, 216, 1, '2020-05-23 15:10:32'),
(225, 217, 1, '2020-05-23 15:16:28'),
(226, 218, 1, '2020-05-23 15:19:06'),
(227, 219, 1, '2020-05-23 15:23:49'),
(228, 220, 1, '2020-05-23 15:43:38'),
(229, 221, 1, '2020-05-23 15:49:41'),
(230, 222, 1, '2020-05-23 15:50:07'),
(231, 223, 1, '2020-05-23 15:50:50'),
(232, 224, 1, '2020-05-23 15:51:59'),
(233, 225, 1, '2020-05-23 15:53:38'),
(234, 226, 1, '2020-05-23 15:54:01'),
(235, 227, 1, '2020-05-23 15:56:16'),
(236, 228, 1, '2020-05-23 15:56:53'),
(237, 229, 1, '2020-05-23 16:04:43'),
(238, 230, 1, '2020-05-23 16:07:01'),
(239, 231, 1, '2020-05-23 16:08:54'),
(240, 232, 1, '2020-05-23 16:14:10'),
(241, 233, 1, '2020-05-23 16:15:40'),
(242, 234, 1, '2020-05-23 16:16:00'),
(243, 235, 1, '2020-05-23 16:16:21'),
(244, 236, 1, '2020-05-23 16:26:59'),
(245, 237, 1, '2020-05-23 16:30:16'),
(246, 238, 1, '2020-05-23 16:33:17'),
(247, 239, 1, '2020-05-23 16:41:17'),
(248, 240, 1, '2020-05-23 16:50:35'),
(249, 241, 1, '2020-05-23 16:51:56'),
(250, 242, 1, '2020-05-23 16:58:21'),
(251, 243, 1, '2020-05-23 17:00:27'),
(252, 244, 1, '2020-05-23 17:06:38'),
(253, 245, 1, '2020-05-23 17:42:28'),
(254, 246, 1, '2020-05-23 17:45:27'),
(255, 247, 1, '2020-05-23 17:47:13'),
(256, 248, 1, '2020-05-24 22:33:59'),
(257, 249, 1, '2020-05-24 22:54:22'),
(258, 249, 4, '2020-05-24 22:54:40'),
(259, 250, 1, '2020-05-24 22:56:40'),
(260, 250, 4, '2020-05-24 22:57:02'),
(261, 251, 1, '2020-05-24 22:59:38'),
(262, 252, 1, '2020-05-25 14:29:34'),
(263, 252, 2, '2020-05-25 14:30:38'),
(264, 253, 1, '2020-05-25 14:42:49'),
(265, 253, 4, '2020-05-25 14:43:09'),
(266, 254, 1, '2020-05-25 14:47:17'),
(267, 254, 2, '2020-05-25 14:47:48'),
(268, 254, 4, '2020-05-25 14:49:17'),
(269, 255, 1, '2020-05-25 15:01:32'),
(270, 255, 2, '2020-05-25 15:02:03'),
(271, 255, 4, '2020-05-25 15:02:21'),
(272, 256, 1, '2020-05-25 19:43:42'),
(273, 256, 4, '2020-05-25 19:44:47'),
(274, 257, 1, '2020-05-25 19:47:49'),
(275, 257, 4, '2020-05-25 19:48:10'),
(276, 258, 1, '2020-05-25 20:44:10'),
(277, 258, 4, '2020-05-25 20:44:31'),
(278, 259, 1, '2020-05-25 21:08:13'),
(279, 259, 4, '2020-05-25 21:08:30'),
(280, 260, 1, '2020-05-25 21:10:28'),
(281, 260, 4, '2020-05-25 21:10:52');

-- --------------------------------------------------------

--
-- Table structure for table `week_iterations`
--

CREATE TABLE `week_iterations` (
  `WeekIterationId` int(11) NOT NULL,
  `WeekIterationDescription` varchar(9000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `week_iterations`
--

INSERT INTO `week_iterations` (`WeekIterationId`, `WeekIterationDescription`) VALUES
(1, 'Week 1'),
(2, 'Week 2'),
(3, 'Week 3'),
(4, 'Week 4'),
(5, 'Week 5'),
(6, 'Week 6'),
(7, 'Week 7'),
(8, 'Week 8'),
(9, 'Week 9'),
(10, 'Week 10'),
(11, 'Week 11'),
(12, 'Week 12'),
(13, 'Week 13'),
(14, 'Week 14');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `academic_class_levels`
--
ALTER TABLE `academic_class_levels`
  ADD PRIMARY KEY (`AcademicClassLevelId`);

--
-- Indexes for table `access_privileges`
--
ALTER TABLE `access_privileges`
  ADD PRIMARY KEY (`AccessPrivilegeId`);

--
-- Indexes for table `actual_terms`
--
ALTER TABLE `actual_terms`
  ADD PRIMARY KEY (`TermId`),
  ADD KEY `TermIterationId` (`TermIterationId`);

--
-- Indexes for table `actual_weeks`
--
ALTER TABLE `actual_weeks`
  ADD PRIMARY KEY (`ActualWeekId`),
  ADD KEY `TermId` (`TermId`),
  ADD KEY `WeekIterationId` (`WeekIterationId`);

--
-- Indexes for table `carry_forwards`
--
ALTER TABLE `carry_forwards`
  ADD PRIMARY KEY (`CarryFowardId`),
  ADD KEY `StudentId` (`StudentId`);

--
-- Indexes for table `classes`
--
ALTER TABLE `classes`
  ADD PRIMARY KEY (`ClassId`),
  ADD KEY `LotId` (`LotId`),
  ADD KEY `ClassStreamId` (`ClassStreamId`);

--
-- Indexes for table `class_fee_structures`
--
ALTER TABLE `class_fee_structures`
  ADD PRIMARY KEY (`ClassFeeStructureId`),
  ADD KEY `FeeStructureId` (`FeeStructureId`),
  ADD KEY `AcademicClassLevelId` (`AcademicClassLevelId`);

--
-- Indexes for table `class_fee_structure_breakdown`
--
ALTER TABLE `class_fee_structure_breakdown`
  ADD PRIMARY KEY (`ClassFeeStructureBreakDownId`),
  ADD KEY `ClassFeeStructureId` (`ClassFeeStructureId`),
  ADD KEY `TermIterationId` (`TermIterationId`),
  ADD KEY `StudentResidenceId` (`StudentResidenceId`);

--
-- Indexes for table `class_fee_structure_components`
--
ALTER TABLE `class_fee_structure_components`
  ADD PRIMARY KEY (`ClassFeeStructureComponentId`),
  ADD KEY `ClassFeeStructureId` (`ClassFeeStructureId`),
  ADD KEY `FeeComponentId` (`FeeComponentId`);

--
-- Indexes for table `class_streams`
--
ALTER TABLE `class_streams`
  ADD PRIMARY KEY (`ClassStreamId`);

--
-- Indexes for table `correction_descriptions`
--
ALTER TABLE `correction_descriptions`
  ADD PRIMARY KEY (`CorrectionDescriptionId`);

--
-- Indexes for table `fee_components`
--
ALTER TABLE `fee_components`
  ADD PRIMARY KEY (`FeeComponentId`);

--
-- Indexes for table `fee_corrections`
--
ALTER TABLE `fee_corrections`
  ADD PRIMARY KEY (`FeeCorrectionId`),
  ADD KEY `CorrectionDescriptionId` (`CorrectionDescriptionId`),
  ADD KEY `SessionLogId` (`SessionLogId`),
  ADD KEY `ActualSessionActivityId` (`UserSessionActivityId`),
  ADD KEY `StudentId` (`StudentId`);

--
-- Indexes for table `fee_statements`
--
ALTER TABLE `fee_statements`
  ADD PRIMARY KEY (`FeeStatementId`),
  ADD KEY `StudentId` (`StudentId`);

--
-- Indexes for table `fee_structures`
--
ALTER TABLE `fee_structures`
  ADD PRIMARY KEY (`FeeStructureId`),
  ADD KEY `UserId` (`UserId`);

--
-- Indexes for table `gender`
--
ALTER TABLE `gender`
  ADD PRIMARY KEY (`GenderId`);

--
-- Indexes for table `installments`
--
ALTER TABLE `installments`
  ADD PRIMARY KEY (`InstallmentId`),
  ADD KEY `SessionLogId` (`SessionLogId`),
  ADD KEY `ActualSessionActivityId` (`UserSessionActivityId`),
  ADD KEY `StudentId` (`StudentId`);

--
-- Indexes for table `lots`
--
ALTER TABLE `lots`
  ADD PRIMARY KEY (`LotId`),
  ADD KEY `AcademicClassLevelId` (`AcademicClassLevelId`),
  ADD KEY `LotDescriptionId` (`LotDescriptionId`);

--
-- Indexes for table `lot_descriptions`
--
ALTER TABLE `lot_descriptions`
  ADD PRIMARY KEY (`LotDescriptionId`);

--
-- Indexes for table `month`
--
ALTER TABLE `month`
  ADD PRIMARY KEY (`MonthId`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`RoleId`);

--
-- Indexes for table `session_activities`
--
ALTER TABLE `session_activities`
  ADD PRIMARY KEY (`SessionActivityId`);

--
-- Indexes for table `session_logs`
--
ALTER TABLE `session_logs`
  ADD PRIMARY KEY (`SessionLogId`),
  ADD KEY `UserId` (`UserId`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`StudentId`),
  ADD UNIQUE KEY `AdmissionNo` (`AdmissionNo`),
  ADD KEY `ClassId` (`ClassId`),
  ADD KEY `StudentTypeId` (`StudentResidenceId`),
  ADD KEY `GenderId` (`GenderId`);

--
-- Indexes for table `student_fee_components`
--
ALTER TABLE `student_fee_components`
  ADD PRIMARY KEY (`StudentFeeComponentId`),
  ADD KEY `StudentId` (`StudentId`);

--
-- Indexes for table `student_registration`
--
ALTER TABLE `student_registration`
  ADD PRIMARY KEY (`StudentRegistrationId`),
  ADD KEY `SessionLogId` (`SessionLogId`),
  ADD KEY `ActualSessionActivityId` (`UserSessionActivityId`),
  ADD KEY `StudentId` (`StudentId`);

--
-- Indexes for table `student_residence`
--
ALTER TABLE `student_residence`
  ADD PRIMARY KEY (`StudentResidenceId`);

--
-- Indexes for table `term_iterations`
--
ALTER TABLE `term_iterations`
  ADD PRIMARY KEY (`TermIterationId`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`TransactionId`),
  ADD KEY `SessionLogId` (`SessionLogId`),
  ADD KEY `ActualSessionActivityId` (`UserSessionActivityId`),
  ADD KEY `TransactionDescriptionId` (`TransactionDescriptionId`),
  ADD KEY `InstallmentId` (`InstallmentId`),
  ADD KEY `FeeCorrectionId` (`FeeCorrectionId`),
  ADD KEY `CarryFowardId` (`CarryFowardId`),
  ADD KEY `StudentId` (`StudentId`);

--
-- Indexes for table `transaction_descriptions`
--
ALTER TABLE `transaction_descriptions`
  ADD PRIMARY KEY (`TransactionDescriptionId`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`UserId`),
  ADD UNIQUE KEY `email` (`Email`),
  ADD KEY `GenderId` (`GenderId`);

--
-- Indexes for table `user_access_privileges`
--
ALTER TABLE `user_access_privileges`
  ADD PRIMARY KEY (`UserAccessPrivilegeId`),
  ADD KEY `AccessPrivilegeId` (`AccessPrivilegeId`),
  ADD KEY `UserRoleId` (`UserRoleId`);

--
-- Indexes for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD PRIMARY KEY (`UserRoleId`),
  ADD KEY `UserId` (`UserId`),
  ADD KEY `RoleId` (`RoleId`);

--
-- Indexes for table `user_session_activities`
--
ALTER TABLE `user_session_activities`
  ADD PRIMARY KEY (`UserSessionActivityId`),
  ADD KEY `SessionLogId` (`SessionLogId`),
  ADD KEY `SessionActivityId` (`SessionActivityId`);

--
-- Indexes for table `week_iterations`
--
ALTER TABLE `week_iterations`
  ADD PRIMARY KEY (`WeekIterationId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `academic_class_levels`
--
ALTER TABLE `academic_class_levels`
  MODIFY `AcademicClassLevelId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `access_privileges`
--
ALTER TABLE `access_privileges`
  MODIFY `AccessPrivilegeId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `actual_terms`
--
ALTER TABLE `actual_terms`
  MODIFY `TermId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `actual_weeks`
--
ALTER TABLE `actual_weeks`
  MODIFY `ActualWeekId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `carry_forwards`
--
ALTER TABLE `carry_forwards`
  MODIFY `CarryFowardId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `classes`
--
ALTER TABLE `classes`
  MODIFY `ClassId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `class_fee_structures`
--
ALTER TABLE `class_fee_structures`
  MODIFY `ClassFeeStructureId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `class_fee_structure_breakdown`
--
ALTER TABLE `class_fee_structure_breakdown`
  MODIFY `ClassFeeStructureBreakDownId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `class_fee_structure_components`
--
ALTER TABLE `class_fee_structure_components`
  MODIFY `ClassFeeStructureComponentId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `class_streams`
--
ALTER TABLE `class_streams`
  MODIFY `ClassStreamId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `correction_descriptions`
--
ALTER TABLE `correction_descriptions`
  MODIFY `CorrectionDescriptionId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `fee_components`
--
ALTER TABLE `fee_components`
  MODIFY `FeeComponentId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `fee_corrections`
--
ALTER TABLE `fee_corrections`
  MODIFY `FeeCorrectionId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `fee_statements`
--
ALTER TABLE `fee_statements`
  MODIFY `FeeStatementId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `fee_structures`
--
ALTER TABLE `fee_structures`
  MODIFY `FeeStructureId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `gender`
--
ALTER TABLE `gender`
  MODIFY `GenderId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `installments`
--
ALTER TABLE `installments`
  MODIFY `InstallmentId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `lots`
--
ALTER TABLE `lots`
  MODIFY `LotId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `lot_descriptions`
--
ALTER TABLE `lot_descriptions`
  MODIFY `LotDescriptionId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `month`
--
ALTER TABLE `month`
  MODIFY `MonthId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `RoleId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `session_activities`
--
ALTER TABLE `session_activities`
  MODIFY `SessionActivityId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `session_logs`
--
ALTER TABLE `session_logs`
  MODIFY `SessionLogId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=261;

--
-- AUTO_INCREMENT for table `students`
--
ALTER TABLE `students`
  MODIFY `StudentId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `student_fee_components`
--
ALTER TABLE `student_fee_components`
  MODIFY `StudentFeeComponentId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT for table `student_registration`
--
ALTER TABLE `student_registration`
  MODIFY `StudentRegistrationId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `student_residence`
--
ALTER TABLE `student_residence`
  MODIFY `StudentResidenceId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `term_iterations`
--
ALTER TABLE `term_iterations`
  MODIFY `TermIterationId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `transactions`
--
ALTER TABLE `transactions`
  MODIFY `TransactionId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `transaction_descriptions`
--
ALTER TABLE `transaction_descriptions`
  MODIFY `TransactionDescriptionId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `UserId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `user_access_privileges`
--
ALTER TABLE `user_access_privileges`
  MODIFY `UserAccessPrivilegeId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=65;

--
-- AUTO_INCREMENT for table `user_roles`
--
ALTER TABLE `user_roles`
  MODIFY `UserRoleId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `user_session_activities`
--
ALTER TABLE `user_session_activities`
  MODIFY `UserSessionActivityId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=282;

--
-- AUTO_INCREMENT for table `week_iterations`
--
ALTER TABLE `week_iterations`
  MODIFY `WeekIterationId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `actual_terms`
--
ALTER TABLE `actual_terms`
  ADD CONSTRAINT `actual_terms_ibfk_1` FOREIGN KEY (`TermIterationId`) REFERENCES `term_iterations` (`TermIterationId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `actual_weeks`
--
ALTER TABLE `actual_weeks`
  ADD CONSTRAINT `actual_weeks_ibfk_1` FOREIGN KEY (`TermId`) REFERENCES `actual_terms` (`TermId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `actual_weeks_ibfk_2` FOREIGN KEY (`WeekIterationId`) REFERENCES `week_iterations` (`WeekIterationId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `carry_forwards`
--
ALTER TABLE `carry_forwards`
  ADD CONSTRAINT `carry_forwards_ibfk_1` FOREIGN KEY (`StudentId`) REFERENCES `students` (`StudentId`);

--
-- Constraints for table `classes`
--
ALTER TABLE `classes`
  ADD CONSTRAINT `classes_ibfk_1` FOREIGN KEY (`LotId`) REFERENCES `lots` (`LotId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `classes_ibfk_2` FOREIGN KEY (`ClassStreamId`) REFERENCES `class_streams` (`ClassStreamId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `class_fee_structures`
--
ALTER TABLE `class_fee_structures`
  ADD CONSTRAINT `class_fee_structures_ibfk_1` FOREIGN KEY (`FeeStructureId`) REFERENCES `fee_structures` (`FeeStructureId`),
  ADD CONSTRAINT `class_fee_structures_ibfk_2` FOREIGN KEY (`AcademicClassLevelId`) REFERENCES `academic_class_levels` (`AcademicClassLevelId`);

--
-- Constraints for table `class_fee_structure_breakdown`
--
ALTER TABLE `class_fee_structure_breakdown`
  ADD CONSTRAINT `class_fee_structure_breakdown_ibfk_1` FOREIGN KEY (`ClassFeeStructureId`) REFERENCES `class_fee_structures` (`ClassFeeStructureId`),
  ADD CONSTRAINT `class_fee_structure_breakdown_ibfk_2` FOREIGN KEY (`TermIterationId`) REFERENCES `term_iterations` (`TermIterationId`),
  ADD CONSTRAINT `class_fee_structure_breakdown_ibfk_3` FOREIGN KEY (`StudentResidenceId`) REFERENCES `student_residence` (`StudentResidenceId`);

--
-- Constraints for table `class_fee_structure_components`
--
ALTER TABLE `class_fee_structure_components`
  ADD CONSTRAINT `class_fee_structure_components_ibfk_1` FOREIGN KEY (`ClassFeeStructureId`) REFERENCES `class_fee_structures` (`ClassFeeStructureId`),
  ADD CONSTRAINT `class_fee_structure_components_ibfk_2` FOREIGN KEY (`FeeComponentId`) REFERENCES `fee_components` (`FeeComponentId`);

--
-- Constraints for table `fee_corrections`
--
ALTER TABLE `fee_corrections`
  ADD CONSTRAINT `fee_corrections_ibfk_1` FOREIGN KEY (`CorrectionDescriptionId`) REFERENCES `correction_descriptions` (`CorrectionDescriptionId`),
  ADD CONSTRAINT `fee_corrections_ibfk_2` FOREIGN KEY (`SessionLogId`) REFERENCES `session_logs` (`SessionLogId`),
  ADD CONSTRAINT `fee_corrections_ibfk_3` FOREIGN KEY (`UserSessionActivityId`) REFERENCES `user_session_activities` (`UserSessionActivityId`),
  ADD CONSTRAINT `fee_corrections_ibfk_4` FOREIGN KEY (`StudentId`) REFERENCES `students` (`StudentId`);

--
-- Constraints for table `fee_statements`
--
ALTER TABLE `fee_statements`
  ADD CONSTRAINT `fee_statements_ibfk_1` FOREIGN KEY (`StudentId`) REFERENCES `students` (`StudentId`);

--
-- Constraints for table `fee_structures`
--
ALTER TABLE `fee_structures`
  ADD CONSTRAINT `fee_structures_ibfk_1` FOREIGN KEY (`UserId`) REFERENCES `users` (`UserId`);

--
-- Constraints for table `installments`
--
ALTER TABLE `installments`
  ADD CONSTRAINT `installments_ibfk_3` FOREIGN KEY (`SessionLogId`) REFERENCES `session_logs` (`SessionLogId`),
  ADD CONSTRAINT `installments_ibfk_4` FOREIGN KEY (`UserSessionActivityId`) REFERENCES `user_session_activities` (`UserSessionActivityId`),
  ADD CONSTRAINT `installments_ibfk_5` FOREIGN KEY (`StudentId`) REFERENCES `students` (`StudentId`);

--
-- Constraints for table `lots`
--
ALTER TABLE `lots`
  ADD CONSTRAINT `lots_ibfk_1` FOREIGN KEY (`AcademicClassLevelId`) REFERENCES `academic_class_levels` (`AcademicClassLevelId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `lots_ibfk_2` FOREIGN KEY (`LotDescriptionId`) REFERENCES `lot_descriptions` (`LotDescriptionId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `lots_ibfk_3` FOREIGN KEY (`LotDescriptionId`) REFERENCES `lot_descriptions` (`LotDescriptionId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `session_logs`
--
ALTER TABLE `session_logs`
  ADD CONSTRAINT `session_logs_ibfk_1` FOREIGN KEY (`UserId`) REFERENCES `users` (`UserId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `students`
--
ALTER TABLE `students`
  ADD CONSTRAINT `students_ibfk_1` FOREIGN KEY (`ClassId`) REFERENCES `classes` (`ClassId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `students_ibfk_2` FOREIGN KEY (`StudentResidenceId`) REFERENCES `student_residence` (`StudentResidenceId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `students_ibfk_3` FOREIGN KEY (`GenderId`) REFERENCES `gender` (`GenderId`);

--
-- Constraints for table `student_fee_components`
--
ALTER TABLE `student_fee_components`
  ADD CONSTRAINT `student_fee_components_ibfk_1` FOREIGN KEY (`StudentId`) REFERENCES `students` (`StudentId`);

--
-- Constraints for table `student_registration`
--
ALTER TABLE `student_registration`
  ADD CONSTRAINT `student_registration_ibfk_1` FOREIGN KEY (`SessionLogId`) REFERENCES `session_logs` (`SessionLogId`),
  ADD CONSTRAINT `student_registration_ibfk_2` FOREIGN KEY (`UserSessionActivityId`) REFERENCES `user_session_activities` (`UserSessionActivityId`),
  ADD CONSTRAINT `student_registration_ibfk_3` FOREIGN KEY (`StudentId`) REFERENCES `students` (`StudentId`);

--
-- Constraints for table `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`SessionLogId`) REFERENCES `session_logs` (`SessionLogId`),
  ADD CONSTRAINT `transactions_ibfk_2` FOREIGN KEY (`UserSessionActivityId`) REFERENCES `user_session_activities` (`UserSessionActivityId`),
  ADD CONSTRAINT `transactions_ibfk_4` FOREIGN KEY (`TransactionDescriptionId`) REFERENCES `transaction_descriptions` (`TransactionDescriptionId`),
  ADD CONSTRAINT `transactions_ibfk_5` FOREIGN KEY (`InstallmentId`) REFERENCES `installments` (`InstallmentId`),
  ADD CONSTRAINT `transactions_ibfk_6` FOREIGN KEY (`FeeCorrectionId`) REFERENCES `fee_corrections` (`FeeCorrectionId`),
  ADD CONSTRAINT `transactions_ibfk_7` FOREIGN KEY (`CarryFowardId`) REFERENCES `carry_forwards` (`CarryFowardId`),
  ADD CONSTRAINT `transactions_ibfk_8` FOREIGN KEY (`StudentId`) REFERENCES `students` (`StudentId`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`GenderId`) REFERENCES `gender` (`GenderId`);

--
-- Constraints for table `user_access_privileges`
--
ALTER TABLE `user_access_privileges`
  ADD CONSTRAINT `user_access_privileges_ibfk_1` FOREIGN KEY (`AccessPrivilegeId`) REFERENCES `access_privileges` (`AccessPrivilegeId`),
  ADD CONSTRAINT `user_access_privileges_ibfk_2` FOREIGN KEY (`UserRoleId`) REFERENCES `user_roles` (`UserRoleId`);

--
-- Constraints for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `user_roles_ibfk_1` FOREIGN KEY (`UserId`) REFERENCES `users` (`UserId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `user_roles_ibfk_2` FOREIGN KEY (`RoleId`) REFERENCES `roles` (`RoleId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `user_session_activities`
--
ALTER TABLE `user_session_activities`
  ADD CONSTRAINT `user_session_activities_ibfk_1` FOREIGN KEY (`SessionLogId`) REFERENCES `session_logs` (`SessionLogId`),
  ADD CONSTRAINT `user_session_activities_ibfk_2` FOREIGN KEY (`SessionActivityId`) REFERENCES `session_activities` (`SessionActivityId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
