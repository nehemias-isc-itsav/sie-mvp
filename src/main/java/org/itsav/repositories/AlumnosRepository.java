package org.itsav.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.itsav.domain.entities.Alumnos;

import java.util.UUID;

@ApplicationScoped
public class AlumnosRepository implements PanacheRepositoryBase<Alumnos, UUID> {
}