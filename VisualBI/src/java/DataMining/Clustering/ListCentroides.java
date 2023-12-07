/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataMining.Clustering;


import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ListIterator;
import weka.clusterers.SimpleKMeans;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author Carlitos
 */
public class ListCentroides {
    private SimpleKMeans kMeans;
    private Instances data;
    private List<IndexesCentroide> listIndexCen;
    private String exceptions;
    private int limitBottom;
    private int limitTop;
    public ListCentroides(SimpleKMeans kMeans, Instances data, int limitBottom, int limitTop) {
        this.kMeans = kMeans;
        this.data = data;
        this.listIndexCen = new ArrayList<IndexesCentroide>();
        this.exceptions = "";
        this.limitBottom = limitBottom;
        this.limitTop = limitTop;
    }
    public boolean processLists()
    {
        try
        {
            //Create ListCentroides------------------------------------
            int  numCentroides = kMeans.getClusterCentroids().numInstances();
            for(int j=0;j<numCentroides;j++)
            { 
               IndexesCentroide ic = new IndexesCentroide(j);
               listIndexCen.add(ic);
            }
            //---------------------------------------------------------
            Enumeration<Instance> dataIns = data.enumerateInstances();
            int i=0;
            int k=this.limitBottom;
            while(k<this.limitTop)
            { 
                Instance ins = data.instance(k);
                int num = (kMeans.clusterInstance(ins));
                ListIterator<IndexesCentroide> liListIndexCen = listIndexCen.listIterator();
                while(liListIndexCen.hasNext() == true)
                {
                    IndexesCentroide inc = liListIndexCen.next();
                    if(inc.getIndexCentroide() == num)
                    {
                        inc.addListIndexes(k);
                    }
                }
                i++;
                k++;
            }
            //int num = (kmean.clusterInstance(data.instance(index)));
            //---------------------------------------------------------
            
            return true;
        }
        catch(Exception error)
        {
            this.exceptions = error.toString();
            return false;
        }
    }
    public List<IndexesCentroide> getListIndexCen() {
        return listIndexCen;
    }
    public String getExceptions() {
        return exceptions;
    }
    /*
    public static void main(String arg[])
    {
        
         InstancesFile inf= new InstancesFile("D:/Ficheros/Drug1n.arff");
         //InstancesFile inf= new InstancesFile("D:/Ficheros/Drug1n.arff");
         if(inf.processingData()==true){
            KMean km = new KMean(5, inf.getData());
            if(km.processKmeans()== true)
            {
                ListCentroides lisCes = new ListCentroides(km.getKmeans(), inf.getData(),100,200);
                if(lisCes.processLists() ==true)
                {
                    
                    BuildFormatJsonCluster bfj = new BuildFormatJsonCluster(lisCes.getListIndexCen(),inf.getData(),km.getKmeans() , 20);
                    if(bfj.buildFormat() == true)
                    {
                        System.out.println(bfj.getFormat());
                        //WriteFile wf = new WriteFile("D:/cluster.json", bfj.getFormat());
                        //wf.write();
                        //System.out.println("listo compadrito");
                    }
                  /* 
                    ListIterator<IndexesCentroide> liIndexesCentroide = lisCes.getListIndexCen().listIterator();
                    while(liIndexesCentroide.hasNext() == true)
                    {
                        IndexesCentroide isCe = liIndexesCentroide.next();
                        System.out.println("indexCentroide=>"+isCe.getIndexCentroide()+"\n");
                        ListIterator<Integer> lIndexes = isCe.getListIndexInstances().listIterator();
                        while(lIndexes.hasNext() == true)
                        {
                            System.out.println("\t IndexInstance=>"+lIndexes.next()+"\n");
                        }
                            //int numeros[] = {1,-1};
                            //int x = (int)(Math.random()*numeros.length+0);
                    }
                }
            }
         }
    }*/
}
