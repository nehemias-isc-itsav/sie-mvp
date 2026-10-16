package org.itsav.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.itsav.domain.entities.Alumnos;
import org.itsav.domain.records.AlumnoRequest;
import org.itsav.repositories.AlumnosRepository;
import org.itsav.service.AlumnosService;
import org.itsav.utils.MatriculaGenerator;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class AlumnosServiceImpl implements AlumnosService {

    @Inject
    AlumnosRepository alumnosRepository;


    @Override
    public List<Alumnos> findAll() {
        return alumnosRepository.find("status", true).list();
    }

    @Override
    public Alumnos findById(UUID id) {
        return alumnosRepository.findById(id);
    }

    @Transactional
    @Override
    public Alumnos addAlumnos(AlumnoRequest req) {
        if (req == null) {
            throw new IllegalArgumentException("Los datos del alumno son requeridos.");
        }
        if (req.nombre() == null || req.nombre().isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }
        if (req.apellidoPaterno() == null || req.apellidoPaterno().isBlank()) {
            throw new IllegalArgumentException("El apellido paterno es obligatorio.");
        }
        if (req.fechaNacimiento() == null) {
            throw new IllegalArgumentException("La fecha de nacimiento es obligatoria.");
        }
        if (req.curp() == null || req.curp().isBlank()) {
            throw new IllegalArgumentException("La CURP es obligatoria.");
        }
        if (req.telefonoMovil() == null || req.telefonoMovil().isBlank()) {
            throw new IllegalArgumentException("El teléfono móvil es obligatorio.");
        }
        if (req.tutor() == null || req.tutor().isBlank()) {
            throw new IllegalArgumentException("El nombre del tutor es obligatorio.");
        }
        if (req.telefonoTutor() == null || req.telefonoTutor().isBlank()) {
            throw new IllegalArgumentException("El contacto del tutor es obligatorio.");
        }

        String prefijo = MatriculaGenerator.generarPrefijo();

        Alumnos ultimoRegistro = alumnosRepository
                .find("numControl like ?1 order by numControl desc", prefijo + "%")
                .firstResult();

        int ultimoSecuencial = MatriculaGenerator.extraerSecuencial(
                ultimoRegistro != null ? ultimoRegistro.getNumControl() : null
        );

        String matricula = MatriculaGenerator.generarMatricula(
                req.nombre(), req.apellidoPaterno(), ultimoSecuencial
        );

        Alumnos nuevoAlumno = new Alumnos();
        nuevoAlumno.setNombre(req.nombre());
        nuevoAlumno.setApellidoPaterno(req.apellidoPaterno());
        nuevoAlumno.setApellidoMaterno(req.apellidoMaterno());
        nuevoAlumno.setFechaNacimiento(req.fechaNacimiento());
        nuevoAlumno.setCurp(req.curp());
        nuevoAlumno.setTelefonoFijo(req.telefonoFijo());
        nuevoAlumno.setTelefonoMovil(req.telefonoMovil());
        nuevoAlumno.setCorreoPersonal(req.correoPersonal());
        nuevoAlumno.setCorreoInstitucional(req.correoInstitucional());
        nuevoAlumno.setTutor(req.tutor());
        nuevoAlumno.setTelefonoTutor(req.telefonoTutor());

        nuevoAlumno.setNumControl(matricula);
        nuevoAlumno.setStatus(true);
        nuevoAlumno.setFechaCreacion(OffsetDateTime.now());
        nuevoAlumno.setFechaActualizacion(OffsetDateTime.now());

        alumnosRepository.persist(nuevoAlumno);

        return nuevoAlumno;
    }

    @Override
    public Alumnos findByNumControl(String numControl) {
        return alumnosRepository.find("numControl", numControl).firstResult();
    }

    @Override
    @Transactional
    public Alumnos updateAlumnos(UUID id, AlumnoRequest req) {
        Alumnos alumnoExistente = alumnosRepository.findById(id);
        if (alumnoExistente == null) {
            throw new IllegalArgumentException("No se encontró el alumno con el ID proporcionado.");
        }

        if (req == null) {
            throw new IllegalArgumentException("Los datos son requeridos.");
        }
        if (req.nombre() == null || req.nombre().isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }
        if (req.apellidoPaterno() == null || req.apellidoPaterno().isBlank()) {
            throw new IllegalArgumentException("El apellido paterno es obligatorio.");
        }
        if (req.fechaNacimiento() == null) {
            throw new IllegalArgumentException("La fecha de nacimiento es obligatoria.");
        }
        if (req.curp() == null || req.curp().isBlank()) {
            throw new IllegalArgumentException("La CURP es obligatoria.");
        }
        if (req.telefonoMovil() == null || req.telefonoMovil().isBlank()) {
            throw new IllegalArgumentException("El teléfono móvil es obligatorio.");
        }
        if (req.tutor() == null || req.tutor().isBlank()) {
            throw new IllegalArgumentException("El nombre del tutor es obligatorio.");
        }
        if (req.telefonoTutor() == null || req.telefonoTutor().isBlank()) {
            throw new IllegalArgumentException("El contacto del tutor es obligatorio.");
        }

        alumnoExistente.setNombre(req.nombre());
        alumnoExistente.setApellidoPaterno(req.apellidoPaterno());
        alumnoExistente.setApellidoMaterno(req.apellidoMaterno());
        alumnoExistente.setFechaNacimiento(req.fechaNacimiento());
        alumnoExistente.setCurp(req.curp());
        alumnoExistente.setTelefonoFijo(req.telefonoFijo());
        alumnoExistente.setTelefonoMovil(req.telefonoMovil());
        alumnoExistente.setCorreoPersonal(req.correoPersonal());
        alumnoExistente.setCorreoInstitucional(req.correoInstitucional());
        alumnoExistente.setTutor(req.tutor());
        alumnoExistente.setTelefonoTutor(req.telefonoTutor());

        return alumnoExistente;
    }

    @Override
    @Transactional
    public Alumnos deactivateAlumno(UUID id) {
        Alumnos alumnoExistente = alumnosRepository.findById(id);
        if (alumnoExistente ==



                null) {
            throw new IllegalArgumentException("No se encontró el alumno con el ID proporcionado.");
        }

        if (Boolean.FALSE.equals(alumnoExistente.getStatus())) {
            return alumnoExistente;
        }

        alumnoExistente.setStatus(false);

        return alumnoExistente;
    }

    /********************************************************************************+
     * **************+****************************************************************/
    @Override
    @Transactional
    public List<Alumnos> addAlumnosBatch(List<AlumnoRequest> requests) {
        if (requests == null || requests.isEmpty()) {
            throw new IllegalArgumentException("La lista de alumnos no puede estar vacía.");
        }

        for (AlumnoRequest req : requests) {
            validarAlumno(req);
        }

        String prefijo = MatriculaGenerator.generarPrefijo();
        Alumnos ultimoRegistro = alumnosRepository.findUltimoConPrefijo(prefijo);

        int ultimoSecuencial = MatriculaGenerator.extraerSecuencial(
                ultimoRegistro != null ? ultimoRegistro.getNumControl() : null
        );

        List<Alumnos> creados = new ArrayList<>();
        for (AlumnoRequest req : requests) {
            String matricula = MatriculaGenerator.generarMatricula(
                    req.nombre(), req.apellidoPaterno(), ultimoSecuencial
            );
            ultimoSecuencial++;

            Alumnos nuevoAlumno = construirAlumno(req, matricula);
            alumnosRepository.persist(nuevoAlumno);
            creados.add(nuevoAlumno);
        }

        return creados;
    }

    private void validarAlumno(AlumnoRequest req) {
        if (req == null) {
            throw new IllegalArgumentException("Los datos del alumno son requeridos.");
        }
        if (req.nombre() == null || req.nombre().isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }
        if (req.apellidoPaterno() == null || req.apellidoPaterno().isBlank()) {
            throw new IllegalArgumentException("El apellido paterno es obligatorio.");
        }
        if (req.fechaNacimiento() == null) {
            throw new IllegalArgumentException("La fecha de nacimiento es obligatoria.");
        }
        if (req.curp() == null || req.curp().isBlank()) {
            throw new IllegalArgumentException("La CURP es obligatoria.");
        }
        if (req.telefonoMovil() == null || req.telefonoMovil().isBlank()) {
            throw new IllegalArgumentException("El teléfono móvil es obligatorio.");
        }
        if (req.tutor() == null || req.tutor().isBlank()) {
            throw new IllegalArgumentException("El nombre del tutor es obligatorio.");
        }
        if (req.telefonoTutor() == null || req.telefonoTutor().isBlank()) {
            throw new IllegalArgumentException("El contacto del tutor es obligatorio.");
        }
    }

    private String generarSiguienteMatricula(AlumnoRequest req) {
        String prefijo = MatriculaGenerator.generarPrefijo();
        Alumnos ultimoRegistro = alumnosRepository.findUltimoConPrefijo(prefijo);
        int ultimoSecuencial = MatriculaGenerator.extraerSecuencial(
                ultimoRegistro != null ? ultimoRegistro.getNumControl() : null
        );
        return MatriculaGenerator.generarMatricula(
                req.nombre(), req.apellidoPaterno(), ultimoSecuencial
        );
    }

    private Alumnos construirAlumno(AlumnoRequest req, String matricula) {
        Alumnos nuevoAlumno = new Alumnos();
        nuevoAlumno.setNombre(req.nombre());
        nuevoAlumno.setApellidoPaterno(req.apellidoPaterno());
        nuevoAlumno.setApellidoMaterno(req.apellidoMaterno());
        nuevoAlumno.setFechaNacimiento(req.fechaNacimiento());
        nuevoAlumno.setCurp(req.curp());
        nuevoAlumno.setTelefonoFijo(req.telefonoFijo());
        nuevoAlumno.setTelefonoMovil(req.telefonoMovil());
        nuevoAlumno.setCorreoPersonal(req.correoPersonal());
        nuevoAlumno.setCorreoInstitucional(req.correoInstitucional());
        nuevoAlumno.setTutor(req.tutor());
        nuevoAlumno.setTelefonoTutor(req.telefonoTutor());
        nuevoAlumno.setNumControl(matricula);
        nuevoAlumno.setStatus(true);
        nuevoAlumno.setFechaCreacion(OffsetDateTime.now());
        nuevoAlumno.setFechaActualizacion(OffsetDateTime.now());
        return nuevoAlumno;
    }
}