package sprites;

import hit.HitListener;
import velocity.Velocity;
import geometryPrimitives.Point;
import geometryPrimitives.Line;
import collidable.CollisionInfo;
import game.GameEnvironment;
import game.GameLevel;
import biuoop.DrawSurface;

import java.util.List;

/**
 * Represents a Ball, defined by center point ,radius ,and color.
 *
 * @author Izhak Ben David izhak573@gmail.com
 * @version 1.6
 * @since 2023 -04-19
 */
//206531139 Izhak Ben David
public class Ball implements Sprite {
    private GameEnvironment gameEnvironment;
    private static final int RIGHT_BORDER = 200;
    private static final int LEFT_BORDER = 0;
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity = new Velocity(0, 0);
    private List<HitListener> hitListeners;

    /**
     * constructor a new Ball.
     *
     * @param center          the center point of the ball.
     * @param r               the radius of the ball.
     * @param color           the color of ball.
     * @param gameEnvironment the game environment
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * another constructor a new Ball.
     *
     * @param x               the x coordinate of ball's center point.
     * @param y               the y coordinate of ball's center point.
     * @param r               the r
     * @param color           the ball's color
     * @param gameEnvironment the game environment
     */
    public Ball(double x, double y, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * the function return the x coordinate of ball's center point.
     *
     * @return the x coordinate of ball's center point.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * the function return the y coordinate of ball's center point.
     *
     * @return the y coordinate of ball's center point.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * the function return the ball size.
     *
     * @return the ball's radius.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * the function return the ball color.
     *
     * @return the ball's color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * The function sets ball's velocity.
     *
     * @param v the new velocity.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * The function sets ball's velocity.
     *
     * @param dx the dx field of new velocity.
     * @param dy the dy field of new velocity.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity.setDx(dx);
        this.velocity.setDy(dy);
    }

    /**
     * The function return the ball's velocity.
     *
     * @return the ball's velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * The function draw ball on surface.
     *
     * @param surface A surface on which the ball is drawn.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Move the ball one step forward depends on velocity.
     */
    public void moveOneStep() {
        Line trajectory = new Line(this.center, new Point(this.center.getX() + this.velocity.getDx(),
                this.center.getY() + this.velocity.getDy()));
        // check if ball hit in frame's corners.
        if (trajectory.isPointOnLine(new Point(20, 20)) || trajectory.isPointOnLine(new Point(20, 580))
                || trajectory.isPointOnLine(new Point(780, 20)) || trajectory.isPointOnLine(new Point(780, 580))) {
            this.setVelocity(-this.velocity.getDx(), -this.velocity.getDy());
            return;
        }
        CollisionInfo closetCollision = this.gameEnvironment.getClosestCollision(trajectory);
        if (closetCollision == null) { //if no hit at all.
            this.center = this.getVelocity().applyToPoint(this.center); //move the ball.
        } else { //if has a hit.
            this.center.setX(this.center.getX()
                    + (Math.abs(closetCollision.collisionPoint().getX() - this.center.getX()) - 0.0001));
            this.center.setY(this.center.getY()
                    + (Math.abs(closetCollision.collisionPoint().getY() - this.center.getY()) - 0.0001));
            Velocity newV = closetCollision.collisionObject().hit(this, closetCollision.collisionPoint(),
                    this.velocity);
            this.setVelocity(newV.getDx(), newV.getDy());
        }
    }

    /**
     * Add a new ball to game.
     *
     * @param g the Game.
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
    /**
     * Remove block from game.
     *
     * @param gameLevel the game.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.getSprites().getSprites().remove(this);
    }
}
