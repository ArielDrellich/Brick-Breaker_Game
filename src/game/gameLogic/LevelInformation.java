package game.gameLogic;
import game.geometryLogic.Block;
import game.geometryLogic.Velocity;
import java.util.List;

/**
 * Information for levels.
 */
public interface LevelInformation {
    /**
     * @return number of balls in level.
     */
    int numberOfBalls();

    /**
     * List containing initial velocities for the balls.
     * @return the list.
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return speed of the paddle.
     */
    int paddleSpeed();

    /**
     * @return width of the paddle.
     */
    int paddleWidth();

    /**
     * @return name of the level
     */
    String levelName();

    /**
     * @return background of the level.
     */
    Sprite getBackground();

    /**
     * @return list of blocks for the level.
     */
    List<Block> blocks();

    /**
     * @return number of non special blocks in the game.
     */
    int numberOfBlocksToRemove();
}