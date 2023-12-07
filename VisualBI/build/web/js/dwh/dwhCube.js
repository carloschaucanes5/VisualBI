/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function funDwhTableData()
{
   //State of dimensions---------------------
   var input1 = $("#input1").val();
   var input2 = $("#input2").val();
   var input3 = $("#input3").val();
   //Arrays Filters----------------------------------
   var vecCheckLevel1 = $( "input[name=filter1]:checked");
   var vecCheckLevel2 = $( "input[name=filter2]:checked");
   var vecCheckLevel3 = $( "input[name=filter3]:checked");
   //Use of Dimensions---------------------------
   var vecCheck = $("input[name=checkD]:checked");  
   //Values of filters------------------------------
    var values1 = getFilters(vecCheckLevel1);
    var values2 = getFilters(vecCheckLevel2);
    var values3 = getFilters(vecCheckLevel3);
   //-----------------------------------------------
   //-----------------------------------------------
   if(input1 == 1 && input2 == 1 && input3 == 1)
   {
       if(values1.trim().length == 0 &&  values2.trim().length == 0 && values3.trim().length == 0)
        {
            if(vecCheck.length == 0)
             {
                 funDwhQueryGeneral();
             }
             else
             {
                 if(vecCheck.length==3)
                  {
                      $( "#dialog-message").html("<center>Debes tener seleccionado   1 o 2 dimensiones <br/> para  ejecutar consultas</center>");
                      $( "#dialog-message").dialog( "open" ); 
                  }
                  else
                  {
                     funDwhQueryGeneralChecked(getDimensionsNoUse(vecCheck)); 
                  }
             }
        }
        else
        {
            if(vecCheck.length == 0)
             {
                 
                 funDwhQueryFilter(values1,values2,values3);
                 //$( "#dialog-message").html("<center>Filter1=>"+values1+"<br/>Filter2=>"+values2+"<br/>Filter3=>"+values3+"</center>");
                 //$( "#dialog-message").dialog( "open" ); 
             }
             else
             {
                 if(vecCheck.length==3)
                  {
                      $( "#dialog-message").html("<center>Debes tener seleccionado   1 o 2 dimensiones <br/> para ejecutar consultas</center>");
                      $( "#dialog-message").dialog( "open" ); 
                  }
                  else
                  {
                     funDwhQueryFilterChecked(values1,values2,values3,getDimensionsNoUse(vecCheck)); 
                  }
             }
        }
   }
   else
   {
        $( "#dialog-message").html("<center>Error al seleccionar medidas</center>");
        $( "#dialog-message").dialog( "open" );
   }  
}






//--------functios--------------------------------------------------------------
function getFilters(vecCheck)
{
    var values = "";
   if(vecCheck.length > 0)
   {
       values+=vecCheck[0].value;
       for(var j=1;j<vecCheck.length;j++)
       {
           values+=","+vecCheck[j].value;
       }
   }
   return values;
}
function getMeasures(vecMeasures)
{
   var namesMeasure = "";
   namesMeasure+=vecMeasures[0].value;
   for(var i=1;i<vecMeasures.length;i++)
    {
        namesMeasure+=","+vecMeasures[i].value;
    }
    return namesMeasure;
}
function getDimensionsNoUse(vecCheck)
{
    var dimensionsCheck ="";
    dimensionsCheck+=vecCheck[0].value;
    for(var j=1;j<vecCheck.length;j++)
     {
         dimensionsCheck+=","+vecCheck[j].value;
     }
    return dimensionsCheck;
}
//--------------------------------------------------------------------------------------------

function funDwhQueryGeneral()
{
   var vecMeasures = $( "input[name=measures]:checked");
   var cadCon = $('#idConnection').val();
   if(vecMeasures.length > 0)
   {
                  var parametrosArchivo = {
                 "level1":$('#idLevel1').val(),
                 "level2":$('#idLevel2').val(),
                 "level3":$('#idLevel3').val(),
                 "measures":getMeasures(vecMeasures),
                 "cadCon":cadCon
                 
                };				
                $.ajax({
                    url: "dwhQueryGeneral.jsp",
                    type:"POST",
                    data:parametrosArchivo,
                    beforeSend:function(){
                    $('#divCharging').html("Espere...<br/><img src='assets/img/loading.gif'>");
                    },
                    success:function(response){  
                    $('#divCharging').html("");
                    $( "#dialog-message4").html(response);
                    $( "#dialog-message4").dialog( "open" );              
                    }
                });
                return false;
   }
   else
   {
        $( "#dialog-message").html("<center>Debes seleccionar almenos una medida</center>");
        $( "#dialog-message").dialog( "open" ); 
   }
}

function funDwhQueryGeneralChecked(dimensionsNoUse) 
{
   var vecMeasures = $( "input[name=measures]:checked");
   var cadCon = $('#idConnection').val();
   if(vecMeasures.length > 0)
   {
                  var parametrosArchivo = {
                 "level1":$('#idLevel1').val(),
                 "level2":$('#idLevel2').val(),
                 "level3":$('#idLevel3').val(),
                 "measures":getMeasures(vecMeasures),
                 "noUse":dimensionsNoUse,
                 "cadCon":cadCon
                 
                };				
                $.ajax({
                    url: "dwhQueryGeneralChecked.jsp",
                    type:"POST",
                    data:parametrosArchivo,
                    beforeSend:function(){
                    $('#divCharging').html("Wait...<br/><img src='assets/img/loading.gif'>");
                    },
                    success:function(response){  
                    $('#divCharging').html("");
                    $( "#dialog-message4").html(response);
                    $( "#dialog-message4").dialog( "open" );              
                    }
                });
                return false;
   }
   else
   {
        $( "#dialog-message").html("<center>Debes seleccionar almenos una medida</center>");
        $( "#dialog-message").dialog( "open" ); 
   }
}
//------------------------------------------------------------------------------------------
function funDwhQueryFilter(vals1,vals2,vals3)
{  
   var vecMeasures = $( "input[name=measures]:checked");
   var cadCon = $('#idConnection').val();
   if(vecMeasures.length > 0)
   {
       
                  var parametrosArchivo = {
                 "level1":$('#idLevel1').val(),
                 "level2":$('#idLevel2').val(),
                 "level3":$('#idLevel3').val(),
                 "measures":getMeasures(vecMeasures),
                 "filters1":vals1,
                 "filters2":vals2,
                 "filters3":vals3,
                 "cadCon":cadCon
                 
                };				
                $.ajax({
                    url: "dwhQueryFilter.jsp",
                    type:"POST",
                    data:parametrosArchivo,
                    beforeSend:function(){
                    $('#divCharging').html("Espere...<br/><img src='assets/img/loading.gif'>");
                    },
                    success:function(response){  
                    $('#divCharging').html("");
                    $( "#dialog-message4").html(response);
                    $( "#dialog-message4").dialog( "open" );              
                    }
                });
                return false;
   }
   else
   {
        $( "#dialog-message").html("<center>Debes seleccionar almenos una medida</center>");
        $( "#dialog-message").dialog( "open" ); 
   }
}
 
function funDwhQueryFilterChecked(values1,values2,values3,dimensionsNoUse)
{    
   var vecCheck = $( "input[name=checkD]:checked");
   var vecMeasures = $( "input[name=measures]:checked");
   var cadCon = $('#idConnection').val();
   if(vecMeasures.length > 0)
   {
                 var parametrosArchivo = {
                 "level1":$('#idLevel1').val(),
                 "level2":$('#idLevel2').val(),
                 "level3":$('#idLevel3').val(),
                 "measures":getMeasures(vecMeasures),
                 "filters1":values1,
                 "filters2":values2,
                 "filters3":values3,
                 "noUse":dimensionsNoUse,
                 "cadCon":cadCon
                 
                };				
                $.ajax({
                    url: "dwhQueryFilterChecked.jsp",
                    type:"POST",
                    data:parametrosArchivo,
                    beforeSend:function(){
                    $('#divCharging').html("Espere...<br/><img src='assets/img/loading.gif'>");
                    },
                    success:function(response){  
                    $('#divCharging').html("");
                    $( "#dialog-message4").html(response);
                    $( "#dialog-message4").dialog( "open" );              
                    }
                });
                return false;
   }
   else
   {
        $( "#dialog-message").html("<center>Debes seleccionar almenos una medida</center>");
        $( "#dialog-message").dialog( "open" ); 
   }
}
//------------------------------------------------------------------------------------------

function funDwhFilter(index)
{ 
   var vecMeasures = $("input[name=measures]:checked");
   var cadCon = $('#idConnection').val();
   if(vecMeasures.length > 0)
   {
      
      if($('#stateFilter'+index).val() == 0)
       {   
         var parametrosArchivo = {
                 "level":$('#idLevel'+index).val(),
                 "measures":getMeasures(vecMeasures),
                 "indexLevel":index,
                 "cadCon":cadCon
                 
                };				
                $.ajax({
                    url: "dwhCubeFilter.jsp",
                    type:"POST",
                    data:parametrosArchivo,
                    beforeSend:function(){
                    $('#divCharging').html("Espere...<br/><img src='assets/img/loading.gif'>");
                    },
                    success:function(response){  
                    $('#divCharging').html("");
                    $( "#dialog-messageD"+index).html(response);
                    $('#stateFilter'+index).val(1);
                    $( "#dialog-messageD"+index).dialog( "open" );               
                    }
                });
                return false;   
       }
       else
       {
           $( "#dialog-messageD"+index).dialog( "open" );  
       }
   }
   else
   {
        $( "#dialog-message").html("<center>Debes seleccionar almenos una medida</center>");
        $( "#dialog-message").dialog( "open" ); 
   }
}