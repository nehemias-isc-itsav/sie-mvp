package org.itsav.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.itsav.domain.entities.RelAlergiasDatosMedicos;
import org.itsav.domain.entities.RelAlergiasDatosMedicosId;

@ApplicationScoped
public class RelAlergiasDatosMedicosRepository implements PanacheRepositoryBase<RelAlergiasDatosMedicos, RelAlergiasDatosMedicosId > {
}
