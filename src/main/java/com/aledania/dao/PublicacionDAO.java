/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aledania.dao;

import com.aledania.entity.Publicacion;
import com.aledania.entity.PublicacionId;
import java.util.List;


/**
 *
 * @author federico
 */
public interface PublicacionDAO {
    
    public void addPublicacion(Publicacion publicacion);
    public void deletePublicacion(PublicacionId id);
    public Publicacion getPublicacion(PublicacionId id);
    public List<Publicacion> getAllPublicaciones();
    public List<Publicacion> getAllPublicaciones(String user);
}
