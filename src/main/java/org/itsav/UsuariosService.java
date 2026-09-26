package org.itsav;

import org.itsav.domain.records.InfoUsuarioDTO;

import java.util.List;

public interface UsuariosService {
    public void getInfoUsuario(String numControl);
    public List<InfoUsuarioDTO> getInfoUsuario();
}
