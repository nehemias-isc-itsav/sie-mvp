package org.itsav.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tbl_alumnos")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Alumnos {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_alumno")
    private UUID id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "apellido_paterno", nullable = false, length = 100)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", length = 100)
    private String apellidoMaterno;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "curp", nullable = false, length = 18, unique = true)
    private String curp;

    @Column(name = "num_telefono_fijo", length = 10)
    private String telefonoFijo;

    @Column(name = "num_telefono_movil", nullable = false, length = 10)
    private String telefonoMovil;

    @Column(name = "correo_personal", length = 100)
    private String correoPersonal;

    @Column(name = "correo_institucional", length = 100)
    private String correoInstitucional;

    @Column(name = "nombre_tutor", nullable = false, length = 100)
    private String tutor;

    @Column(name = "contacto_tutor", nullable = false, length = 10)
    private String telefonoTutor;

    @Column(name = "num_control", length = 9)
    private String numControl;

    @Column(name = "activo", nullable = false)
    private Boolean status;

    @Column(name = "fecha_creacion", nullable = false)
    private OffsetDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion", nullable = false)
    private OffsetDateTime fechaActualizacion;

    @OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<RelAlumnosDirecciones> direcciones = new HashSet<>();
}
