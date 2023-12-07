/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataMining.Clustering;

import java.util.Enumeration;
import weka.core.Attribute;
import weka.core.Instance;

/**
 *
 * @author Carlitos
 */
public class TrCentroide extends Thread {
    private Instance centroide;
    private String html;
    private int index;
    public TrCentroide(Instance centroide,int index) {
        this.centroide=centroide;
        this.html="";
        this.index=index;
    }
     @Override
     public void run()
     {
         processCentroide();
     }
    private void processCentroide(){
        Enumeration<Attribute> ec= centroide.enumerateAttributes();
        html+="<tr>";
        html+="<td>Grupo"+index+"</td>";
        String val="";
        while(ec.hasMoreElements()==true)
        {             
             Attribute attr = ec.nextElement();
              if(attr.isNominal()==true)
               {
                 val = centroide.stringValue(attr.index());
               }
               else
               {
                 val = centroide.value(attr.index())+"";
               }
               html+="<td>"+val+"</td>"; 
        }
        html+="</tr>";
    }
    
    public String getHtml() {
        return html;
    }
}
