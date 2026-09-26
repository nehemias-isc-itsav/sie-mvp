package org.itsav.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import org.itsav.UsuariosService;
import org.itsav.domain.records.InfoUsuarioDTO;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UsuariosServiceImpl implements UsuariosService {

    @Override
    public void getInfoUsuario(String numControl) {

    }

    @Override
    public List<InfoUsuarioDTO> getInfoUsuario() {
        List<InfoUsuarioDTO> infoUsuarios = new ArrayList<>();
        infoUsuarios.add(new InfoUsuarioDTO("1", "Jose"));
        infoUsuarios.add(new InfoUsuarioDTO("2", "Maria"));
        infoUsuarios.add(new InfoUsuarioDTO("3", "Juan"));
        infoUsuarios.add(new InfoUsuarioDTO("4", "Pedro"));
        infoUsuarios.add(new InfoUsuarioDTO("5", "Antonio"));
        infoUsuarios.add(new InfoUsuarioDTO("6", "Carlos"));
        infoUsuarios.add(new InfoUsuarioDTO("7", "Esteban"));
        infoUsuarios.add(new InfoUsuarioDTO("8", "Macario"));
        infoUsuarios.add(new InfoUsuarioDTO("9", "Rosa"));
        infoUsuarios.add(new InfoUsuarioDTO("10", "Natalia"));
        return infoUsuarios;
    }
}
