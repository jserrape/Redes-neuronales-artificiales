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

    private final Capa capaEntrada;
    private final Capa capaOculta;
    private final Capa capaSalida;

    public RedNeuronal() {
        System.out.println("Creando la red neuronal");
        this.capaEntrada = new Capa("Capa de entrada", 784, 0);
        this.capaOculta = new Capa("Capa de oculta", 784, 784);
        this.capaSalida = new Capa("Capa de salida", 10, 784);
        System.out.println("Finalizada la creación de la red neuronal");
    }

}
