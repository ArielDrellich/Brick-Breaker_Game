package game.geometryLogic;

import game.gameLogic.GameEnvironment;
import game.gameLogic.GameLevel;
import game.gameLogic.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 *
 * @author Ariel Drellich 328935275
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity v;
    private GameEnvironment gameEnvironment;

    /**
     * @param center          center of ball
     * @param r               radius of ball
     * @param color           color of ball
     * @param gameEnvironment placements of blocks on board
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * @param x               x coordiante of center
     * @param y               y coordiante of center
     * @param r               radius of ball
     * @param color           color of ball
     * @param gameEnvironment placements of blocks on board
     */
    public Ball(int x, int y, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
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
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) center.getX(), (int) center.getY(), radius);
    }

    /**
     * sets the velocity of the ball.
     * @param velocity velocity of the ball
     */
    public void setVelocity(Velocity velocity) {
        this.v = velocity;
    }

    /**
     * sets the velocity of the ball.
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
     * moves the ball one frame forward according to its speed and location.
     * @param width      width of frame
     * @param height     height of frame
     * @param startPoint top left corner of frame
     */
    public void moveOneStep(int width, int height, Point startPoint) {
        if ((int) (this.center.getX() + radius + this.getVelocity().getX()) > width + (int) startPoint.getX()
                || (int) (this.center.getX() - radius + this.getVelocity().getX()) < startPoint.getX()) {
            setVelocity(-this.v.getX(), this.v.getY());
        }
        if ((int) (this.center.getY() + radius + this.getVelocity().getY()) > height + (int) startPoint.getY()
                || (int) (this.center.getY() - radius + this.getVelocity().getY()) < startPoint.getY()) {
            setVelocity(this.v.getX(), -this.v.getY());
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * Moves the ball to where it should be next frame.
     */
    public void moveOneStep() {
        Point end = new Point(this.center.getX() + this.getVelocity().getX(),
                this.center.getY() + this.getVelocity().getY());
        Line trajectory = new Line(this.center, end);
        if (gameEnvironment.getClosestCollision(trajectory) == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
            return;
        }
        Point collisionPoint = gameEnvironment.getClosestCollision(trajectory).collisionPoint();
        double xBack = collisionPoint.getX();
        double yBack = collisionPoint.getY();
        if (v.getX() != 0) {
            if (this.getVelocity().getX() > 0) {
                xBack = collisionPoint.getX() - 1;
            } else {
                xBack = collisionPoint.getX() + 1;
            }
        }
        if (v.getY() != 0) {
            if (this.getVelocity().getY() > 0) {
                yBack = collisionPoint.getY() - 1;
            } else {
                yBack = collisionPoint.getY() + 1;
            }
        }
        Point oneBack = new Point(xBack, yBack);
        this.center = oneBack;
        this.setVelocity(gameEnvironment.getClosestCollision(trajectory).collisionObject().hit(this, collisionPoint,
                this.getVelocity()));
    }

    /**
     * notifies the ball that time has passed and moves it accordingly.
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * @param g game to add ball to
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * removes ball from game.
     * @param g game to remove ball from.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}