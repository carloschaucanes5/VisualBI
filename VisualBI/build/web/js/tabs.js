/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

      $(function() {
        
        $( "#dialog-message3").dialog({
            autoOpen:false,
            autoclose:false,
            modal: true,
            height:150,
            width: 400,
            buttons: {
            Ok: function() {
                funClose();
                $( this ).dialog( "close" );
            },
            Cancel: function(){
             $( this ).dialog( "close" );   
            }
      }
    });
  });
var tab = null;
function closeTab(a)
{
    tab = a;
    $( "#dialog-message3").html("<center><h4 style:border:1px>Esta seguro de cerrar la pestaña</h4></center>");
    $( "#dialog-message3").dialog( "open" );  
}
function funClose()
{
   $('#tabs-'+tab).html("");
   $('#tabs-'+tab).hide();
   $('#tabIn'+tab).hide();
   $('.tabIn'+tab).hide(); 
}