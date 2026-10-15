package org.itsav.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.itsav.domain.entities.RelDiscapacidadesDatosMedicos;
import org.itsav.domain.entities.RelDiscapacidadesDatosMedicosId;
import org.itsav.repositories.RelDiscapacidadesDatosMedicosRepository;
import org.itsav.service.RelDiscapacidadesDatosMedicosService;

import java.util.List;

@ApplicationScoped
public class RelDiscapacidadesDatosMedicosServiceImpl implements RelDiscapacidadesDatosMedicosService {
    @Inject
    RelDiscapacidadesDatosMedicosRepository relDiscapacidadesDatosMedicosRepository;

    @Override
    public List<RelDiscapacidadesDatosMedicos> findAll() {
        return relDiscapacidadesDatosMedicosRepository.findAll().stream().toList();
    }

    @Override
    public RelDiscapacidadesDatosMedicos findById(RelDiscapacidadesDatosMedicosId id) {
        return relDiscapacidadesDatosMedicosRepository.findById(id);
    }
}
