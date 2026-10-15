package org.itsav.service;

import org.itsav.domain.entities.RelDiscapacidadesDatosMedicos;
import org.itsav.domain.entities.RelDiscapacidadesDatosMedicosId;

import java.util.List;

public interface RelDiscapacidadesDatosMedicosService {
    List<RelDiscapacidadesDatosMedicos> findAll();
    RelDiscapacidadesDatosMedicos findById(RelDiscapacidadesDatosMedicosId id);
}
