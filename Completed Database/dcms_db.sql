-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 26, 2024 at 05:08 PM
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
-- Database: `dcms_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `appointment`
--

CREATE TABLE `appointment` (
  `AppointmentID` int(11) NOT NULL,
  `AppointmentDate` date NOT NULL,
  `AppointmentTime` varchar(100) NOT NULL,
  `TreatmentID` int(11) DEFAULT NULL,
  `PatientID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `appointment`
--

INSERT INTO `appointment` (`AppointmentID`, `AppointmentDate`, `AppointmentTime`, `TreatmentID`, `PatientID`) VALUES
(1, '2024-11-30', '2 PM -3 PM', 2, 1),
(3, '2024-12-03', '5 PM - 6 PM', 1, 5);

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--

CREATE TABLE `inventory` (
  `item_id` int(11) NOT NULL,
  `item_name` varchar(255) NOT NULL,
  `quantity_in_stock` int(11) NOT NULL,
  `selling_price` decimal(10,2) DEFAULT NULL,
  `last_restocked_date` date DEFAULT NULL,
  `expiry_date` date DEFAULT NULL,
  `status` enum('active','low stock','expired') DEFAULT 'active',
  `description` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`item_id`, `item_name`, `quantity_in_stock`, `selling_price`, `last_restocked_date`, `expiry_date`, `status`, `description`) VALUES
(1, 'Peroxide 50 ml', 55, 600.00, '2024-11-26', '2025-11-25', 'active', 'Used for teeth whitening'),
(3, 'Floss Sets', 30, 100.00, '2024-11-26', '2025-11-29', 'active', 'Used for removing excessive particle from your teeth such as dirt etc.');

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

CREATE TABLE `invoice` (
  `InvoiceID` int(11) NOT NULL,
  `PaymentID` int(11) DEFAULT NULL,
  `InvoiceDate` date NOT NULL,
  `TotalAmount` decimal(10,2) NOT NULL,
  `InvoiceStatus` enum('Paid','Unpaid','Cancelled') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `medicalrecord`
--

CREATE TABLE `medicalrecord` (
  `RecordID` int(11) NOT NULL,
  `PatientID` int(11) DEFAULT NULL,
  `PrescriptionID` int(11) DEFAULT NULL,
  `Diagnosis` varchar(255) NOT NULL,
  `Notes` text DEFAULT NULL,
  `RecordDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `PatientID` int(11) NOT NULL,
  `PatientName` varchar(100) DEFAULT NULL,
  `Phone` varchar(15) DEFAULT NULL,
  `PatientAddress` varchar(255) DEFAULT NULL,
  `DateofBirth` date DEFAULT NULL,
  `Gender` enum('Male','Female','Other') NOT NULL,
  `Allergies` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`PatientID`, `PatientName`, `Phone`, `PatientAddress`, `DateofBirth`, `Gender`, `Allergies`) VALUES
(1, 'Nikko Causapin', '09875623535', 'Nasugbu Batangas', '2024-11-14', 'Male', 'Allergic Rhinitis'),
(2, 'Christian Anchorez', '09373495874', 'Tuy Balayan, Batangas', '2003-07-10', 'Other', 'None'),
(3, 'Jenny Rubia', '09782645175', 'Nasugbu Batangas', '2002-09-30', 'Female', 'None'),
(5, 'Lance Malata', '09874375924', 'Nasugbu Batangas', '2004-10-07', 'Male', 'None'),
(6, 'Ley Vasquez', '09867856341', 'Tuy Batangas', '2003-07-09', 'Male', 'None');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `PaymentID` int(11) NOT NULL,
  `PatientID` int(11) DEFAULT NULL,
  `PaymentMethod` enum('Cash','Card','Online') NOT NULL,
  `TotalBill` decimal(10,0) NOT NULL,
  `PaymentStatus` enum('Pending','Completed','Failed') NOT NULL,
  `PaymentDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `prescription`
--

CREATE TABLE `prescription` (
  `PrescriptionID` int(11) NOT NULL,
  `TreatmentID` int(11) DEFAULT NULL,
  `PrescriptionCost` decimal(10,2) NOT NULL,
  `PatientName` int(11) NOT NULL,
  `PrescriptionQuantity` int(11) NOT NULL,
  `PrescriptionMedicine` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `prescription`
--

INSERT INTO `prescription` (`PrescriptionID`, `TreatmentID`, `PrescriptionCost`, `PatientName`, `PrescriptionQuantity`, `PrescriptionMedicine`) VALUES
(1, 2, 150.00, 1, 1, 'Antibiotics'),
(3, 2, 200.00, 5, 1, 'Antiobiotics and pain relievers'),
(4, 1, 100.00, 3, 1, 'Peroxide 50 ml'),
(8, 1, 100.00, 2, 1, 'Peroxide 50 ml, Floss Sets'),
(9, 2, 100.00, 6, 2, 'Peroxide 50 ml, Floss Sets');

-- --------------------------------------------------------

--
-- Table structure for table `treatment`
--

CREATE TABLE `treatment` (
  `TreatmentID` int(11) NOT NULL,
  `TreatmentName` varchar(100) NOT NULL,
  `CostOfTreatment` decimal(10,2) NOT NULL,
  `Medicines` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `treatment`
--

INSERT INTO `treatment` (`TreatmentID`, `TreatmentName`, `CostOfTreatment`, `Medicines`) VALUES
(1, 'Teeth Whitening', 2500.00, 'Peroxide'),
(2, 'Root Canal', 2000.00, 'Pain relievers');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`AppointmentID`),
  ADD KEY `PatientID` (`PatientID`),
  ADD KEY `TreatmentID` (`TreatmentID`);

--
-- Indexes for table `inventory`
--
ALTER TABLE `inventory`
  ADD PRIMARY KEY (`item_id`);

--
-- Indexes for table `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`InvoiceID`),
  ADD KEY `PaymentID` (`PaymentID`);

--
-- Indexes for table `medicalrecord`
--
ALTER TABLE `medicalrecord`
  ADD PRIMARY KEY (`RecordID`),
  ADD KEY `PatientID` (`PatientID`),
  ADD KEY `PrescriptionID` (`PrescriptionID`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`PatientID`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`PaymentID`),
  ADD KEY `PatientID` (`PatientID`);

--
-- Indexes for table `prescription`
--
ALTER TABLE `prescription`
  ADD PRIMARY KEY (`PrescriptionID`),
  ADD KEY `PrescriptionTreatment` (`TreatmentID`),
  ADD KEY `PatientName` (`PatientName`);

--
-- Indexes for table `treatment`
--
ALTER TABLE `treatment`
  ADD PRIMARY KEY (`TreatmentID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `appointment`
--
ALTER TABLE `appointment`
  MODIFY `AppointmentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `inventory`
--
ALTER TABLE `inventory`
  MODIFY `item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `invoice`
--
ALTER TABLE `invoice`
  MODIFY `InvoiceID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `medicalrecord`
--
ALTER TABLE `medicalrecord`
  MODIFY `RecordID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `patient`
--
ALTER TABLE `patient`
  MODIFY `PatientID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `PaymentID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `prescription`
--
ALTER TABLE `prescription`
  MODIFY `PrescriptionID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `treatment`
--
ALTER TABLE `treatment`
  MODIFY `TreatmentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `appointment`
--
ALTER TABLE `appointment`
  ADD CONSTRAINT `appointment_ibfk_1` FOREIGN KEY (`PatientID`) REFERENCES `patient` (`PatientID`) ON DELETE CASCADE,
  ADD CONSTRAINT `appointment_ibfk_2` FOREIGN KEY (`TreatmentID`) REFERENCES `treatment` (`TreatmentID`) ON DELETE SET NULL;

--
-- Constraints for table `invoice`
--
ALTER TABLE `invoice`
  ADD CONSTRAINT `invoice_ibfk_1` FOREIGN KEY (`PaymentID`) REFERENCES `payment` (`PaymentID`) ON DELETE CASCADE;

--
-- Constraints for table `medicalrecord`
--
ALTER TABLE `medicalrecord`
  ADD CONSTRAINT `medicalrecord_ibfk_1` FOREIGN KEY (`PatientID`) REFERENCES `patient` (`PatientID`) ON DELETE CASCADE,
  ADD CONSTRAINT `medicalrecord_ibfk_2` FOREIGN KEY (`PrescriptionID`) REFERENCES `prescription` (`PrescriptionID`) ON DELETE SET NULL;

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`PatientID`) REFERENCES `patient` (`PatientID`) ON DELETE CASCADE;

--
-- Constraints for table `prescription`
--
ALTER TABLE `prescription`
  ADD CONSTRAINT `prescription_ibfk_1` FOREIGN KEY (`TreatmentID`) REFERENCES `treatment` (`TreatmentID`) ON DELETE CASCADE,
  ADD CONSTRAINT `prescription_ibfk_2` FOREIGN KEY (`PatientName`) REFERENCES `patient` (`PatientID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
