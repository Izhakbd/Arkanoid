package sprites;

import biuoop.DrawSurface;
import game.GameLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Background, defined by list of sprites.
 *
 * @author Izhak Ben David izhak573@gmail.com
 * @version 1.6
 * @since 2023 -06-10
 */
//206531139 Izhak Ben David
public class Background implements Sprite {
    private List<Sprite> background;

    /**
     * Constructor of new Background.
     */
    public Background() {
        this.background = new ArrayList<Sprite>();
    }
    @Override
    public void drawOn(DrawSurface d) {
        for (int i = 0; i < background.size(); i++) {
            this.background.get(i).drawOn(d);
        }
    }

    @Override
    public void timePassed() {
        return;
    }

    @Override
    public void addToGame(GameLevel g) {
        return;
    }

    /**
     * Gets background.
     *
     * @return the background list.
     */
    public List<Sprite> getBackground() {
        return background;
    }
}
