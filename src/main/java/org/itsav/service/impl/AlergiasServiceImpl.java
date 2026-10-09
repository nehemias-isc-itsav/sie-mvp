package org.itsav.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.itsav.domain.entities.Alergias;
import org.itsav.repositories.AlergiasRepository;
import org.itsav.service.AlergiasService;

import java.util.List;

@ApplicationScoped
public class AlergiasServiceImpl implements AlergiasService {

    @Inject
    AlergiasRepository alergiasRepository;

    @Override
    public List<Alergias> findAll() {
        return alergiasRepository.findAll().stream().toList();
    }

    @Override
    public Alergias findById(Integer id) {
        return alergiasRepository.findById(id);
    }
}
