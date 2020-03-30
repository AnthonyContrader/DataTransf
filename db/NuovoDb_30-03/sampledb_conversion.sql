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
-- Dumping data for table `conversion`
--

LOCK TABLES `conversion` WRITE;
/*!40000 ALTER TABLE `conversion` DISABLE KEYS */;
INSERT INTO `conversion` VALUES (1,'<note><to>Tove</to><from>Jani</from><heading>Reminder</heading><body>Don\'tforgetmethisweekend!</body></note>','xml','json',0,0),(2,'jhgfdsa','xml','json',0,0),(3,'<note><to>Tove</to><from>Jani</from><heading>Reminder</heading><body>Don\'tforgetmethisweekend!</body></note>','xml','json',0,0),(4,'<prova></prova>','xml','json',1,18),(5,'<prova>Ciao</prova>','xml','json',2,19),(6,'<breakfast_menu><food><name>BelgianWaffles</name><price>$5.95</price><description>TwoofourfamousBelgianWaffleswithplentyofrealmaplesyrup</description><calories>650</calories></food><food><name>StrawberryBelgianWaffles</name><price>$7.95</price><description>LightBelgianwafflescoveredwithstrawberriesandwhippedcream</description><calories>900</calories></food><food><name>Berry-BerryBelgianWaffles</name><price>$8.95</price><description>Belgianwafflescoveredwithassortedfreshberriesandwhippedcream</description><calories>900</calories></food><food><name>FrenchToast</name><price>$4.50</price><description>Thickslicesmadefromourhomemadesourdoughbread</description><calories>600</calories></food><food><name>HomestyleBreakfast</name><price>$6.95</price><description>Twoeggs,baconorsausage,toast,andourever-popularhashbrowns</description><calories>950</calories></food></breakfast_menu>','xml','json',1,20);
/*!40000 ALTER TABLE `conversion` ENABLE KEYS */;
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
