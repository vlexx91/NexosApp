drop table if exists usuario_desaparicion;
drop table if exists foto_comentario;
drop table if exists foto_persona;
drop table if exists foto_aviso;
drop table if exists comentario;
drop table if exists desaparicion;
drop table if exists autoridad;
drop table if exists civil;
drop table if exists aviso;
drop table if exists usuario;
drop table if exists foto;
drop table if exists persona;
drop table if exists lugar;


create table if not exists lugar(
	id serial primary key,
	provincia varchar(100) not null,
	localidad varchar(200) not null,
	calle varchar(300),
	lat varchar(300),
	lon varchar(300)
);

create table if not exists persona(
	id serial primary key,
	dni char(9) not null,
	nombre varchar(200) not null,
	apellido varchar(500) not null,
	fecha_nacimiento timeStamp(6) not null,
	sexo int not null,
	altura float not null,
	complexion int not null,
	descripcion text not null
);

create table if not exists foto(
	id serial primary key,
	url varchar(2500) not null,
	es_cara bool default(false)
);

create table if not exists usuario(
    id serial primary key,
    usuario varchar(100) not null,
    email varchar(500) not null,
    contrasenya varchar(500) not null,
    rol int not null,
    verificado bool not null default false
);

create table if not exists aviso(
    id serial primary key,
    texto varchar(2000) not null,
    fecha timestamp(6) not null,
    id_usuario int not null,
    constraint fk_usuario_aviso foreign key (id_usuario) references usuario(id)
);

create table if not exists civil(
    id serial primary key,
    dni char(9) not null,
    telefono int not null,
    nombre varchar(200) not null,
    apellido varchar(500) not null,
    id_usuario int not null,
    constraint fk_usuario_civil foreign key (id_usuario) references usuario(id)
);

create table if not exists autoridad(
    id serial primary key,
    identificador varchar(20)not null,
    id_usuario int not null,
    constraint fk_usuario_autoridad foreign key (id_usuario) references usuario(id)
);

create table if not exists desaparicion(
	id serial primary key,
	fecha timeStamp(6) not null,
	descripcion varchar(500) not null,
	estado int,
	aprobada bool default(false),
    eliminada bool default(false),
	id_persona int,
	id_usuario int,
	id_lugar int,
	constraint fk_desaparicion_persona foreign key(id_persona) references persona(id),
	constraint fk_desaparicion_usuario foreign key(id_usuario) references usuario(id),
	constraint fk_desaparicion_lugar foreign key(id_lugar) references lugar(id)
);

create table if not exists comentario(
    id serial primary key,
    texto varchar(2000) not null,
    nombre varchar(200) not null,
    fecha timestamp(6) not null,
    email varchar(200) not null,
    telefono int,
    id_desaparicion int not null,
    constraint fk_desaparicion_comentario foreign key (id_desaparicion) references desaparicion(id)
);

create table if not exists foto_aviso(
    id_foto     int not null,
    id_aviso int not null,
    constraint fk_foto_foto_aviso foreign key (id_foto) references foto(id),
    constraint fk_aviso_foto_aviso foreign key (id_aviso) references aviso(id)
);

create table if not exists foto_persona(
    id_foto     int not null,
    id_persona int not null,
    constraint fk_foto_foto_persona foreign key (id_foto) references foto(id),
    constraint fk_persona_foto_persona foreign key (id_persona) references persona(id)
);


create table if not exists foto_comentario(
    id_foto int not null,
    id_comentario int not null,
    constraint fk_foto_foto_comentario foreign key (id_foto) references foto(id),
    constraint fk_comentario_foto_comentario foreign key (id_comentario) references comentario(id)
);

create table if not exists usuario_desaparicion(
    id_usuario int not null,
    id_desaparicion int not null,
    constraint fk_usuario_usuario_desaparicion foreign key (id_usuario) references usuario(id),
    constraint fk_desaparicion_usuario_desaparicion foreign key (id_desaparicion) references desaparicion(id)
);




