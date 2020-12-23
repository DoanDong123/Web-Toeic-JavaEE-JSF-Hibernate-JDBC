-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: toeicdb
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Ngữ pháp trắc nghiệm'),(2,'Phần điền khuyết'),(3,'Nghe phần Photo'),(4,'Nghe Question Response'),(6,'Tesst 1'),(13,'test 2');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `choice`
--

DROP TABLE IF EXISTS `choice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `choice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `is_correct` tinyint(1) NOT NULL DEFAULT '0',
  `question_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_choice_question_idx` (`question_id`),
  CONSTRAINT `fk_choice_question` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `choice`
--

LOCK TABLES `choice` WRITE;
/*!40000 ALTER TABLE `choice` DISABLE KEYS */;
INSERT INTO `choice` VALUES (1,'an',0,1),(2,'one',0,1),(3,'the',0,1),(4,'a',1,1),(5,'the',0,2),(6,'all',1,2),(7,'every',0,2),(8,'not',0,2),(9,'your',0,3),(10,'yours',1,3),(11,'the yours',0,3),(12,'your friend',0,4),(13,'a your friend',0,4),(14,'a friend of yours',1,4),(15,'these',1,5),(16,'those',0,5),(17,'this',0,5),(18,'that',0,5),(19,'What',1,6),(20,'How',0,6),(21,'Which',0,6),(22,'an other',0,7),(23,'other',0,7),(24,'another',1,7),(35,'enough of chairs',0,8),(36,'enough chairs',1,8),(37,'chairs enough',0,8),(38,'very too',0,9),(39,'too much',0,9),(40,'too',1,9),(41,'The woman is wearing glasses.',0,62),(42,'There is a note on the keyboard.',0,62),(43,'The woman is facing the monitor.',1,62),(44,'There is a lamp above the computer.',0,62),(45,'The people are wearing coats.',0,63),(46,'The boat is in the water.',0,63),(47,'The life-jackets are on the boat.',0,63),(48,'The drum is in front of the chair.',1,63),(49,'The man is on a pathway.',1,64),(50,'The mountains are covered with snow.',0,64),(51,'The man is carrying a cap.',0,64),(52,'The sandals are in the water.',0,64),(53,'A) There were 70 people there.',0,65),(54,'B) I don\'t think she is coming.',0,65),(55,'C) At least 150 people.',1,65),(56,'A) I had a hamburger and fries.',0,66),(57,'B) He\'s probably too busy today.',1,66),(58,'C) I didn\'t take the bus.',0,66),(59,'A) I\'ll type it after lunch.',0,67),(60,'B) No, I sent it by email.',1,67),(61,'C) I collected some important facts.',0,67),(62,'A) No more than an hour.',1,68),(63,'B) I hate long line-ups.',0,68),(64,'C) I\'ve gained twenty pounds.',0,68);
/*!40000 ALTER TABLE `choice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exercise`
--

DROP TABLE IF EXISTS `exercise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exercise` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET ucs2 COLLATE ucs2_general_ci NOT NULL,
  `category_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_exercise_category_idx` (`category_id`),
  CONSTRAINT `fk_exercise_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exercise`
--

LOCK TABLES `exercise` WRITE;
/*!40000 ALTER TABLE `exercise` DISABLE KEYS */;
INSERT INTO `exercise` VALUES (1,'NPTN Dễ',1),(2,'NPTN Trung Bình',1),(3,'NPTN  Khó',1),(4,'Điền Khuyết Dễ',2),(5,'Điền Khuyết Trung Bình',2),(6,'Điền Khuyết Khó',2),(7,'Nghe Photo Dễ',3),(8,'Nghe Photo Trung Bình',3),(9,'Nghe Photo Khó ',3),(10,'Nghe Q and R Dễ',4),(11,'Nghe Q and R Trung Bình',4),(12,'Nghe Q and R Khó',4),(14,'test 23',6);
/*!40000 ALTER TABLE `exercise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `audio` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `exercise_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_question_exercise_idx` (`exercise_id`) /*!80000 INVISIBLE */,
  CONSTRAINT `fk_question_exercise` FOREIGN KEY (`exercise_id`) REFERENCES `exercise` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (1,'She\'s ______ university teacher.','',NULL,1),(2,'I like ________ small animals.',NULL,NULL,1),(3,'Is this coat _______?',NULL,NULL,1),(4,'Is Diana ____________ ?',NULL,NULL,2),(5,'Who are ________ people over there?',NULL,NULL,2),(6,'________ is your phone number?',NULL,NULL,2),(7,'Could I have _______ drink?',NULL,NULL,3),(8,'There aren\'t _____________ for everybody.',NULL,NULL,3),(9,'They\'re ________ young to get married.',NULL,NULL,3),(62,'Question 1','upload/nghephoto-de-1.jpg','upload/nghephoto-de-1.mp3',7),(63,'Question 2','upload/nghephoto-de-2.jpg','upload/nghephoto-de-2.mp3',8),(64,'Question 3','upload/nghephoto-de-3.jpg','upload/nghephoto-de-3.mp3',9),(65,'How many people are coming to the conference?',NULL,'upload/nghe-q-r-de-1.mp3',10),(66,'Do you think the boss will take us out for lunch today?',NULL,'upload/nghe-q-r-de-2.mp3',10),(67,'Did you fax the letter to the client?',NULL,'upload/nghe-q-r-de-3.mp3',11),(68,'Did you have to wait very long?',NULL,'upload/nghe-q-r-de-4.mp3',12);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scoreboard`
--

DROP TABLE IF EXISTS `scoreboard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `scoreboard` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `exercise_id` int NOT NULL,
  `score` decimal(10,0) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_score_user_idx` (`user_id`) /*!80000 INVISIBLE */,
  KEY `fk_score_exercise_idx` (`exercise_id`) /*!80000 INVISIBLE */,
  CONSTRAINT `fk_score_exercise` FOREIGN KEY (`exercise_id`) REFERENCES `exercise` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_score_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scoreboard`
--

LOCK TABLES `scoreboard` WRITE;
/*!40000 ALTER TABLE `scoreboard` DISABLE KEYS */;
/*!40000 ALTER TABLE `scoreboard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `first_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `last_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `active` tinyint(1) DEFAULT '1',
  `user_role` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'USER',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (5,'minh','202cb962ac59075b964b07152d234b70','minh','nhat','minh@gmail.com',1,'USER'),(8,'admin','c4ca4238a0b923820dcc509a6f75849b','','','',1,'ADMIN'),(11,'dong','202cb962ac59075b964b07152d234b70','','dong','',1,'USER');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-23 21:02:23
