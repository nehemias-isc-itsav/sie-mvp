package org.itsav.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.itsav.domain.entities.Alumnos;
import org.itsav.repositories.AlumnosRepository;
import org.itsav.service.AlumnosService;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class AlumnosServiceImpl implements AlumnosService {

    @Inject
    AlumnosRepository alumnosRepository;

    @Override
    public List<Alumnos> findAll() {
        return alumnosRepository.findAll().stream().toList();
    }

    @Override
    public Alumnos findById(UUID id) {
        return alumnosRepository.findById(id);
    }
}