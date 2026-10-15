package org.itsav.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.itsav.repositories.CiudadesRespository;
import org.itsav.domain.entities.Ciudades;
import org.itsav.service.CiudadesService;

import java.util.List;

@ApplicationScoped
public class CiudadesServiceImpl implements CiudadesService {
    @Inject
    CiudadesRespository ciudadesRespository;

    @Override
    public List<Ciudades> findAll() {
        return ciudadesRespository.findAll().stream().toList();
    }

    @Override
    public Ciudades findById(Long id) {
        return ciudadesRespository.findById(id);
    }
}
