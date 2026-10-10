package org.itsav.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "ctl_estados",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_estados_pais_nombre",
                        columnNames = {"pais", "nombre"}
                )
        }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estados{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pais", nullable = false)
    private Paises pais;
}