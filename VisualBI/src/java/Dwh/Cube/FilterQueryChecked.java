/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dwh.Cube;

/**
 *
 * @author Carlitos
 */
public class FilterQueryChecked {
   private String dimension1;
    private String dimension2;
    private String dimension3;
    private String level1;
    private String level2;
    private String level3;
    private String nameCube;
    private String nameMeasures;
    private String htmlFilters;

    private String dimsNoUse;
    public FilterQueryChecked(String dimension1, String dimension2, String dimension3, String level1, String level2, String level3, String nameCube, String nameMeasures,String dimsNoUse) 
    {
        this.dimension1 = dimension1;
        this.dimension2 = dimension2;
        this.dimension3 = dimension3;
        this.level1 = level1;
        this.level2 = level2;
        this.level3 = level3;
        this.nameCube = nameCube;
        this.nameMeasures = nameMeasures;
        this.htmlFilters = "";
        this.dimsNoUse = dimsNoUse;
    }
    public String processFilter(String filter1, String filter2, String filter3)
    {
        String sql = "";
        String[] vecDimsNoUse = dimsNoUse.split(",");
       if(vecDimsNoUse.length==1)
       {
           if(vecDimsNoUse[0].compareTo(dimension1)==0)
           {
               sql = getSqlOneNoUse(dimension2, dimension3, level2, level3,filter2,filter3);
           }
          if(vecDimsNoUse[0].compareTo(dimension2)==0)
           {
               sql = getSqlOneNoUse(dimension1, dimension3, level1, level3,filter1,filter3);
           }
          if(vecDimsNoUse[0].compareTo(dimension3)==0)
           {
               sql = getSqlOneNoUse(dimension1, dimension2, level1, level2, filter1,filter2);
           }
       }
       if(vecDimsNoUse.length==2)
       {
           if((vecDimsNoUse[0].compareTo(dimension1)==0 && vecDimsNoUse[1].compareTo(dimension2)==0) || (vecDimsNoUse[0].compareTo(dimension2)==0 && vecDimsNoUse[1].compareTo(dimension1)==0))
           {
               sql = getSqlTwoNoUse(dimension3,level3,filter3);
           }
           if((vecDimsNoUse[0].compareTo(dimension1)==0 && vecDimsNoUse[1].compareTo(dimension3)==0) || (vecDimsNoUse[0].compareTo(dimension3)==0 && vecDimsNoUse[1].compareTo(dimension1)==0))
           {
               sql = getSqlTwoNoUse(dimension2,level2,filter2);
           }
           if((vecDimsNoUse[0].compareTo(dimension2)==0 && vecDimsNoUse[1].compareTo(dimension3)==0) || (vecDimsNoUse[0].compareTo(dimension3)==0 && vecDimsNoUse[1].compareTo(dimension2)==0))
           {
               sql = getSqlTwoNoUse(dimension1,level1,filter1);
           }
       }
        return sql;
    }
   
    private String getSqlOneNoUse(String dimensionA,String dimensionB,String levelA,String levelB,String filterA,String filterB)
    {
        String sql = "";
        if(filterA.trim().length()==0 && filterB.trim().length()!=0)
        {
            sql = getSqlOneNoUseOneFilter(dimensionA,dimensionB,levelA,levelB,filterB,2);
        }
        if(filterA.trim().length()!=0 && filterB.trim().length()==0)
        {
            sql = getSqlOneNoUseOneFilter(dimensionA,dimensionB,levelA,levelB,filterA,1);
        }
        if(filterA.trim().length()!=0 && filterB.trim().length()!=0)
        {
            sql = getSqlOneNoUseTwoFilter(dimensionA,dimensionB,levelA,levelB,filterA,filterB);
        }
        if(filterA.trim().length()==0 && filterB.trim().length()==0)
        {
            //sql = getSqlOneNoUseTwoFilter(dimensionA,dimensionB,levelA,levelB,filterA,filterB);
            sql = "SELECT\n"+
             "{"+getSqlMeasures()+"} ON COLUMNS,\n"+
             "NON EMPTY{CrossJoin({["+dimensionA+"].["+levelA+"].Members},{["+dimensionB+"].["+levelB+"].Members})} ON ROWS\n"+
             "FROM ["+nameCube+"] ";
        }
        return sql;
    }
    
    private String getSqlTwoNoUse(String dimension, String level, String filter)
    {
        String sql = "",vec1 = "";
        if(filter.trim().length()!=0)
        {
           String html =""+
           "<table>"+
             "<tr><th>Filtrado Por:</th></tr>"+
             "<tr><th>"+level+"</th><td>"+filter+"</td></tr>"+
           "</table>";
           this.htmlFilters = html;
            vec1 = getSqlFilter(dimension, level, filter);
            sql = "SELECT\n"+
            "{"+getSqlMeasures()+"} ON COLUMNS,\n"+
            "NON EMPTY{DISTINCT( "+vec1+")} ON ROWS\n"+
            "FROM ["+nameCube+"]";
        }
        else
        {
            sql = "SELECT\n"+
             "{"+getSqlMeasures()+"} ON COLUMNS,\n"+
             "NON EMPTY{DISTINCT({["+dimension+"].["+level+"].Members})} ON ROWS\n"+
             "FROM ["+nameCube+"]";
        }
        return sql;
    }
    
    private String getSqlOneNoUseOneFilter(String dimensionA,String dimensionB,String levelA,String levelB,String filter,int index)
    {
        String sql = "",vec1 = "";
        //-------------------------
/*
 "SELECT\n"
   + "{[Measures].[nprom]} ON COLUMNS,"
   +"NON EMPTY {CrossJoin({[alumno].[dim_alumno.pais_nacimiento].[NICARAGUA]},{[materia].[dim_materia.nombre].Members})} ON ROWS\n"
   + "FROM [aprobacion]"
 */
        if(index == 1)
        {
            vec1 = getSqlFilter(dimensionA, levelA, filter);
            /*sql = "SELECT\n"+
             "{"+getSqlMeasures()+"} ON COLUMNS,\n"+
             "NON EMPTY CrossJoin({["+dimensionA+"].["+levelA+"].Members}, {["+dimensionB+"].["+levelB+"].Members}) ON ROWS\n"+
             "FROM ["+nameCube+"] WHERE "+vec1+"";*/
           String html =""+
           "<table>"+
             "<tr><th>Filtrado Por:</th></tr>"+
             "<tr><th>"+levelA+"</th><td>"+filter+"</td></tr>"+
           "</table>";
           this.htmlFilters = html;
            sql =  "SELECT\n"
            + "{"+getSqlMeasures()+"} ON COLUMNS,"
            +"NON EMPTY {CrossJoin("+vec1+",{["+dimensionB+"].["+levelB+"].Members})} ON ROWS\n"
            + "FROM ["+nameCube+"]";
            
            
        }
        else
        {
            vec1 = getSqlFilter(dimensionB, levelB, filter);
           String html =""+
           "<table>"+
             "<tr><th>Filtrado Por:</th></tr>"+
             "<tr><th>"+levelB+"</th><td>"+filter+"</td></tr>"+
           "</table>";
           this.htmlFilters = html;
            sql =  "SELECT\n"
            + "{"+getSqlMeasures()+"} ON COLUMNS,"
            +"NON EMPTY {CrossJoin("+vec1+",{["+dimensionA+"].["+levelA+"].Members})} ON ROWS\n"
            + "FROM ["+nameCube+"]";
            
        }
        //-------------------------
        return sql;
    }
    
    private String getSqlOneNoUseTwoFilter(String dimensionA,String dimensionB,String levelA,String levelB,String filterA,String filterB)
    {
        String vec1 = "",vec2 = "";
        vec1 = getSqlFilter(dimensionA, levelA, filterA);
        vec2 = getSqlFilter(dimensionB, levelB, filterB);
           String html =""+
           "<table>"+
             "<tr><th>Filtrado Por:</th></tr>"+
             "<tr><th>"+levelA+"</th><td>"+filterA+"</td></tr>"+
             "<tr><th>"+levelB+"</th><td>"+filterB+"</td></tr>"+
           "</table>";
           this.htmlFilters = html;
        String sql = "";
             sql = "SELECT\n"+
             "{"+getSqlMeasures()+"} ON COLUMNS,\n"+
             "NON EMPTY{CrossJoin("+vec1+","+vec2+")} ON ROWS\n"+
             "FROM ["+nameCube+"]";
        return sql;
    }
    private String getSqlMeasures()
    {
        String MeasuresSql = "";
        String[] vecMeasures = nameMeasures.split(",");
        MeasuresSql+="[Measures].["+vecMeasures[0]+"]";
        for(int j=1;j<vecMeasures.length;j++)
        {
           MeasuresSql+=",[Measures].["+vecMeasures[j]+"]";
        }
       return MeasuresSql;
   }
   
   private String getSqlFilter(String dimension,String level,String filter)
    {
       String sqlFiltro = "";
       String[] vecFilter1 = filter.split(",");
       String vec2="{",vec22="";
        vec22+="["+dimension+"].["+level+"].";
        for(int x=0;x<vecFilter1.length;x++)
        {
            sqlFiltro += vec22 +"["+vecFilter1[x]+"],";
        }
        sqlFiltro= sqlFiltro.substring(0,sqlFiltro.length()-1);
        vec2+= sqlFiltro+"}";
      return vec2;
    }
       public String getHtmlFilters() {
        return htmlFilters;
    }
    
}
