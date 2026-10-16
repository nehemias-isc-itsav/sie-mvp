package org.itsav.domain.records;

import java.time.LocalDate;

public record AlumnoRequest(
        String nombre,
        String apellidoPaterno,
        String apellidoMaterno,
        LocalDate fechaNacimiento,
        String curp,
        String telefonoFijo,
        String telefonoMovil,
        String correoPersonal,
        String correoInstitucional,
        String tutor,
        String telefonoTutor) {
}
