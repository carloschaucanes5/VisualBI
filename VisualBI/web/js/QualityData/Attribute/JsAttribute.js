/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function funQdAttributes()
{
  if($('#fileName').val()!=null &&  $('#filePath').val()!=null )//
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
                    url: "qdAttributeProcessFile.jsp",
                    type:"POST",
                    data:parametrosArchivo,
                    beforeSend:function(){
                    $('#divCharging').html("Espere...<br/><img src='assets/img/loading.gif'>");
                    },
                    success:function(response){  
                    $('#divCharging').html("");
                    $('#tabs-2').html(response);
                    $('#tabIn2').show();
                    //------------------------------
                    var tabClick = document.getElementById('tabIn2');
                    tabClick.click();
                    //------------------------------
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
var state=0;
function funSelectAttribute()
{
    state=1;
     var parametrosArchivo = {
           "attribute":$('#idSelectAttribute').val(),
           "path":$('#filePath').val(),
           "name":$('#fileName').val()
            };				
                $.ajax({
                    url: "qdAttributeParameters.jsp",
                    type:"POST",
                    data:parametrosArchivo,
                    beforeSend:function(){
                    $('#idAttributeResult').html("");
                    $('#idAttributeResult').html("<center>Espere...<br/><img src='assets/img/loading.gif'></center>");                 
                    },
                    success:function(response){  
                    $('#idAttributeResult').html(response);
                }
            });
            return false;
}

function funViewGraphic(cod)
{
        if(cod==0)
         {
            window.open("qdAttributeGraphicTableChart.jsp", "", "width=1200, height=630");
         }
        if(cod==1)
         {
            window.open("qdAttributeGraphicLineChart.jsp", "", "width=1200, height=630");  
         }
        if(cod==2)
         {
            window.open("qdAttributeGraphicBarChart.jsp", "", "width=1200, height=630");  
         }
        if(cod==3)
         {
            window.open("qdAttributeGraphicPieChart.jsp", "", "width=1200, height=640");  
         }        
}

function funQdDownloadReport()
{
    location.href='qdAttributeDownloadReport.jsp';
}

