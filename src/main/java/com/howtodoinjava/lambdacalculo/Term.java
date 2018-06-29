/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.lambdacalculo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author federico
 */
public abstract class Term implements Cloneable, Serializable{
    
    public String alias;
    
    protected static class ToString
    {
        public List<String> valores;
        public List<String> alias;
        public String term;
        public int currentnAlias; 
        
        public ToString()
        {
            valores=new LinkedList<String>();
            alias=new LinkedList<String>();
        }
        
        public void setNuevoAlias(String alia,Term t)
        {   
            //int indice= (new Integer(alia.split("@")[1])).intValue();
            String aux;
            //valores.set(indice,t.toStringAbrv(this).term.replace("\\", "\\\\"));
            ToString toString = t.toStringAbrv(new ToString());
            valores.add(toString.term.replace("\\", "\\\\"));
            alias.add(alia.split("@")[0].replace("_", "\\\\_"));
            //t.toStringAbrv(this);
            valores.addAll(toString.valores);
            alias.addAll(toString.alias);
            //alias.set(indice,alia.split("@")[0].replace("_", "\\\\_"));
            aux="\\cssId{agru@alias@"+alia.split("@")[1]+"}{\\style{cursor:pointer; color:#08c;}{"+alia.split("@")[0].replace("_", "\\_") +"}}";
            currentnAlias++;
            
            term=aux;
        }
        
        public void setNuevoAliasV1(String alia,Term t)
        {   
            String aux;
            valores.add(t.toStringAbrvV1(this).term.replace("\\", "\\\\"));
            alias.add(alia.split("@")[0].replace("_", "\\\\_"));
            aux="\\cssId{agru@alias@"+currentnAlias+"}{\\style{cursor:pointer; color:#08c;}{"+alia.split("@")[0].replace("_", "\\_") +"}}";
            currentnAlias++;
            
            term=aux;
        }
    }
    
    public abstract Term bracketAbsSH(Var x);
    
    public abstract Term traducBD();
    
    public abstract List<Term> contandotraducBD();
    
    public abstract Term bracketAbsBD(Var x);
    
    public abstract Term invBD();
    
    public abstract Term invBDOneStep();
    
    public abstract Term invBraBD();
    
    public abstract Term sust(Var x,Term t);
    
    public abstract int setAlias(int currentAlia);
    
    public abstract Tipo esRedex();
    
    public abstract Tipo esRedexFinal();
    
    public abstract Redex buscarRedexIzq(Term contexto,boolean p);
    
    public abstract Redex buscarRedexIzqFinal(Term contexto,boolean p);
    
    public abstract int maxVar();

    public abstract boolean occur(Var x);
    
    @Override
    public abstract String toString();
    
    public abstract ToString toStringAbrvV1(ToString toString);
    
    public abstract ToString toStringAbrv(ToString toString);
    
    public String toStringFinal()
    {
        String term;
        String aux= this.toString();
        if(aux.startsWith("("))
            term=aux.substring(1, aux.length()-1);
        else
            term=aux;
        
        return term;
    }
    
    public void toStringAbrvFinal(ToString toString)
    {
        String term;
        ToString st=this.toStringAbrv(toString);
        String aux= st.term;
        if(aux.startsWith("("))
            term=aux.substring(1, aux.length()-1);
        else
            term=aux;
        
        st.term = term;
    }
    
    public void toStringAbrvFinalV1(ToString toString)
    {
        String term;
        ToString st=this.toStringAbrvV1(toString);
        String aux= st.term;
        if(aux.startsWith("("))
            term=aux.substring(1, aux.length()-1);
        else
            term=aux;
        
        st.term = term;
    }
    
    public Tripla toStringAbrvFinal()
    {
        String term;
        ToString toString=new ToString();
        /*for (int i=0; i<=25;i++)
        {
            toString.alias.add("");
            toString.valores.add("");
        }*/
        ToString st=this.toStringAbrv(toString);
        String aux= st.term;
        if(aux.startsWith("("))
            term=aux.substring(1, aux.length()-1);
        else
            term=aux;
        
        Tripla tripla = new Tripla();
        tripla.term=term;
        tripla.alias = st.alias;
        tripla.valores= st.valores;
                
        
        return tripla;
    }
    
    public Tripla toStringAbrvFinalV1()
    {
        String term;
        ToString toString=new ToString();
        ToString st=this.toStringAbrvV1(toString);
        String aux= st.term;
        if(aux.startsWith("("))
            term=aux.substring(1, aux.length()-1);
        else
            term=aux;
        
        Tripla tripla = new Tripla();
        tripla.term=term;
        tripla.alias = st.alias;
        tripla.valores= st.valores;
                
        
        return tripla;
    }
    
    public String toStringAbrvFinalFinal()
    {
        return this.toStringAbrvFinal().term;
    }
    
    public String toStringAbrvFinalFinalV1()
    {
        return this.toStringAbrvFinalV1().term;
    }
    
    public String toStringJavascript(String id)
    {
        Tripla tri = this.toStringAbrvFinalV1();
        
        String st="<span id=\"Math"+id+"\">$$"+tri.term +"$$</span>";
        st+="<script>var alias=[";
        for(String it:tri.alias)
            st+="\""+it+"\",\n";

        st+= "];\n valorAlias=[";
        for(String it2:tri.valores)
            st+="\""+it2+"\",\n";
        st+="];\n clickAlias(\"Math"+id +"\",alias, valorAlias);</script>";
        
        return st;
    }
    
    public String toStringAbrv()
    {
        Tripla tri = this.toStringAbrvFinal();
        
        return tri.term;
    }
    public class Tipo
    {
        public boolean c;
        public boolean l;
        public boolean t;
        
        public Tipo(boolean com,boolean lambda)
        {
            c=com;
            l=lambda;
        }
        
        public Tipo(boolean com,boolean lambda, boolean traduc)
        {
            c=com;
            l=lambda;
            t=traduc;
        }
    }
    
    public class Redex
    {
        public Term context;
        public Tipo tipo;
        public boolean p;
        
        public Redex(Term con,Tipo tip,boolean pp)
        {
            context=con;
            tipo=tip;
            p=pp;
        }
    }
    
    public List<String> volverPuroList()
    {
        try
        {
            Term clone=(Term)this.clone();
            Redex r=clone.buscarRedexIzq(null,false);
            List<String> puro = new LinkedList<String>();
            if(r!=null && r.tipo.l)
            {
                if(r.context==null)
                {
                    List<Term> list = ((Bracket)(((App)clone).p)).t.contandotraducBD();
                    for(Term t:list)
                        puro.add((new App(new Bracket(((Bracket)(((App)clone).p)).x,t),((App)clone).q)).toStringAbrvFinalFinal().replace("\\", "\\\\"));
                        
                }
                else if(r.context instanceof App)
                {
                     if(r.p)
                     {
                         Term t=((App)r.context).p; 
                         List<Term> list = ((Bracket)((App)t).p).t.contandotraducBD();
                         for(Term ter: list)
                         {
                              ((App)r.context).p=new App(new Bracket(((Bracket)((App)t).p).x,ter),((App)t).q);
                              puro.add(clone.toStringAbrvFinalFinal().replace("\\", "\\\\"));
                         }
                     }
                     else
                     {
                         Term t=((App)r.context).q;  
                         List<Term> list = ((Bracket)((App)t).p).t.contandotraducBD();
                         for(Term ter: list)
                         {
                            ((App)r.context).q=new App(new Bracket(((Bracket)((App)t).p).x,ter),((App)t).q);
                            puro.add(clone.toStringAbrvFinalFinal().replace("\\", "\\\\"));
                         }
                     }
                }
                else if(r.context instanceof Bracket)
                {           
                     Term t=((Bracket)r.context).t;
                     List<Term> list = ((Bracket)((App)t).p).t.contandotraducBD();
                     for(Term ter: list)
                     {
                         ((Bracket)r.context).t=new App(new Bracket(((Bracket)((App)t).p).x,ter),((App)t).q);
                         puro.add(clone.toStringAbrvFinalFinal().replace("\\", "\\\\"));
                     }
                }
            }
            if(puro.size() == 0)
            {
                puro.add(this.toStringAbrvFinalFinal().replace("\\", "\\\\"));
            }
            return puro;
        }
        catch(Exception e){}
        return null;
    }
    
    public Term volverPuro()
    {
        try
        {
            Term clone=(Term)this.clone();
            Redex r=clone.buscarRedexIzq(null,false);
            if(r!=null && r.tipo.l)
            {
                if(r.context==null)
                        return new App(new Bracket(((Bracket)(((App)clone).p)).x,((Bracket)(((App)clone).p)).t.traducBD()),((App)clone).q);
                else if(r.context instanceof App)
                {
                     if(r.p)
                     {
                         Term t=((App)r.context).p; 
                            ((App)r.context).p=new App(new Bracket(((Bracket)((App)t).p).x,((Bracket)((App)t).p).t.traducBD()),((App)t).q);
                            return clone;
                     }
                     else
                     {
                         Term t=((App)r.context).q;  
                            ((App)r.context).q=new App(new Bracket(((Bracket)((App)t).p).x,((Bracket)((App)t).p).t.traducBD()),((App)t).q);
                            return clone;
                     }
                }
                else if(r.context instanceof Bracket)
                {           
                     Term t=((Bracket)r.context).t;
                         ((Bracket)r.context).t=new App(new Bracket(((Bracket)((App)t).p).x,((Bracket)((App)t).p).t.traducBD()),((App)t).q);
                         return clone;
                }
            }
            else
                return this;
        }
        catch(Exception e){}
        return this;
    }
    
    public Term reducir()
    {
        Redex r=buscarRedexIzq(null,false);
        if(r!=null)
        {
            if(r.context==null)
            {
                if(r.tipo.c)
                    return this.kappa();
                else
                    return ((Bracket)(((App)this).p)).t.traducBD().sust(((Bracket)(((App)this).p)).x, ((App)this).q);
            }
            else if(r.context instanceof App)
            {
                 if(r.p)
                 {
                     Term t=((App)r.context).p; 
                     if(r.tipo.c)
                        ((App)r.context).p=t.kappa();
                     else
                        ((App)r.context).p=((Bracket)((App)t).p).t.traducBD().sust(((Bracket)((App)t).p).x, ((App)t).q);
                 }
                 else
                 {
                     Term t=((App)r.context).q; 
                     if(r.tipo.c)
                        ((App)r.context).q=t.kappa();
                     else
                        ((App)r.context).q=((Bracket)((App)t).p).t.traducBD().sust(((Bracket)((App)t).p).x, ((App)t).q);
                 }
            }
            else if(r.context instanceof Bracket)
            {           
                 Term t=((Bracket)r.context).t; 
                 if(r.tipo.c)
                     ((Bracket)r.context).t=t.kappa();
                 else
                     ((Bracket)r.context).t=((Bracket)((App)t).p).t.traducBD().sust(((Bracket)((App)t).p).x, ((App)t).q);

            }
        }
        
        return this;
    }
    
    public Term reducirFinal(Corrida corr)
    {
        Redex r=buscarRedexIzqFinal(null,false);
        if(r!=null)
        {
            Term reduc;
            if(r.context==null)
            {
                if(r.tipo.c)
                {
                    reduc = this.kappa();
                    corr.operations.add(new Integer(3));
                    corr.terminos.add(reduc.toStringAbrvFinalFinal().replace("\\", "\\\\"));
                    try{
                     Term tee=(Term)reduc.clone();
                     corr.lambdaTerms.add(tee.invBD().toStringAbrvFinalFinal().replace("\\", "\\\\"));
                     }
                     catch(Exception e){e.printStackTrace();}
                    corr.reducciones++;
                    return reduc;
                }
                else if(r.tipo.l)
                {
                    List<String> puro=this.volverPuroList();
                    for(int i=0; i < puro.size(); i++)
                        corr.operations.add(new Integer(1));
                    
                    corr.terminos.addAll(puro);
                    corr.traducciones+=puro.size();
                    reduc = ((Bracket)(((App)this).p)).t.traducBD().sust(((Bracket)(((App)this).p)).x, ((App)this).q);
                    corr.operations.add(new Integer(3));
                    corr.terminos.add(reduc.toStringAbrvFinalFinal().replace("\\", "\\\\"));
                    try{
                     Term tee=(Term)reduc.clone();
                     corr.lambdaTerms.add(tee.invBD().toStringAbrvFinalFinal().replace("\\", "\\\\"));
                     }
                     catch(Exception e){e.printStackTrace();}
                    corr.reducciones++;
                    return reduc;
                }
                else
                {
                    reduc=this.invBraBD();
                    corr.operations.add(new Integer(2));
                    corr.terminos.add(reduc.toStringAbrvFinalFinal().replace("\\", "\\\\"));
                    corr.traducciones++;
                    return reduc;
                }
            }
            else if(r.context instanceof App)
            {
                 if(r.p)
                 {
                     Term t=((App)r.context).p; 
                     if(r.tipo.c)
                     {
                        ((App)r.context).p=t.kappa();
                        corr.operations.add(new Integer(3));
                        corr.reducciones++;
                        corr.terminos.add(this.toStringAbrvFinalFinal().replace("\\", "\\\\"));
                        try{
                     Term tee=(Term)((App)r.context).p.clone();
                     corr.lambdaTerms.add(tee.invBD().toStringAbrvFinalFinal().replace("\\", "\\\\"));
                     }
                     catch(Exception e){e.printStackTrace();}
                     }
                     else if(r.tipo.l)
                     {
                        List<String> puro=this.volverPuroList();
                        for(int i=0; i < puro.size(); i++)
                            corr.operations.add(new Integer(1));
                        
                        corr.terminos.addAll(puro);
                        corr.traducciones+=puro.size();
                        ((App)r.context).p=((Bracket)((App)t).p).t.traducBD().sust(((Bracket)((App)t).p).x, ((App)t).q);
                        corr.operations.add(new Integer(3));
                        corr.terminos.add(this.toStringAbrvFinalFinal().replace("\\", "\\\\"));
                        try{
                     Term tee=(Term)this.clone();
                     corr.lambdaTerms.add(tee.invBD().toStringAbrvFinalFinal().replace("\\", "\\\\"));
                     }
                     catch(Exception e){e.printStackTrace();}
                        corr.reducciones++;
                     }
                     else
                     {
                        ((App)r.context).p=t.invBraBD();
                        corr.operations.add(new Integer(2));
                        corr.terminos.add(this.toStringAbrvFinalFinal().replace("\\", "\\\\"));
                        corr.traducciones++;
                     }
                 }
                 else
                 {
                     Term t=((App)r.context).q; 
                     if(r.tipo.c)
                     {
                        ((App)r.context).q=t.kappa();
                        corr.operations.add(new Integer(3));
                        corr.reducciones++;
                        corr.terminos.add(this.toStringAbrvFinalFinal().replace("\\", "\\\\"));
                        try{
                     Term tee=(Term)this.clone();
                     corr.lambdaTerms.add(tee.invBD().toStringAbrvFinalFinal().replace("\\", "\\\\"));
                     }
                     catch(Exception e){e.printStackTrace();}
                     }
                     else if(r.tipo.l)
                     {
                        List<String> puro=this.volverPuroList();
                        for(int i=0; i < puro.size(); i++)
                            corr.operations.add(new Integer(1));
                        
                        corr.terminos.addAll(puro);
                        corr.traducciones+=puro.size();
                        ((App)r.context).q=((Bracket)((App)t).p).t.traducBD().sust(((Bracket)((App)t).p).x, ((App)t).q);
                        corr.operations.add(new Integer(3));
                        corr.terminos.add(this.toStringAbrvFinalFinal().replace("\\", "\\\\"));
                        try{
                     Term tee=(Term)this.clone();
                     corr.lambdaTerms.add(tee.invBD().toStringAbrvFinalFinal().replace("\\", "\\\\"));
                     }
                     catch(Exception e){e.printStackTrace();}
                        corr.reducciones++;
                     }
                     else
                     {
                        ((App)r.context).q=t.invBraBD();
                        corr.operations.add(new Integer(2));
                        corr.terminos.add(this.toStringAbrvFinalFinal().replace("\\", "\\\\"));
                        corr.traducciones++;
                     }
                 }
            }
            else if(r.context instanceof Bracket)
            {           
                 Term t=((Bracket)r.context).t; 
                 if(r.tipo.c)
                 {
                     ((Bracket)r.context).t=t.kappa();
                     corr.operations.add(new Integer(3));
                     corr.terminos.add(this.toStringAbrvFinalFinal().replace("\\", "\\\\"));
                     try{
                     Term tee=(Term)this.clone();
                     corr.lambdaTerms.add(tee.invBD().toStringAbrvFinalFinal().replace("\\", "\\\\"));
                     }
                     catch(Exception e){e.printStackTrace();}
                     corr.reducciones++;
                 }
                 else if(r.tipo.l)
                 {
                     List<String> puro=this.volverPuroList();
                        for(int i=0; i < puro.size(); i++)
                            corr.operations.add(new Integer(1));
                        
                    corr.terminos.addAll(puro);
                    corr.traducciones+=puro.size();
                     ((Bracket)r.context).t=((Bracket)((App)t).p).t.traducBD().sust(((Bracket)((App)t).p).x, ((App)t).q);
                     corr.operations.add(new Integer(3));
                     corr.terminos.add(this.toStringAbrvFinalFinal().replace("\\", "\\\\"));
                     try{
                     Term tee=(Term)this.clone();
                     corr.lambdaTerms.add(tee.invBD().toStringAbrvFinalFinal().replace("\\", "\\\\"));
                     }
                     catch(Exception e){e.printStackTrace();}
                     corr.reducciones++;
                 }
                 else
                 {
                     ((Bracket)r.context).t=t.invBraBD();
                     corr.operations.add(new Integer(2));
                     corr.terminos.add(this.toStringAbrvFinalFinal().replace("\\", "\\\\"));
                     corr.traducciones++;
                 }
            }
            
        }
        
        return this;
    }
    
    protected class AppIzq
    {
        Term p;
        App pq;
        App pqr;
        int deep;
        
        AppIzq(Term p1, App q1,App pqr1, int deep1)
        {
            p=p1;
            pq=q1;
            pqr=pqr1;
            deep=deep1;
        }
    }
    
    public AppIzq obtenerIzq(App in,int deep)
    {
        int i=1;
        Term p1=in.p;
        App appizq=in;
        App aizqnivel2=null;
        while((deep != 0) && p1 instanceof App)
        {
            aizqnivel2=appizq;
            appizq=(App)p1;
            p1=((App)p1).p;
            i++;
            deep--;
        }
        return (new AppIzq(p1,appizq,aizqnivel2,i));
    }
    
    public Term kappa()
    {
        if(this instanceof App)
        {
            AppIzq izq=obtenerIzq((App)this,-1);
            Const k=new Const("\\Phi_{K}");
            if((izq.p instanceof Phi) && izq.deep==((ListaInd)((Phi)izq.p).ind).orden+1)
            {
                ListaInd l=((Phi)izq.p).ind;
                if(!l.list.isEmpty())
                {
                    Indice i=l.removerUlt();
                    if(i instanceof ConstInd)
                    {
                        if(((ConstInd)i).ind.equals("b"))
                        {
                            Term x1=izq.pq.q;
                            izq.pqr.p=izq.p;
                            return new App(x1,this.kappa());
                        }
                        else
                        {
                            Term x1=izq.pq.q;
                            izq.pqr.p=izq.p;
                            return new App(this.kappa(),x1);
                        }
                    }
                    else
                    {
                        Phi phi1=new Phi();
                        Phi phi2=new Phi();
                        phi1.ind=((ParInd)i).i1;//mal tambien
                        phi2.ind=((ParInd)i).i2;//tienes que concatenar con el resto de los indices
                        izq.pq.p=phi1;
                        AppIzq izq2=obtenerIzq((App)this,phi2.ind.orden);//no se empieza del ultimo mal
                        Term t1=izq2.p;
                        Term xnMas1;
                        try{
                            xnMas1=(Term)((App)this).q.clone();
                            izq2.pq.p=phi2;
                            return new App((new App(t1,xnMas1)).kappa(),this.kappa());
                        }
                        catch(CloneNotSupportedException e){
                             System.out.println(e);
                             return null;
                        }
                    }
                }
                else
                {
                    return izq.pq.q;
                }
            }
            else if(izq.p.equals(k) && izq.deep==2)
            {
                return izq.pq.q;
            }
            else
            {
                return this;
            }
        }
        else
        {
            return this;
        }
    }
    
    public Term kappaIndexado(int c,Var xc)
    {
        if(this instanceof App)
        {
            AppIzq izq=obtenerIzq((App)this,-1);
            Const k=new Const("\\Phi_{K}");
            if((izq.p instanceof Phi) && izq.deep==((ListaInd)((Phi)izq.p).ind).orden+1)
            {
                ListaInd l=((Phi)izq.p).ind;
                if(!l.list.isEmpty())
                {
                    Indice i=l.removerUlt();
                    if(i instanceof ConstInd)
                    {
                        if(((ConstInd)i).ind.equals("b"))
                        {
                            Term x1=izq.pq.q;
                            izq.pqr.p=izq.p;
                            return new App(x1,this.kappaIndexado(Math.max(c,x1.maxVar()+1),xc));
                        }
                        else
                        {
                            Term x1=izq.pq.q;
                            izq.pqr.p=izq.p;
                            return new App(this.kappaIndexado(Math.max(c,x1.maxVar()+1),xc),x1);
                        }
                    }
                    else
                    {
                        Phi phi1=new Phi();
                        Phi phi2=new Phi();
                        phi1.ind=((ParInd)i).i1;//mal tambien
                        phi2.ind=((ParInd)i).i2;//tienes que concatenar con el resto de los indices
                        izq.pq.p=phi1;
                        AppIzq izq2=obtenerIzq((App)this,phi2.ind.orden);//no se empieza del ultimo mal
                        Term t1=izq2.p;
                        izq2.pq.p=phi2;
                        return new App((new App(t1,xc)).kappaIndexado(c,xc),this.kappaIndexado(c,xc));
                    }
                }
                else
                {
                    /*xc.setIndice(Math.max(izq.pq.q.maxVar(),c));
                    return xc;*/
                    xc.indice=Math.max(izq.pq.q.maxVar(),c);
                    return xc;
                }
            }
            else if(izq.p.equals(k) && izq.deep==2)
            {
                return izq.pq.q;
            }
            else
            {
                return this;
            }
        }
        else
        {
            return this;
        }
    }
    
    public Term evaluar()
    {
            Term term2=this;
            Term term1=term2;

            do
            {

                term1=term2;
                Redex redex=term1.buscarRedexIzq(null, false);
                while(redex!=null)
                {

                    term1=term1.reducir();
                    redex=term1.buscarRedexIzq(null, false);
                }
                term2=term1.invBDOneStep();
            }while(!term1.equals(term2));

	    return term1;
    }
    
    public Corrida evaluarFinal()
    {
        Corrida corr = new Corrida();
        Term term=this;
        corr.terminos.add(term.toStringAbrvFinalFinal().replace("\\", "\\\\"));
        
        Redex redex=term.buscarRedexIzqFinal(null, false);
        while(redex != null)
        {
            term=term.reducirFinal(corr);
            redex=term.buscarRedexIzqFinal(null, false);
        }
        
        return corr;
    }

    public static Term natural(int n)
    {
	Term suc=new Bracket(new Var(0),new Bracket(new Var(1),new Bracket(new Var(2),new App(new App(new Var(0),new Var(1)),new App(new Var(1),new Var(2)))))); 
	Term ent=new Bracket(new Var(0),new Bracket(new Var(1),new Var(1))); 
	
	for(int i=1; i<=n;i++)
		ent=(new App(suc,ent)).evaluar(); 
        
        ent.alias="N"+n;

	return ent;
    }
    
    public void setAlias(String alias)
    {
        this.alias=alias;
    }
    
    @Override
    protected abstract Object clone() throws CloneNotSupportedException;
    
    
}
