package org.itsav.service;

import org.itsav.domain.entities.Ciudades;

import java.util.List;

public interface CiudadesService {
    List<Ciudades> findAll();
    Ciudades findById(Long id);
}
