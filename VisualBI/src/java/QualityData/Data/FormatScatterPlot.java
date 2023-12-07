/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QualityData.Data;

import java.util.Enumeration;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author Carlitos
 */
public class FormatScatterPlot {
    public Instances data;
    public String rutaArchivo;
    private String formatScatterPlot;
    private String axes;

 
    public FormatScatterPlot(Instances data1,String rutaArchivo1)
    {
        this.data=data1;
        this.rutaArchivo=rutaArchivo1;
        this.formatScatterPlot = "";
        this.axes="";
    }


public void buildFormatScatterPlot(int index1, int index2, int index3, int top, int bottom)
{    
     int limitTop=top,limitBottom=bottom;
     int k = limitBottom;
     String format = ""; 
     if(index1 == -1 && index2 != -1 && index3 != -2)
     {
           format="index,"+data.attribute(index2).name()+","+data.attribute(index3).name()+"\n";
           this.axes = "index,"+data.attribute(index2).name()+","+data.attribute(index3).name();
           while(k >= limitBottom && k<limitTop)
            {
                Instance ins = data.instance(k);
                format+=""+k+",";
                format+=""+ins.value(index2)+",";
                format+=""+ins.value(index3)+"\n";
                k++;
            }
     }
     
     if(index1 != -1 && index2 == -1 && index3 != -2)
     {
           format=data.attribute(index1).name()+",index,"+data.attribute(index3).name()+"\n";
           this.axes = data.attribute(index1).name()+",index,"+data.attribute(index3).name() ;
           while(k >= limitBottom && k<limitTop)
            {
                Instance ins = data.instance(k);
                format+=""+ins.value(index1)+",";
                format+=""+k+",";
                format+=""+ins.value(index3)+"\n";
                k++;
            }
     }

     if(index1 != -1 && index2 != -1 && index3 != -2)
     {
           format="index,"+data.attribute(index1).name()+","+data.attribute(index2).name()+","+data.attribute(index3).name()+"\n";
           this.axes = data.attribute(index1).name()+","+data.attribute(index2).name()+","+data.attribute(index3).name();
           while(k >= limitBottom && k<limitTop)
            {
                Instance ins = data.instance(k);
                format+=""+k+",";
                format+=""+ins.value(index1)+",";
                format+=""+ins.value(index2)+",";
                format+=""+ins.value(index3)+"\n";
                k++;
            }
     }

     if(index1 != -1 && index2 != -1 && index3 == -2)
     {
           format="index,"+data.attribute(index1).name()+","+data.attribute(index2).name()+"\n";
           this.axes = data.attribute(index1).name()+","+data.attribute(index2).name()+"";
           while(k >= limitBottom && k<limitTop)
            {
                Instance ins = data.instance(k);
                format+=""+k+",";
                format+=""+ins.value(index1)+",";
                format+=""+ins.value(index2)+"\n";
                k++;
            }
     }
     
    if(index1 == -1 && index2 != -1 && index3 == -2)
     {
           format="index,"+data.attribute(index2).name()+"\n";
           this.axes = "index,"+data.attribute(index2).name()+"";
           while(k >= limitBottom && k<limitTop)
            {
                Instance ins = data.instance(k);
                format+=""+k+",";
                format+=""+ins.value(index2)+"\n";
                k++;
            }
     }

    if(index1 != -1 && index2 == -1 && index3 == -2)
     {
           format=data.attribute(index1).name()+",index\n";
           this.axes = data.attribute(index1).name()+",index";
           while(k >= limitBottom && k<limitTop)
            {
                Instance ins = data.instance(k);
                format+=""+ins.value(index1)+",";
                format+=""+k+"\n";
                k++;
            }
     }
     this.formatScatterPlot = format;
}
    
private String getValue(Instance ins, Attribute attr)
{
    String val = "";
    if(attr.isNominal() == true)
     {
        val = getCodValueNominal(attr,ins.stringValue(attr.index()));
     }
     else
     {
        if(attr.isNumeric() == true)
         {
             val =""+ins.value(attr.index());
         }
     }
    return val;
}

private String getCodValueNominal(Attribute attr, String valueAttribute)
{

    Enumeration<String> eValues = attr.enumerateValues();
    int b = 0;
    int j=0;
    String found = "";
    while(j< attr.numValues() && b==0)
     {
       if(valueAttribute.trim().compareTo(attr.value(j).trim()) ==0 )
        {
          b=1;
          found =""+j;
        }
       j++;
     }
    return found;
}

    public String getFormatScatterPlot() {
        return formatScatterPlot;
    }
    
    public String getAxes() 
    {
        
        String[] vecAxes = this.axes.trim().split(",");
        String functions = "";
        if(vecAxes.length == 3)
        {
            functions  = "function axisX(){\n";
            functions += "\treturn '"+vecAxes[0]+"';\n";
            functions += "}\n";
            functions += "function axisY(){\n";
            functions += "\treturn '"+vecAxes[1]+"';\n";
            functions += "}\n";
            functions += "function axisZ(){\n";
            functions += "\treturn '"+vecAxes[2]+"';\n";
            functions += "}\n";
        }
        if(vecAxes.length == 2)
        {
            functions  = "function axisX(){\n";
            functions += "\treturn '"+vecAxes[0]+"';\n";
            functions += "}\n";
            functions += "function axisY(){\n";
            functions += "\treturn '"+vecAxes[1]+"';\n";
            functions += "}\n";
            functions += "function axisZ(){\n";
            functions += "\treturn '';\n";
            functions += "}\n";
        }
        
        return functions;
    }
    /*
    public static void main(String[] arg)
    {
       
           //Informacion info= new Informacion("D:/Ficheros/Drug1n.arff");
           Informacion info= new Informacion("D:/Ficheros/Drug1n.arff");
            if(info.procesarDatos()==true)
            {
                FormatScatterPlot fd=new FormatScatterPlot(info.instancias(),"D:/tonces.csv");
                fd.buildFormatScatterPlot(0, 3, 4);
                System.out.println(fd.getFormatScatterPlot());
                //System.out.println(fd.construirFormatoSP());
            }
    }*/
}
