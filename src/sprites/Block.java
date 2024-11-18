package sprites;

import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import game.GameLevel;
import geometryPrimitives.Rectangle;
import collidable.Collidable;
import velocity.Velocity;
import geometryPrimitives.Point;
import hit.HitListener;
import hit.HitNotifier;

/**
 * Represents a Block, defined by rectangle and color.
 *
 * @author Izhak Ben David izhak573@gmail.com
 * @version 1.6
 * @since 2023 -04-28
 */
//206531139 Izhak Ben David
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle block;
    private java.awt.Color color;
    private List<HitListener> hitListeners;

    /**
     * Constructor of new Block.
     *
     * @param block rectangle who represent block.
     * @param color the color of block
     */
    public Block(Rectangle block, java.awt.Color color) {
        this.block = block;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * return the block.
     *
     * @return the block.
     */
    public Rectangle getBlock() {
        return this.block;
    }

    /**
     * return the color of block.
     *
     * @return the color of block
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * return hit listeners list.
     *
     * @return the hit listeners list.
     */
    public List<HitListener> getHitListeners() {
        return this.hitListeners;
    }

    /**
     * Sets block upper left point.
     *
     * @param point the new upper left point.
     */
    public void setBlock(Point point) {
        this.block.setUpperLeft(point);
    }

    /**
     * Add a new block to game.
     *
     * @param g the Game.
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Remove block from game.
     *
     * @param gameLevel the game.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.getSprites().getSprites().remove(this);
        gameLevel.getEnvironment().getCollidables().remove(this);
    }
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * The function draw block on surface.
     *
     * @param surface A surface on which the block is drawn.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                this.block.getWidth(), this.block.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                this.block.getWidth(), this.block.getHeight());
    }

    @Override
    public void timePassed() {
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // calculate the block's corners.
        Point upperRight = new Point(this.block.getUpperLeft().getX() + this.block.getWidth(),
                this.block.getUpperLeft().getY());
        Point downLeft = new Point(this.block.getUpperLeft().getX(),
                this.block.getUpperLeft().getY() + this.block.getHeight());
        Point downRight = new Point(this.block.getUpperLeft().getX() + this.block.getWidth(),
                this.block.getUpperLeft().getY() + this.block.getHeight());
        //if hit in one of block's corners
        if (collisionPoint.equals(this.block.getUpperLeft()) || collisionPoint.equals(upperRight)
                || collisionPoint.equals(downLeft) || collisionPoint.equals(downRight)) {
            this.notifyHit(hitter);
            return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
        }
        // if hit in left or right line of block.
        if (this.getBlock().getLeftLineOfRec().isPointOnLine(collisionPoint)
                || this.getBlock().getRightLineOfRec().isPointOnLine(collisionPoint)) {
            this.notifyHit(hitter);
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        // if hit in up or down line of block.
        if (this.getBlock().getUpperLineOfRec().isPointOnLine(collisionPoint)
                || this.getBlock().getLowerLineOfRec().isPointOnLine(collisionPoint)) {
            this.notifyHit(hitter);
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        this.notifyHit(hitter);
        return new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
