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
                    $('#divCharging').html("<img src='assets/img/loading.gif'>");
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

function funDwhConnect()
{
     var extension =  $('#fileName').val().split(".");
     if(extension[1] == "xml")
      {
       
        var parametrosArchivo = {
            "name" : $('#fileName').val(),
            "path" : $('#filePath').val(),
            "host" : $('#idHost').val(),
            "port" : $('#idPort').val(),
            "dataBase" : $('#idDataBase').val(),
            "user" : $('#idUser').val(),
            "password" : $('#idPassword').val()
            };				
                $.ajax({
                    url: "dwhEstablishConnection.jsp",
                    type:"POST",
                    data:parametrosArchivo,
                    beforeSend:function(){
                    $('#resultConnection').html("<img src='assets/img/loading.gif'>");
                    },
                    success:function(response){  
                    $('#resultConnection').html("");
                    $( "#dialog-message").html("<center>"+response+"</center>");
                    $( "#dialog-message").dialog( "open" ); 
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
                    $('#divCharging').html("<img src='assets/img/loading.gif'>");
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
        $( "#dialog-message").html("<center><h4 style:border:1px>there is not file</h4></center>");
        $( "#dialog-message").dialog( "open" );  
   }
}

function funDwhLevel(a)
{
    $( "#dialog-messageD"+a).html("");
    if(funDwhValidDimension(a) == true)
    {
        $('#divC'+a).html("");
           var index = $('#idDimension'+a).val();
              var parametrosArchivo = {
                    "indexDimension":index,
                    "indexOrdinal":a
                };				
                $.ajax({
                    url: "dwhCubeLevels.jsp",
                    type:"POST",
                    data:parametrosArchivo,
                    beforeSend:function(){
                    $('#divCharging').html("<img src='assets/img/loading.gif'>");
                    },
                    success:function(response){  
                    $('#divCharging').html("");
                    $('#div'+a).html(response);               
                    }
                });
                return false; 
    }
    else
    {
        $( "#dialog-message").html("<center><h4 style:border:1px>Already it is the dimension</h4></center>");
        $( "#dialog-message").dialog( "open" );
    }
}
function funDwhValidDimension(o)
{
    
    var html1 = "";
    if(o==1)
    {
        if($('#idDimension'+o).val() != $('#idDimension2').val() && $('#idDimension'+o).val() != $('#idDimension3').val())
         {
             return true;
         }
         else
         {
             html1 += "<select  onchange = 'funDwhLevel()' class='btn   blue'><option>Select</option>";
             $('#div'+o).html(html1);
             $('#divC'+o).html("");
             return false;
         }
    }
    else
    {
        if(o==2)
        {
            if($('#idDimension'+o).val() != $('#idDimension1').val() && $('#idDimension'+o).val() != $('#idDimension3').val())
            {
                return true;
            }
            else
            {
                html1 += "<select  onchange = 'funDwhLevel()' class='btn   green'><option>Select</option>";
                $('#div'+o).html(html1);
                $('#divC'+o).html("");
                return false;
            } 
        }
        else
        {
            if($('#idDimension'+o).val() != $('#idDimension1').val() && $('#idDimension'+o).val() != $('#idDimension2').val())
            {
                return true;
            }
            else
            {
                 html1 += "<select  onchange = 'funDwhLevel()' class='btn  red'><option>Select</option>";
                $('#div'+o).html(html1);
                $('#divC'+o).html("");
                return false;
            }  
        }
    }
}


function funDwhGoCube(z)
{
   $( "#input"+z).val(0);
   $( "#dialog-messageD"+z).html("");
    var htmlButton = "";
    var color = "";
    var vec = $('#idLevel'+z).val().split("=>");
    var dim = vec[0];
    var lev = vec[1];
    if(z == 1)
     {
        color = "blue";
     }
     else
     {
        if(z == 2)
        {
            color = "green"; 
        }
        else
        {
            color = "red"; 
        }
     }
     var color = '';
     if(z==1)
      {
          color="blue";
      }
     if(z==2)
      {
          color="green";
      }
     if(z==3)
      {
          color="red";
      }
    //--------------------------------------------------
    var dimension = $('#idDimension'+z).val();
     htmlButton+="<div class='btn-group'>"+
                 "<button class='btn "+color+" btn-sm dropdown-toggle' type='button' data-toggle='dropdown'>"+
                 ""+dim+"<i class='fa fa-angle-down'></i>"+
                 "</button>"+
	         "<ul class='dropdown-menu' role='menu'>"+
                 "<li><a style='color:"+color+";' href='#'onclick='funDwhDistinctData1("+z+")'>"+lev+"</a></li>"+
                 "<li><a href='#' onclick='funDwhFilter("+z+")'>Filter</a></li>"+
                 "<li><input type='checkbox' name='checkD' value = '"+dimension+"'/>No use</li>"+
		 "</ul>"+
                 "</div>";
            $('#divC'+z).html(htmlButton)
    //--------------------------------------------------
}

//----- parameters to distinct types of queries and generate the table----
function funDwhQueryGeneral()
{
   var namesMeasure = "";
   var vecMeasures = $( "input[name=measures]:checked");
   if(vecMeasures.length > 0)
   {
       namesMeasure+=vecMeasures[0].value;
       for(var i=1;i<vecMeasures.length;i++)
       {
           namesMeasure+=","+vecMeasures[i].value;
       }
                  var parametrosArchivo = {
                 "level1":$('#idLevel1').val(),
                 "level2":$('#idLevel2').val(),
                 "level3":$('#idLevel3').val(),
                 "measures":namesMeasure
                 
                };				
                $.ajax({
                    url: "dwhTableGeneral.jsp",
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
        $( "#dialog-message").html("<center>No measures chosen<br/>(debe seleccionar una medida)</center>");
        $( "#dialog-message").dialog( "open" ); 
   }
}

function funDwhQueryFilter(vals1,vals2,vals3)
{
    /*$( "#dialog-message").html("values1=>"+vals1+"<br/>values2=>"+vals2+"<br/>values3=>"+vals3);
    $( "#dialog-message").dialog( "open" );*/
    
   var namesMeasure = "";
   var vecMeasures = $( "input[name=measures]:checked");
   if(vecMeasures.length > 0)
   {
       namesMeasure+=vecMeasures[0].value;
       for(var i=1;i<vecMeasures.length;i++)
       {
           namesMeasure+=","+vecMeasures[i].value;
       }
                  var parametrosArchivo = {
                 "level1":$('#idLevel1').val(),
                 "level2":$('#idLevel2').val(),
                 "level3":$('#idLevel3').val(),
                 "measures":namesMeasure,
                 "filters1":vals1,
                 "filters2":vals2,
                 "filters3":vals3
                 
                };				
                $.ajax({
                    url: "dwhTableFilters.jsp",
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
        $( "#dialog-message").html("<center>No measures chosen<br/>(debe seleccionar una medida)</center>");
        $( "#dialog-message").dialog( "open" ); 
   }
}
//------------------------------------------------------------------------
function funDwhQueryChecked(vals1,vals2,vals3)
{
    var dimensionsCheck ="";
    var vecCheck = $( "input[name=checkD]:checked");
    dimensionsCheck+=vecCheck[0].value;
    for(var j=1;j<vecCheck.length;j++)
     {
         dimensionsCheck+=","+vecCheck[j].value;
     }
    //-------------veras---------------------
      //----------------------------------------------------------------------
  var namesMeasure = "";
   var vecMeasures = $( "input[name=measures]:checked");
   if(vecMeasures.length > 0)
   {
       namesMeasure+=vecMeasures[0].value;
       for(var i=1;i<vecMeasures.length;i++)
       {
           namesMeasure+=","+vecMeasures[i].value;
       }
                  var parametrosArchivo = {
                 "level1":$('#idLevel1').val(),
                 "level2":$('#idLevel2').val(),
                 "level3":$('#idLevel3').val(),
                 "measures":namesMeasure,
                 "filters1":vals1,
                 "filters2":vals2,
                 "filters3":vals3,
                 "dimensionsNU":dimensionsCheck
                 
                };				
                $.ajax({
                    url: "dwhTableCheckDiemensions.jsp",
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
        $( "#dialog-message").html("<center>No measures chosen<br/>(debe seleccionar una medida)</center>");
        $( "#dialog-message").dialog( "open" ); 
   }
  
  //----------------------------------------------------------------------     
    //-------------caballo--------------------
 
}
//-------------------------------------------------------------------------
function funDwhTableData()
{
   var vecCheckLevel1 = $( "input[name=filter1]:checked");
   var vecCheckLevel2 = $( "input[name=filter2]:checked");
   var vecCheckLevel3 = $( "input[name=filter3]:checked");
   var values1="",values2="",values3="";
   if(vecCheckLevel1.length > 0)
   {
       values1+=vecCheckLevel1[0].value;
       for(var j=1;j<vecCheckLevel1.length;j++)
       {
           values1+=","+vecCheckLevel1[j].value;
       }
   }
   if(vecCheckLevel2.length > 0)
   {
       values2+=vecCheckLevel2[0].value;
       for(var j=1;j<vecCheckLevel2.length;j++)
       {
           values2+=","+vecCheckLevel2[j].value;
       }
   }
   
   if(vecCheckLevel3.length > 0)
   {
       values3+=vecCheckLevel3[0].value;
       for(var j=1;j<vecCheckLevel3.length;j++)
       {
           values3+=","+vecCheckLevel3[j].value;
       }
   }
   
   if(vecCheckLevel1.length==0 && vecCheckLevel2.length==0 && vecCheckLevel3.length==0)
   {
       if($('#idDimension1').val()!=-1 && $('#idDimension2').val()!=-1 && $('#idDimension3').val()!=-1)
       {
           if($('#idLevel1').val()!=-1 && $('#idLevel2').val()!=-1 && $('#idLevel3').val()!=-1)
           {
              var vecCheck = $( "input[name=checkD]:checked");
              if(vecCheck.length==0)
               {
                   funDwhQueryGeneral(); 
               }
               else
               {
                  if(vecCheck.length==3)
                  {
                      $( "#dialog-message").html("<center>Debes tener seleccionado   1 o 2 dimensiones <br/> para poder ejecutar consultas</center>");
                      $( "#dialog-message").dialog( "open" ); 
                  }
                  else
                  {
                     funDwhQueryChecked(values1,values2,values3); 
                  }
               }
              
           }
           else
           {
                $( "#dialog-message").html("<center>Debes seleccionar 1 nivel por cada dimension </center>");
                $( "#dialog-message").dialog( "open" );
           }
       }
       else
       {
            $( "#dialog-message").html("<center>Es necesario seleccionar las 3 dimensiones</center>");
            $( "#dialog-message").dialog( "open" ); 
       }
        
   }
   else
   {
       
       if($('#idDimension1').val()!=-1 && $('#idDimension2').val()!=-1 && $('#idDimension3').val()!=-1)
       {
           if($('#idLevel1').val()!=-1 && $('#idLevel2').val()!=-1 && $('#idLevel3').val()!=-1)
           {
              var vecCheck = $( "input[name=checkD]:checked");
              if(vecCheck.length==0)
               {
                   funDwhQueryFilter(values1,values2,values3); 
               }
               else
               {
                 if(vecCheck.length==3)
                  {
                      $( "#dialog-message").html("<center>Debes tener seleccionado   1 o 2 dimensiones <br/> para poder ejecutar consultas</center>");
                      $( "#dialog-message").dialog( "open" ); 
                  }
                  else
                  {
                     funDwhQueryChecked(values1,values2,values3); 
                  }
               }
              
              
           }
           else
           {
                $( "#dialog-message").html("<center>Es necesario seleccionar las 3 dimensiones</center>");
                $( "#dialog-message").dialog( "open" ); 
           }
       }
       else
       {
            $( "#dialog-message").html("<center>Es necesario seleccionar las 3 dimensiones</center>");
            $( "#dialog-message").dialog( "open" ); 
       }
   }     
}

function funDwhFilter(index)
{   
   var namesMeasure = "";
   var vecMeasures = $( "input[name=measures]:checked");
   if(vecMeasures.length > 0)
   {
       namesMeasure+=vecMeasures[0].value;
       for(var i=1;i<vecMeasures.length;i++)
       {
           namesMeasure+=","+vecMeasures[i].value;
       }
       
     if($( "#input"+index).val() == 0)
     {      
       var parametrosArchivo = {
                 "level":$('#idLevel'+index).val(),
                 "measures":namesMeasure,
                 "indexLevel":index
                 
                };				
                $.ajax({
                    url: "dwhCubeFilter.jsp",
                    type:"POST",
                    data:parametrosArchivo,
                    beforeSend:function(){
                    $('#divCharging').html("<img src='assets/img/loading.gif'>");
                    },
                    success:function(response){  
                    $('#divCharging').html("");
                    $( "#dialog-messageD"+index).html(response);
                    $( "#dialog-messageD"+index).dialog( "open" );              
                    $( "#input"+index).val(1); 
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
        $( "#dialog-message").html("<center>No measures chosen</center>");
        $( "#dialog-message").dialog( "open" ); 
   }
}



function  funDwhDistinctData(indx)
{
   var namesMeasure = "";
   var vecMeasures = $( "input[name=measures]:checked");
   if(vecMeasures.length > 0)
   {
       namesMeasure+=vecMeasures[0].value;
       for(var i=1;i<vecMeasures.length;i++)
       {
           namesMeasure+=","+vecMeasures[i].value;
       }
       var parametrosArchivo = {
                                "level":$('#idLevel'+indx).val(),
                                "measures":namesMeasure        
                               };				
                $.ajax({
                    url: "dwhCubeDistinctValuesLevel.jsp",
                    type:"POST",
                    data:parametrosArchivo,
                    beforeSend:function(){
                    $('#divCharging').html("<img src='assets/img/loading.gif'>");
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
        $( "#dialog-message").html("<center>No measures chosen</center>");
        $( "#dialog-message").dialog( "open" ); 
   }
}

function funDwhBox(idx)
{
    var html3="";
    var vecFilters = $( "input[name=containerD1]:checked");
    for(var j=0;j<vecFilters.length;j++)
    {
        html3+=vecFilters[j].value+"-";
    }
        $( "#dialog-message").html(html3);
        $( "#dialog-message").dialog( "open" ); 
    
}

