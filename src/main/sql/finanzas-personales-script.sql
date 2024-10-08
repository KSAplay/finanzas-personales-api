DROP DATABASE IF EXISTS finanzas_personales;
CREATE DATABASE finanzas_personales;
USE finanzas_personales;

DROP USER IF EXISTS 'finanzas_admin'@'localhost';
CREATE USER 'finanzas_admin'@'localhost' IDENTIFIED BY 'FinanzasSeguras2024';
GRANT SELECT, INSERT, UPDATE, DELETE ON finanzas_personales.* TO 'finanzas_admin'@'localhost';
GRANT CREATE, ALTER ON finanzas_personales.* TO 'finanzas_admin'@'localhost';

CREATE TABLE usuarios (
    idUsuario INT AUTO_INCREMENT,
    email VARCHAR(40) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    nombre VARCHAR(50),
    fechaRegistro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT PK_USUARIOS PRIMARY KEY (idUsuario)
);

CREATE TABLE categorias (
    idCategoria INT,
    idUsuario INT,
    nombre VARCHAR(50) UNIQUE NOT NULL,
    CONSTRAINT PK_CATEGORIAS PRIMARY KEY (idCategoria, idUsuario),
    CONSTRAINT FK_CATEGORIAS_USUARIOS FOREIGN KEY (idUsuario)
		REFERENCES usuarios(idUsuario)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE transacciones (
    idTransaccion INT AUTO_INCREMENT,
    tipo ENUM('ingreso', 'gasto') NOT NULL,
    monto DECIMAL(10, 2) NOT NULL,
    fecha DATE NOT NULL,
	idCategoria INT,
    idUsuario INT,
    CONSTRAINT PK_TRANSACCIONES PRIMARY KEY (idTransaccion),
    CONSTRAINT FK_TRANSACCIONES_CATEGORIAS FOREIGN KEY (idCategoria)
		REFERENCES categorias(idCategoria)
        ON DELETE SET NULL
        ON UPDATE CASCADE,
    CONSTRAINT FK_TRANSACCIONES_USUARIOS FOREIGN KEY (idUsuario)
		REFERENCES usuarios(idUsuario)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE presupuestos (
    idPresupuesto INT AUTO_INCREMENT,
    idCategoria INT,
    monto DECIMAL(10, 2) NOT NULL,
    mes VARCHAR(7) NOT NULL,
    idUsuario INT,
    CONSTRAINT PK_PRESUPUESTOS PRIMARY KEY (idPresupuesto),
	CONSTRAINT FK_PRESUPUESTOS_CATEGORIAS FOREIGN KEY (idCategoria)
		REFERENCES categorias(idCategoria)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
	CONSTRAINT FK_PRESUPUESTOS_USUARIOS FOREIGN KEY (idUsuario)
		REFERENCES usuarios(idUsuario)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE reportes (
    idReporte INT AUTO_INCREMENT,
    idUsuario INT,
    desde DATE NOT NULL,
    hasta DATE NOT NULL,
    totalIngresos DECIMAL(10, 2),
    totalGastos DECIMAL(10, 2),
    saldo DECIMAL(10, 2),
    CONSTRAINT PK_REPORTES PRIMARY KEY (idReporte),
    CONSTRAINT FK_REPORTES_USUARIOS FOREIGN KEY (idUsuario)
		REFERENCES usuarios(idUsuario)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

INSERT INTO usuarios (email, password, nombre) VALUES 
('ksaplay@correo.com', '1234', 'KSAplay');

INSERT INTO categorias VALUES 
(1, 1, 'Alimentos'),
(2, 1, 'Transporte'),
(3, 1, 'Entretenimiento'),
(4, 1, 'Salud'),
(5, 1, 'Educación');

INSERT INTO transacciones (tipo, monto, fecha, idCategoria, idUsuario) VALUES 
('ingreso', 1500.00, '2024-08-01', 4, 1),
('gasto', 50.00, '2024-08-02', 1, 1),
('gasto', 75.00, '2024-08-02', 2, 1),
('ingreso', 2000.00, '2024-08-05', 5, 1),
('gasto', 100.00, '2024-08-06', 3, 1),
('gasto', 250.00, '2024-08-10', 4, 1);

INSERT INTO presupuestos (idCategoria, monto, mes) VALUES 
(1, 300.00, '2024-08'),
(2, 150.00, '2024-08'),
(3, 200.00, '2024-08'),
(4, 100.00, '2024-08'),
(5, 250.00, '2024-08');

INSERT INTO reportes (idUsuario, desde, hasta, totalIngresos, totalGastos, saldo) VALUES 
(1, '2024-08-01', '2024-08-31', 1500.00, 50.00, 1450.00),
(1, '2024-08-01', '2024-08-31', 2000.00, 75.00, 1925.00),
(1, '2024-08-01', '2024-08-31', 2000.00, 350.00, 1650.00);