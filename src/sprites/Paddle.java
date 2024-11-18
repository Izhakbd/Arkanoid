package sprites;


import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;
import geometryPrimitives.Point;
import geometryPrimitives.Rectangle;
import geometryPrimitives.Line;
import velocity.Velocity;
import collidable.Collidable;
import game.GameLevel;


/**
 * Represents a Paddle, defined by keyboard.
 *
 * @author Izhak Ben David izhak573@gmail.com
 * @version 1.6
 * @since 2023 -05-3
 */
//206531139 Izhak Ben David
public class Paddle implements Sprite, Collidable {
    private Block paddle;
    private java.awt.Color color;
    private Line rightBorder;
    private Line leftBorder;

    private biuoop.KeyboardSensor keyboard;
    private int paddleWidth;
    private int paddleSpeed;

    /**
     * Constructor of new Paddle.
     *
     * @param paddle      the paddle.
     * @param color       the color of paddle.
     * @param keyboard    the keyboard.
     * @param rightBorder the right border of screen.
     * @param leftBorder  the left border of screen.
     * @param paddleWidth the paddle width.
     * @param paddleSpeed the paddle speed.
     */
    public Paddle(Block paddle, java.awt.Color color, biuoop.KeyboardSensor keyboard, Line rightBorder,
                  Line leftBorder, int paddleWidth, int paddleSpeed) {
        this.paddle = paddle;
        this.color = color;
        this.keyboard = keyboard;
        this.rightBorder = rightBorder;
        this.leftBorder = leftBorder;
        this.paddleWidth = paddleWidth;
        this.paddleSpeed = paddleSpeed;
    }

    /**
     * Move the paddle left.
     */
    public void moveLeft() {
        Point newPoint = new Point(this.paddle.getCollisionRectangle()
                .getUpperLeft().getX() - this.paddleSpeed, this.paddle.getCollisionRectangle()
                .getUpperLeft().getY());
        if (isMoveInRange(newPoint, "left")) {
            this.paddle = new Block(new Rectangle(new Point(newPoint.getX(), newPoint.getY()),
                    this.paddle.getBlock().getWidth(), this.paddle.getBlock().getHeight()), Color.ORANGE);
        }
    }

    /**
     * Move the paddle right.
     */
    public void moveRight()  {
        Point newPoint = new Point(this.paddle.getCollisionRectangle()
                .getUpperLeft().getX() + this.paddleSpeed, this.paddle.getCollisionRectangle()
                .getUpperLeft().getY());
        if (isMoveInRange(newPoint, "right")) {
            this.paddle = new Block(new Rectangle(new Point(newPoint.getX(), newPoint.getY()),
                    this.paddle.getBlock().getWidth(), this.paddle.getBlock().getHeight()), Color.ORANGE);
        }
    }

    /**
     * check if paddle not except from screen.
     *
     * @param point  the point we move the paddle on.
     * @param direct the paddle movement direct.
     * @return if the movement in screen rang true,else,false.
     */
    public boolean isMoveInRange(Point point, String direct) {
        if (point.getX() >= this.leftBorder.start().getX() && direct.equals("left")) {
            return true;
        }
        if ((point.getX() + this.paddleWidth) <= this.rightBorder.start().getX()
                && direct.equals("right")) {
            return true;
        }
        return false;
    }
    // Sprite
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }
    @Override
    public void drawOn(DrawSurface d) {
        this.paddle.drawOn(d);
    }

    // Collidable
    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddle.getCollisionRectangle();
    }
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        int divRegion = this.paddleWidth / 5;
        // if hit in region 1.
        if (collisionPoint.getX() >= this.paddle.getBlock().getUpperLeft().getX()
                && collisionPoint.getX() <= this.paddle.getBlock().getUpperLeft().getX() + divRegion) {
            return Velocity.fromAngleAndSpeed(300,
                    Math.sqrt(currentVelocity.getDx() * currentVelocity.getDx()
                            + currentVelocity.getDy() * currentVelocity.getDy()));
        }
        // if hit in region 2.
        if (collisionPoint.getX() > this.paddle.getBlock().getUpperLeft().getX() + divRegion
                && collisionPoint.getX() <= this.paddle.getBlock().getUpperLeft().getX() + 2 * divRegion) {
            return Velocity.fromAngleAndSpeed(330, Math.sqrt(currentVelocity.getDx() * currentVelocity.getDx()
                    + currentVelocity.getDy() * currentVelocity.getDy()));
        }
        // if hit in region 3.
        if (collisionPoint.getX() > this.paddle.getBlock().getUpperLeft().getX() + 2 * divRegion
                && collisionPoint.getX() <= this.paddle.getBlock().getUpperLeft().getX() + 3 * divRegion) {
            return Velocity.fromAngleAndSpeed(360, Math.sqrt(currentVelocity.getDx() * currentVelocity.getDx()
                    + currentVelocity.getDy() * currentVelocity.getDy()));
        }
        // if hit in region 4.
        if (collisionPoint.getX() > this.paddle.getBlock().getUpperLeft().getX() + 3 * divRegion
                && collisionPoint.getX() <= this.paddle.getBlock().getUpperLeft().getX() + 4 * divRegion) {
            return Velocity.fromAngleAndSpeed(30, Math.sqrt(currentVelocity.getDx() * currentVelocity.getDx()
                    + currentVelocity.getDy() * currentVelocity.getDy()));
        }
        // if hit in region 5.
        if (collisionPoint.getX() > this.paddle.getBlock().getUpperLeft().getX() + 4 * divRegion
                && collisionPoint.getX() <= this.paddle.getBlock().getUpperLeft().getX() + 5 * divRegion) {
            return Velocity.fromAngleAndSpeed(60, Math.sqrt(currentVelocity.getDx() * currentVelocity.getDx()
                    + currentVelocity.getDy() * currentVelocity.getDy()));
        }
        // if no hit in paddle.
        return currentVelocity;
    }
    // Add this paddle to the game.
    @Override
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}