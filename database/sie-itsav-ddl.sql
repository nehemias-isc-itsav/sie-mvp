-- ============================================================================
-- SIE ITSAV - DDL (PostgreSQL / Neon)
-- Convenciones: tbl_ = datos, ctl_ = catálogos, rel_ = tablas puente (muchos a muchos)
-- Leyenda de comentarios:  [NUEVO] = se agregó   [MOD] = se modificó lo que ya existía
-- ============================================================================

-- [NUEVO] Función única para actualizar fecha_actualizacion en cada UPDATE (evita repetir la lógica en la app)
CREATE OR REPLACE FUNCTION fn_set_fecha_actualizacion() RETURNS TRIGGER AS $$
BEGIN
    NEW.fecha_actualizacion = now();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


-- ============================================================================
-- CATÁLOGOS GEOGRÁFICOS
-- ============================================================================

CREATE TABLE IF NOT EXISTS ctl_paises (
    id_pais BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre  VARCHAR(50) NOT NULL,
    prefijo VARCHAR(5)  NOT NULL,
    CONSTRAINT uq_paises_nombre UNIQUE (nombre)                    -- [NUEVO] evita países repetidos
);

CREATE TABLE IF NOT EXISTS ctl_estados (
    id_estado BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre    VARCHAR(100) NOT NULL,
    pais      BIGINT NOT NULL,                                     -- [MOD] NOT NULL: un estado sin país no tiene sentido
    -- [MOD] Sin ON DELETE CASCADE: borrar un catálogo no debe borrar en cadena las direcciones de los alumnos
    CONSTRAINT fk_paises_to_estados FOREIGN KEY (pais) REFERENCES ctl_paises (id_pais),
    CONSTRAINT uq_estados_pais_nombre UNIQUE (pais, nombre)        -- [NUEVO] sin estados repetidos por país (también sirve de índice por pais)
);

CREATE TABLE IF NOT EXISTS ctl_ciudades (
    id_ciudad BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre    VARCHAR(100) NOT NULL,
    estado    BIGINT NOT NULL,                                     -- [MOD] NOT NULL
    CONSTRAINT fk_estados_to_ciudades FOREIGN KEY (estado) REFERENCES ctl_estados (id_estado),   -- [MOD] sin CASCADE
    CONSTRAINT uq_ciudades_estado_nombre UNIQUE (estado, nombre)   -- [NUEVO] sin ciudades repetidas por estado (también sirve de índice por estado)
);


-- ============================================================================
-- ALUMNOS
-- ============================================================================

CREATE TABLE IF NOT EXISTS tbl_alumnos (
    id_alumno            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nombre               VARCHAR(100) NOT NULL,
    apellido_paterno     VARCHAR(100) NOT NULL,
    apellido_materno     VARCHAR(100) NULL,
    fecha_nacimiento     DATE NOT NULL,
    curp                 VARCHAR(18)  NOT NULL,
    num_telefono_fijo    VARCHAR(10)  NULL,
    num_telefono_movil   VARCHAR(10)  NOT NULL,
    correo_personal      VARCHAR(100) NULL,
    correo_institucional VARCHAR(100) NULL,
    nombre_tutor         VARCHAR(100) NOT NULL,
    contacto_tutor       VARCHAR(10)  NOT NULL,
    num_control          VARCHAR(9)   NULL,
    activo               BOOLEAN NOT NULL DEFAULT TRUE,            -- [NUEVO] baja lógica: no se borra el historial del alumno
    fecha_creacion       TIMESTAMPTZ NOT NULL DEFAULT now(),       -- [NUEVO] auditoría
    fecha_actualizacion  TIMESTAMPTZ NOT NULL DEFAULT now(),       -- [NUEVO] auditoría (la actualiza el trigger)

    -- [NUEVO] Validaciones básicas para no guardar datos basura
    CONSTRAINT ck_alumnos_curp CHECK (curp ~ '^[A-Z]{4}[0-9]{6}[HM][A-Z]{5}[A-Z0-9][0-9]$'),   -- formato oficial de CURP (en mayúsculas)
    CONSTRAINT ck_alumnos_tel_fijo CHECK (num_telefono_fijo IS NULL OR num_telefono_fijo ~ '^[0-9]{10}$'),
    CONSTRAINT ck_alumnos_tel_movil CHECK (num_telefono_movil ~ '^[0-9]{10}$'),
    CONSTRAINT ck_alumnos_contacto_tutor CHECK (contacto_tutor ~ '^[0-9]{10}$'),
    CONSTRAINT ck_alumnos_correo_personal CHECK (correo_personal IS NULL OR correo_personal LIKE '%_@_%'),
    CONSTRAINT ck_alumnos_correo_inst CHECK (correo_institucional IS NULL OR correo_institucional LIKE '%_@_%')
);

-- [NUEVO] Un alumno no puede repetirse por CURP, número de control ni correo institucional.
-- En num_control y correo_institucional el índice es parcial (solo filas con valor): permite varios NULL y pesa menos.
CREATE UNIQUE INDEX IF NOT EXISTS uq_alumnos_curp        ON tbl_alumnos (curp);
CREATE UNIQUE INDEX IF NOT EXISTS uq_alumnos_num_control ON tbl_alumnos (num_control)          WHERE num_control IS NOT NULL;
CREATE UNIQUE INDEX IF NOT EXISTS uq_alumnos_correo_inst ON tbl_alumnos (correo_institucional) WHERE correo_institucional IS NOT NULL;

-- [NUEVO] Trigger que mantiene fecha_actualizacion
DROP TRIGGER IF EXISTS trg_alumnos_fecha_actualizacion ON tbl_alumnos;
CREATE TRIGGER trg_alumnos_fecha_actualizacion
    BEFORE UPDATE ON tbl_alumnos
    FOR EACH ROW EXECUTE FUNCTION fn_set_fecha_actualizacion();


-- ============================================================================
-- DIRECCIONES
-- ============================================================================

CREATE TABLE IF NOT EXISTS tbl_direcciones (
    id_direccion        UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    calle               VARCHAR(50) NOT NULL,
    numero_ext          VARCHAR(10) NOT NULL,
    numero_int          VARCHAR(10) NULL,
    colonia             VARCHAR(50) NOT NULL,
    cp                  VARCHAR(5)  NOT NULL,
    ciudad              BIGINT NOT NULL,                           -- [MOD] NOT NULL: toda dirección pertenece a una ciudad
    fecha_creacion      TIMESTAMPTZ NOT NULL DEFAULT now(),        -- [NUEVO] auditoría
    fecha_actualizacion TIMESTAMPTZ NOT NULL DEFAULT now(),        -- [NUEVO] auditoría
    CONSTRAINT fk_ciudades_to_direcciones FOREIGN KEY (ciudad) REFERENCES ctl_ciudades (id_ciudad),   -- [MOD] sin CASCADE
    CONSTRAINT ck_direcciones_cp CHECK (cp ~ '^[0-9]{5}$')         -- [NUEVO] código postal de 5 dígitos
);

-- [NUEVO] Postgres NO indexa las llaves foráneas por sí solo; este índice acelera los JOIN y filtros por ciudad
CREATE INDEX IF NOT EXISTS idx_direcciones_ciudad ON tbl_direcciones (ciudad);

-- [NUEVO] Trigger de fecha_actualizacion
DROP TRIGGER IF EXISTS trg_direcciones_fecha_actualizacion ON tbl_direcciones;
CREATE TRIGGER trg_direcciones_fecha_actualizacion
    BEFORE UPDATE ON tbl_direcciones
    FOR EACH ROW EXECUTE FUNCTION fn_set_fecha_actualizacion();

CREATE TABLE IF NOT EXISTS rel_alumnos_direcciones (
    direccion UUID NOT NULL,
    alumno    UUID NOT NULL,
    PRIMARY KEY (direccion, alumno),
    -- [NUEVO] Faltaban las llaves foráneas: sin ellas se podían guardar relaciones a registros inexistentes.
    -- CASCADE es seguro aquí: solo borra la relación cuando desaparece el alumno o la dirección.
    CONSTRAINT fk_direcciones_to_rel_alumnos FOREIGN KEY (direccion) REFERENCES tbl_direcciones (id_direccion) ON DELETE CASCADE,
    CONSTRAINT fk_alumnos_to_rel_direcciones FOREIGN KEY (alumno)    REFERENCES tbl_alumnos (id_alumno)        ON DELETE CASCADE
);

-- [NUEVO] La PK ya cubre búsquedas por dirección; este índice cubre "direcciones de un alumno"
CREATE INDEX IF NOT EXISTS idx_rel_alumnos_direcciones_alumno ON rel_alumnos_direcciones (alumno);


-- ============================================================================
-- DATOS MÉDICOS
-- ============================================================================

CREATE TABLE IF NOT EXISTS tbl_datos_medicos (
    id_dato_medico      UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tipo_sangre         VARCHAR(3) NULL,                           -- [MOD] 5 -> 3 caracteres: el valor más largo es "AB+"
    alumno              UUID NOT NULL,                             -- [MOD] NOT NULL: un dato médico siempre es de un alumno
    fecha_creacion      TIMESTAMPTZ NOT NULL DEFAULT now(),        -- [NUEVO] auditoría
    fecha_actualizacion TIMESTAMPTZ NOT NULL DEFAULT now(),        -- [NUEVO] auditoría
    CONSTRAINT fk_alumnos_to_datos_medicos FOREIGN KEY (alumno) REFERENCES tbl_alumnos (id_alumno) ON DELETE CASCADE,
    CONSTRAINT uq_datos_medicos_alumno UNIQUE (alumno),            -- [NUEVO] un solo registro médico por alumno (también sirve de índice por alumno)
    CONSTRAINT ck_datos_medicos_tipo_sangre CHECK (tipo_sangre IS NULL OR tipo_sangre IN ('A+','A-','B+','B-','AB+','AB-','O+','O-'))   -- [NUEVO] solo tipos válidos
);

-- [NUEVO] Trigger de fecha_actualizacion
DROP TRIGGER IF EXISTS trg_datos_medicos_fecha_actualizacion ON tbl_datos_medicos;
CREATE TRIGGER trg_datos_medicos_fecha_actualizacion
    BEFORE UPDATE ON tbl_datos_medicos
    FOR EACH ROW EXECUTE FUNCTION fn_set_fecha_actualizacion();

CREATE TABLE IF NOT EXISTS ctl_alergias (
    id_alergia  INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre      VARCHAR(50)  NOT NULL,                             -- [MOD] 20 -> 50: 20 se queda corto para nombres como "Alergia a frutos secos"
    descripcion VARCHAR(255) NOT NULL,
    CONSTRAINT uq_alergias_nombre UNIQUE (nombre)                  -- [NUEVO] sin alergias repetidas en el catálogo
);

CREATE TABLE IF NOT EXISTS ctl_discapacidades_medicas (
    id_discapacidad INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre          VARCHAR(50)  NOT NULL,                         -- [MOD] 20 -> 50 (mismo motivo que alergias)
    descripcion     VARCHAR(255) NOT NULL,
    CONSTRAINT uq_discapacidades_nombre UNIQUE (nombre)            -- [NUEVO] sin discapacidades repetidas en el catálogo
);

CREATE TABLE IF NOT EXISTS rel_alergias_datos_medicos (
    alergia     INT  NOT NULL,
    dato_medico UUID NOT NULL,
    PRIMARY KEY (alergia, dato_medico),
    -- [NUEVO] Faltaban las llaves foráneas. Al borrar el dato médico se borran sus alergias,
    -- pero NO se puede borrar del catálogo una alergia que esté en uso.
    CONSTRAINT fk_alergias_to_rel_datos_medicos FOREIGN KEY (alergia)     REFERENCES ctl_alergias (id_alergia),
    CONSTRAINT fk_datos_medicos_to_rel_alergias FOREIGN KEY (dato_medico) REFERENCES tbl_datos_medicos (id_dato_medico) ON DELETE CASCADE
);

-- [NUEVO] La PK cubre búsquedas por alergia; este índice cubre "alergias de un dato médico"
CREATE INDEX IF NOT EXISTS idx_rel_alergias_dato_medico ON rel_alergias_datos_medicos (dato_medico);

CREATE TABLE IF NOT EXISTS rel_discapacidades_datos_medicos (
    discapacidad INT  NOT NULL,
    dato_medico  UUID NOT NULL,
    PRIMARY KEY (discapacidad, dato_medico),
    -- [NUEVO] Faltaban las llaves foráneas (misma lógica que alergias)
    CONSTRAINT fk_discapacidades_to_rel_datos_medicos FOREIGN KEY (discapacidad) REFERENCES ctl_discapacidades_medicas (id_discapacidad),
    CONSTRAINT fk_datos_medicos_to_rel_discapacidades FOREIGN KEY (dato_medico)  REFERENCES tbl_datos_medicos (id_dato_medico) ON DELETE CASCADE
);

-- [NUEVO] La PK cubre búsquedas por discapacidad; este índice cubre "discapacidades de un dato médico"
CREATE INDEX IF NOT EXISTS idx_rel_discapacidades_dato_medico ON rel_discapacidades_datos_medicos (dato_medico);