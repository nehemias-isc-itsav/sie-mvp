package org.itsav.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.itsav.domain.entities.DatosMedicos;

import java.util.UUID;

@ApplicationScoped
public class DatosMedicosRepository implements PanacheRepositoryBase<DatosMedicos, UUID> {
}
