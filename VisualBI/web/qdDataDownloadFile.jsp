<%-- 
    Document   : qdDataDownloadFile
    Created on : 12/01/2015, 03:02:54 PM
    Author     : Carlitos
--%>

<%@page import="java.io.FileInputStream"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            try
              {
                String name = request.getParameter("name");
                String path = request.getParameter("path");
                //out.println(path+"/uploads/"+name);
                FileInputStream archivo = new FileInputStream(path+"/uploads/"+name);
                int longitud = archivo.available();
                byte[] datos = new byte[longitud];
                archivo.read(datos);
                archivo.close();
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition","attachment;filename="+name);
                ServletOutputStream ouputStream = response.getOutputStream();
                ouputStream.write(datos);
                ouputStream.flush();
                ouputStream.close();
              }catch(Exception e){
                  e.printStackTrace(); 
              }
             
                   
            /*FileInputStream archivo = new FileInputStream(path+"/uploads/"+name);
            int longitud = archivo.available();
            byte[] datos = new byte[longitud];
            archivo.read(datos);
            archivo.close();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition","attachment;filename=\""+name+"\"");
            ServletOutputStream ouputStream = response.getOutputStream();
            ouputStream.write(datos);
            ouputStream.flush();
            ouputStream.close();*/
            
            //---
                   
             //response.setContentType ("application/vnd.ms-excel"); //Tipo de fichero.
             //response.setHeader ("Content-Disposition", "attachment;filename=\"result.xls\"");
             //HttpSession sesion=request.getSession();
             
             //out.println((String)sesion.getAttribute("report"));
             
      //---
         
            
        %>
    </body>
</html>
