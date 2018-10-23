/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redNeuronal;

import java.util.ArrayList;

/**
 *
 * @author juanca
 */
public class RedNeuronal {
    
    Neurona neuronas[];
    
    public RedNeuronal(double valorAjuste, int humbral) {
        this.neuronas = new Neurona[10];
        for (int i = 0; i < 10; i++) {
            this.neuronas[i] = new Neurona(valorAjuste, humbral);
        }
    }
    
    
    public boolean entrenarRedImagen(float imagen[][], int etiqueta) {
        double mejorPeso = neuronas[0].obtenerProducto(imagen), aux;
        int mejorNeurona = 0;
        for (int i = 1; i < 10; i++) {
            aux = neuronas[i].obtenerProducto(imagen);
            if (aux > mejorPeso) {
                mejorPeso = aux;
                mejorNeurona = i;
            }
        }
        
        for (int i = 0; i < 10; i++) {
            neuronas[i].entrenar(i == etiqueta);
        }
        
        return mejorNeurona == etiqueta;
    }
}
