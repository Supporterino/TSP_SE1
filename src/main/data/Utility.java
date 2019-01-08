package data;

public class Utility {
    public static double[] toDoubleArray(String[] array) {
        if (array == null)
            throw new IllegalArgumentException("array is null");

        double[] result = new double[array.length];

        for (int i = 0; i < array.length; i++)
            result[i] = Double.valueOf(array[i].trim());

        return result;
    }

    public static int[] toIntArray(String[] array) {
        if (array == null)
            throw new IllegalArgumentException("array is null");

        int[] result = new int[array.length];

        for (int i = 0; i < array.length; i++)
            result[i] = Integer.valueOf(array[i].trim());

        return result;
    }
}