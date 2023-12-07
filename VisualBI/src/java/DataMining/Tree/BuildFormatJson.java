/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataMining.Tree;

import java.util.List;
import java.util.ListIterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Carlitos
 */
public class BuildFormatJson {
   private List<JSONObject> listJsonObjects;
   private List<Relation> listRelations;
   private String exceptions;
   private JSONObject objf;
   private String jsonFormat;
    public BuildFormatJson(List<JSONObject> listJsonObjects, List<Relation> listRelations) {
        this.listJsonObjects = listJsonObjects;
        this.listRelations = listRelations;
        this.objf=null;
        this.jsonFormat="";
        this.exceptions="";
    }




   public boolean buildFormat()
   {  
       String[] vecRelacion=null;
       try
       {
         ListIterator<JSONObject> liObjs = listJsonObjects.listIterator();
         int k=0;
         while(liObjs.hasNext() == true)
          {
              JSONObject objk =liObjs.next();
              String Nod = objk.get("name").toString().split("->")[0];
              JSONArray listArray = new JSONArray();
              ListIterator<Relation> liRels = listRelations.listIterator();
              while(liRels.hasNext()== true)
              {
                  Relation re = liRels.next();
                  vecRelacion = re.getName().split("->");
                  if(Nod.trim().compareTo(vecRelacion[0].trim())==0)
                  {
                      String NodHijo = vecRelacion[1].trim();
                      JSONObject jo = findNode(NodHijo);
                      if(jo!= null)
                      {    
                          listArray.add(jo);
                      }  
                  }
              }
              if(k == 0)
              {
                  setfirstObject(objk);
              }
              if(listArray.size()!=0)
                                   {
                 connectionNodes(objk, listArray);   
              }
              else
              {
                 connectionNodes(objk, null);    
              }
           k++;   
          }  
           return true;
       }
       catch(Exception err)
       {
           this.exceptions=err.getMessage();
           return false;
       }
   }
   private void connectionNodes(JSONObject obj, JSONArray lisObjs)
    {
        obj.put("children", lisObjs);
    }
   
   private void setfirstObject(JSONObject objR){
        objf=objR;
    }
   
   private JSONObject findNode(String cad){
        JSONObject objen = null;
        int b = 0;
        ListIterator<JSONObject> liObjs = listJsonObjects.listIterator();
        while(liObjs.hasNext()==true && b==0)
        {
            JSONObject jo = liObjs.next(); 
            if(cad.compareTo(jo.get("name").toString().split("->")[0].trim())==0)
            {
                b=1;
                objen = jo;
            }
        }
        if(b==1)
        {
            return objen;
        }
        else
        {
            return null;
        }   
    }
   
    public String getJsonFormat() {
        return objf.toJSONString();
    }
    
    public String getExceptions() {
        return exceptions;
    }
}
