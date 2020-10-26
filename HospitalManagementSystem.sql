-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: localhost    Database: hospitalmanagement
-- ------------------------------------------------------
-- Server version	5.6.25-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `appointments`
--

DROP TABLE IF EXISTS `appointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doctorId` int(11) NOT NULL,
  `patientId` int(11) NOT NULL,
  `time` varchar(45) NOT NULL,
  `date` varchar(25) NOT NULL,
  `createdBy` varchar(25) DEFAULT NULL,
  `modifiedBy` varchar(25) DEFAULT NULL,
  `createdDatetime` datetime DEFAULT NULL,
  `modifiedDatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `doctorId_idx` (`doctorId`),
  KEY `patientId_idx` (`patientId`),
  CONSTRAINT `doctorId` FOREIGN KEY (`doctorId`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `patientId` FOREIGN KEY (`patientId`) REFERENCES `patients` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointments`
--

LOCK TABLES `appointments` WRITE;
/*!40000 ALTER TABLE `appointments` DISABLE KEYS */;
INSERT INTO `appointments` VALUES (1,3,1,'11:00 AM','2020-05-01','receptionist','doctor','2020-01-19 00:00:00','2020-01-22 00:00:00'),(2,3,2,'12:00 AM','2020-05-01','receptionist','doctor','2020-01-19 00:00:00','2020-01-22 00:00:00'),(3,3,3,'01:00 PM','2020-05-01','receptionist','doctor','2020-01-19 00:00:00','2020-01-22 00:00:00'),(5,3,2,'11:00','2020-10-20','admin','admin','2020-10-19 04:57:14','2020-10-19 05:09:33'),(6,3,1,'13:56','2020-10-12','admin','admin','2020-10-19 07:53:37','2020-10-19 07:53:37'),(7,3,1,'12:57','2020-10-08','admin','admin','2020-10-19 07:55:58','2020-10-19 07:55:58'),(8,3,3,'01:00 PM','2020-05-01','admin','admin','2020-10-19 07:57:39','2020-10-19 07:57:39'),(9,3,3,'01:00 PM','2020-05-01','admin','admin','2020-10-19 07:59:11','2020-10-19 07:59:11');
/*!40000 ALTER TABLE `appointments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patients`
--

DROP TABLE IF EXISTS `patients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patients` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(25) DEFAULT NULL,
  `lastName` varchar(25) NOT NULL,
  `gender` varchar(25) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `birthDate` varchar(25) DEFAULT NULL,
  `phoneNumber` varchar(25) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `maritalStatus` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `createdBy` varchar(25) DEFAULT NULL,
  `modifiedBy` varchar(25) DEFAULT NULL,
  `created_Datetime` datetime NOT NULL,
  `modified_Datetime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patients`
--

LOCK TABLES `patients` WRITE;
/*!40000 ALTER TABLE `patients` DISABLE KEYS */;
INSERT INTO `patients` VALUES (1,'Adam','Berg','male',22,'1998-09-17','7392658354','patient1@gmil.com','single','Roselin','Buddy street, 10th building, 235 appart','receptionist','doctor','2020-01-17 00:00:00','2020-01-20 00:00:00'),(2,'Elisabeth','Marries','female',17,'2003-04-05','9473862539','patient2@gmil.com','single','Ruhpolding','Sweet street, 12th building, 133 appart','receptionist','doctor','2020-01-18 00:00:00','2020-01-21 00:00:00'),(3,'Evans','Clark','male',21,'1999-02-15','3965748355','patient3@gmil.com','single','New-York','Baker street, 3rd building, 21 appart','receptionist','doctor','2020-01-19 00:00:00','2020-01-22 00:00:00'),(7,'Elle','Fanning','female',26,'1997-07-16','1234595367','elle123@gmail.com','married','New-York','Noah-Hao street','admin','admin','2020-10-19 05:11:50','2020-10-19 05:11:50');
/*!40000 ALTER TABLE `patients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  `createdBy` varchar(25) DEFAULT NULL,
  `modifiedBy` varchar(25) DEFAULT NULL,
  `createdDatetime` datetime NOT NULL,
  `modifiedDatetime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'Admin','Administrator','admin',NULL,'2019-10-16 11:16:03','2019-10-16 12:08:23'),(2,'Receptionist','Receptionist','admin',NULL,'2019-10-16 11:16:03','2019-10-16 12:08:23'),(3,'Doctor','Doctor','admin',NULL,'2019-10-16 11:16:03','2019-10-16 12:08:23');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `firstName` varchar(25) DEFAULT NULL,
  `lastName` varchar(25) NOT NULL,
  `gender` varchar(25) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `birthDate` varchar(25) DEFAULT NULL,
  `phoneNumber` varchar(25) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `maritalStatus` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `specification` varchar(45) DEFAULT NULL,
  `qualification` varchar(45) DEFAULT NULL,
  `hiringDate` varchar(25) DEFAULT NULL,
  `roleId` int(11) NOT NULL,
  `createdBy` varchar(25) DEFAULT NULL,
  `modifiedBy` varchar(25) DEFAULT NULL,
  `createdDatetime` datetime NOT NULL,
  `modifiedDatetime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `roleId_idx` (`roleId`),
  CONSTRAINT `roleId` FOREIGN KEY (`roleId`) REFERENCES `roles` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Admin123','123','Amelia','Atkinson','female',21,'1998-08-11','1234567891','admin123@gmail.com','single','New-York','Cardiologist','Medical degree','2019-10-01',1,'admin',NULL,'2020-01-01 12:11:24','2020-01-01 12:11:24'),(2,'Receptionist123','123','Robert','Reynolds','male',25,'1994-08-11','1234567891','receptionist123@gmail.com','married','New-York','Economist','Economic degree','2019-10-01',2,'admin',NULL,'2020-01-01 12:11:24','2020-01-01 12:11:24'),(3,'Doctor123','123','Davies','Dixon','male',28,'1991-08-11','1234567891','doctor123@gmail.com','single','New-York','Surgeon','Medical degree','2019-10-01',3,'admin',NULL,'2020-01-01 12:11:24','2020-01-01 12:11:24'),(7,'receptionist123','123','Regina','Todorenko','',18,'1999-10-20','6486936524','receptionist123@gmail.com','single','','','','2020-10-15',2,'admin','admin','2020-10-18 21:22:48','2020-10-18 21:22:48');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-19 11:16:05
