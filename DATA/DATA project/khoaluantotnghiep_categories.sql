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
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createday` varchar(255) DEFAULT NULL,
  `deleteday` varchar(255) DEFAULT NULL,
  `namesearch` varchar(255) DEFAULT NULL,
  `personcreate` int(11) NOT NULL,
  `persondelete` int(11) NOT NULL,
  `personupdate` int(11) NOT NULL,
  `updateday` varchar(255) DEFAULT NULL,
  `banner` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (2,'2023-10-28 20:53:16.107',NULL,'rau-cu-qua',1,0,1,'2024-10-04 16:13:51.752',NULL,'Rau củ quả sạch là nông sản an toàn, không dùng hóa chất độc hại.  \nChúng giữ hương vị tự nhiên, giàu dinh dưỡng và tốt cho sức khỏe.  \nSản phẩm này góp phần bảo vệ sức khỏe người tiêu dùng và môi trường.',NULL,'Rau - củ - quả'),(3,'2023-10-28 20:53:16.107',NULL,'Trai-cay-theo-mua',1,0,1,'2024-11-23 19:50:51.499',NULL,'Trái cây theo mùa không chỉ tươi ngon mà còn mang hương vị đặc trưng của từng thời điểm trong năm. Mùa xuân là mùa của dâu tây, quýt và mận, với vị ngọt thanh mát, đầy sức sống.',NULL,'Trái cây theo mùa'),(4,'2023-10-28 20:53:16.107',NULL,'Thit-ca-tuoi-song',1,0,1,'2023-10-28 20:55:38.959',NULL,'Thịt cá tươi sống là nguồn thực phẩm giàu dinh dưỡng, được tuyển chọn kỹ lưỡng để đảm bảo độ tươi ngon và an toàn vệ sinh thực phẩm. Với hương vị tự nhiên và giá trị dinh dưỡng cao, thịt cá tươi sống là lựa chọn lý tưởng cho bữa ăn gia đình.',NULL,'Thịt cá tươi sống'),(5,'2023-10-28 20:53:16.107','2023-12-15 18:07:33.25','Thuc-pham-che-bien-san',1,1,1,'2023-10-28 20:58:43.712',NULL,'OPPO là một công ty điện tử tiêu dùng lớn của Trung Quốc, chuyên thiết kế, phát triển và bán các sản phẩm điện tử tiêu dùng chất lượng cao, giá cả phải chăng. OPPO nổi tiếng với các sản phẩm điện thoại thông minh chất lượng cao, camera và hiệu năng.',NULL,'Thực phẩm chế biến sẵn'),(6,'2023-10-28 20:53:16.107',NULL,'Thuc-pham-kho',1,0,1,'2023-10-28 21:00:59.748',NULL,'Thực phẩm khô là các loại thực phẩm được chế biến và bảo quản lâu dài như hạt, trái cây sấy, nấm khô, mì, hay tôm khô. Tiện lợi, dễ bảo quản và giàu dinh dưỡng, thực phẩm khô là lựa chọn lý tưởng cho bữa ăn nhanh và dự trữ dài hạn.',NULL,'Thực phẩm khô'),(8,'2023-10-28 20:53:16.107',NULL,'Gia-vi',1,0,1,'2023-10-28 21:08:20.255',NULL,'Gia vị là những nguyên liệu không thể thiếu để tạo nên hương vị đậm đà và đặc trưng cho món ăn. Bao gồm muối, tiêu, đường, nước mắm, bột ngọt, và các loại thảo mộc, gia vị giúp nâng tầm ẩm thực, mang đến trải nghiệm ẩm thực phong phú và hấp dẫn.',NULL,'Gia vị'),(9,'2023-10-28 20:53:16.107',NULL,'Do-uong-tot-cho-suc-khoe',1,0,1,'2023-10-28 21:09:34.661',NULL,'Đồ uống tốt cho sức khỏe bao gồm nước ép trái cây, trà thảo mộc, sữa hạt, và các loại nước detox. Chúng cung cấp vitamin, khoáng chất, hỗ trợ thanh lọc cơ thể, tăng cường đề kháng và mang lại cảm giác sảng khoái tự nhiên.',NULL,'Đồ uống');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
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
