-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mydb
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
-- Table structure for table `compra`
--

DROP TABLE IF EXISTS `compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compra` (
  `ID_Compra` int NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  PRIMARY KEY (`ID_Compra`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compra`
--

LOCK TABLES `compra` WRITE;
/*!40000 ALTER TABLE `compra` DISABLE KEYS */;
INSERT INTO `compra` VALUES (1,'2021-02-17'),(2,'2021-02-17'),(3,'2021-02-17'),(4,'2021-02-17'),(5,'2021-02-17'),(6,'2021-02-17'),(7,'2021-02-17'),(8,'2021-02-17'),(9,'2021-02-17'),(10,'2021-02-17'),(11,'2021-02-20'),(12,'2021-02-20'),(13,'2021-02-20'),(14,'2021-02-21'),(15,'2021-02-21'),(16,'2021-02-21'),(17,'2021-02-23');
/*!40000 ALTER TABLE `compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleado` (
  `ID_Empleado` int NOT NULL AUTO_INCREMENT,
  `ultima_sesion` date NOT NULL,
  `fecha_contratacion` date NOT NULL,
  PRIMARY KEY (`ID_Empleado`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` VALUES (1,'2021-02-23','2020-02-01'),(2,'2021-02-21','2020-02-09'),(3,'2021-02-21','2020-03-08');
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hace`
--

DROP TABLE IF EXISTS `hace`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hace` (
  `compra_ID_Compra` int NOT NULL,
  `Empleado_ID_Empleado` int NOT NULL,
  PRIMARY KEY (`compra_ID_Compra`,`Empleado_ID_Empleado`),
  KEY `fk_compra_has_Empleado_Empleado1_idx` (`Empleado_ID_Empleado`),
  KEY `fk_compra_has_Empleado_compra_idx` (`compra_ID_Compra`),
  CONSTRAINT `fk_compra_has_Empleado_compra` FOREIGN KEY (`compra_ID_Compra`) REFERENCES `compra` (`ID_Compra`),
  CONSTRAINT `fk_compra_has_Empleado_Empleado1` FOREIGN KEY (`Empleado_ID_Empleado`) REFERENCES `empleado` (`ID_Empleado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hace`
--

LOCK TABLES `hace` WRITE;
/*!40000 ALTER TABLE `hace` DISABLE KEYS */;
INSERT INTO `hace` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1),(11,1),(12,1),(13,1),(14,1),(15,1),(16,1),(17,2);
/*!40000 ALTER TABLE `hace` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `ID_Producto` int NOT NULL AUTO_INCREMENT,
  `nombre_producto` varchar(45) NOT NULL,
  `precio_venta` int NOT NULL,
  `precio_proveedor` int NOT NULL,
  `cantidad_stock` varchar(45) NOT NULL,
  PRIMARY KEY (`ID_Producto`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,'Disco Duro',100,80,'1'),(2,'USB',8,3,'13'),(3,'Monitor',150,110,'5'),(4,'Raton',25,15,'14');
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tiene`
--

DROP TABLE IF EXISTS `tiene`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tiene` (
  `producto_ID_Producto` int NOT NULL,
  `compra_ID_Compra` int NOT NULL,
  `unidades_vendidas` int NOT NULL,
  PRIMARY KEY (`producto_ID_Producto`,`compra_ID_Compra`),
  KEY `fk_producto_has_compra_compra1_idx` (`compra_ID_Compra`),
  KEY `fk_producto_has_compra_producto1_idx` (`producto_ID_Producto`),
  CONSTRAINT `fk_producto_has_compra_compra1` FOREIGN KEY (`compra_ID_Compra`) REFERENCES `compra` (`ID_Compra`),
  CONSTRAINT `fk_producto_has_compra_producto1` FOREIGN KEY (`producto_ID_Producto`) REFERENCES `producto` (`ID_Producto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tiene`
--

LOCK TABLES `tiene` WRITE;
/*!40000 ALTER TABLE `tiene` DISABLE KEYS */;
INSERT INTO `tiene` VALUES (1,1,1),(1,2,3),(1,4,1),(1,7,1),(1,10,1),(1,15,5),(1,16,2),(2,3,2),(2,9,1),(2,12,3),(2,13,2),(2,14,3),(2,17,4),(3,8,1),(4,6,1);
/*!40000 ALTER TABLE `tiene` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-23 19:25:06
