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
import java.util.Random;

/**
 * 3rd level.
 */
public class Green3 implements LevelInformation {
    private int guiWidth = 800;
    private int guiHeight = 600;
    private int wallWidth = 15;
    private int blockHeight = 20;
    private int specialBlocksCount = 0;
    private double blockWidth = (guiWidth - 2 * wallWidth) / 15.0;
    private double angle = 90 / this.numberOfBalls();

    /**
     * number of balls in game.
     *
     * @return number of balls.
     */
    public int numberOfBalls() {
        return 2;
    }

    /**
     * List of velocities to start balls at.
     *
     * @return list of velocities.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityArrayList = new ArrayList<Velocity>();
        for (int i = 0; i < this.numberOfBalls(); i++) {
            velocityArrayList
                    .add(Velocity.fromAngleAndSpeed(-angle * (int) (numberOfBalls() / 2) + (2 * angle * i), 5));
        }
        return velocityArrayList;
    }

    /**
     * Paddle speed.
     *
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
        return "Green 3";
    }

    /**
     *
     * @return background wanted for level
     */
    public Sprite getBackground() {
        return new BuildingBackground();
    }

    /**
     * List of block in the level.
     *
     * @return the list.
     */
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<Block>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10 - i; j++) {
                Color color = Color.gray;
                if (i == 1) {
                    color = Color.red;
                }
                if (i == 2) {
                    color = Color.yellow;
                }
                if (i == 3) {
                    color = Color.blue;
                }
                if (i == 4) {
                    color = Color.white;
                }
                if ((new Random()).nextInt(50) == 7) {
                    color = Color.black;
                    specialBlocksCount++;
                } else if ((new Random()).nextInt(30) == 7) {
                    color = Color.magenta;
                    specialBlocksCount++;
                }
                blockList.add(new Block(
                        new Rectangle(new Point(guiWidth - wallWidth - blockWidth * (j + 1), 100 + blockHeight * i),
                                blockWidth, blockHeight),
                        color));
            }
        }
        return blockList;
    }

    /**
     *
     * @return amount of non special blocks that need to be broken to win.
     */
    public int numberOfBlocksToRemove() {
        return 40 - specialBlocksCount;
    }
}
