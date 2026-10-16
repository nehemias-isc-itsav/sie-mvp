package org.itsav.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.itsav.domain.entities.Paises;
import org.itsav.domain.records.PaisRequest;
import org.itsav.repositories.PaisesRepository;
import org.itsav.service.PaisesService;

import java.util.List;

@ApplicationScoped
public class PaisesServiceImpl implements PaisesService {

    @Inject
    PaisesRepository paisesRepository;

    @Override
    public List<Paises> findAll() {
        return paisesRepository.findAll().stream().toList();
    }

    @Override
    public Paises findById(Long id) {
        return paisesRepository.findById(id);
    }

    @Override
    @Transactional
    public Paises addCountry(PaisRequest reqDTO) {
        if(reqDTO == null){
            throw new IllegalArgumentException("Parametros incorrectos.");
        }
        if (reqDTO.prefijo() == null || reqDTO.prefijo().isBlank()) {
            throw new IllegalArgumentException("Necesitas ingresar un nombre.");
        }
        if(reqDTO.prefijo() == null || reqDTO.prefijo().isBlank()){
            throw new IllegalArgumentException("Ingresa un prefijo");
        }

        Paises nuevoPais = new Paises(reqDTO.nombre(), reqDTO.prefijo());
        paisesRepository.persist(nuevoPais);
        return nuevoPais;
    }
}
