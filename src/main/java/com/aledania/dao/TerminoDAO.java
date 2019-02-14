/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aledania.dao;

import com.aledania.entity.Termino;
import com.aledania.entity.TerminoId;
import com.aledania.entity.Usuario;
import java.util.List;


/**
 *
 * @author federico
 */
public interface TerminoDAO {
    
    public void addTermino(Termino termino);
    public void deleteTermino(TerminoId id);
    public Termino getTermino(TerminoId id);
    public List<Termino> getAllTerminos();
    public Termino getCombinador(String username, String comb);
    public List<Termino> getAllTerminos(String user);
    public List<Termino> getAllPublicaciones(String user);
}
