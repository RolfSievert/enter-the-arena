package se.liu.ida.carro311rolsi701.tddd78.carlorolf;

/**
 * Saves coordinates and offers calculation methods for them.
 */
public class Vector {
    private double x, y;

    public Vector(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public Vector(Vector vector) {
        this.x = vector.getX();
        this.y = vector.getY();
    }

    public double dot(Vector vector){
        return x*vector.getX() + y*vector.getY();
    }

    //Method is here for eventual future uses
    @SuppressWarnings("unused")
    public Vector times(Vector v) {
        return new Vector(x * v.x, y * v.y);
    }

    //Method is here for eventual future uses
    @SuppressWarnings("unused")
    public Vector addition(Vector v) {
        return new Vector(x + v.x, y + v.y);
    }

    public void add(Vector v) {
        x += v.x;
        y += v.y;
    }

    //Method is here for eventual future uses
    @SuppressWarnings("unused")
    public void add(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public void scale(double d) {
        x *= d;
        y *= d;
    }

    public Vector times(double d) {
        return new Vector(x * d, y * d);
    }

    public double getAbs() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public double getX() {
        return x;
    }

    //Method is here for eventual future uses
    @SuppressWarnings("unused")
    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    //Method is here for eventual future uses
    @SuppressWarnings("unused")
    public void setY(double y) {
        this.y = y;
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getDistance(Vector v) {
        double dx = x - v.x;
        double dy = y - v.y;

        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    public double getDistanceToOrigin(){
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    @Override
    public String toString() {
        return "Vector: X" + x + ", Y" + y;
    }

    public Vector getVectorDifference(Vector vector){
        double dX = vector.x - x;
        double dY = vector.y - y;

        return new Vector(dX, dY);
    }
}
