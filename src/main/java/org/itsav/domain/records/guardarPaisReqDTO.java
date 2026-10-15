package org.itsav.domain.records;

import lombok.Data;

//Data
public class guardarPaisReqDTO {
    private String nombre;
    private String prefijo;

    public String getNombre() {
        return nombre;
    }

    public String getPrefijo() {
        return prefijo;
    }
}
