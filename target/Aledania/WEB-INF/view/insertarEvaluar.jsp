<%-- 
    Document   : insertarEvaluar
    Created on : 24/05/2014, 08:32:15 PM
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
        <h1>Insert the Term to Compute</h1>
    <sf:form action="/Aledania/eval/${usuario.getLogin()}" method="POST" modelAttribute="insertarEvaluar">
      <div class="form-group row justify-content-center">
        <label for="algoritmo_nombre" class="col-lg-2 col-form-label">Max Reductions Number:</label>

       <div class="col-lg-3">
<sf:input path="nombre" id="algoritmo_nombre" value="500" class="form-control" /><sf:errors path="nombre" cssClass="error" /><br/>
       </div>
      </div>
      <div class="form-group row justify-content-center">
       <label for="termino_string" class="col-lg-1 col-form-label">Term:</label>
       <sf:textarea path="algoritmo" id="termino_string" rows="3" class="form-control col-lg-6"/><sf:errors path="algoritmo" cssClass="error" /><br/>
      </div>
      <div class="form-group row justify-content-center">
         <button type="submit" class="btn btn-default">Computing</button> &nbsp;
         <button type="button" onclick="limpiar()" class="btn btn-default">Clean</button>
      </div>
    </sf:form> ${mensaje}
            
        <script>
            t=document.getElementById('termino_string');
            t.innerText="${termino}";
        </script>
        <div style="height: 50vh;">
        <c:choose>
            <c:when test="${!usuario.getLogin().equals(admin)}">
                <article id="predefinidos" >
                    <h2 style="margin: 0px;padding:0px;height:40px;"><a ${hrefAMiMismo} onclick="desplegar('predefinidos')">Predefined Terms</a></h2>
                <iframe width="100%" height="100%" src="/Aledania/perfil/${usuario.getLogin()}/predef?comb=n">
                </iframe>
                </article>
            </c:when>
        </c:choose>
        <article id="misTerminos" >
            <h2 style="margin: 0px;padding:0px;height:40px;"><a ${hrefAMiMismo} onclick="desplegar('misTerminos')">My Terms</a></h2>
            <iframe width="100%" height="100%" src="/Aledania/perfil/${usuario.getLogin()}/listarocult?comb=n">
            </iframe>
        </article>
        
        <article id="publicos" >
            <h2 style="margin: 0px;padding:0px;height:40px;"><a ${hrefAMiMismo} onclick="desplegar('publicos')">Public Terms</a></h2>
            <iframe width="100%" height="100%" src="/Aledania/perfil/${usuario.getLogin()}/publiconoclick?comb=n">
            </iframe>
        </article>
        </div>
        <tiles:insertDefinition name="footer" />
    </body>
</html>
