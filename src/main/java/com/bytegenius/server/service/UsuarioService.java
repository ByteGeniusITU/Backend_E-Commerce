package com.bytegenius.server.service;

import com.bytegenius.server.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> getUsuarios();
    Optional<Usuario> getUsuarioById(long id);
}
