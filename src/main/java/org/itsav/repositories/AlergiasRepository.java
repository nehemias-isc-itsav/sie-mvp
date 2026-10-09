package org.itsav.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.itsav.domain.entities.Alergias;

@ApplicationScoped
public class AlergiasRepository implements PanacheRepositoryBase<Alergias, Integer> {

}
