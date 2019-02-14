<%-- 
    Document   : listar
    Created on : 11/05/2014, 01:59:42 AM
    Author     : federico
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
    <tiles:insertDefinition name="header" />
    <body>
        <c:choose>
                <c:when test="${perfil.intValue()==1}">
                    <tiles:insertDefinition name="nav" />
                    <h1>${titulo}</h1>
                </c:when>
        </c:choose>
    
        <script>

            function insertAtCursor(myField, myValue) 
            {            
                myValue+="";
                //IE support
                if (parent.window.document.selection) {
                    parent.window.document.getElementById(myField).focus();
                    sel = parent.window.document.selection.createRange();
                    sel.text = myValue;
                }
                //MOZILLA and others
                else if (parent.window.document.getElementById(myField).selectionStart || parent.window.document.getElementById(myField).selectionStart == '0') {
                    var startPos = parent.window.document.getElementById(myField).selectionStart;
                    var endPos = parent.window.document.getElementById(myField).selectionEnd;
                    var newPos = startPos + myValue.length
                    parent.window.document.getElementById(myField).value = parent.window.document.getElementById(myField).value.substring(0, startPos)
                        + myValue
                        + parent.window.document.getElementById(myField).value.substring(endPos, parent.window.document.getElementById(myField).value.length);
                    parent.window.document.getElementById(myField).selectionStart = newPos;
                    parent.window.document.getElementById(myField).selectionEnd = newPos;
                } else {
                    parent.window.document.getElementById(myField).value += myValue;
                    parent.window.document.getElementById(myField).selectionStart = newPos;
                    parent.window.document.getElementById(myField).selectionEnd = newPos;
                }
                parent.window.document.getElementById(myField).focus();
            }
        </script>

        <center>
        <table class="table table-striped" border="1">
            <tr><th>Alias</th><th>Term</th></tr>
            <c:forEach var="termino" items="${terminos}">
                <tr><td><c:choose>
                            <c:when test="${click.equals(yes)}">
                                <a href="#!" onclick="insertAtCursor('termino_string', '${termino.getId().getAlias()}')">${termino.getId().getAlias()}</a>
                            </c:when>
                            <c:otherwise>
                                ${termino.getId().getAlias()}
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td><c:choose><c:when test="${comb.intValue()==1}">$$${termino.getCombinador()}$$</c:when><c:otherwise>${termino.termObject.toStringJavascript(termino.getId().getAlias())}</c:otherwise></c:choose></td>
                <c:choose>
                    <c:when test="${!usuario.getLogin().equals(publico)}">
                        <c:choose>
                            <c:when test="${publicaciones.intValue()==0}">
                                <td><a href="../${usuario.getLogin()}/modificar?alias=${termino.getId().getAlias()}" >Modify</a></td>
                                <td><a href="../${usuario.getLogin()}/modificaralias?aliasv=${termino.getId().getAlias()}" >Modify Alias</a></td>
                                <td><a onclick="return confirm('Sure you want to delete the term?')" href="../${usuario.getLogin()}/eliminar?alias=${termino.getId().getAlias()}">Delete</a></td>
                            </c:when>
                            <c:otherwise>
                                <td><a onclick="return confirm('Sure you want to delete the term?')" href="../${usuario.getLogin()}/eliminarpubl?alias=${termino.getId().getAlias()}">Delete</a></td>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${!usuario.getLogin().equals(admin) && publicaciones.intValue()==0}">
                                <td><a href="../${usuario.getLogin()}/publicar?alias=${termino.getId().getAlias()}">Publish</a></td>
                            </c:when>
                        </c:choose>
                    </c:when>
                </c:choose>
                </tr>
            </c:forEach>
        </table>
    <c:choose>
        <c:when test="${comb.intValue()==1}">
            <a href="${accion}?comb=n">Show in lambda term notation</a>
        </c:when>
        <c:otherwise>
            <a href="${accion}?comb=y">Show in Broda Damas notation</a>
        </c:otherwise>
    </c:choose>
            <%--<c:choose>
                <c:when test="${perfil.intValue()==1}">
                    <a href="./">Perfil</a>
                </c:when>
            </c:choose>--%>
            ${mensaje}
       </center>
       <c:choose>
        <c:when test="${perfil.intValue()==1}">
                <tiles:insertDefinition name="footer" />
        </c:when>
       </c:choose>
    </body>
</html>
