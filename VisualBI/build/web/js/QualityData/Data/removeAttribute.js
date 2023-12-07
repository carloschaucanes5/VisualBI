/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function funDataSelectAll(n)
{
   for(var j = 1;j<=n;j++)
     {
         var check=document.getElementById("idR"+j+"");
         check.checked=true;
     }
}

function funDataSelectNone(n)
{
     for(var j = 1;j<=n;j++)
     {
         var check=document.getElementById("idR"+j+"");
         check.checked=false;
     }  
}

//var vecCheckLevel1 = $( "input[name=filter1]:checked");
function funDataDownload()
{
    //window.open("qdDataDownloadFile.jsp?nomFile="+$('#fileName').val()," " ,"width= 300 ,height= 400 ");
  location.href = "qdDataDownloadFile.jsp?name="+$('#fileName').val()+"&path="+$('#filePath').val()+""; 
   /*var parametrosArchivo = {
           "path":$('#filePath').val(),
           "name":$('#fileName').val()
            };				
                $.ajax({
                    url: "qdDataDownloadFile.jsp",
                    type:"POST",
                    data:parametrosArchivo,
                    beforeSend:function(){
                    $('#divCharging').html("Espere...<br/><img src='assets/img/loading.gif'>");
                    },
                    success:function(response){  
                    $('#divCharging').html("");
              
                }
            });
            return false; */
}
function funDataRemove(n)
{
    var vecCheckR = $("input[name=removeA]:checked");
    var indexRemove = "";
    indexRemove=vecCheckR[0].value;
    for(var j = 1;j < vecCheckR.length;j++)
     {
         indexRemove+=","+vecCheckR[j].value;
     }
     if(vecCheckR.length < n)
      {
          var parametrosArchivo = {
           "path":$('#filePath').val(),
           "name":$('#fileName').val(),
           "itemsRemove":indexRemove
            };				
                $.ajax({
                    url: "qdDataProcessRemove.jsp",
                    type:"POST",
                    data:parametrosArchivo,
                    beforeSend:function(){
                    $('#divCharging').html("Espere...<br/><img src='assets/img/loading.gif'>");
                    },
                    success:function(response){  
                    $('#divCharging').html("");
                    $( "#dialog-message").html("<center><h4 style:border:1px>"+response+"</h4></center>");
                    funQdData();
                    $( "#dialog-message").dialog( "open" ); 
                    funCloseTabs();
              
                }
            });
            return false;
      }
      else
      {
        $( "#dialog-message").html("<center><h4 style:border:1px>No es posible eliminar todos los atributos</h4></center>");
        $( "#dialog-message").dialog( "open" ); 
      }

}

function funCloseTabs()
{
    for(var j=2;j<=6;j++)
     {
         if(j!=3)
         {
            $('#tabs-'+j).html("");
            $('#tabs-'+j).hide();
            $('#tabIn'+j).hide();
            $('.tabIn'+j).hide();
         }
     }
}  