package sprites;

import biuoop.DrawSurface;
import game.GameLevel;
import geometryPrimitives.Point;
import java.awt.Color;

/**
 * Represents a draw circle (only draw edge of circle) who draw in background.
 *
 * @author Izhak Ben David izhak573@gmail.com
 * @version 1.6
 * @since 2023 -06-10
 */
//206531139 Izhak Ben David
public class DrawCircle implements Sprite {
    private Point center;
    private int radius;
    private Color color;

    /**
     * Constructor of new Draw circle.
     *
     * @param center the center of circle
     * @param radius the radius of circle
     * @param color  the color of circle
     */
    public DrawCircle(Point center, int radius, Color color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.radius); //draw only the edge of circle.
    }

    @Override
    public void timePassed() {
        return;
    }

    @Override
    public void addToGame(GameLevel g) {
        return;
    }
}
