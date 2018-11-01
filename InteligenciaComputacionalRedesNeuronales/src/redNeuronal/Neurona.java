/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redNeuronal;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author juanca
 */
public class Neurona {

    private double matriz[][];
    private boolean activada;
    private double valorAjuste;
    public double humbral;

    public Neurona(double valorA, int hum) {
        this.matriz = new double[28][28];
        this.activada = false;
        this.valorAjuste = valorA;
        this.humbral = hum;
        Random random = new Random();
        random.setSeed(77368737);
        for (int i = 0; i < 28; i++) {
            for (int j = 0; j < 28; j++) {
                this.matriz[i][j] = random.nextDouble();
            }
        }
    }

    public double obtenerProducto(float trainingImage[][]) {
        double peso = 0.0;
        for (int i = 0; i < 28; i++) {
            for (int j = 0; j < 28; j++) {
                peso += this.matriz[i][j] * trainingImage[i][j];
            }
        }

        this.activada = peso >= this.humbral;

        return peso;
    }

    public void entrenar(boolean acierto) {
        //Si he superado el humbral y no es su numero le penalizo
        if (this.activada && !acierto) {
            for (int i = 0; i < 28; i++) {
                for (int j = 0; j < 28; j++) {
                    this.matriz[i][j] -= this.matriz[i][j] * this.valorAjuste;
                }
            }
        }
        //Si no se ha activado y si es su número, le aumento los valores
        if (!this.activada && acierto) {
            for (int i = 0; i < 28; i++) {
                for (int j = 0; j < 28; j++) {
                    this.matriz[i][j] += this.matriz[i][j] * this.valorAjuste;
                }
            }
        }
    }

    public void pintarImagen(int n) {
        try {
            BufferedImage image = new BufferedImage(28, 28, BufferedImage.TYPE_BYTE_GRAY);
            for (int i = 0; i < 28; i++) {
                for (int j = 0; j < 28; j++) {
                    float a = (float) matriz[i][j];
                    Color newColor = new Color(a, a, a);
                    image.setRGB(j, i, newColor.getRGB());
                }
            }
            File output = new File("neurona" + n + ".jpg");
            ImageIO.write(image, "jpg", output);
        } catch (IOException e) {
        }
    }
}
