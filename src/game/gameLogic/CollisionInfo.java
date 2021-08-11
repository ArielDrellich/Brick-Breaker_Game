package game.gameLogic;

import game.geometryLogic.Point;

/**
 * Information saved on object collided with.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * info of collision object.
     * @param collisionPoint point of collision.
     * @param collisionObject object of collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * @return Point of collision
     */
    // the point at which the collision occurs.
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * @return Collidable object collided with
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}