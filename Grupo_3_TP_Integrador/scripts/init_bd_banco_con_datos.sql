DROP DATABASE IF EXISTS `banco`;

CREATE DATABASE  IF NOT EXISTS `banco` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `banco`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: banco
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientes` (
  `id_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) NOT NULL,
  `dni` varchar(10) NOT NULL,
  `cuil` varchar(15) NOT NULL,
  `nombre` varchar(15) NOT NULL,
  `apellido` varchar(25) NOT NULL,
  `email` varchar(40) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `genero` char(1) NOT NULL,
  `id_nacionalidad` int(11) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `direccion` varchar(30) NOT NULL,
  `id_provincia` int(11) NOT NULL,
  `id_localidad` int(11) NOT NULL,
  PRIMARY KEY (`id_cliente`),
  UNIQUE KEY `dni` (`dni`),
  UNIQUE KEY `cuil` (`cuil`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_nacionalidad` (`id_nacionalidad`),
  KEY `id_provincia` (`id_provincia`),
  KEY `id_localidad` (`id_localidad`),
  CONSTRAINT `clientes_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`),
  CONSTRAINT `clientes_ibfk_2` FOREIGN KEY (`id_nacionalidad`) REFERENCES `nacionalidades` (`id_nacionalidad`),
  CONSTRAINT `clientes_ibfk_3` FOREIGN KEY (`id_provincia`) REFERENCES `provincias` (`id_provincia`),
  CONSTRAINT `clientes_ibfk_4` FOREIGN KEY (`id_localidad`) REFERENCES `localidades` (`id_localidad`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,2,'10100300','20101003003','Homero','Simpson','homero@app.com','3211233211','V',4,'1943-07-19','Av. Siempreviva 742',8,51),(2,3,'39281721','20392817211','Bart','Simpson','bart@app.com','3211233219','V',3,'1995-09-20','Av. Siempreviva 742',11,59),(3,4,'30020024','21300200242','Marge','Simpson','marge@app.com','3211233218','M',5,'1959-10-21','Av. Siempreviva 742',7,49),(4,5,'39281723','21392817233','Lisa','Simpson','lisa@app.com','3211233215','M',1,'1987-03-21','Av. Siempreviva 742',5,39),(5,6,'72332122','21723321223','Maggie','Simpson','maggie@app.com','3211233211','M',6,'1980-02-10','Av. Siempreviva 742',4,36),(6,7,'11111111','20111111111','Duff','Man','duffman@app.com','1111111111','V',4,'1995-08-18','312312',20,86),(7,8,'11111112','20111111121','Ned','Flanders','ned@app.com','1111111112','V',2,'1974-04-19','123123',12,62),(8,9,'11111113','20111111131','Kent','Brockman','kent@app.com','3211233211','V',6,'1964-06-19','123 sadf',21,78),(9,10,'32567847','21723321225','Seymour','Skinner','seymour@app.com','1098765432','V',4,'1971-07-18','asdasdad',21,21),(10,11,'42332122','12312332123','Otto','Mann','otto@app.com','3892718361','V',1,'1967-10-19','De los perals 3625',14,14),(11,12,'25100293','21727721223','Edna','Krabappel','edna@app.com','1111111111','M',6,'1954-12-19','12312314',16,16),(12,13,'44321743','21443217433','Patty','Bouvier','patty@app.com','1231231231','M',3,'1981-04-20','manza 2832',19,87),(13,14,'39281728','21392817282','Selma','Bouvier','selma@app.com','1231233212','M',4,'1976-06-12','Calle 3jsd',17,72),(14,15,'30020028','38273647382','Willie','MacDougal','willie@app.com','1111111111','V',1,'1969-06-14','asdkj 123',7,49),(15,16,'12332121','12332122127','Ralph','Wiggum','ralph@app.com','3892718361','V',1,'1999-07-19','asdkj 123',6,43);
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuentas`
--

DROP TABLE IF EXISTS `cuentas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuentas` (
  `id_cuenta` int(11) NOT NULL AUTO_INCREMENT,
  `id_cliente` int(11) NOT NULL,
  `id_tipo_cuenta` int(11) NOT NULL,
  `fecha_creacion` date NOT NULL,
  `numero_cuenta` bigint(20) NOT NULL,
  `cbu` varchar(22) NOT NULL,
  `saldo` decimal(14,2) NOT NULL,
  `estado_cuenta` bit(1) DEFAULT b'1',
  PRIMARY KEY (`id_cuenta`),
  UNIQUE KEY `numero_cuenta` (`numero_cuenta`),
  UNIQUE KEY `cbu` (`cbu`),
  KEY `id_cliente` (`id_cliente`),
  KEY `id_tipo_cuenta` (`id_tipo_cuenta`),
  CONSTRAINT `cuentas_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`),
  CONSTRAINT `cuentas_ibfk_2` FOREIGN KEY (`id_tipo_cuenta`) REFERENCES `tipos_cuenta` (`id_tipo_cuenta`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuentas`
--

LOCK TABLES `cuentas` WRITE;
/*!40000 ALTER TABLE `cuentas` DISABLE KEYS */;
INSERT INTO `cuentas` VALUES (1,1,1,'2024-11-22',100001,'1000000000000000000001',0.00,''),(2,1,1,'2024-11-22',100002,'1000000000000000000002',240000.00,''),(3,2,1,'2024-11-22',100003,'1000000000000000000003',10000.00,''),(4,2,2,'2024-11-22',100004,'1000000000000000000004',10000.00,''),(5,3,1,'2024-11-22',100005,'1000000000000000000005',40000.00,''),(6,3,2,'2024-11-22',100006,'1000000000000000000006',332000.00,''),(7,3,1,'2024-11-22',100007,'1000000000000000000007',10000.00,''),(8,4,1,'2024-11-22',100008,'1000000000000000000008',343000.00,''),(9,5,2,'2024-11-22',100009,'1000000000000000000009',10000.00,''),(10,6,2,'2024-11-22',100010,'1000000000000000000010',10000.00,''),(11,7,1,'2024-11-22',100011,'1000000000000000000011',10000.00,''),(12,7,1,'2024-11-22',100012,'1000000000000000000012',10000.00,''),(13,7,2,'2024-11-22',100013,'1000000000000000000013',10000.00,''),(14,9,1,'2024-11-22',100014,'1000000000000000000014',10000.00,''),(15,11,1,'2024-11-22',100015,'1000000000000000000015',10000.00,''),(16,10,1,'2024-11-22',100016,'1000000000000000000016',10000.00,''),(17,11,2,'2024-11-22',100017,'1000000000000000000017',10000.00,''),(18,14,2,'2024-11-22',100018,'1000000000000000000018',310000.00,''),(19,8,1,'2024-11-22',100019,'1000000000000000000019',10000.00,''),(20,12,2,'2024-11-22',100020,'1000000000000000000020',10000.00,''),(21,15,1,'2024-11-22',100021,'1000000000000000000021',10000.00,''),(22,14,2,'2024-11-22',100022,'1000000000000000000022',10000.00,'');
/*!40000 ALTER TABLE `cuentas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuotas`
--

DROP TABLE IF EXISTS `cuotas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuotas` (
  `id_cuota` int(11) NOT NULL AUTO_INCREMENT,
  `id_prestamo` int(11) NOT NULL,
  `numero_cuota` int(11) NOT NULL,
  `monto_pagado` decimal(14,2) DEFAULT NULL,
  `fecha_pago` date DEFAULT NULL,
  `estado_pago` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id_cuota`),
  KEY `id_prestamo` (`id_prestamo`),
  CONSTRAINT `cuotas_ibfk_1` FOREIGN KEY (`id_prestamo`) REFERENCES `prestamos` (`id_prestamo`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuotas`
--

LOCK TABLES `cuotas` WRITE;
/*!40000 ALTER TABLE `cuotas` DISABLE KEYS */;
INSERT INTO `cuotas` VALUES (1,1,1,57500.00,'2024-12-22','\0'),(2,1,2,57500.00,'2025-01-22','\0'),(3,1,3,57500.00,'2025-02-22','\0'),(4,1,4,57500.00,'2025-03-22','\0'),(5,1,5,57500.00,'2025-04-22','\0'),(6,1,6,57500.00,'2025-05-22','\0'),(7,2,1,12500.00,'2024-12-22','\0'),(8,2,2,12500.00,'2025-01-22','\0'),(9,2,3,12500.00,'2025-02-22','\0'),(10,2,4,12500.00,'2025-03-22','\0'),(11,2,5,12500.00,'2025-04-22','\0'),(12,2,6,12500.00,'2025-05-22','\0'),(13,2,7,12500.00,'2025-06-22','\0'),(14,2,8,12500.00,'2025-07-22','\0'),(15,2,9,12500.00,'2025-08-22','\0'),(16,2,10,12500.00,'2025-09-22','\0'),(17,2,11,12500.00,'2025-10-22','\0'),(18,2,12,12500.00,'2025-11-22','\0'),(19,2,13,12500.00,'2025-12-22','\0'),(20,2,14,12500.00,'2026-01-22','\0'),(21,2,15,12500.00,'2026-02-22','\0'),(22,2,16,12500.00,'2026-03-22','\0'),(23,2,17,12500.00,'2026-04-22','\0'),(24,2,18,12500.00,'2026-05-22','\0'),(25,8,1,13216.67,'2024-12-22','\0'),(26,8,2,13216.67,'2025-01-22','\0'),(27,8,3,13216.67,'2025-02-22','\0'),(28,8,4,13216.67,'2025-03-22','\0'),(29,8,5,13216.67,'2025-04-22','\0'),(30,8,6,13216.67,'2025-05-22','\0'),(31,8,7,13216.67,'2025-06-22','\0'),(32,8,8,13216.67,'2025-07-22','\0'),(33,8,9,13216.67,'2025-08-22','\0'),(34,8,10,13216.67,'2025-09-22','\0'),(35,8,11,13216.67,'2025-10-22','\0'),(36,8,12,13216.67,'2025-11-22','\0'),(37,10,1,27750.00,'2024-12-22','\0'),(38,10,2,27750.00,'2025-01-22','\0'),(39,10,3,27750.00,'2025-02-22','\0'),(40,10,4,27750.00,'2025-03-22','\0'),(41,10,5,27750.00,'2025-04-22','\0'),(42,10,6,27750.00,'2025-05-22','\0'),(43,10,7,27750.00,'2025-06-22','\0'),(44,10,8,27750.00,'2025-07-22','\0'),(45,10,9,27750.00,'2025-08-22','\0'),(46,10,10,27750.00,'2025-09-22','\0'),(47,10,11,27750.00,'2025-10-22','\0'),(48,10,12,27750.00,'2025-11-22','\0'),(49,10,13,27750.00,'2025-12-22','\0'),(50,10,14,27750.00,'2026-01-22','\0'),(51,10,15,27750.00,'2026-02-22','\0'),(52,10,16,27750.00,'2026-03-22','\0'),(53,10,17,27750.00,'2026-04-22','\0'),(54,10,18,27750.00,'2026-05-22','\0'),(55,12,1,21250.00,'2024-12-22','\0'),(56,12,2,21250.00,'2025-01-22','\0'),(57,12,3,21250.00,'2025-02-22','\0'),(58,12,4,21250.00,'2025-03-22','\0'),(59,12,5,21250.00,'2025-04-22','\0'),(60,12,6,21250.00,'2025-05-22','\0'),(61,12,7,21250.00,'2025-06-22','\0'),(62,12,8,21250.00,'2025-07-22','\0'),(63,12,9,21250.00,'2025-08-22','\0'),(64,12,10,21250.00,'2025-09-22','\0'),(65,12,11,21250.00,'2025-10-22','\0'),(66,12,12,21250.00,'2025-11-22','\0'),(67,12,13,21250.00,'2025-12-22','\0'),(68,12,14,21250.00,'2026-01-22','\0'),(69,12,15,21250.00,'2026-02-22','\0'),(70,12,16,21250.00,'2026-03-22','\0'),(71,12,17,21250.00,'2026-04-22','\0'),(72,12,18,21250.00,'2026-05-22','\0'),(73,12,19,21250.00,'2026-06-22','\0'),(74,12,20,21250.00,'2026-07-22','\0'),(75,12,21,21250.00,'2026-08-22','\0'),(76,12,22,21250.00,'2026-09-22','\0'),(77,12,23,21250.00,'2026-10-22','\0'),(78,12,24,21250.00,'2026-11-22','\0');
/*!40000 ALTER TABLE `cuotas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estados_prestamo`
--

DROP TABLE IF EXISTS `estados_prestamo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estados_prestamo` (
  `id_estado_prestamo` int(11) NOT NULL,
  `estado_prestamo` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id_estado_prestamo`),
  UNIQUE KEY `estado_prestamo` (`estado_prestamo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estados_prestamo`
--

LOCK TABLES `estados_prestamo` WRITE;
/*!40000 ALTER TABLE `estados_prestamo` DISABLE KEYS */;
INSERT INTO `estados_prestamo` VALUES (2,'Autorizado'),(4,'Pagado'),(1,'Pendiente'),(3,'Rechazado');
/*!40000 ALTER TABLE `estados_prestamo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `localidades`
--

DROP TABLE IF EXISTS `localidades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `localidades` (
  `id_localidad` int(11) NOT NULL,
  `id_provincia` int(11) NOT NULL,
  `localidad` varchar(50) NOT NULL,
  PRIMARY KEY (`id_localidad`),
  UNIQUE KEY `localidad` (`localidad`),
  KEY `id_provincia` (`id_provincia`),
  CONSTRAINT `localidades_ibfk_1` FOREIGN KEY (`id_provincia`) REFERENCES `provincias` (`id_provincia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `localidades`
--

LOCK TABLES `localidades` WRITE;
/*!40000 ALTER TABLE `localidades` DISABLE KEYS */;
INSERT INTO `localidades` VALUES (1,1,'San Miguel de Tucumán'),(2,2,'La Plata'),(3,3,'Santa Fe'),(4,4,'Paraná'),(5,5,'Corrientes'),(6,6,'Posadas'),(7,7,'Resistencia'),(8,8,'Formosa'),(9,9,'San Salvador de Jujuy'),(10,10,'Salta'),(11,11,'San Fernando del Valle de Catamarca'),(12,12,'La Rioja'),(13,13,'San Juan'),(14,14,'Mendoza'),(15,15,'Neuquén'),(16,16,'Viedma'),(17,17,'Rawson'),(18,18,'Río Gallegos'),(19,19,'Ushuaia'),(20,20,'Santa Rosa'),(21,21,'Córdoba'),(22,22,'San Luis'),(23,23,'Santiago del Estero'),(24,1,'Alberdi'),(25,1,'El Mollar'),(26,1,'Ruinas De Quilmes'),(27,1,'Tafí Del Valle'),(28,2,'Junín'),(29,2,'Pehuajó'),(30,2,'Vicente López'),(31,2,'Tigre'),(32,3,'Los Nogales'),(33,3,'Chapuy'),(34,3,'Godoy'),(35,3,'La Camila'),(36,4,'Gualeguaychú'),(37,4,'Diamante'),(38,4,'Nogoyá'),(39,5,'Bella Vista'),(40,5,'Itati'),(41,5,'Goya'),(42,5,'Yapeyú'),(43,6,'El Dorado'),(44,6,'El Soberbio'),(45,6,'San Javier'),(46,6,'Oberá'),(47,6,'Puerto Iguazú'),(48,7,'Villa Angela'),(49,7,'Gancedo'),(50,7,'Las BreÃ±as'),(51,8,'Clorinda'),(52,8,'Palo Santo'),(53,9,'Tilcara'),(54,9,'Humahuaca'),(55,9,'Purmamarca'),(56,10,'San Lorenzo'),(57,10,'San Antonio De Los Cobres'),(58,10,'Los Toldos'),(59,11,'El Alto'),(60,11,'Tinogasta'),(61,12,'Anillaco'),(62,12,'Chilecito'),(63,13,'Valle Fertil'),(64,13,'Rodeo'),(65,14,'El sosneado'),(66,14,'Malargüe'),(67,15,'Piedra Del Águila'),(68,15,'Villa Pehuenia'),(69,16,'El bolsón'),(70,16,'Dina Huapi'),(71,17,'Esquel'),(72,17,'El Hoyo'),(73,18,'El chaltén'),(74,19,'Río Grande'),(75,20,'Casa De Piedra'),(76,21,'Tanti'),(77,21,'Mina Clavero'),(78,21,'Yacanto'),(79,22,'La Toma'),(80,22,'Balde'),(81,22,'Merlo'),(82,23,'Pinto'),(83,23,'Frías'),(84,23,'Villa La Punta'),(85,18,'Fitz Roy'),(86,20,'Mocachín'),(87,19,'Tolhuin');
/*!40000 ALTER TABLE `localidades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimientos`
--

DROP TABLE IF EXISTS `movimientos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movimientos` (
  `id_movimiento` int(11) NOT NULL AUTO_INCREMENT,
  `id_cuenta` int(11) NOT NULL,
  `id_tipo_movimiento` int(11) NOT NULL,
  `fecha_movimiento` date NOT NULL,
  `concepto` varchar(50) NOT NULL,
  `importe_movimiento` decimal(14,2) NOT NULL,
  PRIMARY KEY (`id_movimiento`),
  KEY `id_cuenta` (`id_cuenta`),
  KEY `id_tipo_movimiento` (`id_tipo_movimiento`),
  CONSTRAINT `movimientos_ibfk_1` FOREIGN KEY (`id_cuenta`) REFERENCES `cuentas` (`id_cuenta`),
  CONSTRAINT `movimientos_ibfk_2` FOREIGN KEY (`id_tipo_movimiento`) REFERENCES `tipos_movimiento` (`id_tipo_movimiento`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimientos`
--

LOCK TABLES `movimientos` WRITE;
/*!40000 ALTER TABLE `movimientos` DISABLE KEYS */;
INSERT INTO `movimientos` VALUES (1,1,1,'2024-11-22','Alta de cuentas',10000.00),(2,2,1,'2024-11-22','Alta de cuentas',10000.00),(3,3,1,'2024-11-22','Alta de cuentas',10000.00),(4,4,1,'2024-11-22','Alta de cuentas',10000.00),(5,5,1,'2024-11-22','Alta de cuentas',10000.00),(6,6,1,'2024-11-22','Alta de cuentas',10000.00),(7,7,1,'2024-11-22','Alta de cuentas',10000.00),(8,8,1,'2024-11-22','Alta de cuentas',10000.00),(9,9,1,'2024-11-22','Alta de cuentas',10000.00),(10,10,1,'2024-11-22','Alta de cuentas',10000.00),(11,11,1,'2024-11-22','Alta de cuentas',10000.00),(12,12,1,'2024-11-22','Alta de cuentas',10000.00),(13,13,1,'2024-11-22','Alta de cuentas',10000.00),(14,14,1,'2024-11-22','Alta de cuentas',10000.00),(15,15,1,'2024-11-22','Alta de cuentas',10000.00),(16,16,1,'2024-11-22','Alta de cuentas',10000.00),(17,17,1,'2024-11-22','Alta de cuentas',10000.00),(18,18,1,'2024-11-22','Alta de cuentas',10000.00),(19,19,1,'2024-11-22','Alta de cuentas',10000.00),(20,20,1,'2024-11-22','Alta de cuentas',10000.00),(21,21,1,'2024-11-22','Alta de cuentas',10000.00),(22,22,1,'2024-11-22','Alta de cuentas',10000.00),(23,1,2,'2024-11-22','Acreditacion de Prestamo',300000.00),(24,2,2,'2024-11-22','Acreditacion de Prestamo',150000.00),(25,6,2,'2024-11-22','Acreditacion de Prestamo',122000.00),(26,8,2,'2024-11-22','Acreditacion de Prestamo',333000.00),(27,18,2,'2024-11-22','Acreditacion de Prestamo',300000.00),(28,1,5,'2024-11-22','Débito por transferencia',-20000.00),(29,2,4,'2024-11-22','Crédito por transferencia',20000.00),(30,1,5,'2024-11-22','Débito por transferencia',-10000.00),(31,2,4,'2024-11-22','Crédito por transferencia',10000.00),(32,1,5,'2024-11-22','Débito por transferencia',-20000.00),(33,2,4,'2024-11-22','Crédito por transferencia',20000.00),(34,1,5,'2024-11-22','Débito por transferencia',-200000.00),(35,2,4,'2024-11-22','Crédito por transferencia',200000.00),(36,1,5,'2024-11-22','Débito por transferencia',-60000.00),(37,2,4,'2024-11-22','Crédito por transferencia',60000.00),(38,2,5,'2024-11-22','Débito por transferencia',-30000.00),(39,5,4,'2024-11-22','Crédito por transferencia',30000.00),(40,2,5,'2024-11-22','Débito por transferencia',-200000.00),(41,6,4,'2024-11-22','Crédito por transferencia',200000.00);
/*!40000 ALTER TABLE `movimientos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nacionalidades`
--

DROP TABLE IF EXISTS `nacionalidades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nacionalidades` (
  `id_nacionalidad` int(11) NOT NULL,
  `nacionalidad` varchar(30) NOT NULL,
  PRIMARY KEY (`id_nacionalidad`),
  UNIQUE KEY `nacionalidad` (`nacionalidad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nacionalidades`
--

LOCK TABLES `nacionalidades` WRITE;
/*!40000 ALTER TABLE `nacionalidades` DISABLE KEYS */;
INSERT INTO `nacionalidades` VALUES (1,'Argentina'),(3,'Boliviana'),(5,'Brasilera'),(4,'Chilena'),(2,'Paraguaya'),(6,'Peruana'),(7,'Uruguaya');
/*!40000 ALTER TABLE `nacionalidades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestamos`
--

DROP TABLE IF EXISTS `prestamos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prestamos` (
  `id_prestamo` int(11) NOT NULL AUTO_INCREMENT,
  `id_cliente` int(11) NOT NULL,
  `id_cuenta` int(11) NOT NULL,
  `fecha_alta_prestamo` date NOT NULL,
  `importe_prestamo` decimal(14,2) NOT NULL,
  `meses_plazo` int(11) NOT NULL,
  `importe_cuota` decimal(14,2) NOT NULL,
  `cantidad_cuotas` int(11) NOT NULL,
  `id_estado_prestamo` int(11) NOT NULL,
  PRIMARY KEY (`id_prestamo`),
  KEY `id_cliente` (`id_cliente`),
  KEY `id_cuenta` (`id_cuenta`),
  KEY `id_estado_prestamo` (`id_estado_prestamo`),
  CONSTRAINT `prestamos_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`),
  CONSTRAINT `prestamos_ibfk_2` FOREIGN KEY (`id_cuenta`) REFERENCES `cuentas` (`id_cuenta`),
  CONSTRAINT `prestamos_ibfk_3` FOREIGN KEY (`id_estado_prestamo`) REFERENCES `estados_prestamo` (`id_estado_prestamo`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamos`
--

LOCK TABLES `prestamos` WRITE;
/*!40000 ALTER TABLE `prestamos` DISABLE KEYS */;
INSERT INTO `prestamos` VALUES (1,1,1,'2024-11-22',300000.00,6,57500.00,6,2),(2,1,2,'2024-11-22',150000.00,18,12500.00,18,2),(3,1,1,'2024-11-22',75000.00,18,6250.00,18,3),(4,2,3,'2024-11-22',55000.00,6,10541.67,6,1),(5,2,4,'2024-11-22',200000.00,18,16666.67,18,1),(6,2,4,'2024-11-22',500000.00,24,35416.67,24,1),(7,3,5,'2024-11-22',66000.00,6,12650.00,6,1),(8,3,6,'2024-11-22',122000.00,12,13216.67,12,2),(9,3,7,'2024-11-22',240000.00,24,17000.00,24,1),(10,4,8,'2024-11-22',333000.00,18,27750.00,18,2),(11,10,16,'2024-11-22',150000.00,12,16250.00,12,1),(12,14,18,'2024-11-22',300000.00,24,21250.00,24,2),(13,14,22,'2024-11-22',74023.00,12,8019.16,12,1),(14,1,2,'2024-11-22',5500000.00,24,389583.33,24,1),(15,1,1,'2024-11-22',9999999.00,24,708333.26,24,1);
/*!40000 ALTER TABLE `prestamos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provincias`
--

DROP TABLE IF EXISTS `provincias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `provincias` (
  `id_provincia` int(11) NOT NULL,
  `provincia` varchar(50) NOT NULL,
  PRIMARY KEY (`id_provincia`),
  UNIQUE KEY `provincia` (`provincia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provincias`
--

LOCK TABLES `provincias` WRITE;
/*!40000 ALTER TABLE `provincias` DISABLE KEYS */;
INSERT INTO `provincias` VALUES (2,'Buenos Aires'),(11,'Catamarca'),(7,'Chaco'),(17,'Chubut'),(21,'Córdoba'),(5,'Corrientes'),(4,'Entre Ríos'),(8,'Formosa'),(9,'Jujuy'),(20,'La Pampa'),(12,'La Rioja'),(14,'Mendoza'),(6,'Misiones'),(15,'Neuquén'),(16,'Río Negro'),(10,'Salta'),(13,'San Juan'),(22,'San Luis'),(18,'Santa Cruz'),(3,'Santa Fe'),(23,'Santiago del Estero'),(19,'Tierra del Fuego'),(1,'Tucumán');
/*!40000 ALTER TABLE `provincias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipos_cuenta`
--

DROP TABLE IF EXISTS `tipos_cuenta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipos_cuenta` (
  `id_tipo_cuenta` int(11) NOT NULL,
  `tipo_cuenta` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id_tipo_cuenta`),
  UNIQUE KEY `tipo_cuenta` (`tipo_cuenta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipos_cuenta`
--

LOCK TABLES `tipos_cuenta` WRITE;
/*!40000 ALTER TABLE `tipos_cuenta` DISABLE KEYS */;
INSERT INTO `tipos_cuenta` VALUES (1,'Caja de Ahorro'),(2,'Cuenta Corriente');
/*!40000 ALTER TABLE `tipos_cuenta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipos_movimiento`
--

DROP TABLE IF EXISTS `tipos_movimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipos_movimiento` (
  `id_tipo_movimiento` int(11) NOT NULL,
  `tipo_movimiento` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id_tipo_movimiento`),
  UNIQUE KEY `tipo_movimiento` (`tipo_movimiento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipos_movimiento`
--

LOCK TABLES `tipos_movimiento` WRITE;
/*!40000 ALTER TABLE `tipos_movimiento` DISABLE KEYS */;
INSERT INTO `tipos_movimiento` VALUES (1,'Alta de Cuenta'),(2,'Alta de Préstamo'),(3,'Pago de Préstamo'),(4,'Transferencia Acreditada'),(5,'Transferencia Debitada');
/*!40000 ALTER TABLE `tipos_movimiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipos_usuario`
--

DROP TABLE IF EXISTS `tipos_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipos_usuario` (
  `id_tipo_usuario` int(11) NOT NULL,
  `tipo_usuario` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id_tipo_usuario`),
  UNIQUE KEY `tipo_usuario` (`tipo_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipos_usuario`
--

LOCK TABLES `tipos_usuario` WRITE;
/*!40000 ALTER TABLE `tipos_usuario` DISABLE KEYS */;
INSERT INTO `tipos_usuario` VALUES (2,'Administrador'),(1,'Cliente');
/*!40000 ALTER TABLE `tipos_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_usuario` varchar(50) NOT NULL,
  `pass` varchar(20) NOT NULL,
  `tipo_usuario` int(11) NOT NULL,
  `estado_usuario` bit(1) DEFAULT b'1',
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `nombre_usuario` (`nombre_usuario`),
  KEY `tipo_usuario` (`tipo_usuario`),
  CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`tipo_usuario`) REFERENCES `tipos_usuario` (`id_tipo_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'usuarioadmin','admin1234',2,''),(2,'homero','homero123',1,''),(3,'bart','bart1234',1,''),(4,'marge','marge123',1,''),(5,'lisa','lisa1234',1,''),(6,'maggie','maggie123',1,''),(7,'duffman','duffman123',1,''),(8,'nedflanders','nedflanders1',1,''),(9,'kent','kent1234',1,''),(10,'seymour','seymour123',1,''),(11,'otto','otto1234',1,''),(12,'edna','edna1234',1,''),(13,'patty','patty123',1,''),(14,'selma','selma123',1,''),(15,'willie','willie123',1,''),(16,'ralph','ralph123',1,'');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-22 11:32:55
