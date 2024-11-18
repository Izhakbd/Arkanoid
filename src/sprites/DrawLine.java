package sprites;

import biuoop.DrawSurface;
import game.GameLevel;
import geometryPrimitives.Line;

import java.awt.Color;

/**
 * Represents a draw line who draw in background.
 *
 * @author Izhak Ben David izhak573@gmail.com
 * @version 1.6
 * @since 2023 -06-10
 */
//206531139 Izhak Ben David
public class DrawLine implements Sprite {
    private Line line;
    private Color color;

    /**
     * Constructor of new Draw Line.
     *
     * @param line      the line we draw.
     * @param color     the color of rectangle.
     */
    public DrawLine(Line line, Color color) {
        this.line = line;
        this.color = color;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawLine((int) this.line.start().getX(), (int) this.line.start().getY(), (int) this.line.end().getX(),
                (int) this.line.end().getY());
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
