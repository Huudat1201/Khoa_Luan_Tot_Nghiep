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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createday` varchar(255) DEFAULT NULL,
  `deleteday` varchar(255) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `persondelete` int(11) NOT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `sex` int(11) NOT NULL,
  `subscribe` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1096 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1069,'2023-09-09 11:52:28.310',NULL,'Trần Thị B',0,'2024-11-11','tran.thi.b@example.com','1234567',1,1),(1070,'2023-09-09 11:52:28.310',NULL,'Lê Thành C',0,'2024-11-11','le.thanh.c@example.com','1234567',1,0),(1071,'2023-09-09 11:52:28.310',NULL,'Phạm Thanh D',0,'2024-11-11','pham.thanh.d@example.com','1234567',0,1),(1072,'2023-09-09 11:52:28.310',NULL,'Đỗ Văn E',0,'1987-05-18 11:52:28.310','do.van.e@example.com','1234567',1,0),(1,'2023-09-09 11:52:28.310',NULL,'Huudat Nguyen',0,'2001-12-01','Huudat1201@gmail.com','1234567',1,1),(1068,'2023-09-09 11:52:28.310',NULL,'Nguyễn Văn A',0,'1990-01-01 11:52:28.310','nguyen.van.a@example.com','1234567',1,1),(1073,'2023-09-09 11:52:28.310',NULL,'Hoàng Thị F',0,'2024-11-11','hoang.thi.f@example.com','1234567',0,1),(1074,'2023-09-09 11:52:28.310',NULL,'Nguyễn Hữu G',0,'1993-07-15 11:52:28.310','nguyen.huu.g@example.com','1234567',1,1),(1075,'2023-09-09 11:52:28.310',NULL,'Trương Mỹ H',0,'2024-11-11','truong.my.h@example.com','1234567',0,1),(1076,'2023-09-09 11:52:28.310',NULL,'Võ Tiến I',0,'1995-09-09 11:52:28.310','vo.tien.i@example.com','1234567',1,0),(1077,'2023-09-09 11:52:28.310',NULL,'Nguyễn Ánh J',0,'2024-12-07','nguyen.anh.j@example.com','1234567',0,0),(1078,'2023-09-09 11:52:28.310',NULL,'Phạm Mạnh K',0,'1996-11-11 11:52:28.310','pham.manh.k@example.com','1234567',1,1),(1079,'2023-09-09 11:52:28.310',NULL,'Nguyễn Huyền L',0,'2024-11-11','nguyen.huyen.l@example.com','1234567',0,1),(1080,'2023-09-09 11:52:28.310',NULL,'Đặng Hồng M',0,'1986-01-01 11:52:28.310','dang.hong.m@example.com','1234567',1,0),(1081,'2023-09-09 11:52:28.310',NULL,'Lý Quỳnh N',0,'','ly.quynh.n@example.com','1234567',1,0),(1082,'2023-09-09 11:52:28.310',NULL,'Bùi Quốc O',0,'1987-03-03 11:52:28.310','bui.quoc.o@example.com','1234567',1,1),(1083,'2023-09-09 11:52:28.310',NULL,'Nguyễn Ngọc P',0,'2024-12-05','nguyen.ngoc.p@example.com','1234567',1,0),(1084,'2023-09-09 11:52:28.310',NULL,'Đoàn Hoàng Q',0,'1992-05-05 11:52:28.310','doan.hoang.q@example.com','1234567',1,1),(1085,'2023-09-09 11:52:28.310',NULL,'Phạm Phương R',0,'2024-11-11','pham.phuong.r@example.com','1234567',1,1),(1088,'2024-11-11 15:59:05.029',NULL,'Nguyễn Hữu Đạt',0,NULL,'Huudat120101@gmail.com','1234567',0,0),(1087,'2023-09-09 11:52:28.310',NULL,'Trương Thu T',0,'2024-11-11','truong.thu.t@example.com','1234567',0,0),(1089,'2024-11-11 18:24:20.675',NULL,'Lê Phúc Hậu',0,NULL,'lph19032002@gmail.com','1234567',0,0),(1090,'2024-11-18 14:20:06.787',NULL,'Lê Phúc Hậu',0,NULL,'lph1903200212@gmail.com','1234567',0,0),(1091,'2024-11-23 19:15:57.754',NULL,'Hậu Hihi',0,NULL,'20110278@student.hcmute.edu.vn','1234567',0,0),(1092,'2024-11-23 19:25:25.969',NULL,'Trần Thị Hậu',0,NULL,'haule190302@gmail.com','1234567',0,0),(1093,'2024-11-29 21:43:18.517',NULL,'Nguyễn Trung Tín',0,NULL,'nguyentin514@gmail.com','Huudat1201#',0,0),(1094,'2024-12-12 18:17:20.013',NULL,'Nguyễn Hữu Đạt',0,NULL,'201106311@student.hcmute.edu.vn','1234567',0,0),(1095,'2024-12-13 11:53:02.656',NULL,'Nguyễn Hữu Đạt',0,NULL,'201106312@student.hcmute.edu.vn','1234567',0,0);
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

-- Dump completed on 2024-12-13 13:22:21
