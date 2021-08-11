package game.levels;

import game.gameLogic.LevelInformation;
import game.gameLogic.Sprite;
import game.geometryLogic.Block;
import game.geometryLogic.Point;
import game.geometryLogic.Rectangle;
import game.geometryLogic.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * 1st level.
 */
public class DirectHit implements LevelInformation {
    private int blockWidth = 30;
    private int blockHeight = blockWidth;
    private int guiWidth = 800;
    private int guiHeight = 600;

    /**
     * number of balls in game.
     * @return number of balls.
     */
    public int numberOfBalls() {
        return 1;
    }

    /**
     * List of velocities to start balls at.
     * @return list of velocities.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityArrayList = new ArrayList<Velocity>();
        velocityArrayList.add(new Velocity(0, -5));
        return velocityArrayList;
    }


    /**
     * Paddle speed.
     * @return speed.
     */
    public int paddleSpeed() {
        return 10;
    }

    /**
     *
     * @return width of paddle.
     */
    public int paddleWidth() {
        return 100;
    }

    /**
     *
     * @return level name.
     */
    public String levelName() {
        return "Direct Hit";
    }

    /**
     *
     * @return background wanted for level
     */
    public Sprite getBackground() {
        return new TargetBackground();
    }

    /**
     * List of block in the level.
     * @return the list.
     */
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<Block>();
        Block dontMissThisOne = new Block(new Rectangle(new Point((guiWidth - blockWidth) / 2, 150),
         blockWidth, blockHeight), Color.RED);
        blockList.add(dontMissThisOne);
        return blockList;
    }

    /**
     *
     * @return amount of non special blocks that need to be broken to win.
     */
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
