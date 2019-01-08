package base;

public class City {
    private int id;
    private double x;
    private double y;

    public City(int id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{ City : ");
        stringBuilder.append("id = ").append(id).append(" - ");
        stringBuilder.append("x = ").append(x).append(" - ");
        stringBuilder.append("y = ").append(y);
        stringBuilder.append(" }");
        return stringBuilder.toString();
    }
}