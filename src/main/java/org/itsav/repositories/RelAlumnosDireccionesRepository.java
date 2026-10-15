package org.itsav.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.itsav.domain.entities.RelAlumnosDirecciones;
import org.itsav.domain.entities.RelAlumnosDireccionesId;

@ApplicationScoped
public class RelAlumnosDireccionesRepository implements PanacheRepositoryBase<RelAlumnosDirecciones, RelAlumnosDireccionesId> {
}
