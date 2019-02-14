/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aledania.lambdacalculo;

/**
 *
 * @author federico
 */
public class SKI extends SK{
    
    I i;
    
    public SKI(){
        super();
        i=new I();
    }
    
    public String toString(){
        return "{"+s.toString()+","+k.toString()+","+i.toString()+"}";
    }
    
}
