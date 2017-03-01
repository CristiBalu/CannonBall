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
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import coursework.mobile.cannonball.Constants;
import coursework.mobile.cannonball.R;

import static coursework.mobile.cannonball.Constants.*;

/**
 * Created by 650016706 on 23/02/2017.
 */

public class MainView extends SurfaceView implements SurfaceHolder.Callback {
    private Integer score;
    private Integer time;
    private double cannonAngle;
    private double canvasCenterBottomX;
    private double canvasCenterBottomY;
    private int ballX;
    private int ballY;
    private Bitmap cannonBitmap;
    private Canvas canvas;
    private StaticThread thread;

    private class StaticThread extends Thread {
        private boolean running = false;
        private SurfaceHolder holder;

        public StaticThread(SurfaceHolder holder) {
            this.holder = holder;
        }

        @Override
        public void run() {
            while(isRunning()) {
                if(holder.getSurface().isValid()) {
                    Canvas canvas = holder.lockCanvas();
                    doDraw(canvas);
                    holder.unlockCanvasAndPost(canvas);
                }
            }
        }

        public boolean isRunning() {
            return running;
        }

        public void setRunning(boolean running) {
            this.running = running;
        }
    }

    public MainView(Context context) {
        super(context);

        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        setupUi();
    }

    public MainView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        SurfaceHolder holder = getHolder();
        holder.addCallback(this);

        setupUi();
    }

    protected void doDraw(Canvas canvas) {
        this.canvas = canvas;

        canvasCenterBottomX = (double) (canvas.getWidth() / 2);
        canvasCenterBottomY = (double) canvas.getHeight();
        drawBackground();
        drawScore();
        drawTime();
        drawCannon();
        drawBall();
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch(e.getAction()) {
        case MotionEvent.ACTION_DOWN:
            final double touchX = e.getX();
            final double touchY = e.getY();
            final double m1 = Math.atan((touchX - canvasCenterBottomX) / (touchY - canvasCenterBottomY));

            cannonAngle = -m1 * 180 / Math.PI;
            break;
        default:
            break;
        }
        return true;
    }

    public void updateScoreAndTime(Integer time, Integer score) {
        setTime(time);
        setScore(score);
    }

    private void drawBackground() {
        Paint paint = new Paint();
        paint.setColor(BACKGROUND_COLOR);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(new Rect(0, 0, canvas.getWidth(), canvas.getHeight()), paint);
    }

    private void drawBall() {
        Paint paint = new Paint();
        paint.setAntiAlias(false);
        paint.setFilterBitmap(false);

        Bitmap ball = BitmapFactory.decodeResource(getResources(), R.mipmap.ball);

        canvas.drawBitmap(rotatedScaledBitmap(ball, 0.0, BALL_SIZE, BALL_SIZE), ballX, ballY, paint);
    }

    private void drawCannon() {
        Paint paint = new Paint();
        paint.setAntiAlias(false);
        paint.setFilterBitmap(false);

        canvas.drawBitmap(rotatedScaledBitmap(cannonBitmap, cannonAngle, CANNON_WIDTH, CANNON_HEIGHT),
                (canvas.getWidth() - CANNON_WIDTH) / 2, canvas.getHeight() - CANNON_HEIGHT, paint);
    }

    private void drawScore() {
        Paint paint = textPaint();
        canvas.drawText(SCORE_LABEL + score.toString(), SCORE_X + PADDING, SCORE_Y, paint);
    }

    private void drawTime() {
        Paint paint = textPaint();
        float textWidth = paint.measureText(TIME_LABEL + time.toString()) + PADDING;
        canvas.drawText(TIME_LABEL + time.toString(), canvas.getWidth() - textWidth, SCORE_Y, paint);
    }

    private Bitmap rotatedScaledBitmap(Bitmap src, double angle, int width, int height) {
        Matrix matrix = new Matrix();
        matrix.postRotate((float)angle);

        return Bitmap.createScaledBitmap(
                Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true),
                width, height, true);
    }

    private void setupUi() {
        score = new Integer(INITIAL_SCORE);
        time = new Integer(INITIAL_TIME);
        cannonAngle = DEFAULT_ANGLE;
        cannonBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.cannon_transparent);
        canvas = null;
        canvasCenterBottomX = 0.0;
        canvasCenterBottomY = 0.0;
        ballX = 3000;
        ballY = 3000;
    }

    private Paint textPaint() {
        Paint paint = new Paint();
        paint.setColor(TEXT_COLOR);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setTextSize(TEXT_SIZE);

        return paint;
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

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new StaticThread(holder);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;

        thread.setRunning(false);

        while(retry) {
            try {
                thread.join();
                retry = false;
            } catch(InterruptedException e) {}
        }
    }
}
