package org.itsav.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.itsav.domain.entities.Alumnos;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class AlumnosRepository implements PanacheRepositoryBase<Alumnos, UUID> {
        @SuppressWarnings("unchecked")
        public Alumnos findUltimoConPrefijo(String prefijo) {
            List<Alumnos> resultados = getEntityManager()
                    .createNativeQuery(
                            "SELECT * FROM tbl_alumnos " +
                                    "WHERE num_control LIKE :prefijo " +
                                    "ORDER BY CAST(SUBSTRING(num_control, 6, 4) AS INTEGER) DESC " +
                                    "LIMIT 1",
                            Alumnos.class
                    )
                    .setParameter("prefijo", prefijo + "%")
                    .getResultList();

            return resultados.isEmpty() ? null : resultados.get(0);
        }
}