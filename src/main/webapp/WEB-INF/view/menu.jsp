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
	    <li ${guardarMenu}><a href="guardar" style="padding-right:0px" >Guardar T&eacute;rmino</a></li>
            <li ${listarTerminosMenu}><a href="listar?comb=n" style="padding-right:0px">listar T&eacute;rminos</a></li>
            <li ${verTerminosPublicosMenu}><a href="publico?comb=n" style="padding-right:0px">ver t&eacute;rminos publicos</a></li>
            <li ${misPublicacionesMenu}><a href="mispublic?comb=n" style="padding-right:0px">Mis publicaciones</a></li>
            <li ${computarMenu}><a href="ingresar" style="padding-right:0px">computar</a></li>
            <li ${perfilMenu}><a href="./">Perfil</a></li>
            <li><a href="close">cerrar sesi&oacute;n</a></li>
          </ul>
        </div>
        </div>
        </div>
	</div>
     <div class="row-fluid" style="margin-left: 50px; margin-top: 69px; height:552px; width: ${anchuraDiv}; overflow-y:${overflow};">