package velocity;
import geometryPrimitives.Point;
/**
 * Represent a Velocity, defined by dx and dy parameters.
 *
 * @author Izhak Ben David izhak573@gmail.com
 * @version 1.6
 * @since 2023 -03-26
 */
//206531139 Izhak Ben David
public class Velocity {
    private double dx;
    private double dy;

    /**
     * construct a new Instantiates of Velocity.
     *
     * @param dx the change in position in X axe.
     * @param dy the change in position in Y axe.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * the function return the dx field.
     *
     * @return the dx field.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * the function return the dy field.
     *
     * @return the dy field.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * update dx field in velocity.
     *
     * @param dx the new dx for Velocity.
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * update dy field in velocity.
     *
     * @param dy the dy for Velocity.
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * The function get point with position (x, y) and return new point
     * with position (x+dx, y+dy).
     *
     * @param p current point with posiotion (x, y).
     * @return point with position (x+dx, y+dy).
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * The function calculate and return the velocity.
     *
     * @param angle the direction of velocity.
     * @param speed the speed of velocity.
     * @return new velocity object.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radianAngle = Math.toRadians(angle);
        double dx = speed * Math.sin(radianAngle);
        double dy = -speed * Math.cos(radianAngle);
        return new Velocity(dx, dy);
    }
}
