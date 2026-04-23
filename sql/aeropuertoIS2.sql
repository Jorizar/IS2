DROP DATABASE IF EXISTS aeropuertoIS2;
CREATE DATABASE aeropuertoIS2 CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci;
USE aeropuertoIS2;

/*-------------------------------------------------
	EMPLEADOS
-------------------------------------------------*/
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

/*-------------------------------------------------
	INCIDENCIAS
-------------------------------------------------*/
CREATE TABLE incidencias (
    idIncidencia CHAR(9) PRIMARY KEY, 
    tipo VARCHAR(10) NOT NULL, 
    estado VARCHAR(20) NOT NULL, 
    fecha DATETIME NOT NULL, 
    descrip VARCHAR(200), 
    causa VARCHAR(100) NOT NULL,

    -- id con exactamante 9 dig (del 0 al 9)
    CONSTRAINT chk_id_formato CHECK (idIncidencia REGEXP '^[0-9]{9}$'),
    CONSTRAINT chk_tipo CHECK (tipo IN ('General', 'Personal')),
    CONSTRAINT chk_estado CHECK (estado IN ('Abierta', 'Asignada', 'En progreso', 'Cerrada', 'Cancelada'))
);

INSERT INTO incidencias (idIncidencia, tipo, estado, fecha, descrip, causa) VALUES
('100000001', 'General', 'Cerrada', '2026-04-15 08:30:00', 'Fuga de agua en los baños de la Terminal 2, puerta D.', 'Rotura de cañería principal'),
('100000002', 'Personal', 'En progreso', '2026-04-18 09:15:00', 'Un pasajero ha reportado la pérdida de su equipaje en bodega.', 'Pérdida de equipaje'),
('100000003', 'General', 'Abierta', '2026-04-20 14:45:00', 'Cinta de recogida de equipajes número 4 atascada.', 'Sobrecarga de maletas pesadas'),
('100000004', 'General', 'Asignada', '2026-04-21 10:10:00', 'Panel de información de vuelos de la puerta B12 apagado.', 'Fallo eléctrico localizado'),
('100000005', 'Personal', 'Abierta', '2026-04-21 11:20:00', 'Operario de pista reporta falta de equipo de protección (EPI).', 'Retraso en entrega de almacén'),
('100000006', 'General', 'Cancelada', '2026-04-19 16:00:00', 'Falsa alarma de incendios en la zona de embarque VIP.', 'Activación accidental por usuario'),
('100000007', 'General', 'En progreso', '2026-04-21 12:05:00', 'Puerta automática de acceso principal bloqueada a la mitad.', 'Fallo en el sensor de movimiento'),
('100000008', 'Personal', 'Cerrada', '2026-04-10 07:00:00', 'Conflicto de cuadrantes: dos pilotos asignados al mismo descanso.', 'Error en el software de turnos'),
('100000009', 'General', 'Asignada', '2026-04-21 15:30:00', 'Se produce un retraso en el vuelo UX9043.', 'Causas meteorológicas'),
('100000010', 'General', 'Abierta', '2026-04-21 16:45:00', 'Tripulación del vuelo IB3442 llega tarde al embarque  por atasco.', 'Tráfico externo al aeropuerto');

