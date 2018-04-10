-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: winevaultdbtest150
-- ------------------------------------------------------
-- Server version	5.7.21-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `winevaultdbtest150`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `winevaultdbtest150` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `winevaultdbtest150`;

--
-- Table structure for table `wines`
--

DROP TABLE IF EXISTS `wines`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wines` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `variety` varchar(100) NOT NULL,
  `country` varchar(50) DEFAULT NULL,
  `avgrating` double(5,2) DEFAULT NULL,
  `price_low` double(6,2) DEFAULT NULL,
  `price_high` double(6,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=151 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wines`
--

LOCK TABLES `wines` WRITE;
/*!40000 ALTER TABLE `wines` DISABLE KEYS */;
INSERT INTO `wines` VALUES (1,'White Blend','Italy',87.80,10.00,62.00),(2,'Portuguese Red','Portugal',90.62,10.00,150.00),(3,'Pinot Gris','US',88.00,13.00,112.00),(4,'Riesling','US',89.75,9.00,775.00),(5,'Pinot Noir','US',89.80,12.00,350.00),(6,'Tempranillo-Merlot','Spain',87.00,15.00,15.00),(7,'Frappato','Italy',87.00,16.00,16.00),(8,'Gewürztraminer','France',87.33,12.00,54.00),(9,'Cabernet Sauvignon','US',88.80,10.00,200.00),(10,'Nerello Mascalese','Italy',88.25,28.00,41.00),(11,'Chardonnay','US',88.74,7.00,630.00),(12,'Malbec','Argentina',88.54,11.00,215.00),(13,'Tempranillo Blend','Spain',87.17,12.00,53.00),(14,'Meritage','US',86.00,11.00,55.00),(15,'Red Blend','Italy',88.22,11.00,100.00),(16,'Merlot','South Africa',87.27,9.00,95.00),(17,'Nero d\'Avola','Italy',86.44,10.00,45.00),(18,'Chenin Blanc','France',88.00,12.00,30.00),(19,'Gamay','France',87.08,9.00,23.00),(20,'Sauvignon Blanc','South Africa',87.57,9.00,55.00),(21,'Viognier-Chardonnay','Chile',86.00,15.00,15.00),(22,'Primitivo','Italy',85.50,11.00,48.00),(23,'Catarratto','Italy',86.00,17.00,17.00),(24,'Inzolia','Italy',86.00,13.00,13.00),(25,'Petit Verdot','US',86.00,22.00,32.00),(26,'Monica','Italy',85.00,14.00,14.00),(27,'Bordeaux-style White Blend','France',86.50,11.00,25.00),(28,'Grillo','Italy',87.67,13.00,22.00),(29,'Sangiovese','Italy',88.29,16.00,150.00),(30,'Cabernet Franc','US',89.13,20.00,120.00),(31,'Champagne Blend','France',89.26,15.00,101.00),(32,'Bordeaux-style Red Blend','US',87.96,9.00,125.00),(33,'Aglianico','Italy',86.50,30.00,32.00),(34,'Petite Sirah','US',89.17,27.00,65.00),(35,'Touriga Nacional','Portugal',86.00,NULL,NULL),(36,'Carmenère','Chile',86.67,9.00,15.00),(37,'Albariño','Spain',87.60,15.00,25.00),(38,'Petit Manseng','France',86.00,11.00,11.00),(39,'Rosé','Portugal',88.32,9.00,65.00),(40,'Zinfandel','US',88.65,14.00,56.00),(41,'Vernaccia','Italy',86.50,18.00,29.00),(42,'Rosato','Italy',86.25,10.00,19.00),(43,'Grüner Veltliner','Austria',88.07,10.00,24.00),(44,'Viognier','US',87.33,14.00,60.00),(45,'Vermentino','Italy',87.00,18.00,18.00),(46,'Grenache Blanc','US',88.75,20.00,27.00),(47,'Syrah','US',89.29,11.00,120.00),(48,'Nebbiolo','Italy',88.85,18.00,180.00),(49,'Shiraz-Cabernet Sauvignon','Australia',92.00,40.00,40.00),(50,'Pinot Blanc','Germany',89.17,10.00,29.00),(51,'Alsace white blend','France',91.00,10.00,45.00),(52,'Barbera','Italy',90.00,25.00,33.00),(53,'Rhône-style Red Blend','France',89.50,13.00,50.00),(54,'Portuguese White','Portugal',87.44,8.00,60.00),(55,'Graciano','Spain',91.00,50.00,50.00),(56,'Tannat-Cabernet','France',91.00,25.00,25.00),(57,'Sauvignon','Italy',89.00,13.00,52.00),(58,'Sangiovese Grosso','Italy',88.00,90.00,90.00),(59,'Torrontés','Argentina',86.00,12.00,17.00),(60,'Prugnolo Gentile','Italy',87.00,35.00,35.00),(61,'G-S-M','US',89.00,30.00,40.00),(62,'Verdejo','Spain',88.00,14.00,40.00),(63,'Fumé Blanc','US',90.00,20.00,20.00),(64,'Furmint','Hungary',93.00,36.00,320.00),(65,'Pinot Bianco','Italy',89.25,21.00,40.00),(66,'Bonarda','Argentina',87.00,10.00,30.00),(67,'Shiraz','Australia',90.73,12.00,125.00),(68,'Montepulciano','Italy',85.00,10.00,10.00),(69,'Moscato','Austria',86.00,18.00,18.00),(70,'Grenache','US',89.14,25.00,42.00),(71,'Ugni Blanc-Colombard','France',85.00,9.00,9.00),(72,'Syrah-Viognier','South Africa',89.00,20.00,20.00),(73,'Blaufränkisch','Austria',88.00,16.00,17.00),(74,'Friulano','Italy',87.00,25.00,25.00),(75,'Assyrtico','Greece',89.00,15.00,15.00),(76,'Carignan-Grenache','Spain',92.00,40.00,40.00),(77,'Sagrantino','Italy',92.00,NULL,NULL),(78,'Savagnin','France',92.00,45.00,45.00),(79,'Cabernet Sauvignon-Syrah','US',87.00,35.00,35.00),(80,'Prosecco','Italy',85.63,12.00,24.00),(81,'Vignoles','US',86.00,11.00,11.00),(82,'Sparkling Blend','US',88.94,10.00,65.00),(83,'Muscat','Australia',98.50,100.00,350.00),(84,'Muscadelle','Australia',98.00,350.00,350.00),(85,'Shiraz-Viognier','Australia',96.00,125.00,225.00),(86,'Garganega','Italy',87.25,11.00,23.00),(87,'Pinot Grigio','Australia',86.50,9.00,18.00),(88,'Tempranillo','Spain',88.38,7.00,88.00),(89,'Zierfandler','Austria',89.00,NULL,NULL),(90,'Cortese','Italy',88.00,15.00,19.00),(91,'Mencía','Spain',88.67,15.00,42.00),(92,'Zweigelt','Austria',87.67,12.00,24.00),(93,'Melon','France',88.00,14.00,14.00),(94,'Rhône-style White Blend','France',88.00,19.00,19.00),(95,'Vidal','Canada',92.00,30.00,30.00),(96,'Cannonau','Italy',87.00,15.00,18.00),(97,'Verdelho','US',87.00,15.00,15.00),(98,'Marsanne','US',91.00,35.00,35.00),(99,'Scheurebe','Germany',91.00,30.00,30.00),(100,'Kerner','Italy',93.00,30.00,30.00),(101,'Syrah-Grenache','US',93.00,48.00,48.00),(102,'Dolcetto','Italy',89.50,15.00,35.00),(103,'Vilana','Greece',89.00,13.00,13.00),(104,'Glera','Italy',87.00,20.00,25.00),(105,'Viura','Spain',89.50,10.00,28.00),(106,'Garnacha Tintorera','Spain',90.00,15.00,15.00),(107,'Pinot Nero','Italy',90.00,45.00,45.00),(108,'Roter Veltliner','Austria',91.00,NULL,NULL),(109,'Pinotage','South Africa',91.00,40.00,40.00),(110,'Sémillon','Argentina',85.00,25.00,25.00),(111,'Pinot Noir-Gamay','France',85.00,10.00,10.00),(112,'Antão Vaz','Portugal',85.00,12.00,12.00),(113,'Cabernet Sauvignon-Carmenère','Chile',87.00,19.00,19.00),(114,'Verdejo-Viura','Spain',87.00,10.00,10.00),(115,'Verduzzo','Italy',92.00,18.00,18.00),(116,'Verdicchio','Italy',91.50,32.00,57.00),(117,'Silvaner','Germany',89.00,12.00,16.00),(118,'Colombard','France',86.00,9.00,9.00),(119,'Carricante','Italy',89.00,24.00,28.00),(120,'Sylvaner','France',90.00,16.00,24.00),(121,'Fiano','Italy',90.00,25.00,25.00),(122,'Früburgunder','Germany',90.00,40.00,40.00),(123,'Sousão','Portugal',90.00,40.00,40.00),(124,'Roussanne','US',87.00,18.00,22.00),(125,'Avesso','Portugal',87.00,30.00,30.00),(126,'Cinsault','France',87.00,23.00,23.00),(127,'Chinuri',NULL,87.00,30.00,30.00),(128,'Tinta Miúda','Portugal',87.00,14.00,14.00),(129,'Muscat Blanc à Petits Grains','US',87.00,20.00,20.00),(130,'Portuguese Sparkling','Portugal',87.00,12.00,12.00),(131,'Monastrell','Spain',87.00,15.00,15.00),(132,'Xarel-lo','Spain',87.00,16.00,16.00),(133,'Greco','Italy',87.00,30.00,30.00),(134,'Trebbiano','Italy',85.00,17.00,17.00),(135,'Corvina, Rondinella, Molinara','Italy',85.00,41.00,41.00),(136,'Port','Portugal',92.50,50.00,78.00),(137,'Chenin Blanc-Chardonnay','France',88.00,12.00,12.00),(138,'Insolia','Italy',88.00,27.00,27.00),(139,'Merlot-Malbec','New Zealand',87.00,20.00,20.00),(140,'Ribolla Gialla','Italy',85.00,19.00,19.00),(141,'Cabernet Sauvignon-Merlot','South Africa',85.00,20.00,20.00),(142,'Duras','France',90.00,NULL,NULL),(143,'Weissburgunder','Austria',87.00,15.00,15.00),(144,'Roditis','Greece',87.00,15.00,15.00),(145,'Traminer','Austria',87.00,21.00,21.00),(146,'Papaskarasi','Turkey',88.00,20.00,20.00),(147,'Tannat-Syrah','France',88.00,10.00,10.00),(148,'Marsanne-Roussanne','Australia',87.00,25.00,25.00),(149,'Charbono','US',93.00,38.00,38.00),(150,'Merlot-Argaman','Israel',85.00,12.00,12.00);
/*!40000 ALTER TABLE `wines` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `winevaultdbtest10`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `winevaultdbtest10` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `winevaultdbtest10`;

--
-- Table structure for table `wines`
--

DROP TABLE IF EXISTS `wines`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wines` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `variety` varchar(100) NOT NULL,
  `country` varchar(50) DEFAULT NULL,
  `avgrating` double(5,2) DEFAULT NULL,
  `price_low` double(6,2) DEFAULT NULL,
  `price_high` double(6,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wines`
--

LOCK TABLES `wines` WRITE;
/*!40000 ALTER TABLE `wines` DISABLE KEYS */;
INSERT INTO `wines` VALUES (1,'White Blend','Italy',85.00,NULL,NULL),(2,'Portuguese Red','Portugal',87.50,15.00,15.00),(3,'Pinot Gris','France',87.50,14.00,27.00),(4,'Riesling','US',88.02,13.00,13.00),(5,'Pinot Noir','US',88.00,65.00,65.00),(6,'Tempranillo-Merlot','Spain',84.00,15.00,15.00),(7,'Frappato','Italy',90.00,16.00,16.00),(8,'Gewürztraminer','France',91.00,12.00,30.00),(9,'Cabernet Sauvignon','US',87.00,19.00,34.00),(10,'Nerello Mascalese','Italy',87.00,NULL,NULL);
/*!40000 ALTER TABLE `wines` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-09 22:32:28
