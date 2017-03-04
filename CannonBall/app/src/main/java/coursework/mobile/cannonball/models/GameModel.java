package coursework.mobile.cannonball.models;

import static coursework.mobile.cannonball.Constants.*;

/**
 * Created by 650016706 on 24/02/2017.
 */

public class GameModel {

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
