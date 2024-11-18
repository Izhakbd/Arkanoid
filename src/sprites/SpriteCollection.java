package sprites;

import java.util.ArrayList;
import biuoop.DrawSurface;

/**
 * Represents a sprite collection, defined by array list of sprites.
 *
 * @author Izhak Ben David izhak573@gmail.com
 * @version 1.6
 * @since 2023 -05-2
 */
//206531139 Izhak Ben David
public class SpriteCollection {
    private ArrayList<Sprite> sprites;

    /**
     * Constructor of new Sprite collection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }

    /**
     * Gets sprites.
     *
     * @return the array sprites
     */
    public ArrayList<Sprite> getSprites() {
        return this.sprites;
    }

    /**
     * Add sprite to sprites.
     *
     * @param s the new sprites we add.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Notify all time passed.
     */
// call timePassed() on all sprites.
    public void notifyAllTimePassed() {
        for (int i = 0; i < sprites.size(); i++) {
            sprites.get(i).timePassed();
        }
    }

    /**
     * Draw all sprites on draw surface.
     *
     * @param d the draw surface.
     */
// call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < sprites.size(); i++) {
            sprites.get(i).drawOn(d);
        }
    }
}
