package org.itsav.service;

import jakarta.transaction.Transactional;
import org.itsav.domain.entities.Alumnos;
import org.itsav.domain.records.AlumnoRequest;

import java.util.List;
import java.util.UUID;

public interface AlumnosService {
    List<Alumnos> findAll();
    Alumnos findById(UUID id);
    Alumnos addAlumnos(AlumnoRequest req);
    Alumnos findByNumControl(String numControl);
    Alumnos updateAlumnos(UUID id, AlumnoRequest req);
    Alumnos deactivateAlumno(UUID id);
    List<Alumnos> addAlumnosBatch(List<AlumnoRequest> requests);
}
