<%-- 
    Document   : index
    Created on : 30/07/2014, 01:03:51 PM
    Author     : Carlitos
--%>

<%@page import="File.Upload.WriteFile"%>
<%@page import="QualityData.Attribute.AttributeInformation"%>
<%@page import="java.util.ListIterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="weka.core.Attribute"%>
<%@page import="QualityData.Attribute.StatisticalValues"%>
<%@page import="java.util.Enumeration"%>
<%@page import="File.Upload.InstancesFile"%>
<%@page import="File.Upload.UploadFile"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
<title>Inteligencia de negocios</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
	<meta name="MobileOptimized" content="320">
	<!-- BEGIN GLOBAL MANDATORY STYLES -->          
	<link href="assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
	<link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<link href="assets/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
	<!-- END GLOBAL MANDATORY STYLES -->
	<!-- BEGIN PAGE LEVEL STYLES -->
	<link rel="stylesheet" type="text/css" href="assets/plugins/select2/select2_metro.css" />
	<link rel="stylesheet" href="assets/plugins/data-tables/DT_bootstrap.css" />
	<!-- END PAGE LEVEL STYLES -->
	<!-- BEGIN THEME STYLES --> 
	<link href="assets/css/style-metronic.css" rel="stylesheet" type="text/css"/>
	<link href="assets/css/style.css" rel="stylesheet" type="text/css"/>
	<link href="assets/css/style-responsive.css" rel="stylesheet" type="text/css"/>
	<link href="assets/css/plugins.css" rel="stylesheet" type="text/css"/>
	<link href="assets/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
	<link href="assets/css/custom.css" rel="stylesheet" type="text/css"/>
        <link href="css/jquery/jquery-ui.css" rel="stylesheet" type="text/css"/>
        <!--estilos personalizados -->
        <link href="css/personalizados/titulos.css" rel="stylesheet" type="text/css"/>
	<!-- END THEME STYLES -->
        <script type="text/javascript" src="js/jquery/jquery-1.10.2.min.js"></script>
        <script type="text/javascript" src="assets/plugins/data-tables/jquery.dataTables.js"></script>
        <script type="text/javascript" src="js/QualityData/Attribute/JsAttribute.js"></script>
        <script type="text/javascript" src="js/QualityData/Data/JsData.js"></script>
        <script type="text/javascript" src="js/DataMining/Clustering/clustering.js"></script>
        <script type="text/javascript" src="js/DataMining/tree/tree.js"></script>
        <script type="text/javascript" src="js/DataMining/rule/rule.js"></script>
        <!--script type="text/javascript" src="js/dwh/dwh.js"></script-->
        <script type="text/javascript" src="js/dwh/dwhConnection.js"></script>
        <script type="text/javascript" src="js/dwh/dwhSelect.js"></script>
        <script type="text/javascript" src="js/dwh/dwhCube.js"></script>
        <!------------------------------------------------------------------------>
        <link rel="stylesheet" href="css/jquery/jquery-ui.css">
        <link rel="stylesheet" href="css/jquery/style.css">
        <script src="js/jquery/jquery-1.8.2.js"></script>
        <script src="js/jquery/jquery-ui.js"></script>
        <script src="js/modales/modales.js"></script>
        <script src="js/Files/JsFile.js"></script>
        <script src="js/tabs.js"></script>
        <link rel="stylesheet" href="css/estiloTabs.css" >
        <script>
            $(function() {
                $( "#tabs" ).tabs();
            });
        </script>
        <script type="text/javascript">
            $(document).ready(function(){
            $( "#tabs" ).click(function(evento){
            $('#destino1').html("");
            });		
            });
	</script>
    </head>
    <body class="page-header-fixed">
            
	<!-- BEGIN HEADER --> 
	<div class="header navbar navbar-inverse navbar-fixed-top">
		<!-- BEGIN TOP NAVIGATION BAR -->
                    <center><img alt="Mi web" src="logo/logo.png" width="300" height="43"/></center>
                    <br/>
			<!-- END LOGO -->
			<!-- BEGIN RESPONSIVE MENU TOGGLER --> 
			<a href="javascript:;" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
			</a> 		
		<!-- END TOP NAVIGATION BAR -->
	</div>
	<!-- END HEADER -->
	<!-- BEGIN CONTAINER -->
        <div class="page-container" >
		<!-- /.modal -->
		<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
		<!-- BEGIN SIDEBAR1 -->
                <br/><br/><br/>
		<div class="page-sidebar navbar-collapse collapse">
			<!-- BEGIN SIDEBAR MENU1 -->         
			<ul class="page-sidebar-menu">
				<li>
					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
					<div class="sidebar-toggler hidden-xs"></div>
					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
				</li>
				<li>
				</li>
				<li class="start">
					<a class="ajaxify start">
					<i class="fa fa-home"></i> 
					<span class="title">Menu Principal</span>
					<span class="selected"></span>
					</a>
				</li>
				<li >
					<a href="javascript:;">
					<i class="fa fa-cogs"></i> 
					<span class="title">Calidad de Datos</span>
					<span class="selected"></span>
					<span class="arrow open"></span>
					</a>
					<ul class="sub-menu">
						<li>
                                                    <a onclick="funQdAttributes()">
                                                        Atributos
                                                    </a>
						</li>
						<li>
                                                    <a onclick="funQdData()">					
                                                        Datos                   
					            </a>
						</li>
					</ul>
				</li>
				<li >
					<a href="javascript:;">
					<i class="fa fa-cogs"></i> 
					<span class="title">Patrones de MIneria</span>
					<span class="selected"></span>
					<span class="arrow"></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a onclick="funDmTreeParameters()">
							Arboles de decisión              
							</a>
						</li>
						<li>
							<a onclick="funDmClusterParameters()">
							Agrupamiento                   
							</a>
						</li>
						<li>
							<a onclick="funDmRulesParameters()" >
							Reglas de Asociación                 
							</a>
						</li>
					</ul>
				</li>
				<li class="last">
					<a href="javascript:;">
					<i class="fa fa-cogs"></i> 
					<span class="title">Analisis de Datos</span>
					<span class="selected"></span>
					<span class="arrow"></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a onclick="funDwhConnection()">
							Conexión OLAP                  
							</a>
						</li>
						<li>
                                                        <a onclick="funDwhOlapAnalysis()">
							Analisís OlAP                
							</a>
						</li>
					</ul>
				</li>
                                				<li class="last">
					<a href="javascript:;">
					<i class="fa fa-cogs"></i> 
					<span class="title">Acerca de ...</span>
					<span class="selected"></span>
					<span class="arrow"></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a>
							Proyecto                
							</a>
						</li>
						<li>
                                                        <a>
							Detalles                
							</a>
						</li>
                                                <li>
                                                        <a>
							Licencia               
							</a>
                                                </li>
					</ul>
				</li>
			</ul>
			<!-- END SIDEBAR MENU1 -->    
		</div>
                <div style="text-align: center;position: fixed;top:400px;left: 10px" id="divCharging"></div>  
		<!-- END SIDEBAR1 -->
		<!-- BEGIN PAGE -->
		<div class="page-content">
			<!-- BEGIN STYLE CUSTOMIZER -->
			<div class="theme-panel hidden-xs hidden-sm">
				<div class="toggler"></div>
				<div class="toggler-close"></div>
				<div class="theme-options">
					<div class="theme-option theme-colors clearfix">
						<span>COLOR DEL TEMA</span>
						<ul>
							<li class="color-black current color-default" data-style="default"></li>
							<li class="color-blue" data-style="blue"></li>
							<li class="color-brown" data-style="brown"></li>
							<li class="color-purple" data-style="purple"></li>
							<li class="color-grey" data-style="grey"></li>
							<li class="color-white color-light" data-style="light"></li>
						</ul>
					</div>
					<div class="theme-option">
						<span>Layout</span>
						<select class="layout-option form-control input-small">
							<option value="fluid" selected="selected">Fluid</option>
							<option value="boxed">Boxed</option>
						</select>
					</div>
					<div class="theme-option">
						<span>Header</span>
						<select class="header-option form-control input-small">
							<option value="fixed" selected="selected">Fixed</option>
							<option value="default">Default</option>
						</select>
					</div>
					<div class="theme-option">
						<span>Sidebar</span>
						<select class="sidebar-option form-control input-small">
							<option value="fixed">Fixed</option>
							<option value="default" selected="selected">Default</option>
						</select>
					</div>
					<div class="theme-option">
						<span>Footer</span>
						<select class="footer-option form-control input-small">
							<option value="fixed">Fixed</option>
							<option value="default" selected="selected">Default</option>
						</select>
					</div>
				</div>
			</div>
			<!-- END BEGIN STYLE CUSTOMIZER --> 
                        <div class="row">
                            <h3 class="page-title">
                                 INTELIGENCIA DE NEGOCIOS  <small>Minería de datos && Análisis Olap</small>
                            </h3>
                        <!------------------------------------------------------->
                            <div id="tabs">
                               <ul>
                                  <li style="background-color:#c6d5e1" id="tabIn1"><a href="#tabs-1" >Abrir Archivo</a></li>
                                  <li style="display: none" id="tabIn2"><a href="#tabs-2" >Atributos</a></li>
                                  <li style="display: none" id="tabIn3"  ><a href="#tabs-3" >Datos</a></li>
                                  <li style="display: none" id="tabIn4"  ><a href="#tabs-4" >Arboles de desición</a></li>
                                  <li style="display: none" id="tabIn5"  ><a href="#tabs-5" >Agrupamiento</a></li>
                                  <li style="display: none" id="tabIn6"  ><a href="#tabs-6" >Reglas de Asociación</a></li>
                                  <li style="display: none" id="tabIn7"  ><a href="#tabs-7" >Conexión OLAP</a></li>
                                  <li style="display: none" id="tabIn8"  ><a href="#tabs-8" >Analisís OLAP </a></li>
                               </ul>
                               <div id="tabs-1">
                                   <br/>
                                   <center><h2>Nuevo Archivo</h2></center>
                                   <center>
                                     <div>
                                        <form action="index.jsp" enctype="multipart/form-data" method="post"><%-- enctype: permite carga de datos--%>
                                            <input type="file" id="idFile" name="file" class="btn default" accept=".arff,.csv,.xml" >
                                        <br/>
                                        <button type="submit" onclick="funFile()" class="btn blue start">
                                            <i class="fa fa-upload"></i>
                                            <span>Subir</span>
                                        </button>
                                        </form>
                                    </div>
                                   </center>
                               </div>
                               <div  id="tabs-2"></div>
                               <div id="tabs-3"></div>
                               <div id="tabs-4"></div>
                               <div id="tabs-5"></div>
                               <div id="tabs-6"></div>
                               <div id="tabs-7"></div>
                               <div id="tabs-8"></div>
                             </div>
                                        <!------------------------------------------------------->
			</div>
			<!-- END PAGE HEADER--> 
                          <div id="contenidoPrincipal">

				<!-- HERE WILL BE LOADED AN AJAX CONTENT -->
                                               
   <%
    HttpSession sesion=request.getSession();
   String html1="",html2="",ids="idInfo";
   //delete sesions-----------------------------------------------------
   sesion.removeAttribute("seInformation");
   sesion.removeAttribute("seConnectionOlap");
   List<AttributeInformation> lI= new ArrayList<AttributeInformation>();
   //-------------------------------------------------------------------
try
 {
    UploadFile cA = new UploadFile(request);
    if(cA.setFile(application)==true && cA.sizeFile!=0)
      {
        cA.deleteFiles(cA.getPath()+"/uploads/");      
        html2+="<br><br><div id='idInfo'><table  border='1' class='table table-striped table-hover table-bordered' id='sample_editable_1'>";
        html2+="<td>Información File</td>";
        html2+="<tr><td>Tamaño:</td><td>"+cA.sizeFile+"bytes</td></tr>";
        html2+="<tr><td>Tipo:</td><td>"+cA.typeFile+"</td></tr>";
        html2+="<tr><td>Directorio:</td><td>"+cA.getPath()+"/uploads/"+cA.nameFile+"</td></tr>";
        html2+="</table>";
        html2+="</div>";
        out.println("<textarea  id='fileTable' style='display:none'>"+html2+"</textarea>");
        html1+="<input type='text' id='fileName' value='"+cA.nameFile+"' style='display:none'/>";
        html1+="<input type='text' id='filePath' value='"+cA.getPath()+"' style='display:none'/>";     
        cA.writeFile();
        Thread.sleep(500);
        out.println(html1);
        out.println("<center><a href='#'  onclick='funInfoFile()' title=''>view information of file</a></center>");
        sesion.setAttribute("seInformation",lI);
        WriteFile wf1 = new WriteFile(cA.getPath()+"/js/QualityData/datosDistribucionNormal.js","");
        wf1.write();
        WriteFile wf2 = new WriteFile(cA.getPath()+"/js/Infovis/datos.js","");
        wf2.write();
        out.println("<script language='javascript'>window.onload = function() {funMessage();}</script>");       
    }         
 }       
 catch(Exception err)
 {
     out.println(err.toString());
 }%>  
                                </div>
			</div>
                        <div id="contenidoPrimario"></div>
                        <div id="secondContect" style="position: absolute;top: 340px"></div>
                        <div id="contenidoTerciario"></div>
                        <div id="contenidoCuarto"></div>
		</div>
		<!-- BEGIN PAGE -->     
	<!-- END CONTAINER -->
	<!-- BEGIN FOOTER -->
	<div class="footer">
		<div class="footer-inner" >
                    2014 &copy; Universidad de Nariño.&nbsp;&nbsp;Carlos German Chaucanes Montenegro                        
		</div>
		<div class="footer-tools">
			<span class="go-top">
			<i class="fa fa-angle-up"></i>
			</span>
		</div>                
	</div>
	<script src="assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
	<script src="assets/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
	<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
	<script src="assets/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>      
	<script src="assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="assets/plugins/bootstrap-hover-dropdown/twitter-bootstrap-hover-dropdown.min.js" type="text/javascript" ></script>
	<script src="assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script src="assets/plugins/jquery.blockui.min.js" type="text/javascript"></script>  
	<script src="assets/plugins/jquery.cookie.min.js" type="text/javascript"></script>
	<script src="assets/plugins/uniform/jquery.uniform.min.js" type="text/javascript" ></script>
	<!-- END CORE PLUGINS -->
	<script type="text/javascript" src="assets/plugins/select2/select2.min.js"></script>
	<script src="assets/scripts/app.js"></script> 
        	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<!-- BEGIN PLUGINS USED BY X-EDITABLE -->
	<script type="text/javascript" src="assets/plugins/select2/select2.min.js"></script>
	<script type="text/javascript" src="assets/plugins/bootstrap-wysihtml5/wysihtml5-0.3.0.js"></script> 
	<script type="text/javascript" src="assets/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.js"></script>
	<script type="text/javascript" src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript" src="assets/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="assets/plugins/moment.min.js"></script>
	<script type="text/javascript" src="assets/plugins/jquery.mockjax.js"></script>
	<script type="text/javascript" src="assets/plugins/bootstrap-editable/bootstrap-editable/js/bootstrap-editable.min.js"></script>
	<script type="text/javascript" src="assets/plugins/bootstrap-editable/inputs-ext/address/address.js"></script>
	<script type="text/javascript" src="assets/plugins/bootstrap-editable/inputs-ext/wysihtml5/wysihtml5.js"></script>   
	<!-- END X-EDITABLE PLUGIN -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<!--script src="assets/scripts/app.js"></script-->
	<script src="assets/scripts/form-editable.js"></script>
	<script>
	   jQuery(document).ready(function() {    
		   App.init();
		   $('.page-sidebar .ajaxify.start').click() // load the content for the dashboard page.	
		    // button state demo
		    $('.demo-loading-btn')
		      .click(function () {
		        var btn = $(this)
		        btn.button('loading')
		        setTimeout(function () {
		          btn.button('reset')
		        }, 3000)
		    });
		});
	</script>

	<!-- END JAVASCRIPTS -->
<script type="text/javascript">  var _gaq = _gaq || [];  _gaq.push(['_setAccount', 'UA-37564768-1']);  _gaq.push(['_setDomainName', 'keenthemes.com']);  _gaq.push(['_setAllowLinker', true]);  _gaq.push(['_trackPageview']);  (function() {    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;    ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);  })();
</script>
<div id="dialog-message" title="VisulBiTool(Mensaje)"></div>
<div id="dialog-message1" title="VisulBiTool(Mensaje)"></div>
<div id="dialog-message2" title="VisulBiTool(Mensaje)"></div>
<div id="dialog-message3" title="VisulBiTool(Mensaje)"></div>
<div id="dialog-message4" title="VisulBiTool(Cubo)"></div>
<div id="dialog-sql" title="VisulBiTool(SQL)"></div>
<div id="dialog-messageD1"  title="VisulBiTool(Filtros)"></div>
<div id="dialog-messageD2" title="VisulBiTool(Filtros)"></div>
<div id="dialog-messageD3"  title="VisulBiTool(Filtros)"></div>
<div id="dialog-messagea" title="VisulBiTool(Mensaje)"></div>
<div id="dialog-limits" title="VisulBiTool(Mensaje)"></div>
<!--div id="dialog-Table-cluster" title="Information visulaBITools"></div-->
<div><input type="text" id="idConnection"></div>

   </body>   
</html>
