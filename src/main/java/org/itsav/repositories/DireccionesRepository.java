package org.itsav.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.itsav.domain.entities.Direcciones;

import java.util.UUID;

@ApplicationScoped
public class DireccionesRepository implements PanacheRepositoryBase<Direcciones, UUID> {
}
