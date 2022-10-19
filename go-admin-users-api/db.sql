-- admin_users.systema definition

CREATE TABLE `systema` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;


-- admin_users.tipo_identificacion definition

CREATE TABLE `tipo_identificacion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `prefijo` varchar(4) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;


-- admin_users.persona definition

CREATE TABLE `persona` (
  `NUMERO_IDENTIFICACION` varchar(30) NOT NULL,
  `ID_TIPO_IDENTIFICACION` int(11) NOT NULL,
  `apellidos` varchar(100) DEFAULT NULL,
  `edad` int(11) DEFAULT NULL,
  `estado_civil` varchar(100) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp(),
  `nombres` varchar(100) DEFAULT NULL,
  `numero_hijos` int(11) DEFAULT NULL,
  `profesion` varchar(100) DEFAULT NULL,
  `sexo` varchar(2) DEFAULT NULL,
  `religion` varchar(100) DEFAULT NULL,
  `nivel_escolaridad` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID_TIPO_IDENTIFICACION`,`NUMERO_IDENTIFICACION`),
  CONSTRAINT `persona_FK` FOREIGN KEY (`ID_TIPO_IDENTIFICACION`) REFERENCES `tipo_identificacion` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- admin_users.persona_contacto definition

CREATE TABLE `persona_contacto` (
  `numero_identificacion` varchar(30) NOT NULL,
  `id_tipo_identificacion` int(11) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `movil` varchar(20) DEFAULT NULL,
  `tel` varchar(100) DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `activo` bit(1) NOT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`,`id_tipo_identificacion`,`numero_identificacion`),
  KEY `persona_contacto_FK` (`id_tipo_identificacion`,`numero_identificacion`),
  CONSTRAINT `persona_contacto_FK` FOREIGN KEY (`id_tipo_identificacion`, `numero_identificacion`) REFERENCES `persona` (`ID_TIPO_IDENTIFICACION`, `NUMERO_IDENTIFICACION`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- admin_users.privilegios definition

CREATE TABLE `privilegios` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(150) NOT NULL,
  `descripcion` varchar(1000) DEFAULT NULL,
  `level` int(11) NOT NULL,
  `activo` bit(1) NOT NULL DEFAULT b'1',
  `fk_id_privilegio` bigint(20) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `is_visible_menu` bit(1) DEFAULT b'0',
  `icono_class` varchar(100) DEFAULT NULL,
  `id_sistema` int(11) NOT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `privilegios_FK` (`fk_id_privilegio`),
  KEY `privilegios_FK_1` (`id_sistema`),
  CONSTRAINT `privilegios_FK` FOREIGN KEY (`fk_id_privilegio`) REFERENCES `privilegios` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `privilegios_FK_1` FOREIGN KEY (`id_sistema`) REFERENCES `systema` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- admin_users.roles_sistema definition

CREATE TABLE `roles_sistema` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_sistema` int(11) NOT NULL,
  `NOMBRE_ROL` varchar(100) NOT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`,`id_sistema`,`NOMBRE_ROL`),
  KEY `roles_sistema_FK` (`id_sistema`),
  CONSTRAINT `roles_sistema_FK` FOREIGN KEY (`id_sistema`) REFERENCES `systema` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;


-- admin_users.usuario definition

CREATE TABLE `usuario` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ID_TIPO_IDENTIFICACION` int(11) NOT NULL,
  `NUMERO_IDENTIFICACION` varchar(30) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp(),
  `movil` varchar(100) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `activo` bit(1) DEFAULT b'0',
  `confirmado` bit(1) DEFAULT b'0',
  `token_activate` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`,`ID_TIPO_IDENTIFICACION`,`NUMERO_IDENTIFICACION`),
  KEY `usuario_FK` (`ID_TIPO_IDENTIFICACION`,`NUMERO_IDENTIFICACION`),
  CONSTRAINT `usuario_FK` FOREIGN KEY (`ID_TIPO_IDENTIFICACION`, `NUMERO_IDENTIFICACION`) REFERENCES `persona` (`ID_TIPO_IDENTIFICACION`, `NUMERO_IDENTIFICACION`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4;


-- admin_users.roles_usuario definition

CREATE TABLE `roles_usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_tipo_identificacion` int(11) NOT NULL,
  `numero_identificacion` varchar(30) NOT NULL,
  `id_usuario` bigint(20) NOT NULL,
  `id_sistema` int(11) NOT NULL,
  `id_rol_sistema` bigint(20) NOT NULL,
  `nombre_rol_sistema` varchar(100) NOT NULL,
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`,`id_tipo_identificacion`,`numero_identificacion`,`id_usuario`,`id_sistema`,`id_rol_sistema`,`nombre_rol_sistema`),
  KEY `roles_usuario_FK` (`id_usuario`,`id_tipo_identificacion`,`numero_identificacion`),
  KEY `roles_usuario_FK_1` (`id`,`id_sistema`,`nombre_rol_sistema`),
  KEY `roles_usuario_FK_2` (`id_rol_sistema`,`id_sistema`,`nombre_rol_sistema`),
  CONSTRAINT `roles_usuario_FK` FOREIGN KEY (`id_usuario`, `id_tipo_identificacion`, `numero_identificacion`) REFERENCES `usuario` (`ID`, `ID_TIPO_IDENTIFICACION`, `NUMERO_IDENTIFICACION`) ON UPDATE CASCADE,
  CONSTRAINT `roles_usuario_FK_2` FOREIGN KEY (`id_rol_sistema`, `id_sistema`, `nombre_rol_sistema`) REFERENCES `roles_sistema` (`id`, `id_sistema`, `NOMBRE_ROL`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4;


-- admin_users.privilegios_rol_usuario definition

CREATE TABLE `privilegios_rol_usuario` (
  `id_rol_usuario` bigint(20) NOT NULL,
  `id_tipo_identificacion` int(11) NOT NULL,
  `numero_identificacion` varchar(30) NOT NULL,
  `id_usuario` bigint(20) NOT NULL,
  `id_sistema` int(11) NOT NULL,
  `id_rol_sistema` bigint(20) NOT NULL,
  `nombre_rol_sistema` varchar(100) NOT NULL,
  `id_privilegio` bigint(20) NOT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp(),
  `crear` bit(1) NOT NULL DEFAULT b'1',
  `editar` bit(1) NOT NULL DEFAULT b'1',
  `buscar` bit(1) NOT NULL DEFAULT b'1',
  `getAll` bit(1) NOT NULL DEFAULT b'0',
  `getNormal` bit(1) NOT NULL DEFAULT b'1',
  `desactivar` bit(1) NOT NULL DEFAULT b'1',
  `eliminar` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id_rol_usuario`,`id_tipo_identificacion`,`numero_identificacion`,`id_usuario`,`id_sistema`,`id_rol_sistema`,`nombre_rol_sistema`,`id_privilegio`),
  KEY `privilegios_rol_usuario_FK` (`id_privilegio`),
  CONSTRAINT `privilegios_rol_usuario_FK` FOREIGN KEY (`id_privilegio`) REFERENCES `privilegios` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `roles_usuario_privilegio_rol_fk_2` FOREIGN KEY (`id_rol_usuario`, `id_tipo_identificacion`, `numero_identificacion`, `id_usuario`, `id_sistema`, `id_rol_sistema`, `nombre_rol_sistema`) REFERENCES `roles_usuario` (`id`, `id_tipo_identificacion`, `numero_identificacion`, `id_usuario`, `id_sistema`, `id_rol_sistema`, `nombre_rol_sistema`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
