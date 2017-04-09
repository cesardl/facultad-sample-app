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
INSERT INTO `alumno` VALUES ('4', '200152', 'Lizbeth Mamani', '1988-01-01', 'FEMALE', 'Av Habich. 231', '56849963', '2');
INSERT INTO `alumno` VALUES ('5', '200159', 'Silvia Peña Guevara', '1987-03-10', 'FEMALE', 'Av Leitod', '85412878', '1');
INSERT INTO `alumno` VALUES ('6', '200170', 'César Díaz', '1988-01-01', 'MALE', 'Av Confraternidad', '25698845', '3');
INSERT INTO `alumno` VALUES ('7', '200369', 'Alexsandra Herrera Vasquez', '1988-06-19', 'FEMALE', 'Av Rios', '96321478', '2');
INSERT INTO `alumno` VALUES ('8', '200456', 'Gustavo Avila', '1988-01-01', 'MALE', 'Av Universitaria', '', '1');
INSERT INTO `alumno` VALUES ('9', '200584', 'Etmee Zarate', '1988-01-01', 'FEMALE', 'Av Sauces', '12354785', '1');
INSERT INTO `alumno` VALUES ('10', '200852', 'José Lopez', '1988-01-01', 'MALE', 'Av Aviacion', '', '2');
INSERT INTO `alumno` VALUES ('11', '200045', 'Máximo Romero', '1985-02-05', 'MALE', 'Av Sucre 2678', '', '5');
INSERT INTO `alumno` VALUES ('12', '200006', 'Mario Inga', '1986-06-11', 'MALE', 'Por Wilson', '', '4');
INSERT INTO `alumno` VALUES ('13', '200465', 'Jaime Leandro', '1988-10-07', 'MALE', 'Por la Campiña', '32323412', '5');
INSERT INTO `alumno` VALUES ('14', '200368', 'Milu Bazán', '1988-11-23', 'FEMALE', 'Por el Callao, la jato de Max', '214544', '4');
INSERT INTO `alumno` VALUES ('15', '231123', 'Josue Barrios', '1985-07-12', 'MALE', 'En San Borja', '123123', '5');
INSERT INTO `alumno` VALUES ('16', '200005', 'Jorge Veramendi Salazar', '1986-10-03', 'MALE', 'qwfasfasdfs', '', '4');
