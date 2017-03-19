package coursework.mobile.cannonball.models;

import static coursework.mobile.cannonball.utils.Constants.*;

/**
 * Created by 650016706 on 24/02/2017.
 */

public class GameModel {

    private int    score;
    private int    time;
    private float  ballSize;
    private double ballVelX;
    private double ballVelY;

    public GameModel() {
        setScore(INITIAL_SCORE);
        setTime(INITIAL_TIME);
        setBallSize(BALL_SIZE);
        setBallVelX(BALL_VEL_X);
        setBallVelY(BALL_VEL_Y);
    }
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public float getBallSize() {
        return ballSize;
    }

    public void setBallSize(float ballSize) {
        this.ballSize = ballSize;
    }

    public double getBallVelX() {
        return ballVelX;
    }

    public void setBallVelX(double ballVelX) {
        this.ballVelX = ballVelX;
    }

    public double getBallVelY() {
        return ballVelY;
    }

    public void setBallVelY(double ballVelY) {
        this.ballVelY = ballVelY;
    }
}
