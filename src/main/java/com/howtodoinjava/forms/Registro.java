/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.forms;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author federico
 */
public class Registro {
 
    @NotEmpty(message="no debe dejar este campo vacío")
    private String nombre;
    @NotEmpty(message="no debe dejar este campo vacío")
    private String apellido;
    @NotEmpty(message="no debe dejar este campo vacío")
    @Email(message="debe colocar un email válido")
    private String correo;
    @NotEmpty(message="no debe dejar este campo vacío")
    @Length(min=3, max=20, message="login debe tener entre 3 y 20 caracteres")
    @Pattern(regexp="^[a-zA-Z0-9]+$",message="login debe ser alphanumerico sin espacios")
    private String login;
    @NotEmpty(message="no debe dejar este campo vacío")
    private String password;
    @NotEmpty(message="no debe dejar este campo vacío")
    private String passwordConf;
    
    public Registro() {
    }

    public Registro(String nombre, String apellido, String correo, String login,
                    String password, String passwordConf) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.login = login;
        this.password = password;
        this.passwordConf = passwordConf;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordConf() {
        return passwordConf;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordConf(String passwordConf) {
        this.passwordConf = passwordConf;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (this.nombre != null ? this.nombre.hashCode() : 0);
        hash = 79 * hash + (this.apellido != null ? this.apellido.hashCode() : 0);
        hash = 79 * hash + (this.correo != null ? this.correo.hashCode() : 0);
        hash = 79 * hash + (this.login != null ? this.login.hashCode() : 0);
        hash = 79 * hash + (this.password != null ? this.password.hashCode() : 0);
        hash = 79 * hash + (this.passwordConf != null ? this.passwordConf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Registro other = (Registro) obj;
        if ((this.nombre == null) ? (other.nombre != null) : !this.nombre.equals(other.nombre)) {
            return false;
        }
        if ((this.apellido == null) ? (other.apellido != null) : !this.apellido.equals(other.apellido)) {
            return false;
        }
        if ((this.correo == null) ? (other.correo != null) : !this.correo.equals(other.correo)) {
            return false;
        }
        if ((this.login == null) ? (other.login != null) : !this.login.equals(other.login)) {
            return false;
        }
        if ((this.password == null) ? (other.password != null) : !this.password.equals(other.password)) {
            return false;
        }
        if ((this.passwordConf == null) ? (other.passwordConf != null) : !this.passwordConf.equals(other.passwordConf)) {
            return false;
        }
        return true;
    }
}
