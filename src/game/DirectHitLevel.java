package game;


import geometryPrimitives.Line;
import geometryPrimitives.Point;
import geometryPrimitives.Rectangle;
import velocity.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import sprites.Block;
import sprites.Sprite;
import sprites.Background;
import sprites.DrawLine;
import sprites.DrawCircle;

/**
 * Represents a DirectHitLevel.
 *
 * @author Izhak Ben David izhak573@gmail.com
 * @version 1.6
 * @since 2023 -05-3
 */
//206531139 Izhak Ben David
public class DirectHitLevel implements LevelInformation {
    private static final int RIGHT_AND_LEFT_BORDER = 600;
    private static final int UP_AND_DOWN_BORDER = 800;
    private int numberOfBalls;
    private List<Velocity> initialBallVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private List<Sprite> getBackground;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;
    private Background background;

    /**
     * Constructor of new Direct hit level.
     */
    public DirectHitLevel() {
        this.numberOfBalls = 1;
        this.initialBallVelocities = new ArrayList<Velocity>();
        this.paddleSpeed = 5;
        this.paddleWidth = 100;
        this.levelName = "Direct Hit";
        this.getBackground = new ArrayList<Sprite>();
        this.blocks = new ArrayList<Block>();
        this.numberOfBlocksToRemove = 1;
        this.background = new Background();
    }
    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> ballVelocityList = new ArrayList<>();
        ballVelocityList.add(new Velocity(0, 4));
        return ballVelocityList;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        this.background.getBackground().add(new Block(new Rectangle(new Point(0, 0), UP_AND_DOWN_BORDER,
                RIGHT_AND_LEFT_BORDER), Color.BLACK));
        this.background.getBackground().add(new DrawCircle(new Point(400, 150), 100, Color.BLUE));
        this.background.getBackground().add(new DrawCircle(new Point(400, 150), 70, Color.BLUE));
        this.background.getBackground().add(new DrawCircle(new Point(400, 150), 50, Color.BLUE));
        this.background.getBackground().add(new DrawLine(new Line(new Point(400, 40), new Point(400, 130)),
                Color.BLUE));
        this.background.getBackground().add(new DrawLine(new Line(new Point(400, 260), new Point(400, 170)),
                Color.BLUE));
        this.background.getBackground().add(new DrawLine(new Line(new Point(290, 150), new Point(380, 150)),
                Color.BLUE));
        this.background.getBackground().add(new DrawLine(new Line(new Point(510, 150), new Point(420, 150)),
                Color.BLUE));
        return this.background;
    }
    @Override
    public List<Block> blocks() {
        ArrayList<Block> blocks = new ArrayList<>();
        //add the red block appear in middle of Dartboard.
        blocks.add(new Block(new Rectangle(new Point(385, 135), 30, 30), Color.RED));
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
}
