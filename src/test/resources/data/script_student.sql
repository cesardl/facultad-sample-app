-- ----------------------------
-- Table structure for alumno
-- ----------------------------
DROP TABLE IF EXISTS `alumno`;
CREATE TABLE `alumno` (
  `id_alum` int(11) NOT NULL AUTO_INCREMENT,
  `cod_alum` varchar(8) NOT NULL UNIQUE,
  `nom_alum` varchar(255) NOT NULL,
  `nacimiento_alum` date NOT NULL,
  `sexo_alum` varchar(6) NOT NULL,
  `direc_alum` varchar(255) NOT NULL,
  `telef_alum` varchar(9) DEFAULT '',
  `profesor_id_prof` int(11) NOT NULL,
  PRIMARY KEY (`id_alum`)
);

-- ----------------------------
-- Records of alumno
-- ----------------------------
INSERT INTO `alumno` VALUES ('1', '200004', 'Carlos Perez', '1988-01-14', 'MALE', 'Av Javier Prado', '85631456', '4');
INSERT INTO `alumno` VALUES ('2', '200135', 'Eliana Zapata', '1988-01-01', 'FEMALE', 'Av Mirones', '', '1');
INSERT INTO `alumno` VALUES ('3', '200147', 'Richard Lozano Orihuela', '1988-11-28', 'MALE', 'Av Castilla', '96321547', '1');
INSERT INTO `alumno` VALUES ('4', '200159', 'Silvia Peña Guevara', '1987-03-10', 'FEMALE', 'Av Leitod', '85412878', '1');
INSERT INTO `alumno` VALUES ('5', '231123', 'Josue Barrios', '1985-07-12', 'MALE', 'En San Borja', '123123', '5');
