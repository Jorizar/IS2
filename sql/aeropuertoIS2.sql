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
('48201151M', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'SergioCelmaSosa',       'personal',     'administrador',       'mañana'),
('12345678Z', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'LauraGarciaLopez',      'financiero',   'personal de equipo', 'tarde'),
('23456789H', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'PabloMartinRuiz',       'incidencias',  'personal de equipo', 'noche'),
('34567890P', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'MartaSanchezDiaz',      'operaciones',  'personal de equipo', 'mañana'),
('45678901K', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'JorgeNavarroGil',       'paneles',      'personal de equipo', 'tarde'),
('56789012Q', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'ElenaTorresVega',       'seguridad',    'personal de equipo', 'noche'),
('67890123T', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'AlvaroPerezMora',       'vuelos',       'personal de equipo', 'mañana'),
('78901234L', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'NuriaCamposSerrano',    'personal',     'personal de equipo', 'tarde'),
('89012345C', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'BeatrizLopezRamos',     'financiero',   'administrador',       'noche'),
('90123456D', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'CarlosMendezOrtega',    'operaciones',  'administrador',       'tarde'),
('11223344A', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'LuciaRomanHerrera',     'paneles',      'personal de equipo', 'mañana'),
('22334455B', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'DavidSuarezFlores',     'seguridad',    'personal de equipo', 'tarde'),
('33445566E', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'IreneCastroNavas',      'vuelos',       'personal de equipo', 'noche'),
('44556677F', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'RaulPrietoSantos',      'incidencias',  'administrador',       'mañana'),
('55667788G', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'ClaudiaReyesDominguez', 'personal',     'personal de equipo', 'noche'),
('66778899J', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'MarioVegaPascual',      'financiero',   'personal de equipo', 'mañana'),
('77889900N', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'SandraMorenoIglesias',  'operaciones',  'personal de equipo', 'tarde'),
('88990011R', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'HectorBravoLuna',       'vuelos',       'personal de equipo', 'noche');
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

/*-------------------------------------------------
	FINANZAS
-------------------------------------------------*/

CREATE TABLE IF NOT EXISTS cuentas_bancarias (
    iban VARCHAR(34) PRIMARY KEY,
    nombre_banco VARCHAR(100) NOT NULL,
    sucursal VARCHAR(50) NOT NULL,
    tipo_cuenta VARCHAR(50) NOT NULL,
    moneda VARCHAR(10) NOT NULL,
    saldo DECIMAL(15, 2) NOT NULL DEFAULT 0.00,
    estado_validacion VARCHAR(20) NOT NULL,
    
    -- Validamos que el IBAN empiece por 2 letras (Ej: ES) y sea coherente
    CONSTRAINT chk_iban_formato CHECK (iban REGEXP '^[A-Z]{2}[0-9A-Z]{13,32}$'),
    -- Restringimos los tipos de cuenta
    CONSTRAINT chk_tipo_cuenta CHECK (tipo_cuenta IN ('Corriente', 'Ahorro', 'Crédito')),
    -- El saldo inicial nunca debería ser menor a 0 al crear la cuenta
    CONSTRAINT chk_saldo_positivo CHECK (saldo >= 0),
    -- Bloqueamos los estados posibles
    CONSTRAINT chk_estado_val CHECK (estado_validacion IN ('PENDIENTE', 'VALIDADA'))
);

CREATE TABLE IF NOT EXISTS registros_contables (
    id_registro VARCHAR(20) PRIMARY KEY,
    cuenta_contable VARCHAR(34) NOT NULL,
    concepto VARCHAR(255) NOT NULL,
    tipo_operacion VARCHAR(20) NOT NULL,
    monto DECIMAL(15, 2) NOT NULL,
    estado_balance BOOLEAN NOT NULL DEFAULT TRUE,
    
    -- Forzamos que el ID siga el patrón que generamos con UUID en Java (REG-XXXXXX)
    CONSTRAINT chk_id_registro CHECK (id_registro LIKE 'REG-%'),
    -- Solo se pueden registrar estos 3 tipos de operaciones
    CONSTRAINT chk_tipo_operacion CHECK (tipo_operacion IN ('INGRESO', 'EGRESO', 'TRANSFERENCIA')),
    -- No se pueden registrar montos negativos
    CONSTRAINT chk_monto_positivo CHECK (monto > 0)
    
    FOREIGN KEY (cuenta_contable) REFERENCES cuentas_bancarias(iban),
);

CREATE TABLE IF NOT EXISTS nominas (
    id_nomina VARCHAR(20) PRIMARY KEY,
    id_empleado VARCHAR(50) NOT NULL,
    iban_cuenta VARCHAR(34) NOT NULL,
    bruto DECIMAL(10, 2) NOT NULL,
    neto DECIMAL(10, 2) NOT NULL,
    fecha_emision DATE NOT NULL,
    estado VARCHAR(20) NOT NULL,
    
    -- Forzamos el patrón del ID (NOM-XXXXX)
    CONSTRAINT chk_id_nomina CHECK (id_nomina LIKE 'NOM-%'),
    -- Lógica de negocio dura: El bruto siempre debe ser mayor al neto
    CONSTRAINT chk_importes_coherentes CHECK (bruto > neto AND neto > 0),
    -- Bloqueamos los estados
    CONSTRAINT chk_estado_nomina CHECK (estado IN ('PENDIENTE', 'PAGADA'))
    
    FOREIGN KEY (id_empleado) REFERENCES empleados(id_empleado) ON DELETE RESTRICT,
    FOREIGN KEY (iban_cuenta) REFERENCES cuentas_bancarias(iban) ON DELETE RESTRICT
);


INSERT INTO cuentas_bancarias (iban, nombre_banco, sucursal, tipo_cuenta, moneda, saldo, estado_validacion) VALUES
('ES1234567890123456789012', 'Banco Santander', 'Oficina Central', 'Corriente', 'EUR', 1500000.50, 'VALIDADA'),
('ES9876543210987654321098', 'BBVA', 'Sucursal T4', 'Ahorro', 'EUR', 500000.00, 'VALIDADA'),
('US1122334455667788990011', 'Chase Bank', 'NY Branch', 'Corriente', 'USD', 250000.00, 'PENDIENTE');

INSERT INTO registros_contables (id_registro, cuenta_contable, concepto, tipo_operacion, monto, estado_balance) VALUES
('REG-A1B2C3', 'ES1234567890123456789012', 'Ingreso por tasas aeroportuarias', 'INGRESO', 45000.00, 1),
('REG-X9Y8Z7', 'ES1234567890123456789012', 'Mantenimiento de pistas', 'EGRESO', 12500.50, 1),
('REG-M4N5P6', 'ES9876543210987654321098', 'Traspaso a fondo de emergencias', 'TRANSFERENCIA', 50000.00, 1);

INSERT INTO nominas (id_nomina, id_empleado, iban_cuenta, bruto, neto, fecha_emision, estado) VALUES
('NOM-111AA', 'EMP-001', 'ES1234567890123456789012', 3500.00, 2800.00, '2024-05-01', 'PAGADA'),
('NOM-222BB', 'EMP-045', 'ES1234567890123456789012', 2800.00, 2240.00, '2024-05-01', 'PAGADA'),
('NOM-333CC', 'EMP-089', 'ES9876543210987654321098', 4200.00, 3360.00, '2024-05-01', 'PAGADA');

-- =========================
-- SUBSISTEMA SEGURIDAD
-- =========================
CREATE TABLE Usuario (
  idUsuario INT NOT NULL,
  nombre VARCHAR(45),
  tipo ENUM('Gestor', 'Empleado', 'Visitante'),
  credencial VARCHAR(45),
  PRIMARY KEY (idUsuario)
) ENGINE=InnoDB;

CREATE TABLE ZonaRestringida (
  idZonaRestringida INT NOT NULL,
  nombreZona VARCHAR(45),
  nivelSeguridad INT CHECK (nivelSeguridad BETWEEN 1 AND 10),
  ubicacion VARCHAR(150),
  PRIMARY KEY (idZonaRestringida)
) ENGINE=InnoDB;

CREATE TABLE PermisoAcceso (
  idPermisoAcceso INT NOT NULL AUTO_INCREMENT,
  fechaInicio DATE,
  fechaFin DATE,
  estado ENUM('Activo', 'Expirado', 'Revocado'),
  Usuario_idUsuario INT NOT NULL,
  ZonaRestringida_idZonaRestringida INT NOT NULL,
  PRIMARY KEY (idPermisoAcceso),
  CONSTRAINT chk_fechas CHECK (fechaFin >= fechaInicio),
  CONSTRAINT fk_usuario
    FOREIGN KEY (Usuario_idUsuario)
    REFERENCES Usuario(idUsuario),
  CONSTRAINT fk_zona
    FOREIGN KEY (ZonaRestringida_idZonaRestringida)
    REFERENCES ZonaRestringida(idZonaRestringida)
) ENGINE=InnoDB;

ALTER TABLE PermisoAcceso
ADD INDEX idx_usuario_zona (Usuario_idUsuario, ZonaRestringida_idZonaRestringida);

INSERT INTO Usuario (idUsuario, nombre, tipo, credencial) VALUES
(12345678, 'Juan Perez',    'Gestor',    'GES001'),
(23456789, 'Laura Martin',  'Empleado',  'EMP002'),
(34567890, 'Carlos Ruiz',   'Empleado',  'EMP003'),
(45678901, 'Ana Torres',    'Visitante', 'VIS004'),
(56789012, 'Miguel Santos', 'Gestor',    'GES005');

INSERT INTO ZonaRestringida (idZonaRestringida, nombreZona, nivelSeguridad, ubicacion) VALUES
(10, 'Pista Norte',          5,  'Zona exterior aeropuerto - acceso vehiculos autorizados'),
(11, 'Torre de Control',     9,  'Edificio principal, planta superior'),
(12, 'Hangar Mantenimiento', 7,  'Area tecnica junto a pista sur'),
(13, 'Sala Servidores',      10, 'Sotano terminal principal, acceso biometrico'),
(14, 'Almacen Combustible',  8,  'Zona aislada perimetral este');

INSERT INTO PermisoAcceso
(fechaInicio, fechaFin, estado, Usuario_idUsuario, ZonaRestringida_idZonaRestringida)
VALUES
('2025-01-01', '2025-12-31', 'Activo',   12345678, 10),
('2025-02-01', '2026-02-01', 'Activo',   23456789, 11),
('2025-03-15', '2025-09-15', 'Expirado', 34567890, 12),
('2025-01-10', '2025-04-10', 'Revocado', 45678901, 13),
('2026-05-01', '2026-11-01', 'Activo',   56789012, 14);

/*-------------------------------------------------
	OPERACIONES (Terminales y Puertas)
-------------------------------------------------*/
CREATE TABLE terminales (
    id_terminal INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    CONSTRAINT chk_nombre_terminal CHECK (nombre REGEXP '^T[1-9]$')
) ENGINE=InnoDB;

CREATE TABLE puertas (
    id_puerta INT AUTO_INCREMENT PRIMARY KEY,
    numero_gate VARCHAR(10) NOT NULL,
    id_terminal INT NOT NULL,
    zona ENUM('Norte', 'Sur', 'Este', 'Oeste') NOT NULL,
    bloqueada BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT chk_numero_gate CHECK (numero_gate REGEXP '^[A-Z][0-9]{1,2}$'),
    CONSTRAINT uq_gate_terminal UNIQUE (numero_gate, id_terminal),
    FOREIGN KEY (id_terminal) REFERENCES terminales(id_terminal) ON DELETE CASCADE
) ENGINE=InnoDB;

INSERT INTO terminales (nombre) VALUES ('T1'), ('T2'), ('T3'), ('T4');
INSERT INTO puertas (numero_gate, id_terminal, zona, bloqueada) VALUES
('A1', 1, 'Norte', FALSE),
('C1', 2, 'Este', FALSE),
('E1', 4, 'Norte', FALSE);

/*-------------------------------------------------
	VUELOS (Versión detallada del colega)
-------------------------------------------------*/
CREATE TABLE vuelos (
    id_vuelo VARCHAR(20) PRIMARY KEY,
    origen VARCHAR(100) NOT NULL,
    destino VARCHAR(100) NOT NULL,
    aerolinea VARCHAR(100) NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    id_operador_creador VARCHAR(50) NOT NULL,
    id_operador_ultimo_modificador VARCHAR(50) NOT NULL,
    fecha_ultima_modificacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_origen_destino CHECK (origen <> destino)
) ENGINE=InnoDB;

INSERT INTO vuelos (id_vuelo, origen, destino, aerolinea, id_operador_creador, id_operador_ultimo_modificador) VALUES
('IB1001', 'Madrid', 'Barcelona', 'Iberia', '48201151M', '48201151M'),
('IB1002', 'Madrid', 'París', 'Iberia', '48201151M', '48201151M'),
('UX2001', 'Madrid', 'Palma de Mallorca', 'Air Europa', '34567890P', '34567890P'),
('UX2005', 'Madrid', 'Nueva York', 'Air Europa', '34567890P', '34567890P');

/*-------------------------------------------------
	VIAJES (Tu parte, vinculada a Vuelos y Puertas)
-------------------------------------------------*/
CREATE TABLE viajes (
    id_viaje VARCHAR(20) PRIMARY KEY,
    fecha_salida DATE NOT NULL,
    hora_salida TIME NOT NULL,
    fecha_llegada DATE NOT NULL,
    hora_llegada TIME NOT NULL,
    id_vuelo VARCHAR(20) NOT NULL,
    id_puerta INT NULL,
    estado ENUM('Programado', 'EnCurso', 'Completado', 'Cancelado') DEFAULT 'Programado',
    FOREIGN KEY (id_vuelo) REFERENCES vuelos(id_vuelo),
    FOREIGN KEY (id_puerta) REFERENCES puertas(id_puerta) ON DELETE SET NULL
) ENGINE=InnoDB;

INSERT INTO viajes (id_viaje, fecha_salida, hora_salida, fecha_llegada, hora_llegada, id_vuelo, id_puerta, estado) VALUES
('VJ-001', '2026-05-01', '10:30:00', '2026-05-01', '12:00:00', 'IB1001', 1, 'Programado'),
('VJ-002', '2026-05-02', '15:00:00', '2026-05-02', '18:45:00', 'UX2005', 3, 'Programado');
