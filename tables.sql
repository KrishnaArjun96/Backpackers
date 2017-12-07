-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: backpackers
-- ------------------------------------------------------
-- Server version	5.7.19-log

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
-- Table structure for table `airline`
--

DROP TABLE IF EXISTS `airline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `airline` (
  `Id` char(2) NOT NULL,
  `Name` varchar(20) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airline`
--

LOCK TABLES `airline` WRITE;
/*!40000 ALTER TABLE `airline` DISABLE KEYS */;
INSERT INTO `airline` VALUES ('AA','American Airlines'),('AB','Air Berlin'),('B6','JetBlue Airways'),('BA','British Airlines'),('DL','Delta Airlines'),('EK','Emirates'),('LH','Lufthansa'),('MD','Air Madagascar'),('NQ','Air Japan'),('UA','United Airlines'),('WN','Southwest Airlines');
/*!40000 ALTER TABLE `airline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `airport`
--

DROP TABLE IF EXISTS `airport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `airport` (
  `Id` char(3) NOT NULL,
  `Name` varchar(75) NOT NULL,
  `City` varchar(75) NOT NULL,
  `Country` varchar(75) NOT NULL,
  `IsVisible` bit(1) DEFAULT b'1',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `airport`
--

LOCK TABLES `airport` WRITE;
/*!40000 ALTER TABLE `airport` DISABLE KEYS */;
INSERT INTO `airport` VALUES ('ATL','Hartsfield-Jackson Atlanta International','Atlanta','United States of America',''),('BLR','Kempegowda International','Bengaluru','India',''),('BOS','Logan International','Boston','United States of America',''),('DXB','Dubai International','Dubai','United Arab Emirates',''),('HND','Tokyo International','Tokyo','Japan',''),('JFK','John F. Kennedy International','New York','United States of America',''),('LAX','Los Angeles International','Los Angeles','United States of America',''),('LGA','LaGuardia','New York','United States of America',''),('LHR','London Heathrow','London','United Kingdom',''),('ORD','Chicago O\'Hare International','Chicago','United States of America',''),('SFO','San Francisco International','San Francisco','United States of America',''),('TNR','Ivato International','Antananarivo','Madagascar',''),('TXL','Berlin Tegel','Berlin','Germany','');
/*!40000 ALTER TABLE `airport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auction`
--

DROP TABLE IF EXISTS `auction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auction` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `BookingId` int(11) NOT NULL,
  `FlightNo` int(11) NOT NULL,
  `AirlineId` char(2) NOT NULL,
  `TravelDate` date NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `LegId` (`BookingId`,`FlightNo`,`AirlineId`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auction`
--

LOCK TABLES `auction` WRITE;
/*!40000 ALTER TABLE `auction` DISABLE KEYS */;
INSERT INTO `auction` VALUES (19,1,565,'EK','2017-12-12'),(20,2,215,'EK','2017-12-13'),(21,3,216,'EK','2017-12-28');
/*!40000 ALTER TABLE `auction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bid`
--

DROP TABLE IF EXISTS `bid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bid` (
  `AuctionId` int(11) NOT NULL,
  `ResrNo` int(11) NOT NULL,
  `Status` bit(1) DEFAULT b'0',
  `BidAmount` decimal(10,2) NOT NULL,
  PRIMARY KEY (`AuctionId`,`ResrNo`),
  KEY `bid_reservation_ResrNo_fk` (`ResrNo`),
  CONSTRAINT `bid_auction_Id_fk` FOREIGN KEY (`AuctionId`) REFERENCES `auction` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bid_reservation_ResrNo_fk` FOREIGN KEY (`ResrNo`) REFERENCES `reservation` (`ResrNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bid`
--

LOCK TABLES `bid` WRITE;
/*!40000 ALTER TABLE `bid` DISABLE KEYS */;
INSERT INTO `bid` VALUES (19,932,'',2500.00),(20,932,'',2500.00);
/*!40000 ALTER TABLE `bid` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booking` (
  `Id` int(11) NOT NULL,
  `ResrNo` int(11) NOT NULL,
  `LegId` int(11) NOT NULL,
  `FlightNo` int(11) NOT NULL,
  `AirlineId` char(2) NOT NULL,
  `TravelDate` date NOT NULL,
  `ClassId` int(11) NOT NULL,
  `layover` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`,`ResrNo`),
  KEY `ResrNo` (`ResrNo`),
  KEY `LegId` (`LegId`,`FlightNo`,`AirlineId`),
  KEY `booking_class_Id_fk` (`ClassId`),
  CONSTRAINT `booking_class_Id_fk` FOREIGN KEY (`ClassId`) REFERENCES `class` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `booking_ibfk_1` FOREIGN KEY (`ResrNo`) REFERENCES `reservation` (`ResrNo`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `booking_ibfk_3` FOREIGN KEY (`LegId`, `FlightNo`, `AirlineId`) REFERENCES `leg` (`LegId`, `FlightNo`, `AirlineId`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (1,111,1,111,'AA','2017-12-27',4,'01:20'),(1,222,1,111,'B6','2017-12-20',5,'00:00'),(1,333,1,1337,'MD','2017-12-30',9,'00:00'),(1,444,1,567,'EK','2018-01-26',30,'02:20'),(1,932,1,565,'EK','2017-12-12',51,'19:30'),(2,111,2,111,'AA','2017-12-27',4,'00:00'),(2,444,1,203,'EK','2018-01-27',21,'03:15'),(2,932,1,215,'EK','2017-12-13',32,'00:00'),(3,444,1,442,'B6','2018-01-27',60,'00:00'),(3,932,1,216,'EK','2017-12-28',35,'20:00');
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `class` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `FlightNo` int(11) NOT NULL,
  `AirlineId` char(2) NOT NULL,
  `Name` varchar(20) NOT NULL,
  `Aircraft` varchar(20) NOT NULL,
  `Fare` decimal(10,2) NOT NULL,
  `Seats` int(11) NOT NULL,
  `IsVisible` bit(1) DEFAULT b'1',
  `ClassRank` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `AirlineId` (`AirlineId`,`FlightNo`),
  CONSTRAINT `class_ibfk_1` FOREIGN KEY (`AirlineId`) REFERENCES `airline` (`Id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `class_ibfk_2` FOREIGN KEY (`AirlineId`, `FlightNo`) REFERENCES `flight` (`AirlineId`, `FlightNo`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class`
--

LOCK TABLES `class` WRITE;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` VALUES (1,111,'AA','First','Boeing 787-9',2500.00,8,'',4),(2,111,'AA','Business','Boeing 787-9',1800.00,22,'',3),(3,111,'AA','Premium Economy','Boeing 787-9',2100.00,8,'',2),(4,111,'AA','Economy','Boeing 787-9',1500.00,234,'',1),(5,111,'B6','Business','Airbus A321 neo',1800.00,16,'',2),(6,111,'B6','Economy','Airbus A321 neo',1000.00,143,'',1),(7,1337,'MD','Business','Airbus A340-300',1800.00,30,'',3),(8,1337,'MD','Premium Economy','Airbus A340-300',1500.00,21,'',2),(9,1337,'MD','Economy','Airbus A340-300',900.00,224,'',1),(10,112,'AA','First','Boeing 787-9',2500.00,8,'',4),(11,112,'AA','Business','Boeing 787-9',1800.00,22,'',3),(12,112,'AA','Premium Economy','Boeing 787-9',2100.00,8,'',2),(13,112,'AA','Economy','Boeing 787-9',2500.00,234,'',1),(14,112,'B6','Business','Airbus A321 neo',1800.00,16,'',2),(15,112,'B6','Economy','Airbus A321 neo',1000.00,143,'',1),(16,1336,'MD','Business','Airbus A340-300',1800.00,30,'',3),(17,1336,'MD','Premium Economy','Airbus A340-300',1500.00,21,'',2),(18,1336,'MD','Economy','Airbus A340-300',900.00,224,'',1),(19,203,'EK','First','Airbus A380-800',2500.00,14,'',3),(20,203,'EK','Business','Airbus A380-800',1800.00,76,'',2),(21,203,'EK','Economy','Airbus A380-800',700.00,399,'',1),(22,204,'EK','First','Airbus A380-800',2500.00,14,'',3),(23,204,'EK','Business','Airbus A380-800',1800.00,76,'',2),(24,204,'EK','Economy','Airbus A380-800',700.00,399,'',1),(25,566,'EK','First','Boeing 777-300ER',1200.00,8,'',3),(26,566,'EK','Business','Boeing 777-300ER',900.00,42,'',2),(27,566,'EK','Economy','Boeing 777-300ER',200.00,310,'',1),(28,567,'EK','First','Boeing 777-300ER',1200.00,8,'',3),(29,567,'EK','Business','Boeing 777-300ER',900.00,42,'',2),(30,567,'EK','Economy','Boeing 777-300ER',200.00,310,'',1),(31,215,'EK','First','Airbus A380-800',2500.00,14,'',3),(32,215,'EK','Business','Airbus A380-800',1800.00,76,'',2),(33,215,'EK','Economy','Airbus A380-800',700.00,399,'',1),(34,216,'EK','First','Airbus A380-800',2500.00,14,'',3),(35,216,'EK','Business','Airbus A380-800',1800.00,76,'',2),(36,216,'EK','Economy','Airbus A380-800',700.00,399,'',1),(37,225,'EK','First','Airbus A380-800',2500.00,14,'',3),(38,225,'EK','Business','Airbus A380-800',1800.00,76,'',2),(39,225,'EK','Economy','Airbus A380-800',700.00,399,'',1),(40,226,'EK','First','Airbus A380-800',2500.00,14,'',3),(41,226,'EK','Business','Airbus A380-800',1800.00,76,'',2),(42,226,'EK','Economy','Airbus A380-800',700.00,399,'',1),(43,442,'B6','Business','Airbus A321 neo',1800.00,16,'',0),(44,442,'B6','Economy','Airbus A321 neo',1000.00,143,'',1),(45,443,'B6','Business','Airbus A321 neo',1800.00,16,'',0),(46,443,'B6','Economy','Airbus A321 neo',1000.00,143,'',1),(47,564,'EK','First','Boeing 777-300ER',1200.00,8,'',3),(48,564,'EK','Business','Boeing 777-300ER',900.00,42,'',2),(49,564,'EK','Economy','Boeing 777-300ER',200.00,310,'',1),(50,565,'EK','First','Boeing 777-300ER',1200.00,8,'',3),(51,565,'EK','Business','Boeing 777-300ER',900.00,42,'',2),(52,565,'EK','Economy','Boeing 777-300ER',200.00,310,'',1),(53,568,'EK','First','Boeing 777-300ER',1200.00,8,'',3),(54,568,'EK','Business','Boeing 777-300ER',900.00,42,'',2),(55,568,'EK','Economy','Boeing 777-300ER',200.00,310,'',1),(56,569,'EK','First','Boeing 777-300ER',1200.00,8,'',3),(57,569,'EK','Business','Boeing 777-300ER',900.00,42,'',2),(58,569,'EK','Economy','Boeing 777-300ER',200.00,310,'',1),(59,442,'B6','Business','Airbus A321 neo',600.00,16,'',2),(60,442,'B6','Economy','Airbus A321 neo',300.00,143,'',1),(61,443,'B6','Business','Airbus A321 neo',600.00,16,'',2),(62,443,'B6','Economy','Airbus A321 neo',300.00,143,'',1);
/*!40000 ALTER TABLE `class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `PersonId` int(11) NOT NULL,
  `UserId` varchar(20) NOT NULL,
  `JoinDate` date NOT NULL,
  `CardNo` char(16) NOT NULL,
  `SeatPref` varchar(20) DEFAULT NULL,
  `MealPref` varchar(20) DEFAULT NULL,
  `Rating` int(11) DEFAULT NULL,
  PRIMARY KEY (`PersonId`,`UserId`),
  CONSTRAINT `customer_person_Id_fk` FOREIGN KEY (`PersonId`) REFERENCES `person` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (19,'awesomejane@ftw.com','2015-12-06','1111222233334444','window','veg',3),(20,'jdoe@woot.com','2017-05-15','5555666677778888','aisle','sea food',4),(21,'rickroller@rolld.com','2017-10-25','9999000011112222','window','vegan',5);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `PersonId` int(11) NOT NULL,
  `SSN` char(9) NOT NULL,
  `StartDate` date NOT NULL,
  `Role` varchar(20) NOT NULL,
  `WageHourly` decimal(10,2) NOT NULL,
  PRIMARY KEY (`PersonId`,`SSN`),
  CONSTRAINT `employee_person_Id_fk` FOREIGN KEY (`PersonId`) REFERENCES `person` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (22,'121224455','2017-01-02','employee',45.00),(23,'303112222','2017-06-04','employee',40.00),(24,'541023333','2016-08-08','manager',55.00);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flight`
--

DROP TABLE IF EXISTS `flight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flight` (
  `FlightNo` int(11) NOT NULL,
  `AirlineId` char(2) NOT NULL,
  `Stops` int(11) NOT NULL,
  `Days` int(11) NOT NULL,
  `IsVisible` bit(1) DEFAULT b'1',
  PRIMARY KEY (`FlightNo`,`AirlineId`),
  KEY `AirlineId` (`AirlineId`),
  CONSTRAINT `flight_ibfk_1` FOREIGN KEY (`AirlineId`) REFERENCES `airline` (`Id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight`
--

LOCK TABLES `flight` WRITE;
/*!40000 ALTER TABLE `flight` DISABLE KEYS */;
INSERT INTO `flight` VALUES (111,'AA',2,1010100,''),(111,'B6',2,1111111,''),(112,'AA',2,1010100,''),(112,'B6',2,1111111,''),(203,'EK',1,1111111,''),(204,'EK',1,1111111,''),(215,'EK',1,1111111,''),(216,'EK',1,1111111,''),(225,'EK',1,1111111,''),(226,'EK',1,1111111,''),(442,'B6',1,1111111,''),(443,'B6',1,1111111,''),(564,'EK',1,1111111,''),(565,'EK',1,1111111,''),(566,'EK',1,1111111,''),(567,'EK',1,1111111,''),(568,'EK',1,1111111,''),(569,'EK',1,1111111,''),(1336,'MD',1,11,''),(1337,'MD',1,11,'');
/*!40000 ALTER TABLE `flight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leg`
--

DROP TABLE IF EXISTS `leg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `leg` (
  `LegId` int(11) NOT NULL,
  `FlightNo` int(11) NOT NULL,
  `AirlineId` char(2) NOT NULL,
  `Origin` char(3) NOT NULL,
  `Destination` char(3) NOT NULL,
  `Departure` time NOT NULL,
  `Arrival` time NOT NULL,
  `Duration` time NOT NULL,
  PRIMARY KEY (`LegId`,`FlightNo`,`AirlineId`),
  KEY `AirlineId` (`AirlineId`,`FlightNo`),
  KEY `Origin` (`Origin`),
  KEY `Destination` (`Destination`),
  CONSTRAINT `leg_ibfk_1` FOREIGN KEY (`AirlineId`, `FlightNo`) REFERENCES `flight` (`AirlineId`, `FlightNo`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `leg_ibfk_2` FOREIGN KEY (`Origin`) REFERENCES `airport` (`Id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `leg_ibfk_3` FOREIGN KEY (`Destination`) REFERENCES `airport` (`Id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leg`
--

LOCK TABLES `leg` WRITE;
/*!40000 ALTER TABLE `leg` DISABLE KEYS */;
INSERT INTO `leg` VALUES (1,111,'AA','LGA','LAX','17:00:00','20:10:00','06:00:00'),(1,111,'B6','SFO','BOS','19:30:00','04:50:00','05:00:00'),(1,112,'AA','HND','LAX','09:00:00','14:10:00','06:00:00'),(1,112,'B6','LHR','BOS','14:30:00','17:20:00','05:00:00'),(1,203,'EK','DXB','JFK','02:30:00','08:15:00','14:00:00'),(1,204,'EK','JFK','DXB','10:40:00','08:40:00','14:00:00'),(1,215,'EK','DXB','LAX','08:30:00','12:45:00','16:00:00'),(1,216,'EK','LAX','DXB','15:20:00','07:40:00','16:00:00'),(1,225,'EK','DXB','SFO','08:20:00','13:05:00','16:00:00'),(1,226,'EK','SFO','DXB','15:40:00','07:25:00','16:00:00'),(1,442,'B6','JFK','SFO','11:30:00','14:30:00','16:00:00'),(1,443,'B6','SFO','JFK','21:20:00','06:20:00','16:00:00'),(1,564,'EK','DXB','BLR','03:40:00','08:50:00','04:00:00'),(1,565,'EK','BLR','DXB','10:25:00','13:00:00','04:00:00'),(1,566,'EK','DXB','BLR','13:30:00','19:00:00','04:00:00'),(1,567,'EK','BLR','DXB','20:00:00','23:10:00','04:00:00'),(1,568,'EK','DXB','BLR','02:25:00','21:50:00','04:00:00'),(1,569,'EK','BLR','DXB','04:20:00','06:50:00','04:00:00'),(1,1336,'MD','TNR','JFK','13:00:00','18:50:00','08:00:00'),(1,1337,'MD','JFK','TNR','23:00:00','11:00:00','08:00:00'),(2,111,'AA','LAX','HND','21:30:00','03:00:00','06:00:00'),(2,111,'B6','BOS','LHR','06:00:00','12:15:00','05:00:00'),(2,112,'AA','LAX','LGA','15:30:00','23:50:00','06:00:00'),(2,112,'B6','BOS','SFO','19:00:00','23:00:00','05:00:00');
/*!40000 ALTER TABLE `leg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passenger`
--

DROP TABLE IF EXISTS `passenger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `passenger` (
  `PaxId` int(11) NOT NULL,
  `ResrNo` int(11) NOT NULL,
  `Name` varchar(20) NOT NULL,
  `SeatPref` varchar(20) DEFAULT NULL,
  `MealPref` varchar(20) DEFAULT NULL,
  KEY `ResrNo` (`ResrNo`),
  CONSTRAINT `passenger_ibfk_1` FOREIGN KEY (`ResrNo`) REFERENCES `reservation` (`ResrNo`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passenger`
--

LOCK TABLES `passenger` WRITE;
/*!40000 ALTER TABLE `passenger` DISABLE KEYS */;
INSERT INTO `passenger` VALUES (1,111,'Ashlynn Kutch','window','Veg'),(2,111,'Douglas Harber','Aisle','Low Calorie'),(3,111,'Jane Smith','window','Veg'),(1,222,'John Doe','Aisle','Sea Food'),(1,333,'Mauricio Prohaska','window','Veg'),(2,333,'Javon Kertzmann','Aisle','Halal'),(3,333,'Rick Astley','window','Vegan'),(1,444,'Celestino Cremin','Aisle','Vegan'),(2,444,'Elliot Blick','window','Low Calorie'),(3,444,'Jane Smith','window','Veg');
/*!40000 ALTER TABLE `passenger` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(20) NOT NULL,
  `LastName` varchar(20) NOT NULL,
  `Address` varchar(80) NOT NULL,
  `City` varchar(75) DEFAULT NULL,
  `State` varchar(75) DEFAULT NULL,
  `Phone` varchar(20) NOT NULL,
  `ZipCode` varchar(10) NOT NULL,
  `Country` varchar(75) NOT NULL,
  `Username` varchar(30) NOT NULL,
  `Password` varchar(30) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `person_Id_uindex` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (19,'Jane','Smith','100 Nicolls Rd','Stony Brook','New York','+1 (555) 555-5555','17790','United States of America','jSmith','janesmith'),(20,'John','Doe','123 N Fake Street','New York City','New York','+1 (123) 123-1234','10001','United States of America','jDoe','johndoe'),(21,'Rick','Astley','1337 Internet Lane','Los Angeles','California','+1 (314) 159-2653','90001','United States of America','rAstley','rickastley'),(22,'Benito','Mills','2904 Stanton Hollow Road','Winchester','Massachusetts','+1 (781) 460-4856','01890','United States of America','bMills','benitomills'),(23,'Joshua','Schmidt','1329 Lyon Avenue','Westborough','Massachusetts','+1 (508) 868-9084','01581','United States of America','jSchmidt','joshuaschmidt'),(24,'Dong','Chen','2991 Haymond Rocks Road','Lostine','Oregon','+1 (541) 569-4222','97885','United States of America','dChen','dongchen');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation` (
  `ResrNo` int(11) NOT NULL,
  `BookingDate` date NOT NULL,
  `Fare` decimal(10,2) NOT NULL,
  `BookingFee` decimal(10,2) NOT NULL,
  `IsVisible` bit(1) DEFAULT b'1',
  `EmployeeId` int(11) NOT NULL,
  `EmployeeSSN` char(9) NOT NULL,
  `CustomerId` int(11) NOT NULL,
  `UserId` varchar(20) NOT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ResrNo`),
  KEY `EmployeeId` (`EmployeeId`,`EmployeeSSN`),
  KEY `CustomerId` (`CustomerId`,`UserId`),
  CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`EmployeeId`, `EmployeeSSN`) REFERENCES `employee` (`PersonId`, `SSN`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`CustomerId`, `UserId`) REFERENCES `customer` (`PersonId`, `UserId`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (111,'2017-09-04',1500.00,150.00,'',22,'121224455',19,'awesomejane@ftw.com','auctioned'),(222,'2017-10-10',1800.00,180.00,'',23,'303112222',20,'jdoe@woot.com','booked'),(333,'2017-10-31',900.00,90.00,'',23,'303112222',21,'rickroller@rolld.com','booked'),(444,'2017-11-21',2100.00,210.00,'',22,'121224455',19,'awesomejane@ftw.com','booked'),(932,'2017-12-07',5400.00,540.00,'',22,'121224455',19,'awesomejane@ftw.com','auctioned');
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-07 17:25:52
