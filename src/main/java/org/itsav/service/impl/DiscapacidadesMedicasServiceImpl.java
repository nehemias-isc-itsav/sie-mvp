package org.itsav.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.itsav.domain.entities.DiscapacidadesMedicas;
import org.itsav.repositories.DiscapacidadesMedicasRepository;
import org.itsav.service.DiscapacidadesMedicasService;

import java.util.List;

@ApplicationScoped
public class DiscapacidadesMedicasServiceImpl implements DiscapacidadesMedicasService {
    @Inject
    DiscapacidadesMedicasRepository discapacidadesMedicasRepository;

    @Override
    public List<DiscapacidadesMedicas> findAll() {
        return discapacidadesMedicasRepository.findAll().stream().toList();
    }

    @Override
    public DiscapacidadesMedicas findById(Integer id) {
        return discapacidadesMedicasRepository.findById(id);
    }
}
