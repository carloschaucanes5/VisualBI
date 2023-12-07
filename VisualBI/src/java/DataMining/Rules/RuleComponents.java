package DataMining.Rules;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Carlitos
 */
public class RuleComponents {
   private String beforeComponent;
   private String afterComponent;
   private double confidence;
   
   public RuleComponents(String beforeComponent,String afterComponent,double confidence)
   {
       this.beforeComponent=beforeComponent;
       this.afterComponent=afterComponent;
       this.confidence=confidence;
   }
       public String getAfterComponent() {
        return afterComponent;
    }

    public String getBeforeComponent() {
        return beforeComponent;
    }

    public double getConfidence() {
        return confidence;
    }
   
}
