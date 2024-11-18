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
import sprites.DrawFillCircle;
import sprites.DrawLine;

/**
 * Represents a WideEasyLevel.
 *
 * @author Izhak Ben David izhak573@gmail.com
 * @version 1.6
 * @since 2023 -05-3
 */
//206531139 Izhak Ben David
public class WideEasyLevel implements LevelInformation {
    private static final int RIGHT_AND_LEFT_BORDER = 600;
    private static final int UP_AND_DOWN_BORDER = 800;
    private static final int WIDTH_OF_BLOCKS = 51;
    private static final int HEIGHT_OF_BLOCKS = 20;
    private static final int START_X_LOCATION_OF_BLOCKS = 20;
    private static final int START_Y_LOCATION_OF_BLOCKS = 250;
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
     * Constructor of new Wide Easy Level.
     */
    public WideEasyLevel() {
        this.numberOfBalls = 10;
        this.initialBallVelocities = new ArrayList<Velocity>();
        this.paddleSpeed = 3;
        this.paddleWidth = 600;
        this.levelName = "Wide Easy";
        this.getBackground = new ArrayList<Sprite>();
        this.blocks = new ArrayList<Block>();
        this.numberOfBlocksToRemove = 15;
        this.background = new Background();
    }
    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> ballVelocityList = new ArrayList<>();
        double startAngle = -65;
        for (int i = 0; i < this.numberOfBalls; i++) {
            ballVelocityList.add(Velocity.fromAngleAndSpeed(startAngle, 8));
            startAngle = startAngle + 15;
        }
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
        int startPointX = START_X_LOCATION_OF_BLOCKS;
        this.background.getBackground().add(new Block(new Rectangle(new Point(0, 0), UP_AND_DOWN_BORDER,
                RIGHT_AND_LEFT_BORDER), Color.WHITE));
        // add the Sun rays to background.
        for (int i = startPointX; i < 705; i += 10) {
            this.background.getBackground().add(new DrawLine(new Line(new Point(120, 120), new Point(i, 250)),
                    Color.ORANGE));
        }
        // add the sun to background.
        this.background.getBackground().add(new DrawFillCircle(new Point(120, 120), 50, Color.ORANGE));
        this.background.getBackground().add(new DrawFillCircle(new Point(120, 120), 40, Color.YELLOW));
        return background;
    }
    @Override
    public List<Block> blocks() {
        int startPointX = START_X_LOCATION_OF_BLOCKS;
        int startPointY = START_Y_LOCATION_OF_BLOCKS;
        ArrayList<Block> blocks = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Rectangle rec = new Rectangle(new Point(startPointX, startPointY), WIDTH_OF_BLOCKS, HEIGHT_OF_BLOCKS);
            Block block = new Block(rec, Color.red);
            blocks.add(block);
            startPointX += WIDTH_OF_BLOCKS;
        }
        for (int i = 0; i < 2; i++) {
            Rectangle rec = new Rectangle(new Point(startPointX, startPointY), WIDTH_OF_BLOCKS, HEIGHT_OF_BLOCKS);
            Block block = new Block(rec, Color.ORANGE);
            blocks.add(block);
            startPointX += WIDTH_OF_BLOCKS;
        }
        for (int i = 0; i < 2; i++) {
            Rectangle rec = new Rectangle(new Point(startPointX, startPointY), WIDTH_OF_BLOCKS, HEIGHT_OF_BLOCKS);
            Block block = new Block(rec, Color.YELLOW);
            blocks.add(block);
            startPointX += WIDTH_OF_BLOCKS;
        }
        for (int i = 0; i < 3; i++) {
            Rectangle rec = new Rectangle(new Point(startPointX, startPointY), WIDTH_OF_BLOCKS, HEIGHT_OF_BLOCKS);
            Block block = new Block(rec, Color.GREEN);
            blocks.add(block);
            startPointX += WIDTH_OF_BLOCKS;
        }
        for (int i = 0; i < 2; i++) {
            Rectangle rec = new Rectangle(new Point(startPointX, startPointY), WIDTH_OF_BLOCKS, HEIGHT_OF_BLOCKS);
            Block block = new Block(rec, Color.BLUE);
            blocks.add(block);
            startPointX += WIDTH_OF_BLOCKS;
        }
        for (int i = 0; i < 2; i++) {
            Rectangle rec = new Rectangle(new Point(startPointX, startPointY), WIDTH_OF_BLOCKS, HEIGHT_OF_BLOCKS);
            Block block = new Block(rec, Color.PINK);
            blocks.add(block);
            startPointX += WIDTH_OF_BLOCKS;
        }
        for (int i = 0; i < 2; i++) {
            Rectangle rec = new Rectangle(new Point(startPointX, startPointY), WIDTH_OF_BLOCKS, HEIGHT_OF_BLOCKS);
            Block block = new Block(rec, Color.CYAN);
            blocks.add(block);
            startPointX += WIDTH_OF_BLOCKS;
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
}
