package com.aledania.service;

import java.util.List;

import com.aledania.entity.Usuario;

public interface UsuarioManager {
    public void addUsuario(Usuario usuario);
    public void updateUsuario(Usuario usuario);
    public List<Usuario> getAllUsuarios();
    public Usuario getUsuario(String login);
    public void deleteUsuario(Integer usuarioId);
}
