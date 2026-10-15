package org.itsav.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.itsav.domain.entities.Estados;
import org.itsav.repositories.EstadosRepository;
import org.itsav.service.EstadosService;

import java.util.List;

@ApplicationScoped
public class EstadosServiceImpl implements EstadosService {

    @Inject
    EstadosRepository estadosRepository;

    @Override
    public List<Estados> findAll() {
        return estadosRepository.findAll().stream().toList();
    }

    @Override
    public Estados findById(Long id) {
        return estadosRepository.findById(id);
    }
}
