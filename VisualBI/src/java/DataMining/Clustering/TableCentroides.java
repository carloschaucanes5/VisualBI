/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataMining.Clustering;

import File.Upload.InstancesFile;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ListIterator;
import weka.clusterers.SimpleKMeans;
import weka.core.Attribute;
import weka.core.Debug.Random;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author Carlitos
 */
public class TableCentroides {
    private Instances data;
    private SimpleKMeans kmean;
    private List<TrCentroide> lisCentroides;
    private String  tableCentroides;
    private String exceptions;
    private int limitData;
    public TableCentroides(Instances data,SimpleKMeans kmean) {
        this.data = data;
        this.kmean=kmean;
        this.lisCentroides= new ArrayList<TrCentroide>();
        this.tableCentroides="";
        this.exceptions="";
    }




    public boolean processTableCentroides()
    {
     try
     {  
        String html="";
        html+="<table class='table table-striped table-hover table-bordered' id='sample_editable_1'>";
        html+="<thead><tr>";
        html+="<th></th>";
        //Attributes-----------------------------------------
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
        //Centroides---------------------------------------------
        Instances insCen = kmean.getClusterCentroids();
        Enumeration<Instance> ec= insCen.enumerateInstances();
        int j=0;
        while(ec.hasMoreElements()==true)
        {
            TrCentroide ce = new TrCentroide(ec.nextElement(), j);
            ce.start();
            lisCentroides.add(ce);
            j++;
        }
        ListIterator<TrCentroide> lic = lisCentroides.listIterator();
        while(lic.hasNext()==true)
        {
            TrCentroide ce = lic.next();
            while(ce.isAlive()==true){}
            html+=ce.getHtml()+"\n";
        }
        //-------------------------------------------------------
        html+="</tbody>";
        html+="</table>";
        this.tableCentroides=html;
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
    
    public String getTableCentroides() {
        return tableCentroides;
    }
    
       public Instance getRowCentroide(int index,int ce)
    {
        try
        {
        
            Instance ins =data.instance(index);
            int num = (kmean.clusterInstance(data.instance(index)));
            if(ce==num)
            {
               return ins; 
            }
            else
            {
                return null;
            }     
        }
        catch(Exception err)
        {
            return null;
        }
    }
    public static void main(String arg[])
    {
        //InstancesFile inf= new InstancesFile("D:/Ficheros/ingles_31_variables.arff");
        InstancesFile inf= new InstancesFile("D:/Ficheros/Drug1n.arff");
        if(inf.processingData()==true){
            KMean km = new KMean(2, inf.getData());
            km.processKmeans();
          
 
            //TableCentroides ce = new TableCentroides(inf.getData(),km.getKmeans());
            //System.out.println(ce.getTableCentroides());
        }
        
    }
}
