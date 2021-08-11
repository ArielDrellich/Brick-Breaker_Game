package game.gameLogic;

import game.geometryLogic.Ball;
import game.geometryLogic.Point;
import game.geometryLogic.Rectangle;
import game.geometryLogic.Velocity;
import biuoop.DrawSurface;

/**
 * interface for collidable objects.
 */
public interface Collidable {
    /**
     * @return objected collided with.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given
     * velocity. The return is the new velocity expected after the hit (based on the
     * force the object inflicted on us).
     * @param collisionPoint  point of collision
     * @param currentVelocity velocity of ball
     * @param hitter ball thats hitting block
     * @return new velocity based on what it hit where.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * @param surface to draw on
     */
    void drawOn(DrawSurface surface);
}