/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

            function funFile()
            {
               if($("#idFile").val()=="")
                {
                   $( "#dialog-message").html("<center><h4 style:border:1px>NO se ha seleccionado un archivo</h4></center>");
                   $( "#dialog-message").dialog( "open" ); 
                   event.preventDefault();             
                }
                else
                {
                   $('#idCharging').html("<img src='assets/img/loading.gif'>");      
                }
            }
            function funInfoFile()
            {
                var table=$('#fileTable').val();
               $( "#dialog-message1").html("<center>"+table+"</center>");
               $( "#dialog-message1").dialog( "open" ); 
            }

function funMessage()
{
    //$( "#dialog-message").html("<center>hola  CABALLLOOOOOOOO</center>");
    $( "#dialog-message").html("<center>Archivo subido con éxito</center>");
    $( "#dialog-message").dialog( "open" ); 
    //alert("Archivo subido con éxito");
}

function comprueba_extension(formulario, archivo) { 
   extensiones_permitidas = new Array(".arff", ".jpg", ".doc", ".pdf"); 
   mierror = ""; 
   if (!archivo) { 
      //Si no tengo archivo, es que no se ha seleccionado un archivo en el formulario 
      	mierror = "No has seleccionado ningún archivo"; 
   }else{ 
      //recupero la extensión de este nombre de archivo 
      extension = (archivo.substring(archivo.lastIndexOf("."))).toLowerCase(); 
      //alert (extension); 
      //compruebo si la extensión está entre las permitidas 
      permitida = false; 
      for (var i = 0; i < extensiones_permitidas.length; i++) { 
         if (extensiones_permitidas[i] == extension) { 
         permitida = true; 
         break; 
         } 
      } 
      if (!permitida) { 
         mierror = "Comprueba la extensión de los archivos a subir. \nSólo se pueden subir archivos con extensiones: " + extensiones_permitidas.join(); 
      	}else{ 
         	//submito! 
         alert ("Todo correcto. Voy a submitir el formulario."); 
         formulario.submit(); 
         return 1; 
      	} 
   } 
   //si estoy aqui es que no se ha podido submitir 
   alert (mierror); 
   return 0; 
}
