/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inteligenciacomputacionalredesneuronales;

import java.io.IOException;
import java.util.ArrayList;
import redNeuronal.RedNeuronal;
import util.lecturaFichero;

/**
 *
 * @author juanca
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        lecturaFichero lectura = new lecturaFichero();
        
        ArrayList<float[][]> trainingImages=lectura.getTrainingImages();
        int trainingLabels[]=lectura.getTrainingLabels();
        
        RedNeuronal redPerceptron=new RedNeuronal(0.3,2);
        
        int errores=0;
        for(int i=0;i<trainingImages.size();i++){
            if(redPerceptron.entrenarRedImagen(trainingImages.get(i), trainingLabels[i])){
                ++errores;
            }
        }
        System.out.println("Errores de entrenamiento: "+errores);
    }
    
}
