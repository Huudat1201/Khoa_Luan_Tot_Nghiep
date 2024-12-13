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
-- Table structure for table `favorites`
--

DROP TABLE IF EXISTS `favorites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `favorites` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(255) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6sgu5npe8ug4o42bf9j71x20c` (`product_id`),
  KEY `FKk7du8b8ewipawnnpg76d55fus` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorites`
--

LOCK TABLES `favorites` WRITE;
/*!40000 ALTER TABLE `favorites` DISABLE KEYS */;
INSERT INTO `favorites` VALUES (39,'2024-11-23',95,1089),(38,'2024-11-23',95,1),(37,'2024-11-20',87,1089),(36,'2024-11-20',86,1089),(35,'2024-11-20',88,1088),(34,'2024-11-20',89,1088),(33,'2024-11-18',86,1),(32,'2024-11-16',80,1),(31,'2024-11-16',79,1),(30,'2024-11-16',84,1089),(29,'2024-11-12',84,1088),(28,'2024-11-12',80,1089),(27,'2024-11-11',79,1088),(24,'2024-10-22',84,1),(26,'2024-10-23',83,1),(40,'2024-11-23',84,1091),(41,'2024-11-23',91,1092),(42,'2024-11-23',92,1092),(43,'2024-11-24',95,1091),(44,'2024-11-24',80,1088),(45,'2024-11-25',108,1),(46,'2024-11-25',109,1),(47,'2024-11-25',110,1088),(48,'2024-11-25',108,1088),(49,'2024-11-25',106,1088),(50,'2024-11-25',111,1091),(51,'2024-11-25',102,1091),(52,'2024-11-25',107,1091),(53,'2024-11-25',94,1092),(54,'2024-11-25',90,1089),(55,'2024-11-25',109,1092),(56,'2024-11-26',120,1),(57,'2024-11-26',131,1091),(58,'2024-11-26',135,1091),(59,'2024-11-26',115,1091),(60,'2024-11-26',124,1092),(61,'2024-11-26',121,1092),(62,'2024-11-26',129,1089),(63,'2024-11-26',133,1088),(64,'2024-11-27',166,1091),(65,'2024-11-27',165,1091),(66,'2024-11-27',122,1091),(67,'2024-11-27',146,1091),(68,'2024-11-27',164,1088),(69,'2024-11-27',134,1088),(70,'2024-11-27',158,1092),(71,'2024-11-27',145,1089);
/*!40000 ALTER TABLE `favorites` ENABLE KEYS */;
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