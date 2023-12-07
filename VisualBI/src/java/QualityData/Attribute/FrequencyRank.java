/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QualityData.Attribute;

/**
 *
 * @author Carlitos
 */
public class FrequencyRank {
    private double limSuperior;
    private double limInferior;
    private int frecuencia;
   public FrequencyRank(double limSuperior,double limInferior,int frecuencia)
    {
        this.limSuperior=limSuperior;
        this.limInferior=limInferior;
        this.frecuencia=frecuencia;
    }
   public void actualizarFrecuencia(int fre)
   {
       this.frecuencia=fre;
   }
    public int getFrecuencia() {
        return frecuencia;
    }

    public double getLimInferior() {
        return limInferior;
    }

    public double getLimSuperior() {
        return limSuperior;
    }


}
