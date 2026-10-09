package org.itsav.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@IdClass(RelAlumnosDireccionesId.class)
@Table(name = "rel_alumnos_direcciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelAlumnosDirecciones {
    @Id
    @Column(name = "direccion", nullable = false)
    private UUID direccion;

    @Id
    @Column(name = "alumno", nullable = false)
    private UUID alumno;
}
