package com.aledania.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aledania.entity.Usuario;
import com.aledania.forms.Registro;
import com.aledania.service.UsuarioManager;
import java.util.List;
import javax.validation.Valid;
import org.apache.commons.codec.digest.DigestUtils;

@Controller
@RequestMapping(value="/registro")
public class RegistroController {
	
	@Autowired
	private UsuarioManager usuarioManager;
        
        @RequestMapping(method=RequestMethod.GET, params="new")
        public String createUsuarioProfile(ModelMap map)
        {
            map.addAttribute("registro",new Registro());
            map.addAttribute("valueSubmit", "Check In");
            map.addAttribute("isRegistro", "1");
            return "registro";
            //map.addAttribute("usuario",new Usuario());
            //return "registro";
        }
        
        @RequestMapping(method=RequestMethod.POST)
        public String addUsuarioFromForm(@Valid Registro registro,BindingResult bindingResult, ModelMap map)
        {
            Usuario user = usuarioManager.getUsuario(registro.getLogin());
            if( bindingResult.hasErrors() )
            {
                if (!registro.getPassword().equals(registro.getPasswordConf()))
                  bindingResult.rejectValue("passwordConf","error.registro","The password does not match");
                if (user != null)
                  bindingResult.rejectValue("login","error.registro","Username not available");

                map.addAttribute("valueSubmit", "Check In");
                map.addAttribute("isRegistro", "1");
                return "registro";
            }
            else{

                if (user != null || !registro.getPassword().equals(registro.getPasswordConf()))
                {
                  if (!registro.getPassword().equals(registro.getPasswordConf()))
                    bindingResult.rejectValue("passwordConf","error.registro","The password does not match");
                  if (user != null)
                    bindingResult.rejectValue("login","error.registro","Username not available");

                  map.addAttribute("valueSubmit", "Check In");
                  map.addAttribute("isRegistro", "1");
                  return "registro";
                }

                String randomchars = "hdfGLd6J4$&(3nd^{bHGF@fs";
                String pass = DigestUtils.sha512Hex(registro.getPassword()+randomchars);
                user = new Usuario(registro.getLogin(), registro.getNombre(), 
                                           registro.getApellido(), registro.getCorreo(), pass);
                usuarioManager.addUsuario(user);
            }
            
            return "redirect:registro/"+registro.getLogin();
            /*if( bindingResult.hasErrors() )
            {
                return "registro";
            }
            else{
                usuarioManager.addUsuario(usuario);
                return "redirect:registro/"+usuario.getLogin();
            }*/
        }
        
        @RequestMapping(value="/{username}", method=RequestMethod.GET)
        public String showUsuarioProfile(@PathVariable String username, ModelMap map) {
        map.addAttribute("usuario", usuarioManager.getUsuario(username));
            return "registrado";
        }
        public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
        }
}
