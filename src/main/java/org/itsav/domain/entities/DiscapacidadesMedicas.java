package org.itsav.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ctl_discapacidades_medicas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscapacidadesMedicas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_discapacidad")
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 50, unique = true)
    private String nombre;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @OneToMany(mappedBy = "discapacidad", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RelDiscapacidadesDatosMedicos> datosMedicos = new HashSet<>();
}
