/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aledania.forms;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author federico
 */
public class ModificarAliasForm {
    
     @NotEmpty(message="you must not leave this field empty")
     @Pattern(regexp="^[A-Z][a-z-]*_$",message="the alias must start in upper case and end with _")
     private String alias;

    public ModificarAliasForm() {
    }

    public ModificarAliasForm( String alias) {
       this.alias = alias;
    }
   
    public String getAlias() {
        return this.alias;
    }
    
    public void setAlias(String alias) {
        this.alias = alias;
    }

   @Override
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ModificarAliasForm) ) return false;
		 ModificarAliasForm castOther = ( ModificarAliasForm ) other; 
         
		 return  (this.getAlias()==castOther.getAlias()) || ( this.getAlias()!=null && castOther.getAlias()!=null && this.getAlias().equals(castOther.getAlias()) ) ;
   }
   
   @Override
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getAlias() == null ? 0 : this.getAlias().hashCode() );
         return result;
   }

    
}
