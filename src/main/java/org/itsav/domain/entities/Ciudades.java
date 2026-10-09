package org.itsav.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "ctl_ciudades",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_ciudades_estado_nombre",
                        columnNames = {"estado", "nombre"}
                )
        }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ciudades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ciudad")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "estado", nullable = false)
    private Long estado;
}