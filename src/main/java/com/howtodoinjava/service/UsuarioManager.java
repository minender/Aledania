package com.howtodoinjava.service;

import java.util.List;

import com.howtodoinjava.entity.Usuario;

public interface UsuarioManager {
    public void addUsuario(Usuario usuario);
    public void updateUsuario(Usuario usuario);
    public List<Usuario> getAllUsuarios();
    public Usuario getUsuario(String login);
    public void deleteUsuario(Integer usuarioId);
}
