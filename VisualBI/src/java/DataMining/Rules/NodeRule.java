package DataMining.Rules;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
;

/**
 *
 * @author carlitos
 */
public class NodeRule {
    private int id;
    private String label;
    private String group;
    private String color;

    public NodeRule(int id, String label, String group, String color) {
        this.id=id;
        this.label=label;
        this.group=group;
        this.color =color;
    }
    public int getId()
    {
        return id;
    }
    public String getLabel()
    {
        return label;
    }
    
    public String getGroup()
    {
        return group;
    }
    
    public String getColor()
    {
        return color;
    }
}
