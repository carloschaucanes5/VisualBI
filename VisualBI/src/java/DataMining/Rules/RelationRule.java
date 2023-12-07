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
public class RelationRule {
    private int from;
    private int to;
    private String style;
    private String color;
    private int width;
    private int length;
    private String label;
    //style: 'arrow', color: 'red',width: 3, length: 200

    public RelationRule(int from,int to,String style,String color,int width,int length,String label) {
        this.from=from;
        this.to=to;
        this.style=style;
        this.color=color;
        this.width=width;
        this.length=length;
        this.label=label;
    }

    public String getColor() {
        return color;
    }

    public int getFrom() {
        return from;
    }

    public String getLabel() {
        return label;
    }

    public int getLength() {
        return length;
    }

    public String getStyle() {
        return style;
    }

    public int getTo() {
        return to;
    }

    public int getWidth() {
        return width;
    }
}
