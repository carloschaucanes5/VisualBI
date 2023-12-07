/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



var div1='div0';
function funMdMostrarRegla()
{
    funMdOcultar(div1);
   //alert($('#divCentroide').val());
   var div=$('#divRule').val();
   $('#'+div).show();
   $('.'+div).show();
   div1=div;
}
function funMdOcultar(divO)
{
   $('#'+divO).hide();
   $('.'+divO).hide(); 
}