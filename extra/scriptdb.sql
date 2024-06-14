
-- Definición de la tabla Paises
CREATE TABLE Paises (
    idPais SERIAL PRIMARY KEY,
    nombre VARCHAR(45) NOT NULL
);

-- Definición de la tabla Personas
CREATE TABLE Personas (
    idPersona SERIAL PRIMARY KEY,
    cedula VARCHAR(45),
    nombre VARCHAR(45) NOT NULL,
    apellido VARCHAR(45),
    fechaNacimiento DATE CHECK(fechaNacimiento < CURRENT_DATE),
    idPaisNacionalidad INTEGER,
    FOREIGN KEY (idPaisNacionalidad) REFERENCES Paises(idPais)
);

-- Definición de la tabla Generos
CREATE TABLE Generos (
    idGenero SERIAL PRIMARY KEY,
    nombre VARCHAR(45) NOT NULL,
    descripcion VARCHAR(200)
);

-- Definición de la tabla Editoriales
CREATE TABLE Editoriales (
    idEditorial SERIAL PRIMARY KEY,
    nombre VARCHAR(45) NOT NULL,
    descripcion VARCHAR(200)
);

-- Definición de la tabla Usuarios
CREATE TABLE Usuarios (
    idUsuario INTEGER PRIMARY KEY UNIQUE,
    numeroPrestamos INTEGER DEFAULT 0,
    password VARCHAR(256),
    FOREIGN KEY (idUsuario) REFERENCES Personas(idPersona)
);

-- Definición de la tabla Autor
CREATE TABLE Autores (
    idAutor INTEGER PRIMARY KEY UNIQUE,
    idGeneroPrincipal INTEGER,
    FOREIGN KEY (idAutor) REFERENCES Personas(idPersona),
    FOREIGN KEY (idGeneroPrincipal) REFERENCES Generos(idGenero)
);

-- Definición de la tabla Libros
CREATE TABLE Libros (
    idLibro SERIAL PRIMARY KEY,
    titulo VARCHAR(100),
    sinopsis VARCHAR(200),
    stock INTEGER DEFAULT 0,
    idGenero INTEGER,
    idAutor INTEGER,
    idEditorial INTEGER,
    FOREIGN KEY (idGenero) REFERENCES Generos(idGenero),
    FOREIGN KEY (idAutor) REFERENCES Autores(idAutor),
    FOREIGN KEY (idEditorial) REFERENCES Editoriales(idEditorial)
);

-- Definición de la tabla Prestamos
CREATE TABLE Prestamos (
    idPrestamo SERIAL PRIMARY KEY,
    vigente BOOLEAN,
    fechaPrestamo DATE,
    idUsuario INTEGER,
    idLibro INTEGER,
    FOREIGN KEY (idUsuario) REFERENCES Usuarios(idUsuario),
    FOREIGN KEY (idLibro) REFERENCES Libros(idLibro)
);


--1 funcion
CREATE OR REPLACE FUNCTION librosPorAutor(nombreAutor VARCHAR(45))
RETURNS TABLE (
	titulo VARCHAR(45),
	stock INT,
	sinopsis VARCHAR(200),
	genero VARCHAR(45),
	editorial VARCHAR(45),
	nombre VARCHAR(45),
	apellido VARCHAR(45)
) AS
$$
BEGIN
	RETURN QUERY
	SELECT 	l.titulo,
			l.stock,
			l.sinopsis,
			g.nombre,
			e.nombre,
			p.nombre,
			p.apellido
	FROM libros l
	INNER JOIN autores a USING(idAutor)
	INNER JOIN personas p ON(p.idPersona = a.idAutor)
	INNER JOIN editoriales e USING(idEditorial)
	INNER JOIN generos g USING(idGenero)
	WHERE LOWER(p.nombre) = LOWER(nombreAutor);
	
END;
$$
LANGUAGE plpgsql;

--2 funcion
CREATE OR REPLACE FUNCTION librosPorGenero(nombreGenero VARCHAR(45))
RETURNS TABLE (
	titulo VARCHAR(45),
	stock INT,
	sinopsis VARCHAR(200),
	editorial VARCHAR(45),
	nombre VARCHAR(45),
	apellido VARCHAR(45)
)
AS
$$
BEGIN
	RETURN QUERY
	SELECT 	l.titulo,
			l.stock,
			l.sinopsis,
			e.nombre,
			p.nombre,
			p.apellido
	FROM libros l
	INNER JOIN generos g USING(idGenero)
	INNER JOIN editoriales e USING(idEditorial)
	INNER JOIN autores a USING(idAutor)
	INNER JOIN personas p ON(p.idPersona = a.idAutor)
	WHERE LOWER(g.nombre) = LOWER(nombreGenero);
	
END;
$$
LANGUAGE plpgsql;

--3 procedimiento
CREATE OR REPLACE PROCEDURE insertarPersonaYAutor(
    IN p_cedula VARCHAR(45),
    IN p_nombre VARCHAR(45),
    IN p_apellido VARCHAR(45),
    IN p_fechaNacimiento DATE,
    IN p_idPaisNacionalidad INTEGER,
    IN p_idGeneroPrincipal INTEGER
)
AS $$
DECLARE
	nueva_persona_id INT;
BEGIN
    -- Insertar datos en la tabla Personas
    INSERT INTO Personas (cedula, nombre, apellido, fechaNacimiento, idPaisNacionalidad)
    VALUES (p_cedula, p_nombre, p_apellido, p_fechaNacimiento, p_idPaisNacionalidad)
    RETURNING idPersona INTO nueva_persona_id;
    
    -- Insertar datos en la tabla Autores utilizando el ID de Persona obtenido
    INSERT INTO Autores (idAutor, idGeneroPrincipal)
    VALUES (nueva_persona_id, p_idGeneroPrincipal);
END;
$$
LANGUAGE plpgsql;

--4 procedimiento
CREATE OR REPLACE PROCEDURE insertarPersonaYUsuario(
    IN p_cedula VARCHAR(45),
    IN p_nombre VARCHAR(45),
    IN p_apellido VARCHAR(45),
    IN p_fechaNacimiento DATE,
    IN p_idPaisNacionalidad INTEGER
)
AS $$
DECLARE
	nueva_persona_id INT;
BEGIN
    -- Insertar datos en la tabla Personas
    INSERT INTO Personas (cedula, nombre, apellido, fechaNacimiento, idPaisNacionalidad)
    VALUES (p_cedula, p_nombre, p_apellido, p_fechaNacimiento, p_idPaisNacionalidad)
    RETURNING idPersona INTO nueva_persona_id;
    
    -- Insertar datos en la tabla Autores utilizando el ID de Persona obtenido
    INSERT INTO Usuarios (idUsuario)
    VALUES (nueva_persona_id);
END;
$$
LANGUAGE plpgsql;

--Paises
INSERT INTO paises (nombre) VALUES
('Reino Unido'),  --1 William Shakespeare
('Rusia'),        --2 Leo Tolstoy
('Estados Unidos'), --3 Mark Twain
('Colombia'),     --4 Gabriel García Márquez
('Francia'),      --5 Victor Hugo
('Irlanda'),      --6 James Joyce
('Alemania'),     --7 Johann Wolfgang von Goethe
('Italia'),       --8 Dante Alighieri
('España'),       --9 Miguel de Cervantes
('Japón'),        --10 Haruki Murakami
('Chile'),		  --11
('Nigeria'),	  --12
('Brasil'),		  --13
('Canadá');	 	  --14

--Editoriales
INSERT INTO editoriales (nombre, descripcion) VALUES
('Editorial Alfa', 'Editorial enfocada en publicaciones educativas y científicas.'),
('Editorial Beta', 'Especializada en literatura juvenil y novelas de fantasía.'),   
('Editorial Gamma', 'Publicaciones sobre tecnología y avances científicos.'),
('Editorial Delta', 'Dedicada a la impresión de revistas y artículos periodísticos.'),
('Editorial Epsilon', 'Publicaciones de libros de autoayuda y desarrollo personal.'),
('Editorial Zeta', 'Especializada en libros de historia y biografías.'),
('Editorial Eta', 'Publicaciones sobre arte y diseño gráfico.'),
('Editorial Theta', 'Editorial enfocada en novelas románticas y de misterio.'),
('Editorial Iota', 'Dedicada a la publicación de libros de cocina y gastronomía.'),
('Editorial Kappa', 'Publicaciones de libros de ciencias sociales y filosofía.');

--Géneros
INSERT INTO generos (nombre, descripcion) VALUES
('Ficción', 'Género literario basado en hechos imaginarios.'),
('No Ficción', 'Género literario basado en hechos reales.'),
('Romance', 'Historias centradas en relaciones amorosas.'),
('Ciencia Ficción', 'Historias con base en avances científicos y tecnológicos.'),
('Fantasía', 'Narrativas con elementos mágicos y mundos imaginarios.'),
('Misterio', 'Relatos que giran en torno a la resolución de un enigma o crimen.'),
('Terror', 'Historias diseñadas para causar miedo y suspenso.'),
('Biografía', 'Narraciones sobre la vida de personas reales.'),
('Autobiografía', 'Historias de vida escritas por la persona en cuestión.'),
('Poesía', 'Expresión literaria basada en versos y métrica.'),
('Épico', 'Narrativa que canta las hazañas de unos héroes'),
('Distopía', 'Narrativa que explora la deshumanización de las personas'),
('Desarrollo personal', 'Novelas de autoayuda o superación');

--Personas y Autores
CALL insertarPersonaYAutor('123456789', 'Gabriel', 'García Márquez', '1927-03-06', 4, 5);  -- Colombia, Fantasía
CALL insertarPersonaYAutor('987654321', 'William', 'Shakespeare', '1564-04-26', 1, 1);    -- Reino Unido, Ficción
CALL insertarPersonaYAutor('456789123', 'Leo', 'Tolstoy', '1828-09-09', 2, 3);            -- Rusia, Romance
CALL insertarPersonaYAutor('321654987', 'Mark', 'Twain', '1835-11-30', 3, 6);             -- Estados Unidos, Misterio
CALL insertarPersonaYAutor('741852963', 'Victor', 'Hugo', '1802-02-26', 5, 2);            -- Francia, No Ficción
CALL insertarPersonaYAutor('852963741', 'James', 'Joyce', '1882-02-02', 6, 4);            -- Irlanda, Ciencia Ficción
CALL insertarPersonaYAutor('963852741', 'Johann Wolfgang von', 'Goethe', '1749-08-28', 7, 10); -- Alemania, Poesía
CALL insertarPersonaYAutor('159753486', 'Dante', 'Alighieri', '1265-05-30', 8, 1);        -- Italia, Ficción
CALL insertarPersonaYAutor('357951486', 'Miguel', 'de Cervantes', '1547-09-29', 9, 1);    -- España, Ficción
CALL insertarPersonaYAutor('753159486', 'Haruki', 'Murakami', '1949-01-12', 10, 5);       -- Japón, Fantasía
CALL insertarPersonaYAutor('111222333', 'J.K.', 'Rowling', '1965-07-31', 1, 5);         -- Reino Unido, Fantasía
CALL insertarPersonaYAutor('444555666', 'George', 'R.R. Martin', '1948-09-20', 3, 5);   -- Estados Unidos, Fantasía
CALL insertarPersonaYAutor('777888999', 'Neil', 'Gaiman', '1960-11-10', 1, 5);          -- Reino Unido, Fantasía
CALL insertarPersonaYAutor('000111222', 'Margaret', 'Atwood', '1939-11-18', 14, 4);      -- Canadá, Ciencia Ficción
CALL insertarPersonaYAutor('666777888', 'Isabel', 'Allende', '1942-08-02', 11, 1);      -- Chile, Ficción
CALL insertarPersonaYAutor('999000111', 'Kazuo', 'Ishiguro', '1954-11-08', 1, 2);       -- Reino Unido, No Ficción
CALL insertarPersonaYAutor('222333444', 'Chimamanda', 'Ngozi Adichie', '1977-09-15', 12, 2); -- Nigeria, No Ficción
CALL insertarPersonaYAutor('555666777', 'Elena', 'Ferrante', '1943-04-05', 8, 1);       -- Italia, Ficción
CALL insertarPersonaYAutor('888999000', 'Paulo', 'Coelho', '1947-08-24', 13, 1);         -- Brasil, Ficción
CALL insertarPersonaYAutor('129234381', 'Stephen', 'King', '1947-09-21', 3, 7);         -- Estados Unidos, Terror

--Personas y Usuarios
CALL InsertarPersonaYUsuario('11111111', 'Pedro', 'Gomez', '1985-03-10', 2);
CALL InsertarPersonaYUsuario('22222222', 'María', 'Lopez', '1992-07-21', 3);
CALL InsertarPersonaYUsuario('33333333', 'Luis', 'Martinez', '1978-11-05', 1);
CALL InsertarPersonaYUsuario('44444444', 'Ana', 'Rodriguez', '1980-09-15', 4);
CALL InsertarPersonaYUsuario('55555555', 'Eduardo', 'Ruiz', '1995-04-30', 5);
CALL InsertarPersonaYUsuario('66666666', 'Laura', 'Gonzalez', '1988-12-28', 6);
CALL InsertarPersonaYUsuario('77777777', 'Javier', 'Hernandez', '1990-08-17', 7);
CALL InsertarPersonaYUsuario('88888888', 'Sofia', 'Diaz', '1983-06-25', 8);
CALL InsertarPersonaYUsuario('99999999', 'Daniel', 'Perez', '1975-02-03', 9);
CALL InsertarPersonaYUsuario('12345678', 'Carmen', 'Torres', '1998-10-12', 10);
CALL InsertarPersonaYUsuario('11111111', 'Juan', 'Fernandez', '2000-03-10', 2);
CALL InsertarPersonaYUsuario('22222222', 'Laura', 'Gutierrez', '2001-07-21', 3);

-- Inserción de datos en la tabla Libros
INSERT INTO Libros (titulo, sinopsis, idGenero, idAutor, idEditorial, stock) VALUES
('Harry Potter y la piedra filosofal', 'Un joven descubre que es un mago y asiste a una escuela de magia.', 5, 11, 1, 5),
('Juego de Tronos', 'Luchas de poder en un mundo medieval lleno de intriga.', 11, 12, 2, 3),
('American Gods', 'Un hombre se ve atrapado en una guerra entre dioses antiguos y nuevos.', 5, 13, 3, 3),
('El cuento de la criada', 'Una distopía donde las mujeres son forzadas a reproducirse.', 12, 14, 4, 2),
('Kafka en la orilla', 'Una novela surrealista que mezcla lo real con lo fantástico.', 1, 10, 5, 4),
('La casa de los espíritus', 'Una saga familiar en un contexto de realismo mágico.', 7, 15, 6, 7),
('Nunca me abandones', 'Una historia de amor y desilusión en un internado inglés.', 3, 16, 7, 6),
('Medio sol amarillo', 'Una novela sobre la guerra de Biafra en Nigeria.', 2, 17, 8, 4),
('La amiga estupenda', 'La historia de dos amigas en la Italia de posguerra.', 1, 18, 9, 2),
('El Alquimista', 'Un joven pastor busca un tesoro en las pirámides de Egipto.', 13, 19, 10, 8),
('Cien años de soledad', 'La saga de la familia Buendía en el mítico Macondo.', 1, 1, 2, 2);

select * from personas;