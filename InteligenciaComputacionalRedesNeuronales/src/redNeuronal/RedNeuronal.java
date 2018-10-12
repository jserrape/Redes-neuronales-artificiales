/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redNeuronal;

/**
 *
 * @author juanca
 */
public class RedNeuronal {

    Neurona neuronas[];

    public RedNeuronal() {
        this.neuronas = new Neurona[10];
        for (int i = 0; i < 10; i++) {
            this.neuronas[i] = new Neurona();
        }
    }

    public void entrenar(int etiqueta, double imagen[]) {

    }
}
