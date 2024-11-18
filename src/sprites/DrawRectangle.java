package sprites;

import biuoop.DrawSurface;
import game.GameLevel;
import geometryPrimitives.Rectangle;
import java.awt.Color;

/**
 * Represents a draw Rectangle who draw in background.
 *
 * @author Izhak Ben David izhak573@gmail.com
 * @version 1.6
 * @since 2023 -06-10
 */
//206531139 Izhak Ben David
public class DrawRectangle implements Sprite {
    private Rectangle rectangle;
    private Color color;

    /**
     * Constructor of new Draw circle.
     *
     * @param rectangle the rectangle we draw.
     * @param color     the color of circle
     */
    public DrawRectangle(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                this.rectangle.getWidth(), this.rectangle.getHeight());
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
