/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataMining.Tree;

/**
 *
 * @author Carlitos
 */
public class Relation {
   private String name;
   private String label;

    public Relation(String name,String label)
    {
        this.name=name;
        this.label=label;
    } 
    public String getLabel() {
        return label;
    }

    public String getName() {
        return name;
    }
    
}