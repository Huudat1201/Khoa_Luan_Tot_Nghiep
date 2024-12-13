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
-- Table structure for table `manufactures`
--

DROP TABLE IF EXISTS `manufactures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `manufactures` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createday` varchar(255) DEFAULT NULL,
  `deleteday` varchar(255) DEFAULT NULL,
  `personcreate` int(11) NOT NULL,
  `persondelete` int(11) NOT NULL,
  `personupdate` int(11) NOT NULL,
  `updateday` varchar(255) DEFAULT NULL,
  `banner` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manufactures`
--

LOCK TABLES `manufactures` WRITE;
/*!40000 ALTER TABLE `manufactures` DISABLE KEYS */;
INSERT INTO `manufactures` VALUES (2,'2023-12-15 12:57:13.209',NULL,1,0,0,NULL,'apple.png','Sự hoàn hảo tinh tế đúng chất Apple. iPhone - Hiệu suất vượt trội, thiết kế đẳng cấp, và trải nghiệm người dùng đỉnh cao. Sáng tạo không ngừng, đưa tương lai vào tay bạn.','apple.png','Organicfood.vn'),(3,'2023-12-15 12:58:16.51',NULL,1,0,0,NULL,'samsung.png','Samsung - Kết nối tối ưu, sáng tạo không ngừng. Trải nghiệm đỉnh cao với công nghệ hàng đầu thế giới. Sự linh hoạt và phong cách hoàn hảo, mỗi sản phẩm là tinh hoa của đẳng cấp.','samsung.png','Samsung'),(4,'2023-12-15 12:59:24.98',NULL,1,0,0,NULL,'xiaomi.png','Xiaomi - Sức mạnh đổi mới, giá trị vô song. Sản phẩm thông minh, hiệu suất xuất sắc, và thiết kế tinh tế. Đồng hành cùng bạn trong mọi khoảnh khắc.','xiaomi.png','Xiaomi'),(5,'2023-12-15 13:00:57.423',NULL,1,0,0,NULL,'oppo.jpg','OPPO - Kết nối tận cùng, đẳng cấp đến từng chi tiết. Sự hoàn hảo gặp gỡ sáng tạo, mang đến trải nghiệm tuyệt vời nhất. Mỗi sản phẩm là kiệt tác của công nghệ và phong cách.','oppo.jpg','Oppo'),(6,'2023-12-15 13:04:17.535',NULL,1,0,0,NULL,'vivo.png','Vivo - Đẳng cấp thăng trầm, kiệt tác công nghệ. Mỗi chiếc điện thoại là một tuyệt phẩm kết hợp giữa sự hiện đại và sáng tạo. Sức mạnh của ảo diệu đem lại trải nghiệm vô song.','vivo.png','Vivo'),(7,'2023-12-15 13:06:02.373',NULL,1,0,0,NULL,'googlepixel.jpg','Google - Nâng cao cuộc sống, mở rộng tri thức. Tìm kiếm không giới hạn, sáng tạo không ngừng. Mỗi dịch vụ là cầu nối đến thế giới thông tin và tiện ích. Tạo ra ngày mai, mỗi ngày.','googlepixel.jpg','Google');
/*!40000 ALTER TABLE `manufactures` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-13 13:22:23
