/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QualityData.Data;

import File.Upload.InstancesFile;
import java.util.Enumeration;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author Carlitos
 */
public class ViewInstance {
    private Instances data;  
    private int indexInstance;
    private String exceptions;
    private String tableInstance;

    public ViewInstance(Instances data, int indexInstance) {
        this.data = data;
        this.indexInstance = indexInstance;
        this.exceptions = "";
        this.tableInstance = "";
    }

    
     public boolean proccessInstances()
    {
        String html="";
        try
        {
            html="<table border='1'>";
            Instance ins = data.instance(this.indexInstance);
            Enumeration<Attribute> eat = data.enumerateAttributes();
            String val="";
            int j=0;
            while(eat.hasMoreElements()==true)
            {
                Attribute attr = eat.nextElement();
              if(attr.isNominal()==true)
               {
                 val = ins.stringValue(attr.index());
               }
               else
               {
                 val = ins.value(attr.index())+"";
               }
               if(j%2==0)
               {
                 html+="<tr BGCOlOR='#81BEF7'>";
                 html+="<th>"+attr.name()+"</th><td>"+val+"</td>";
                 html+="</tr>";
               }
               else
               {
                 html+="<tr BGCOlOR='#abd0bd'>";
                 html+="<th>"+attr.name()+"</th><td>"+val+"</td>";
                 html+="</tr>";
               }
               j++;
            }
            html+="</table>";
            this.tableInstance=html;
            return true;
        }
        catch(Exception err)
        {
            this.exceptions=err.getMessage();
            return false;
        }
    }   
     
    public String getTableInstance() {
        return tableInstance;
    }
    public String getExceptions() {
        return exceptions;
    }
    /*
    public static void main(String[] arg)
    {
        InstancesFile inf = new InstancesFile("D:/Ficheros/Drug1n.arff");
        if(inf.processingData() == true)
        {
            ViewInstance vi = new ViewInstance(inf.getData(), 4);
            if(vi.proccessInstances() == true)
            {
                System.out.println(vi.getTableInstance());
            }      
            
        }
    }*/
}
