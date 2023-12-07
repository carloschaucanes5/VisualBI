/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//Modal para informacion de parametros de graficoCuagrado.jsp
$(document).ready(function(){		
        $(function() {
        $( "#dialog-message" ).dialog({
            autoOpen:false,
            autoclose:false,
            modal: true,
            height:150,
            width: 500,
            buttons: {
            Ok: function() {
          $( this ).dialog( "close" );
            }
      }
    });
  });
         $(function() {
        $( "#dialog-messagea" ).dialog({
            autoOpen:false,
            autoclose:false,
            modal: true,
            height:150,
            width: 500,
            buttons: {
            Ok: function() {
          $( this ).dialog( "close" );
            }
      }
    });
  });
  
  $(function() {
        $( "#dialog-message1").dialog({
            autoOpen:false,
            autoclose:false,
            modal: true,
            height:300,
            width: 670,
            buttons: {
            Ok: function() {
          $( this ).dialog( "close" );
            }
      }
    });
  });
  //

  
    $(function() {
        
        $( "#dialog-message2").dialog({
            autoOpen:false,
            autoclose:false,
            modal: true,
            height:600,
            width: 1000,
            buttons: {
            Ok: function() {
          $( this ).dialog( "close" );
            }
      }
    });
  });
  

  
      $(function() {
        
        $("#dialog-instance").dialog({
            autoOpen:false,
            autoclose:false,
            modal: true,
            height:350,
            width: 600,
            buttons: {
            Ok: function() {
          $( this ).dialog( "close" );
            }
      }
    });
  });
  
});


    $(function() {
        
        $( "#dialog-message4").dialog({
            autoOpen:false,
            autoclose:false,
            modal: true,
            height:500,
            width: 700,
            buttons: {
            Descargar: function() {
             funDwhDownload()
            },
            SQL: function() {
             funDwhViewQuery()
            },
            Salir: function() {
                $( this ).dialog( "close" );
            }

      }
    });
  });
  
  //Modal de verificacion de limites--------------------------------------------
      $(function() {
        
        $( "#dialog-limits").dialog({
            autoOpen:false,
            autoclose:false,
            modal: true,
            height:180,
            width: 600,
            buttons: {
            Procesar: function() {
             funQdProcessLimits();
             $( this ).dialog( "close" );
            },
            Cancel: function() {
                $( this ).dialog( "close" );
            }

      }
    });
  });
  //----------------------------------------------------
  
      $(function() {
        
        $( "#dialog-sql").dialog({
            autoOpen:false,
            autoclose:false,
            modal: true,
            height:250,
            width: 700,
            buttons: {
            Ok: function() {
                $( this ).dialog( "close" );
            }

      }
    });
  });


 

//------------------Modals by filters----------------------------
    $(function() {
        
        $( "#dialog-messageD1").dialog({
            autoOpen:false,
            autoclose:false,
            modal: true,
            height:400,
            width: 600,
            buttons: {
            Done: function() {
          $( this ).dialog( "close" );
            }
      }
    });
  });
  
      $(function() {
        
        $( "#dialog-messageD2").dialog({
            autoOpen:false,
            autoclose:false,
            modal: true,
            height:400,
            width: 600,
            buttons: {
            Done: function() {
          $( this ).dialog( "close" );
            }
      }
    });
  });
  
      $(function() {
        
        $( "#dialog-messageD3").dialog({
            autoOpen:false,
            autoclose:false,
            modal: true,
            height:400,
            width: 600,
            buttons: {
            Done: function() {
          $( this ).dialog( "close" );
            }
      }
    });
  });
 //----------------------------------------------------------- 
  