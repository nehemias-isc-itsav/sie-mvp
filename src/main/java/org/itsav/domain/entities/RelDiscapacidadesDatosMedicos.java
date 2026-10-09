package org.itsav.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@IdClass(RelDiscapacidadesDatosMedicosId.class)
@Table(name = "rel_discapacidades_datos_medicos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelDiscapacidadesDatosMedicos {
    @Id
    @Column(name = "discapacidad")
    private Integer discapacidad;

    @Id
    @Column(name = "dato_medico")
    private UUID datoMedico;
}
