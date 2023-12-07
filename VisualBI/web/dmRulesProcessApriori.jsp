<%-- 
    Document   : dmRulesProcessApriori
    Created on : Oct 14, 2014, 6:44:39 PM
    Author     : Carlitos
--%>

<%@page import="File.Upload.WriteFile"%>
<%@page import="weka.associations.Apriori"%>
<%@page import="DataMining.Rules.ProcessRules"%>
<%@page import="DataMining.Rules.ClassApriori"%>
<%@page import="File.Upload.InstancesFile"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>VisualBiTool(Apriori)</title>
    </head>
    <body>
        <% 
            HttpSession sesion=request.getSession();
            sesion.removeAttribute("seTituloR");
            sesion.removeAttribute("seSelectR");
            sesion.removeAttribute("seReglasR");
            String path =  (String)request.getParameter("path");
            String name =  (String)request.getParameter("name");
            String vec = (String)request.getParameter("vec");
            String[] vecIds = vec.split(",");
            InstancesFile inf = new InstancesFile(path+"/uploads/"+name);
            String format="";
            if(inf.processingData()==true)
            {
                if(sesion.getAttribute("alApriori")!= null)
                {
                    Apriori alApriori = (Apriori)sesion.getAttribute("alApriori");
                    ProcessRules pr = new ProcessRules(alApriori,inf.getData() ,vecIds);
                    if(pr.processFormat() == true)
                     {
                         format = pr.buildFormatVisJS();
                         WriteFile wf = new WriteFile(path+"/js/DataMining/rule/data.js", format);
                        if(wf.write()==true)
                        {
                            if(pr.processHtmlRules(vecIds)==true)
                            {
                               sesion.setAttribute("seTituloR",inf.getData().relationName());
                               sesion.setAttribute("seSelectR",pr.getHtmlSelect());
                               sesion.setAttribute("seReglasR",pr.getHtmlRules());
                               Thread.sleep(1000);
                            }
                            Thread.sleep(2000);
                            //------------------------------------------------------
                        }
                      }
                }
                else
                {
                    out.println("Error:Ejecuta nuevamente el proceso de <br/> análisis de datos(Reglas de asociación)");
                }
            }
            else
            {
                out.println(inf.getException());
            }
        %>
    </body>
</html>
