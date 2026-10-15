package org.itsav.service;

import org.itsav.domain.entities.DatosMedicos;

import java.util.List;
import java.util.UUID;

public interface DatosMedicosService {
    List<DatosMedicos> findAll();
    DatosMedicos findById(UUID id);
}
