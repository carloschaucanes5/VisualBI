package DataMining.Rules;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
/**
 *
 * @author carlitos
 */
public class ListsNodesRelations {
    private List<NodeRule> listNodes;
    private List<RelationRule> listRelations;

    public ListsNodesRelations() {
        this.listNodes = new ArrayList<NodeRule>();
        this.listRelations = new ArrayList<RelationRule>();
    }

    public List<NodeRule> getListNodes() {
        return listNodes;
    }

    public void addListNode(NodeRule nodeRule) {
        this.listNodes.add(nodeRule);
    }

    public List<RelationRule> getListRelations() {
        return listRelations;
    }

    public void addListRelation(RelationRule listRelations) {
        this.listRelations.add(listRelations);
    }
  
    public void updateListNodes(int index, NodeRule nodeRule){
        listNodes.set(index, nodeRule);
    }
    
   public void updateListRelation(int index, RelationRule relationRule){
        listRelations.set(index, relationRule);
    }
    
    public NodeRule findListNode(String lebel)
    {
        int b=0;
        NodeRule nodeFound=null;
        ListIterator<NodeRule> liN = listNodes.listIterator();
        while(liN.hasNext() == true && b==0)
        {
            NodeRule nr = liN.next();
            if(nr.getLabel().compareTo(lebel)==0)
            {
                nodeFound = nr;
                b=1;
            }
        }
        return nodeFound;
    }
}
