CREATE DATABASE  IF NOT EXISTS `test_security2` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `test_security2`;
-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: test_security2
-- ------------------------------------------------------
-- Server version	8.0.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8sewwnpamngi6b1dwaa88askk` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN'),(2,'USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `birthday` datetime(6) DEFAULT NULL,
  `cart_id` int DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `gender` int DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_code` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'2025-04-17 20:07:17.000000',1,'admin@gmail.com','Nguyen Van Admin',NULL,'880e0d76','U001','admin');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1),(1,2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'test_security2'
--

--
-- Dumping routines for database 'test_security2'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-24  0:03:29
CREATE DATABASE  IF NOT EXISTS `insuranceclaim` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `insuranceclaim`;
-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: insuranceclaim
-- ------------------------------------------------------
-- Server version	8.0.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `claim`
--

DROP TABLE IF EXISTS `claim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `claim` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `claim_date` date DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status_id` bigint DEFAULT NULL,
  `customer_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfkypqix42u6j9uj5a1kq2428n` (`status_id`),
  KEY `FKlmon26is6b9p2wipdl88yn68y` (`customer_id`),
  KEY `FK6k25jaynajdvvcmst08cphrwl` (`product_id`),
  CONSTRAINT `FK6k25jaynajdvvcmst08cphrwl` FOREIGN KEY (`product_id`) REFERENCES `insurance_product` (`id`),
  CONSTRAINT `FKfkypqix42u6j9uj5a1kq2428n` FOREIGN KEY (`status_id`) REFERENCES `claim_status` (`id`),
  CONSTRAINT `FKlmon26is6b9p2wipdl88yn68y` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `claim`
--

LOCK TABLES `claim` WRITE;
/*!40000 ALTER TABLE `claim` DISABLE KEYS */;
INSERT INTO `claim` VALUES (1,'tran.user1','2024-04-28 10:09:04.000000',_binary '\0','le.user2','2024-05-03 10:09:04.000000',6290.2,'2024-04-28',NULL,'Yêu cầu bồi thường chi phí y tế',3,2,3),(2,'tran.user1','2023-08-12 10:09:04.000000',_binary '\0','le.user2','2023-08-22 10:09:04.000000',8217.87,'2023-08-12',NULL,'Yêu cầu bồi thường tai nạn xe',1,1,3),(3,'tran.user1','2024-07-04 10:09:04.000000',_binary '\0','le.user2','2024-07-14 10:09:04.000000',4063.26,'2024-07-04',NULL,'Yêu cầu bồi thường bảo hiểm du lịch',3,3,3),(4,'nguyen.admin','2024-03-30 10:09:04.000000',_binary '\0','tran.user1','2024-04-09 10:09:04.000000',6963.71,'2024-03-30',NULL,'Yêu cầu bồi thường bảo hiểm du lịch',2,3,4),(5,'nguyen.admin','2024-03-23 10:09:04.000000',_binary '\0','nguyen.admin','2024-04-02 10:09:04.000000',1057.15,'2024-03-23',NULL,'Yêu cầu bồi thường bảo hiểm du lịch',3,1,4);
/*!40000 ALTER TABLE `claim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `claim_document`
--

DROP TABLE IF EXISTS `claim_document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `claim_document` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `document_name` varchar(255) DEFAULT NULL,
  `document_type` varchar(255) DEFAULT NULL,
  `file_path` varchar(255) DEFAULT NULL,
  `upload_date` date DEFAULT NULL,
  `claim_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK482n7wmh1ry39ewmqmailkpb8` (`claim_id`),
  CONSTRAINT `FK482n7wmh1ry39ewmqmailkpb8` FOREIGN KEY (`claim_id`) REFERENCES `claim` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `claim_document`
--

LOCK TABLES `claim_document` WRITE;
/*!40000 ALTER TABLE `claim_document` DISABLE KEYS */;
INSERT INTO `claim_document` VALUES (1,'nguyen.admin','2025-04-14 18:51:42.000000',_binary '\0','nguyen.admin','2025-04-14 18:51:42.000000','Giấy tờ liên quan bồi thường chi phí y tế','PDF','/path/to/medical_expense.pdf','2024-05-01',NULL),(2,'nguyen.admin','2025-04-14 18:51:42.000000',_binary '\0','nguyen.admin','2025-04-14 18:51:42.000000','Giấy tờ liên quan bồi thường tai nạn xe','Image','/path/to/car_accident.png','2023-08-15',NULL),(3,'nguyen.admin','2025-04-14 18:51:42.000000',_binary '\0','nguyen.admin','2025-04-14 18:51:42.000000','Giấy tờ liên quan bồi thường bảo hiểm du lịch','Image','/path/to/travel_insurance.png','2024-07-05',NULL);
/*!40000 ALTER TABLE `claim_document` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `claim_status`
--

DROP TABLE IF EXISTS `claim_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `claim_status` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `claim_status`
--

LOCK TABLES `claim_status` WRITE;
/*!40000 ALTER TABLE `claim_status` DISABLE KEYS */;
INSERT INTO `claim_status` VALUES (1,'nguyen.admin','2025-04-14 18:53:06.000000',_binary '\0','nguyen.admin','2025-04-14 18:53:06.000000','Pending',NULL),(2,'nguyen.admin','2025-04-14 18:53:06.000000',_binary '\0','nguyen.admin','2025-04-14 18:53:06.000000','Approved',NULL),(3,'nguyen.admin','2025-04-14 18:53:06.000000',_binary '\0','nguyen.admin','2025-04-14 18:53:06.000000','Rejected',NULL);
/*!40000 ALTER TABLE `claim_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `bank_name` varchar(255) DEFAULT NULL,
  `bank_number` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'nguyen.admin','2025-04-14 18:52:05.000000',_binary '\0','nguyen.admin','2025-04-14 18:52:05.000000','123 Đường ABC, Quận 1, TP. HCM','Ngân hàng A','1234567890','john.doe@example.com','John Doe','0123456789'),(2,'nguyen.admin','2025-04-14 18:52:05.000000',_binary '\0','nguyen.admin','2025-04-14 18:52:05.000000','456 Đường XYZ, Quận 2, TP. HCM','Ngân hàng B','0987654321','jane.smith@example.com','Jane Smith','0987654321'),(3,'nguyen.admin','2025-04-14 18:52:05.000000',_binary '\0','nguyen.admin','2025-04-14 18:52:05.000000','789 Đường DEF, Quận 3, TP. HCM','Ngân hàng C','5555555555','bob.johnson@example.com','Bob Johnson','0987654321');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `insurance_product`
--

DROP TABLE IF EXISTS `insurance_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `insurance_product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `coverage` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `insurance_product`
--

LOCK TABLES `insurance_product` WRITE;
/*!40000 ALTER TABLE `insurance_product` DISABLE KEYS */;
INSERT INTO `insurance_product` VALUES (1,'nguyen.admin','2025-04-14 18:51:56.000000',_binary '\0','nguyen.admin','2025-04-14 18:51:56.000000','Bảo hiểm toàn diện cho xe hơi','Bảo hiểm xe hơi','Bảo hiểm xe hơi'),(2,'nguyen.admin','2025-04-14 18:51:56.000000',_binary '\0','nguyen.admin','2025-04-14 18:51:56.000000','Bảo hiểm sức khỏe toàn diện','Bảo hiểm sức khỏe','Bảo hiểm sức khỏe'),(3,'nguyen.admin','2025-04-14 18:51:56.000000',_binary '\0','nguyen.admin','2025-04-14 18:51:56.000000','Bảo hiểm nhà toàn diện','Bảo hiểm nhà','Bảo hiểm nhà'),(4,'nguyen.admin','2025-04-14 18:51:56.000000',_binary '\0','nguyen.admin','2025-04-14 18:51:56.000000','Bảo hiểm du lịch toàn diện','Bảo hiểm du lịch','Bảo hiểm du lịch');
/*!40000 ALTER TABLE `insurance_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'admin','2025-04-14 18:56:00.000000',_binary '\0','admin','2025-04-14 18:56:12.000000','ADMIN','admin'),(2,'admin','2025-04-14 18:56:02.000000',_binary '\0','admin','2025-04-14 18:56:14.000000','USER','user');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`),
  CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,1),(3,1),(2,2);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `mime_type` varchar(255) DEFAULT NULL,
  `path_avatar` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','2025-04-14 19:03:43.000000',_binary '\0','admin','2025-04-14 19:03:32.000000','Ha Noi','U001','admin@gmail.com','Nguyen','Admin','$2a$10$XaDucyGIeUNE3CXL1TmgE.dzIkaKN.G3K1Ak6sw193bl4qEngRHl6','0925334772','admin','data:image/jpeg;base64,','D:\\T3h\\LJava2411\\github\\LJava2411\\LJava2411\\spring-boot\\file-upload\\image\\avatar_admin2.jpg'),(2,'admin','2025-04-14 19:03:44.000000',_binary '\0','admin','2025-04-14 19:03:34.000000','Thanh Hoa','U002','user@gmail.com','Le','User','$2a$10$rPqL/r/FMRL3TGWEdDwVnuWgDSPv1OIlCnDpeiiK09yVYOSIJwasS','0925334772','user','data:image/jpeg;base64,','D:\\T3h\\LJava2411\\github\\LJava2411\\LJava2411\\spring-boot\\file-upload\\image\\avatar_admin2.jpg'),(3,NULL,'2025-04-14 19:34:54.266064',_binary '\0',NULL,'2025-04-14 19:34:54.182213','Song Phương Hoài Đức Hà Nội','C4c77cdec-4631-4e20-b6b3-7663cae8202e','nguyenkien2521998@gmail.com','kien','nguyen',NULL,'0925334772','admin2','data:image/png;base64,','D:/T3h/LJava2411/github/LJava2411/LJava2411/spring-boot/file-upload/image/avatar_admin2.png');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'insuranceclaim'
--

--
-- Dumping routines for database 'insuranceclaim'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-24  0:03:30
