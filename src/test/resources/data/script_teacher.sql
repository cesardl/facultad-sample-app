-- ----------------------------
-- Table structure for profesor
-- ----------------------------
DROP TABLE IF EXISTS `profesor`;
CREATE TABLE `profesor`
(
    `id_prof`         int(11)      NOT NULL AUTO_INCREMENT,
    `cod_prof`        varchar(8)   NOT NULL UNIQUE,
    `nom_prof`        varchar(255) NOT NULL,
    `nacimiento_prof` date         NOT NULL,
    `email_prof`      varchar(45)  NOT NULL,
    PRIMARY KEY (`id_prof`)
);

-- ----------------------------
-- Records of profesor
-- ----------------------------
INSERT INTO `profesor`
VALUES ('1', '212963', 'Francisco Vargas', '1981-02-13', 'francisco.vargas@scytl.com');
INSERT INTO `profesor`
VALUES ('2', '212696', 'Ernesto Quiñones', '1975-11-18', 'equiñones@eqsoft.com.pe');
INSERT INTO `profesor`
VALUES ('3', '212456', 'Paul Vargas', '1985-02-07', 'paul.vargas@scytl.com');
