-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: khoaluantotnghiep
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `informationshop`
--

DROP TABLE IF EXISTS `informationshop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `informationshop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createday` varchar(255) DEFAULT NULL,
  `deleteday` varchar(255) DEFAULT NULL,
  `logofooter` varchar(255) DEFAULT NULL,
  `personcreate` int(11) NOT NULL,
  `persondelete` int(11) NOT NULL,
  `personupdate` int(11) NOT NULL,
  `timeopen` varchar(255) DEFAULT NULL,
  `updateday` varchar(255) DEFAULT NULL,
  `active` bit(1) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `informationshop`
--

LOCK TABLES `informationshop` WRITE;
/*!40000 ALTER TABLE `informationshop` DISABLE KEYS */;
INSERT INTO `informationshop` VALUES (1,'2023-09-29 15:58:27.223',NULL,'logo.jpg',1,0,1,'7:00 AM đến 11:00 PM','2023-12-19 21:22:46.192',_binary '','1 Võ Văn Ngân, Phường Linh Chiểu, Thành phố Thủ Đức, Thành phố Hồ Chí Minh','cskh@hotro.ecart.vn','0842166643','logo.jpg','Ecart Shop','0842166643'),(2,'2023-09-29 15:59:52.360',NULL,'logo.jpg',1,0,1,'11:00 AM đến 11:00 PM','2021-09-30 16:40:53.460',_binary '\0','Tăng Nhơn Phú B, quận 9, TPHCM','demo@gmail.com','0842166643','logo.jpg','Shop Cart','0912233498');
/*!40000 ALTER TABLE `informationshop` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-13 13:22:26
