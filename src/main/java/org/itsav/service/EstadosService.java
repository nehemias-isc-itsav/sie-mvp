package org.itsav.service;

import org.itsav.domain.entities.Estados;

import java.util.List;

public interface EstadosService {
    List<Estados> findAll();
    Estados findById(Long id);
}
