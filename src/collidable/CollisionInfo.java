package collidable;

import geometryPrimitives.Point;

/**
 * Represents a CollisionInfo, defined by a collision point and collision object.
 *
 * @author Izhak Ben David izhak573@gmail.com
 * @version 1.6
 * @since 2023 -04-28
 */
//206531139 Izhak Ben David
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * constructor of new Collision info.
     *
     * @param collisionPoint  the collision point.
     * @param collisionObject the collision object.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * return the point at which the collision occurs.
     *
     * @return the collision point.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * return the collidable object involved in the collision.
     *
     * @return the collision object.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
