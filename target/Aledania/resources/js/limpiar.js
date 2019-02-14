function limpiar()
{
       var texArea=document.getElementById('termino_string');
       if(texArea.value != "")
       {
             if(confirm("Sure you want to delete the content of the text area?"))
                 texArea.value="";
       }
}
