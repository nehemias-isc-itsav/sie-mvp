package org.itsav.service;

import org.itsav.domain.entities.DiscapacidadesMedicas;

import java.util.List;

public interface DiscapacidadesMedicasService {
    List<DiscapacidadesMedicas> findAll();
    DiscapacidadesMedicas findById(Integer id);
}
