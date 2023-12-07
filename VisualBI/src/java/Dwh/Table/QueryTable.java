/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dwh.Table;
/**
 *
 * @author Carlitos
 */
import Dwh.Connection.ClassConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.olap4j.Cell;
import org.olap4j.CellSet;
import org.olap4j.CellSetAxis;
import org.olap4j.OlapConnection;
import org.olap4j.OlapException;
import org.olap4j.OlapStatement;
import org.olap4j.Position;
import org.olap4j.metadata.Member;

/**
 *
 * @author Carlitos
 */
public class QueryTable {
    private  OlapConnection olapConnection;
    private String sql;
    private String exceptions;
    private CellSet result;
    private List<String> listCols;
    private List<String> listRows;      
    private List<MeasureRowCols> listValues;
    private String measures;
    public QueryTable(OlapConnection olapConnection ,String sql, String measures) {
        this.olapConnection = olapConnection;
        this.sql = sql;
        this.exceptions = "";
        this.listCols = new ArrayList<String>();
        this.listRows = new ArrayList<String>();
        this.listValues=new ArrayList<MeasureRowCols>();
        this.measures = measures;
    }
    public boolean processQuery() throws SQLException
    {
        try
        {
             OlapStatement statement = olapConnection.createStatement();
             result =   statement.executeOlapQuery(sql);
            // Fifth, display the Axes
            for (CellSetAxis axis : result.getAxes())
            {                     
                for(Position position : axis.getPositions())
                {
                    String itemFXC="";
                    for (Member member : position.getMembers())
                    {
                         String ca=member.getName();
                           itemFXC+=ca+",";     
                    }
                    itemFXC=itemFXC.substring(0,itemFXC.length()-1);
                    listRows.add(itemFXC);
                }
            }     
            // Finally, display the Cells
            CellSetAxis cols = result.getAxes().get(0);
            CellSetAxis rows = result.getAxes().get(1);
            for(int row = 0; row < rows.getPositions().size(); row++) 
            {
                String vals="";
                for (int col = 0; col < cols.getPositions().size(); col++)
                {
                    List positions = new ArrayList(2);
                    positions.add(col);
                    positions.add(row);
                    Cell cell = result.getCell(positions);
                    String med=cell.getFormattedValue();
                    vals+=med+"-";
                }
                vals=vals.substring(0, vals.length()-1);
                MeasureRowCols oMedida = new MeasureRowCols(row,vals);
                listValues.add(oMedida);
            }
        
            return true;
        }
        catch(OlapException error)
        {
            this.exceptions=error.getSQLState();
            return false;
        }
    }
    
//-----------------------------------------------------------------------------
    private String getHtmlCols() 
    { 
        try
        {
            String htmlC = "";
            htmlC="<tr>";
            //---------------------------------------------------------------------
            List<CellSetAxis> v = result.getAxes();
            List<Position> c = v.get(0).getPositions();
            List<Position>  f=v.get(1).getPositions(); 
            List<Member> lisf = f.get(0).getMembers(); 
            List<Member> lisc = c.get(0).getMembers();
            for(int j=0;j<lisf.size();j++)
            {
                htmlC+="<th>"+lisf.get(j).getLevel().getName()+"</th>";
            }
            String[] vecMeaures = measures.split(",");
            for(int y=0;y<vecMeaures.length;y++)
            {
                htmlC+="<th>"+vecMeaures[y]+"</th>";
            }
            htmlC+="</tr>";
            return htmlC;
        }catch(Exception err)
        {
            this.exceptions = err.getMessage();
            return err.getMessage();
        }
    }

   public String getTableLevelFilter(int index)
   {
       String tableHtml="";
        int indexFirstRow = listValues.get(0).getValuesCols().split("-").length;
        List<CellSetAxis> v = result.getAxes();
        List<Position>  f=v.get(1).getPositions(); 
        List<Member> lisf = f.get(0).getMembers();
        tableHtml="<table>";
        tableHtml+="<tr><th>Chequear</th><th>"+lisf.get(0).getLevel().getName()+"</th></tr>";
        for(int j=indexFirstRow;j<listRows.size();j++)
        {
            if(j%2==0)
            {
                tableHtml+="<tr><td  BGCOLOR='#81BEF7'><input  name = 'filter"+index+"'  type='checkbox'  value='"+listRows.get(j)+"'></td><td>"+listRows.get(j)+"</td></tr>";
            }
            else
            {
                tableHtml+="<tr><td  BGCOLOR='#abd0bd'><input  name = 'filter"+index+"'  type='checkbox'  value='"+listRows.get(j)+"'></td><td>"+listRows.get(j)+"</td></tr>";
            }
            
        }
        tableHtml+="</table>";
       return tableHtml;
   }
   private String getHtmlRows()
    {
        int indexFirstRow = listValues.get(0).getValuesCols().split("-").length;
        int row = indexFirstRow,nc=0,numCols=0;
        String colrgb="",first="",htmlC="",htmlF="",concat="";
        numCols = listRows.get(listRows.size()-1).split(",").length;
        first="";//listRows.get(row).split(",")[0];
        ColorRGB rgb= new ColorRGB();
        colrgb=rgb.getColorAleatorio();
        //-----------------------------------------
        List<String> lFila=new ArrayList<String>();
        String[] vecValores=null;
        for(int g=indexFirstRow;g<listRows.size();g++)
        {
            lFila.add("");
        }
        //-----------------------------------------
        while(row<listRows.size() && nc<numCols)
        {
            //System.out.println(listRows.get(row)+"\t"+listValues.get(row-indexFirstRow).getValuesCols()); 
            //------------------------------------------------------------------------------
           String col=listRows.get(row).split(",")[nc];
           if(col.trim().compareTo(first.trim())!=0 || nc==numCols-1)
           {
               colrgb=rgb.getColorAleatorio();
               first=col;
               //-----------------------------
               if(nc==numCols-1)
               {
                   if(row%2==0)
                   {
                        concat=lFila.get(row-indexFirstRow);
                        htmlF=concat+"<th  BGCOLOR='#81BEF7'>"+col+"</th>";
                        lFila.set(row-indexFirstRow, htmlF);
                        htmlF="";
                   }
                   else
                   {
                        concat=lFila.get(row-indexFirstRow);
                        htmlF=concat+"<th BGCOLOR='#abd0bd'>"+col+"</th>";
                        lFila.set(row-indexFirstRow, htmlF);
                        htmlF="";
                   }
                //--------------------------------------------------------
               }
               else
               {
                 concat=lFila.get(row-indexFirstRow);
                 htmlF=concat+"<th BGCOLOR='"+colrgb+"'>"+col+"</th>";
                 lFila.set(row-indexFirstRow, htmlF);
                 htmlF="";
               }
           }
           else
           {
             if(row==indexFirstRow)
               {
                   concat=lFila.get(row-indexFirstRow);
                   htmlF=concat+"<th BGCOLOR='"+colrgb+"'>"+col+"</th>";
                   lFila.set(row-indexFirstRow, htmlF);
                   htmlF="";
               }
               else
               {
                   concat=lFila.get(row-indexFirstRow);
                   htmlF=concat+"<th BGCOLOR='"+colrgb+"'></th>";
                   lFila.set(row-indexFirstRow, htmlF);
                   htmlF="";
               }
           }
            //------------------------------------------------------------------------------
          if(nc==numCols-1)
           {
             //----------------------------------------------------------
             concat=lFila.get(row-indexFirstRow);
             htmlF=concat;
             vecValores=listValues.get(row-indexFirstRow).getValuesCols().split("-");
             for(int y=0;y<vecValores.length;y++)
              {
                  if(row%2==0)
                  {
                     htmlF+="<td BGCOLOR='#81BEF7'>"+vecValores[y]+"</td>"; 
                  }
                  else
                  {
                      htmlF+="<td BORDER BGCOLOR='#abd0bd'>"+vecValores[y]+"</td>"; 
                  }    
              }
             lFila.set(row-indexFirstRow, htmlF);
              
           }
            //------------------------------------------------------------------------------
            row++;
           if(row==listRows.size())
           {
               nc++;
               row=indexFirstRow;
           }
        }
       htmlF="";
       for(int i=0;i<lFila.size();i++)
       {
             htmlF+="<tr>"+lFila.get(i) +"</tr>\n";       
       }
        return htmlF;
    }
    
   public String getTable()
    {
        String tabla="";
        tabla="<table style='text-align:center'>";
        tabla+=getHtmlCols();
        tabla+=getHtmlRows();
        tabla+="</table>";
        return tabla;
    }
    
    public String getExceptions() {
        return exceptions;
    }
    /*
    public static void main(String arg[])
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
        /*sql1 ="SELECT\n"+
             "NON EMPTY [Measures].[measure_cantidad] ON COLUMNS,\n"+
             "NON EMPTY CrossJoin([dim_usage_colegio].[level_colegio].Members, CrossJoin([dim_usage_estudiante].[level_estado_civil].Members, [dim_usage_academica].[level_facultad].Members)) ON ROWS\n"+
             "FROM [cube_udenar]";*/
        /*sql1+="SELECT\n"+
        "NON EMPTY [Measures].[measure_cantidad] ON COLUMNS,\n"+
        "NON EMPTY CrossJoin([dim_usage_colegio ].[level_tipo_colegio].Members, CrossJoin([dim_usage_academica].[level_programa].Members, [dim_usage_geografia].[level_pais].Members)) ON ROWS\n"+
        "FROM [cube_udenar]\n";*/
        //----------------------------------------------------------------------
        /* sql1="SELECT\n"+
             "NON EMPTY [Measures].[measure_cantidad] ON COLUMNS,\n"+
             "NON EMPTY CrossJoin([dim_usage_colegio].[level_tipo_colegio].Members, CrossJoin([dim_usage_academica].[level_programa].Members, [dim_usage_geografia].[level_pais].Members)) ON ROWS\n"+
             "FROM [cube_udenar]\n";*/
        
       /* sql1="SELECT\n"+
             "{[Measures].[nprom],[Measures].[nveces]} ON COLUMNS,\n"+
             "NON EMPTY {CrossJoin({[alumno].[dim_alumno.pais_nacimiento].Members}, CrossJoin({[carrera].[dim_carrera.nombre_carrera].Members}, {[materia].[dim_materia.nombre].Members}))} ON ROWS\n"+
             "FROM [aprobacion]\n";*/
        
            /*sql1="SELECT\n"+
             "{[Measures].[nprom],[Measures].[nveces]} ON COLUMNS,\n"+
             "NON EMPTY {DISTINCT({[carrera].[dim_carrera.nombre_carrera].Members})} ON ROWS\n"+
             "FROM [aprobacion]\n";*/
        /*
             sql1 ="SELECT\n"+
             "NON EMPTY [Measures].[measure_cantidad] ON COLUMNS,\n"+
             "NON EMPTY {DISTINCT({[dim_usage_estado].[level_estado].Members})} ON ROWS\n"+
             "FROM [cube_udenar]";*/
            
            /*sql1="SELECT\n"+
             "{[Measures].[nprom],[Measures].[nveces]} ON COLUMNS,\n"+
             "NON EMPTY{DISTINCT( {[carrera].[dim_carrera.nombre_carrera].Members})} ON ROWS\n"+
             "FROM [aprobacion]";
            
        //sql1+="SELECT {[Measures].[nprom],[Measures].[nveces]} ON COLUMNS, NON EMPTY{DISTINCT({[carrera].[dim_carrera.nombre_carrera].Members})} ON ROWS FROM [aprobacion]";
        //sql1+="SELECT {[Measures].[nprom],[Measures].[nveces]} ON COLUMNS, NON EMPTY{CrossJoin({[alumno].[dim_alumno.pais_nacimiento].Members},{[carrera].[dim_carrera.nombre_carrera].Members})} ON ROWS FROM [aprobacion]";
        //sql1="SELECT {[Measures].[nprom],[Measures].[nveces]} ON COLUMNS, NON EMPTY{CrossJoin({[alumno].[dim_alumno.pais_nacimiento].Members},{[carrera].[dim_carrera.nombre_carrera].Members})} ON ROWS FROM [aprobacion]";
        //sql1="SELECT {[Measures].[nprom],[Measures].[nveces]} ON COLUMNS, NON EMPTY {DISTINCT({[carrera].[dim_carrera.nombre_carrera].Members})} ON ROWS FROM [aprobacion]";
        //sql1="SELECT {[Measures].[nprom],[Measures].[nveces]} ON COLUMNS, NON EMPTY {DISTINCT({[carrera].[dim_carrera.nombre_carrera].Members})} ON ROWS FROM [aprobacion]";
        //sql1="SELECT {[Measures].[nprom]} ON COLUMNS, NON EMPTY CrossJoin({[alumno].[dim_alumno.pais_nacimiento].Members}, {[carrera].[dim_carrera.nombre_carrera].Members}) ON ROWS FROM [aprobacion] WHERE {[alumno].[dim_alumno.pais_nacimiento].[NICARAGUA]}"; 
        //sql1 = "SELECT {[Measures].[nveces]} ON COLUMNS, NON EMPTY CrossJoin({[alumno].[dim_alumno.pais_nacimiento].Members}, {[carrera].[dim_carrera.nombre_carrera].Members}) ON ROWS FROM [aprobacion] WHERE {[alumno].[dim_alumno.pais_nacimiento].[NICARAGUA]}"; 
        //sql1 = "SELECT {[Measures].[nveces]} ON COLUMNS, NON EMPTY{CrossJoin({[carrera].[dim_carrera.nombre_carrera].[INGENIERIA DE SISTEMAS]},{[materia].[dim_materia.nombre].[CALCULO I],[materia].[dim_materia.nombre].[CALCULO II],[materia].[dim_materia.nombre].[CONTABILIDAD Y ANALISIS FINANCIERO],[materia].[dim_materia.nombre].[DISENO DE COMPILADORES],[materia].[dim_materia.nombre].[LOGICA MATEMATICA]})} ON ROWS FROM [aprobacion]";
        //sql1="SELECT {[Measures].[nprom],[Measures].[nveces]} ON COLUMNS, NON EMPTY{CrossJoin({[carrera].[dim_carrera.nombre_carrera].Members},{[materia].[dim_materia.nombre].Members})} ON ROWS FROM [aprobacion] ";
        //sql1="SELECT {[Measures].[nprom],[Measures].[nveces]} ON COLUMNS, NON EMPTY {DISTINCT({[carrera].[dim_carrera.nombre_carrera].Members})} ON ROWS FROM [aprobacion] ";
        //sql1="SELECT {[Measures].[nprom]} ON COLUMNS, NON EMPTY CrossJoin({[materia].[dim_materia.nombre].Members}, {[alumno].[dim_alumno.pais_nacimiento].Members}) ON ROWS FROM [aprobacion] WHERE {[materia].[dim_materia.nombre].[CALCULO I]}";
        //sql1="SELECT {[Measures].[nprom],[Measures].[nveces]} ON COLUMNS, NON EMPTY {DISTINCT({[carrera].[dim_carrera.nombre_carrera].Members})} ON ROWS FROM [aprobacion]";
        //sql1 = "SELECT {[Measures].[measure_cantidad]} ON COLUMNS,NON EMPTY {CrossJoin(CrossJoin({[dim_usage_temporal].[level_semester_per].[ARQUITECTURA],[dim_usage_temporal].[level_semester_per].[ARTES VISUALES],[dim_usage_temporal].[level_semester_per].[BIOLOGIA],[dim_usage_temporal].[level_semester_per].[DERECHO]},{[dim_usage_estudiante].[level_genero].[M]}),{[dim_usage_academica].[level_programa].Members})} ON ROWS FROM [cube_udenar]";
        //sql1="SELECT {[Measures].[measure_cantidad]} ON COLUMNS, NON EMPTY{DISTINCT( {[dim_usage_estudiante].[level_estado_civil].[UNIÓN LIBRE]})} ON ROWS FROM [cube_udenar]";
        //sql1="SELECT {[Measures].[measure_cantidad]} ON COLUMNS,NON EMPTY {CrossJoin({[dim_usage_estudiante].[level_estado_civil].[SOLTERO]},{[dim_usage_colegio].[level_colegio].Members})} ON ROWS FROM [cube_udenar]";
        try
        {
            String levels = "colegio,facultad,genero";
            String measures = "prom,med";
            ClassConnection cOlap = new ClassConnection(path, host, dataBase, port, user, password);
            if(cOlap.processConnection() == true)
            {
                QueryTable qo = new QueryTable(cOlap.getOlapConnection(), sql1,measures);
                if(qo.processQuery() == true)
                {
                  System.out.println(qo.getTable());
                  //System.out.println(qo.getHtmlRows()); 
                }     
                //System.out.println("Connection success");
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
