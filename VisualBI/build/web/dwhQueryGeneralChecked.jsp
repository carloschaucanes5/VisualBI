<%-- 
    Document   : dwhQueryGeneralChecked
    Created on : 13/11/2014, 11:38:48 AM
    Author     : Carlitos
--%>

<%@page import="Dwh.Connection.ClassConnection"%>
<%@page import="org.olap4j.metadata.Dimension"%>
<%@page import="Dwh.Cube.GeneralQueryChecked"%>
<%@page import="Dwh.Table.QueryTable"%>
<%@page import="Dwh.Cube.GeneralQuery"%>
<%@page import="Dwh.Cube.StructureCube"%>
<%@page import="Dwh.Table.QueryTable"%>
<%@page import="org.olap4j.OlapConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>VisualBiTool(General SQL)</title>
        <script>
         function funDwhViewQuery()
            {
                var sql = document.getElementById("idSql").value;
                $( "#dialog-sql").html(sql);
                $( "#dialog-sql").dialog( "open" ); 
            }
         function funDwhDownload()
            {
               location.href='dwhDownloadReportQuery.jsp';
            }
        </script>
        
        <title>VisualBiTool(General SQL)</title>
    </head>
    <body>
        <%
            //out.println("dwhQueryGeneralChecked.jsp");
            String sql = "",cadLevels="";
            String[] vecLevels = null,vecMeasures = null;
            HttpSession sesion=request.getSession();
            String dim1 = (String)request.getParameter("level1");
            String dim2 = (String)request.getParameter("level2");
            String dim3 = (String)request.getParameter("level3");
            String measures = (String)request.getParameter("measures");
            String dimsNoUse = (String)request.getParameter("noUse");
            String[] d1 = dim1.split("=>");
            String[] d2 = dim2.split("=>");
            String[] d3 = dim3.split("=>");
            String cadCon = (String)request.getParameter("cadCon");
            String[] vecCon = cadCon.split("=>");
            ClassConnection cc = new ClassConnection(vecCon[0].trim(), vecCon[1].trim(),vecCon[2].trim() , vecCon[3].trim(), vecCon[4].trim(), vecCon[5].trim());
                if(cc.processConnection() == true)
                {
                  OlapConnection conOlap= cc.getOlapConnection();
                  StructureCube sc = new StructureCube(conOlap);
                  if(sc.processCube() == true)
                  {
                      GeneralQueryChecked gq = new GeneralQueryChecked(d1[0], d2[0], d3[0], d1[1], d2[1], d3[1], sc.getNameCube(), measures,dimsNoUse);
                      sql= gq.getSql();
                      out.println("<textarea id='idSql' style='display:none;text-color:blue'>"+sql+"</textarea>");
                      //out.println(sql);
                      //out.println("<br/>Dim1"+d1[0]+"<br/>Dim2=>"+d2[0]+"<br/>Dim3=>"+d3[0]+"<br/>Level1=>"+d1[1]+"<br/>level2=>"+ d2[1]+"<br/>level3=>"+d3[1]+"<br/>cube=>"+ sc.getNameCube()+"<br/>Medidas=>"+measures+"<br/>No usadas=>"+dimsNoUse);
                      QueryTable qt = new QueryTable(conOlap,sql,measures);
                      if(qt.processQuery() == true)
                        {
                            out.print(qt.getTable());
                            sesion.setAttribute("report",qt.getTable());
                            
                        }
                        else
                        {
                            out.println(qt.getExceptions());
                        }
                  }
                  else
                  {
                      out.println(sc.getExceptions());
                  }                  
              }
             else
              {
                out.println(cc.getExceptions());
              }
        %>

        </table>
    </body>
</html>
