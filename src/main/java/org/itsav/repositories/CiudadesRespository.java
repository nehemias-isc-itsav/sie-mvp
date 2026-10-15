package org.itsav.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.itsav.domain.entities.Ciudades;

@ApplicationScoped
public class CiudadesRespository implements PanacheRepositoryBase<Ciudades, Long> {
}
