/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataMining.Tree;

/**
 *
 * @author carlitos
 */
public class Node {
   private String name;
   private String label;
   private String link;

    public Node(String name,String label,String link)
    {
        this.name=name;
        this.label=label;
        this.link=link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLabel() {
        return label;
    }

    public String getLink() {
        return link;
    }

    public String getName() {
        return name;
    }
}
