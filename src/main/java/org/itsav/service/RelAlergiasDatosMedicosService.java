package org.itsav.service;

import org.itsav.domain.entities.RelAlergiasDatosMedicos;
import org.itsav.domain.entities.RelAlergiasDatosMedicosId;

import java.util.List;

public interface RelAlergiasDatosMedicosService {
    List<RelAlergiasDatosMedicos> findAll();
    RelAlergiasDatosMedicos findById(RelAlergiasDatosMedicosId id);
}
