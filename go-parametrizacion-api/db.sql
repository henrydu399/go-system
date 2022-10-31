create or replace table parametros
(
    `key`       varchar(100) not null
        primary key,
    value       varchar(300) not null,
    descripcion varchar(500) null,
    api_name    varchar(100) null
);

grant alter, index, insert, references, select, update on table parametros to parametros;

INSERT INTO parametros.parametros (`key`,value,descripcion,api_name) VALUES
	 ('EMAIL_FROM_GO_HOME','go-system-home@outlook.com','CORREO DE DONDE SE ENVIAN TODOS LOS CORREOS DE LA APP GO-HOME','emailApi'),
	 ('KEY_SENDBLUEMAILS','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','URL PROVEDOR EXTERNO DE CORREOS','emailApi'),
	 ('NAME_FROM_GO_HOME','GO-HOME-SYSTEM','NOMBREDE DONDE SE ENVIAN TODOS LOS CORREOS DE LA APP GO-HOME','emailApi'),
	 ('PATH_GET_USER_FOR_LOGIN','/adminUsers/usuario/userforlogin/','GET : OBTIENE LA INFORMACION DEL USUARIO EN GO-ADMIN-USER-API','adminUsersApi'),
	 ('PATH_GO_ADMIN_USER','/adminUsers',NULL,'adminUsersApi'),
	 ('PATH_GO_ADMIN_USER_API_TIPO_DOCUMENTO','/adminUsers/tipoIdentificacion/','CRUD Tipo Documento','adminUsersApi'),
	 ('PATH_GO_ADMIN_USER_CONFIRM','/adminUsers/usuario/confirm/','Metodo que confirma el usuario atravez de un token','adminUsersApi'),
	 ('PATH_GO_ADMIN_USER_EDITH_FOR_SYSTEM','/adminUsers/usuario/edithforsystem/','METODO QUE EDITA EL USUARIO + PERSONA + PERSONA CONTACTO + ROL','adminUsersApi'),
	 ('PATH_GO_ADMIN_USER_FIND','/adminUsers/usuario/find/','POST: USUARIODTO : BUSCA EL USUARIO POR LAS PROPIEDADES ENVIADAS EN EL OBJETO','adminUsersApi'),
	 ('PATH_GO_ADMIN_USER_GET_ROLES','/adminUsers/usuario/rolesSistema/','CRUD ROLES SISTEMA','adminUsersApi');
INSERT INTO parametros.parametros (`key`,value,descripcion,api_name) VALUES
	 ('PATH_GO_ADMIN_USER_SAVE_FOR_SYSTEM','/adminUsers/usuario/saveforsystem/','METODO QUE GUARDA EL USUARIO + PERSONA + PERSONA CONTACTO + ROL','adminUsersApi'),
	 ('PATH_GO_ADMIN_USER_USUARIO','/adminUsers/usuario/','CRUD usuario','adminUsersApi'),
	 ('PATH_GO_ADMIN_USER_PERSONA','/adminUsers/persona/','CRUD persona','adminUsersApi'),
	 ('PATH_GO_ADMIN_USER_USUARIO_DELETE','/adminUsers/usuario/delete/','Eliminar usuario','adminUsersApi'),
	 ('PATH_GO_ADMIN_USER_USUARIO_DESACTIVATE','/adminUsers/usuario/desactivate/','Desactivar usuario','adminUsersApi'),
	 ('PATH_GO_ADMIN_USER_USUARIO_SYSTEM','/adminUsers/usuario/saveforsystem/','POST : USUARIODTO: GUARDA GENERAL  EL USUARIO EN GO-ADMIN-USER-API','adminUsersApi'),
	 ('PATH_GO_ADMIN_USER_USUARIO_SYSTEM_PUBLIC','/adminUsers/usuario/saveforsystemPublic/','POST : USUARIODTO: GUARDA GENERAL  EL USUARIO EN GO-ADMIN-USER-API','adminUsersApi'),
	 ('PATH_GO_EMAIL_API','/email/',NULL,NULL),
	 ('PATH_GO_FILE_API','/file/',NULL,NULL),
	 ('PATH_GO_PARAMETRICAS_API','/parametricas/',NULL,'parametricasAPI');
INSERT INTO parametros.parametros (`key`,value,descripcion,api_name) VALUES
	 ('PATH_GO_PARAMETRICAS_API_BARRIO','/parametricas/barrio/','CRUD para barrio entity','parametricasAPI'),
	 ('PATH_GO_PARAMETRICAS_API_CIUDAD','/parametricas/ciudad/','CRUD para Ciudad entity','parametricasAPI'),
	 ('PATH_GO_PARAMETRICAS_API_DEPARTAMENTO','/parametricas/departamento/','CRUD para departamento entity','parametricasAPI'),
	 ('PATH_GO_ROLES_USERS_BY_SISTEMA_NAME','/adminUsers/rolSistema/findBySistemaName/','GET : OBTIENE LOS ROLES DE UN SISTEMA POR EL NOMBRE DEL SISTEMA','adminUsersApi'),
	 ('PATH_SEND_EMAIL','send/','POST: ENVIA CORRREO ','emailApi'),
	 ('PLANTILLA_CONFIRM_USER_GO_HOME','REGISTER_FOR_COMPLETE','NOMBRE DE LA PLANTILLA PARA CONFIRMAR LA CREACION DEL USUARIO',NULL),
	 ('URL_GATEWAY','http://localhost:8080','URL DEL GATEWAY',NULL),
	 ('URL_SENDBLUEMAILS','https://api.sendinblue.com/v3/smtp/email','URL PROVEDOR EXTERNO DE CORREOS','emailApi');



