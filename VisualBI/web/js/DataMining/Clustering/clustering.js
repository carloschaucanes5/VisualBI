/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



function funDmClusterParameters(){
    
  if($('#fileName').val()!=null &&  $('#filePath').val()!=null )
   {
       var vecName = $('#fileName').val().split(".");
       var idx = vecName.length-1;
       if(vecName[idx] == "csv" || vecName[idx] == "arff")
       {
            var parametrosArchivo = {
                };				
                $.ajax({
                    url: "dmClusterParameters.jsp",
                    type:"POST",
                    data:parametrosArchivo,
                    beforeSend:function(){
                    $('#divCharging').html("Espere...<br/><img src='assets/img/loading.gif'>");
                    },
                    success:function(response){  
                    $('#divCharging').html("");
                    $('#tabs-5').html(response);
                    $('#tabIn5').show();
                    $('.tabIn5').show();                  
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
      $( "#dialog-message").html("<center><h4 style:border:1px>NO hay archivo</h4></center>");
      $( "#dialog-message").dialog( "open" ); 
   }
}

function funDmProcessKmean(){
    var numClusters = $('#idNumClusters').val();
           var parametrosArchivo = {
               "numClusters":numClusters,
               "path":$('#filePath').val(),
               "name":$('#fileName').val()
            };				
                $.ajax({
                    url: "dmClusterProcessKmeans.jsp",
                    type:"POST",
                    data:parametrosArchivo,
                    beforeSend:function(){
                    $('#divCharging').html("Espere...<br/><img src='assets/img/loading.gif'>");
                    },
                    success:function(response){  
                    $('#divCharging').html("");
                    $('#divTableClusters').html(response);                
                }
            });
            return false;
}
function funDmProcessInstances()
{ 
    //alert($('#limitBottom').val()+"-----"+$('#limitTop').val());   
    var bottom = $('#limitBottom').val();
    var top = $('#limitTop').val();
    var numIns = $('#numInstances').val();
 if(isNaN(bottom)==false && isNaN(top)==false && isNaN(numIns)==false)
  {
       if(bottom < top)
        {
           var parametrosArchivo = {
               "path":$('#filePath').val(),
               "name":$('#fileName').val(),
               "limitBottom":bottom,
               "limitTop":top,
               "numInstances":numIns
            };				
                $.ajax({
                    url: "dmClusterViewClusters.jsp",
                    type:"POST",
                    data:parametrosArchivo,
                    beforeSend:function(){
                    $('#divCharging').html("Espere...<br/><img src='assets/img/loading.gif'>");
                    },
                    success:function(response){  
                    $('#divGraphicImages').html(response);
                    $('#divCharging').html("");
                    //window.open("dmClusterGraphicCircles.jsp", "", "width=1200, height=630");
                }
            });
            return false;
        }
        else
        {
            $( "#dialog-message").html("<center><h4>El limite superior debe ser mayor al inferior</h4></center>");
            $( "#dialog-message").dialog( "open" ); 
        }
 }
 else
 {
    $( "#dialog-message").html("<center><h4>Datos Invalidos:Solo valores numéricos</h4></center>");
    $( "#dialog-message").dialog( "open" ); 
 }
}

function funDmViewWarning()
{
    $( "#dialog-message").html("<center><h4>Debes procesar los parametros para generar el gráfico</h4></center>");
    $( "#dialog-message").dialog( "open" ); 
}

function funDmShowHIde()
{
    //
    $('#table-clusters').show();
}

function funDmViewGraphics(cod)
{
    if(cod == 1)
     {
         window.open("dmClusterGraphicBubble.jsp", "", "width=1200, height=630");
     }
    if(cod == 2)
     {
         window.open("dmClusterGraphicTreeMap.jsp", "", "width=1400, height=640");
     }
}


  function funInfo(a)
  {

      var cen = a.toString().substring(0,0);
      if(cen!="c")
       {
       
             var parametrosArchivo = {
               "indexInstance":a
            };				
                $.ajax({
                    url: "dmClusterProcessInstance.jsp",
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
        else
        {
           $( "#dialog-message").html("<center><h3>Ver información  "+a+" a la derecha del gráfico</h3></center>");
           $( "#dialog-message").dialog( "open" );  
        }
  }
  function funIndexes(name,label,size)
  {
      return name+label+size;
  }
  
  
  function funDmSelectClusters()
  {
      //divClusters
      var cluster = $("#cluster").val();
               var parametrosArchivo = {
               "indexInstance":cluster
            };				
                $.ajax({
                    url: "dmClusterProcessCentroide.jsp",
                    type:"POST",
                    data:parametrosArchivo,
                    beforeSend:function(){
                    $('#divCharge').html("Espere...<br/><img src='assets/img/loading.gif'>");
                    },
                    success:function(response){  
                    $('#divCharge').html("");
                    $( "#divClusters").html(response);
                }
            });
            return false;
      
  }