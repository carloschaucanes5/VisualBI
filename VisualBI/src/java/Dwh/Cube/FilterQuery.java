/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dwh.Cube;

/**
 *
 * @author Carlitos
 */
public class FilterQuery {
    private String dimension1;
    private String dimension2;
    private String dimension3;
    private String level1;
    private String level2;
    private String level3;
    private String nameCube;
    private String nameMeasures;
    private String htmlFilters;
    public FilterQuery(String dimension1, String dimension2, String dimension3, String level1, String level2, String level3, String nameCube, String nameMeasures) 
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
    }

    public String processFilter(String filter1, String filter2, String filter3)
    {
        String sql = "";
        //------------------------------------------------------------
        if(filter1.trim().length()==0 && filter2.trim().length() == 0 && filter3.trim().length()!=0)
        {
            sql = getSqlOneFilter(filter3,3);
        }
        if(filter1.trim().length()==0 && filter3.trim().length() == 0 && filter2.trim().length()!=0)
        {
            sql = getSqlOneFilter(filter2,2);
        }
        if(filter2.trim().length()==0 && filter3.trim().length() == 0 && filter1.trim().length()!=0)
        {
            sql = getSqlOneFilter(filter1,1);
        }
        //-------------------------------------------------------------
        if(filter1.trim().length()==0 && filter2.trim().length() != 0 && filter3.trim().length()!=0)
        {
            sql = getSqlTwoFilters(filter2,2,filter3,3);
        }
        if(filter2.trim().length()==0 && filter1.trim().length() != 0 && filter3.trim().length()!=0)
        {
            sql = getSqlTwoFilters(filter1,1,filter3,3);
        }
        if(filter3.trim().length()==0 && filter1.trim().length() != 0 && filter2.trim().length()!=0)
        {
            sql = getSqlTwoFilters(filter1,1,filter2,2);
        }
        //-------------------------------------------------------------
        if(filter1.trim().length()!=0 && filter2.trim().length() != 0 && filter3.trim().length()!=0)
        {
            sql = getSqlThreeFilters(filter1,filter2, filter3);
        }
        //-------------------------------------------------------------
        
        //String sql="";
       /*sql+="Dimension1=>"+dimension1+"<br/>";
        sql+="Dimension2=>"+dimension2+"<br/>";
        sql+="Dimension3=>"+dimension3+"<br/>";
        sql+="level1=>"+level1+"<br/>";
        sql+="level2=>"+level2+"<br/>";
        sql+="level3=>"+level3+"<br/>";
        sql+="Filter1=>"+filter1+"<br/>";
        sql+="Filter2=>"+filter2+"<br/>";
        sql+="Filter3=>"+filter3+"<br/>";*/
        return sql;
        
    }
    
    private String getSqlOneFilter(String filter, int index)
    {
       String sql = "",html="";
       if(index==1)
       {
            //*************************************************
           html =""+
           "<table>"+
             "<tr><th>Filtrado Por:</th></tr>"+
             "<tr><th>"+level1+"</th><td>"+filter+"</td></tr>"+
           "</table>";
           this.htmlFilters = html;
           //**************************************************
            sql = "SELECT\n"+
            "{"+getSqlMeasures()+"} ON COLUMNS,\n"+
            "NON EMPTY{CrossJoin({["+dimension2+"].["+level2+"].Members},{["+dimension3+"].["+level3+"].Members})} ON ROWS\n"+
            "FROM ["+nameCube+"] WHERE "+getSqlFilter(dimension1, level1, filter);
       }
       if(index==2)
       {
          html =""+
           "<table>"+
             "<tr><th>Filtrado Por:</th></tr>"+
             "<tr><th>"+level2+"</th><td>"+filter+"</td></tr>"+
           "</table>";
           this.htmlFilters = html;
            sql = "SELECT\n"+
            "{"+getSqlMeasures()+"} ON COLUMNS,\n"+
            "NON EMPTY{CrossJoin({["+dimension1+"].["+level1+"].Members},{["+dimension3+"].["+level3+"].Members})} ON ROWS\n"+
            "FROM ["+nameCube+"] WHERE "+getSqlFilter(dimension2, level2, filter);
       }
       if(index==3)
       {
           html =""+
           "<table>"+
             "<tr><th>Filtrado Por:</th></tr>"+
             "<tr><th>"+level3+"</th><td>"+filter+"</td></tr>"+
           "</table>";
           this.htmlFilters = html;
            sql = "SELECT\n"+
            "{"+getSqlMeasures()+"} ON COLUMNS,\n"+
            "NON EMPTY{CrossJoin({["+dimension1+"].["+level1+"].Members},{["+dimension2+"].["+level2+"].Members})} ON ROWS\n"+
            "FROM ["+nameCube+"] WHERE "+getSqlFilter(dimension3, level3, filter);
       }
       
       
        return sql;
    }
    
    private String getSqlTwoFilters(String filterA,int indexA,String filterB,int indexB)
    {
        String sql = "", html = "";
        if(indexA==1 && indexB==2)
        {
           html =""+
           "<table>"+
             "<tr><th>Filtrado Por:</th></tr>"+
             "<tr><th>"+level1+"</th><td>"+filterA+"</td></tr>"+
             "<tr><th>"+level2+"</th><td>"+filterB+"</td></tr>"+
           "</table>";
           this.htmlFilters = html;           
             sql= "SELECT\n"
               + "{"+getSqlMeasures()+"} ON COLUMNS,"
               +"NON EMPTY {CrossJoin(CrossJoin("+getSqlFilter(dimension1, level1, filterA) +","+getSqlFilter(dimension2, level2, filterB)+"),{["+dimension3+"].["+level3+"].Members})} ON ROWS\n"
               + "FROM ["+nameCube+"]";
        }
        if(indexA==1 && indexB==3)
        {
           html =""+
           "<table>"+
             "<tr><th>Filtrado Por:</th></tr>"+
             "<tr><th>"+level1+"</th><td>"+filterA+"</td></tr>"+
             "<tr><th>"+level3+"</th><td>"+filterB+"</td></tr>"+
           "</table>";
           this.htmlFilters = html;
            sql= "SELECT\n"
               + "{"+getSqlMeasures()+"} ON COLUMNS,"
               +"NON EMPTY {CrossJoin(CrossJoin("+getSqlFilter(dimension1, level1, filterA) +","+getSqlFilter(dimension3, level3, filterB)+"),{["+dimension2+"].["+level2+"].Members})} ON ROWS\n"
               + "FROM ["+nameCube+"]";
        }
        if(indexA==2 && indexB==3)
        {
           html =""+
           "<table>"+
             "<tr><th>Filtrado Por:</th></tr>"+
             "<tr><th>"+level2+"</th><td>"+filterA+"</td></tr>"+
             "<tr><th>"+level3+"</th><td>"+filterB+"</td></tr>"+
           "</table>";
           this.htmlFilters = html;
              sql= "SELECT\n"
               + "{"+getSqlMeasures()+"} ON COLUMNS,"
               +"NON EMPTY {CrossJoin(CrossJoin("+getSqlFilter(dimension2, level2, filterA) +","+getSqlFilter(dimension3, level3, filterB)+"),{["+dimension1+"].["+level1+"].Members})} ON ROWS\n"
               + "FROM ["+nameCube+"]";
        }        
        return sql;
    }
    
   private String getSqlThreeFilters(String filter1, String filter2, String filter3)
   {
       String html = "";
      html =""+
           "<table>"+
             "<tr><th>Filtrado Por:</th></tr>"+
             "<tr><th>"+level1+"</th><td>"+filter1+"</td></tr>"+
             "<tr><th>"+level2+"</th><td>"+filter2+"</td></tr>"+
             "<tr><th>"+level3+"</th><td>"+filter3+"</td></tr>"+
           "</table>";
           this.htmlFilters = html;
       String sql = "",filterTotal="";
       filterTotal+= getSqlFilter(dimension1, level1, filter1)+",";
       filterTotal+= getSqlFilter(dimension2, level2, filter2);
               sql = "SELECT\n"+
             "{"+getSqlMeasures()+"} ON COLUMNS,\n"+
             "NON EMPTY{CrossJoin(CrossJoin("+filterTotal+"),"+getSqlFilter(dimension3, level3, filter3) +")} ON ROWS\n"+
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
