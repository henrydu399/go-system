create or replace table parametros
(
    `key`       varchar(100) not null
        primary key,
    value       varchar(300) not null,
    descripcion varchar(500) null,
    api_name    varchar(100) null
);

grant alter, index, insert, references, select, update on table parametros to parametros;

INSERT INTO parametros.parametros (`key`, value, descripcion, api_name) VALUES ('EMAIL_FROM_GO_HOME', 'go-system-home@outlook.com', 'CORREO DE DONDE SE ENVIAN TODOS LOS CORREOS DE LA APP GO-HOME', null);
INSERT INTO parametros.parametros (`key`, value, descripcion, api_name) VALUES ('PATH_GET_USER_FOR_LOGIN', '/usuario/userforlogin/', 'GET : OBTIENE LA INFORMACION DEL USUARIO EN GO-ADMIN-USER-API', 'adminUsersApi');
INSERT INTO parametros.parametros (`key`, value, descripcion, api_name) VALUES ('PATH_GO_ADMIN_USER', '/adminUsers', null, 'adminUsersApi');
INSERT INTO parametros.parametros (`key`, value, descripcion, api_name) VALUES ('PATH_GO_ADMIN_USER_FIND', '/usuario/find/', 'POST: USUARIODTO : BUSCA EL USUARIO POR LAS PROPIEDADES ENVIADAS EN EL OBJETO', 'adminUsersApi');
INSERT INTO parametros.parametros (`key`, value, descripcion, api_name) VALUES ('PATH_GO_ADMIN_USER_SAVE_FOR_SYSTEM', '/usuario/saveforsystem/', null, 'adminUsersApi');
INSERT INTO parametros.parametros (`key`, value, descripcion, api_name) VALUES ('PATH_GO_EMAIL_API', '/email/', null, null);
INSERT INTO parametros.parametros (`key`, value, descripcion, api_name) VALUES ('PATH_GO_FILE_API', '/file/', null, null);
INSERT INTO parametros.parametros (`key`, value, descripcion, api_name) VALUES ('PATH_GO_PARAMETRICAS_API', '/parametricas/', null, null);
INSERT INTO parametros.parametros (`key`, value, descripcion, api_name) VALUES ('PATH_GO_ROLES_USERS_BY_SISTEMA_NAME', '/rolSistema/findBySistemaName/', 'GET : OBTIENE LOS ROLES DE UN SISTEMA POR EL NOMBRE DEL SISTEMA', 'adminUsersApi');
INSERT INTO parametros.parametros (`key`, value, descripcion, api_name) VALUES ('PATH_SAVE_USER', '/usuario/', 'POST : USUARIODTO: GUARDA GENERAL  EL USUARIO EN GO-ADMIN-USER-API', 'adminUsersApi');
INSERT INTO parametros.parametros (`key`, value, descripcion, api_name) VALUES ('PATH_SEND_EMAIL', 'send/', 'POST: ENVIA CORRREO ', 'emailApi');
INSERT INTO parametros.parametros (`key`, value, descripcion, api_name) VALUES ('PLANTILLA_CONFIRM_USER_GO_HOME', 'REGISTER_FOR_COMPLETE', 'NOMBRE DE LA PLANTILLA PARA CONFIRMAR LA CREACION DEL USUARIO', null);
INSERT INTO parametros.parametros (`key`, value, descripcion, api_name) VALUES ('URL_GATEWAY', 'http://192.168.1.150:8080', 'URL DEL GATEWAY', null);

