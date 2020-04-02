-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sampledb2
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `changes`
--

DROP TABLE IF EXISTS `changes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `changes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `changes` varchar(500) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `removed` varchar(500) DEFAULT NULL,
  `user` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `changes`
--

LOCK TABLES `changes` WRITE;
/*!40000 ALTER TABLE `changes` DISABLE KEYS */;
INSERT INTO `changes` VALUES (1,'[test=test, sourcrt=sourcrt]','','[]',1),(2,'[breakfast_menu=breakfast_menu, food=food, name=name, price=price, description=description, calories=calories]','',NULL,1),(3,'[breakfast_menu=breakfast_menu, food=food, name=name, price=price, description=description, calories=calories]','',NULL,1),(4,'[breakfast_menu=breakfast_menu, food=food, name=name, price=price, description=description, calories=calories]',NULL,NULL,1),(5,'[breakfast_menu=breakfast_menu, food=food, name=name, price=price, description=description, calories=calories]','test','[name, calories]',1),(6,'[test=test, sourcrt=sourcrt]',NULL,NULL,1),(7,'[test=test, sourcrt=sourcrt]',NULL,NULL,1),(8,'[test=test, sourcrt=sourcrt]',NULL,NULL,1),(9,'[test=test, sourcrt=sourcrt]',NULL,NULL,1),(10,'[test=test, sourcrt=sourcrt]',NULL,NULL,1),(11,'[test=test, sourcrt=sourcrt]',NULL,NULL,1),(12,'[breakfast_menu=breakfast_menu, food=food, name=name, price=price, description=description, calories=calories]',NULL,NULL,1),(13,'[test=test, sourcrt=sourcrt]',NULL,NULL,1),(14,'[test=test, sourcrt=sourcrt]',NULL,NULL,1),(15,'[test=test, sourcrt=sourcrt]',NULL,NULL,1),(16,'[breakfast_menu=breakfast_menu, food=food, name=name, price=price, description=description, calories=calories]',NULL,'[name]',1);
/*!40000 ALTER TABLE `changes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-02 18:06:49
