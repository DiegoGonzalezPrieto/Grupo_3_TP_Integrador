DROP DATABASE IF EXISTS banco;

CREATE DATABASE banco;

USE banco;

-- tablas maestras (solo se hace insert al iniciar la BD)

CREATE TABLE nacionalidades (
	id_nacionalidad int primary key,
    nacionalidad varchar(30) unique not null
);

CREATE TABLE provincias (
	id_provincia int primary key,
    provincia varchar(50) unique not null
);

CREATE TABLE localidades (
	id_localidad int primary key,
    id_provincia int  not null,
    localidad varchar(50) unique not null,
    
    foreign key (id_provincia) references provincias(id_provincia)
);

CREATE TABLE tipos_usuario (
	id_tipo_usuario int primary key,
    tipo_usuario varchar(15) UNIQUE
);

CREATE TABLE tipos_cuenta (
	id_tipo_cuenta int primary key,
    tipo_cuenta varchar(25) UNIQUE
);

CREATE TABLE tipos_movimiento (
	id_tipo_movimiento int primary key,
    tipo_movimiento varchar(25) UNIQUE
);

CREATE TABLE estados_prestamo (
	id_estado_prestamo int primary key,
    estado_prestamo varchar(15) UNIQUE
);

-- inserts en tablas maestras

INSERT INTO nacionalidades (id_nacionalidad, nacionalidad) VALUES
(1, 'Argentina'),
(2, 'Paraguaya'),
(3, 'Boliviana'),
(4, 'Chilena'),
(5, 'Brasilera'),
(6, 'Peruana'),
(7, 'Uruguaya');


INSERT INTO provincias (id_provincia, provincia) VALUES
(1, 'Tucumán'),
(2, 'Buenos Aires'),
(3, 'Santa Fe'),
(4, 'Entre Ríos'),
(5, 'Corrientes'),
(6, 'Misiones'),
(7, 'Chaco'),
(8, 'Formosa'),
(9, 'Jujuy'),
(10, 'Salta'),
(11, 'Catamarca'),
(12, 'La Rioja'),
(13, 'San Juan'),
(14, 'Mendoza'),
(15, 'Neuquén'),
(16, 'Río Negro'),
(17, 'Chubut'),
(18, 'Santa Cruz'),
(19, 'Tierra del Fuego'),
(20, 'La Pampa'),
(21, 'Córdoba'),
(22, 'San Luis'),
(23, 'Santiago del Estero');

INSERT INTO localidades (id_localidad, id_provincia, localidad) VALUES
(1, 1, 'San Miguel de Tucumán'),
(2, 2, 'La Plata'),
(3, 3, 'Santa Fe'),
(4, 4, 'Paraná'),
(5, 5, 'Corrientes'),
(6, 6, 'Posadas'),
(7, 7, 'Resistencia'),
(8, 8, 'Formosa'),
(9, 9, 'San Salvador de Jujuy'),
(10, 10, 'Salta'),
(11, 11, 'San Fernando del Valle de Catamarca'),
(12, 12, 'La Rioja'),
(13, 13, 'San Juan'),
(14, 14, 'Mendoza'),
(15, 15, 'Neuquén'),
(16, 16, 'Viedma'),
(17, 17, 'Rawson'),
(18, 18, 'Río Gallegos'),
(19, 19, 'Ushuaia'),
(20, 20, 'Santa Rosa'),
(21, 21, 'Córdoba'),
(22, 22, 'San Luis'),
(23, 23, 'Santiago del Estero');


INSERT INTO tipos_usuario (id_tipo_usuario, tipo_usuario) VALUES
(1, 'Cliente'),
(2, 'Administrador');

INSERT INTO tipos_cuenta (id_tipo_cuenta, tipo_cuenta) VALUES
(1, 'Caja de Ahorro'),
(2, 'Cuenta Corriente');

INSERT INTO tipos_movimiento (id_tipo_movimiento, tipo_movimiento) VALUES
(1, 'Alta de Cuenta'),
(2, 'Alta de Préstamo'),
(3, 'Pago de Préstamo'),
(4, 'Transferencia Acreditada'),
(5, 'Transferencia Debitada');

INSERT INTO estados_prestamo (id_estado_prestamo, estado_prestamo) VALUES
(1, 'Pendiente'),
(2, 'Autorizado'),
(3, 'Rechazado');

-- tablas transaccionales (registran datos de operaciones en el sistema)

CREATE TABLE usuarios (
	id_usuario int primary key auto_increment,
    nombre_usuario varchar(50) UNIQUE NOT NULL,
    pass varchar(20) NOT NULL,
    tipo_usuario int NOT NULL,
    estado_usuario bit DEFAULT 1, -- 1 es activo, 0 baja lógica
    
    FOREIGN KEY (tipo_usuario) REFERENCES tipos_usuario(id_tipo_usuario)
    
);

CREATE TABLE clientes (
	id_cliente int primary key auto_increment,
    id_usuario int NOT NULL,
    dni varchar(10) UNIQUE NOT NULL,
    cuil varchar(15) UNIQUE NOT NULL,
    nombre varchar(15) NOT NULL,
    apellido varchar(25) NOT NULL,
    email varchar(40) NOT NULL,
    telefono varchar(15) NOT NULL,
    genero char(1) NOT NULL,
    id_nacionalidad int NOT NULL,
    fecha_nacimiento date NOT NULL,
    direccion varchar(30) NOT NULL,
    id_provincia int NOT NULL,
    id_localidad int NOT NULL,
    
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario),
    FOREIGN KEY (id_nacionalidad) REFERENCES nacionalidades(id_nacionalidad),
    FOREIGN KEY (id_provincia) REFERENCES provincias(id_provincia),
    FOREIGN KEY (id_localidad) REFERENCES localidades(id_localidad)
);

CREATE TABLE cuentas (
	id_cuenta int primary key auto_increment,
    id_cliente int NOT NULL,
    id_tipo_cuenta int NOT NULL,
    fecha_creacion date NOT NULL,
    numero_cuenta bigint UNIQUE NOT NULL,
    cbu varchar(22) UNIQUE NOT NULL,
    saldo decimal(14, 2) NOT NULL,
    estado_cuenta bit DEFAULT 1,
    
    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente),
    FOREIGN KEY (id_tipo_cuenta) REFERENCES tipos_cuenta(id_tipo_cuenta),
    
    CONSTRAINT CHK_Saldo_Positivo CHECK(saldo>=0)
);

CREATE TABLE movimientos (
	id_movimiento  int primary key auto_increment,
    id_cuenta int NOT NULL,
    id_tipo_movimiento int NOT NULL,
    fecha_movimiento date NOT NULL,
    concepto varchar(50) NOT NULL,
    importe_movimiento decimal(14, 2) NOT NULL,
	
    FOREIGN KEY (id_cuenta) REFERENCES cuentas(id_cuenta),
    FOREIGN KEY (id_tipo_movimiento) REFERENCES tipos_movimiento(id_tipo_movimiento)
);

CREATE TABLE prestamos (
	id_prestamo int primary key auto_increment,
    id_cliente int NOT NULL,
    id_cuenta int NOT NULL,
    fecha_alta_prestamo date NOT NULL,
    importe_prestamo decimal(14, 2) NOT NULL,
    meses_plazo int NOT NULL,
    importe_cuota decimal(14, 2) NOT NULL,
    cantidad_cuotas int NOT NULL,
    id_estado_prestamo int NOT NULL,
    
    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente),
    FOREIGN KEY (id_cuenta) REFERENCES cuentas(id_cuenta),
    FOREIGN KEY (id_estado_prestamo) REFERENCES estados_prestamo(id_estado_prestamo)
);

CREATE TABLE cuotas (
	id_cuota int primary key auto_increment,
    id_prestamo int NOT NULL,
    numero_cuota int NOT NULL,
    monto_pagado decimal(14, 2) NULL,
    fecha_pago date NULL,
    estado_pago bit DEFAULT 0, -- 0 sin pagar, 1 ya pagado
    
    FOREIGN KEY (id_prestamo) REFERENCES prestamos(id_prestamo)
);