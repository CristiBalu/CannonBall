package coursework.mobile.cannonball.utils;

import android.graphics.Color;

/**
 * Created by 650016706 on 28/02/2017.
 */

public interface Constants {
    int    TEXT_COLOR = Color.WHITE;
    float  TEXT_SIZE = 60.0F;
    String SCORE_LABEL = "Score: ";
    String TIME_LABEL = "Time : ";
    int    SCORE_X = 0;
    int    SCORE_Y = 100;
    int    PADDING = 40;
    int    INITIAL_SCORE = 0;
    int    INITIAL_TIME = 10;
    int    CANNON_HEIGHT = 350;
    int    CANNON_WIDTH  = 350;
    double DEFAULT_ANGLE = 0.0F;
    int    BACKGROUND_COLOR = Color.parseColor("#006400");
    float  BALL_SIZE = 50.0F;
    double BALL_VEL_X = 30.0;
    double BALL_VEL_Y = -30.0F;
}
