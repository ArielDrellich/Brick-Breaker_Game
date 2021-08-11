import biuoop.DrawSurface;
//import java.awt.Color;

/**
 *
 * @author Ariel Drellich 328935275
 */
public class Ball {
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity v;

    /**
     * @param center center of ball
     * @param r radius of ball
     * @param color color of ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }
    /**
     * @param x x coordiante of center
     * @param y y coordiante of center
     * @param r radius of ball
     * @param color color of ball
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
    }

    /**
     * @return x coordiante of center
     */
    // accessors
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return y coordinate of center
     */
    public int getY() {
        return (int) this.center.getY();

    }

    /**
     * @return radius of ball
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * @return Color of ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * draws the ball on the given DrawSurface.
     * @param surface draw the ball on the given DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int) center.getX(), (int) center.getY(), radius);
    }

    /**
     * sets the velocity of the ball.
     * @param velocity velocity of the ball
     */
    public void setVelocity(Velocity velocity) {
        this.v = velocity;
    }

    /**
     * sets the veloctiy of the ball.
     * @param dx x velocity of ball
     * @param dy y velocity of ball
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * @return Velocity of ball
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * moves ball one frame forward according to speed and loaction.
     */
    public void moveOneStep() {
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * moves the ball one frame forward according to its speed and location.
     * @param width width of frame
     * @param height height of frame
     * @param startPoint top left corner of frame
     */
    public void moveOneStep(int width, int height, Point startPoint) {
        if ((int) (this.center.getX() + radius + this.getVelocity().getX())
        > width + (int) startPoint.getX()
                || (int) (this.center.getX() - radius + this.getVelocity().getX()) < startPoint.getX()) {
            setVelocity(-this.v.getX(), this.v.getY());
        }
        if ((int) (this.center.getY() + radius + this.getVelocity().getY())
        > height + (int) startPoint.getY()
                || (int) (this.center.getY() - radius + this.getVelocity().getY()) < startPoint.getY()) {
            setVelocity(this.v.getX(), -this.v.getY());
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }
}