/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QualityData.Attribute;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ListIterator;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author Carlitos
 */
public class FrequencyDistribution {
    private double minimum;
    private double maximum;
    private List<Double> listData;
    private Instances data;
    private int indexAttribute;
    public List<FrequencyRank> listFrequency; 
    public String numericFormat;
    public String nominalFormat;
    public String htmlFormat;
    public FrequencyDistribution(double minimum, double maximum,Instances data,int indexAttribute /*List<Double> listData*/) {
        this.minimum = minimum;
        this.maximum = maximum;
        this.data=data;
        this.indexAttribute=indexAttribute;
        this.listFrequency= new ArrayList<FrequencyRank>();
        this.numericFormat="";
        this.nominalFormat="function funJson1(){var json={'label':['"+data.attribute(indexAttribute).name() +"'],'values':[ ]};return json;};";
        this.htmlFormat = "";
    }

    public boolean applyDistributionNormal()
    {
        try
        {
          //------------------------------------------------------------------
          //double numeroRangos=1+(3.322*Math.log10(listData.size()));
          double numeroRangos=1+(3.322*Math.log10(data.numInstances()));
          numeroRangos=Math.rint(numeroRangos*1)/1;
          double rango=maximum-minimum;
          double amplitud=rango/numeroRangos;
          //-----------------------------------------------------------
          String cadRangos="";
          double acumulado=minimum+amplitud;
          double minimo=minimum;
          cadRangos+=minimo+"-"+acumulado;  
          FrequencyRank fr=new FrequencyRank(acumulado,minimo,0);
          listFrequency.add(fr);
          for(int i=1;i<numeroRangos;i++)
            {
                minimo=acumulado;
                acumulado=minimo+amplitud;
                fr = new FrequencyRank(acumulado,minimo,0);
                listFrequency.add(fr);
                cadRangos+="\n,"+minimo+"-"+acumulado;  
            }
            //----------------------------------------------------------
             String ins="";
             Enumeration ins1=data.enumerateInstances(); 
             //ListIterator<Double> liData = listData.listIterator();
             while(ins1.hasMoreElements()==true)
              //while(liData.hasNext()==true)
              {
                  Instance in = (Instance) ins1.nextElement();
                  ins=in.toString();
                  double valData = in.value(indexAttribute);;
                  ListIterator<FrequencyRank> liFrequency = listFrequency.listIterator();
                    while(liFrequency.hasNext()==true)
                    {
                        FrequencyRank fra = liFrequency.next();
                        if(valData>=fra.getLimInferior() && valData<fra.getLimSuperior())
                        {
                            int conAcu=fra.getFrecuencia();
                            fra.actualizarFrecuencia(conAcu+1);         
                        }
                    }
              }
            //----------------------------------------------------------
            String labels="",datas="",html="",label="";int frequency = 0; 
            ListIterator<FrequencyRank> liFrequency = listFrequency.listIterator();
            html+="<table>";
            int j=0;
            while(liFrequency.hasNext()==true)
            {
                FrequencyRank fra = liFrequency.next();
                label = ""+(float)fra.getLimInferior()+" <=X < "+(float)fra.getLimSuperior();
                frequency = fra.getFrecuencia();
                if(j%2==0)
                {
                    html+="<tr><th BGCOLOR='#81BEF7'>"+label+"</th><td BGCOLOR='#81BEF7'>"+frequency+"</td></tr>";
                }
                else
                {
                    html+="<tr><th BGCOLOR='#abd0bd'>"+label+"</th><td BGCOLOR='#abd0bd'>"+frequency+"</td></tr>";
                }
                labels+="'"+label+"',";
                double valuePorcentaje = (frequency*100)/(data.numInstances()-1);
                setNominalFormat(label,valuePorcentaje);
                datas+=frequency+",";
                j++;
            }
            html+="</table>";
            this.htmlFormat = html;
            labels = labels.substring(0,labels.length()-1);
            datas = datas.substring(0,datas.length()-1);
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
            return true;
        }
        catch(Exception err)
        {
            return false;
        }
    }
    
    private void setNominalFormat(String val,double quantity)
    {
        String s="{'label':'"+val+"','values':['"+quantity+"']},]};return json;};";
        nominalFormat=nominalFormat.substring(0,nominalFormat.length()-18);
       nominalFormat+=s+"\n";
    }
    
    public String getFormatNumeric() {
        return numericFormat;
    }
    
    public String getFormatNominal()
    {
        return nominalFormat;
    }
    public String getHtmlFormat() {
        return htmlFormat;
    }
}
