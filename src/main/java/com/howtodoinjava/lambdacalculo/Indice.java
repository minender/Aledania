/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.lambdacalculo;

/**
 *
 * @author federico
 */
public abstract class Indice {
     
    public int orden;
    
    public abstract String toString();
    
    @Override
    protected abstract Object clone() throws CloneNotSupportedException;
}
