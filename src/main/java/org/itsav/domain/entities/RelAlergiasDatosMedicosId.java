package org.itsav.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelAlergiasDatosMedicosId implements Serializable {
    private Integer alergia;
    private UUID datoMedico;
}
