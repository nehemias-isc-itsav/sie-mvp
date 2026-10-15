package org.itsav.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.itsav.domain.entities.RelAlumnosDirecciones;
import org.itsav.domain.entities.RelAlumnosDireccionesId;
import org.itsav.repositories.RelAlumnosDireccionesRepository;
import org.itsav.service.RelAlumnosDireccionesService;

import java.util.List;

@ApplicationScoped
public class RelAlumnosDireccionesServiceImpl implements RelAlumnosDireccionesService {
    @Inject
    RelAlumnosDireccionesRepository relAlumnosDireccionesRepository;

    @Override
    public List<RelAlumnosDirecciones> findAll() {
        return relAlumnosDireccionesRepository.findAll().stream().toList();
    }

    @Override
    public RelAlumnosDirecciones findById(RelAlumnosDireccionesId id) {
        return relAlumnosDireccionesRepository.findById(id);
    }
}
