package data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InstanceReader {
    protected FileReader fileReader;
    protected BufferedReader bufferedReader;
    protected String filename;

    public InstanceReader(String filename) {
        this.filename = filename;
    }

    public void open() {
        try {
            fileReader = new FileReader(filename);
            bufferedReader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (bufferedReader != null)
                bufferedReader.close();

            if (fileReader != null)
                fileReader.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public String readLine() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int readInt() {
        return Integer.valueOf(readLine());
    }

    public double[][] readDoubleMatrix(int i, int j) {
        return readDoubleMatrix(i, j, ",");
    }

    public double[][] readDoubleMatrix(int i, int j, String separator) {
        double[][] result = new double[i][j];

        for (int k = 0; k < i; k++) {
            String[] split = readLine().split(separator);
            result[k] = Utility.toDoubleArray(split);
        }

        return result;
    }

    public int[] readIntVector(String separator) {
        String[] strings = readLine().split(separator);

        int[] result = new int[strings.length];

        for (int k = 0; k < strings.length; k++)
            result[k] = Integer.valueOf(strings[k]);

        return result;
    }

    public int[] readIntVector(int size, String separator) {
        return readIntVector(",");
    }

    public int[][] readIntMatrix(int i, int j) {
        return readIntMatrix(i, j, ",");
    }

    public int[][] readIntMatrix(int i, int j, String separator) {
        int[][] result = new int[i][j];

        for (int k = 0; k < i; k++) {
            String[] split = readLine().split(separator);
            result[k] = Utility.toIntArray(split);
        }

        return result;
    }
}