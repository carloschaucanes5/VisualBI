/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dwh.Cube;

/**
 *
 * @author Carlitos
 */
import Dwh.Connection.ClassConnection;
import java.util.List;
import org.olap4j.OlapConnection;
import org.olap4j.OlapException;
import org.olap4j.metadata.Cube;
import org.olap4j.metadata.Dimension;
import org.olap4j.metadata.Hierarchy;
import org.olap4j.metadata.Level;
import org.olap4j.metadata.Measure;
import org.olap4j.metadata.NamedList;
import org.olap4j.metadata.Schema;

/**
 *
 * @author Alexander
 */
public class StructureCube{
    private OlapConnection olapCon;
    private Cube cube;
    private String exceptions;
    private Schema schema;
    
    public StructureCube(OlapConnection olapCon) {
        this.olapCon = olapCon;
        this.exceptions = "";
        this.cube = null;
        this.schema = null;
    }

    public boolean processCube()
    {
        try
        {
            this.schema=this.olapCon.getOlapSchema(); 
            NamedList<Cube> listCubes=this.schema.getCubes();
            this.cube = listCubes.get(0);
            return true;
        }
        catch(OlapException ex)
        {
            this.exceptions = ex.toString();
            return false;
        }
    }
    
    public String getNameSchema()
    {     
        return this.schema.getName();  
    }
    
    public String getNameCube()
    {
        return this.cube.getName();
    }
    
    public NamedList<Cube> getListCubes()
    {       
       try
       {
            Schema esquema=this.olapCon.getOlapSchema(); 
            NamedList<Cube> listCubes=esquema.getCubes();
            return listCubes;
       }
       catch(OlapException ex)
       {
           this.exceptions = ex.getMessage();
           return null;
       }      
    }
    
    public  NamedList<Dimension> getListDimensions()
    {
        //metodo encargado de retornar la lista de Dimensiones de el cubo deseado      
        NamedList<Dimension> dimensionesLista=this.cube.getDimensions();        
        return dimensionesLista;
    }
    
    public  List<Measure> getListMeasures()
    {
        List<Measure> medidasLista= this.cube.getMeasures();
        return medidasLista;
    }
    public String getCheckBoxHtmlMeasures()
    {
        String html = "<div>";
        List<Measure> lisMeasures= this.cube.getMeasures();
        for(int k=0;k<lisMeasures.size()-1;k++)
        {
            html+="<input type='checkbox'  name='measures' onchange='changeMeasures()'  value='"+lisMeasures.get(k).getName()+"'/>"+lisMeasures.get(k).getName()+"<br/>";
        }
        html+="</div>";
        return html;
    }
    
    public  NamedList<Hierarchy> getListHierarchies(Dimension di)
    {
        NamedList<Hierarchy> her= di.getHierarchies();
        return her;
    }
      
    public NamedList<Level> getListLevels(Hierarchy he)
    {
        NamedList<Level> ni= he.getLevels();
        return ni;
    }
    public String getExceptions() {
        return exceptions;
    }
    
    public String getSelectHtmlLevelFull(String nameDimension, int ordinalDimension)
    {
        String html = "";
        if(ordinalDimension == 1)
        {
          html += "<select  id='idLevel"+1+"' class='btn blue' onchange='funDwhGoCube(1)'>";  
        }
        else
        {
            if(ordinalDimension== 2)
            {
                html += "<select  id='idLevel"+2+"' class='btn green ' onchange='funDwhGoCube(2)'>"; 
            }
            else
            {
                html += "<select  id='idLevel"+3+"' class='btn red ' onchange='funDwhGoCube(3)'>"; 
            }      
        }
        html += "<option value='-1'>Seleccionar</option>";
        String val = "";
        Dimension dim = cube.getDimensions().get(nameDimension);
        NamedList<Hierarchy> her= dim.getHierarchies();
        for(int j=0;j<her.size();j++)
        {
            for(int k=0;k<her.get(j).getLevels().size();k++)
            {
                Level le = her.get(j).getLevels().get(k);
                val = dim.getName()+"=>"+le.getName();               
                html += "<option value='"+val+"'>"+le.getName()+"</option>";
            }
        }
        html += "</select>";
        return html;
    }
    
    public String setDivHtmlLevel(int ordinalDimension)
    {
        String html ="<div id='div"+ordinalDimension+"'>";
        html+="</div>";
       return html;
    }
    public String getSelectHtmlDimension(int ordinalDimension)
    {
        NamedList<Dimension> listDim = this.cube.getDimensions();
        String html = "";
        if(ordinalDimension == 1)
        {
          html = "<select id='idDimension"+ordinalDimension+"' onchange = 'funDwhDimension("+ordinalDimension+")' class='btn   blue'>";  
        }
        else
        {
            if(ordinalDimension == 2)
            {
                html = "<select id='idDimension"+ordinalDimension+"' onchange = 'funDwhDimension("+ordinalDimension+")' class='btn   green'>"; 
            }
            else
            {
                html = "<select id='idDimension"+ordinalDimension+"' onchange = 'funDwhDimension("+ordinalDimension+")' class='btn   red'>"; 
            }      
        }
        html+="<option value = '-1'>seleccionar</option>";
        for(int i=1;i<listDim.size();i++)
         {
            Dimension d=listDim.get(i);
            html+="<option value = '"+d.getName()+"'>"+ d.getName()+"</option>";
         }
        html += "</select>";
        return html;
    }  
    /*
    public static void main(String[] arg)
    {
        String sql1 = "";
        String path = "D:/cubo/schema_udenar_count.xml";
         //String path = "D:/cubo/xmlfiles/aprobacion.xml";
        String host = "127.0.0.1";
        String port = "5432";
        String dataBase = "repositorio_dwh";
        String user = "postgres";
        String password ="postgres1";
        //----------------------------------------------------------------------
        sql1 ="SELECT\n"+
             "NON EMPTY [Measures].[measure_cantidad] ON COLUMNS,\n"+
             "NON EMPTY {DISTINCT({[dim_usage_colegio].[level_colegio].Members})} ON ROWS\n"+
             "FROM [cube_udenar]";
        
             /*  "SELECT\n" +
               "{[Measures].["+med+"]}ON COLUMNS,\n" +
                "NON EMPTY {DISTINCT({["+lev+"].Members})} ON ROWS\n" +
                "FROM ["+cu+"]"
        
        try
        {
            ClassConnection cOlap = new ClassConnection(path, host, dataBase, port, user, password);
            if(cOlap.processConnection() == true)
            {
                StructureCube sc = new StructureCube(cOlap.getOlapConnection());
                
            }
            else
            {
                System.out.println(cOlap.getExceptions());
            }
        }catch(Exception err)
        {
            System.out.println(err.toString());
        }
    }*/
}
