/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.lambdacalculo;

/**
 *
 * @author federico
 */
public class Main {
    
    public static void main(String args[])
    {
        Term x1=new Var(1);
        x1.alias="Hola@0";
        Term x2=new Var(1);
        x2.alias="Hola@1";
        Term t3=new App(x1,x2);
        t3.toStringAbrvFinal();
        String st=t3.toStringFinal();
    }
    
}
