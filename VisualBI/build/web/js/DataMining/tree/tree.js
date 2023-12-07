/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function funDmTreeParameters()
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
                    url: "dmTreeParameters.jsp",
                    type:"POST",
                    data:parametrosArchivo,
                    beforeSend:function(){
                    $('#divCharging').html("Espere...<br/><img src='assets/img/loading.gif'>");
                    },
                    success:function(response){  
                    $('#divCharging').html("");
                    $('#tabs-4').html(response);
                    $('#tabIn4').show();
                    $('.tabIn4').show();                  
                }
            });
            return false;
        }
        else
        {
            $( "#dialog-message").html("<center><h4 style:border:1px>Archivo Invalido</h4></center>");
            $( "#dialog-message").dialog( "open" ); 
        }
   }
   else
   {
      $( "#dialog-message").html("<center><h4 style:border:1px>No hay archivo</h4></center>");
      $( "#dialog-message").dialog( "open" ); 
   }
}

function funDmProcessJ48()
{
   var parametrosArchivo = {
   "name" : $('#fileName').val(),
   "path" : $('#filePath').val(),
   "idConfidenceFactor":$('#idConfidenceFactor').val(),
   "idNumMinObj":$('#idNumMinObj').val(),
   "idNumFolds":$('#idNumFolds').val(),
   "idSeed":$('#idSeed').val()
    };				
    $.ajax({
            url: "dmTreeProcessJ48.jsp",
            type:"POST",
            data:parametrosArchivo,
            beforeSend:function(){
            $('#divCharging').html("Espere...<br/><img src='assets/img/loading.gif'>");
            },
            success:function(response){  
            $('#divCharging').html("");
            $('#divResultJ48').html(response);   
            }
          });
            return false; 
}

function funDMViewTree(a)
{
    if(a==1)
    {
        window.open("dmTreeGraphicSangria.jsp", "", "width=1200, height=630")
    }
    else
    {
        window.open("dmTreeGraphicHierarchy.jsp", "", "width=1200, height=630")   
    }
}