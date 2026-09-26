-- Crear tabla de alumnos
CREATE TABLE tbl_alumnos (
	id_alumno UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	nombre VARCHAR(100) NOT NULL,
	apellido_paterno VARCHAR(50) NULL,
	apellido_materno VARCHAR(50) NOT NULL,
	fecha_nacimiento DATE NOT NULL,
	curp VARCHAR(18) NOT NULL,
	num_telefono_fijo VARCHAR(10) NULL,
	num_telefono_movil VARCHAR(10) NOT NULL,
	correo_personal VARCHAR(100) NULL,
	correo_institucional VARCHAR(100) NULL,
	nombre_tutor VARCHAR(100) NOT NULL,
	contacto_tutor VARCHAR(10) NOT NULL,
	num_control VARCHAR
);

CREATE TABLE ctl_paises(
	id_pais BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, --Para que siempre se genere el id automaticamente
	nombre VARCHAR(50) NOT NULL,
	prefijo VARCHAR(5) NOT NULL
);

CREATE TABLE ctl_estados(
	id_estado BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	nombre VARCHAR(100) NOT NULL,
	id_pais BIGINT,
	CONSTRAINT fk_paises FOREIGN KEY (id_pais) REFERENCES ctl_paises (id_paises) ON DELETE CASCADE
);

CREATE TABLE ctl_ciudades(
	id_ciudad BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	nombre VARCHAR(100) NOT NULL,
	estado BIGINT,
	CONSTRAINT fk_estado_to_ciudades FOREIGN KEY (estado) REFERENCES ctl_paises (id_estado) ON DELETE CASCADE
);

CREATE TABLE tbl_direcciones (
	id_direccion UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	calle VARCHAR(50) NOT NULL,
	numero_ext VARCHAR(10) NOT NULL,
	numero_int VARCHAR(10) NULL,
	colonia VARCHAR(50) NOT NULL,
	cp VARCHAR(5) NOT NULL,
	ciudad BIGINT,
	CONSTRAINT fk_estado_to_ciudades FOREIGN KEY (ciudad) REFERENCES ctl_paises (id_ciudad) ON DELETE CASCADE
);

CREATE TABLE rel_alumnos_direcciones(
	direccion UUID,
	alumno UUID,
	PRIMARY KEY (direccion, alumno)
);

CREATE TABLE tbl_datos_medicos(
	id_dato_medico UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	tipo_sangre VARCHAR(5) NULL,
	alumno UUID,
	CONSTRAINT fk_alumnos_to_datos_medicos FOREIGN KEY (alumno) REFERENCES tbl_alumnos(id_alumno) ON DELETE CASCADE
);

CREATE TABLE ctl_alergias(
	id_alergia INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	nombre VARCHAR(20) NOT NULL,
	descripcion VARCHAR(255) NOT NULL
);
CREATE TABLE ctl_discapacidades_medicas(
	id_discapacidad INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	nombre VARCHAR(20) NOT NULL,
	descripcion VARCHAR(255) NOT NULL
);

CREATE TABLE rel_alergias_datos_medicos(
	alergia INT,
	dato_medico UUID,
	PRIMARY KEY (alergia, dato_medico)
);