package org.itsav.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ctl_paises")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paises {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pais")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50, unique = true)
    private String nombre;

    @Column(name = "prefijo", nullable = false, length = 5)
    private String prefijo;


    public Paises(String nombre, String prefijo) {
        this.nombre = nombre;
        this.prefijo = prefijo;
    }
}
