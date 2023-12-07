/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function funDwhDimension(a)
{
    $('#divC'+a).html("");
    $('#input'+a).val(0); 
    $('#divError').html("");
    $('#stateFilter1').val(0);
    $('#stateFilter2').val(0);
    $('#stateFilter3').val(0);
     var nameDimension = $('#idDimension'+a).val();
     if(nameDimension !="-1")
     {
         if(funDwhValidDimension(a) == true)
           {
              var parametrosArchivo = {
                    "nameDimension":nameDimension,
                    "indexOrdinal":a
                };				
                $.ajax({
                    url: "dwhCubeLevels.jsp",
                    type:"POST",
                    data:parametrosArchivo,
                    beforeSend:function(){
                    $('#divCharging').html("Espere...<br/><img src='assets/img/loading.gif'>");
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
               $('#divError').html("<center>Dimensiones repetidas</center>");
           }
     }
     else
     {
         $('#input'+a).val(0);
         $('#div'+a).html("");
     }
}

function funDwhGoCube(z)
{
    $('#divC'+z).html("");
    $('#stateFilter1').val(0);
    $('#stateFilter2').val(0);
    $('#stateFilter3').val(0);
  if(funDwhValidDimension(z) == true)
   {
        var htmlButton = "";
        var color = "";
        $('#input'+z).val(1);
        var vec = $('#idLevel'+z).val().split("=>");
        var dim = vec[0];
        var lev = vec[1];
        if(dim!="-1")
         {   
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
             htmlButton+="<input type='checkbox' name='checkD' value = '"+dimension+"' id='checkDimension"+z+"' onchange='dwhHideDimension("+z+")'/>No use</br>"+
                 "<div class='btn-group' id='divCheck"+z+"'>"+
                 "<button class='btn "+color+" btn-sm dropdown-toggle' type='button' data-toggle='dropdown'>"+
                 ""+dim+"<i class='fa fa-angle-down'></i>"+
                 "</button>"+
	         "<ul class='dropdown-menu' role='menu'>"+
                 "<li><a style='color:"+color+";' href='#'onclick='funDwhDistinctData1("+z+")'>"+lev+"</a></li>"+
                 "<li><a href='#' onclick='funDwhFilter("+z+")'>Filter</a></li>"+
		 "</ul>"+
                 "</div>";
            $('#divC'+z).html(htmlButton)
    //--------------------------------------------------
         }
         else
         {
             $('#input'+z).val(0);
             $('#divC'+z).html("");
         }
             
   }

}

function dwhHideDimension(x)
{
    var check=document.getElementById("checkDimension"+x);
    if(check.checked==true)
     {
         $('#divCheck'+x).hide();
     }
     else
     {
         $('#divCheck'+x).show();
     }
}

function changeMeasures()
{
    $('#stateFilter1').val(0);
    $('#stateFilter2').val(0);
    $('#stateFilter3').val(0);
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
                return false;
            }  
        }
    }
}

