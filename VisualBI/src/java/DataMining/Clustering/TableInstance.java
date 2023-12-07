/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataMining.Clustering;


import java.util.Enumeration;
import weka.clusterers.SimpleKMeans;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author Carlitos
 */
public class TableInstance {
    private Instances data;
    private String table;
    private SimpleKMeans kMeans;
    private int indexInstance;
    private String exceptions;


    public TableInstance(Instances data,int indexInstance, SimpleKMeans kMeans) {
        this.data = data;
        this.table = "";
        this.indexInstance=indexInstance;
        this.exceptions="";
        this.kMeans = kMeans;
    }
    public boolean proccessTable()
    {
        //int num = (kMeans.clusterInstance(ins));
        //double distanceIns = kMeans.getDistanceFunction().distance(kMeans.getClusterCentroids().instance(indexCen), data.instance(indexIns));
        String html="";
        int con = 0;
        try
        {
            html="<table border='1'>";
            int indexCentroide = kMeans.clusterInstance(data.instance(indexInstance));
            //------------------------------------------------------------------
            Instance centroide = kMeans.getClusterCentroids().instance(indexCentroide);
            Instance ins = data.instance(indexInstance);
            //------------------------------------------------------------------
            Enumeration<Attribute> eat = data.enumerateAttributes();
            String valInstance="";
            String valCentroide="";
            int j=0;
            html+="<th>Atributos</th><th>Instancia "+indexInstance+"</th><th>Grupo "+indexCentroide+"</th>";
            while(eat.hasMoreElements()==true)
            {
                Attribute attr = eat.nextElement();
              if(attr.isNominal()==true)
               {
                 valInstance = ins.stringValue(attr.index());
                 valCentroide = centroide.stringValue(attr.index());
               }
               else
               {
                 valInstance = ins.value(attr.index())+"";
                 valCentroide = centroide.value(attr.index())+"";
               }
               if(j%2==0)
               {
                 html+="<tr BGCOlOR='#81BEF7'>";
                 html+="<th>"+attr.name()+"</th><td>"+valInstance+"</td><td>"+valCentroide+"</td>";
                 html+="</tr>";
               }
               else
               {
                 html+="<tr BGCOlOR='#abd0bd'>";
                 html+="<th>"+attr.name()+"</th><td>"+valInstance+"</td><td>"+valCentroide+"</td>";
                 html+="</tr>";
               }
               j++;
                if(valInstance.trim().compareTo(valCentroide.trim())==0)
                {
                    con++;
                }
            }
            double valConfidence = (100*con)/centroide.numAttributes();
            //100%/x = 7/3
            html+="<th>Factor de Confianza</th><td>"+valConfidence+"%</td>";
            html+="</table>";
            this.table=html;
            return true;
        }
        catch(Exception err)
        {
            this.exceptions=err.getMessage();
            return false;
        }
    }
        public String getExceptions() {
        return exceptions;
    }
        
   public boolean proccessTableCentroide()
    {
        String html="";
        try
        {
            html="<table border='1'>";
            Instance ins = data.instance(indexInstance);
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
            this.table=html;
            return true;
        }
        catch(Exception err)
        {
            this.exceptions=err.getMessage();
            return false;
        }
    }   
        
        
     public String getTable() {
        return table;
    }
     private Double getConfidenceFactor()
     {
         try
         {
            int indexCentroide = kMeans.clusterInstance(data.instance(indexInstance));
            Instance centroide = kMeans.getClusterCentroids().instance(indexCentroide);
            for(int j=0;j<centroide.numAttributes();j++)
            {
                
            }
         }
         catch(Exception err)
         {
         }
         return 1.0;
     }
     
}
