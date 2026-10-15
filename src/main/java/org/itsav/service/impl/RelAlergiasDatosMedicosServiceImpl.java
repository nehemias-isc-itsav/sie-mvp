package org.itsav.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.itsav.domain.entities.RelAlergiasDatosMedicos;
import org.itsav.domain.entities.RelAlergiasDatosMedicosId;
import org.itsav.repositories.RelAlergiasDatosMedicosRepository;
import org.itsav.service.RelAlergiasDatosMedicosService;

import java.util.List;
@ApplicationScoped
public class RelAlergiasDatosMedicosServiceImpl implements RelAlergiasDatosMedicosService {
    @Inject
    RelAlergiasDatosMedicosRepository relAlergiasDatosMedicosRepository;
    @Override
    public List<RelAlergiasDatosMedicos> findAll() {
        return relAlergiasDatosMedicosRepository.findAll().stream().toList();
    }

    @Override
    public RelAlergiasDatosMedicos findById(RelAlergiasDatosMedicosId id) {
        return relAlergiasDatosMedicosRepository.findById(id);
    }
}
