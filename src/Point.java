/**
 *
 * @author Ariel Drellich 328935275
 */
public class Point {
    private double x;
    private double y;

    /**
     * sets point based on given cordinates.
     * @param x coordinate
     * @param y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * return the distance of this point to the other point.
     * @param other other point to equate distance between
     * @return distance between points
     */
    public double distance(Point other) {
        return Math.sqrt(((this.x - other.x) * (this.x - other.x)) + ((this.y - other.y) * (this.y - other.y)));
    }

    /**
     * return true if the points are equal, false otherwise.
     * @param other other point to check if equal
     * @return true if equal, false if not
     */
    public boolean equals(Point other) {
        return this.x == other.x && this.y == other.y;
    }

    /**
     * @return x value of point
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return y value of point
     */
    public double getY() {
        return this.y;
    }
}