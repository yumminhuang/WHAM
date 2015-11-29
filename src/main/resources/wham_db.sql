-- MySQL dump 10.13  Distrib 5.5.46, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: wham
-- ------------------------------------------------------
-- Server version	5.5.46-0ubuntu0.14.04.2

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
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booking` (
  `uId` int(11) NOT NULL,
  `eId` bigint(20) NOT NULL,
  `comment` varchar(1500) DEFAULT NULL,
  `dislike` bit(1) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  PRIMARY KEY (`uId`,`eId`),
  KEY `eId_idx` (`eId`),
  CONSTRAINT `eventId` FOREIGN KEY (`eId`) REFERENCES `event` (`eId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `userId` FOREIGN KEY (`uId`) REFERENCES `user` (`uId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event` (
  `eId` bigint(20) NOT NULL,
  `name` varchar(150) DEFAULT NULL,
  `url` varchar(150) DEFAULT NULL,
  `description` text,
  `startTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `capacity` int(11) DEFAULT NULL,
  `status` varchar(150) DEFAULT NULL,
  `logo_url` varchar(150) DEFAULT NULL,
  `organizerID` int(10) unsigned DEFAULT NULL,
  `categoryID` int(10) unsigned DEFAULT NULL,
  `subCategoryID` int(10) unsigned DEFAULT NULL,
  `address_1` varchar(150) DEFAULT NULL,
  `address_2` varchar(150) DEFAULT NULL,
  `city` varchar(150) DEFAULT NULL,
  `postal_code` varchar(150) DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  PRIMARY KEY (`eId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `uId` int(11) NOT NULL AUTO_INCREMENT,
  `emailId` varchar(45) DEFAULT NULL,
  `fName` varchar(45) DEFAULT NULL,
  `lName` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `address` varchar(150) DEFAULT NULL,
  `zipCode` varchar(150) DEFAULT NULL,
  `city` varchar(150) DEFAULT NULL,
  `password` varchar(250) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  PRIMARY KEY (`uId`),
  UNIQUE KEY `emailId_UNIQUE` (`emailId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `userpreference`
--

DROP TABLE IF EXISTS `userpreference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userpreference` (
  `uId` int(11) NOT NULL,
  `subCategoryId` int(11) unsigned NOT NULL,
  `CategoryId` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`uId`,`subCategoryId`),
  KEY `userId_idx` (`uId`),
  CONSTRAINT `userPref` FOREIGN KEY (`uId`) REFERENCES `user` (`uId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-23 20:47:21
