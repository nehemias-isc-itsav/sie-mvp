package org.itsav.service;

import org.itsav.domain.entities.Direcciones;

import java.util.List;
import java.util.UUID;

public interface DireccionesService {
    List<Direcciones> findAll();
    Direcciones findById(UUID id);
}
