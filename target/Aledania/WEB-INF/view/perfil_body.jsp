<%-- 
    Document   : perfil
    Created on : 20/03/2014, 08:34:24 AM
    Author     : federico
--%>

<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" >
        <title>Aledania</title>
        <base href="/Aledania/perfil/"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" >
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap-responsive.css" >
    </head>
    <body>
        <div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
      <div class="container">
        <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <div class="nav-collapse collapse">
          <ul class="nav">
	    <li class="active"><a href="${usuario.login}/guardar" style="padding-right:0px" >Guardar T&eacute;rmino</a></li>
            <li><a href="${usuario.login}/listar?comb=n" style="padding-right:0px">listar T&eacute;rminos</a></li>
            <li><a href="${usuario.login}/publico?comb=n" style="padding-right:0px">ver t&eacute;rminos publicos</a></li>
            <li><a href="${usuario.login}/mispublic?comb=n" style="padding-right:0px">Mis publicaciones</a></li>
            <li><a href="${usuario.login}/ingresar" style="padding-right:0px">computar</a></li>
            <li><a href="${usuario.login}/close">cerrar sesi&oacute;n</a></li>
          </ul>
        </div>
        </div>
        </div>
	</div>--%>
            <h1 class="span6">Este es el perfil de ${usuario.login}</h1>
            <div style="margin-top: 100px">${mensaje}</div>
           <table>
             <tr><td>Nombre:</td><td>${usuario.getNombre()}</td></tr>
             <tr><td>Apellido:</td><td>${usuario.getApellido()}</td></tr>
             <tr><td>Correo:</td><td>${usuario.getCorreo()}</td></tr>
            </table>
            <br>
            <div class="row" style="margin-left: 3%">
                <a href="editar">
                    <button type="button" class="btn row">Editar Perfil</button>
                </a>
            </div>

        <%--<div class="row-fluid" style="border:1px #ccc solid; padding:10px">
		<div class="span1" style="border-right: 1px #ccc solid">
		${usuario.login}
		</div>
	</div>
    </body>
</html>--%>
