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
@Table(name = "tbl_datos_medicos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatosMedicos {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_dato_medico" )
    private UUID id;

    @Column(name = "tipo_sangre", length = 3)
    private String tipoSangre;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "alumno", nullable = false)
    private Alumnos alumno;

    @Column(name = "fecha_creacion", nullable = false)
    private OffsetDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion", nullable = false)
    private OffsetDateTime fechaActualizacion;

    @OneToMany(mappedBy = "datoMedico", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RelAlergiasDatosMedicos> alergias = new HashSet<>();

    @OneToMany(mappedBy = "datoMedico", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RelDiscapacidadesDatosMedicos> discapacidades = new HashSet<>();
}
