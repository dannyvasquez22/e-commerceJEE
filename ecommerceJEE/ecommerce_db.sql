/*
SQLyog Ultimate v12.4.3 (64 bit)
MySQL - 10.1.25-MariaDB : Database - ecommerce_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ecommerce_db` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci */;

USE `ecommerce_db`;

/*Table structure for table `tbl_categoria` */

DROP TABLE IF EXISTS `tbl_categoria`;

CREATE TABLE `tbl_categoria` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL,
  `visible` tinyint(1) NOT NULL DEFAULT '1',
  `categoria_superior` int(11) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `UNIQUE` (`nombre`),
  KEY `FK_CATEGORIA_SUPER` (`categoria_superior`),
  CONSTRAINT `FK_CATEGORIA_SUPER` FOREIGN KEY (`categoria_superior`) REFERENCES `tbl_categoria` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

/*Data for the table `tbl_categoria` */

insert  into `tbl_categoria`(`codigo`,`nombre`,`visible`,`categoria_superior`) values 
(1,'ROPA DEPORTIVA',1,1),
(2,'NIKE',1,1),
(3,'ADIDAS',1,1),
(4,'PUMA',1,1),
(5,'HOMBRES',1,5),
(6,'SACOS',1,5),
(7,'PANTALONES',1,5),
(8,'NIÑOS',1,8),
(9,'MUJERES',1,9);

/*Table structure for table `tbl_marca` */

DROP TABLE IF EXISTS `tbl_marca`;

CREATE TABLE `tbl_marca` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL,
  `visible` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `tbl_marca` */

insert  into `tbl_marca`(`codigo`,`nombre`,`visible`) values 
(1,'NIKE',1),
(2,'ADIDAS',1),
(3,'PUMA',1),
(4,'LACOSTE',1);

/*Table structure for table `tbl_producto` */

DROP TABLE IF EXISTS `tbl_producto`;

CREATE TABLE `tbl_producto` (
  `webid` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) DEFAULT NULL,
  `precio` decimal(10,2) DEFAULT NULL,
  `stock` int(11) DEFAULT '1',
  `nuevo` tinyint(1) DEFAULT '1',
  `recomendado` tinyint(1) DEFAULT '0',
  `visible` tinyint(1) DEFAULT '1',
  `descripcion` varchar(255) DEFAULT NULL,
  `precio_nuevo` decimal(10,2) DEFAULT NULL,
  `codigo_marca` int(11) DEFAULT NULL,
  `codigo_categoria` int(11) DEFAULT NULL,
  `img` varchar(100) DEFAULT 'demo.png',
  PRIMARY KEY (`webid`),
  KEY `FK_MARCA_PRODUCTO` (`codigo_marca`),
  KEY `FK_CATEGORIA_PRODUCTO` (`codigo_categoria`),
  CONSTRAINT `FK_CATEGORIA_PRODUCTO` FOREIGN KEY (`codigo_categoria`) REFERENCES `tbl_categoria` (`codigo`),
  CONSTRAINT `FK_MARCA_PRODUCTO` FOREIGN KEY (`codigo_marca`) REFERENCES `tbl_marca` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

/*Data for the table `tbl_producto` */

insert  into `tbl_producto`(`webid`,`nombre`,`precio`,`stock`,`nuevo`,`recomendado`,`visible`,`descripcion`,`precio_nuevo`,`codigo_marca`,`codigo_categoria`,`img`) values 
(5,'Camibuso Nike',70.00,10,1,1,1,'estas es la rpueba							',0.00,1,5,'13220181123258110741388009112734pro4.jpg'),
(6,'Cascaca mujer',12.00,50,1,1,1,'asdsa							',0.00,3,9,'14220180853308295123197402573357pu5.jpg');

/*Table structure for table `tbl_producto_moneda` */

DROP TABLE IF EXISTS `tbl_producto_moneda`;

CREATE TABLE `tbl_producto_moneda` (
  `moneda` char(3) COLLATE latin1_spanish_ci NOT NULL,
  `precio` decimal(10,2) DEFAULT NULL,
  `precio_nuevo` decimal(10,2) DEFAULT NULL,
  `webid` int(11) NOT NULL,
  PRIMARY KEY (`moneda`,`webid`),
  KEY `FK_PRODUCT` (`webid`),
  CONSTRAINT `FK_PRODUCT` FOREIGN KEY (`webid`) REFERENCES `tbl_producto` (`webid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

/*Data for the table `tbl_producto_moneda` */

insert  into `tbl_producto_moneda`(`moneda`,`precio`,`precio_nuevo`,`webid`) values 
('COP',1000.00,0.00,5),
('COP',12.00,0.00,6),
('PEN',170.00,0.00,5),
('PEN',123.00,0.00,6),
('USD',30.00,0.00,5),
('USD',12.00,0.00,6);

/*Table structure for table `tbl_revision` */

DROP TABLE IF EXISTS `tbl_revision`;

CREATE TABLE `tbl_revision` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL,
  `correo` varchar(60) DEFAULT NULL,
  `comentario` varchar(200) DEFAULT NULL,
  `estrellas` int(11) DEFAULT '3',
  `fecha` datetime DEFAULT NULL,
  `webid` int(11) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_PRODUCTO_REVISION` (`webid`),
  CONSTRAINT `FK_PRODUCTO_REVISION` FOREIGN KEY (`webid`) REFERENCES `tbl_producto` (`webid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `tbl_revision` */

/* Procedure structure for procedure `sp_consultar_producto` */

/*!50003 DROP PROCEDURE IF EXISTS  `sp_consultar_producto` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_consultar_producto`(p_moneda CHAR(3), p_webid INT)
BEGIN

IF p_moneda != 'MXN' THEN
	SELECT p.*, m.precio AS precio2, m.precio_nuevo AS precion2
	FROM tbl_producto p
	INNER JOIN tbl_producto_moneda m ON p.webid = m.webid
	INNER JOIN tbl_marca mar ON mar.codigo = p.codigo_marca
	INNER JOIN tbl_categoria c ON c.codigo = p.codigo_categoria
	WHERE p.visible = TRUE 
	AND c.visible = TRUE 
	AND mar.visible = TRUE 
	AND m.moneda = p_moneda 
	AND p.webid = p_webid;
ELSE
	SELECT p.*
	FROM tbl_producto  p
	INNER JOIN tbl_marca mar ON mar.codigo = p.codigo_marca
	INNER JOIN tbl_categoria c ON c.codigo = p.codigo_categoria
	WHERE p.visible = TRUE 
	AND c.visible = TRUE 
	AND mar.visible = TRUE
	AND p.webid = p_webid;
END IF;

END */$$
DELIMITER ;

/* Procedure structure for procedure `sp_contar_productos_marca` */

/*!50003 DROP PROCEDURE IF EXISTS  `sp_contar_productos_marca` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_contar_productos_marca`(mar INT)
BEGIN

SELECT COUNT(p.webid)
FROM tbl_producto p
WHERE p.codigo_marca = mar;

END */$$
DELIMITER ;

/* Procedure structure for procedure `sp_contar_sub_categorias` */

/*!50003 DROP PROCEDURE IF EXISTS  `sp_contar_sub_categorias` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_contar_sub_categorias`(codcat int)
BEGIN
	
		SELECT COUNT(codigo) AS cantidad
		FROM tbl_categoria
		WHERE categoria_superior = codcat
		AND codigo <> codcat;

	END */$$
DELIMITER ;

/* Procedure structure for procedure `sp_listar_categoria_superior` */

/*!50003 DROP PROCEDURE IF EXISTS  `sp_listar_categoria_superior` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_categoria_superior`()
BEGIN

		SELECT	codigo, nombre 
		FROM tbl_categoria
		WHERE codigo = categoria_superior 
		and visible = TRUE;

	END */$$
DELIMITER ;

/* Procedure structure for procedure `sp_listar_por_categoria` */

/*!50003 DROP PROCEDURE IF EXISTS  `sp_listar_por_categoria` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_por_categoria`(p_moneda CHAR(3), cat int)
BEGIN

IF p_moneda != 'MXN' THEN
	SELECT p.*, m.precio AS precio2, m.precio_nuevo AS precion2
	FROM tbl_producto p
	INNER JOIN tbl_producto_moneda m ON p.webid = m.webid
	INNER JOIN tbl_marca mar ON mar.codigo = p.codigo_marca
	INNER JOIN tbl_categoria c ON c.codigo = p.codigo_categoria
	WHERE p.visible = TRUE 
	AND c.visible = TRUE 
	AND mar.visible = TRUE 
	AND m.moneda = p_moneda 
	AND p.codigo_categoria = cat;
ELSE
	SELECT p.*
	FROM tbl_producto  p
	INNER JOIN tbl_marca mar ON mar.codigo = p.codigo_marca
	INNER JOIN tbl_categoria c ON c.codigo = p.codigo_categoria
	WHERE p.visible = TRUE 
	AND c.visible = TRUE 
	AND mar.visible = TRUE
	AND p.codigo_categoria = cat;
END IF;

END */$$
DELIMITER ;

/* Procedure structure for procedure `sp_listar_por_marca` */

/*!50003 DROP PROCEDURE IF EXISTS  `sp_listar_por_marca` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_por_marca`(p_moneda CHAR(3), mar INT)
BEGIN

IF p_moneda != 'MXN' THEN
	SELECT p.*, m.precio AS precio2, m.precio_nuevo AS precion2
	FROM tbl_producto p
	INNER JOIN tbl_producto_moneda m ON p.webid = m.webid
	INNER JOIN tbl_marca mar ON mar.codigo = p.codigo_marca
	INNER JOIN tbl_categoria c ON c.codigo = p.codigo_categoria
	WHERE p.visible = TRUE 
	AND c.visible = TRUE 
	AND mar.visible = TRUE 
	AND m.moneda = p_moneda 
	AND p.codigo_marca = mar;
ELSE
	SELECT p.*
	FROM tbl_producto  p
	INNER JOIN tbl_marca mar ON mar.codigo = p.codigo_marca
	INNER JOIN tbl_categoria c ON c.codigo = p.codigo_categoria
	WHERE p.visible = TRUE 
	AND c.visible = TRUE 
	AND mar.visible = TRUE
	AND p.codigo_marca = mar;
END IF;

END */$$
DELIMITER ;

/* Procedure structure for procedure `sp_listar_recomendados` */

/*!50003 DROP PROCEDURE IF EXISTS  `sp_listar_recomendados` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_recomendados`(p_moneda char(3))
BEGIN

if p_moneda != 'MXN' THEN
	SELECT p.*, m.precio as precio2, m.precio_nuevo AS precion2
	FROM tbl_producto p
	INNER JOIN tbl_producto_moneda m ON p.webid = m.webid
	INNER JOIN tbl_marca mar ON mar.codigo = p.codigo_marca
	INNER JOIN tbl_categoria c ON c.codigo = p.codigo_categoria
	WHERE p.visible = TRUE 
	AND c.visible = TRUE 
	AND mar.visible = TRUE 
	AND m.moneda = p_moneda;
ELSE
	SELECT p.*
	FROM tbl_producto  p
	INNER JOIN tbl_marca mar ON mar.codigo = p.codigo_marca
	INNER JOIN tbl_categoria c ON c.codigo = p.codigo_categoria
	WHERE p.visible = TRUE 
	AND c.visible = TRUE 
	AND mar.visible = TRUE;
END IF;

END */$$
DELIMITER ;

/* Procedure structure for procedure `sp_listar_sub_categoria` */

/*!50003 DROP PROCEDURE IF EXISTS  `sp_listar_sub_categoria` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_sub_categoria`(v_categoria_superior int)
BEGIN

		SELECT codigo, nombre 
		FROM tbl_categoria 
		WHERE codigo <> categoria_superior 
		AND categoria_superior = v_categoria_superior 
		AND visible = TRUE;

	END */$$
DELIMITER ;

/* Procedure structure for procedure `sp_listar_todo_categoria` */

/*!50003 DROP PROCEDURE IF EXISTS  `sp_listar_todo_categoria` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_todo_categoria`()
BEGIN

		SELECT codigo, nombre 
		FROM tbl_categoria
		WHERE visible = TRUE
		order by nombre;

	END */$$
DELIMITER ;

/* Procedure structure for procedure `sp_listar_todo_marca` */

/*!50003 DROP PROCEDURE IF EXISTS  `sp_listar_todo_marca` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_todo_marca`()
BEGIN

		SELECT codigo, nombre 
		FROM tbl_marca
		WHERE visible = TRUE
		ORDER BY nombre;

	END */$$
DELIMITER ;

/* Procedure structure for procedure `sp_registrar_producto` */

/*!50003 DROP PROCEDURE IF EXISTS  `sp_registrar_producto` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_registrar_producto`(
	p_nombre varchar(30),
	p_precio decimal(10, 2),
	p_precio_nuevo decimal(10, 2),
	p_stock int,
	p_nuevo boolean,
	p_recomendado boolean,
	p_descripcion text,
	p_visible boolean,
	p_codigo_marca int,
	p_codigo_categoria int,
	p_img varchar(100),
	p_moneda_cop char(3),
	p_precio_cop decimal(10, 2),
	p_precio_nuevo_cop decimal(10, 2),
	p_moneda_usd CHAR(3),
	p_precio_usd DECIMAL(10, 2),
	p_precio_nuevo_usd DECIMAL(10, 2),
	p_moneda_pen CHAR(3),
	p_precio_pen DECIMAL(10, 2),
	p_precio_nuevo_pen DECIMAL(10, 2)
	
    )
BEGIN

declare v_webid int;

INSERT INTO tbl_producto VALUES (NULL, p_nombre, p_precio, p_stock, p_nuevo, p_recomendado,
			p_visible, p_descripcion, p_precio_nuevo, p_codigo_marca, p_codigo_categoria, p_img);
			

set v_webid = (SELECT LAST_INSERT_ID());			

INSERT INTO tbl_producto_moneda VALUES (p_moneda_cop, p_precio_cop, p_precio_nuevo_cop, v_webid);
INSERT INTO tbl_producto_moneda VALUES (p_moneda_usd, p_precio_usd, p_precio_nuevo_usd, v_webid);
INSERT INTO tbl_producto_moneda VALUES (p_moneda_pen, p_precio_pen, p_precio_nuevo_pen, v_webid);

END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
