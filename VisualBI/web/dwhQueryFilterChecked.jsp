<%-- 
    Document   : dwhQueryFilterChecked
    Created on : 13/11/2014, 11:42:30 AM
    Author     : Carlitos
--%>

<%@page import="Dwh.Connection.ClassConnection"%>
<%@page import="Dwh.Table.QueryTable"%>
<%@page import="Dwh.Cube.FilterQueryChecked"%>
<%@page import="Dwh.Cube.StructureCube"%>
<%@page import="org.olap4j.OlapConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>VisualBiTool(Filtros SQL)</title>
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
        <title>JSP Page</title>
    </head>
    <body>
      <%
      //out.println("dwhQueryFilterChecked.jsp");
        try
        {      
            String sql = "";
            HttpSession sesion=request.getSession();
            String dim1 = (String)request.getParameter("level1");
            String dim2 = (String)request.getParameter("level2");
            String dim3 = (String)request.getParameter("level3");
            String filters1 = (String)request.getParameter("filters1");
            String filters2 = (String)request.getParameter("filters2");
            String filters3 = (String)request.getParameter("filters3");            
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
                      //out.println(dim1+dim2+dim3+"<br/>"+dimsNoUse);
                      FilterQueryChecked fq = new FilterQueryChecked(d1[0], d2[0], d3[0], d1[1], d2[1], d3[1], sc.getNameCube(), measures,dimsNoUse);
                      sql=fq.processFilter(filters1, filters2, filters3);
                      out.println("<textarea id='idSql' style='display:none;text-color:blue'>"+sql+"</textarea>");
                      //out.println(sql);
                      //GeneralQuery gq = new GeneralQuery(d1[0], d2[0], d3[0], d1[1], d2[1], d3[1], sc.getNameCube(), measures);
                      // sql= gq.getSqlFilters(filters1, filters2, filters3);
                      //out.println("Dimension1=>"+d1[0]+"<br/>");
                      //out.println("level1=>"+d1[1]+"<br/>");
                      //out.println("Filters1=>"+filters1+"<br/><br/>");
                      
                      //out.println("Dimension2=>"+d2[0]+"<br/>");
                      //out.println("level2=>"+d2[1]+"<br/>");
                      //out.println("Filters2=>"+filters2+"<br/><br/>");
                      
                      //out.println("Dimension3=>"+d3[0]+"<br/>");
                      //out.println("level3=>"+d3[1]+"<br/>");
                      //out.println("Filters3=>"+filters3+"<br/><br/>");                      
                      //out.println("Measures=>"+measures+"<br/><br/>");  
                      //out.println(sql);
                      /*cadLevels = d1[1]+","+d2[1]+","+d3[1];*/
                      QueryTable qt = new QueryTable(conOlap,sql,measures);
                      if(qt.processQuery() == true)
                        {
                            out.println( fq.getHtmlFilters()+"<br/>");
                            out.print(qt.getTable());
                            sesion.setAttribute("report",fq.getHtmlFilters()+qt.getTable());
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
           }catch(Exception erro)
           {
               out.println(erro.toString());
           }          
        %>
    </body>
</html>
