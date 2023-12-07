/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QualityData.Attribute;

import File.Upload.InstancesFile;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import weka.core.Instances;
import weka.experiment.Stats;

/**
 *
 * @author Carlitos
 */
public class StatisticalValues {
    private Instances data;
    private String attributeName;
    private String htmlResultsTable;
    private String nominalFormat;
    private String numericFormat;
    private String exceptions;
    private String generalFormat;
    public StatisticalValues(Instances data,String attributeName)
    {
        this.data=data;
        this.attributeName=attributeName;
        this.htmlResultsTable="<table   class='table table-striped table-hover table-bordered' id='sample_editable_1' size='80'><tr><th colspan='2'>"+attributeName+"</th></tr><tr><th>Característica</th><th>Valor</th></tr></table>";
        this.nominalFormat="function funJson1(){var json={'label':['"+attributeName+"'],'values':[ ]};return json;};";
        this.numericFormat="";
        this.generalFormat="";
    }

    public void calculateStatisticalValues()
    {   
     try
      {   
        int indexAtributo=data.attribute(attributeName.trim()).index();
        if(data.attribute(attributeName.trim()).isNumeric()==true)
        {  
            Stats st1 = data.attributeStats(indexAtributo).numericStats;
            setHtmlResultsTable("Mínimo",st1.min+"",0);
            setHtmlResultsTable("Máximo",st1.max+"",1);
            setHtmlResultsTable("Media",st1.mean+"",2);
            setHtmlResultsTable("Desviación estándar",st1.stdDev+"",3);
            FrequencyDistribution fd= new FrequencyDistribution(st1.min, st1.max,data,indexAtributo);
            fd.applyDistributionNormal();
            this.nominalFormat=fd.getFormatNominal();
            this.numericFormat = fd.getFormatNumeric();
            this.htmlResultsTable+="<br/><br/>"+fd.getHtmlFormat();
        }
        else
        {
            Enumeration<String> ins3 = data.attribute(attributeName).enumerateValues();
             int j=0;
             String labels="",datas="";
              while(ins3.hasMoreElements()==true)
              {
                String valAtributo=ins3.nextElement();
                int[] st2 = data.attributeStats(indexAtributo).nominalCounts;
                double valuePorcentaje = (st2[j]*100)/(data.numInstances()-1);
                setHtmlResultsTable(valAtributo,st2[j]+"",j);
               // setNominalFormat(valAtributo,st2[j]);
                setNominalFormat(valAtributo,valuePorcentaje);
                //-----------------------------------------
                labels+="'"+valAtributo+"',";
                datas+=st2[j]+",";
                //-----------------------------------------
                j++;
              }
            labels = labels.substring(0,labels.length()-1);
            datas = datas.substring(0,datas.length()-1);
           this.nominalFormat=getNominalFormat();
               //--------------------------------------------------------------------------
            String dat="";
            dat+="var lineChartData = {\n";
            dat+="\t\tlabels : ["+labels+"],\n";
            dat+="\t\tdatasets : [\n";
            dat+="\t\t	{\n";
            dat+="\t\t\t label: 'My First dataset',\n";
            dat+="\t\t\t fillColor : 'rgba(35,5,35,5)',\n";
            dat+="\t\t\t strokeColor : 'rgba(25,5,25,25)',\n";
            dat+="\t\t\tpointColor : 'rgba(45,45,45,45)',\n";
            dat+="\t\t\tpointStrokeColor : '#f23',\n";
            dat+="\t\t\tpointHighlightFill : '#f23',\n";
            dat+="\t\t\tpointHighlightStroke : 'rgba(45,45,45,45)',\n";
            dat+="\t\t\tdata : ["+datas+"]\n";
            dat+="\t\t			}\n";
            dat+="\t\t\t\n]";
            dat+="\t\t\t}\n";
            this.numericFormat=dat;
            //----------------------------------------------------------
        }
      }catch(Exception err)
      {
          System.out.print(err.toString());
      }
    }
    public String getHtmlResultsTable() {
        return htmlResultsTable;
    }
    
    private void setHtmlResultsTable(String valueHtml,String quantityHtml, int index)
    {
        String s="";
       if(index%2==0)
          {
                    s="<tr><td BGCOLOR='#81BEF7'>"+valueHtml+"</td><td BGCOLOR='#81BEF7'>"+quantityHtml+"</td></tr></table>";
          }
          else
          {
                    s="<tr><td BGCOLOR='#abd0bd'>"+valueHtml+"</td><td BGCOLOR='#abd0bd'>"+quantityHtml+"</td></tr></table>";
          }
        
        htmlResultsTable=htmlResultsTable.substring(0,htmlResultsTable.length()-8);
        htmlResultsTable+=s;
    }
    private void setNominalFormat(String val,double quantity)
    {
        String s="{'label':'"+val+"','values':['"+quantity+"']},]};return json;};";
        nominalFormat=nominalFormat.substring(0,nominalFormat.length()-18);
       nominalFormat+=s+"\n";
    }

    public String getNominalFormat() {
        
        return nominalFormat;
    }
    public String getNumericFormat() {
        return numericFormat;
    }
    /*
    public static void main(String arg[])
    {
        try
        {   
            //InstancesFile insF= new InstancesFile("D:/Ficheros/ingles_31_variables.arff");
            InstancesFile insF= new InstancesFile("D:/Ficheros/Drug1n.arff");
            if(insF.processingData()==true)
            {
                StatisticalValues sv = new StatisticalValues(insF.getData(),"Drug");
                sv.calculateStatisticalValues();
                System.out.println(sv.getNominalFormat()+"\n\n\n");
                System.out.println(sv.getNumericFormat()+"\n\n\n");
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
        }
    } */
}
