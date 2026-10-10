package org.itsav.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table (name = "tbl_direcciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Direcciones {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_direccion")
    private UUID id;

    @Column(name = "calle", nullable = false, length = 50)
    private String calle;

    @Column(name = "numero_ext", nullable = false, length = 10)
    private String numExterior;

    @Column(name = "numero_int", length = 10)
    private String numInterior;

    @Column(name = "colonia", nullable = false, length = 50)
    private String colonia;

    @Column(name = "cp", nullable = false, length = 5)
    private String cp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ciudad", nullable = false)
    private Ciudades ciudad;

    @Column(name = "fecha_creacion", nullable = false)
    private OffsetDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion", nullable = false)
    private OffsetDateTime fechaActualizacion;

    @OneToMany(mappedBy = "direccion", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RelAlumnosDirecciones> alumnos = new HashSet<>();
}
