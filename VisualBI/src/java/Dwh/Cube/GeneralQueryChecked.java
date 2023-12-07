/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dwh.Cube;

/**
 *
 * @author Carlitos
 */
public class GeneralQueryChecked {
    private String dimension1;
    private String dimension2;
    private String dimension3;
    private String level1;
    private String level2;
    private String level3;
    private String nameCube;
    private String nameMeasures;
    private String dimsNoUse;
    public GeneralQueryChecked(String dimension1, String dimension2,String dimension3, String level1, String level2, String level3, String nameCube, String nameMeasures, String dimsNoUse) {
        this.dimension1 = dimension1.trim();
        this.dimension2 = dimension2.trim();
        this.dimension3 = dimension3.trim();
        this.level1 = level1.trim();
        this.level2 = level2.trim();
        this.level3 = level3.trim();
        this.nameCube = nameCube;
        this.nameMeasures = nameMeasures.trim();
        this.dimsNoUse = dimsNoUse.trim();
    }
    
   public String getSql()
   {
       String sql = "";
       /*String a = "";
       a+="Dimension1=>"+dimension1+"<br/>";
       a+="Dimension2=>"+dimension2+"<br/>";
       a+="Dimension3=>"+dimension3+"<br/>";
       a+="Dims No Use=>"+dimsNoUse+"<br/>";*/
       //---------------------------------
       String[] vecDimsNoUse = dimsNoUse.split(",");
       if(vecDimsNoUse.length==1)
       {
           if(vecDimsNoUse[0].compareTo(dimension1)==0)
           {
               sql = getSqlOneNoUse(dimension2, dimension3, level2, level3);
           }
          if(vecDimsNoUse[0].compareTo(dimension2)==0)
           {
               sql = getSqlOneNoUse(dimension1, dimension3, level1, level3);
           }
          if(vecDimsNoUse[0].compareTo(dimension3)==0)
           {
               sql = getSqlOneNoUse(dimension1, dimension2, level1, level2);
           }
       }
       if(vecDimsNoUse.length==2)
       {
           if((vecDimsNoUse[0].compareTo(dimension1)==0 && vecDimsNoUse[1].compareTo(dimension2)==0) || (vecDimsNoUse[0].compareTo(dimension2)==0 && vecDimsNoUse[1].compareTo(dimension1)==0))
           {
               sql = getSqlTwoNoUse(dimension3,level3);
           }
           if((vecDimsNoUse[0].compareTo(dimension1)==0 && vecDimsNoUse[1].compareTo(dimension3)==0) || (vecDimsNoUse[0].compareTo(dimension3)==0 && vecDimsNoUse[1].compareTo(dimension1)==0))
           {
               sql = getSqlTwoNoUse(dimension2,level2);
           }
           if((vecDimsNoUse[0].compareTo(dimension2)==0 && vecDimsNoUse[1].compareTo(dimension3)==0) || (vecDimsNoUse[0].compareTo(dimension3)==0 && vecDimsNoUse[1].compareTo(dimension2)==0))
           {
               sql = getSqlTwoNoUse(dimension1,level1);
           }
       }
       return sql;
   }
   private String getSqlOneNoUse(String dim1, String dim2, String lev1, String lev2)
   {
       String sql = "";
       sql = "SELECT\n"+
             "{"+getSqlMeasures()+"} ON COLUMNS,\n"+
             "NON EMPTY{CrossJoin({["+dim1+"].["+lev1+"].Members},{["+dim2+"].["+lev2+"].Members})} ON ROWS\n"+
             "FROM ["+nameCube+"] ";
       return sql;
   }
   
   private String getSqlTwoNoUse(String dim,String level)
   {
       String sql = "";
       sql = "SELECT\n"+
             "{"+getSqlMeasures()+"} ON COLUMNS,\n"+
             "NON EMPTY{DISTINCT({["+dim+"].["+level+"].Members})} ON ROWS\n"+
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
   
}
