import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Blocks for the game.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * constructor for the block.
     * @param rectangle rectangle of the block.
     * @param color color of the block.
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * @return Rectangle of this block.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Returns color of block.
     * @return color of block.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Notify the object that we collided with it at collisionPoint with a given
     * velocity. The return is the new velocity expected after the hit (based on the
     * force the object inflicted on us).
     * @param collisionPoint of collision
     * @param currentVelocity     velocity of ball
     * @param hitter ball thats hitting block
     * @return Velocity new velocity after collision
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
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

    /**
     * Removes object from game.
     * @param game game to remove object from.
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    /**
     * adds hit listener.
     * @param hl hitlistener to add
     */
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    /**
     * removes hitlistener.
     * @param hl hitlistener to remove
     */
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * notifies hitting.
     * @param hitter ball that hits the block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
