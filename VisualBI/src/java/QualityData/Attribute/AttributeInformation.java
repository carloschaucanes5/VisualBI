/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QualityData.Attribute;

/**
 *
 * @author Carlitos
 */
public class AttributeInformation {

    private String attributeName;
    private String attributeTable;
    private String attributeNumericFormat;
    private String attributeNominalFormat;

    public AttributeInformation(String attributeName, String attributeTable, String nominalFormat, String numericFormat) {
        this.attributeName = attributeName;
        this.attributeTable = attributeTable;
        this.attributeNumericFormat = numericFormat;
        this.attributeNominalFormat = nominalFormat;
    }
   
    public String getAttributeNumericFormat() {
        return attributeNumericFormat;
    }
    public String getAttributeNominalFormat() {
        return attributeNominalFormat;
    }
    
    public String getAttributeName() {
        return attributeName;
    }

    public String getAttributeTable() {
        return attributeTable;
    }
}
