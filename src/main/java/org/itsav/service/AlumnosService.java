package org.itsav.service;

import org.itsav.domain.entities.Alumnos;

import java.util.List;
import java.util.UUID;

public interface AlumnosService {
    List<Alumnos> findAll();
    Alumnos findById(UUID id);
}
