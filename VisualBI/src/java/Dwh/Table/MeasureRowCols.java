/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dwh.Table;

/**
 *
 * @author Carlitos
 */
public class MeasureRowCols {
    private int row;
    private String valuesCols;
  public MeasureRowCols(int row,String valuesCols)
    {
        this.row = row;
        this.valuesCols = valuesCols;
    }

    public int getRow() {
        return row;
    }

    public String getValuesCols() {
        return valuesCols;
    }
}
