package game.gameLogic;

import game.geometryLogic.Line;
import game.geometryLogic.Point;
import game.geometryLogic.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 * Keeps track of all collidables in the game.
 */
public class GameEnvironment {
    private List<Collidable> collidables = new ArrayList<Collidable>();

    /**
     * @param c collidable to add to list.
     */
    // add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Removes collidable from game environment.
     * @param c collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

    /**
     * @return List<Collidable> returns list of collidables in game.
     */
    public List<Collidable> getCollidablesList() {
        return collidables;
    }

    /**
     * @param trajectory line from center of ball to where it will be next frame
     * without interupptions.
     * @return CollisionInfo info one where and who it collided with. Null if no
     * collision.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closestCollision = null;
        Collidable closestCollidable = null;
        boolean firstFlag = true;
        for (int i = 0; i < collidables.size(); i++) {
            Rectangle rec = collidables.get(i).getCollisionRectangle();
            Point temp = trajectory.closestIntersectionToStartOfLine(rec);
            if (temp != null) {
                if (firstFlag) {
                    closestCollision = temp;
                    closestCollidable = collidables.get(i);
                    firstFlag = false;
                } else {
                    if (trajectory.start().distance(temp) < trajectory.start().distance(closestCollision)) {
                        closestCollision = temp;
                        closestCollidable = collidables.get(i);
                    }
                }
            }
        }
        CollisionInfo colInf = new CollisionInfo(closestCollision, closestCollidable);
        if (colInf.collisionObject() != null) {
            return colInf;
        }
        return null;
    }
}
