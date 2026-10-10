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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "direccion", nullable = false)
    private Direcciones direccion;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alumno", nullable = false)
    private Alumnos alumno;
}
