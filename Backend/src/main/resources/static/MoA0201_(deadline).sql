-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: moa
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `id` varchar(30) NOT NULL,
  `pw` varchar(20) NOT NULL,
  `name` varchar(10) NOT NULL,
  `age` int NOT NULL,
  `major` varchar(10) NOT NULL,
  `status` int NOT NULL,
  `phone` varchar(20) NOT NULL,
  `favorite_1` varchar(20) NOT NULL,
  `favorite_2` varchar(20) NOT NULL,
  `favorite_3` varchar(20) NOT NULL,
  `number_of_project` int DEFAULT '0',
  `point` int DEFAULT '0',
  `introduce` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES ('dnjswns@naver.com','dnjswns1','won',26,'design',2,'010-2853-1234','web','aric','mohavi',0,0,'ㅎㅇㅎㅇㅎㅇ'),('dnjswnsthd@naver.com','dnjswns2','Song',26,'Computer',0,'010-2853-3195','Spring','Vue','DB',0,0,'ㅎㅇㅎㅇ'),('song@naver.com','dnjswns1','Song',26,'Computer',1,'010-2853-3195','Spring','Vue','DB',0,0,'ㅎㅇㅎㅇ');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_project`
--

DROP TABLE IF EXISTS `member_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_project` (
  `project_num` int NOT NULL,
  `id` varchar(30) NOT NULL,
  KEY `member_projecttomember_idx` (`id`),
  KEY `member_projecttoproject_idx` (`project_num`),
  CONSTRAINT `member_projecttomember` FOREIGN KEY (`id`) REFERENCES `member` (`id`),
  CONSTRAINT `member_projecttoproject` FOREIGN KEY (`project_num`) REFERENCES `project` (`project_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_project`
--

LOCK TABLES `member_project` WRITE;
/*!40000 ALTER TABLE `member_project` DISABLE KEYS */;
/*!40000 ALTER TABLE `member_project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mentee`
--

DROP TABLE IF EXISTS `mentee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mentee` (
  `id` varchar(20) NOT NULL,
  `skill` varchar(128) NOT NULL,
  `communication` int unsigned NOT NULL DEFAULT '0',
  `responsibility` int unsigned NOT NULL DEFAULT '0',
  `performance` int unsigned NOT NULL DEFAULT '0',
  `positiveness` int unsigned NOT NULL DEFAULT '0',
  `leadership` int unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `membertomenteeid_idx` (`id`),
  CONSTRAINT `membertomenteeid` FOREIGN KEY (`id`) REFERENCES `member` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mentee`
--

LOCK TABLES `mentee` WRITE;
/*!40000 ALTER TABLE `mentee` DISABLE KEYS */;
INSERT INTO `mentee` VALUES ('dnjswns@naver.com','design',0,0,0,0,0),('dnjswnsthd@naver.com','design',0,0,0,0,0);
/*!40000 ALTER TABLE `mentee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mentor`
--

DROP TABLE IF EXISTS `mentor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mentor` (
  `id` varchar(20) NOT NULL,
  `career` varchar(128) NOT NULL,
  `exp` int unsigned NOT NULL DEFAULT '0',
  `morality` int unsigned NOT NULL DEFAULT '0',
  `positiveness` int unsigned NOT NULL DEFAULT '0',
  `reliability` int unsigned NOT NULL DEFAULT '0',
  `professional` int unsigned NOT NULL DEFAULT '0',
  `leadership` int unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `membertomentorid_idx` (`id`),
  CONSTRAINT `membertomentorid` FOREIGN KEY (`id`) REFERENCES `member` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mentor`
--

LOCK TABLES `mentor` WRITE;
/*!40000 ALTER TABLE `mentor` DISABLE KEYS */;
INSERT INTO `mentor` VALUES ('song@naver.com','컴퓨터',0,0,0,0,0,0);
/*!40000 ALTER TABLE `mentor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project` (
  `project_num` int NOT NULL AUTO_INCREMENT,
  `project_name` varchar(30) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `category` varchar(30) NOT NULL,
  `participants` int NOT NULL,
  `funding_cost` int NOT NULL,
  `description` varchar(128) NOT NULL,
  `mentor_chk` varchar(10) NOT NULL,
  `deadline` date DEFAULT NULL,
  PRIMARY KEY (`project_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sprint`
--

DROP TABLE IF EXISTS `sprint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sprint` (
  `sprint_num` int NOT NULL AUTO_INCREMENT,
  `sprint_name` varchar(30) NOT NULL,
  `sprint_status` int DEFAULT '0',
  `sprint_subject` varchar(20) NOT NULL,
  `sprint_description` varchar(128) NOT NULL,
  `sprint_start_date` date NOT NULL,
  `sprint_end_date` date NOT NULL,
  `project_num` int NOT NULL,
  PRIMARY KEY (`sprint_num`),
  KEY `sprinttoproject_idx` (`project_num`),
  CONSTRAINT `sprinttoproject` FOREIGN KEY (`project_num`) REFERENCES `project` (`project_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sprint`
--

LOCK TABLES `sprint` WRITE;
/*!40000 ALTER TABLE `sprint` DISABLE KEYS */;
/*!40000 ALTER TABLE `sprint` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-01 16:33:05
