package DataMining.Rules;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import File.Upload.InstancesFile;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import weka.associations.Apriori;
import weka.associations.AprioriItemSet;
import weka.associations.ItemSet;
import weka.core.FastVector;
import weka.core.Instances;
import weka.core.Utils;
/*
 *
 * @author Carlitos
 */
public class ClassApriori {
    private double deltaValue;
    private double lowerBoundMinSupport; 
    private double minMetric;
    private int numRulesValue; 
    private  double upperBoundMinSupport; 
    private Apriori apriori;
    private String excepciones;
    private Instances data;
    private List<RuleComponents> listRules;
    public ClassApriori(Instances data ,int numeroReglas,double minMetric,double lowerBoundMinSupport,double upperBoundMinSupport) 
    {
         this.deltaValue = 0.05; 
         this.lowerBoundMinSupport = lowerBoundMinSupport; 
         this.minMetric = minMetric; 
         this.numRulesValue = numeroReglas; 
         this.upperBoundMinSupport = upperBoundMinSupport; 
         this.apriori = new Apriori();
         this.excepciones="";
         this.data=data;
         this.listRules=new ArrayList<RuleComponents>();
    }
   public boolean  buildRules()
    {
        try 
        {
            data.setClassIndex(data.numAttributes() - 1);
            apriori.setDelta(deltaValue);
            apriori.setLowerBoundMinSupport(lowerBoundMinSupport); 
            apriori.setNumRules(numRulesValue); 
            apriori.setUpperBoundMinSupport(upperBoundMinSupport); 
            apriori.setMinMetric(minMetric);
            apriori.buildAssociations(data); 
            return true;
                 
        } 
        catch ( Exception e ) 
        { 
            this.excepciones=e.toString();
            return false; 
        }
    }

   public void listdRules()
   {
       FastVector[] m_allTheRules = apriori.getAllTheRules();
       //-----------------------------------------------------------------------
      for (int i = 0; i < m_allTheRules[0].size(); i++)
      {
          String antecedente = "" + ((ItemSet)m_allTheRules[0].elementAt(i)).toString(data);
          String consecuente = "" + ((ItemSet)m_allTheRules[1].elementAt(i)).toString(data);
          String confianza =    Utils.doubleToString(((Double)m_allTheRules[2].elementAt(i)).doubleValue(),2);

          System.out.println("Regla"+(i+1)+"\t");
          System.out.println("Antececente"+antecedente+"\t");
          System.out.println("Consecuente"+consecuente+"\t");
          System.out.println("Confianza"+confianza+"\n");
          
        /*text.append(
                        Utils.doubleToString((double)i+1,
                        (int)(Math.log(m_numRules)/Math.log(10)+1),0)+  
                        ". " + ((ItemSet)m_allTheRules[0].elementAt(i)).  
                        toString(m_instances)   
                        + " ==> " + ((ItemSet)m_allTheRules[1].elementAt(i)).  
                        toString(m_instances) +"    conf:("+    
                        Utils.doubleToString(((Double)m_allTheRules[2].  
                        elementAt(i)).doubleValue(),2)+")"
                    );  
        if (m_metricType != CONFIDENCE || m_significanceLevel != -1) 
        {  
           text.append(
                        (m_metricType == LIFT ? " <" : "")+" lift:("+    
                        Utils.doubleToString(((Double)m_allTheRules[3].  
                        elementAt(i)).doubleValue(),2)  
                        +")"+(m_metricType == LIFT ? ">" : "")
                       );  
           text.append(
                        (m_metricType == LEVERAGE ? " <" : "")+" lev:("+    
                        Utils.doubleToString(((Double)m_allTheRules[4].  
                        elementAt(i)).doubleValue(),2)+")"
                      );  
           text.append(
                        " ["+  
                        (int)(((Double)m_allTheRules[4].elementAt(i))  
                        .doubleValue() * (double)m_instances.numInstances())  
                        +"]"+(m_metricType == LEVERAGE ? ">" : "")
                       );
           
            text.append(
                         (m_metricType == CONVICTION ? " <" : "")+" conv:("+    
                          Utils.doubleToString(((Double)m_allTheRules[5].  
                          elementAt(i)).doubleValue(),2)  
                          +")"+(m_metricType == CONVICTION ? ">" : "")
                       );  
        }  
        text.append('\n');  */
          //System.out.print("\n");
    } 
      //System.out.println(text1.toString());
       //-----------------------------------------------------------------------
   }
   
    public Apriori getApriori() {
        return apriori;
    }
   
    public String getExcepciones() {
        return excepciones;
    }
    
    public String getHtmlTableRules()
    {
        
        //--------------------------------------------------------------------
        String html="<div>";
        html+="<table class='table table-striped table-hover table-bordered' width='30' >";
        html+="<thead><tr>";
        html+="<th>Ver</th>";
        html+="<th>Reglas</th>";
        html+="<th>Factor de confianza</th>";
        html+="</tr></thead>";	
        html+="<tbody>\n";
        FastVector[] m_allTheRules = apriori.getAllTheRules();
       //-----------------------------------------------------------------------
      for (int i = 0; i < m_allTheRules[0].size(); i++)
      {
          String confianza =    Utils.doubleToString(((Double)m_allTheRules[2].elementAt(i)).doubleValue(),3);
          html+="<tr>";
          html+="<td><input type='checkbox' name = 'rules' value='"+i+"'/></td>";
          html+="<td>Regla"+(i)+"</td>";
          html+="<td>"+confianza+"</td>";
          html+="</tr>";
      } 
                
        html+="</tbody>";
        html+="</table>";
        html+="</div>";
        return html;
    }
    public static void main(String[] arg)
    {
       InstancesFile inf = new InstancesFile("D:/Ficheros/titanic.arff");
       //InstancesFile inf = new InstancesFile("D:/Ficheros/ingles_31_variables.arff");      
       if(inf.processingData() == true)
       {
           ClassApriori ca = new ClassApriori(inf.getData(), 10, 0.9, 0.1, 1.0);
           if(ca.buildRules()==true)
           {
              
               
               ca.listdRules(); 
                 
               /*String a = "2,3";
               String[] vec = a.split(",");
               ProcessRules pr = new ProcessRules(ca.getApriori(), vec);
               if(pr.processFormat()==true)
               {
                   System.out.println(pr.buildFormatVisJS());
               }*/
           }
           else
           {
               System.out.println(ca.getExcepciones());
           }
       }
       else
       {
           System.out.print(inf.getException());
       }
    }    
}
 