/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redNeuronal;

/**
 *
 * @author xenahort
 */
public class Capa {

    private final String nombreCapa;

    private int outputTam;
    private double outputArray[];

    public Capa(String nomCapa, int tamArrayOutput) {
        this.nombreCapa = nomCapa;
        this.outputTam = tamArrayOutput;
        this.outputArray = new double[this.outputTam];

        System.out.println("Creada la capa: " + this.nombreCapa + " con una salida de tamaño: " + this.outputTam);
    }
}
