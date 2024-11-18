package geometryPrimitives;
/**
 * Represents a Line, defined by a start point and an end point.
 *
 * @author Izhak Ben David izhak573@gmail.com
 * @version 1.6
 * @since 2023 -04-19
 */
//206531139 Izhak Ben David
public class Line {

    /**
     * The Epsilon.
     */
    static final double EPSILON = 0.00001; //threshold.
    private Point start;
    private Point end;
    /**
     * The Zero.
     */
    static final int ZERO = 0;

    /**
     * construct a new Line.
     *
     * @param start the start point of line.
     * @param end   the end point of line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * construct a new Line.
     *
     * @param x1 the x of start point of line.
     * @param y1 the y start point of line.
     * @param x2 the x end point of line.
     * @param y2 the y end point of line.
     */
    public Line(double x1, double y1, double x2, double y2) {
        Point start = new Point(x1, y1);
        Point end = new Point(x2, y2);
        this.start = start;
        this.end = end;
    }

    /**
     * Calculate slope of line.
     *
     * @return the slope of line.
     */
    public double calculateSlope() {
        //check if not dividing in zero
        if (this.start.getX() - this.end.getX() != ZERO) {
            double slope = (this.end.getY() - this.start.getY())
                    / (this.end.getX() - this.start.getX());
            return slope;
        }
        // if line vertical to Y axe.
        return ZERO;
    }

    /**
     * Calculate intersection point of line with y axe.
     *
     * @return the intersection point of line with y axe.
     */
    public double calcInterWithY() {
        double interWithY = this.start.getY()
                - (this.start.getX() * this.calculateSlope());
        return interWithY;
    }

    /**
     * Calculate Length of line.
     *
     * @return the length of line.
     */
    public double length() {
        return Math.sqrt((this.start.getX() - this.end.getX())
                * (this.start.getX() - this.end.getX())
                + (this.start.getY() - this.end.getY())
                * (this.start.getY() - this.end.getY()));
    }

    /**
     * Calculate the middle point of line.
     *
     * @return the middle point of line.
     */
    public Point middle() {
        double middlePointX;
        double middlePointY;
        if (this.end.getX() >= this.start.getX()) {
             middlePointX = this.start.getX() + (this.end.getX() - this.start.getX()) / 2;
        } else {
             middlePointX = this.end.getX() + (this.start.getX() - this.end.getX()) / 2;
        }
        if (this.end.getY() >= this.start.getY()) {
             middlePointY = this.start.getY() + (this.end.getY() - this.start.getY()) / 2;
        } else {
             middlePointY = this.end.getY() + (this.start.getY() - this.end.getY()) / 2;
        }
        return new Point(middlePointX, middlePointY);
    }

    /**
     * return the start point of line.
     *
     * @return the start point of line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * the end point of line.
     *
     * @return the end point of line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * the method check if two lines has intersecting.
     *
     * @param other the second line.
     * @return true, if they have intersection point, else ,false.
     */
    public boolean isIntersecting(Line other) {
        //if lines same.
        if (intersectionWith(other) == null
                && Math.abs(this.calcInterWithY() - other.calcInterWithY()) < EPSILON) {
            return true;
        }
        //if line get intersection point ,and they are not same.
        if (intersectionWith(other) != null) {
            return true;
        }
        return false;
    }

    /**
     * the method check if two lines has intersecting point and calculate her .
     *
     * @param other the second line
     * @return the intersection point if the lines intersect,and null otherwise.
     */
    public Point intersectionWith(Line other) {
        double x, y;
        //if lines parallel or same.
        if (Math.abs(this.calculateSlope() - other.calculateSlope()) < EPSILON && this.calculateSlope() != 0
                && other.calculateSlope() != 0) {
            return null;
        }
        //if this line is vertical to X-line.
        if (Math.abs(this.calculateSlope() - ZERO) < EPSILON
                && Math.abs(this.start.getX() - this.end.getX()) < EPSILON) {
             y = other.calculateSlope() * this.start.getX()
                    + other.calcInterWithY();
            Point interPoint = new Point(this.start.getX(), y); // create intersection point.
            //check if intersection point in rang of the two lines.
            if (this.isPointOnLine(interPoint) && other.isPointOnLine(interPoint)) {
                return new Point(this.start.getX(), y);
            } else {
                return null;
            }
        }


        //if the other line is vertical to X-line.
        if (Math.abs(other.calculateSlope() - ZERO) < EPSILON
                && Math.abs(other.start.getX() - other.end.getX()) < EPSILON) {
                y = this.calculateSlope() * other.start.getX()
                        + this.calcInterWithY();
            Point interPoint = new Point(other.start.getX(), y); // create intersection point.
            //check if intersection point in rang of the two lines.
            if (this.isPointOnLine(interPoint) && other.isPointOnLine(interPoint)) {
                return new Point(other.start.getX(), y);
            } else {
                return null;
            }
        }

        //if they are not vertical or parallel.
         x = (this.calcInterWithY() - other.calcInterWithY())
                / (other.calculateSlope() - this.calculateSlope());
         y = this.calculateSlope() * x + this.calcInterWithY();
         Point interPoint = new Point(x, y); // create intersection point.
         //check if intersection point in rang of the two lines.
         if (this.isPointOnLine(interPoint) && other.isPointOnLine(interPoint)) {
             return new Point(x, y);
         } else {
             return null;
         }
    }

    /**
     * the method calculate if two lines are equals.
     *
     * @param other the second line.
     * @return true is the lines are equal, false otherwise.
     */
// equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) {
        if ((Math.abs(this.start.getX() - other.start.getX()) < EPSILON
                && Math.abs(this.start.getY() - other.start.getY()) < EPSILON
                && Math.abs(this.end.getX() - other.end.getX()) < EPSILON
                && Math.abs(this.end.getY() - other.end.getY()) < EPSILON)
                || (Math.abs(this.start.getX() - other.end.getX()) < EPSILON
                && Math.abs(this.start.getY() - other.end.getY()) < EPSILON
                && Math.abs(this.end.getX() - other.start.getX()) < EPSILON
                && Math.abs(this.end.getY() - other.start.getY()) < EPSILON)) {
            return true;
        }
        return false;
    }

    /**
     * check if current point stay in range of line.
     *
     * @param point current point.
     * @return true if in range of line, else, false.
     */
    public boolean isPointOnLine(Point point) {
        if (((point.getX() >= this.start.getX() || Math.abs(point.getX() - this.start.getX()) < EPSILON)
                && (point.getX() <= this.end.getX() || Math.abs(point.getX() - this.end.getX()) < EPSILON)
                && (point.getY() >= this.start.getY() || Math.abs(point.getY() - this.start.getY()) < EPSILON)
                && (point.getY() <= this.end.getY() || Math.abs(point.getY() - this.end.getY()) < EPSILON))

                || ((point.getX() >= this.start.getX() || Math.abs(point.getX() - this.start.getX()) < EPSILON)
                && (point.getX() <= this.end.getX() || Math.abs(point.getX() - this.end.getX()) < EPSILON)
                && (point.getY() <= this.start.getY() || Math.abs(point.getY() - this.start.getY()) < EPSILON)
                && (point.getY() >= this.end.getY() || Math.abs(point.getY() - this.end.getY()) < EPSILON))

                /*|| ((point.getX() <= this.start.getX() || Math.abs(point.getX() - this.start.getX()) < EPSILON)
                && (point.getX() >= this.end.getX() || Math.abs(point.getX() - this.end.getX()) < EPSILON)
                && (point.getY() <= this.start.getY() || Math.abs(point.getY() - this.start.getY()) < EPSILON)
                && (point.getY() >= this.end.getY() || Math.abs(point.getY() - this.end.getY()) < EPSILON))*/

                || ((point.getX() >= this.end.getX() || Math.abs(point.getX() - this.end.getX()) < EPSILON)
                && (point.getX() <= this.start.getX() || Math.abs(point.getX() - this.start.getX()) < EPSILON)
                && (point.getY() >= this.end.getY() || Math.abs(point.getY() - this.end.getY()) < EPSILON)
                && (point.getY() <= this.start.getY() || Math.abs(point.getY() - this.start.getY()) < EPSILON))

                || ((point.getX() >= this.end.getX() || Math.abs(point.getX() - this.end.getX()) < EPSILON)
                && (point.getX() <= this.start.getX() || Math.abs(point.getX() - this.start.getX()) < EPSILON)
                && (point.getY() <= this.end.getY() || Math.abs(point.getY() - this.end.getY()) < EPSILON)
                && (point.getY() >= this.start.getY() || Math.abs(point.getY() - this.start.getY()) < EPSILON))

                /*|| ((point.getX() <= this.end.getX() || Math.abs(point.getX() - this.end.getX()) < EPSILON)
                && (point.getX() >= this.start.getX() || Math.abs(point.getX() - this.start.getX()) < EPSILON)
                && (point.getY() <= this.end.getY() || Math.abs(point.getY() - this.end.getY()) < EPSILON)
                && (point.getY() >= this.start.getY() || Math.abs(point.getY() - this.start.getY()) < EPSILON))*/
        ) {
            return true;
        }
        return false;
    }

    /**
     * The method return null If this line does not intersect with the rectangle,
     * Otherwise, return the closest intersection point to the
     * start of the line.
     *
     * @param rect the rectangle.
     * @return the closest intersection point to the start of the line,
     * if hasn't intersection point return null.
     */
    // If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> interPoints = rect.intersectionPoints(this);
        //If there is no intersection point.
        if (interPoints.size() == 0 || interPoints.get(0) == null) {
            return null;
        }
        //default value for closest point is the first element in list.
        Point closestPoint = interPoints.get(0);
        //if has only one intersection point.
        if (interPoints.size() == 1) {
            return interPoints.get(0);
        }
        //checking for two first points.
        if (interPoints.get(1).distance(this.start) < interPoints.get(0).distance(this.start)) {
            closestPoint = interPoints.get(1);
        }
        //check for all other points.
        for (int i = 1; i < interPoints.size(); i++) {
            // if element in list is null.
            if (interPoints.get(i) == null) {
                return null;
            }
            if (interPoints.get(i).distance(this.start) < interPoints.get(i - 1).distance(this.start)) {
                closestPoint = interPoints.get(i);
            }
        }
        return closestPoint;
    }
}
