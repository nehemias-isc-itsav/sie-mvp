package org.itsav.service;

import org.itsav.domain.entities.RelAlumnosDirecciones;
import org.itsav.domain.entities.RelAlumnosDireccionesId;

import java.util.List;

public interface RelAlumnosDireccionesService {
    List<RelAlumnosDirecciones> findAll();
    RelAlumnosDirecciones findById(RelAlumnosDireccionesId id);
}