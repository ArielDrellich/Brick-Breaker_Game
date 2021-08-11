/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 * @author Ariel Drellich 328935275
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * @param dx velocity on x axis
     * @param dy velocity on y axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * sets velocity based of angle and speed.
     * @param angle direction to move in
     * @param speed speed to move at
     * @return Velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos(Math.toRadians(angle - 90));
        double dy = speed * Math.sin(Math.toRadians(angle - 90));
        return new Velocity(dx, dy);
    }

    /**
     * @return returns velocity on x axis
     */
    public double getX() {
        return this.dx;
    }

    /**
     * @return returns velocity on y axis
     */
    public double getY() {
        return this.dy;
    }

    /**
     * @param p point to apply to
     * @return Point
     */
    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}