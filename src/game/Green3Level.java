package game;

import geometryPrimitives.Point;
import geometryPrimitives.Rectangle;
import sprites.Sprite;
import sprites.Block;
import sprites.Background;
import sprites.DrawRectangle;
import sprites.DrawFillCircle;
import velocity.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Green3Level.
 *
 * @author Izhak Ben David izhak573@gmail.com
 * @version 1.6
 * @since 2023 -05-3
 */
//206531139 Izhak Ben David
public class Green3Level implements LevelInformation {
    private static final int WIDTH_OF_BLOCKS = 50;
    private static final int HEIGHT_OF_BLOCKS = 20;
    private static final int START_X_LOCATION_OF_BLOCKS = 280;
    private static final int START_Y_LOCATION_OF_BLOCKS = 170;
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
    public Green3Level() {
        this.numberOfBalls = 2;
        this.initialBallVelocities = new ArrayList<Velocity>();
        this.paddleSpeed = 10;
        this.paddleWidth = 100;
        this.levelName = "Green 3";
        this.getBackground = new ArrayList<Sprite>();
        this.blocks = new ArrayList<Block>();
        this.numberOfBlocksToRemove = 40;
        this.background = new Background();
    }
    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> ballVelocityList = new ArrayList<>();
        ballVelocityList.add(new Velocity(5, -5));
        ballVelocityList.add(new Velocity(-5, -5));
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
                RIGHT_AND_LEFT_BORDER),
                Color.GREEN));
        // add the building to background.
        this.background.getBackground().add(new DrawRectangle(new Rectangle(new Point(60, 450), 90,
                150), Color.BLACK));

        this.background.getBackground().add(new DrawRectangle(new Rectangle(new Point(70, 470), 70,
                50), Color.WHITE));
        this.background.getBackground().add(new DrawRectangle(new Rectangle(new Point(70, 530), 70,
                40), Color.CYAN));

        // add the windows of building to background.
        this.background.getBackground().add(new DrawRectangle(new Rectangle(new Point(95, 400), 25,
                50), Color.GRAY));
        this.background.getBackground().add(new DrawRectangle(new Rectangle(new Point(102, 200), 12,
                200), Color.GRAY));

        // add the light on the building to background.
        this.background.getBackground().add(new DrawFillCircle(new Point(108, 195), 12, Color.YELLOW));
        this.background.getBackground().add(new DrawFillCircle(new Point(108, 195), 8, Color.RED));
        this.background.getBackground().add(new DrawFillCircle(new Point(108, 195), 3, Color.WHITE));

        return background;
    }
    @Override
    public List<Block> blocks() {
        ArrayList<Block> blocks = new ArrayList<>();
        //initialize the location of first block.
        int startPointX = START_X_LOCATION_OF_BLOCKS;
        int startPointY = START_Y_LOCATION_OF_BLOCKS;
        // generate row 1.
        rowBlockGenerator(blocks, startPointX, startPointY, Color.GRAY);
        // generate row 2.
        rowBlockGenerator(blocks, startPointX + WIDTH_OF_BLOCKS, startPointY + HEIGHT_OF_BLOCKS,
                Color.red);
        // generate row 3.
        rowBlockGenerator(blocks, startPointX +  2 * WIDTH_OF_BLOCKS, startPointY + 2 * HEIGHT_OF_BLOCKS,
                Color.YELLOW);
        // generate row 4.
        rowBlockGenerator(blocks, startPointX + 3 * WIDTH_OF_BLOCKS, startPointY + 3 * HEIGHT_OF_BLOCKS,
                Color.BLUE);
        // generate row 5.
        rowBlockGenerator(blocks, startPointX + 4 * WIDTH_OF_BLOCKS, startPointY + 4 * HEIGHT_OF_BLOCKS,
                Color.WHITE);
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
    /**
     * The method generate row of blocks in GUI with given color.
     *
     * @param blocks      the blocks array.
     * @param startPointX the x coordinate of the start point of first block in each row.
     * @param startPointY the y coordinate of the start point of first block in each row.
     * @param color       the color of all block in row
     */
    public void rowBlockGenerator(ArrayList<Block> blocks, int startPointX, int startPointY, java.awt.Color color) {
        for (int i = 0; i < 20; i++) {
            Rectangle rec = new Rectangle(new Point(startPointX, startPointY), WIDTH_OF_BLOCKS, HEIGHT_OF_BLOCKS);
            Block block = new Block(rec, color);
            blocks.add(block); // add the block to array of blocks.
            startPointX += WIDTH_OF_BLOCKS;
            // if we except from screen bounds.
            if (startPointX + WIDTH_OF_BLOCKS > 780) {
                break;
            }
        }
    }
}
