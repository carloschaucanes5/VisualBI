/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataMining.Tree;


import File.Upload.InstancesFile;
import File.Upload.WriteFile;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONObject;
import weka.classifiers.trees.J48;
import weka.core.Instances;


/**
 *
 * @author Carlitos
 */
public class ClassJ48 {
    private Instances data;
    private float confidenceFactor;
    private int numMinObj;
    private int numFolds;
    private int seed;
    private String tree;
    private List<Node> listNodes;
    private List<Relation> listRelations;
    private  String exceptions;
    private List<JSONObject> listObjetosJson;
    private J48 j48;

    
    public ClassJ48(Instances data,float confidenceFactor,int numMinObj,int numFolds,int seed) {
        this.data=data;
        this.confidenceFactor = confidenceFactor;
        this.numMinObj=numMinObj;
        this.numFolds=numFolds;
        this.seed = seed;
        this.listRelations=new ArrayList<Relation>(); 
        this.tree="";
        this.exceptions="";
        this.listObjetosJson = new ArrayList<JSONObject>();
        this.j48=null;
    }



    public boolean processJ48()
    {
        try
        {
            j48 = new J48();
            data.setClassIndex(data.numAttributes() - 1);          
            String[] options = new String[1]; 
            options[0] = "-C "+confidenceFactor+" -M "+numMinObj+""; // confidenceFactor = 1.0, minNumObject = 5   
            j48.setUnpruned(true);        // using an unpruned J48
            j48.setNumFolds(numFolds);
            j48.setSeed(seed);
            j48.setOptions(options);
            j48.buildClassifier(data);
            tree=j48.graph().toString();
            //-------------------------------------------------------------------
            String[] s=tree.split("]");
            int i=0;
            String regla="";
            String nombreNi="";
            String textoNi="";
            //LISTAS
            while(i<s.length-1)
            {
              String r=s[i].toString();
                if(i==0)
                {
                 regla=""+ r.substring(17,r.length()) +"]";
                }
                else
                {
                    regla=r+"]";
                }
             //----------FILTRO DE NODOS Y SUS RelationES------------------------------              
             if(i%2!=0)
             { 
                String[] m=regla.split("l");
                String rela=m[0].toString().substring(0,m[0].toString().length()-1);   
                String[] m1=regla.split("\"");
                String label=m1[1];
                Relation Relation = new Relation(rela, label);
                listRelations.add(Relation);
             }
             else
             {   
                 String[] regla1=regla.split("\"");
                 String cad=regla1[0];
                 String[] regla2=cad.split("\\[");
                 nombreNi=regla2[0].toString();
                 textoNi=regla1[1].toString();
                 Node node = new Node(nombreNi, textoNi, "");
                 Node nod =setLink(1,node);
                 JSONObject obj = createObjectNode(nod.getName()+"->"+nod.getLabel(),nod.getLink());
                 this.listObjetosJson.add(obj);
              } 
             i++;
          }

           return true;
        }
        catch(Exception err)
        {
            this.exceptions=err.toString();
            return false;
        }
    }
        
    public J48 getJ48() {
        return j48;
    }
    //-------------------------------------------------------------------------
   private JSONObject createObjectNode(String nameNodo,String linkNodo)
   {
        JSONObject obj = new JSONObject();
        obj.put("name",nameNodo);
        obj.put("label",linkNodo);
        return obj;
    }
    //-------------------------------------------------------------------------
    private Node setLink(int cod, Node nod)
    {
      if(cod==1)
      {
        String nameNode = nod.getName();
        int j = listRelations.size()-1;
        int b = 0;
        while(j>=0 && b==0)
        {
            String nodLight = listRelations.get(j).getName().split("->")[1];
            if(nameNode.trim().compareTo(nodLight.trim())==0)
            {
                nod.setLink(listRelations.get(j).getLabel());
                b=1;
            }
            j--;
        }
      }
      return nod;
    }
    
    public String getTableFeatures()
    {
        String table="";
        table+="<table border='1' class='table table-bordered table-hover'>";
        table+="<tr><th>Característica</th><th>Value</th><tr>";
        table+="<tr><td>Información global</td><td>"+j48.globalInfo()+"</td><tr>";
        table+="<tr><td>Resumen</td><td>"+j48.toSummaryString()+"</td><tr>";
        table+="<table>";
        return table;
    }
     public List<JSONObject> getListObjetosJson() {
        return listObjetosJson;
    }
     
     public List<Relation> getListRelations() {
        return listRelations;
    }
     /*
    public static void main(String[] arg)
    {
       InstancesFile inf = new InstancesFile("D:/Ficheros/ingles_31_variables.arff");
       if(inf.processingData()==true)
       {
            ClassJ48 cJ48 = new ClassJ48(inf.getData(),0.25f,3,3,3);
            if(cJ48.processJ48() == true)
            {
                BuildFormatJson bfj = new BuildFormatJson(cJ48.getListObjetosJson(), cJ48.getListRelations());
                if(bfj.buildFormat()==true)
                {
                    //System.out.println(bfj.getJsonFormat());
                    WriteFile wf = new WriteFile("D:/ceci.json",bfj.getJsonFormat());
                    wf.write();
                    System.out.print("listo compadre");
                }
            }
       }
    }*/
    
}
