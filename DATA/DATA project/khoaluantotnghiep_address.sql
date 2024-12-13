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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fullname` varchar(255) DEFAULT NULL,
  `detail` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `ward` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6i66ijb8twgcqtetl8eeeed6v` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (37,'Lê Phúc Hậu','ĐH SPKT','Thành phố Thủ Đức','0842166643','Thành phố Hồ Chí Minh','Phường Linh Chiểu',1091,_binary ''),(38,'Lê Phúc Hậu','cafe ông giáo','Thành phố Thủ Đức','0842166643','Thành phố Hồ Chí Minh','Phường Long Thạnh Mỹ',1091,_binary ''),(39,'Nguyễn Trung Tín','Ủy ban nhân dân phường Tăng Nhơn Phú B','Thành phố Thủ Đức','0842166643','Thành phố Hồ Chí Minh','Phường Tăng Nhơn Phú B',1093,_binary ''),(40,'Huudat Nguyen','Cafe Hoàng Anh','Thành phố Thủ Đức','0842166643','Thành phố Hồ Chí Minh','Phường Tăng Nhơn Phú A',1088,_binary ''),(32,'Huudat Nguyen','Ủy ban nhân dân phường Tăng Nhơn Phú B','Thành phố Thủ Đức','0842166643','Thành phố Hồ Chí Minh','Phường Tăng Nhơn Phú B',1,_binary ''),(33,'Huudat Nguyen','Số 1 Võ Văn Ngân','Thành phố Thủ Đức','0842166643','Thành phố Hồ Chí Minh','Phường Linh Chiểu',1088,_binary ''),(34,'Lê Phúc Hậu','Sư phạm kỹ thuật','Thành phố Thủ Đức','0842166643','Thành phố Hồ Chí Minh','Phường Linh Chiểu',1089,_binary ''),(35,'Huudat Nguyen','Cafe Hoàng Anh','Quận Tây Hồ','0842166643','Thành phố Hà Nội','Phường Xuân La',1091,_binary ''),(36,'Huudat Nguyen','Cafe Hoàng Anh','Quận Hoàng Mai','0842166643','Thành phố Hà Nội','Phường Mai Động',1092,_binary ''),(41,'Huudat Nguyen','Ủy ban nhân dân phường Tăng Nhơn Phú B','Thành phố Thủ Đức','0842166643','Thành phố Hồ Chí Minh','Phường Tăng Nhơn Phú B',1094,_binary '');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-13 13:22:22
