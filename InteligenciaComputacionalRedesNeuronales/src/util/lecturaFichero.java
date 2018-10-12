/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import inteligenciacomputacionalredesneuronales.main;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;

/**
 *
 * @author juanca
 */
public final class lecturaFichero {

    // MNIST URL
    private static final String MNIST_URL = "http://yann.lecun.com/exdb/mnist/";

    // Training data
    private static final String trainingImagesName = "train-images-idx3-ubyte.gz";
    private static final String trainingLabelsName = "train-labels-idx1-ubyte.gz";

    // Test data
    private static final String testImagesName = "t10k-images-idx3-ubyte.gz";
    private static final String testLabelsName = "t10k-labels-idx1-ubyte.gz";

    // Logger
    protected static final Logger log = Logger.getLogger(main.class.getName());

    private final ArrayList<float[][]> trainingImages;
    private final int trainingLabels[];
    private final ArrayList<float[][]> testImages;
    private final int testLabels[];

    public lecturaFichero() throws IOException {
        trainingImages = this.getTrainingImagesRead();
        testImages = this.getTestImagesRead();
        trainingLabels = this.getTrainingLabelsRead();
        testLabels = this.getTestLabelsRead();
    }

    private ArrayList<float[][]> getTrainingImagesRead() throws IOException {
        int images[][][];
        images = readImages("data/mnist/" + trainingImagesName);

        //System.out.println("Raw image data:");
        //System.out.println(toString(images[1]));
        // Normalize image data 
        ArrayList<float[][]> data = new ArrayList<>();
        for (int i = 0; i < images.length; i++) {
            data.add(normalize(images[i]));
        }

        //System.out.println("Normalized image:");
        //System.out.println(toString(data.get(1)));
        return data;
    }

    private int[] getTrainingLabelsRead() throws IOException {
        int labels[] = readLabels("data/mnist/" + trainingLabelsName);

        //System.out.println("Image label:");
        //System.out.println(Arrays.toString(labels));
        return labels;
    }

    private ArrayList<float[][]> getTestImagesRead() throws IOException {
        int images[][][];
        images = readImages("data/mnist/" + testImagesName);

        //System.out.println("Raw image data:");
        //System.out.println(toString(images[0]));
        // Normalize image data 
        ArrayList<float[][]> data = new ArrayList<>();
        for (int i = 0; i < images.length; i++) {
            data.add(normalize(images[i]));
        }

        //System.out.println("Normalized image:");
        //System.out.println(toString(data.get(0)));
        return data;
    }

    private int[] getTestLabelsRead() throws IOException {
        int labels[] = readLabels("data/mnist/" + testLabelsName);
        //System.out.println("Image label:");
        //System.out.println(Arrays.toString(labels));
        return labels;
    }

    /**
     * Download URL to file using Java NIO.
     *
     * @param url Source URL
     * @param filename Destination file name
     */
    private static void download(String urlString, String filename) throws IOException {
        URL url = new URL(urlString);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(filename);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
    }

    /**
     * Download MNIST database.
     *
     * @param directory Destination folder/directory.
     * @throws IOException
     */
    private static void downloadMNIST(String directory) throws IOException {
        File baseDir = new File(directory);
        if (!(baseDir.isDirectory() || baseDir.mkdir())) {
            throw new IOException("Unable to create destination folder " + baseDir);
        }
        log.info("Downloading MNIST database...");
        download(MNIST_URL + trainingImagesName, directory + trainingImagesName);
        download(MNIST_URL + trainingLabelsName, directory + trainingLabelsName);
        download(MNIST_URL + testImagesName, directory + testImagesName);
        download(MNIST_URL + testLabelsName, directory + testLabelsName);
        log.info("MNIST database downloaded into " + directory);
    }

    // Read data from files
    /**
     * Read MNIST image data.
     *
     * @param filename File name
     * @return 3D int array
     * @throws IOException
     */
    private static int[][][] readImages(String filename) throws IOException {
        FileInputStream file = null;
        InputStream gzip = null;
        DataInputStream data = null;
        int images[][][] = null;

        try {
            file = new FileInputStream(filename);
            gzip = new GZIPInputStream(file);
            data = new DataInputStream(gzip);
            log.info("Reading MNIST data...");
            int magicNumber = data.readInt();
            if (magicNumber != 2051) {
                throw new IOException("Error while reading MNIST data from " + filename);
            }
            int size = data.readInt();
            int rows = data.readInt();
            int columns = data.readInt();
            images = new int[size][rows][columns];
            log.info("Reading " + size + " " + rows + "x" + columns + " images...");
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < rows; j++) {
                    for (int k = 0; k < columns; k++) {
                        images[i][j][k] = data.readUnsignedByte();
                    }
                }
            }
            log.info("MNIST images read from " + filename);
        } finally {
            if (data != null) {
                data.close();
            }
            if (gzip != null) {
                gzip.close();
            }
            if (file != null) {
                file.close();
            }
        }
        return images;
    }

    /**
     * Read MNIST labels
     *
     * @param filename File name
     * @return Label array
     * @throws IOException
     */
    private static int[] readLabels(String filename) throws IOException {
        FileInputStream file = null;
        InputStream gzip = null;
        DataInputStream data = null;
        int labels[] = null;
        try {
            file = new FileInputStream(filename);
            gzip = new GZIPInputStream(file);
            data = new DataInputStream(gzip);
            log.info("Reading MNIST labels...");
            int magicNumber = data.readInt();
            if (magicNumber != 2049) {
                throw new IOException("Error while reading MNIST labels from " + filename);
            }
            int size = data.readInt();
            labels = new int[size];
            for (int i = 0; i < size; i++) {
                labels[i] = data.readUnsignedByte();
            }
            log.info("MNIST labels read from " + filename);
        } finally {
            if (data != null) {
                data.close();
            }
            if (gzip != null) {
                gzip.close();
            }
            if (file != null) {
                file.close();
            }
        }

        return labels;
    }

    /**
     * Normalize raw image data, i.e. convert to floating-point and rescale to
     * [0,1].
     *
     * @param image Raw image data
     * @return Floating-point 2D array
     */
    private static float[][] normalize(int image[][]) {
        int rows = image.length;
        int columns = image[0].length;
        float data[][] = new float[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                data[i][j] = (float) image[i][j] / 255f;
            }
        }

        return data;
    }

    // Standard I/O
    private static String toString(int label) {
        return Integer.toString(label);
    }

    private static String toString(int image[][]) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                String hex = Integer.toHexString(image[i][j]);
                if (hex.length() == 1) {
                    builder.append("0");
                }
                builder.append(hex);
                builder.append(' ');
            }
            builder.append('\n');
        }

        return builder.toString();
    }

    private static String toString(float image[][]) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                builder.append(String.format(Locale.US, "%.3f ", image[i][j]));
            }
            builder.append('\n');
        }
        return builder.toString();
    }

    /**
     * @return the trainingImages
     */
    public ArrayList<float[][]> getTrainingImages() {
        return trainingImages;
    }

    /**
     * @return the trainingLabels
     */
    public int[] getTrainingLabels() {
        return trainingLabels;
    }

    /**
     * @return the testImages
     */
    public ArrayList<float[][]> getTestImages() {
        return testImages;
    }

    /**
     * @return the testLabels
     */
    public int[] getTestLabels() {
        return testLabels;
    }

}
