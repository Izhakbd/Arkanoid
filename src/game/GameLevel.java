package game;

import Animation.Animation;
import Animation.AnimationRunner;
import Animation.KeyPressStoppableAnimation;
import Animation.CountdownAnimation;
import Animation.PauseScreen;
import biuoop.DrawSurface;
import java.awt.Color;
import biuoop.KeyboardSensor;
import collidable.Collidable;
import geometryPrimitives.Point;
import geometryPrimitives.Rectangle;
import hit.BallRemover;
import hit.BlockRemover;
import hit.Counter;
import hit.ScoreTrackingListener;
import sprites.Sprite;
import sprites.SpriteCollection;
import sprites.Ball;
import sprites.Block;
import sprites.Paddle;

/**
 * Represents a GameLevel, defined by array list of sprites.
 *
 * @author Izhak Ben David izhak573@gmail.com
 * @version 1.6
 * @since 2023 -05-2
 */
//206531139 Izhak Ben David
public class GameLevel implements Animation {
    private static final int RIGHT_AND_LEFT_BORDER = 600;
    private static final int UP_AND_DOWN_BORDER = 800;
    private static final int CONSTANT_OF_BORDERS = 20;

    private static final int WIDTH_OF_PADDLE = 100;
    private static final int HEIGHT_OF_PADDLE = 20;

    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation levelInformation;
    private biuoop.KeyboardSensor ks;

    /**
     * Constructor a new Game.
     *
     * @param levelInformation the level information.
     * @param ks               the key Board Sensor.
     * @param ar               the Animation Runner.
     * @param score            the game's score.
     */
    public GameLevel(LevelInformation levelInformation, biuoop.KeyboardSensor ks, AnimationRunner ar, Counter score) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blockCounter = new Counter();
        this.ballCounter = new Counter();
        this.score = score;
        this.runner = ar;
        this.running = true;
        this.levelInformation = levelInformation;
        this.ks = ks;
    }

    /**
     * Gets sprites.
     *
     * @return the array sprites
     */
    public SpriteCollection getSprites() {
        return this.sprites;
    }

    /**
     * Environment game environment.
     *
     * @return the game environment.
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * Add new collidable to environment game.
     *
     * @param c the collidable we added.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add new sprite to environment game.
     *
     * @param s the sprite we added.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Remove collidable c from collidable array.
     *
     * @param c the collidable we are removing.
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollidables().remove(c);
    }

    /**
     * Remove sprite s frome sprites array.
     *
     * @param s the sprite we are removing.
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSprites().remove(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball and Paddle,
     * and add them to the game.
     */
    public void initialize() {
        //creat a ballRemover.
        BallRemover ballRemover = new BallRemover(this, this.ballCounter);
        //create a blockRemover.
        BlockRemover blockRemover = new BlockRemover(this, this.blockCounter);
        //create a ScoreTrackingListener.
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);
        // add background elements to spriteCollection of GameLevel according the level information.
        this.addSprite(this.levelInformation.getBackground());
        //create balls according the level information and insert them to game.
        createBallsAndInsertToLevel(ballRemover);
        // initialize the score block , who show on top of screen.
        Rectangle scoreRec = new Rectangle(new Point(0, 0), UP_AND_DOWN_BORDER, CONSTANT_OF_BORDERS);
        Block scoreBlock = new Block(scoreRec, Color.WHITE);
        // add score block to sprite list only.
        this.addSprite(scoreBlock);
        // add all blocks to spriteCollection of GameLevel according the level information.
        for (int i = 0; i < this.levelInformation.blocks().size(); i++) {
            Block block = this.levelInformation.blocks().get(i);
            // add blockRemover to HitListener list of each block in blocks.
            block.addHitListener(blockRemover);
            // add scoreTrackingListener to HitListener list of each block in blocks.
            block.addHitListener(scoreTrackingListener);
            blockRemover.getRemainingBlocks().increase(1);
            block.addToGame(this);
        }

        //initialize the border blocks.
        //up border
        Rectangle upBound = new Rectangle(new Point(0, CONSTANT_OF_BORDERS), UP_AND_DOWN_BORDER, CONSTANT_OF_BORDERS);
        //left border
        Rectangle leftBound = new Rectangle(new Point(0, CONSTANT_OF_BORDERS), CONSTANT_OF_BORDERS,
                RIGHT_AND_LEFT_BORDER - CONSTANT_OF_BORDERS);
        //down border.
        Rectangle downBound = new Rectangle(new Point(0, RIGHT_AND_LEFT_BORDER),
                UP_AND_DOWN_BORDER - 2 * CONSTANT_OF_BORDERS, CONSTANT_OF_BORDERS);
        //right border.
        Rectangle rightBound = new Rectangle(new Point(UP_AND_DOWN_BORDER  - CONSTANT_OF_BORDERS,
                CONSTANT_OF_BORDERS), CONSTANT_OF_BORDERS, RIGHT_AND_LEFT_BORDER - CONSTANT_OF_BORDERS);
        Block upBoundBlock = new Block(upBound, Color.GRAY);
        Block leftBoundBlock = new Block(leftBound, Color.GRAY);
        Block downBoundBlock = new Block(downBound, Color.GRAY);
        Block rightBoundBlock = new Block(rightBound, Color.GRAY);
        // add bounds to game.
        upBoundBlock.addToGame(this);
        leftBoundBlock.addToGame(this);
        rightBoundBlock.addToGame(this);
        // add down border to colliadble list only.
        this.addCollidable(downBoundBlock);
        // add ballRemover as listener to down border.
        downBoundBlock.addHitListener(ballRemover);

        //create paddle according the level information and insert them to game.
        createPaddleAndInsertToLevel(leftBound, rightBound);
    }

    /**
     * Create balls according the level information and insert them to game.
     *
     * @param ballRemover the ball remover object.
     */
    public void createBallsAndInsertToLevel(BallRemover ballRemover) {
        //create balls according the level information and insert them to game.
        for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {
            Ball ball = new Ball(new Point(400, 550), 5, Color.BLUE, this.environment);
            ball.setVelocity(this.levelInformation.initialBallVelocities().get(i)); // set velocity of balls.
            ball.addToGame(this); // add balls to game.
        }
        // update the number of remainingBall.
        ballRemover.getRemainingBall().increase(this.levelInformation.numberOfBalls());
    }

    /**
     * Create paddle according the level information and insert them to game.
     *
     * @param leftBound  the left bound of Gui.
     * @param rightBound the right bound of Gui.
     */
    public void createPaddleAndInsertToLevel(Rectangle leftBound, Rectangle rightBound) {
        //create paddle according the level information and insert them to game.
        biuoop.KeyboardSensor keyboard = this.runner.getGui().getKeyboardSensor();
        Rectangle paddleRec =
                new Rectangle(new Point((UP_AND_DOWN_BORDER - this.levelInformation.paddleWidth()) / 2.0,
                RIGHT_AND_LEFT_BORDER - 2 * CONSTANT_OF_BORDERS), this.levelInformation.paddleWidth(),
                HEIGHT_OF_PADDLE);
        Block paddleBlc = new Block(paddleRec, Color.ORANGE);
        Paddle paddle = new Paddle(paddleBlc, Color.ORANGE, keyboard, rightBound.getLeftLineOfRec(),
                leftBound.getRightLineOfRec(), this.levelInformation.paddleWidth(),
                this.levelInformation.paddleSpeed());
        paddle.addToGame(this);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        this.runner.setFramesPerSecond(2); // update the framePerSecond to be slower from counting screen.
        // run the Counter 3...2...1
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        runner.setFramesPerSecond(60); // update the framePerSecond to be faster for run of game.
        this.running = true;
        // use our runner to run the current animation -- which is one turn of the game.
        // and run the current level.
        this.runner.run(this);
    }
    @Override
    public void doOneFrame(DrawSurface d) {
            //draw all the sprites.
            this.sprites.drawAllOn(d);
            // draw all painting according specific level.
            //this.levelInformation.drawPainting(d);
            d.setColor(Color.BLACK);
            // print Score to screen.
            d.drawText(350, 15, "Score: " + String.valueOf(this.score.getValue()), 15);
            // print the level name.
            d.drawText(500, 15, "Level Name: " + this.levelInformation.levelName(), 15);
            this.sprites.notifyAllTimePassed();
            // stopping condition
            // if all balls off.
            if (this.ballCounter.getValue() == 0) {
                this.running = false;
            }
            // if all blocks off.
            if (this.blockCounter.getValue() == 0) {
                this.score.increase(100); // add 100 points in end level.
                this.running = false;
            }
            if (this.runner.getGui().getKeyboardSensor().isPressed("p")) {
                KeyboardSensor keyboardSensor = this.runner.getGui().getKeyboardSensor();
                // wrap the pauseScreen with KeyPressStoppableAnimation decorator.
                this.runner.run(new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY,
                        new PauseScreen()));
            }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Gets ball counter.
     *
     * @return the ball counter
     */
    public int getBallCounter() {
        return this.ballCounter.getValue();
    }

    /**
     * Gets block counter.
     *
     * @return the block counter
     */
    public int getBlockCounter() {
        return this.blockCounter.getValue();
    }
}
