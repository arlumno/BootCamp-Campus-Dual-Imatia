DROP TABLE IF EXISTS peliculas_generos 
DROP TABLE IF EXISTS peliculas_actores
DROP TABLE IF EXISTS peliculas_directores
DROP TABLE IF EXISTS premios
DROP TABLE IF EXISTS galardones
DROP TABLE IF EXISTS alquileres 
DROP TABLE IF EXISTS clientes
DROP TABLE IF EXISTS productos
DROP TABLE IF EXISTS formatos
DROP TABLE IF EXISTS peliculas 
DROP TABLE IF EXISTS nacionalidades
DROP TABLE IF EXISTS estudios 
DROP TABLE IF EXISTS generos 
DROP TABLE IF EXISTS actores 
DROP TABLE IF EXISTS directores

CREATE TABLE alquileres (
	id INTEGER IDENTITY PRIMARY KEY, 
	fecha_alquiler DATE NOT NULL,
	fecha_devolucion DATE NOT NULL,
	estado VARCHAR(50) NOT NULL, 
	importe decimal NOT NULL,
	observaciones VARCHAR(255),
	numero_socio INTEGER NOT NULL,	
	codigo_producto INTEGER NOT NULL, 
	);	

CREATE TABLE clientes (
	numero_socio INTEGER IDENTITY PRIMARY KEY, 
	nombre VARCHAR(255) NOT NULL,
	dni VARCHAR(9) NOT NULL UNIQUE,
	direccion VARCHAR(255) NOT NULL, 
	telefono VARCHAR(20) NOT NULL,
	email VARCHAR(50) NOT NULL,
	observaciones VARCHAR(255)
	);

CREATE TABLE productos(
	codigo INTEGER IDENTITY PRIMARY KEY, 
	fecha_alta DATE DEFAULT NOW() NOT NULL,
	observaciones VARCHAR(255),
	id_formato INTEGER NOT NULL,
	ref_pelicula INTEGER NOT NULL
	);	
	
CREATE TABLE formatos(
	id INTEGER IDENTITY PRIMARY KEY, 	
	tipo VARCHAR(50)
	);


CREATE TABLE peliculas(
	ref INTEGER IDENTITY PRIMARY KEY,
	titulo VARCHAR(100) NOT NULL,
	sinopsis LONGVARCHAR NOT NULL,
	fecha DATE NOT NULL,	
	cod_pais varchar(2),
	id_estudio INTEGER,	
	);

CREATE TABLE nacionalidades( cod_pais varchar(2) NOT NULL PRIMARY KEY,
nacionalidad varchar(30) NOT NULL );

CREATE TABLE estudios(
	id INTEGER IDENTITY PRIMARY KEY,
	estudio varchar(30) NOT NULL 
);

CREATE TABLE generos(
	id INTEGER IDENTITY PRIMARY KEY,
	genero varchar(30) NOT NULL 
);

CREATE TABLE peliculas_generos(
	ref_pelicula INTEGER NOT NULL,
	id_genero INTEGER NOT NULL,
	PRIMARY KEY(ref_pelicula, id_genero)
);

CREATE TABLE actores(
	id INTEGER IDENTITY PRIMARY KEY,
	nombre varchar(255) NOT NULL 
);

CREATE TABLE peliculas_actores( 
	ref_pelicula INTEGER NOT NULL,
	id_actor INTEGER NOT NULL,
	PRIMARY KEY(ref_pelicula,id_actor) );


CREATE TABLE directores(
	id INTEGER IDENTITY PRIMARY KEY,
	nombre varchar(255) NOT NULL 
);

CREATE TABLE peliculas_directores(
	ref_pelicula INTEGER NOT NULL,
	id_director INTEGER NOT NULL,
	PRIMARY KEY(ref_pelicula, id_director)
);

CREATE TABLE galardones(
	id INTEGER IDENTITY PRIMARY KEY,
	nombre VARCHAR(50) NOT NULL
);

CREATE TABLE premios(
	puesto varchar(50) NOT NULL,
	edicion varchar(50) NOT NULL,
	categoria varchar(50) NOT NULL,
	id_galardon INTEGER NOT NULL,
	ref_pelicula INTEGER NOT NULL,
	PRIMARY KEY (edicion, categoria, id_galardon)
);


ALTER TABLE alquileres ADD FOREIGN KEY (numero_socio) REFERENCES clientes(numero_socio)
ALTER TABLE alquileres ADD FOREIGN KEY (codigo_producto) REFERENCES productos(codigo)


ALTER TABLE productos ADD FOREIGN KEY (id_formato) REFERENCES formatos(id)	
ALTER TABLE productos ADD FOREIGN KEY (ref_pelicula) REFERENCES peliculas(ref)

ALTER TABLE peliculas ADD CONSTRAINT fk_cosas FOREIGN KEY (cod_pais) REFERENCES nacionalidades(cod_pais)
ALTER TABLE peliculas ADD FOREIGN KEY (id_estudio) REFERENCES estudios(id)

ALTER TABLE peliculas_generos ADD FOREIGN KEY (id_genero) REFERENCES generos(id)
ALTER TABLE peliculas_generos ADD FOREIGN KEY (ref_pelicula) REFERENCES peliculas(ref)

ALTER TABLE peliculas_actores ADD FOREIGN KEY (id_actor) REFERENCES actores(id)
ALTER TABLE peliculas_actores ADD FOREIGN KEY (ref_pelicula) REFERENCES peliculas(ref)

ALTER TABLE peliculas_directores ADD FOREIGN KEY (id_director) REFERENCES directores(id)
ALTER TABLE peliculas_directores ADD FOREIGN KEY (ref_pelicula) REFERENCES peliculas(ref)

ALTER TABLE premios ADD FOREIGN KEY (id_galardon) REFERENCES galardones(id)
ALTER TABLE premios ADD FOREIGN KEY (ref_pelicula) REFERENCES peliculas(ref)

--********************
-- INSERCIONES
--********************
SELECT * FROM PUBLIC.PELICULAS 

INSERT INTO generos (genero) VALUES('miedo');
INSERT INTO generos (genero) VALUES('accion');
INSERT INTO generos (genero) VALUES('comedia');

INSERT INTO nacionalidades (cod_pais,nacionalidad) VALUES('ES','España');
INSERT INTO nacionalidades (cod_pais,nacionalidad) VALUES('FR','Francia');
INSERT INTO nacionalidades (cod_pais,nacionalidad) VALUES('US','Estados Unidos');

INSERT INTO estudios (estudio) VALUES('Warner Brothers');
INSERT INTO estudios (estudio) VALUES('Universals Studios');
INSERT INTO estudios (estudio) VALUES('Dream Works');

INSERT INTO actores (nombre) VALUES('Leonardo di Capio');
INSERT INTO actores (nombre) VALUES('Benedict Cumberbatch');
INSERT INTO actores (nombre) VALUES('Keira Knighley');
INSERT INTO actores (nombre) VALUES('Mads Mikkelsen');

INSERT INTO directores (nombre) VALUES('Scott Derrickson');
INSERT INTO directores (nombre) VALUES('Morten Tyldum');

INSERT INTO galardones (nombre) VALUES('Oscars');
INSERT INTO galardones (nombre) VALUES('Goya');
INSERT INTO galardones (nombre) VALUES('Golden Globes');
INSERT INTO galardones (nombre) VALUES('Golden Globes');

INSERT INTO formatos(tipo) VALUES ('VHS');
INSERT INTO formatos(tipo) VALUES ('DVD');
INSERT INTO formatos(tipo) VALUES ('Blue-Ray');

INSERT INTO peliculas (titulo,sinopsis,fecha) VALUES ('Dc. Strange','--texto muy largo aquí','2018-01-19');
INSERT INTO peliculas (titulo,sinopsis,fecha) VALUES ('Descifrando Enigma','--otro texto muy largo aquí','2016-03-10');


INSERT INTO productos (id_formato,ref_pelicula) VALUES (2,0);
INSERT INTO productos (id_formato,ref_pelicula) VALUES (2,0);
INSERT INTO productos (id_formato,ref_pelicula, observaciones) VALUES (2,0,'disco rallado');
INSERT INTO productos (id_formato,ref_pelicula) VALUES (1,0);

INSERT INTO productos (id_formato,ref_pelicula) VALUES (2,1);
INSERT INTO productos (id_formato,ref_pelicula) VALUES (2,1);


INSERT INTO premios (edicion,categoria,id_galardon,puesto,ref_pelicula) VALUES('2018','mejor actor', 1, 'ganador',0);
INSERT INTO premios (edicion,categoria,id_galardon,puesto,ref_pelicula) VALUES('2020','mejor pelicula', 2, 'ganador',1);
INSERT INTO premios (edicion,categoria,id_galardon,puesto,ref_pelicula) VALUES('2018','mejor banda sonora', 1, 'normido',0);