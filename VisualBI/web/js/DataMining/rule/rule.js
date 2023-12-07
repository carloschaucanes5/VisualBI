/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function funDmRulesParameters()
{
   if($('#fileName').val()!=null &&  $('#filePath').val()!=null )
   {
       var vecName = $('#fileName').val().split(".");
       var idx = vecName.length-1;
       if(vecName[idx] == "csv" || vecName[idx] == "arff")
        {
            var parametrosArchivo = {
            };				
                $.ajax({
                    url: "dmRulesParameters.jsp",
                    type:"POST",
                    data:parametrosArchivo,
                    beforeSend:function(){
                    $('#divCharging').html("Espere...<br/><img src='assets/img/loading.gif'>");
                    },
                    success:function(response){  
                    $('#divCharging').html("");
                    $('#tabs-6').html(response);
                    $('#tabIn6').show();
                    $('.tabIn6').show();                  
                }
            });
            return false;
        }
        else
        {
            $( "#dialog-message").html("<center><h4 style:border:1px>Archivo invalido</h4></center>");
            $( "#dialog-message").dialog( "open" ); 
        }
   }
   else
   {
      $( "#dialog-message").html("<center><h4 style:border:1px>No hay archivo</h4></center>");
      $( "#dialog-message").dialog( "open" ); 
   }
}

function funDmProcessApriori()
{
        var parametrosArchivo = {
            "name" : $('#fileName').val(),
            "path" : $('#filePath').val(),
            "numberRules":$('#idNumberRules').val(),
            "minMetric":$('#idMinMetric').val(),
            "lowerBoundMinSupport":$('#idLowerBoundMinSupport').val(),
            "upperBoundMinSupport":$('#idUpperBoundMinSupport').val()
            };				
                $.ajax({
                    url: "dmRulesTable.jsp",
                    type:"POST",
                    data:parametrosArchivo,
                    beforeSend:function(){
                    $('#divCharging').html("Espere...<br/><img src='assets/img/loading.gif'>");
                    },
                    success:function(response){  
                    $('#divCharging').html("");
                    $('#divResultApriori').html(response);   
                }
            });
            return false; 
}


function funDmRulerGraphhic()
{
    var rules = "";
    var vecRules = $( "input[name=rules]:checked");
    if(vecRules.length > 0)
    {
        rules=vecRules[0].value;
        for(var k = 1;k < vecRules.length;k++)
         {
             rules+=","+vecRules[k].value;
         }
        
        var parametrosArchivo = {
            "name" : $('#fileName').val(),
            "path" : $('#filePath').val(),
            "vec":rules
            };				
                $.ajax({
                    url: "dmRulesProcessApriori.jsp",
                    type:"POST",
                    data:parametrosArchivo,
                    beforeSend:function(){
                    $('#divCharging').html("Espere...<br/><img src='assets/img/loading.gif'>");
                    },
                    success:function(response){  
                    $('#divCharging').html("");
                    window.open("dmRulesGraphicNetwork.jsp", "", "width=1200, height=630")
                }
            });
            return false;  
    }
    else
    {
      $( "#dialog-message").html("<center><h4 style:border:1px>No se ha seleccionado ninguna regla</h4></center>");
      $( "#dialog-message").dialog( "open" ); 
    }
}