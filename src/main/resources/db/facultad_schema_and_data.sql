-- MySQL dump 10.13  Distrib 5.7.13, for Win32 (AMD64)
--
-- Host: localhost    Database: facultad
-- ------------------------------------------------------
-- Server version	5.7.13-log

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
-- Table structure for table `alumno`
--

DROP TABLE IF EXISTS `alumno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alumno` (
  `id_alum` int(11) NOT NULL AUTO_INCREMENT,
  `cod_alum` varchar(8) NOT NULL,
  `nom_alum` varchar(255) NOT NULL,
  `nacimiento_alum` date NOT NULL,
  `sexo_alum` varchar(6) NOT NULL,
  `direc_alum` varchar(255) NOT NULL,
  `telef_alum` varchar(9) DEFAULT '',
  `profesor_id_prof` int(11) NOT NULL,
  PRIMARY KEY (`id_alum`),
  UNIQUE KEY `cod_alum` (`cod_alum`),
  KEY `fk_alumno_profesor` (`profesor_id_prof`),
  CONSTRAINT `fk_alumno_profesor` FOREIGN KEY (`profesor_id_prof`) REFERENCES `profesor` (`id_prof`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumno`
--

LOCK TABLES `alumno` WRITE;
/*!40000 ALTER TABLE `alumno` DISABLE KEYS */;
INSERT INTO `alumno` VALUES (1,'200004','Carlos Perez','1988-01-14','MALE','Av Javier Prado','85631456',4),(2,'200135','Eliana Zapata','1988-01-01','FEMALE','Av Mirones','',1),(3,'200147','Richard Lozano Orihuela','1988-11-28','MALE','Av Castilla','96321547',1),(4,'200152','Lizbeth Mamani','1988-01-01','FEMALE','Av Habich. 231','56849963',2),(5,'200159','Silvia Peña Guevara','1987-03-10','FEMALE','Av Leitod','85412878',1),(6,'200170','César Díaz','1988-01-01','MALE','Av Confraternidad','25698845',3),(7,'200369','Alexsandra Herrera Vasquez','1988-06-19','FEMALE','Av Rios Pihue','96321478',2),(8,'200456','Gustavo Avila','1988-01-01','MALE','Av Universitaria','',1),(9,'200584','Etmee Zarate Castañeda','1988-01-01','FEMALE','Av Sauces','12354785',1),(10,'200852','José Lopez','1988-01-01','MALE','Av Aviacion','',2),(11,'200045','Máximo Romero Benites','1985-02-05','MALE','Av Sucre 2678','',5),(12,'200006','Mario Inga','1986-06-11','MALE','Por Wilson','57675756',4),(13,'200465','Jaime Leandro','1988-10-07','MALE','Por la Campiña','32323412',5),(14,'200368','Milu Bazán','1988-11-23','FEMALE','Por el Callao, la jato de Max','214544',4),(15,'231123','Josue Barrios','1985-07-12','MALE','En San Borja','123123',5),(16,'200005','Jorge Veramendi Salazar','1986-10-03','MALE','qwfasfasdfs','',4),(20,'43453','Carlos Lobaton','2017-04-12','FEMALE','AV. generador','121235555',5);
/*!40000 ALTER TABLE `alumno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profesor`
--

DROP TABLE IF EXISTS `profesor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profesor` (
  `id_prof` int(11) NOT NULL AUTO_INCREMENT,
  `cod_prof` varchar(8) NOT NULL,
  `nom_prof` varchar(255) NOT NULL,
  `nacimiento_prof` date NOT NULL,
  `email_prof` varchar(45) NOT NULL,
  PRIMARY KEY (`id_prof`),
  UNIQUE KEY `cod_prof` (`cod_prof`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profesor`
--

LOCK TABLES `profesor` WRITE;
/*!40000 ALTER TABLE `profesor` DISABLE KEYS */;
INSERT INTO `profesor` VALUES (1,'212399','Carlos Melo','1960-01-01','cmelo@univ.edu'),(2,'212588','Giovanna Melva','1963-06-08','gmelva@univ.edu'),(3,'212963','Francisco Vargas','1981-02-13','francisco.vargas@scytl.com'),(4,'212696','Ernesto Quiñones','1975-11-18','equiñones@eqsoft.com.pe'),(5,'212456','Paul Vargas Machuca','1985-02-07','paul.vargas@scytl.com'),(6,'83258937','David Pérez Martin','1988-12-28','david.perez@entelgy.com');
/*!40000 ALTER TABLE `profesor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-13 14:32:34
