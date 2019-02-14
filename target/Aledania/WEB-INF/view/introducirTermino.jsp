<%-- 
    Document   : introducirTermino
    Created on : 06/05/2014, 02:16:02 PM
    Author     : federico
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
    <tiles:insertDefinition name="header" />
    <body>
    <tiles:insertDefinition name="nav" />
        <h1>Enter the term that you want to save</h1>
        <c:choose>
            <c:when test="${modificar.intValue()==0}">
                <sf:form method="POST" modelAttribute="usuarioGuardar">
                   <div class="form-group row justify-content-center">
                      <label for="termino_alias" class="col-lg-1 col-form-label">Alias:</label>
                    <div class="col-lg-3">
                       <sf:input path="alias" id="termino_alias" value="${alias}" class="form-control"/><sf:errors path="alias" cssClass="error" /><br/>
                    </div>
                   </div>
                   <div class="form-group row justify-content-center">
                    <label for="termino_string" class="col-lg-1 col-form-label">Term:</label>
                    <sf:textarea path="termino" id="termino_string" rows="3" class="form-control col-lg-6"/><sf:errors path="termino" cssClass="error" /><br/>
                   </div>
                   <div class="form-group row justify-content-center">
                     <button type="submit" class="btn btn-default">Save</button>&nbsp;
                     <button type="button" onclick="limpiar()" class="btn btn-default">Clean</button>
                   </div>
                </sf:form>${mensaje}
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${modificar.intValue()==1}">
                        <sf:form method="POST" modelAttribute="modificarForm">
                           <div class="form-group row justify-content-center">
                             <label class="col-lg-1 col-form-label" for="termino_string">Term:</label>
                             <sf:textarea path="termino" id="termino_string" rows="3" class="form-control col-lg-6"/><sf:errors path="termino" cssClass="error" />
                           </div>
                           <div class="row justify-content-center" >
                            <div class="offset-lg-1 col-lg-6">
                              <button type="submit" class="btn btn-default">Save</button>&nbsp;
                              <button type="button" onclick="limpiar()" class="btn btn-default">Clean</button>
                            </div>
                           </div>
                        </sf:form>${mensaje}
                    </c:when>
                    <c:otherwise>
                        <sf:form method="POST" modelAttribute="modificarAliasForm">
                            <div class="form-group row justify-content-center">
                             <label for="termino_alias" class="col-lg-1 col-form-label">Alias:</label>
                            <div class="col-lg-3">
                             <sf:input path="alias" id="termino_alias" value="${alias}" class="form-control"/><sf:errors path="alias" cssClass="error" />
                            </div>
                           </div>
                           <div class="row justify-content-center" >
                            <div class="offset-lg-1 col-lg-3">
                             <button type="submit" class="btn btn-default">Save</button>
                            </div>
                           </div>
                        </sf:form>${mensaje}
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
    
        <script>
            t=document.getElementById('termino_string');
            t.innerText="${termino}";
        </script>
        <div style="height: 50vh;">
        <c:choose>
            <c:when test="${!usuario.getLogin().equals(admin)}">
                <article id="predefinidos" >
                    <h2 style="margin: 0px;padding:0px;height:40px;"><a href="#!" onclick="desplegar('predefinidos')">Predefined Terms</a></h2>
                <iframe width="100%" height="100%" src="../${usuario.getLogin()}/predef?comb=n">
                </iframe>
                </article>
            </c:when>
        </c:choose>
        <article id="misTerminos" >
        <h2 style="margin: 0px;padding:0px;height:40px;"><a href="#!" onclick="desplegar('misTerminos')">My Terms</a></h2>
        <iframe width="100%" height="100%" src="../${usuario.getLogin()}/listarocult?comb=n">        
        </iframe>
        </article>
        <article id="publicos" >
            <h2 style="margin: 0px;padding:0px;height:40px;"><a href="#!" onclick="desplegar('publicos')">Public Terms</a></h2>
        <iframe width="100%" height="100%" src="../${usuario.getLogin()}/publiconoclick?comb=n">
        
        </iframe>
        </article>
        </div>
        <tiles:insertDefinition name="footer" />
    </body>
</html>
