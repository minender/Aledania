/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.controller;

import com.howtodoinjava.entity.Termino;
import com.howtodoinjava.entity.TerminoId;
import com.howtodoinjava.entity.Usuario;
import com.howtodoinjava.forms.InsertarEvaluar;
import com.howtodoinjava.lambdacalculo.Corrida;
import com.howtodoinjava.lambdacalculo.Term;
import com.howtodoinjava.lambdacalculo.Term.Redex;
import com.howtodoinjava.lambdacalculo.Tripla;
import com.howtodoinjava.parse.IsNotInDBException;
import com.howtodoinjava.parse.TermLexer;
import com.howtodoinjava.parse.TermParser;
import com.howtodoinjava.service.TerminoManager;
import com.howtodoinjava.service.UsuarioManager;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author federico
 */
@Controller
@RequestMapping(value="/eval")
public class EvaluarController {
    
    @Autowired
    private UsuarioManager usuarioManager;
    @Autowired
    private TerminoManager terminoManager;
    @Autowired
    private HttpSession session;
    
    @RequestMapping(value="/{username}/paso", method=RequestMethod.POST)
    public String evaluarView(@Valid InsertarEvaluar insertarEvaluar, BindingResult bindingResult, @PathVariable String username, ModelMap map)
    {
            if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
            {
                return "redirect:/index";
            }
            if( bindingResult.hasErrors() )
            {
                map.addAttribute("usuario", usuarioManager.getUsuario(username));
                map.addAttribute("mensaje","");
                map.addAttribute("termino",insertarEvaluar.getAlgoritmo());
                map.addAttribute("nombre",insertarEvaluar.getNombre());
                map.addAttribute("guardarMenu","");
                map.addAttribute("listarTerminosMenu","");
                map.addAttribute("verTerminosPublicosMenu","");
                map.addAttribute("misPublicacionesMenu","");
                map.addAttribute("computarMenu","class=\"active\"");
                map.addAttribute("perfilMenu","");
                map.addAttribute("hrefAMiMismo","href=../../eval/"+username+"#!");
                return "insertarEvaluar";
            }
        
            String programa=insertarEvaluar.getAlgoritmo();
            TerminoId terminoid=new TerminoId();
            terminoid.setLogin(username);
            
            //Hay que construir un Term aqui con el String termino.combinador
            //para luego traducir, hace falta construir un parse   
            ANTLRStringStream in = new ANTLRStringStream(programa);
            TermLexer lexer = new TermLexer(in);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            TermParser parser = new TermParser(tokens);
            Term term;
            try //si la sintanxis no es correcta ocurre una Exception
            {

                term=parser.t(terminoid,terminoManager);
                
            }
            catch(IsNotInDBException e)
            {
                String hdr = parser.getErrorHeader(e);
		String msg = parser.getErrorMessage(e, TermParser.tokenNames);
                map.addAttribute("usuario", usuarioManager.getUsuario(username));
                map.addAttribute("insertarEvaluar",new InsertarEvaluar());
                map.addAttribute("mensaje", hdr +((IsNotInDBException)e).message);
                map.addAttribute("termino",programa);
                map.addAttribute("nombre",insertarEvaluar.getNombre());
                map.addAttribute("guardarMenu","");
                map.addAttribute("listarTerminosMenu","");
                map.addAttribute("verTerminosPublicosMenu","");
                map.addAttribute("misPublicacionesMenu","");
                map.addAttribute("computarMenu","class=\"active\"");
                map.addAttribute("perfilMenu","");   
                map.addAttribute("hrefAMiMismo","href=../../eval/"+username+"#!");
                return "insertarEvaluar";
            }
            catch(RecognitionException e)
            {
                String hdr = parser.getErrorHeader(e);
		String msg = parser.getErrorMessage(e, TermParser.tokenNames);
                map.addAttribute("usuario", usuarioManager.getUsuario(username));
                map.addAttribute("insertarEvaluar",new InsertarEvaluar());
                map.addAttribute("mensaje", hdr+" "+msg);
                map.addAttribute("termino",programa);
                map.addAttribute("nombre",insertarEvaluar.getNombre());
                map.addAttribute("guardarMenu","");
                map.addAttribute("listarTerminosMenu","");
                map.addAttribute("verTerminosPublicosMenu","");
                map.addAttribute("misPublicacionesMenu","");
                map.addAttribute("computarMenu","class=\"active\"");
                map.addAttribute("perfilMenu","");   
                map.addAttribute("hrefAMiMismo","href=../../eval/"+username+"#!");
                return "insertarEvaluar";
            }
            
            ArrayList<String> terminos = new ArrayList<String>();
            ArrayList<String> operations = new ArrayList<String>();
            Term term2=term;
            Term term1=term2;
            int nTerms=0;
            int i=-1;
            do
            {

                term1=term2;
                if(i==-1) 
                    operations.add("\\downarrow");
                else
                {
                    if( i == 2)
                        i=0;
                    operations.add("\\uparrow");
                }
                terminos.add(term1.toStringFinal());
                nTerms++;
                i++;
                Redex redex=term1.buscarRedexIzq(null, false);
                while(redex!=null)
                {
                    if(redex.tipo.l)
                    {
                            operations.add("\\downarrow");
                            terminos.add(term1.volverPuro().toStringFinal());
                            nTerms++;
                    }
                    term1=term1.reducir();

                    operations.add("\\rightarrow");
                    terminos.add(term1.toStringFinal());
                    nTerms++;

                    redex=term1.buscarRedexIzq(null, false);
                }
                term2=term1.invBDOneStep();
            }while(!term1.equals(term2));
            
            map.addAttribute("nTerms",new Integer(nTerms-1));
            map.addAttribute("terminos",terminos);
            map.addAttribute("operations",operations);
            return "evaluarTodo";
    }
    
    /*@RequestMapping(value="/{username}", method=RequestMethod.POST)
    public String evaluarPasoAPasoView(@Valid InsertarEvaluar insertarEvaluar, BindingResult bindingResult, @PathVariable String username, ModelMap map)
    {

            if( bindingResult.hasErrors() )
            {
                map.addAttribute("usuario", usuarioManager.getUsuario(username));
                map.addAttribute("mensaje","");
                map.addAttribute("termino",insertarEvaluar.getAlgoritmo());
                map.addAttribute("nombre",insertarEvaluar.getNombre());
                return "insertarEvaluar";
            }
        
            String programa=insertarEvaluar.getAlgoritmo();
            TerminoId terminoid=new TerminoId();
            terminoid.setLogin(username);
            
            //Hay que construir un Term aqui con el String termino.combinador
            //para luego traducir, hace falta construir un parse   
            ANTLRStringStream in = new ANTLRStringStream(programa);
            TermLexer lexer = new TermLexer(in);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            TermParser parser = new TermParser(tokens);
            Term term;
            try //si la sintanxis no es correcta ocurre una Exception
            {

                term=parser.t(terminoid,terminoManager);
                
                
            }
            catch(IsNotInDBException e)
            {
                String hdr = parser.getErrorHeader(e);
		String msg = parser.getErrorMessage(e, TermParser.tokenNames);
                map.addAttribute("usuario", usuarioManager.getUsuario(username));
                map.addAttribute("insertarEvaluar",new InsertarEvaluar());
                map.addAttribute("mensaje", hdr +((IsNotInDBException)e).message);
                map.addAttribute("nombre",insertarEvaluar.getNombre());
                map.addAttribute("termino",programa);
                return "insertarEvaluar";
            }
            catch(RecognitionException e)
            {
                String hdr = parser.getErrorHeader(e);
		String msg = parser.getErrorMessage(e, TermParser.tokenNames);
                map.addAttribute("usuario", usuarioManager.getUsuario(username));
                map.addAttribute("insertarEvaluar",new InsertarEvaluar());
                map.addAttribute("mensaje", hdr+" "+msg);
                map.addAttribute("nombre",insertarEvaluar.getNombre());
                map.addAttribute("termino",programa);
                return "insertarEvaluar";
            }
            
            Tripla tripla=term.toStringAbrvFinal();
            ArrayList<String> terminos = new ArrayList<String>();
            List<String> alias=tripla.alias;
            List<String> valores=tripla.valores;
            ArrayList<Integer> operations = new ArrayList<Integer>();
            Term term2=term;
            Term term1=term2;
            int nTerms=0;
            int i=-1;
            do
            {

                term1=term2;
                if(i==-1) 
                    operations.add(new Integer(1));
                else
                {
                    if( i == 2)
                        i=0;
                    operations.add(new Integer(2));
                }
                //tripla=term1.toStringAbrvFinal();
                //terminos.add(tripla.term.replace("\\", "\\\\"));
                terminos.add(term1.toStringFinal().replace("\\", "\\\\"));
                nTerms++;
                i++;
                Redex redex=term1.buscarRedexIzq(null, false);
                while(redex!=null)
                {
                    if(redex.tipo.l)
                    {
                            operations.add(new Integer(1));
                            //tripla=term1.volverPuro().toStringAbrvFinal();
                            //terminos.add(tripla.term.replace("\\", "\\\\"));
                            terminos.add(term1.volverPuro().toStringFinal().replace("\\", "\\\\"));
                            nTerms++;
                    }
                    term1=term1.reducir();

                    operations.add(new Integer(3));
                    //tripla=term1.toStringAbrvFinal();
                    //terminos.add(tripla.term.replace("\\", "\\\\"));
                    terminos.add(term1.toStringFinal().replace("\\", "\\\\"));
                    nTerms++;

                    redex=term1.buscarRedexIzq(null, false);
                }
                term2=term1.invBDOneStep();
            }while(!term1.equals(term2));
            
           terminos.set(0, tripla.term.replace("\\", "\\\\"));
            
            map.addAttribute("nTerms",new Integer(nTerms-1));
            map.addAttribute("terminos",terminos);
            map.addAttribute("operations",operations);
            map.addAttribute("nAlias",new Integer(alias.size()-1));
            map.addAttribute("alias",alias);
            map.addAttribute("valores",valores);
            return "evaluar";
    }*/
    
    @RequestMapping(value="/{username}", method=RequestMethod.POST)
    public String evaluarPasoAPasoView(@Valid InsertarEvaluar insertarEvaluar, BindingResult bindingResult, @PathVariable String username, ModelMap map)
    {
            if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
            {
                return "redirect:/index";
            }
            if( bindingResult.hasErrors() )
            {
                map.addAttribute("usuario", usuarioManager.getUsuario(username));
                map.addAttribute("mensaje","");
                map.addAttribute("termino",insertarEvaluar.getAlgoritmo());
                map.addAttribute("nombre",insertarEvaluar.getNombre());
                map.addAttribute("guardarMenu","");
                map.addAttribute("admin","admin");
                map.addAttribute("listarTerminosMenu","");
                map.addAttribute("verTerminosPublicosMenu","");
                map.addAttribute("misPublicacionesMenu","");
                map.addAttribute("computarMenu","class=\"active\"");
                map.addAttribute("perfilMenu","");
                map.addAttribute("hrefAMiMismo","href=../../eval/"+username+"#!");
                map.addAttribute("overflow","hidden");
                map.addAttribute("anchuraDiv","1100px");
                return "insertarEvaluar";
            }
        
            String programa=insertarEvaluar.getAlgoritmo();
            TerminoId terminoid=new TerminoId();
            terminoid.setLogin(username);
            
            //Hay que construir un Term aqui con el String termino.combinador
            //para luego traducir, hace falta construir un parse   
            ANTLRStringStream in = new ANTLRStringStream(programa);
            TermLexer lexer = new TermLexer(in);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            TermParser parser = new TermParser(tokens);
            Term term;
            try //si la sintanxis no es correcta ocurre una Exception
            {

                term=parser.t(terminoid,terminoManager);
                term.setAlias(0);
                
            }
            catch(IsNotInDBException e)
            {
                String hdr = parser.getErrorHeader(e);
		String msg = parser.getErrorMessage(e, TermParser.tokenNames);
                map.addAttribute("usuario", usuarioManager.getUsuario(username));
                map.addAttribute("insertarEvaluar",new InsertarEvaluar());
                map.addAttribute("mensaje", hdr +((IsNotInDBException)e).message);
                map.addAttribute("nombre",insertarEvaluar.getNombre());
                map.addAttribute("termino",programa);
                map.addAttribute("admin","admin");
                map.addAttribute("guardarMenu","");
                map.addAttribute("listarTerminosMenu","");
                map.addAttribute("verTerminosPublicosMenu","");
                map.addAttribute("misPublicacionesMenu","");
                map.addAttribute("computarMenu","class=\"active\"");
                map.addAttribute("perfilMenu","");
                map.addAttribute("hrefAMiMismo","href=../../eval/"+username+"#!");
                map.addAttribute("overflow","hidden");
                map.addAttribute("anchuraDiv","1100px");
                return "insertarEvaluar";
            }
            catch(RecognitionException e)
            {
                String hdr = parser.getErrorHeader(e);
		String msg = parser.getErrorMessage(e, TermParser.tokenNames);
                map.addAttribute("usuario", usuarioManager.getUsuario(username));
                map.addAttribute("insertarEvaluar",new InsertarEvaluar());
                map.addAttribute("mensaje", hdr+" "+msg);
                map.addAttribute("nombre",insertarEvaluar.getNombre());
                map.addAttribute("termino",programa);
                map.addAttribute("guardarMenu","");
                map.addAttribute("admin","admin");
                map.addAttribute("listarTerminosMenu","");
                map.addAttribute("verTerminosPublicosMenu","");
                map.addAttribute("misPublicacionesMenu","");
                map.addAttribute("computarMenu","class=\"active\"");
                map.addAttribute("perfilMenu","");
                map.addAttribute("hrefAMiMismo","href=../../eval/"+username+"#!");
                map.addAttribute("overflow","hidden");
                map.addAttribute("anchuraDiv","1100px");
                return "insertarEvaluar";
            }
            
            Tripla tripla=term.toStringAbrvFinal();
            List<String> alias=tripla.alias;
            List<String> valores=tripla.valores;
            Corrida corr=term.evaluarFinal();
            corr.operations.add(new Integer(7));//agrego cualquier cosa
            
           //corr.terminos.set(0, tripla.term.replace("\\", "\\\\"));
           
            map.addAttribute("usuario", usuarioManager.getUsuario(username));
            map.addAttribute("nReducciones",new Float(((float)corr.reducciones/(float)(corr.reducciones+corr.traducciones))*100));
            map.addAttribute("nTraduc",new Float(((float)corr.traducciones/(float)(corr.reducciones+corr.traducciones))*100));
            map.addAttribute("nTerms",new Integer(corr.terminos.size()-1));
            map.addAttribute("nTermsPuros",new Integer(corr.lambdaTerms.size()-1));
            map.addAttribute("terminos",corr.terminos);
            map.addAttribute("operations",corr.operations);
            map.addAttribute("termPuros",corr.lambdaTerms);
            if(alias.size()!= 0)
                map.addAttribute("nAlias",new Integer(alias.size()-1));
            else
            {
                map.addAttribute("nAlias",new Integer(0));
                alias.add("");
                valores.add("");
            }
            map.addAttribute("alias",alias);
            map.addAttribute("valores",valores);
            return "evaluar";
    }
    
    public void setUsuarioManager(UsuarioManager usuarioManager) 
    {
            this.usuarioManager = usuarioManager;
    }
    
    public void setTerminoManager(TerminoManager terminoManager) 
    {
            this.terminoManager = terminoManager;
    }
}
