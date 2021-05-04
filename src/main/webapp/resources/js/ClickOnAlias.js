/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function clickAlias(Math1,alias,valorAlias)
{
    var render=document.getElementById(Math1);
//    render.innerHTML="<span id=\"Math1\">$$"+terminos[0]+"$$</span>";
   
    
    render.onclick = function (event) {
      if (!event) {event = window.event}
      var target = event.toElement || event.target;
      while (target && (!target.id || target.id.substring(0,4) != 'agru' )) {target = target.parentNode}
      if(target)
      {

        var tipo = target.id.split("@")[1];
        var index = parseInt(target.id.split("@")[2]);

        //var math = MathJax.Hub.getAllJax(Math1)[0]; // for MathJax version 2
        var node = document.getElementById(Math1) // for MathJax version 3
        var math = MathJax.startup.document.getMathItemsWithin(node)[0]; // for MathJax version 3
        //var originalText = math.originalText; // for MathJax version 2
        var originalText = math.math; // for MathJax version 3
        var newText;

        if(tipo == 'alias')
        {
            newText = originalText.replace("\\cssId{"+target.id+"}{\\style{cursor:pointer; color:#08c;}{"+ alias[index] +"}}","\\cssId{"+target.id.replace("alias","valor")+"}{\\style{cursor:pointer;}{\\underline{~"+ valorAlias[index] +"}}}");
        }
        else
        {
            newText = originalText.replace("\\cssId{"+target.id+"}{\\style{cursor:pointer;}{\\underline{~"+ valorAlias[index] +"}}}","\\cssId{"+target.id.replace("valor","alias")+"}{\\style{cursor:pointer; color:#08c;}{"+ alias[index] +"}}");
        }

        MathJax.typesetClear([node]); // for MathJax version 3
        node.innerHTML = '$$'+newText+'$$'; // for MathJax version 3
        MathJax.typeset([node]); // for MathJax version 3
        //MathJax.Hub.Queue(["Text",math,newText]); // for MathJax version 2
      }
    }
    
}
