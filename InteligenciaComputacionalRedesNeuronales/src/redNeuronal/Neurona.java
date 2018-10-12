/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redNeuronal;

import java.util.Random;

/**
 *
 * @author juanca
 */
public class Neurona {

    double matriz[][];
    boolean activada;

    public Neurona() {
        this.matriz = new double[28][28];
        this.activada = false;
        Random random = new Random();
        random.setSeed(77368737);
        for (int i = 0; i < 28; i++) {
            for (int j = 0; j < 28; j++) {
                this.matriz[i][j] = random.nextDouble();
            }
        }
    }
}
