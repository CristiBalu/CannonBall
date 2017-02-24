package coursework.mobile.cannonball.models;

/**
 * Created by 650016706 on 24/02/2017.
 */

public class GameModel {
    private static final int INITIAL_SCORE = 0;
    private static final int INITIAL_TIME = 10;

    private int score;
    private int time;

    public GameModel() {
        setScore(INITIAL_SCORE);
        setTime(INITIAL_TIME);
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
}
