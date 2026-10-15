package org.itsav.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.itsav.domain.entities.DiscapacidadesMedicas;

@ApplicationScoped
public class DiscapacidadesMedicasRepository implements PanacheRepositoryBase<DiscapacidadesMedicas, Integer> {
}
