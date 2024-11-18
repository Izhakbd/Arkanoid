package geometryPrimitives;
/**
 * Represent a Point, defined by  x and y coordinate.
 *
 * @author Izhak Ben David izhak573@gmail.com
 * @version 1.6
 * @since 2023 -04-19
 */
//206531139 Izhak Ben David
public class Point {
    static final double EPSILON = 0.00001; //threshold.
    private double x;
    private double y;

    /**
     * The constructor.
     *
     * @param x the x of new point.
     * @param y the y of new point.
     */
    public Point(double x, double y) {
            this.x = x;
            this.y = y;

        }

    /**
     * return the distance between two points.
     *
     * @param other other point
     * @return the distance between two points.
     */
    public double distance(Point other) {
            return Math.sqrt((this.x - other.x) * (this.x - other.x)
                    + (this.y - other.y) * (this.y - other.y));
        }

    /**
     * check if two points are equals.
     *
     * @param other other point.
     * @return if points equals return true, otherwise false.
     */
    public boolean equals(Point other) {
            if (other != null) {
                if ((this.x == other.x || Math.abs(this.x - other.x) < EPSILON)
                        && (this.y == other.y || Math.abs(this.y - other.y) < EPSILON)) {
                    return true;
                }
            }
            return false;
        }

    /**
     * return the x coordinates of the point.
     *
     * @return the x coordinates of the point
     */
    public double getX() {
            return this.x;
        }

    /**
     * return the y coordinates of the point.
     *
     * @return the y coordinates of the point
     */
    public double getY() {
            return this.y;
        }

    /**
     * Sets new coordinate x of point.
     *
     * @param x the new value for x coordinate.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets new coordinate y of point.
     *
     * @param y the new value for y coordinate.
     */
    public void setY(double y) {
        this.y = y;
    }
}