package collidable;

import geometryPrimitives.Rectangle;
import geometryPrimitives.Point;
import sprites.Ball;
import velocity.Velocity;

/**
 * The interface Collidable represent a collidable.
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     *
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity. The return is the new velocity expected after the hit
     * (based on the force the object inflicted on us).
     *
     * @param hitter          the hitter ball.
     * @param collisionPoint  the collision point.
     * @param currentVelocity the current velocity of object.
     * @return the velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}

