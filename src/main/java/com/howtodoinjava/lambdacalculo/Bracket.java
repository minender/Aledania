/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.lambdacalculo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author federico
 */
public class Bracket extends Term{
    final Var x;
    public Term t;
    
    public Bracket(Var x1,Term t1)
    {
        x=x1;
        t=t1;
    }
    
    public boolean occur(Var y)
    {
         if(x.equals(y))
             return false;
         else
             return t.occur(y);
    }
    
    public Term sust(Var x,Term t)
    {
        return null;
    }
    
    public int setAlias(int currentAlia)
    {
        if(t.alias != null)
        {
            t.alias = t.alias+"@"+currentAlia;
            currentAlia++;
        }
        
        currentAlia = t.setAlias(currentAlia);
        return currentAlia;
    }
    
    public Term bracketAbsSH(Var y)
    {
        return t.bracketAbsSH(x).bracketAbsSH(y);
    }
    
    public Term bracketAbsBD(Var y)
    {
        return t.bracketAbsBD(x).bracketAbsBD(y);
    }
    
    public Term bracketAbsBD()
    {
        return t.bracketAbsBD(x);
    }
    
    public Term traducBD()
    {
        return t.traducBD().bracketAbsBD(x);
    }
    
    public List<Term> contandotraducBD()
    {
        List<Term> list=t.contandotraducBD();
        for (int i=0; i<list.size(); i++) {
            Var xaux = new Var(x.indice);
            list.set(i,new Bracket(xaux, list.get(i)));            
        }
        list.add(t.traducBD().bracketAbsBD(x));
        
        return list;
    }
    
    public int maxVar()
    {
        return t.maxVar();
    }
    
    public Tipo esRedex()
    {
        return new Tipo(false,false);
    }
    
     public Tipo esRedexFinal()
    {
        return new Tipo(false,false,false);
    }
    
    public Redex buscarRedexIzq(Term contexto,boolean p)
    {
        return t.buscarRedexIzq(this,false);
    }
    
    public Redex buscarRedexIzqFinal(Term contexto,boolean p)
    {
        return t.buscarRedexIzqFinal(this,false);
    }
    
    public Term invBraBD()
    {
        return this;
    }
    
    public Term invBD()
    {
        Term term = new Bracket(x,t.invBD());
        term.alias = this.alias;
        return term;
    }
    
    public Term invBDOneStep()
    {
        return new Bracket(x,t.invBDOneStep());
    }
    
    public String toString()
    {
        if(t.alias == null)
            return "(\\lambda "+x.toString()+"."+t.toStringFinal()+")";
        else
            return "(\\lambda "+x.toString()+"."+t.alias.split("@")[0].replace("_", "\\_") +")";
    }
    
    public ToString toStringAbrv(ToString toString)
    {
        if(t.alias == null)
        {
            t.toStringAbrvFinal(toString);
            toString.term= "(\\lambda "+x.toString()+"."+toString.term+")";
            return toString;
        }
        else
        {
            toString.setNuevoAlias(t.alias, t);
            toString.term="(\\lambda "+x.toString()+"."+toString.term+")";
            return toString;
        }
        
    }
    
    public ToString toStringAbrvV1(ToString toString)
    {
        if(t.alias == null)
        {
            t.toStringAbrvFinalV1(toString);
            toString.term= "(\\lambda "+x.toString()+"."+toString.term+")";
            return toString;
        }
        else
        {
            toString.setNuevoAliasV1(t.alias, t);
            toString.term="(\\lambda "+x.toString()+"."+toString.term+")";
            return toString;
        }
        
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bracket other = (Bracket) obj;
        if (this.x != other.x && (this.x == null || !this.x.equals(other.x))) {
            return false;
        }
        if (this.t != other.t && (this.t == null || !this.t.equals(other.t))) {
            return false;
        }
        return true;
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException{
        Term term=new Bracket(x,(Term)t.clone());
        term.alias=this.alias;
        return term;
    }
}
