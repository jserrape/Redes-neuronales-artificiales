/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redNeuronal;

import java.util.Random;

/**
 *
 * @author xenahort
 */
public class Capa {

    private final String nombreCapa;

    private final int outputTam;
    private final double outputArray[];
    private final double weith[][];
    private final int tamCapaAnterior;
    private final double umbral[];
    private final double salidaDelta[];
    private final double deltaArray[];
    private final double deltaWeith[][];
    private final double umbralDelta[];

    public Capa(String nomCapa, int tamArrayOutput, int tCapaAnterior) {

        Random random = new Random();

        this.nombreCapa = nomCapa;
        this.outputTam = tamArrayOutput;
        this.outputArray = new double[this.outputTam];

        this.tamCapaAnterior = tCapaAnterior;
        this.weith = new double[this.outputTam][this.tamCapaAnterior];
        for (int i = 0; i < this.outputTam; i++) {
            for (int j = 0; j < this.tamCapaAnterior; j++) {
                this.weith[i][j] = random.nextDouble();
            }
        }

        this.umbral = new double[tamArrayOutput];
        for (int i = 0; i < tamArrayOutput; i++) {
            this.umbral[i] = random.nextDouble();
        }

        this.salidaDelta = new double[10];
        this.deltaArray = new double[tamArrayOutput];

        this.deltaWeith = new double[tamArrayOutput][tCapaAnterior];
        for (int i = 0; i < tamArrayOutput; i++) {
            for (int j = 0; j < tCapaAnterior; j++) {
                this.deltaWeith[i][j] = 0.0;
            }
        }

        this.umbralDelta = new double[tamArrayOutput];
        for (int i = 0; i < tamArrayOutput; i++) {
            this.umbralDelta[i] = 0.0;
        }

        System.out.println("Creada la capa: " + this.nombreCapa + " con una salida de tamaño: " + this.outputTam);
    }
}
