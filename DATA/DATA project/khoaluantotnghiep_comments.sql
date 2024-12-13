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
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `star` int(11) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6uv0qku8gsu6x1r2jkrtqwjtn` (`product_id`),
  KEY `FK8omq0tc18jd43bu5tjh6jvraq` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (28,'Oke tươi ngon','2024-11-11',5,'1',79,1088),(29,'Oke ngon','2024-11-20',5,'1',89,1088),(30,'Nghê','2024-11-20',5,'1',88,1088),(31,'Ngon','2024-11-20',5,'1',86,1089),(32,'Ngon','2024-11-20',5,'1',87,1089),(33,'Ngon, lành mạnh','2024-11-23',5,'1',95,1),(27,'Sản phẩm tươi ngon','2024-10-23',5,'1',83,1),(34,'Ngon','2024-11-23',5,'1',95,1089),(35,'Oke','2024-11-23',5,'1',84,1091),(36,'Ngon','2024-11-23',5,'1',91,1092),(37,'Oke','2024-11-23',5,'1',92,1092),(38,'ghê','2024-11-24',5,'1',95,1091),(39,'ngon','2024-11-24',4,'1',80,1088),(40,'Đắt','2024-11-25',5,'1',109,1),(41,'Cũng lạ đấy, thử xem','2024-11-25',4,'1',110,1088),(42,'Ngon','2024-11-25',5,'1',108,1088),(43,'Tươi ngon bổ','2024-11-25',5,'1',106,1088),(44,'cũng được, thử xem!','2024-11-25',5,'1',111,1091),(45,'thử xem, cũng an toàn, ngon','2024-11-25',5,'1',102,1091),(46,'cũng ngon, an toàn','2024-11-25',5,'1',102,1091),(47,'cũng ngon','2024-11-25',4,'1',107,1091),(48,'một sản phẩm ngon, bổ, rẻ và rất ngon','2024-11-25',5,'1',104,1091),(49,'cũng được','2024-11-25',5,'1',92,1092),(50,'cũng được','2024-11-25',5,'1',94,1092),(51,'cũng tươi ngon đấy nhỉ','2024-11-25',5,'1',90,1089),(52,'thử xem nào!!!','2024-11-25',5,'1',109,1092),(53,'Cũng được','2024-11-26',5,'1',131,1091),(54,'Rẻ','2024-11-26',5,'1',135,1091),(55,'Thử xem!','2024-11-26',5,'1',115,1091),(56,'Xem thử','2024-11-26',5,'1',124,1092),(57,'Tươi','2024-11-26',5,'1',121,1092),(58,'Thử xem','2024-11-26',5,'1',129,1089),(59,'cũng oke','2024-11-27',4,'1',166,1091),(60,'dở','2024-11-27',3,'1',165,1091),(61,'cũng được của nó đấy','2024-11-27',5,'1',122,1091),(62,'thử xem có oke không?','2024-11-27',5,'1',146,1091),(63,'thử xem','2024-11-27',4,'1',164,1088),(64,'Oke','2024-11-27',5,'1',162,1088),(65,'mua ngay','2024-11-27',5,'1',134,1088),(66,'Oke','2024-11-27',4,'1',148,1088),(67,'cũng được','2024-11-27',5,'1',158,1092),(68,'Rất ngon, ủng hộ shop nhiều nhiều','2024-11-27',5,'1',145,1089),(69,'Cũng được của nó đấy em Đạt!!!','2024-11-29',5,'1',164,1093),(70,'God bro','2024-12-09',5,'1',109,1),(71,'Good','2024-12-09',5,'1',121,1);
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-13 13:22:25
