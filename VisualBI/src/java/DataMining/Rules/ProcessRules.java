package DataMining.Rules;


import java.util.ArrayList;

import java.util.List;
import java.util.ListIterator;
import weka.associations.Apriori;
import weka.associations.ItemSet;
import weka.core.FastVector;
import weka.core.Instances;
import weka.core.Utils;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Carlitos
 */
public class ProcessRules {
   private Apriori apriori;
   private String[] idsRules;
   private List<RuleComponents>listRules;
   private String colorLetraNI;
   private String colorFondoNI;
   private String colorLetraNM;
   private String colorFondoNM;
   private String colorLetraNF;
   private String colorFondoNF;
   private String colorRI;
   private String colorRM;
   private String colorRF;
   private int idNode;
   private ListsNodesRelations lnr;
   private String htmlSelect;
   private String htmlRules;
   private String exceptions;
   private Instances data;
   public ProcessRules(Apriori apriori,Instances data ,String[] idsRules) {
        this.apriori = apriori;
        this.idsRules = idsRules;
        this.listRules = new ArrayList<RuleComponents>();
        this.colorFondoNI="red";
        this.colorLetraNI="black";
        this.colorFondoNM="yellow";
        this.colorLetraNM="black";
        this.colorFondoNF="green";
        this.colorLetraNF="black";
        this.colorRI="red";
        this.colorRM="yellow";
        this.colorRF="green";
        this.lnr = new ListsNodesRelations();
        this.idNode=1;
        this.htmlRules="";
        this.htmlSelect="";
        this.exceptions="";
        this.data = data;
    }



    public boolean processFormat()
    {
        try
        {            
          FastVector[] m_allTheRules = apriori.getAllTheRules();
        for(int j=0;j<idsRules.length;j++)
        {
            int indexRule = (Integer.parseInt(idsRules[j]));
            String antecedente = "" + ((ItemSet)m_allTheRules[0].elementAt(indexRule)).toString(data);
            String consecuente = "" + ((ItemSet)m_allTheRules[1].elementAt(indexRule)).toString(data);
            //information Node
            processNodes(antecedente,consecuente);
            //Information edges
            processRelations(antecedente,consecuente, indexRule);
        }
              
            return true;
      }
      catch(Exception err)
      {
         return false;
      }  
    }
    
    private void processNodes( String before, String after)
    {
       //information Node 
          NodeRule nod = null;
          if(lnr.findListNode(before.trim())== null)
           {
               nod = new NodeRule(idNode,before.trim(),"black","yellow");
               lnr.addListNode(nod);
               idNode++;
           }
          if(lnr.findListNode(after.trim())== null)
           {
               nod = new NodeRule(idNode,after.trim(),"black","cyan");
               lnr.addListNode(nod);
               idNode++;
           } 
    }
    private void processRelations(String before, String after,int indexRule)
    {

         NodeRule nr1= lnr.findListNode(before.trim());
         NodeRule nr2= lnr.findListNode(after.trim());
         if( nr1 != null && nr2!= null)
          {
            RelationRule r1 = null;

               r1= new RelationRule(nr1.getId(),nr2.getId(),"arrow",colorRI,2,300,""+(indexRule));          
               lnr.addListRelation(r1);
          }
    }
    
    public String buildFormatVisJS()
    {
        String tNodos = "function fun_nodes(){var nodes1 = [";
        String tEdges="function fun_edges(){var edges1 = [";
        ListIterator<NodeRule> eln = lnr.getListNodes().listIterator();
        while(eln.hasNext()==true)
        {
            NodeRule nr = eln.next();
            tNodos +="{id: "+nr.getId()+", label: '"+nr.getLabel()+"', group: '"+nr.getGroup()+"', color:'"+nr.getColor()+"'},"; 
        }
        tNodos+="];return nodes1;}";
        ListIterator<RelationRule> elr = lnr.getListRelations().listIterator();
        while(elr.hasNext()==true)
        {
            RelationRule rr = elr.next(); 
            tEdges+="{from: "+rr.getFrom()+", to: "+rr.getTo()+",label:'"+rr.getLabel()+"' ,style:'"+rr.getStyle()+"',color:'"+rr.getColor()+"',width: "+rr.getWidth()+", length: "+rr.getLength()+" },";  
        }
        tEdges+="];return edges1;}";
       return tNodos+"\n\n\n"+tEdges;
    }
    
    public List<RuleComponents> getListRules() {
        return listRules;
    } 
    
    public boolean processHtmlRules(String[] vec)
    {
       //------------------------------------------------------
      try
      {   
        htmlSelect+="<select id='divRule' onchange='funMdMostrarRegla()'>";
        htmlSelect+="<option value='div0'>Seleccionar</option>"; 
               FastVector[] m_allTheRules = apriori.getAllTheRules();
        for(int j=0;j<idsRules.length;j++)
        {
            int indexRule = (Integer.parseInt(idsRules[j]));
            String antecedente = "" + ((ItemSet)m_allTheRules[0].elementAt(indexRule)).toString(data);
            String consecuente = "" + ((ItemSet)m_allTheRules[1].elementAt(indexRule)).toString(data);
            String confianza =    Utils.doubleToString(((Double)m_allTheRules[2].elementAt(indexRule)).doubleValue(),3);
          htmlSelect+="<option value='div"+indexRule+"'>Regla"+indexRule+"</option>";
          htmlRules+="<div id='div"+(indexRule)+"' style='display:none;position:absolute;top:60px;left:970px'>";
          htmlRules+="<table border='1'>";
          htmlRules+="<tr><th>Antecedente</th></tr><tr><td BGCOLOR='yellow'>"+antecedente +"</td></tr>";
          htmlRules+="<tr><th>Consecuente</th></tr><tr><td BGCOLOR='cyan'>"+consecuente +"</td></tr>";
          htmlRules+="<tr><th>Factor de confianza</th></tr><tr><td>"+confianza+"</td></tr>";
          htmlRules+="</table>";
          htmlRules+="</div>";
          
        }
       htmlSelect+="</select>";
        return true;
      }catch(Exception error)
      {
          this.exceptions = error.toString();
          return false;
      }
      
    }
    
    
    public String getHtmlRules() {
        return htmlRules;
    }

    public String getHtmlSelect() {
        return htmlSelect;
    }
}
