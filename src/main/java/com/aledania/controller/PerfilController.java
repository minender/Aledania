/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aledania.controller;

import com.aledania.dao.PublicacionDAO;
import com.aledania.entity.Publicacion;
import com.aledania.entity.PublicacionId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aledania.entity.Usuario;
import com.aledania.entity.Termino;
import com.aledania.entity.TerminoId;
import com.aledania.forms.InsertarEvaluar;
import com.aledania.forms.ModificarAliasForm;
import com.aledania.forms.ModificarForm;
import com.aledania.forms.Registro;
import com.aledania.forms.UsuarioGuardar;
import com.aledania.service.TerminoManager;
import com.aledania.service.UsuarioManager;
import com.aledania.lambdacalculo.Term;
import com.aledania.parse.IsNotInDBException;
import com.aledania.parse.TermLexer;
import com.aledania.parse.TermParser;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.BaseRecognizer;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping(value="/perfil")
public class PerfilController {
    
    @Autowired
    private UsuarioManager usuarioManager;
    @Autowired
    private TerminoManager terminoManager;
    @Autowired
    private HttpSession session;
    
    @RequestMapping(value="/{username}/close", method=RequestMethod.GET)
    public String closeSesion(@PathVariable String username, ModelMap map){
        map.addAttribute("usuariolog",new Usuario());
        map.addAttribute("mensaje", "");
        session.removeAttribute("user");
        return "redirect:../../index";
    }
    
    @RequestMapping(value="/{username}/home", method=RequestMethod.GET)
    public String perfilView(@PathVariable String username, ModelMap map) {
        if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
        {
            return "redirect:/index";
        }
        map.addAttribute("usuario", usuarioManager.getUsuario(username));
        map.addAttribute("mensaje","");
        map.addAttribute("guardarMenu","");
        map.addAttribute("listarTerminosMenu","");
        map.addAttribute("verTerminosPublicosMenu","");
        map.addAttribute("misPublicacionesMenu","");
        map.addAttribute("computarMenu","");
        map.addAttribute("perfilMenu","active");
        map.addAttribute("theoMenu","");
        map.addAttribute("helpMenu","");
        map.addAttribute("overflow","hidden");
        map.addAttribute("anchuraDiv","1100px");
        return "perfil";
    }

    @RequestMapping(value="/{username}/theo", method=RequestMethod.GET)
    public String showTheoreticalBasis(@PathVariable String username, ModelMap map) {
        if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
        {
            return "redirect:/index";
        }

        map.addAttribute("logout","logout");
        map.addAttribute("sesion","login");
        map.addAttribute("guardarMenu","");
        map.addAttribute("listarTerminosMenu","");
        map.addAttribute("verTerminosPublicosMenu","");
        map.addAttribute("misPublicacionesMenu","");
        map.addAttribute("computarMenu","");
        map.addAttribute("perfilMenu","");
        map.addAttribute("theoMenu","active");
        map.addAttribute("helpMenu","");
        return "theobasis";
    }

    @RequestMapping(value="/{username}/help", method=RequestMethod.GET)
    public String showHelp(@PathVariable String username, ModelMap map) {
        if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
        {
            return "redirect:/index";
        }

        map.addAttribute("guardarMenu","");
        map.addAttribute("listarTerminosMenu","");
        map.addAttribute("verTerminosPublicosMenu","");
        map.addAttribute("misPublicacionesMenu","");
        map.addAttribute("computarMenu","");
        map.addAttribute("perfilMenu","");
        map.addAttribute("theoMenu","");
        map.addAttribute("helpMenu","active");
        return "help";
    }
    
    @RequestMapping(value="/{username}/editar", method=RequestMethod.GET)
    public String editarView(@PathVariable String username, ModelMap map) {
        if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
        {
            return "redirect:/index";
        }
        Usuario user = usuarioManager.getUsuario(username);
        map.addAttribute("usuario",user);
        map.addAttribute("registro",new Registro(user.getNombre(), user.getApellido(), 
                                    user.getCorreo(), user.getLogin(), "", ""));
        map.addAttribute("valueSubmit", "Edit");
        map.addAttribute("isRegistro", "0");
        map.addAttribute("perfilMenu","active");
        map.addAttribute("theoMenu","");
        map.addAttribute("helpMenu","");
        return "editPerfil";
    }
    
    @RequestMapping(value="/{username}/editar", method=RequestMethod.POST)
    public String editar(@PathVariable String username, @Valid Registro registro, BindingResult bindingResult, ModelMap map) {
        if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
        {
            return "redirect:/index";
        }
        
        Usuario user = usuarioManager.getUsuario(username);
        if( bindingResult.hasErrors() )
        {
            if (!registro.getPassword().equals(registro.getPasswordConf()))
               bindingResult.rejectValue("passwordConf","error.registro","The password does not match");
//                map.addAttribute("registro", registro);
            map.addAttribute("usuario",user);
            map.addAttribute("valueSubmit", "Edit");
            map.addAttribute("isRegistro", "0");
            map.addAttribute("perfilMenu","active");
            map.addAttribute("theoMenu","");
            map.addAttribute("helpMenu","");
            return "editPerfil";
        }
        else{
            if (!registro.getPassword().equals(registro.getPasswordConf()))
            {
               if (!registro.getPassword().equals(registro.getPasswordConf()))
                  bindingResult.rejectValue("passwordConf","error.registro","The password does not match");
//                  map.addAttribute("registro", registro);
               map.addAttribute("usuario",user);
               map.addAttribute("valueSubmit", "Edit");
               map.addAttribute("isRegistro", "0");
               map.addAttribute("perfilMenu","active");
               map.addAttribute("theoMenu","");
               map.addAttribute("helpMenu","");
               return "editPerfil";
            }

            String randomchars = "hdfGLd6J4$&(3nd^{bHGF@fs";
            String pass = DigestUtils.sha512Hex(registro.getPassword()+randomchars);
            user.setNombre(registro.getNombre());
            user.setApellido(registro.getApellido());
            user.setCorreo(registro.getCorreo());
            user.setPassword(pass);

            usuarioManager.updateUsuario(user);
        }  
                       
        map.addAttribute("usuario", user);
        //map.addAttribute("mensaje","");
        map.addAttribute("guardarMenu","");
        map.addAttribute("listarTerminosMenu","");
        map.addAttribute("verTerminosPublicosMenu","");
        map.addAttribute("misPublicacionesMenu","");
        map.addAttribute("computarMenu","");
        map.addAttribute("perfilMenu","active");
        map.addAttribute("theoMenu","");
        map.addAttribute("helpMenu","");
        map.addAttribute("overflow","hidden");
        map.addAttribute("anchuraDiv","1100px");
        return "perfil";
    }
    /**
     *
     * @param username
     * @param map
     * @return
     */
    @RequestMapping(value="/{username}/guardar", method=RequestMethod.GET)
    public String guardarView(@PathVariable String username,ModelMap map) {
        if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
        {
            return "redirect:/index";
        }
        map.addAttribute("usuario", usuarioManager.getUsuario(username));
        map.addAttribute("usuarioGuardar",new UsuarioGuardar());
        map.addAttribute("modificar",new Integer(0));
        map.addAttribute("termino","");
        map.addAttribute("alias","");
        map.addAttribute("mensaje", "");
        map.addAttribute("admin","admin");
        map.addAttribute("guardarMenu","active");
        map.addAttribute("listarTerminosMenu","");
        map.addAttribute("verTerminosPublicosMenu","");
        map.addAttribute("misPublicacionesMenu","");
        map.addAttribute("computarMenu","");
        map.addAttribute("perfilMenu","");
        map.addAttribute("theoMenu","");
        map.addAttribute("helpMenu","");
        map.addAttribute("overflow","hidden");
        map.addAttribute("anchuraDiv","1100px");
        
        return "introducirTermino";
    }
    
    @RequestMapping(value="/{username}/guardar", method=RequestMethod.POST)
    public String guardar(@Valid UsuarioGuardar usuarioGuardar, BindingResult bindingResult, @PathVariable String username, ModelMap map)
    {
            if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
            {
                return "redirect:/index";
            }
            //Aqui hay que validar sintaxis se puede hacer como un aspecto con 
            // un @Valid
            //aqui se acomoda la estructura de la entidad Termino ya que en la
            // vista lo que se construyo fue un TerminoId nada mas y se uso el 
            // campo login para guardar el String combinador
            if(bindingResult.hasErrors())
            {
                map.addAttribute("usuario", usuarioManager.getUsuario(username));
                map.addAttribute("modificar",new Integer(0));
                map.addAttribute("termino",usuarioGuardar.getTermino());
                map.addAttribute("alias",usuarioGuardar.getAlias());
                map.addAttribute("mensaje", "");
                map.addAttribute("admin","admin");
                map.addAttribute("guardarMenu","active");
                map.addAttribute("listarTerminosMenu","");
                map.addAttribute("verTerminosPublicosMenu","");
                map.addAttribute("misPublicacionesMenu","");
                map.addAttribute("computarMenu","");
                map.addAttribute("perfilMenu","");
                map.addAttribute("theoMenu","");
                map.addAttribute("helpMenu","");
                map.addAttribute("overflow","hidden");
                map.addAttribute("anchuraDiv","1100px");
                return "introducirTermino";
            }
            
            
            TerminoId terminoid = new TerminoId();
            String alias=usuarioGuardar.getAlias();
            if(username.equals("admin"))
                alias=alias.substring(0, alias.length()-1);
            terminoid.setAlias(alias);
            terminoid.setLogin(username);
            Termino termino = new Termino();
            Usuario user=usuarioManager.getUsuario(username);
            termino.setUsuario(user);
            termino.setId(terminoid);
            TerminoId terminoid2=new TerminoId();
            terminoid2.setLogin(username);
            String programa=usuarioGuardar.getTermino();
            
            //Hay que construir un Term aqui con el String termino.combinador
            //para luego traducir, hace falta construir un parse   
            ANTLRStringStream in = new ANTLRStringStream(programa);
            TermLexer lexer = new TermLexer(in);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            TermParser parser = new TermParser(tokens);
            Term term;
            try //si la sintanxis no es correcta ocurre una Exception
            {

            
                //aqui hay que hacer un query para verificar que el combinador 
                //es no esta ya en la BD, poner esta verificacion en el dig de sec

                Termino terminoEnBD=terminoManager.getTermino(terminoid); //arreglar solo consigue los tuyos mas no los de admin y publico
                if(terminoEnBD == null)
                {
                    //System.out.println(terminoManager.getTermino(terminoid));
                    term=parser.t(terminoid2,terminoManager);
                    term.setAlias(terminoid.getAlias());
                    //aqui se traduce y luego se llama a toString para tener el
                    //combinador en String
                    termino.setTermObject(term);//este metodo serializa de ademas de setear el terminoObject
                    termino.setCombinador(term.traducBD().toStringFinal());
                    Termino termino2=terminoManager.getCombinador(username, termino.getCombinador());
                    if(termino2 != null) 
                        throw new AlphaEquivalenceException(termino2.getId().getAlias());
                    //termino.setSerializado(ToString.toString(term));
                    //verificar si el String combinador existe pero con otro alias
                    termino.getId().setLogin(username);
                    terminoManager.addTermino(termino);
                    map.addAttribute("mensaje", "Your term has been saved successfully");
                    map.addAttribute("usuario", usuarioManager.getUsuario(username));
                    map.addAttribute("guardarMenu","");
                    map.addAttribute("listarTerminosMenu","");
                    map.addAttribute("verTerminosPublicosMenu","");
                    map.addAttribute("misPublicacionesMenu","");
                    map.addAttribute("computarMenu","");
                    map.addAttribute("perfilMenu","active");
                    map.addAttribute("theoMenu","");
                    map.addAttribute("helpMenu","");
                    map.addAttribute("overflow","hidden");
                    map.addAttribute("anchuraDiv","1100px");
                    return "perfil";
                }
                else
                {
                    map.addAttribute("usuarioGuardar",new UsuarioGuardar());
                    map.addAttribute("usuario",user);
                    map.addAttribute("modificar",new Integer(0));
                    map.addAttribute("mensaje", "There is already a term with the alias you have placed");
                    map.addAttribute("termino",programa);
                    map.addAttribute("admin","admin");
                    if(username.equals("admin"))
                        map.addAttribute("alias",alias+"_");
                    else
                        map.addAttribute("alias",alias);
                    map.addAttribute("guardarMenu","active");
                    map.addAttribute("listarTerminosMenu","");
                    map.addAttribute("verTerminosPublicosMenu","");
                    map.addAttribute("misPublicacionesMenu","");
                    map.addAttribute("computarMenu","");
                    map.addAttribute("perfilMenu","");
                    map.addAttribute("theoMenu","");
                    map.addAttribute("helpMenu","");
                    map.addAttribute("overflow","hidden");
                    map.addAttribute("anchuraDiv","1100px");
                    return "introducirTermino";
                }
            }
            catch(AlphaEquivalenceException e)
            {
                map.addAttribute("usuarioGuardar",new UsuarioGuardar());
                map.addAttribute("usuario",user);
                map.addAttribute("modificar",new Integer(0));
                map.addAttribute("mensaje", "You can not enter your term since it is alpha equivalent to a existing term "+e.alias);
                map.addAttribute("termino",programa);
                map.addAttribute("admin","admin");
                if(username.equals("admin"))
                   map.addAttribute("alias",alias+"_");
                else
                   map.addAttribute("alias",alias);
                map.addAttribute("guardarMenu","active");
                map.addAttribute("listarTerminosMenu","");
                map.addAttribute("verTerminosPublicosMenu","");
                map.addAttribute("misPublicacionesMenu","");
                map.addAttribute("computarMenu","");
                map.addAttribute("perfilMenu","");
                map.addAttribute("theoMenu","");
                map.addAttribute("helpMenu","");
                map.addAttribute("overflow","hidden");
                map.addAttribute("anchuraDiv","1100px");
                return "introducirTermino";
            }
            catch(IsNotInDBException e)
            {
                String hdr = parser.getErrorHeader(e);
		String msg = parser.getErrorMessage(e, TermParser.tokenNames);
                map.addAttribute("usuario",user);
                map.addAttribute("usuarioGuardar",new UsuarioGuardar());
                map.addAttribute("modificar",new Integer(0));
                map.addAttribute("mensaje", hdr +((IsNotInDBException)e).message);
                map.addAttribute("termino",programa);
                map.addAttribute("admin","admin");
                if(username.equals("admin"))
                   map.addAttribute("alias",alias+"_");
                else
                   map.addAttribute("alias",alias);
                map.addAttribute("guardarMenu","active");
                map.addAttribute("listarTerminosMenu","");
                map.addAttribute("verTerminosPublicosMenu","");
                map.addAttribute("misPublicacionesMenu","");
                map.addAttribute("computarMenu","");
                map.addAttribute("perfilMenu","");
                map.addAttribute("theoMenu","");
                map.addAttribute("helpMenu","");
                map.addAttribute("overflow","hidden");
                map.addAttribute("anchuraDiv","1100px");
                return "introducirTermino";
            }
            catch(RecognitionException e)
            {
                String hdr = parser.getErrorHeader(e);
		String msg = parser.getErrorMessage(e, TermParser.tokenNames);
                map.addAttribute("usuario",user);
                map.addAttribute("usuarioGuardar",new UsuarioGuardar());
                map.addAttribute("modificar",new Integer(0));
                map.addAttribute("mensaje", hdr+" "+msg);
                map.addAttribute("termino",programa);
                map.addAttribute("admin","admin");
                if(username.equals("admin"))
                   map.addAttribute("alias",alias+"_");
                else
                   map.addAttribute("alias",alias);
                map.addAttribute("guardarMenu","active");
                map.addAttribute("listarTerminosMenu","");
                map.addAttribute("verTerminosPublicosMenu","");
                map.addAttribute("misPublicacionesMenu","");
                map.addAttribute("computarMenu","");
                map.addAttribute("perfilMenu","");
                map.addAttribute("theoMenu","");
                map.addAttribute("helpMenu","");
                map.addAttribute("overflow","hidden");
                map.addAttribute("anchuraDiv","1100px");
                return "introducirTermino";
            }
    }
    
    @RequestMapping(value="/{username}/modificaralias", method=RequestMethod.GET)
    public String modificarAliasView(ModelMap map, @PathVariable String username, @RequestParam("aliasv") String aliasv) 
    {
        if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
        {
            return "redirect:/index";
        }
        //map.addAttribute("usuario", usuarioManager.getUsuario(username));
        map.addAttribute("usuario", usuarioManager.getUsuario(username));
        map.addAttribute("termino","");
        map.addAttribute("modificarAliasForm",new ModificarAliasForm());
        map.addAttribute("modificar",new Integer(2));
        if(username.equals("admin"))
            map.addAttribute("alias",aliasv+"_");
        else
            map.addAttribute("alias",aliasv);
        map.addAttribute("mensaje", "");
        map.addAttribute("admin","admin");
        map.addAttribute("overflow","hidden");
        map.addAttribute("anchuraDiv","1100px");
        
        return "introducirTermino";
    }
    
    @RequestMapping(value="/{username}/modificar", method=RequestMethod.GET)
    public String modificarView(ModelMap map, @PathVariable String username, @RequestParam("alias") String alias) 
    {
        if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
        {
            return "redirect:/index";
        }
        //map.addAttribute("usuario", usuarioManager.getUsuario(username));
        TerminoId id= new TerminoId();
        id.setAlias(alias);
        id.setLogin(username);
        Termino t=terminoManager.getTermino(id);
        String term=t.getTermObject().toStringFinal();
        String termino;
        termino = term.replace("\\","" ).replace("}", "").replaceAll("[_][{]", "");
        map.addAttribute("usuario", usuarioManager.getUsuario(username));
        map.addAttribute("termino",termino);
        map.addAttribute("modificarForm",new UsuarioGuardar());
        map.addAttribute("modificar",new Integer(1));
        map.addAttribute("alias","");
        map.addAttribute("mensaje", "");
        map.addAttribute("admin","admin");
        map.addAttribute("overflow","hidden");
        map.addAttribute("anchuraDiv","1100px");
        
        return "introducirTermino";
    }
    
    @RequestMapping(value="/{username}/modificaralias", method=RequestMethod.POST)
    public String modificarAlias(@Valid ModificarAliasForm modificarAliasForm, BindingResult bindingResult, @PathVariable String username, @RequestParam("aliasv") String aliasv, ModelMap map)
    {
            if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
            {
                return "redirect:/index";
            }
            //Aqui hay que validar sintaxis se puede hacer como un aspecto con 
            // un @Valid
        
            //aqui se acomoda la estructura de la entidad Termino ya que en la
            // vista lo que se construyo fue un TerminoId nada mas y se uso el 
            // campo login para guardar el String combinador
            if(bindingResult.hasErrors())
            {
                map.addAttribute("usuario", usuarioManager.getUsuario(username));
                map.addAttribute("modificar",new Integer(2));
                map.addAttribute("termino","");
                map.addAttribute("alias",modificarAliasForm.getAlias());
                map.addAttribute("mensaje", "");
                map.addAttribute("admin","admin");
                map.addAttribute("overflow","hidden");
                map.addAttribute("anchuraDiv","1100px");
                return "introducirTermino";
            }
        
            String aliasNuevo=modificarAliasForm.getAlias();
            if(username.equals("admin"))
                aliasNuevo=aliasNuevo.substring(0, aliasNuevo.length()-1);
            TerminoId nuevo=new TerminoId();
            TerminoId anterior=new TerminoId();
            nuevo.setAlias(aliasNuevo);
            nuevo.setLogin(username);
            anterior.setAlias(aliasv);
            anterior.setLogin(username);
            Usuario u =usuarioManager.getUsuario(username);
            
            
            Termino terminoEnBD=null;
            if(!aliasv.equals(aliasNuevo))
                terminoEnBD=terminoManager.getTermino(nuevo); //arreglar solo consigue los tuyos mas no los de admin y publico
            if(terminoEnBD == null)
            {
                nuevo.setLogin(username);
                terminoManager.modificarAlias(anterior,nuevo);
                map.addAttribute("mensaje", "Your Alias ​​has been modified successfully");
                map.addAttribute("usuario",u);
                map.addAttribute("guardarMenu","");
                map.addAttribute("listarTerminosMenu","");
                map.addAttribute("verTerminosPublicosMenu","");
                map.addAttribute("misPublicacionesMenu","");
                map.addAttribute("computarMenu","");
                map.addAttribute("perfilMenu","active");
                map.addAttribute("theoMenu","");
                map.addAttribute("helpMenu","");
                map.addAttribute("overflow","hidden");
                map.addAttribute("anchuraDiv","1100px");
                return "perfil";
            }
            else
            {
                map.addAttribute("modificarAliasForm",new ModificarAliasForm());
                map.addAttribute("usuario",u);
                map.addAttribute("modificar",new Integer(2));
                map.addAttribute("mensaje", "There is already a term with the alias you have placed");
                map.addAttribute("termino","");
                map.addAttribute("admin","admin");
                if(username.equals("admin"))
                    map.addAttribute("alias",aliasNuevo+"_");
                else
                    map.addAttribute("alias",aliasNuevo);
                map.addAttribute("overflow","hidden");
                map.addAttribute("anchuraDiv","1100px");
                return "introducirTermino";
            }
    }
    
    @RequestMapping(value="/{username}/modificar", method=RequestMethod.POST)
    public String modificar(@Valid ModificarForm modificarForm, BindingResult bindingResult, @PathVariable String username, @RequestParam("alias") String alias, ModelMap map)
    {
            if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
            {
                return "redirect:/index";
            }
            //Aqui hay que validar sintaxis se puede hacer como un aspecto con 
            // un @Valid
        
            //aqui se acomoda la estructura de la entidad Termino ya que en la
            // vista lo que se construyo fue un TerminoId nada mas y se uso el 
            // campo login para guardar el String combinador
            if(bindingResult.hasErrors())
            {
                map.addAttribute("usuario", usuarioManager.getUsuario(username));
                map.addAttribute("modificar",new Integer(1));
                map.addAttribute("termino",modificarForm.getTermino());
                map.addAttribute("alias","");
                map.addAttribute("mensaje", "");
                map.addAttribute("admin","admin");
                map.addAttribute("overflow","hidden");
                map.addAttribute("anchuraDiv","1100px");
                return "introducirTermino";
            }
        
            TerminoId terminoid=new TerminoId();
            terminoid.setAlias(alias);
            terminoid.setLogin(username);
            Termino termino = new Termino();
            Usuario u =usuarioManager.getUsuario(username);
            termino.setUsuario(u);
            termino.setId(terminoid);
            TerminoId terminoid2=new TerminoId();
            terminoid2.setLogin(terminoid.getLogin());
            String programa=modificarForm.getTermino();
            
            //Hay que construir un Term aqui con el String termino.combinador
            //para luego traducir, hace falta construir un parse   
            ANTLRStringStream in = new ANTLRStringStream(programa);
            TermLexer lexer = new TermLexer(in);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            TermParser parser = new TermParser(tokens);
            Term term;
            try //si la sintanxis no es correcta ocurre una Exception
            {
                term=parser.t(terminoid2,terminoManager);
                term.alias=alias;
                //aqui se traduce y luego se llama a toString para tener el
                //combinador en String
                termino.setTermObject(term);//este metodo serializa de ademas de setear el terminoObject
                termino.setCombinador(term.traducBD().toStringFinal());
                
            
                //verificar si el String combinador existe pero con otro alias
                Termino termino2=terminoManager.getCombinador(username, termino.getCombinador());
                //revisar si la instruccion de arriva arroja una excepcion para diferenciarla de las del parse
                if(termino2 != null) 
                {
                    if(!termino2.getId().getAlias().equals(alias))
                        throw new AlphaEquivalenceException(termino2.getId().getAlias());
                }

                terminoManager.modificarTermino(termino);
                map.addAttribute("mensaje", "Your term has been modified successfully");
                map.addAttribute("usuario", usuarioManager.getUsuario(username));
                map.addAttribute("guardarMenu","");
                map.addAttribute("listarTerminosMenu","");
                map.addAttribute("verTerminosPublicosMenu","");
                map.addAttribute("misPublicacionesMenu","");
                map.addAttribute("computarMenu","");
                map.addAttribute("perfilMenu","active");
                map.addAttribute("theoMenu","");
                map.addAttribute("helpMenu","");
                map.addAttribute("overflow","hidden");
                map.addAttribute("anchuraDiv","1100px");
                return "perfil";
            }
            catch(AlphaEquivalenceException e)
            {
                map.addAttribute("terminoid",new TerminoId());
                map.addAttribute("usuario",usuarioManager.getUsuario(username));
                map.addAttribute("modificar",new Integer(1));
                map.addAttribute("mensaje", "You can not enter your term since it is alpha equivalent to a existing term "+e.alias);
                map.addAttribute("termino",programa);
                map.addAttribute("alias","");
                map.addAttribute("admin","admin");
                map.addAttribute("overflow","hidden");
                map.addAttribute("anchuraDiv","1100px");
                return "introducirTermino";
            }
            catch(IsNotInDBException e)
            {
                String hdr = parser.getErrorHeader(e);
		String msg = parser.getErrorMessage(e, TermParser.tokenNames);
                map.addAttribute("terminoid",new TerminoId());
                map.addAttribute("usuario",usuarioManager.getUsuario(username));                
                map.addAttribute("modificar",new Integer(1));
                map.addAttribute("mensaje", hdr +((IsNotInDBException)e).message);
                map.addAttribute("termino",programa);
                map.addAttribute("alias","");
                map.addAttribute("admin","admin");
                map.addAttribute("overflow","hidden");
                map.addAttribute("anchuraDiv","1100px");
                return "introducirTermino";
            }
            catch(RecognitionException e)
            {
                String hdr = parser.getErrorHeader(e);
		String msg = parser.getErrorMessage(e, TermParser.tokenNames);
                map.addAttribute("terminoid",new TerminoId());
                map.addAttribute("usuario",usuarioManager.getUsuario(username));                
                map.addAttribute("modificar",new Integer(1));
                map.addAttribute("mensaje", hdr+" "+msg);
                map.addAttribute("termino",programa);
                map.addAttribute("alias","");
                map.addAttribute("admin","admin");
                map.addAttribute("overflow","hidden");
                map.addAttribute("anchuraDiv","1100px");
                return "introducirTermino";
            }
    }
    
    @RequestMapping(value="/{username}/listar", method=RequestMethod.GET)
    public String listarView(@PathVariable String username, ModelMap map, @RequestParam("comb") String comb) 
    {
        if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
        {
            return "redirect:/index";
        }    
        map.addAttribute("titulo", "My Terms");
        map.addAttribute("publico", "publico");
        map.addAttribute("admin","admin");
        map.addAttribute("yes","yes");
        map.addAttribute("usuario", usuarioManager.getUsuario(username));
        map.addAttribute("terminos", terminoManager.getAllTerminos(username));
        if(comb.equals("y"))
            map.addAttribute("comb", new Integer(1));
        else
            map.addAttribute("comb", new Integer(0));
        map.addAttribute("mensaje","");
        map.addAttribute("accion","listar");
        map.addAttribute("perfil",new Integer(1));
        map.addAttribute("click","no");
        map.addAttribute("publicaciones",new Integer(0));
        map.addAttribute("guardarMenu","");
        map.addAttribute("listarTerminosMenu","active");
        map.addAttribute("verTerminosPublicosMenu","");
        map.addAttribute("misPublicacionesMenu","");
        map.addAttribute("computarMenu","");
        map.addAttribute("perfilMenu","");
        map.addAttribute("theoMenu","");
        map.addAttribute("helpMenu","");
        map.addAttribute("overflow","scroll");
        map.addAttribute("anchuraDiv","1163px");
        
        return "listar";
    }
    
    @RequestMapping(value="/{username}/mispublic", method=RequestMethod.GET)
    public String misPublicacionesView(@PathVariable String username, ModelMap map, @RequestParam("comb") String comb) 
    {
        if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
        {
            return "redirect:/index";
        }
        map.addAttribute("titulo", "My Publications");
        map.addAttribute("publico", "publico");
        map.addAttribute("admin","admin");
        map.addAttribute("yes","yes");
        map.addAttribute("usuario", usuarioManager.getUsuario(username));
        map.addAttribute("terminos", terminoManager.getAllPublicaciones(username));
        if(comb.equals("y"))
            map.addAttribute("comb", new Integer(1));
        else
            map.addAttribute("comb", new Integer(0));
        map.addAttribute("mensaje","");
        map.addAttribute("accion","mispublic");
        map.addAttribute("perfil",new Integer(1));
        map.addAttribute("click","no");
        map.addAttribute("publicaciones",new Integer(1));
        map.addAttribute("guardarMenu","");
        map.addAttribute("listarTerminosMenu","");
        map.addAttribute("verTerminosPublicosMenu","");
        map.addAttribute("misPublicacionesMenu","active");
        map.addAttribute("computarMenu","");
        map.addAttribute("perfilMenu","");
        map.addAttribute("theoMenu","");
        map.addAttribute("helpMenu","");
        map.addAttribute("overflow","scroll");
        map.addAttribute("anchuraDiv","1163px");
        
        return "listar";
    }
    
    @RequestMapping(value="/{username}/listarocult", method=RequestMethod.GET)
    public String listarOcultEdicionView(@PathVariable String username, ModelMap map, @RequestParam("comb") String comb) 
    {
        if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
        {
            return "redirect:/index";
        }
        map.addAttribute("titulo", "My Terms");
        map.addAttribute("publico", "publico");
        map.addAttribute("admin","admin");
        map.addAttribute("yes","yes");
        map.addAttribute("usuario", usuarioManager.getUsuario("publico"));
        map.addAttribute("terminos", terminoManager.getAllTerminos(username));
        if(comb.equals("y"))
            map.addAttribute("comb", new Integer(1));
        else
            map.addAttribute("comb", new Integer(0));
        map.addAttribute("mensaje","");
        map.addAttribute("accion","listarocult");
        map.addAttribute("perfil",new Integer(0));
        map.addAttribute("click","yes");
        map.addAttribute("publicaciones",new Integer(0));
        
        return "listar";
    }
    
    @RequestMapping(value="/{username}/publico", method=RequestMethod.GET)
    public String listarView(ModelMap map, @RequestParam("comb") String comb, @PathVariable String username) 
    {
        if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
        {
            return "redirect:/index";
        }
        map.addAttribute("titulo", "Public Terms");
        map.addAttribute("publico", "publico");
        map.addAttribute("admin","admin");
        map.addAttribute("yes","yes");
        map.addAttribute("usuario", usuarioManager.getUsuario("publico"));
        map.addAttribute("terminos", terminoManager.getAllTerminos("publico"));
        if(comb.equals("y"))
            map.addAttribute("comb", new Integer(1));
        else
            map.addAttribute("comb", new Integer(0));
        map.addAttribute("mensaje","");
        map.addAttribute("accion","publico");
        map.addAttribute("perfil",new Integer(1));
        map.addAttribute("click","no");
        map.addAttribute("publicaciones",new Integer(0));
        map.addAttribute("guardarMenu","");
        map.addAttribute("listarTerminosMenu","");
        map.addAttribute("verTerminosPublicosMenu","active");
        map.addAttribute("misPublicacionesMenu","");
        map.addAttribute("computarMenu","");
        map.addAttribute("perfilMenu","");
        map.addAttribute("theoMenu","");
        map.addAttribute("helpMenu","");
        map.addAttribute("overflow","scroll");
        map.addAttribute("anchuraDiv","1163px");
        
        return "listar";
    }
    
    @RequestMapping(value="/{username}/publiconoclick", method=RequestMethod.GET)
    public String listarViewNoClick(ModelMap map, @RequestParam("comb") String comb, @PathVariable String username) 
    {
        if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
        {
            return "redirect:/index";
        }
        map.addAttribute("titulo", "Public Terms");
        map.addAttribute("publico", "publico");
        map.addAttribute("admin","admin");
        map.addAttribute("yes","yes");
        map.addAttribute("usuario", usuarioManager.getUsuario("publico"));
        map.addAttribute("terminos", terminoManager.getAllTerminos("publico"));
        if(comb.equals("y"))
            map.addAttribute("comb", new Integer(1));
        else
            map.addAttribute("comb", new Integer(0));
        map.addAttribute("mensaje","");
        map.addAttribute("accion","publiconoclick");
        map.addAttribute("perfil",new Integer(0));
        map.addAttribute("click","yes");
        map.addAttribute("publicaciones",new Integer(0));
        
        return "listar";
    }
    
    @RequestMapping(value="/{username}/predef", method=RequestMethod.GET)
    public String predefView(ModelMap map, @RequestParam("comb") String comb, @PathVariable String username) 
    {
        if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
        {
            return "redirect:/index";
        }
        map.addAttribute("titulo", "Predefined Terms");
        map.addAttribute("publico", "publico");
        map.addAttribute("admin","admin");
        map.addAttribute("yes","yes");
        map.addAttribute("usuario", usuarioManager.getUsuario("publico"));
        map.addAttribute("terminos", terminoManager.getAllTerminos("admin"));
        if(comb.equals("y"))
            map.addAttribute("comb", new Integer(1));
        else
            map.addAttribute("comb", new Integer(0));
        map.addAttribute("mensaje","");
        map.addAttribute("accion","predef");
        map.addAttribute("perfil",new Integer(0));
        map.addAttribute("click","yes");
        map.addAttribute("publicaciones",new Integer(0));
        
        return "listar";
    }
    
    @RequestMapping(value="/{username}/eliminar", 
            method=RequestMethod.GET)
    public String eliminarTermino(@PathVariable String username, ModelMap map, @RequestParam("alias") String alias)
    {
        if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
        {
            return "redirect:/index";
        }
        TerminoId id=new TerminoId( alias, username);
        terminoManager.deleteTermino(id);
        return "redirect:../../perfil/"+username+"/listar?comb=n";
    }
    
    @RequestMapping(value="/{username}/eliminarpubl", 
            method=RequestMethod.GET)
    public String eliminarPublicacion(@PathVariable String username, ModelMap map, @RequestParam("alias") String alias)
    {
        if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
        {
            return "redirect:/index";
        }
        TerminoId id=new TerminoId( alias, username);
        terminoManager.deletePublicacion(id);
        return "redirect:../../perfil/"+username+"/mispublic?comb=n";
    }
    
    @RequestMapping(value="/{username}/publicar", method=RequestMethod.GET)
    public String publicarTermino(@PathVariable String username, ModelMap map, @RequestParam("alias") String alias)
    {
        if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
        {
            return "redirect:/index";
        }
        TerminoId terminoid = new TerminoId(alias,username);
        PublicacionId publicacionId = new PublicacionId(alias.substring(0, alias.length()-1),username);
        TerminoId publicTerminoid = new TerminoId(alias.substring(0, alias.length()-1),"publico");
        Termino termino0 = terminoManager.getTermino(publicTerminoid);
        if(termino0 != null)
        {
            map.addAttribute("titulo", "My Terms");
            map.addAttribute("publico", "publico");
            map.addAttribute("admin","admin");
            map.addAttribute("usuario", usuarioManager.getUsuario(username));
            map.addAttribute("terminos", terminoManager.getAllTerminos(username));
            map.addAttribute("comb", new Integer(0));
            map.addAttribute("perfil",new Integer(1));
            map.addAttribute("accion","listar");
            map.addAttribute("mensaje", "You can not publish your term since there is already a public term with the alias "+publicacionId.getAlias());
            map.addAttribute("publicaciones",new Integer(0));
            map.addAttribute("guardarMenu","");
            map.addAttribute("listarTerminosMenu","active");
            map.addAttribute("verTerminosPublicosMenu","");
            map.addAttribute("misPublicacionesMenu","");
            map.addAttribute("computarMenu","");
            map.addAttribute("perfilMenu","");
            map.addAttribute("theoMenu","");
            map.addAttribute("helpMenu","");
            map.addAttribute("overflow","scroll");
            map.addAttribute("anchuraDiv","1163px");
            return "listar";
        }
        Termino termino = terminoManager.getTermino(terminoid);
        Termino termino2 = terminoManager.getCombinador("publico", termino.getCombinador());
        Termino termino3 = terminoManager.getCombinador("admin", termino.getCombinador());
        try{
            if(termino2 != null)
                throw new AlphaEquivalenceException(termino2.getId().getAlias());
            else if(termino3 != null)
                throw new AlphaEquivalenceException(termino3.getId().getAlias());
        }
        catch(AlphaEquivalenceException e)
        {
            map.addAttribute("titulo", "My Terms");
            map.addAttribute("publico", "publico");
            map.addAttribute("admin","admin");
            map.addAttribute("usuario", usuarioManager.getUsuario(username));
            map.addAttribute("terminos", terminoManager.getAllTerminos(username));
            map.addAttribute("comb", new Integer(0));
            map.addAttribute("perfil",new Integer(1));
            map.addAttribute("accion","listar");
            map.addAttribute("mensaje", "You can not publish your term since it is alpha equivalent to a existing public term "+e.alias);
            map.addAttribute("publicaciones",new Integer(0));
            map.addAttribute("guardarMenu","");
            map.addAttribute("listarTerminosMenu","active");
            map.addAttribute("verTerminosPublicosMenu","");
            map.addAttribute("misPublicacionesMenu","");
            map.addAttribute("computarMenu","");
            map.addAttribute("perfilMenu","");
            map.addAttribute("theoMenu","");
            map.addAttribute("helpMenu","");
            map.addAttribute("overflow","scroll");
            map.addAttribute("anchuraDiv","1163px");
            return "listar";
        }
        PublicacionId publicacionId2 = new PublicacionId(alias.substring(0, alias.length()-1),username);
        Publicacion publicacion = new Publicacion(publicacionId2, usuarioManager.getUsuario(username));
        Termino terminoPublico=new Termino();
        TerminoId publicTerminoid2 = new TerminoId(alias.substring(0, alias.length()-1),"publico");
        terminoPublico.setId(publicTerminoid2);
        terminoPublico.setCombinador(termino.getCombinador());
        Term cambioDeAlias = termino.getTermObject();
        cambioDeAlias.setAlias(alias.substring(0, alias.length()-1));
        terminoPublico.setTermObject(cambioDeAlias);
        terminoPublico.setUsuario(usuarioManager.getUsuario("publico"));
        terminoManager.addPublicacion(terminoPublico,publicacion);
        map.addAttribute("mensaje", "Your term has been published successfully");
        map.addAttribute("usuario", usuarioManager.getUsuario(username));
        map.addAttribute("guardarMenu","");
        map.addAttribute("listarTerminosMenu","");
        map.addAttribute("verTerminosPublicosMenu","");
        map.addAttribute("misPublicacionesMenu","");
        map.addAttribute("computarMenu","");
        map.addAttribute("perfilMenu","active");
        map.addAttribute("theoMenu","");
        map.addAttribute("helpMenu","");
        map.addAttribute("overflow","hidden");
        map.addAttribute("anchuraDiv","1100px");
        
        return "perfil";
    }
    
    @RequestMapping(value="/{username}/ingresar", method=RequestMethod.GET)
    public String insertarEvaluarView(@PathVariable String username, ModelMap map)
    {
        if ( (Usuario)session.getAttribute("user") == null || !((Usuario)session.getAttribute("user")).getLogin().equals(username))
        {
            return "redirect:/index";
        }
        map.addAttribute("usuario", usuarioManager.getUsuario(username));
        //map.addAttribute("terminoid",new TerminoId());
        map.addAttribute("insertarEvaluar",new InsertarEvaluar());
        map.addAttribute("mensaje","");
        //map.addAttribute("modificar",new Integer(0));
        map.addAttribute("nombre","");
        map.addAttribute("termino","");
        map.addAttribute("admin","admin");
        map.addAttribute("guardarMenu","");
        map.addAttribute("listarTerminosMenu","");
        map.addAttribute("verTerminosPublicosMenu","");
        map.addAttribute("misPublicacionesMenu","");
        map.addAttribute("computarMenu","active");
        map.addAttribute("perfilMenu","");
        map.addAttribute("theoMenu","");
        map.addAttribute("helpMenu","");
        map.addAttribute("hrefAMiMismo","href=ingresar#!");
        map.addAttribute("overflow","hidden");
        map.addAttribute("anchuraDiv","1100px");
        
        return "insertarEvaluar";
    }
    
    public void setUsuarioManager(UsuarioManager usuarioManager) 
    {
            this.usuarioManager = usuarioManager;
    }
    
    public void setTerminoManager(TerminoManager terminoManager) 
    {
            this.terminoManager = terminoManager;
    }

    private static class AlphaEquivalenceException extends Exception
    {

        public String alias;
                
        public AlphaEquivalenceException(String ali) 
        {
            alias=ali;
        }
    }

    
}
