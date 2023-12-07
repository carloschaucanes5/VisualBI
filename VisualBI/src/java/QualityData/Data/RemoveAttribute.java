/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QualityData.Data;

import File.Upload.InstancesFile;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

/**
 *
 * @author Carlitos
 */
public class RemoveAttribute {
    private Instances Data;
    private Instances newData;
    private String itemsRemove;
    private String exceptions;
    public RemoveAttribute(Instances Data, String itemsRemove) {
        this.Data = Data;
        this.itemsRemove = itemsRemove;
        this.exceptions = "";
    }

    
    public boolean processRemove()
    {
        try
        {
            String[] options = new String[2];
            options[0] = "-R";                                    // "range"
            options[1] = this.itemsRemove;//+indexAttribute;                                     // first attribute
            Remove remove = new Remove();                         // new instance of filter
            remove.setOptions(options);                           // set options
            remove.setInputFormat(Data);                          // inform filter about dataset **AFTER** setting options
            this.newData = Filter.useFilter(Data, remove); 
            return true;
        }
        catch(Exception err)
        {
            this.exceptions = err.getMessage();
            return false;
        }
    }
      public Instances getNewData() 
      {
         return newData;
      }
      
      public String getExceptions()
      {
        return exceptions;
      }
     /*
      public static void main(String arg[])
      {
          InstancesFile inf = new InstancesFile("D:/Ficheros/Drug1n.arff");
          if(inf.processingData() == true)
          {
              RemoveAttribute ra = new RemoveAttribute(inf.getData(),"");
              if(ra.processRemove() == true)
              {
                  System.out.println(ra.getNewData());
              }
          }
      }*/
    
}
