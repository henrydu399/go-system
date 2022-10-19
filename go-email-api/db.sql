create or replace table emails
(
    id             bigint auto_increment
        primary key,
    fecha_creacion date         not null,
    fecha_envio    date         null,
    sistema        varchar(100) not null,
    status         varchar(100) not null,
    error_tecnico  text         null,
    de             varchar(150) not null,
    para           varchar(150) null,
    cuerpo         text         not null,
    asunto         varchar(150) not null,
    plantilla      varchar(100) null,
    is_html        bit          null,
    parameters     text         null,
    destinatarios  text         not null
);

grant delete, index, insert, references, select, trigger, update on table emails to goemail;

create or replace table plantillas
(
    id            bigint auto_increment
        primary key,
    name          varchar(100) not null,
    sistema       varchar(100) not null,
    data          blob         not null,
    fecha_creada  date         null,
    fecha_editada date         null
);

grant delete, index, insert, references, select, show view, trigger, update on table plantillas to goemail;

create or replace table users
(
    id                      bigint auto_increment
        primary key,
    email                   varchar(150) not null,
    password                varchar(500) not null,
    server                  varchar(100) not null,
    port                    varchar(6)   not null,
    ttls_enable             bit          not null,
    ssl_enable              bit          null,
    protocole               varchar(100) null,
    socket_factory_fallback bit          null,
    auth_enable             bit          null,
    fecha_creacion          date         null
);

grant delete, index, insert, references, select, trigger, update on table users to goemail;


