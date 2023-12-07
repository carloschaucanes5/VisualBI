/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataMining.Clustering;

import java.util.List;
import java.util.ListIterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;

/**
 *
 * @author Carlitos
 */
public class BuildFormatJsonCluster {
    private static SimpleKMeans kmeans;
    private List<IndexesCentroide> listIndexCen;
    private Instances data;
    private SimpleKMeans kMeans;
    private int numInstances;
    private String format;
    private String exceptions;
    public BuildFormatJsonCluster(List<IndexesCentroide> listIndexCen, Instances data, SimpleKMeans kMeans, int numInstances) {
        this.listIndexCen = listIndexCen;
        this.data = data;
        this.kMeans = kMeans;
        this.numInstances = numInstances;
        this.format = "";
        this.exceptions = "";
    }

    public boolean buildFormat()
    {
        try
        {
            //----------------------------------------
            FormatJson fj = new FormatJson();
            JSONObject objF = fj.createObjectNode("c", "", "");
            fj.firstObject(objF);
            JSONArray lisObjsF=new JSONArray();
            ListIterator<IndexesCentroide> liIndexesCentroide = listIndexCen.listIterator();
            while(liIndexesCentroide.hasNext() == true)
             {
                IndexesCentroide isCe = liIndexesCentroide.next();
                //System.out.println("indexCentroide=>"+isCe.getIndexCentroide()+"\n");
                //--------------------
                int indexCen = isCe.getIndexCentroide();
                JSONObject objC = fj.createObjectNode("c"+indexCen, "", "");
                lisObjsF.add(objC);
                JSONArray lisObjsC=new JSONArray();
                //----------------------
                ListIterator<Integer> lIndexes = isCe.getListIndexInstances().listIterator();
                int k=0;
                while(lIndexes.hasNext() == true && k < this.numInstances)
                 {
                   int indexins = lIndexes.next();
                   double distanceIns = kMeans.getDistanceFunction().distance(kMeans.getClusterCentroids().instance(indexCen), data.instance(indexins));
                   JSONObject objIns = fj.createObjectNode(""+indexins, ""+indexCen, ""+distanceIns);
                   lisObjsC.add(objIns);
                   //System.out.println("\t IndexInstance=>"+lIndexes.next()+"\n");                 
                    k++;
                 }
                 fj.connectionNodes(objC, lisObjsC);
             }
            fj.connectionNodes(objF, lisObjsF);
            this.format = fj.getFormatJson();
            //----------------------------------------
            return true;
        }
        catch(Exception error)
        {
            this.exceptions = error.toString();
            return false;
        }    
    }
    public String getFormat() {
        return format;
    }
    
    public String getExceptions() {
        return exceptions;
    }
    /*
    public static void main(String arg[])
    {
        /*InstancesFile inf = new InstancesFile("D:/Ficheros/Drug1n.arff");
        if(inf.processingData()==true)
               {
                  //-----
                 KMean km =  new KMean(4, inf.getData());
                  if(km.processKmeans()==true)
                   {
                        ListCentroides lisCes = new ListCentroides(km.getKmeans(), inf.getData());
                        if(lisCes.processLists() ==true)
                        {
                            BuildFormatJsonCluster bfj = new BuildFormatJsonCluster(lisCes.getListIndexCen(),inf.getData(),km.getKmeans(), 0, 10);
                            if(bfj.buildFormat() == true)
                             {
                                 System.out.println(bfj.getFormat());
                             }}}}
    }*/
}
