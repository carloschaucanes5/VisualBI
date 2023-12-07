/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dwh.Table;

import java.awt.Color;

/**
 *
 * @author Carlitos
 */
public class ColorRGB {
   
    public String getColorAleatorio()
    {
        int valor1 = (int) Math.floor(Math.random()*(255-0+1)+0); 
        int valor2 = (int) Math.floor(Math.random()*(255-0+1)+0); 
        int valor3 = (int) Math.floor(Math.random()*(255-0+1)+0); 
        Color colori=new Color(valor1, valor2, valor3);
        String hex = "#"+Integer.toHexString(colori.getRGB()).substring(2);
        return hex;
    }
    
    public static void main(String arg[])
    {
        ColorRGB col= new ColorRGB();
        System.out.print(col.getColorAleatorio());
    }
}
