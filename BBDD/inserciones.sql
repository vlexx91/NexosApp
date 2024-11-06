-- lugar --
INSERT INTO lugar(provincia, localidad, calle) VALUES ('Sevilla', 'La Algaba', 'Virgen de Guadalupe, 12');
-- persona --
insert into persona(dni,nombre,apellido,fecha_nacimiento,sexo,altura,complexion,descripcion)values('12345678b','Juan','carmona','1994-01-01 00:00:00',1,1.78,1,'persona perdida');
-- foto --
insert into foto(url) values('https://concepto.de/wp-content/uploads/2018/08/persona-e1533759204552.jpg');
-- usuario --
insert into usuario(usuario,email,contrasenya,rol)values('prueba','prueba@prueba.es','1234',1);
insert into usuario(usuario,email,contrasenya,rol)values('prueba1','prueba1@prueba.es','1234',2);
-- aviso --
insert into aviso(texto,fecha,id_usuario)values('persona vista',CURRENT_TIMESTAMP,1);
-- civil --
insert into civil(dni,telefono,nombre,apellido,id_usuario)values('12345678u',665126537,'manolo','castro',1);
-- autoridad --
insert into autoridad(identificador,id_usuario)values('12345',2);
-- desaparicion --
insert into desaparicion(fecha,descripcion,estado,id_persona,id_usuario,id_lugar)values('2024-06-10 12:34:56','no lo encuentro',1,1,1,1);
-- comentario --
insert into comentario(texto,nombre,fecha,email,telefono,id_desaparicion)values('lo vi','luis',CURRENT_TIMESTAMP,'com@com.com',665126537,1);
-- foto_aviso --
insert into foto_aviso(id_foto,id_aviso)values(1,1);
-- foto_persona --
insert into foto_persona(id_foto,id_persona)values(1,1);
-- foto_comentario --
insert into foto_comentario(id_foto,id_comentario)values(1,1);