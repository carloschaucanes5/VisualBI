/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QualityData.Data;

import File.Upload.InstancesFile;
import java.util.Enumeration;
import weka.core.*;
/**
 *
 * @author Carlitos
 */
public class ProcessRankTable{
    private int bottonLimit;
    private int topLimit;
    private Instances data;
    private String html;
    public ProcessRankTable(int bottonLimit, int topLimit, Instances data) {
        this.bottonLimit = bottonLimit;
        this.topLimit = topLimit;
        this.data=data;
        this.html="";
    }
    private String getHeaderHtml()
    {
        String html="";
        html+="<table class='table table-striped table-hover table-bordered' id='sample_editable_1'>";
        html+="<thead><tr>";
        html+="<th>Indice</th>";
        //Row attributes-----------------------------------------
        Enumeration<Attribute> enA = data.enumerateAttributes();
        while(enA.hasMoreElements()==true)
        {
           Attribute attr = enA.nextElement();
           String AttributeName=attr.name();
           html+="<th>"+AttributeName+"</th>"; 
        }
        html+="</tr></thead>";	
        //-------------------------------------------------------
        html+="<tbody>\n";
        return html;
    }
    public void builFormat()
    {
        html+=getHeaderHtml();       
        Enumeration<Attribute> enA = data.enumerateAttributes();
        //Row Instances------------------------------------------
        if(topLimit>data.numInstances())
        {
            topLimit=data.numInstances();
        }
        for(int j=bottonLimit;j<topLimit;j++)
        {
            html+="<tr>";
            Instance ins= data.instance(j); 
            html+="<td>"+ins.index(j) +"</td>";
            Enumeration<Attribute> enI= ins.enumerateAttributes();            
            String val="";
            while(enI.hasMoreElements()==true )
            {
              Attribute attr = enI.nextElement();
              if(attr.isNominal()==true)
               {
                 val = ins.stringValue(attr.index());
               }
               else
               {
                 val = ins.value(attr.index())+"";
               }
                 html+="<td>"+val+"</td>"; 
            }          
             html+="</tr>\n";
        }
        html+="</tbody>";
        html+="</table>";   
    }
   public String getHtml() {
        return html;
    }
   /*
    public static void main(String arg[])
    {
        InstancesFile inf= new InstancesFile("D:/Ficheros/ingles_31_variables.arff");
        if(inf.processingData()==true)
        {
            ProcessRankTable pr= new ProcessRankTable(96943,1000000,inf.getData());
            pr.builFormat();
            System.out.println(pr.getHtml());
        }
    }*/
}
