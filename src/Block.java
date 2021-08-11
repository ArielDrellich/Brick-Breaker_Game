import biuoop.DrawSurface;

import java.awt.Color;
/**
 * Blocks for the game.
 */
public class Block implements Collidable, Sprite {
    private Rectangle rectangle;
    private Color color;

    /**
     * constructor for the block.
     * @param rectangle rectangle of the block.
     * @param color color of the block.
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
    }

    /**
     * @return Rectangle of this block.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Notify the object that we collided with it at collisionPoint with a given
     * velocity. The return is the new velocity expected after the hit (based on the
     * force the object inflicted on us).
     * @param collisionPoint of collision
     * @param currentVelocity     velocity of ball
     * @return Velocity new velocity after collision
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        double vx = currentVelocity.getX();
        double vy = currentVelocity.getY();
        final double epsilon = 0.000001;
        if (collisionPoint.getX() + epsilon > rectangle.getUpperLeft().getX()
                && collisionPoint.getX() - epsilon < rectangle.getUpperLeft().getX()) {
            vx = -vx;
        }
        if (collisionPoint.getY() + epsilon > rectangle.getUpperLeft().getY()
                && collisionPoint.getY() - epsilon < rectangle.getUpperLeft().getY()) {
            vy = -vy;
        }
        if (collisionPoint.getX() + epsilon > rectangle.getUpperLeft().getX() + rectangle.getWidth()
                && collisionPoint.getX() - epsilon < rectangle.getUpperLeft().getX() + rectangle.getWidth()) {
            vx = -vx;
        }
        if (collisionPoint.getY() + epsilon > rectangle.getUpperLeft().getY() + rectangle.getHeight()
                && collisionPoint.getY() - epsilon < rectangle.getUpperLeft().getY() + rectangle.getHeight()) {
            vy = -vy;
        }
        return new Velocity(vx, vy);
    }

    /**
     * draws the rectangle on the given DrawSurface.
     * @param surface draw the ball on the given DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
    }

    /**
     * needed to be part of interface, not yet implemented.
     */
    public void timePassed() {

    }

    /**
     * @param g game to add to.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
