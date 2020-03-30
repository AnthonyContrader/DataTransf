-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sampledb
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
-- Dumping data for table `changes`
--

LOCK TABLES `changes` WRITE;
/*!40000 ALTER TABLE `changes` DISABLE KEYS */;
INSERT INTO `changes` VALUES (1,'','{note=note, heading=heading, from=from, to=to, body=body}',1,NULL),(2,'','{breakfast_menu=breakfast_menu, price=price, name=name, description=description, calories=calories, food=food}',1,NULL),(3,'','{breakfast_menu=breakfast_menu, price=price, name=name, description=description, calories=calories, food=food}',1,NULL),(4,'','{breakfast_menu=breakfast_menu, price=price, name=name, description=description, calories=calories, food=food}',1,NULL),(5,'','{}',1,NULL),(6,'','{}',1,NULL),(7,'','{prova=prova}',1,NULL),(8,'','{prova=prova}',1,NULL),(9,'','{prova=prova}',1,NULL),(10,'','{prova=prova}',1,NULL),(11,'','{prova=prova}',1,NULL),(12,'','{prova=prova}',1,NULL),(13,'','{prova=prova}',1,NULL),(14,'','{prova=prova}',1,NULL),(15,'','{prova=prova}',1,NULL),(16,'','{prova=prova}',1,NULL),(17,'','{prova=prova}',1,NULL),(18,'','{prova=prova}',1,NULL),(19,'','{prova=prova}',2,NULL),(20,'','[breakfast_menu=breakfast_menu, food=food, name=name, price=price, description=description, calories=calories]',1,NULL);
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

-- Dump completed on 2020-03-30 12:00:56
