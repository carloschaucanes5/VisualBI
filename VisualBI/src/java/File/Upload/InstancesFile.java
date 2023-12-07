/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package File.Upload;



import weka.core.converters.ConverterUtils.DataSource;
 
 import weka.core.Instances;
 import weka.filters.Filter;
 import weka.filters.unsupervised.attribute.Remove;
/**
 *
 * @author Carlitos
 */
public class InstancesFile{
    private String file;
    private Instances data;
    private String exception;

    
    public InstancesFile(String file) {
        this.file=file;
        this.data=null; 
    }   
    public boolean  processingData() 
    {
        try
        {
            data= DataSource.read(file);
            return true;
        }catch(Exception error)
        {
            this.exception=error.toString();
            return false;
        }
    }
    public Instances getData() {
        return data;
    }
    public String getException() {
        return exception;
    }
    
    public Instances prueba()
    {   
        /*AttributeStats st=data.attributeStats(0);
        st.numericStats.calculateDerived();
        Stats st1 = data.attributeStats(0).numericStats;
        String cad="";
        cad= "max=>"+st.numericStats.max+"\nmin=>"+st.numericStats.min+"\n"+"\nmean=>"+st.numericStats.mean+"\nstandard desvition"+st.numericStats.stdDev+"\t";
        //-------------------------------------------------------------------
        String cad="";
        int[] st2 = data.attributeStats(1).nominalCounts;
        for(int i=0;i<st2.length;i++)
        {
            cad+=st2[i]+"\n";
        }
        //-------------------------------------------------------------------
        return cad;*/
        
        try
        {
            String[] options = new String[2];
            options[0] = "-C";                                    // "range"
            options[1] = "2";
            // first attribute
            Remove remove = new Remove();                         // new instance of filter
            remove.setOptions(options);                           // set options
            remove.setInputFormat(data);                          // inform filter about dataset **AFTER** setting options
            Instances newData = Filter.useFilter(data, remove);
            return newData;
        }
        catch(Exception error)
        {
            return null;
        }
    }
    /*
    public static void main(String[] arg)
    {
        InstancesFile ifa = new InstancesFile("D:/Ficheros/Drug1n.arff");
        if(ifa.processingData()==true)
        {
            System.out.print(ifa.prueba());
            /*for(int j=0;j<ifa.prueba().numInstances();j++)
            {
                System.out.println(ifa.prueba().instance(j)+"\n");
            }
        }
    } */ 
}
