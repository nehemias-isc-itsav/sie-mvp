package org.itsav.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.itsav.domain.entities.DatosMedicos;
import org.itsav.repositories.DatosMedicosRepository;
import org.itsav.service.DatosMedicosService;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class DatosMedicosServiceImpl implements DatosMedicosService {
    @Inject
    DatosMedicosRepository datosMedicosRepository;

    @Override
    public List<DatosMedicos> findAll() {
        return datosMedicosRepository.findAll().stream().toList();
    }

    @Override
    public DatosMedicos findById(UUID id) {
        return datosMedicosRepository.findById(id);
    }
}
