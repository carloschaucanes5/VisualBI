/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dwh.Cube;

/**
 *
 * @author Carlitos
 */
public class SqlFilterLevel {

    private String dimension;
    private String level;
    private String nameCube;
    private String measures;
    
    public SqlFilterLevel(String dimension, String level, String nameCube, String measures) {
        this.dimension = dimension;
        this.level = level;
        this.nameCube = nameCube;
        this.measures = measures;
    }
    
    public String getDistinctValuesSql()
    {
      String MeasuresSql = "",sql="";
      String[] vecMeasures = measures.split(",");
      for(int j=0;j<vecMeasures.length;j++)
       {
           MeasuresSql+="[Measures].["+vecMeasures[j]+"],";
       }
      MeasuresSql = MeasuresSql.substring(0,MeasuresSql.length()-1);
            sql="SELECT\n"+
             "{"+MeasuresSql+"} ON COLUMNS,\n"+
             "NON EMPTY {DISTINCT({["+dimension+"].["+level+"].Members})} ON ROWS\n"+
             "FROM ["+nameCube+"]\n";
        return sql;
    }
}
