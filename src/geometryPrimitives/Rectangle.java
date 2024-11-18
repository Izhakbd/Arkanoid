package geometryPrimitives;
import java.util.ArrayList;

/**
 * Represents a Rectangle, defined by a upperLeft point , width and height.
 *
 * @author Izhak Ben David izhak573@gmail.com
 * @version 1.6
 * @since 2023 -04-26
 */
//206531139 Izhak Ben David
public class Rectangle {
    private Point upperLeft;
    private int width;
    private int height;
    private Line upperLineOfRec;
    private Line lowerLineOfRec;
    private Line leftLineOfRec;
    private Line rightLineOfRec;

    /**
     * constructor of new rectangle.
     *
     * @param upperLeft the upper left point of rectangle.
     * @param width     the width of rectangle.
     * @param height    the height of rectangle.
     */
    public Rectangle(Point upperLeft, int width, int height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        // the upper border.
        this.upperLineOfRec = new Line(this.getUpperLeft(), new Point(this.getUpperLeft().getX() + this.getWidth(),
                this.getUpperLeft().getY()));
        //the lower border.
        this.lowerLineOfRec = new Line(new Point(this.getUpperLeft().getX(),
                this.getUpperLeft().getY() + this.getHeight()),
                new Point(this.getUpperLeft().getX() + this.getWidth(),
                        this.getUpperLeft().getY() + this.getHeight()));
        // the left border.
        this.leftLineOfRec = new Line(this.getUpperLeft(),
                new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY() + this.getHeight()));
        //the right border.
        this.rightLineOfRec = new Line(new Point(this.getUpperLeft().getX() + this.getWidth(),
                this.getUpperLeft().getY()), new Point(this.getUpperLeft().getX() + this.getWidth(),
                this.getUpperLeft().getY() + this.getHeight()));
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     *
     * @param line the line
     * @return list of intersection points with specified line, if not have intersection points return null.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> list = new ArrayList<Point>();
        if (line.isIntersecting(this.upperLineOfRec)) {
            list.add(line.intersectionWith(this.upperLineOfRec));
        }
        if (line.isIntersecting(this.lowerLineOfRec)) {
            list.add(line.intersectionWith(this.lowerLineOfRec));
        }
        if (line.isIntersecting(this.leftLineOfRec)) {
            list.add(line.intersectionWith(this.leftLineOfRec));
        }
        if (line.isIntersecting(this.rightLineOfRec)) {
            list.add(line.intersectionWith(this.rightLineOfRec));
        }
        return list;
    }

    /**
     * The method return the width of rectangle.
     *
     * @return the width of rectangle.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * The method return the height of rectangle.
     *
     * @return the height of rectangle.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * The methode update the width of rectangle.
     *
     * @param width the width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * The methode update the height of rectangle.
     *
     * @param height the height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Sets new upper left of rectangle.
     *
     * @param newUpperLeft the new upper left of rectangle.
     */
    public void setUpperLeft(Point newUpperLeft) {
        this.upperLeft.setX(newUpperLeft.getX());
        this.upperLeft.setY(newUpperLeft.getY());
    }

    /**
     * The method return the upper left of rectangle.
     *
     * @return the upper left of rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Gets upper line of rec.
     *
     * @return the upper line of rec
     */
    public Line getUpperLineOfRec() {
        return this.upperLineOfRec;
    }

    /**
     * The method return lower line of rec.
     *
     * @return the lower line of rec
     */
    public Line getLowerLineOfRec() {
        return this.lowerLineOfRec;
    }

    /**
     * The method return line of rec.
     *
     * @return the left line of rec
     */
    public Line getLeftLineOfRec() {
        return this.leftLineOfRec;
    }

    /**
     * The method return line of rec.
     *
     * @return the right line of rec
     */
    public Line getRightLineOfRec() {
        return this.rightLineOfRec;
    }
}
