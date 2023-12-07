/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataMining.Clustering;
import File.Upload.InstancesFile;
import weka.clusterers.SimpleKMeans;
import weka.core.Instance;
import weka.core.Instances;
/**
 *
 * @author Carlitos
 */
public class KMean {
    private Instances data;
    private SimpleKMeans kMeans;
    private Instances centroids;
    private String exceptions;

    private int numClusters;
    public KMean(int numClusters,Instances data) {
        this.data=data;
        centroids=null;
        kMeans=new SimpleKMeans();
        exceptions="";
        this.numClusters=numClusters;
    } 
    public boolean processKmeans()
    {
        try
        {
            kMeans = new SimpleKMeans();
            kMeans.setNumClusters(numClusters);
            kMeans.buildClusterer(data);
            centroids = kMeans.getClusterCentroids();
            return true;
        }catch(Exception error)
        {
            exceptions=error.toString();
            return false;
        }     
    } 
    public Instances getCentroides()
    {            
        return centroids;
    }
    public SimpleKMeans  getKmeans()
    {
        return kMeans;
    } 
    public String getExceptions() {
        return exceptions;
    }
    /*
    public static void main(String arg[]) throws Exception 
    {
        InstancesFile inf = new InstancesFile("D:/Ficheros/ingles_31_variables.arff"); 
        inf.processingData();
        KMean km= new KMean(6, inf.getData());
        if(km.processKmeans()==true)
        {
            Instance insCentroide0 = km.getKmeans().getClusterCentroids().instance(0);
            Instance insCentroide1 = km.getKmeans().getClusterCentroids().instance(1);
            Instance insCentroide2 = km.getKmeans().getClusterCentroids().instance(2);
            Instance insCentroide3 = km.getKmeans().getClusterCentroids().instance(3);
            Instance insCentroide4 = km.getKmeans().getClusterCentroids().instance(4);
            Instance insCentroide5 = km.getKmeans().getClusterCentroids().instance(5);
            System.out.println(""+km.getKmeans().getDistanceFunction().distance(null,insCentroide1)+"\n");
            System.out.println(""+km.getKmeans().getDistanceFunction().distance(insCentroide0,insCentroide2)+"\n");
            System.out.println(""+km.getKmeans().getDistanceFunction().distance(insCentroide0,insCentroide3)+"\n");
            System.out.println(""+km.getKmeans().getDistanceFunction().distance(insCentroide0,insCentroide4)+"\n");
            System.out.println(""+km.getKmeans().getDistanceFunction().distance(insCentroide0,insCentroide5)+"\n");
        }
        for(int j=0;j<km.getCentroides().numInstances();j++)
        {
            System.out.println(km.getCentroides().instance(j)+"\n");
        }
        
    } */  
}
