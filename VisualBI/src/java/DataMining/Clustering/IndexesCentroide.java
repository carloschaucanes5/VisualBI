/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataMining.Clustering;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlitos
 */
public class IndexesCentroide {
    private int indexCentroide;
    private List<Integer> listIndexInstances;
    public IndexesCentroide(int indexCentroide) {
        this.indexCentroide = indexCentroide;
        this.listIndexInstances = new ArrayList<Integer>();
    }

    public int getIndexCentroide() {
        return indexCentroide;
    }

    public List<Integer> getListIndexInstances() {
        return listIndexInstances;
    }
    public void addListIndexes(int index)
    {
        listIndexInstances.add(index);
    }
    
}
