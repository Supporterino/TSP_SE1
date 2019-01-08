package data;

import base.City;

import java.util.ArrayList;

public class TSPLIBReader {
    protected InstanceReader instanceReader;
    protected double[][] coordinates;
    private int dimension;
    private ArrayList<City> cities;
    private double[][] distances;

    public TSPLIBReader(InstanceReader instanceReader) {
        this.instanceReader = instanceReader;
        cities = new ArrayList<>();
        readHeader();
        readCoordinates();
        convertCoordinatesToDistance();
    }

    public static double euclideanDistance(double x1, double y1, double x2, double y2) {
        double xDistance = Math.abs(x1 - x2);
        double yDistance = Math.abs(y1 - y2);
        return Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
    }

    public void readHeader() {
        String line = instanceReader.readLine();

        while (!line.equalsIgnoreCase("NODE_COORD_SECTION")) {
            String[] split = line.split(":");

            String key = split[0].trim();

            if (key.equalsIgnoreCase("DIMENSION"))
                dimension = Integer.valueOf(split[1].trim());

            line = instanceReader.readLine();

            if (line == null)
                break;
        }
    }

    private void readCoordinates() {
        coordinates = new double[dimension][3];

        String line = instanceReader.readLine();

        int i = 0;
        while (line != null) {
            String[] split = line.split(" ");

            coordinates[i][0] = Integer.valueOf(split[0].trim()); // id
            coordinates[i][1] = Double.valueOf(split[1].trim());  // x
            coordinates[i][2] = Double.valueOf(split[2].trim());  // y

            cities.add(i, new City((int) coordinates[i][0], coordinates[i][1], coordinates[i][2]));

            i++;
            line = instanceReader.readLine();
        }
    }

    private void convertCoordinatesToDistance() {
        distances = new double[dimension][dimension];

        for (int i = 0; i < dimension; i++)
            for (int j = i; j < dimension; j++)
                if (i != j) {
                    double x1 = coordinates[i][1];
                    double y1 = coordinates[i][2];
                    double x2 = coordinates[j][1];
                    double y2 = coordinates[j][2];

                    distances[i][j] = euclideanDistance(x1, y1, x2, y2);
                    distances[j][i] = distances[i][j];
                }
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public int getDimension() {
        return dimension;
    }

    public double[][] getDistances() {
        return distances;
    }
}