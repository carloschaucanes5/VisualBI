/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QualityData.Data;

import java.util.Enumeration;
import weka.core.Attribute;
import weka.core.Instances;

/**
 *
 * @author Carlitos
 */
public class ValuesAttribute {
    private int indexAtribute;
    private Instances Data;
    private String exceptions;
    private String valuesCodes;
    private int position;
public ValuesAttribute(Instances Data,int indexAtribute, int position) {
        this.indexAtribute = indexAtribute;
        this.Data = Data;
        this.exceptions = "";
        this.valuesCodes = "";
        this.position = position;
    }
    
    public boolean processValues()
    {
        try
        {
            if(indexAtribute != -1)
            {
               if(indexAtribute!=-2)
               {
                   Attribute attr = this.Data.attribute(indexAtribute);
                   String codes = "";
                    if(attr.isNominal())
                    {
                        Enumeration<String> eva = attr.enumerateValues();
                        if(this.position == 2)
                        {  
                            codes ="<center><b>"+attr.name()+"</b></center><br/>";
                            codes += "<table>";
                            double i = 0;
                            while(eva.hasMoreElements() == true)
                            {         
                                String val = eva.nextElement();
                                codes+="<tr><th>"+i+"</th><td>"+val+"</td></tr>";
                                i++;
                            }
                            codes += "</table>";
                        }
                       if(this.position == 1)
                        {  
                            double i = 0;
                            codes  = "<center><b>"+attr.name()+"</b></center><br/>";
                            codes +="<table>";
                            codes +="<tr>";
                            while(eva.hasMoreElements() == true)
                            {         
                                String val = eva.nextElement();
                                codes+="<th>"+i+"</th><td>"+val+"</td>";
                                i++;
                            }
                            codes+="</tr>";
                            codes += "</table>";
                        } 
                        this.valuesCodes = codes;
                    }
                    else
                    {
                        this.valuesCodes = codes;
                    }

               }
               else
               {
                   this.valuesCodes = "";
               }
            }
            else
            {
               this.valuesCodes = ""; 
            }
                return true;
        }
        catch(Exception err)
        {
            this.exceptions = err.toString();
            return false;
        }
                
    }
    
    public String getExceptions() {
        return exceptions;
    }

    public String getValuesCodes() {
        return valuesCodes;
    }


    
}
