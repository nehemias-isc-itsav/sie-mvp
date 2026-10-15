package org.itsav.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.itsav.domain.entities.RelDiscapacidadesDatosMedicos;
import org.itsav.domain.entities.RelDiscapacidadesDatosMedicosId;

@ApplicationScoped
public class RelDiscapacidadesDatosMedicosRepository implements PanacheRepositoryBase<RelDiscapacidadesDatosMedicos, RelDiscapacidadesDatosMedicosId> {
}
