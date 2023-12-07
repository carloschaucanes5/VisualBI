package DataMining.Clustering;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author carlitos
 */

import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class FormatJson {
    private List<JSONObject> lisObjetosJson;
    private JSONObject objf;

    public FormatJson() {
         this.lisObjetosJson  = new ArrayList<JSONObject>();
         this.objf = null;
    }
   
    public JSONObject createObjectNode(String node,String centroide,String distance){
        JSONObject obj = new JSONObject();
        obj.put("name",node);
        obj.put("label",centroide);
        obj.put("size",distance);
        lisObjetosJson.add(obj);
        return obj; 
    }
    
    public void firstObject(JSONObject objR){
        objf=objR;
    }
    
    public  List<JSONObject>  getListObjects(){
        return lisObjetosJson;        
    }
    
    public void connectionNodes(JSONObject obj, JSONArray lisObjs)
    {
        obj.put("children", lisObjs);
    }
    public String getFormatJson()
    {
        return objf.toJSONString();
    }
}
