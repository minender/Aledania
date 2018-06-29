package com.howtodoinjava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.howtodoinjava.dao.UsuarioDAO;
import com.howtodoinjava.entity.Usuario;

@Service
public class UsuarioManagerImpl implements UsuarioManager {
	
	@Autowired
        private UsuarioDAO usuarioDAO;

	@Override
	@Transactional
	public void addUsuario(Usuario usuario) {
		usuarioDAO.addUsuario(usuario);
	}
        
        
        @Override
        @Transactional
        public void updateUsuario(Usuario usuario) {
                usuarioDAO.updateUsuario(usuario);
        }

	@Override
	@Transactional
	public List<Usuario> getAllUsuarios() {
		return usuarioDAO.getAllUsuarios();
	}

	@Override
	@Transactional
	public void deleteUsuario(Integer usuarioId) {
		usuarioDAO.deleteUsuario(usuarioId);
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

        @Override
        @Transactional
        public Usuario getUsuario(String login) {
            return usuarioDAO.getUsuario(login);
        }
}
