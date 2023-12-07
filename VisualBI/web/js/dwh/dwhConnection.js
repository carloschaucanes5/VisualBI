/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function funDwhConnection()
{
   if($('#fileName').val() != null)
   {
        var extension =  $('#fileName').val().split(".");
        if(extension[1] == "xml")
        {    
                var parametrosArchivo = {
                    "name":$('#fileName').val()
                };				
                $.ajax({
                    url: "dwhEstablishParameters.jsp",
                    type:"POST",
                    data:parametrosArchivo,
                    beforeSend:function(){
                    $('#divCharging').html("Espere...<br/><img src='assets/img/loading.gif'>");
                    },
                    success:function(response){  
                    $('#divCharging').html("");
                    $('#tabs-7').html(response);
                    $('#tabIn7').show();
                    $('.tabIn7').show();
                    
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

function funDwhConnect()
{
     var extension =  $('#fileName').val().split(".");
     if(extension[1] == "xml")
      {
       var cadCon = ""+
           ""+$('#filePath').val()+"/uploads/"+$('#fileName').val()+"=>"+
           ""+$('#idHost').val()+"=>"+
           ""+$('#idDataBase').val()+"=>"+
           ""+$('#idPort').val()+"=>"+
           ""+$('#idUser').val()+"=>"+
           ""+$('#idPassword').val();
        var parametrosArchivo = {
            "cadCon" : cadCon
            };				
                $.ajax({
                    url: "dwhEstablishConnection.jsp",
                    type:"POST",
                    data:parametrosArchivo,
                    beforeSend:function(){
                    $('#resultConnection').html("Espere...<br/><img src='assets/img/loading.gif'>");
                    },
                    success:function(response){  
                    $('#resultConnection').html("");
                    $( "#dialog-message").html("<center>"+response+"</center>");
                    $( "#dialog-message").dialog("open");
                    $( "#idConnection").val(cadCon);
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

function funDwhOlapAnalysis()
{
  if($('#fileName').val() != null)
   {
          var parametrosArchivo = {
                    "name":$('#fileName').val()
                };				
                $.ajax({
                    url: "dwhCubeDimensions.jsp",
                    type:"POST",
                    data:parametrosArchivo,
                    beforeSend:function(){
                    $('#divCharging').html("Espere...<br/><img src='assets/img/loading.gif'>");
                    },
                    success:function(response){  
                    $('#divCharging').html("");
                    $('#tabs-8').html(response);
                    $('#tabIn8').show();
                    $('.tabIn8').show();                 
                    }
                });
                return false;
                   }
   else
   {
        $( "#dialog-message").html("<center><h4 style:border:1px>NO hay archivo</h4></center>");
        $( "#dialog-message").dialog( "open" );  
   }
}

