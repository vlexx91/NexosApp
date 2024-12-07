-- lugar --
INSERT INTO nexo_app.lugar ( provincia, localidad, calle, lat, lon) VALUES ( 'Madrid', 'Madrid', 'Gran Vía, 14', '40.4197149', '-3.6991374');
INSERT INTO nexo_app.lugar ( provincia, localidad, calle, lat, lon) VALUES ( 'Barcelona', 'Sitges', 'Passeig Marítim, 22', '41.2639887', '1.9387488');
INSERT INTO nexo_app.lugar (provincia, localidad, calle, lat, lon) VALUES ( 'León', 'Ponferrada', 'Avenida de la Puebla, 45', '42.5469955', '-6.5931197');
INSERT INTO nexo_app.lugar ( provincia, localidad, calle, lat, lon) VALUES ( 'Valencia', 'Valencia', 'Calle Colón, 58', '38.5369486', '-0.8223743');
INSERT INTO nexo_app.lugar ( provincia, localidad, calle, lat, lon) VALUES ( 'Sevilla', 'Dos Hermanas', 'Avenida de España, 32', '37.2917231', '-5.9270226');
INSERT INTO nexo_app.lugar ( provincia, localidad, calle, lat, lon) VALUES ( 'Granada', 'Granada', 'Calle Reyes Católicos, 18', '37.1979588', '-4.0460516');
INSERT INTO nexo_app.lugar ( provincia, localidad, calle, lat, lon) VALUES ( 'Cádiz', 'Tarifa', 'Calle Sancho IV El Bravo, 12', '36.0132244', '-5.6028223');
INSERT INTO nexo_app.lugar ( provincia, localidad, calle, lat, lon) VALUES ( 'Málaga', 'Marbella', 'Avenida Ricardo Soriano, 25', '36.5103784', '-4.8904821');
INSERT INTO nexo_app.lugar ( provincia, localidad, calle, lat, lon) VALUES ( 'Alicante', 'Alicante', 'Calle del Teatro, 10', '38.3454266', '-0.4874113');
INSERT INTO nexo_app.lugar ( provincia, localidad, calle, lat, lon) VALUES ('Granada', 'Granada', 'Calle Reyes Católicos, 18', '37.1979588', '-4.0460516');

-- persona --
INSERT INTO nexo_app.persona ( dni, nombre, apellido, fecha_nacimiento, sexo, altura, complexion, descripcion) VALUES ( '71123456A', 'Carlos', 'Fernández', '1985-04-12 00:00:00.000000', 0, 1.75, 1, 'Cabello castaño, ojos marrones, viste chaqueta roja.');
INSERT INTO nexo_app.persona ( dni, nombre, apellido, fecha_nacimiento, sexo, altura, complexion, descripcion) VALUES ( '52893456B', 'Laura', 'Gómez', '1990-06-19 00:00:00.000000', 1, 1.6200000047683716, 3, 'Cabello rubio corto, ojos verdes, llevaba bañador azul.');
INSERT INTO nexo_app.persona ( dni, nombre, apellido, fecha_nacimiento, sexo, altura, complexion, descripcion) VALUES ( '45612378C', 'Miguel', 'Ruiz', '1978-11-03 00:00:00.000000', 0, 1.7999999523162842, 1, 'Cabello negro, barba, lleva mochila verde.');
INSERT INTO nexo_app.persona ( dni, nombre, apellido, fecha_nacimiento, sexo, altura, complexion, descripcion) VALUES ('89712345D', 'Elena', 'Martínez', '1988-09-25 00:00:00.000000', 1, 1.6799999475479126, 0, 'Cabello castaño claro, gafas, viste uniforme de enfermera.');
INSERT INTO nexo_app.persona ( dni, nombre, apellido, fecha_nacimiento, sexo, altura, complexion, descripcion) VALUES ( '32187654E', 'José', 'López', '1962-02-14 00:00:00.000000', 0, 1.7300000190734863, 2, 'Cabello gris, usa bastón, lleva abrigo marrón.');
INSERT INTO nexo_app.persona ( dni, nombre, apellido, fecha_nacimiento, sexo, altura, complexion, descripcion) VALUES ( '65432189F', 'Ana', 'Jiménez', '1994-03-07 00:00:00.000000', 1, 1.649999976158142, 1, 'Cabello negro, coleta, llevaba camiseta azul.');
INSERT INTO nexo_app.persona ( dni, nombre, apellido, fecha_nacimiento, sexo, altura, complexion, descripcion) VALUES ( '74125896G', 'Pablo', 'Moreno', '1983-12-30 00:00:00.000000', 0, 1.7799999713897705, 3, 'Cabello castaño oscuro, gorro verde, lleva mochila negra.');
INSERT INTO nexo_app.persona ( dni, nombre, apellido, fecha_nacimiento, sexo, altura, complexion, descripcion) VALUES ( '58974123H', 'María', 'Castro', '1995-08-16 00:00:00.000000', 1, 1.6299999952316284, 0, 'Cabello rubio largo, vestido rojo, bolso negro.');
INSERT INTO nexo_app.persona ( dni, nombre, apellido, fecha_nacimiento, sexo, altura, complexion, descripcion) VALUES ( '98745632J', 'Diego', 'Romero', '1976-10-21 00:00:00.000000', 0, 1.7000000476837158, 3, 'Cabello canoso, gafas, lleva una chaqueta azul.');
INSERT INTO nexo_app.persona ( dni, nombre, apellido, fecha_nacimiento, sexo, altura, complexion, descripcion) VALUES ( '65432189F', 'Ana', 'Jiménez', '1994-03-07 00:00:00.000000', 1, 1.649999976158142, 1, 'Cabello negro, coleta, llevaba camiseta azul.');
-- foto --
INSERT INTO nexo_app.foto (id, url, es_cara) VALUES ( 'http://res.cloudinary.com/nexo/image/upload/v1731268605/aru4k31upa7c32zp6gjk.jpg', false);
INSERT INTO nexo_app.foto (id, url, es_cara) VALUES ( 'http://res.cloudinary.com/nexo/image/upload/v1731268604/ryc2hxl0efd0brddqxsc.webp', false);
INSERT INTO nexo_app.foto (id, url, es_cara) VALUES ( 'http://res.cloudinary.com/nexo/image/upload/v1731268649/wnqr13xmbrufe7fignif.jpg', false);
INSERT INTO nexo_app.foto (id, url, es_cara) VALUES ( 'http://res.cloudinary.com/nexo/image/upload/v1731268648/hzvv94ctyklo5i6ka7tx.jpg', false);
INSERT INTO nexo_app.foto (id, url, es_cara) VALUES ( 'http://res.cloudinary.com/nexo/image/upload/v1731268685/ocx8p4hpgwpiys1y2yxv.webp', false);
INSERT INTO nexo_app.foto (id, url, es_cara) VALUES ( 'http://res.cloudinary.com/nexo/image/upload/v1731268686/wkiltbrchbkrtfx3izs2.jpg', false);
INSERT INTO nexo_app.foto (id, url, es_cara) VALUES ( 'http://res.cloudinary.com/nexo/image/upload/v1731268792/xjnmod7kep8bonehmbrz.jpg', false);
INSERT INTO nexo_app.foto (id, url, es_cara) VALUES ( 'http://res.cloudinary.com/nexo/image/upload/v1731268792/uk9tpip8vhhtk3nml8om.jpg', false);
INSERT INTO nexo_app.foto (id, url, es_cara) VALUES ( 'http://res.cloudinary.com/nexo/image/upload/v1731268885/irhrrfj8ftyeu1xemqfc.jpg', false);
INSERT INTO nexo_app.foto (id, url, es_cara) VALUES ( 'http://res.cloudinary.com/nexo/image/upload/v1731268884/jfjgb2djtif6m2v0vtg7.webp', false);
INSERT INTO nexo_app.foto (id, url, es_cara) VALUES ( 'http://res.cloudinary.com/nexo/image/upload/v1731268933/eqwzwjqmbjfjqrewoekt.webp', false);
INSERT INTO nexo_app.foto (id, url, es_cara) VALUES ( 'http://res.cloudinary.com/nexo/image/upload/v1731268933/kckzbbvu67lpsswwv3wv.jpg', false);
INSERT INTO nexo_app.foto (id, url, es_cara) VALUES ( 'http://res.cloudinary.com/nexo/image/upload/v1731271213/la1u5atnxxdmkmfqeuvz.webp', false);
INSERT INTO nexo_app.foto (id, url, es_cara) VALUES ( 'http://res.cloudinary.com/nexo/image/upload/v1731271214/m3xxoimkx82udunjjgly.jpg', false);
INSERT INTO nexo_app.foto (id, url, es_cara) VALUES ( 'http://res.cloudinary.com/nexo/image/upload/v1731271269/oc6jvapoiuyw2y3wbnow.webp', false);
INSERT INTO nexo_app.foto (id, url, es_cara) VALUES ( 'http://res.cloudinary.com/nexo/image/upload/v1731271270/yrdhbpjj9syhul70bnhp.jpg', false);
INSERT INTO nexo_app.foto (id, url, es_cara) VALUES ( 'http://res.cloudinary.com/nexo/image/upload/v1731271413/dypvacqywglsojd3f7mk.jpg', false);
INSERT INTO nexo_app.foto (id, url, es_cara) VALUES ( 'http://res.cloudinary.com/nexo/image/upload/v1731271414/e0huoxol2mfuobw2nvqc.jpg', false);
INSERT INTO nexo_app.foto (id, url, es_cara) VALUES ('http://res.cloudinary.com/nexo/image/upload/v1731271480/lsv4gd6j3p2yauvudppe.jpg', false);
INSERT INTO nexo_app.foto (id, url, es_cara) VALUES ( 'http://res.cloudinary.com/nexo/image/upload/v1731271480/dd3ys5ge6obxbvzwxx0c.jpg', false);
INSERT INTO nexo_app.foto (id, url, es_cara) VALUES ( 'http://res.cloudinary.com/nexo/image/upload/v1731273510/e0mdkogujvszxui7qgke.jpg', false);

-- usuario --
INSERT INTO nexo_app.usuario (usuario, email, contrasenya, rol, verificado) VALUES ( 'rafa', 'aa@aa', '1234', 1, true);
INSERT INTO nexo_app.usuario (usuario, email, contrasenya, rol, verificado) VALUES ( 'dani', 'aa@aa', '1234', 1, true);
INSERT INTO nexo_app.usuario (usuario, email, contrasenya, rol, verificado) VALUES ( 'alberto', 'aa@aa', '1234', 1, true);
INSERT INTO nexo_app.usuario (usuario, email, contrasenya, rol, verificado) VALUES ( 'ale', 'aa@aa', '1234', 1, true);
INSERT INTO nexo_app.usuario (usuario, email, contrasenya, rol, verificado) VALUES ( 'nahuel', 'aa@aa', '1234', 1, true);
INSERT INTO nexo_app.usuario (usuario, email, contrasenya, rol, verificado) VALUES ('admin', 'aa@aa', '1234', 0, true);
INSERT INTO nexo_app.usuario (usuario, email, contrasenya, rol, verificado) VALUES ('agente', 'aa@aa', '1234', 2, true);

-- aviso --
insert into aviso(texto,fecha,id_usuario)values('persona vista',CURRENT_TIMESTAMP,1);
-- civil --
INSERT INTO nexo_app.civil ( dni, telefono, nombre, apellido, id_usuario) VALUES ('12345678a', 665126537, 'rafa', 'diez', 1);
INSERT INTO nexo_app.civil ( dni, telefono, nombre, apellido, id_usuario) VALUES ('12345678b', 123456789, 'daniel', 'nuñez', 2);
INSERT INTO nexo_app.civil ( dni, telefono, nombre, apellido, id_usuario) VALUES ('12345678c', 123456789, 'alberto', 'velázquez', 3);
INSERT INTO nexo_app.civil ( dni, telefono, nombre, apellido, id_usuario) VALUES ( '12345678d', 123456789, 'alejandro', 'Martinenghi ', 4);
INSERT INTO nexo_app.civil ( dni, telefono, nombre, apellido, id_usuario) VALUES ( '12345678e', 123456789, 'nahuel', 'castro', 5);

-- autoridad --
INSERT INTO nexo_app.autoridad ( identificador, id_usuario) VALUES ( '123456', 7);

-- desaparicion --
INSERT INTO nexo_app.desaparicion ( fecha, descripcion, estado, aprobada, id_persona, id_usuario, id_lugar) VALUES ( '2024-09-15 00:00:00.000000', 'Persona desaparecida durante una excursión en las montañas.', 0, false, 1, 4, 1);
INSERT INTO nexo_app.desaparicion ( fecha, descripcion, estado, aprobada, id_persona, id_usuario, id_lugar) VALUES ( '2024-08-03 00:00:00.000000', 'Última vez visto cerca de la playa.', 0, false, 2, 3, 2);
INSERT INTO nexo_app.desaparicion ( fecha, descripcion, estado, aprobada, id_persona, id_usuario, id_lugar) VALUES ( '2024-07-21 00:00:00.000000', 'Desaparecido en un paseo por la montaña.', 0, false, 3, 3, 3);
INSERT INTO nexo_app.desaparicion ( fecha, descripcion, estado, aprobada, id_persona, id_usuario, id_lugar) VALUES ( '2024-06-10 00:00:00.000000', 'No regresó de su turno de trabajo.', 0, false, 4, 4, 4);
INSERT INTO nexo_app.desaparicion ( fecha, descripcion, estado, aprobada, id_persona, id_usuario, id_lugar) VALUES ( '2024-05-17 00:00:00.000000', 'Fue visto por última vez en un supermercado.', 0, false, 5, 5, 5);
INSERT INTO nexo_app.desaparicion ( fecha, descripcion, estado, aprobada, id_persona, id_usuario, id_lugar) VALUES ( '2024-04-29 00:00:00.000000', 'Desapareció tras salir a correr.', 0, false, 6, 1, 6);
INSERT INTO nexo_app.desaparicion ( fecha, descripcion, estado, aprobada, id_persona, id_usuario, id_lugar) VALUES ( '2024-03-14 00:00:00.000000', 'Desaparecido en una excursión en la sierra.', 0, false, 7, 5, 7);
INSERT INTO nexo_app.desaparicion ( fecha, descripcion, estado, aprobada, id_persona, id_usuario, id_lugar) VALUES ( '2024-02-25 00:00:00.000000', 'Desapareció tras salir de una fiesta.', 0, false, 8, 3, 8);
INSERT INTO nexo_app.desaparicion ( fecha, descripcion, estado, aprobada, id_persona, id_usuario, id_lugar) VALUES ( '2024-01-18 00:00:00.000000', 'No volvió después de un paseo nocturno.', 0, false, 9, 4, 9);
INSERT INTO nexo_app.desaparicion ( fecha, descripcion, estado, aprobada, id_persona, id_usuario, id_lugar) VALUES ( '2024-04-29 00:00:00.000000', 'Desapareció tras salir a correr.', 0, false, 10, 5, 10);

-- comentario --
INSERT INTO nexo_app.comentario (id, texto, nombre, fecha, email, telefono, id_desaparicion) VALUES (5, 'texto_08f2ff7b7db4', 'nombre_e9d215b74dcc', '2024-11-10 00:00:00.000000', 'email_80b5afd48899', 112343234, 1);
-- foto_aviso --
insert into foto_aviso(id_foto,id_aviso)values(1,1);
-- foto_persona --
INSERT INTO nexo_app.foto_persona (id_foto, id_persona) VALUES (1, 1);
INSERT INTO nexo_app.foto_persona (id_foto, id_persona) VALUES (2, 1);
INSERT INTO nexo_app.foto_persona (id_foto, id_persona) VALUES (3, 2);
INSERT INTO nexo_app.foto_persona (id_foto, id_persona) VALUES (4, 2);
INSERT INTO nexo_app.foto_persona (id_foto, id_persona) VALUES (5, 3);
INSERT INTO nexo_app.foto_persona (id_foto, id_persona) VALUES (6, 3);
INSERT INTO nexo_app.foto_persona (id_foto, id_persona) VALUES (7, 4);
INSERT INTO nexo_app.foto_persona (id_foto, id_persona) VALUES (8, 4);
INSERT INTO nexo_app.foto_persona (id_foto, id_persona) VALUES (9, 5);
INSERT INTO nexo_app.foto_persona (id_foto, id_persona) VALUES (10, 5);
INSERT INTO nexo_app.foto_persona (id_foto, id_persona) VALUES (11, 6);
INSERT INTO nexo_app.foto_persona (id_foto, id_persona) VALUES (12, 6);
INSERT INTO nexo_app.foto_persona (id_foto, id_persona) VALUES (13, 7);
INSERT INTO nexo_app.foto_persona (id_foto, id_persona) VALUES (14, 7);
INSERT INTO nexo_app.foto_persona (id_foto, id_persona) VALUES (15, 8);
INSERT INTO nexo_app.foto_persona (id_foto, id_persona) VALUES (16, 8);
INSERT INTO nexo_app.foto_persona (id_foto, id_persona) VALUES (17, 9);
INSERT INTO nexo_app.foto_persona (id_foto, id_persona) VALUES (18, 9);
INSERT INTO nexo_app.foto_persona (id_foto, id_persona) VALUES (19, 10);
INSERT INTO nexo_app.foto_persona (id_foto, id_persona) VALUES (20, 10);

-- foto_comentario --
INSERT INTO nexo_app.foto_comentario (id_foto, id_comentario) VALUES (24, 5);

-- usuario-desaparicion --

INSERT INTO nexo_app.usuario_desaparicion (id_usuario, id_desaparicion) VALUES (1, 1);
INSERT INTO nexo_app.usuario_desaparicion (id_usuario, id_desaparicion) VALUES (1, 6);
INSERT INTO nexo_app.usuario_desaparicion (id_usuario, id_desaparicion) VALUES (1, 10);
INSERT INTO nexo_app.usuario_desaparicion (id_usuario, id_desaparicion) VALUES (2, 2);
INSERT INTO nexo_app.usuario_desaparicion (id_usuario, id_desaparicion) VALUES (2, 7);
INSERT INTO nexo_app.usuario_desaparicion (id_usuario, id_desaparicion) VALUES (3, 3);
INSERT INTO nexo_app.usuario_desaparicion (id_usuario, id_desaparicion) VALUES (3, 8);
INSERT INTO nexo_app.usuario_desaparicion (id_usuario, id_desaparicion) VALUES (4, 9);
-- inserciones 2
-- lugares
INSERT INTO lugar (provincia, localidad, calle, lat, lon) VALUES
                                                              ('Madrid', 'Madrid', 'Gran Vía 45', '40.42028', '-3.70577'),
                                                              ('Barcelona', 'Barcelona', 'Passeig de Gràcia 58', '41.39123', '2.16489'),
                                                              ('Valencia', 'Valencia', 'Carrer de Colom 12', '39.46975', '-0.37597'),
                                                              ('Sevilla', 'Sevilla', 'Calle Sierpes 17', '37.38863', '-5.99534'),
                                                              ('Bilbao', 'Bilbao', 'Calle Gran Vía 12', '43.26301', '-2.93500'),
                                                              ('Málaga', 'Málaga', 'Calle Larios 6', '36.72126', '-4.42127'),
                                                              ('Zaragoza', 'Zaragoza', 'Paseo de la Independencia 22', '41.64882', '-0.88909'),
                                                              ('Granada', 'Granada', 'Calle Recogidas 25', '37.17651', '-3.59793'),
                                                              ('Alicante', 'Alicante', 'Rambla Méndez Núñez 7', '38.34517', '-0.48149'),
                                                              ('Oviedo', 'Oviedo', 'Calle Uría 10', '43.36191', '-5.84939');

-- personas
INSERT INTO persona (dni, nombre, apellido, fecha_nacimiento, sexo, altura, complexion, descripcion) VALUES
                                                                                                         ('12345678A', 'Carlos', 'Gómez Pérez', '1985-03-14 00:00:00', 1, 1.75, 2, 'Ojos marrones, pelo castaño corto.'),
                                                                                                         ('23456789B', 'Laura', 'Fernández López', '1992-07-23 00:00:00', 2, 1.62, 1, 'Ojos verdes, pelo largo rubio.'),
                                                                                                         ('34567890C', 'Manuel', 'Sánchez García', '1978-11-10 00:00:00', 1, 1.80, 3, 'Ojos negros, calvo.'),
                                                                                                         ('45678901D', 'Ana', 'Martínez Ruiz', '2000-01-01 00:00:00', 2, 1.70, 2, 'Pelo ondulado moreno, delgada.'),
                                                                                                         ('56789012E', 'Javier', 'López Torres', '1988-05-05 00:00:00', 1, 1.82, 2, 'Barba, ojos azules.'),
                                                                                                         ('67890123F', 'Elena', 'Díaz Moreno', '1995-10-20 00:00:00', 2, 1.65, 1, 'Ojos grises, cabello corto.'),
                                                                                                         ('78901234G', 'Raúl', 'Jiménez Suárez', '1973-09-09 00:00:00', 1, 1.78, 3, 'Complexión fuerte, pelo canoso.'),
                                                                                                         ('89012345H', 'Isabel', 'García López', '1980-06-30 00:00:00', 2, 1.72, 2, 'Sonrisa prominente, pelo rizado.'),
                                                                                                         ('90123456I', 'Sergio', 'Pérez Gutiérrez', '1990-12-15 00:00:00', 1, 1.68, 1, 'Lentes, pelo castaño claro.'),
                                                                                                         ('01234567J', 'Marta', 'Rodríguez Díaz', '1998-03-03 00:00:00', 2, 1.75, 1, 'Piercing en la nariz, tatuaje visible.');
