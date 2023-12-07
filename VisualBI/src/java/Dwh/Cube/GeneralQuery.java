/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dwh.Cube;


/**
 *
 * @author Carlitos
 */
public class GeneralQuery {
    private String dimension1;
    private String dimension2;
    private String dimension3;
    private String level1;
    private String level2;
    private String level3;
    private String nameCube;
    private String nameMeasure;
    public GeneralQuery(String dimension1, String dimension2, String dimension3, String level1, String level2, String level3, String nameCube, String nameMeasure) 
    {
        this.dimension1 = dimension1;
        this.dimension2 = dimension2;
        this.dimension3 = dimension3;
        this.level1 = level1;
        this.level2 = level2;
        this.level3 = level3;
        this.nameCube = nameCube;
        this.nameMeasure = nameMeasure;
    }
   public String getSql()
   {
       String MeasuresSql = "",sql="";
       String[] vecMeasures = nameMeasure.split(",");
       for(int j=0;j<vecMeasures.length;j++)
       {
           MeasuresSql+="[Measures].["+vecMeasures[j]+"],";
       }
       MeasuresSql = MeasuresSql.substring(0,MeasuresSql.length()-1);
       sql="SELECT\n"+
             "{"+MeasuresSql+"} ON COLUMNS,\n"+
             "NON EMPTY CrossJoin(CrossJoin({["+dimension1+"].["+level1+"].Members}, {["+dimension2+"].["+level2+"].Members}), {["+dimension3+"].["+level3+"].Members}) ON ROWS\n"+
             "FROM ["+nameCube+"]\n";
           return sql;  
   }
}
