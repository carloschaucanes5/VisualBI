/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function funQdData()
{
   if($('#fileName').val()!=null &&  $('#filePath').val()!=null )
   { 
       
       var vecName = $('#fileName').val().split(".");
       var idx = vecName.length-1;
       if(vecName[idx] == "csv" || vecName[idx] == "arff")
        { 
          var parametrosArchivo = {
           "path":$('#filePath').val(),
           "name":$('#fileName').val()
            };				
                $.ajax({
                    //url: "qdDataParameters.jsp",
                    url: "qdDataParamaters.jsp",
                    type:"POST",
                    data:parametrosArchivo,
                    beforeSend:function(){
                    $('#divCharging').html("Espere...<br/><img src='assets/img/loading.gif'>");
                    },
                    success:function(response){  
                    $('#divCharging').html("");
                    $('#tabs-3').html(response);
                    $('#tabIn3').show();
                    $('.tabIn3').show();
                }
            });
            return false;
         }
        else
        {
            $( "#dialog-message").html("<center><h4 style:border:1px>Invalid file</h4></center>");
            $( "#dialog-message").dialog( "open" ); 
        }
  }
  else
  {
      $( "#dialog-message").html("<center><h4 style:border:1px>There is not file</h4></center>");
      $( "#dialog-message").dialog( "open" ); 
   }
}

function funQdScatterPlot()
{
    var parametrosArchivo = {
           "path":$('#filePath').val(),
           "name":$('#fileName').val()
            };				
                $.ajax({
                    //url: "qdDataParameters.jsp",
                    url: "qdDataParameters.jsp",
                    type:"POST",
                    data:parametrosArchivo,
                    beforeSend:function(){
                    $('#divCharging').html("Espere...<br/><img src='assets/img/loading.gif'>");
                    },
                    success:function(response){  
                    $('#divCharging').html("");
                    $('#divParametros').html(response);               
                }
            });
            return false;
}

function funQdProcessLimits1()
{
      $( "#dialog-limits").html("<center><h4 style:border:1px>Esta seguro(a) de procesar los limites ingresados</h4></center>");
      $( "#dialog-limits").dialog( "open" ); 
}

function funQdProcessLimits()
{
   //if(isNaN(bottom)==false && isNaN(top)==false && isNaN(numIns)==false)
   //alert($('#idBottom').val()+"===="+$('#idTop').val());
   var top = $('#idTop').val();
   var bottom = $('#idBottom').val();
   var attribute1 = $('#idAttribute1').val();
   var attribute2 = $('#idAttribute2').val();
   var attribute3 = $('#idAttribute3').val();
   if(attribute1!=attribute2)
    {
        if(isNaN(bottom)==false && isNaN(top)==false)
         {
             if(bottom < top)
              {
                var parametrosArchivo = {
                "path":$('#filePath').val(),
                "name":$('#fileName').val(),
                "top":top,
                "bottom":bottom,
                "attribute1":attribute1,
                "attribute2":attribute2,
                "attribute3":attribute3
                };
                $.ajax({
                    url: "qdDataProcessInstances.jsp",
                    type:"POST",
                    data:parametrosArchivo,
                    beforeSend:function(){
                    $('#divScatterPlot').html("<center>Espere...<br/><img src='assets/img/loading.gif'></center>");
                    },
                    success:function(response){  
                        $('#divScatterPlot').html(response);                
                      }
                    });
                    return false;
              }
              else
              {
                $("#dialog-message").html("<center><h4 style:border:1px>El límite inferior debe ser menor al superior</h4></center>");
                $("#dialog-message").dialog( "open" ); 
              }
         }
         else
         {
            $("#dialog-message").html("<center><h4 style:border:1px>Límites invalidos</h4></center>");
            $("#dialog-message").dialog( "open" ); 
         }
    }
    else
    {
        $( "#dialog-message").html("<center><h4 style:border:1px>Atributos repetidos</h4></center>");
        $( "#dialog-message").dialog( "open" ); 
    }
   //alert(top+bottom+attribute1+attribute2+attribute3);
}

function funQdViewGraphic()
{
    window.open("qdDataGraphicScatterPlot.jsp", "", "width=1380, height=650");
}

//------------------------------------------------------------------------------
function funQdViewInstance(index)
{
  
        var parametrosArchivo = {
           "path":$('#filePath').val(),
           "name":$('#fileName').val(),
           "indexInstance":index
            };				
                $.ajax({
                    //url: "qdDataParameters.jsp",
                    url: "qdDataViewInstance.jsp",
                    type:"POST",
                    data:parametrosArchivo,
                    beforeSend:function(){
                    $('#divLoad').html("Espere...<br/><img src='assets/img/loading.gif'>");
                    },
                    success:function(response){  
                    $('#divLoad').html("");
                    $( "#dialog-instance").html(response);
                    $( "#dialog-instance").dialog( "open" ); 
                }
            });
            return false;
            
}
//------------------------------------------------------------------------------