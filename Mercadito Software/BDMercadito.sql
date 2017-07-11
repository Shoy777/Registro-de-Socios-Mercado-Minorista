-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mercadito
-- ------------------------------------------------------
-- Server version	5.6.26-log

create database mercadito;
use mercadito;

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
-- Table structure for table `alquiler`
--

DROP TABLE IF EXISTS `alquiler`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alquiler` (
  `idAlquiler` int(11) NOT NULL AUTO_INCREMENT,
  `firma_secretaria` longblob,
  `firma_presidente` longblob,
  `idSocio` int(11) NOT NULL,
  `estado_alquiler` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idAlquiler`),
  KEY `fk_Alquiler_Socio1_idx` (`idSocio`),
  CONSTRAINT `fk_Alquiler_Socio1` FOREIGN KEY (`idSocio`) REFERENCES `socio` (`idSocio`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alquiler`
--

LOCK TABLES `alquiler` WRITE;
/*!40000 ALTER TABLE `alquiler` DISABLE KEYS */;
/*!40000 ALTER TABLE `alquiler` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_alquiler`
--

DROP TABLE IF EXISTS `detalle_alquiler`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detalle_alquiler` (
  `idAlquiler` int(11) NOT NULL,
  `idEstablecimiento` int(11) NOT NULL,
  `estado` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idAlquiler`,`idEstablecimiento`),
  KEY `fk_Alquiler_has_Establecimiento_Establecimiento1_idx` (`idEstablecimiento`),
  KEY `fk_Alquiler_has_Establecimiento_Alquiler1_idx` (`idAlquiler`),
  CONSTRAINT `fk_Alquiler_has_Establecimiento_Alquiler1` FOREIGN KEY (`idAlquiler`) REFERENCES `alquiler` (`idAlquiler`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Alquiler_has_Establecimiento_Establecimiento1` FOREIGN KEY (`idEstablecimiento`) REFERENCES `establecimiento` (`idEstablecimiento`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_alquiler`
--

LOCK TABLES `detalle_alquiler` WRITE;
/*!40000 ALTER TABLE `detalle_alquiler` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_alquiler` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_pagare`
--

DROP TABLE IF EXISTS `detalle_pagare`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detalle_pagare` (
  `idPagare` int(11) NOT NULL,
  `idEstablecimiento` int(11) NOT NULL,
  `precio_alquiler` double DEFAULT NULL,
  PRIMARY KEY (`idPagare`,`idEstablecimiento`),
  KEY `fk_Pagare_has_Establecimiento_Establecimiento1_idx` (`idEstablecimiento`),
  KEY `fk_Pagare_has_Establecimiento_Pagare1_idx` (`idPagare`),
  CONSTRAINT `fk_Pagare_has_Establecimiento_Establecimiento1` FOREIGN KEY (`idEstablecimiento`) REFERENCES `establecimiento` (`idEstablecimiento`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pagare_has_Establecimiento_Pagare1` FOREIGN KEY (`idPagare`) REFERENCES `pagare` (`idPagare`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_pagare`
--

LOCK TABLES `detalle_pagare` WRITE;
/*!40000 ALTER TABLE `detalle_pagare` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_pagare` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dominio`
--

DROP TABLE IF EXISTS `dominio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dominio` (
  `idDominio` int(11) NOT NULL AUTO_INCREMENT,
  `tabla` varchar(45) DEFAULT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idDominio`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `establecimiento`
--

DROP TABLE IF EXISTS `establecimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `establecimiento` (
  `idEstablecimiento` int(11) NOT NULL AUTO_INCREMENT,
  `pasaje` varchar(45) DEFAULT NULL,
  `numero` varchar(45) DEFAULT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  `giro_negocio` varchar(45) DEFAULT NULL,
  `precio_alquiler` double DEFAULT NULL,
  `estado_est` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idEstablecimiento`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `pagare`
--

DROP TABLE IF EXISTS `pagare`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pagare` (
  `idPagare` int(11) NOT NULL AUTO_INCREMENT,
  `idAlquiler` int(11) NOT NULL,
  `fecha_generada` datetime DEFAULT NULL,
  `monto_total` double DEFAULT NULL,
  `fecha_pago` datetime DEFAULT NULL,
  `estado_pagare` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idPagare`),
  KEY `fk_Pagare_Alquiler1_idx` (`idAlquiler`),
  CONSTRAINT `fk_Pagare_Alquiler1` FOREIGN KEY (`idAlquiler`) REFERENCES `alquiler` (`idAlquiler`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagare`
--

LOCK TABLES `pagare` WRITE;
/*!40000 ALTER TABLE `pagare` DISABLE KEYS */;
/*!40000 ALTER TABLE `pagare` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `socio`
--

DROP TABLE IF EXISTS `socio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `socio` (
  `idSocio` int(11) NOT NULL AUTO_INCREMENT,
  `nombres` varchar(100) DEFAULT NULL,
  `apellidos` varchar(100) DEFAULT NULL,
  `dni` varchar(45) DEFAULT NULL,
  `direccion_residencia` varchar(200) DEFAULT NULL,
  `telefono_fijo` varchar(45) DEFAULT NULL,
  `telefono_movil` varchar(45) DEFAULT NULL,
  `correo` varchar(70) DEFAULT NULL,
  `fecha_nac` date DEFAULT NULL,
  `sexo` varchar(45) DEFAULT NULL,
  `estado_civil` varchar(45) DEFAULT NULL,
  `nombre_conyuge` varchar(45) DEFAULT NULL,
  `idUbigeo` int(11) NOT NULL,
  `fecha_ins` date DEFAULT NULL,
  `fecha_reins` date DEFAULT NULL,
  `fecha_ing` date DEFAULT NULL,
  `cargo_anterior` varchar(45) DEFAULT NULL,
  `foto_socio` longblob,
  `firma_socio` longblob,
  `estado_socio` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idSocio`),
  KEY `fk_Socio_Ubigeo_idx` (`idUbigeo`),
  CONSTRAINT `fk_Socio_Ubigeo` FOREIGN KEY (`idUbigeo`) REFERENCES `ubigeo` (`idUbigeo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ubigeo`
--

DROP TABLE IF EXISTS `ubigeo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ubigeo` (
  `idUbigeo` int(11) NOT NULL,
  `departamento` varchar(45) DEFAULT NULL,
  `provincia` varchar(45) DEFAULT NULL,
  `distrito` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idUbigeo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_usuario` varchar(45) DEFAULT NULL,
  `nombres` varchar(45) DEFAULT NULL,
  `apellidos` varchar(45) DEFAULT NULL,
  `dni` varchar(200) DEFAULT NULL,
  `direccion_residencia` varchar(200) DEFAULT NULL,
  `telefono_fijo` varchar(45) DEFAULT NULL,
  `telefono_movil` varchar(45) DEFAULT NULL,
  `correo` varchar(70) DEFAULT NULL,
  `fecha_nac` date DEFAULT NULL,
  `sexo` varchar(45) DEFAULT NULL,
  `estado_civil` varchar(45) DEFAULT NULL,
  `nombre_conyuge` varchar(45) DEFAULT NULL,
  `idUbigeo` int(11) NOT NULL,
  `foto_usuario` longblob,
  `firma_usuario` longblob,
  `usuario` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `fecha_reg` date DEFAULT NULL,
  `estado_usuario` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`),
  KEY `fk_Usuario_Ubigeo1_idx` (`idUbigeo`),
  CONSTRAINT `fk_Usuario_Ubigeo1` FOREIGN KEY (`idUbigeo`) REFERENCES `ubigeo` (`idUbigeo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-15 16:56:36
