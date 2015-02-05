/*
Navicat MySQL Data Transfer

Source Server         : Mysql Connection
Source Server Version : 50541
Source Host           : localhost:3306
Source Database       : facultad

Target Server Type    : MYSQL
Target Server Version : 50541
File Encoding         : 65001

Date: 2015-02-05 17:54:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for alumno
-- ----------------------------
DROP TABLE IF EXISTS `alumno`;
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
  KEY `fk_alumno_profesor` (`profesor_id_prof`),
  CONSTRAINT `fk_alumno_profesor` FOREIGN KEY (`profesor_id_prof`) REFERENCES `profesor` (`id_prof`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of alumno
-- ----------------------------
INSERT INTO `alumno` VALUES ('1', '200004', 'Carlos Perez', '1988-01-01', 'MALE', 'Av Javier Prado', '85631456', '1');
INSERT INTO `alumno` VALUES ('2', '200135', 'Eliana Zapata', '1988-01-01', 'FEMALE', 'Av Mirones', '', '1');
INSERT INTO `alumno` VALUES ('3', '200147', 'Richard Lozano', '1988-01-01', 'MALE', 'Av Castilla', '96321547', '1');
INSERT INTO `alumno` VALUES ('4', '200152', 'Lizbeth Mamani', '1988-01-01', 'FEMALE', 'Av Habich', '56849963', '2');
INSERT INTO `alumno` VALUES ('5', '200159', 'Silvia Peña', '1988-01-01', 'FEMALE', 'Av Leitod', '85412878', '2');
INSERT INTO `alumno` VALUES ('6', '200170', 'Cesar Diaz', '1988-01-01', 'MALE', 'Av Confraternidad', '25698845', '2');
INSERT INTO `alumno` VALUES ('7', '200369', 'Alexsandra Herrera', '1988-01-01', 'FEMALE', 'Av Rios', '96321478', '2');
INSERT INTO `alumno` VALUES ('8', '200456', 'Gustavo Avila', '1988-01-01', 'MALE', 'Av Universitaria', '', '1');
INSERT INTO `alumno` VALUES ('9', '200584', 'Etmee Zarate', '1988-01-01', 'FEMALE', 'Av Sauces', '12354785', '1');
INSERT INTO `alumno` VALUES ('10', '200852', 'José Lopez', '1988-01-01', 'MALE', 'Av Aviacion', '', '2');
INSERT INTO `alumno` VALUES ('11', '200005', 'Máximo Romero', '2015-02-05', 'MALE', 'Av Sucre 2678', '', '1');

-- ----------------------------
-- Table structure for profesor
-- ----------------------------
DROP TABLE IF EXISTS `profesor`;
CREATE TABLE `profesor` (
  `id_prof` int(11) NOT NULL AUTO_INCREMENT,
  `cod_prof` varchar(8) NOT NULL,
  `nom_prof` varchar(255) NOT NULL,
  `nacimiento_prof` date NOT NULL,
  `email_prof` varchar(45) NOT NULL,
  PRIMARY KEY (`id_prof`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of profesor
-- ----------------------------
INSERT INTO `profesor` VALUES ('1', '212399', 'Carlos Melo', '1960-01-01', 'cmelo@univ.edu');
INSERT INTO `profesor` VALUES ('2', '212588', 'Giovanna Melva', '1960-01-01', 'gmelva@univ.edu');
INSERT INTO `profesor` VALUES ('3', '212963', 'Francisco Vargas', '1975-02-13', 'fran.vargas@scytl.com');
