package org.itsav.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.itsav.domain.entities.Estados;

@ApplicationScoped
public class EstadosRepository implements PanacheRepositoryBase<Estados, Long> {
}
