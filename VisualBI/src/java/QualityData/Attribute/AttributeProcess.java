/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QualityData.Attribute;

import File.Upload.WriteFile;
import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;
import weka.core.Attribute;
import weka.core.Instances;

/**
 *
 * @author Carlitos
 */
public class AttributeProcess {
    private List<AttributeInformation> lI;
    private Attribute attributeP;
    public String exceptions;
    private Instances data;
    private String table;
    private String nominalFormat;
    private String numericFormat;
    private String path;
    public AttributeProcess(List<AttributeInformation> laI, Attribute attributep,Instances data,String path) {
        this.lI = laI;
        this.attributeP = attributep;
        this.data=data;
        this.table="";
        this.nominalFormat="";
        this.numericFormat="";
        this.path="";
    }

    public boolean toProcess()
    {
        try
        {
           ListIterator<AttributeInformation> liia = lI.listIterator();
           int v=0;
           AttributeInformation ainfo=null,aFind=null;
           StatisticalValues sv = null;
           while(liia.hasNext()==true && v==0)
            {
              ainfo= liia.next();
              if(attributeP.name().compareTo(ainfo.getAttributeName())==0)
               {
                 aFind=ainfo; 
                 v=1;
               }                       
            }
            if(v==0)
             {
                sv= new StatisticalValues(data, attributeP.name());
                sv.calculateStatisticalValues();
              
                 table=sv.getHtmlResultsTable();
                 nominalFormat=sv.getNominalFormat();
                 numericFormat= sv.getNumericFormat();
                 ainfo = new AttributeInformation(attributeP.name(), table, nominalFormat, numericFormat);
                 lI.add(ainfo);
              }
              else
              {
                  table= aFind.getAttributeTable();
                  nominalFormat = aFind.getAttributeNominalFormat();
                  numericFormat = aFind.getAttributeNumericFormat();
              } 
       return true;
        }catch(Exception error)
        {
            this.exceptions=error.getMessage();
            return false;
        }
    }
    
    public String getTable() {
        return table;
    }

    public String getNumericFormat() {
        return numericFormat;
    }
    public String getNominalFormat() {
        return nominalFormat;
    }
   public static void main(String arg[])
   {
       
   }
}
