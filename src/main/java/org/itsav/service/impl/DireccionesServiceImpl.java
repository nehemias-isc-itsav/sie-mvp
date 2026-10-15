package org.itsav.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.itsav.domain.entities.Direcciones;
import org.itsav.repositories.DireccionesRepository;
import org.itsav.service.DireccionesService;

import java.util.List;
import java.util.UUID;
@ApplicationScoped
public class DireccionesServiceImpl implements DireccionesService {
    @Inject
    DireccionesRepository direccionesRepository;

    @Override
    public List<Direcciones> findAll() {
        return direccionesRepository.findAll().stream().toList();
    }

    @Override
    public Direcciones findById(UUID id) {
        return direccionesRepository.findById(id);
    }
}