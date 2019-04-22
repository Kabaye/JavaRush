package by.bsu.kulich.game.elements.entity;

import by.bsu.kulich.game.elements.Pausable;
import lombok.Getter;
import lombok.NonNull;

import static by.bsu.kulich.game.elements.view.View.REAL_LEFT_WINDOW_BOUND;
import static by.bsu.kulich.game.elements.view.View.REAL_RIGHT_WINDOW_BOUND;

public class Paddle extends Rectangle implements Pausable {

    private static final double PADDLE_WIDTH = 110.0;
    private static final double PADDLE_HEIGHT = 15.0;
    private static final double PADDLE_SIMPLE_STEP = 1.0;

    private double velocityValue = 0.45;
    private double velocity = 0.0;

    @Getter
    private GameDifficultyLevel gameDifficultyLevel;

    private int direction;
    private boolean pause;

    public Paddle(double x, double y, @NonNull GameDifficultyLevel difficultyLevel) {
        super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        this.gameDifficultyLevel = difficultyLevel;
        this.setGameDifficultyLevel(difficultyLevel);
        start();
    }

    public void update() {
        if (!pause) {
            if (left() < REAL_LEFT_WINDOW_BOUND && velocity < 0.0)
                velocity = 0.0;
            if (right() > REAL_RIGHT_WINDOW_BOUND && velocity > 0.0)
                velocity = 0.0;
            this.setX(this.getX() + velocity * PADDLE_SIMPLE_STEP);
        }
    }

    public void setGameDifficultyLevel(GameDifficultyLevel gameDifficultyLevel) {
        this.gameDifficultyLevel = gameDifficultyLevel;
        switch (this.gameDifficultyLevel) {
            case LIGHT:
                this.setSizeX(PADDLE_WIDTH);
                break;
            case MEDIUM:
                this.setSizeX(PADDLE_WIDTH - 15.0);
                break;
            case HARD:
                this.setSizeX(PADDLE_WIDTH - 30.0);
                this.velocityValue = 0.7;
                break;
            case VERY_HARD:
                this.setSizeX(PADDLE_WIDTH - 45.0);
                this.velocityValue = 0.6;
                break;
            case YOU_ARE_GOD:
                this.setSizeX(PADDLE_WIDTH - 60.0);
                this.velocityValue = 0.5;
                break;
        }
    }

    public void stopMove() {
        this.velocity = 0.0;
    }

    public void moveLeft() {
        this.velocity = -this.velocityValue;
    }

    public void moveRight() {
        this.velocity = this.velocityValue;
    }

    @Override
    public void start() {
        velocity = 0.0;
        pause = false;
    }

    @Override
    public void continueGame() {
        pause = false;
    }

    @Override
    public void pause() {
        pause = true;
    }
}