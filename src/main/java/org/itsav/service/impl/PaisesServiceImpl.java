package org.itsav.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.itsav.domain.entities.Paises;
import org.itsav.domain.records.guardarPaisReqDTO;
import org.itsav.repositories.PaisesRepository;
import org.itsav.service.PaisesService;

import java.util.List;

@Transactional
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
    public String addCountry(guardarPaisReqDTO reqDTO) {
        if(reqDTO == null){
            throw new IllegalArgumentException("Parametros incorrectos.");
        } else if (reqDTO.getNombre().isEmpty()) {
            throw new IllegalArgumentException("Necesitas ingresar un nombre.");
        } else if(reqDTO.getPrefijo().isEmpty()){
            throw new IllegalArgumentException("Ingresa un prefijo");
        }
        paisesRepository.persist(new Paises(reqDTO.getNombre(), reqDTO.getPrefijo()));
        return "Pais guardado correctamente.";
    }
}
