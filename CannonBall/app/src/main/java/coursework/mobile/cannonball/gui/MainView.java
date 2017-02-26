package coursework.mobile.cannonball.gui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import coursework.mobile.cannonball.R;

/**
 * Created by crist on 23/02/2017.
 */

public class MainView extends View {
    private static final int    TEXT_COLOR = Color.WHITE;
    private static final float  TEXT_SIZE = 60.0F;
    private static final String SCORE_LABEL = "Score: ";
    private static final String TIME_LABEL = "Time : ";
    private static final int    SCORE_X = 0;
    private static final int    SCORE_Y = 100;
    private static final int    PADDING = 40;
    private static final int    INITIAL_SCORE = 0;
    private static final int    INITIAL_TIME = 10;
    private static final int    CANNON_HEIGHT = 350;
    private static final int    CANNON_WIDTH  = 350;

    private int screenWidth;
    private int screenHeight;
    private Integer score;
    private Integer time;
    Canvas canvas;

    public MainView(Context context) {
        super(context);
        screenWidth = getWidth();
        screenHeight = getHeight();
        score = new Integer(INITIAL_SCORE);
        time = new Integer(INITIAL_TIME);
    }

    public MainView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        screenWidth = getWidth();
        screenHeight = getHeight();
        score = new Integer(INITIAL_SCORE);
        time = new Integer(INITIAL_TIME);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.canvas = canvas;
        drawScore();
        drawTime();
        drawCannon();
    }

    public void updateScoreAndTime(Integer time, Integer score) {
        setTime(time);
        setScore(score);
    }

    private void drawCannon() {
        Paint paint = new Paint();
        paint.setAntiAlias(false);
        paint.setFilterBitmap(false);
        Bitmap cannonBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.cannon_transparent);
        canvas.drawBitmap(Bitmap.createScaledBitmap(cannonBitmap, CANNON_WIDTH, CANNON_HEIGHT, true),
                (canvas.getWidth() - CANNON_WIDTH) / 2, canvas.getHeight() - CANNON_HEIGHT, paint);
    }

    private void drawScore() {
        Paint paint = new Paint();
        paint.setColor(TEXT_COLOR);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setTextSize(TEXT_SIZE);
        canvas.drawText(SCORE_LABEL + score.toString(), SCORE_X + PADDING, SCORE_Y, paint);
    }

    private void drawTime() {
        Paint paint = new Paint();
        paint.setColor(TEXT_COLOR);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setTextSize(TEXT_SIZE);
        float textWidth = paint.measureText(TIME_LABEL + time.toString()) + PADDING;
        canvas.drawText(TIME_LABEL + time.toString(), canvas.getWidth() - textWidth, SCORE_Y, paint);
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
