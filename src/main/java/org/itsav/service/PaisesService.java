package org.itsav.service;

import org.itsav.domain.entities.Paises;
import org.itsav.domain.records.PaisRequest;

import java.util.List;

public interface PaisesService {
    List<Paises> findAll();
    Paises findById(Long id);
    Paises addCountry(PaisRequest reqDTO);
}
