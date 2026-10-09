package org.itsav.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@IdClass(RelAlergiasDatosMedicosId.class)
@Table(name = "rel_alergias_datos_medicos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelAlergiasDatosMedicos {
    @Id
    @Column(name = "alergia")
    private Integer alergia;

    @Id
    @Column(name = "dato_medico")
    private UUID datoMedico;
}
