DROP DATABASE IF EXISTS aeropuertoIS2;
CREATE DATABASE aeropuertoIS2 CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci;
USE aeropuertoIS2;

CREATE TABLE empleados (
    id CHAR(9) PRIMARY KEY,
    password_hash CHAR(64) NOT NULL,
    nombre VARCHAR(30) NOT NULL,
    funcion ENUM('financiero', 'incidencias', 'operaciones', 'paneles', 'personal', 'seguridad', 'vuelos') NOT NULL,
    rol ENUM('personal de equipo', 'administrador') NOT NULL,
    turno ENUM('mañana', 'tarde', 'noche') NOT NULL,
    CONSTRAINT chk_id_dni_formato CHECK (id REGEXP '^[0-9]{8}[A-Z]$'),
    CONSTRAINT chk_nombre_formato CHECK (nombre REGEXP '^[A-ZÁÉÍÓÚÑ][a-zA-ZáéíóúÁÉÍÓÚñÑ]{0,29}$')
);

INSERT INTO empleados (id, password_hash, nombre, funcion, rol, turno) VALUES
('48201151M', '240be518fabd2724ddb6f04eeb2c0692650a5f793b10ddcee95283c72ebf8f93', 'SergioCelmaSosa',       'personal',     'administrador',       'mañana'),
('12345678Z', '3cb7ea0f12fbdabc1fb39a71869f22da26da30bd4d9c6ff4391092f3b4014c97', 'LauraGarciaLopez',      'financiero',   'personal de equipo', 'tarde'),
('23456789H', '3cb7ea0f12fbdabc1fb39a71869f22da26da30bd4d9c6ff4391092f3b4014c97', 'PabloMartinRuiz',       'incidencias',  'personal de equipo', 'noche'),
('34567890P', '3cb7ea0f12fbdabc1fb39a71869f22da26da30bd4d9c6ff4391092f3b4014c97', 'MartaSanchezDiaz',      'operaciones',  'personal de equipo', 'mañana'),
('45678901K', '3cb7ea0f12fbdabc1fb39a71869f22da26da30bd4d9c6ff4391092f3b4014c97', 'JorgeNavarroGil',       'paneles',      'personal de equipo', 'tarde'),
('56789012Q', '3cb7ea0f12fbdabc1fb39a71869f22da26da30bd4d9c6ff4391092f3b4014c97', 'ElenaTorresVega',       'seguridad',    'personal de equipo', 'noche'),
('67890123T', '3cb7ea0f12fbdabc1fb39a71869f22da26da30bd4d9c6ff4391092f3b4014c97', 'AlvaroPerezMora',       'vuelos',       'personal de equipo', 'mañana'),
('78901234L', '3cb7ea0f12fbdabc1fb39a71869f22da26da30bd4d9c6ff4391092f3b4014c97', 'NuriaCamposSerrano',    'personal',     'personal de equipo', 'tarde'),
('89012345C', '240be518fabd2724ddb6f04eeb2c0692650a5f793b10ddcee95283c72ebf8f93', 'BeatrizLopezRamos',     'financiero',   'administrador',       'noche'),
('90123456D', '240be518fabd2724ddb6f04eeb2c0692650a5f793b10ddcee95283c72ebf8f93', 'CarlosMendezOrtega',    'operaciones',  'administrador',       'tarde'),
('11223344A', '3cb7ea0f12fbdabc1fb39a71869f22da26da30bd4d9c6ff4391092f3b4014c97', 'LuciaRomanHerrera',     'paneles',      'personal de equipo', 'mañana'),
('22334455B', '3cb7ea0f12fbdabc1fb39a71869f22da26da30bd4d9c6ff4391092f3b4014c97', 'DavidSuarezFlores',     'seguridad',    'personal de equipo', 'tarde'),
('33445566E', '3cb7ea0f12fbdabc1fb39a71869f22da26da30bd4d9c6ff4391092f3b4014c97', 'IreneCastroNavas',      'vuelos',       'personal de equipo', 'noche'),
('44556677F', '240be518fabd2724ddb6f04eeb2c0692650a5f793b10ddcee95283c72ebf8f93', 'RaulPrietoSantos',      'incidencias',  'administrador',       'mañana'),
('55667788G', '3cb7ea0f12fbdabc1fb39a71869f22da26da30bd4d9c6ff4391092f3b4014c97', 'ClaudiaReyesDominguez', 'personal',     'personal de equipo', 'noche'),
('66778899J', '3cb7ea0f12fbdabc1fb39a71869f22da26da30bd4d9c6ff4391092f3b4014c97', 'MarioVegaPascual',      'financiero',   'personal de equipo', 'mañana'),
('77889900N', '3cb7ea0f12fbdabc1fb39a71869f22da26da30bd4d9c6ff4391092f3b4014c97', 'SandraMorenoIglesias',  'operaciones',  'personal de equipo', 'tarde'),
('88990011R', '3cb7ea0f12fbdabc1fb39a71869f22da26da30bd4d9c6ff4391092f3b4014c97', 'HectorBravoLuna',       'vuelos',       'personal de equipo', 'noche');