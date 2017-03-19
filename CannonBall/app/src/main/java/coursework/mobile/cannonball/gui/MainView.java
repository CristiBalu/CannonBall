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
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.concurrent.CopyOnWriteArrayList;

import coursework.mobile.cannonball.R;
import coursework.mobile.cannonball.models.GameModel;
import coursework.mobile.cannonball.utils.Vector2D;

import static coursework.mobile.cannonball.utils.Constants.*;

/**
 * Created by 650016706 on 23/02/2017.
 */

public class MainView extends SurfaceView implements SurfaceHolder.Callback {

    private GameModel model;
    private Integer score;
    private Integer time;
    private double cannonAngle;
    private double canvasCenterBottomX;
    private double canvasCenterBottomY;
    private Bitmap cannonBitmap;
    private TargetSprite targetSprite;
    private Canvas canvas;
    private StaticThread thread;
    private CopyOnWriteArrayList<BallSprite> balls;

    private class StaticThread extends Thread {
        boolean running = false;
        private SurfaceHolder holder;

        public StaticThread(SurfaceHolder holder) {
            this.holder = holder;
        }

        @Override
        public void run() {
            while(isRunning()) {
                if(holder.getSurface().isValid()) {
                    Canvas canvas = null;
                    updateBalls();

                    if(targetNearEdge()) {
                        targetSprite.invertVelocityX();
                    }

                    targetSprite.update();
                    try {
                        canvas = holder.lockCanvas();
                        synchronized (holder) {
                            postInvalidate();
                        }
                    } finally {
                        if (canvas != null) {
                            holder.unlockCanvasAndPost(canvas);
                        }
                    }
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

    @Override
    protected void onDraw(Canvas canvas) {
        this.canvas = canvas;

        canvasCenterBottomX = (double) (canvas.getWidth() / 2);
        canvasCenterBottomY = (double) canvas.getHeight();

        drawBackground();
        drawScore();
        drawTime();
        drawCannon();
        drawBalls();
        targetSprite.draw(canvas, model.getLevel(), 150, TARGET_WIDTH, TARGET_HEIGHT);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch(e.getAction()) {
        case MotionEvent.ACTION_DOWN:
            final double touchX = e.getX();
            final double touchY = e.getY();
            final double m1 = Math.atan((touchX - canvasCenterBottomX) / (touchY - canvasCenterBottomY));

            cannonAngle = -m1 * 180 / Math.PI;

            final double velX = model.getBallVelX() * Math.sin(-m1);
            final double velY = model.getBallVelY() * Math.cos(-m1);
            balls.add(new BallSprite(new Vector2D((float)canvasCenterBottomX, (float)canvasCenterBottomY),
                                     new Vector2D((float) velX,(float)velY)));

            break;
        default:
            break;
        }
        return true;
    }

    public void setModel(GameModel model) {
        this.model = model;
        updateElements();
    }

    private boolean targetNearEdge() {
        if (canvas != null)
            return ((canvas.getWidth() - (int) targetSprite.getPosition().getX() - TARGET_WIDTH) < 15) ||
               (((int) targetSprite.getPosition().getX()) < 15);
        return false;
    }

    private void updateElements() {
        this.score = model.getScore();
        this.time  = model.getTime();
        targetSprite.getVelocity().add(new Vector2D(model.getLevel() * LEVEL_SPEED_ADD, 0.0F));
    }

    private void updateBalls() {
        for(int i = 0; i < balls.size(); i++) {
            balls.get(i).update();
        }
    }

    private void drawBalls() {
        for(int i = 0; i < balls.size(); i++) {
            balls.get(i).draw(canvas, model.getBallSize());
        }
    }

    private void drawBackground() {
        Paint paint = new Paint();
        paint.setColor(BACKGROUND_COLOR);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(new Rect(0, 0, canvas.getWidth(), canvas.getHeight()), paint);
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
        targetSprite = new TargetSprite(new Vector2D(TARGET_INITIAL_X, TARGET_Y),
                                        new Vector2D(BASE_TARGET_SPEED, 0.0F));
        canvas = null;
        canvasCenterBottomX = 0.0;
        canvasCenterBottomY = 0.0;
        balls = new CopyOnWriteArrayList<>();
    }

    private Paint textPaint() {
        Paint paint = new Paint();
        paint.setColor(TEXT_COLOR);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setTextSize(TEXT_SIZE);

        return paint;
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        setWillNotDraw(false);
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
