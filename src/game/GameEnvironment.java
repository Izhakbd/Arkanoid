package game;
import geometryPrimitives.Point;
import geometryPrimitives.Line;
import collidable.Collidable;
import collidable.CollisionInfo;
import java.util.ArrayList;

/**
 * Represents a GameEnvironment, defined by a collidables arraylist.
 *
 * @author Izhak Ben David izhak573@gmail.com
 * @version 1.6
 * @since 2023 -04-28
 */
//206531139 Izhak Ben David
public class GameEnvironment {
    private ArrayList<Collidable> collidables;

    /**
     * Constructor of new Game environment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c the new collidable added to arraylist
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * rutun collidables array.
     *
     * @return the collidables array.
     */
    public ArrayList<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory a line starting at current location, and ending where the velocity will take the ball if no
     *                   collisions will occur.
     * @return the closest collision to start of trajectory.
     */
// Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory) {
        if (this.collidables.size() == 0) {
            return null;
        }
        //initialize of closet collision point and checking if point is not null.
        Point closetCollision =
                trajectory.closestIntersectionToStartOfLine(this.collidables.get(0).getCollisionRectangle());
        int placeOfClosCollision = 0;
        for (int j = 1; j < this.collidables.size(); j++) {
            if (closetCollision == null) {
                closetCollision =
                        trajectory.closestIntersectionToStartOfLine(this.collidables.get(j).getCollisionRectangle());
                placeOfClosCollision = j;
            } else {
                break;
            }
        }
        // check all other collidables if has someone closer to start of trajectory.
        for (int i = placeOfClosCollision + 1; i < this.collidables.size(); i++) {
            Point curInterPoint =
                    trajectory.closestIntersectionToStartOfLine(this.collidables.get(i).getCollisionRectangle());
            if (curInterPoint != null) {
                closetCollision = equalsCollisionPoints(curInterPoint, closetCollision, trajectory);
                if (closetCollision == curInterPoint) { //update the location in list of closet collision point.
                    placeOfClosCollision = i;
                }
            }
        }
        if (closetCollision != null) {
            return new CollisionInfo(closetCollision, this.collidables.get(placeOfClosCollision));
        } else { // if has not collision point.
            return null;
        }
    }

    /**
     * Equals collision points.
     *
     * @param curInterPoint   the cur inter point
     * @param closetCollision the closet collision
     * @param trajectory      a line starting at current location, and ending where the velocity will take the ball
     *                        if no collisions will occur.
     * @return curInterPoint if she is closer to start of trajectory ,else return the closetCollision.
     */
    public Point equalsCollisionPoints(Point curInterPoint, Point closetCollision, Line trajectory) {
        if (curInterPoint.distance(trajectory.start()) < closetCollision.distance(trajectory.start())) {
            return curInterPoint;
        }
        return closetCollision;
    }

}
