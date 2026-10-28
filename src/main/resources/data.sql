INSERT INTO curso (titulo, autor, duracion) VALUES ('Java desde cero', 'Cecilio Álvarez', 40);
INSERT INTO curso (titulo, autor, duracion) VALUES ('Spring Boot', 'Cecilio Álvarez', 30);
INSERT INTO curso (titulo, autor, duracion) VALUES ('JPA e Hibernate', 'Cecilio Álvarez', 25);
INSERT INTO curso (titulo, autor, duracion) VALUES ('Angular', 'Ana García', 35);
INSERT INTO curso (titulo, autor, duracion) VALUES ('Docker y Kubernetes', 'Luis Pérez', 20);

INSERT INTO profesor (dni, nombre, apellidos) VALUES ('12345678Z', 'Cecilio', 'Álvarez Caules');
INSERT INTO profesor (dni, nombre, apellidos) VALUES ('87654321X', 'Ana', 'García López');
INSERT INTO profesor (dni, nombre, apellidos) VALUES ('11223344B', 'Luis', 'Pérez Martín');

INSERT INTO alumno (dni, nombre, apellidos) VALUES ('22334455Y', 'Laura', 'Martínez Gil');
INSERT INTO alumno (dni, nombre, apellidos) VALUES ('33445566F', 'Pablo', 'Sánchez Ruiz');
INSERT INTO alumno (dni, nombre, apellidos) VALUES ('44556677W', 'Elena', 'Torres Vidal');

INSERT INTO imparticion (nombre, fecha_inicio, fecha_fin, curso_id) VALUES ('Java - Edición enero', '2027-01-11', '2027-02-05', 1);
INSERT INTO imparticion (nombre, fecha_inicio, fecha_fin, curso_id) VALUES ('Java - Edición abril', '2027-04-05', '2027-04-30', 1);
INSERT INTO imparticion (nombre, fecha_inicio, fecha_fin, curso_id) VALUES ('Spring Boot - Intensivo', '2027-02-15', '2027-02-26', 2);
