package game.geometryLogic;

import game.gameLogic.Collidable;
import game.gameLogic.GameLevel;
import game.gameLogic.Sprite;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
/**
 * creates  and keeps track of paddle for the game.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private Color color;
    private double moveSpeed;

    /**
     * @param rectangle rectangle of paddle
     * @param color clor of paddle
     * @param keyboard keyboard to check presses
     * @param moveSpeed speed to move at
     */
    public Paddle(Rectangle rectangle, Color color, biuoop.KeyboardSensor keyboard, double moveSpeed) {
        this.rectangle = rectangle;
        this.color = color;
        this.keyboard = keyboard;
        this.moveSpeed = moveSpeed;
    }

    /**
     * @return returns this rectangle if collided with.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * @param newMoveSpeed changes speed of paddle.
     */
    public void changeSpeed(double newMoveSpeed) {
        this.moveSpeed = newMoveSpeed;
    }

    /**
     * moves the paddle left when clicked.
     */
    public void moveLeft() {
        if (this.rectangle.getUpperLeft().getX() != 10) {
            this.rectangle = new Rectangle(
                    new Point(this.rectangle.getUpperLeft().getX() - moveSpeed, this.rectangle.getUpperLeft().getY()),
                    this.rectangle.getWidth(), this.rectangle.getHeight());
        }
    }

    /**
     * moves the paddle right when clicked.
     */
    public void moveRight() {
        if (this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() != 790) {
            this.rectangle = new Rectangle(
                    new Point(this.rectangle.getUpperLeft().getX() + moveSpeed, this.rectangle.getUpperLeft().getY()),
                    this.rectangle.getWidth(), this.rectangle.getHeight());
        }
    }

    /**
     * updates the paddle that time has passed and if a key is being pressed, move
     * the paddle.
     */
    public void timePassed() {
        if (keyboard.isPressed("a") || keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed("d") || keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * draws the paddle in the game.
     * @param surface surface on which to draw on.
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
     * @param collisionPoint  point of collision on the paddle.
     * @param currentVelocity velocity of the ball.
     * @param hitter ball that's hitting.
     * @return Velocity new velocity based on where the ball hits.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double fifthWidth = this.rectangle.getWidth() / 5;
        double speed = Math.sqrt(
                currentVelocity.getX() * currentVelocity.getX() + currentVelocity.getY() * currentVelocity.getY());
        double angle = Math.atan2(currentVelocity.getY(), currentVelocity.getX());
        double roundedCollisionX = (double) ((int) (collisionPoint.getX() * 1000000)) / 1000000;
        double upperLeftX = this.rectangle.getUpperLeft().getX();
        if (roundedCollisionX == upperLeftX || roundedCollisionX == upperLeftX + fifthWidth * 5) {
            return new Velocity(-currentVelocity.getX(), currentVelocity.getY());
        }
        if (roundedCollisionX >= upperLeftX && roundedCollisionX < upperLeftX + fifthWidth) {
            angle = 300;
        }
        if (roundedCollisionX >= upperLeftX + fifthWidth && roundedCollisionX < upperLeftX + 2 * fifthWidth) {
            angle = 330;
        }
        if (roundedCollisionX >= upperLeftX + 2 * fifthWidth && roundedCollisionX < upperLeftX + 3 * fifthWidth) {
            return new Velocity(0, -speed);
        }
        if (roundedCollisionX >= upperLeftX + 3 * fifthWidth && roundedCollisionX < upperLeftX + 4 * fifthWidth) {
            angle = 30;
        }
        if (roundedCollisionX >= upperLeftX + 4 * fifthWidth && roundedCollisionX < upperLeftX + 5 * fifthWidth) {
            angle = 60;
        }
        return Velocity.fromAngleAndSpeed(angle, speed);
    }

    /**
     * @param g game to add to
     */
    // Add this paddle to the game.
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}