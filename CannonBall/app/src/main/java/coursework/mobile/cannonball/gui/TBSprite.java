package coursework.mobile.cannonball.gui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import coursework.mobile.cannonball.utils.Vector2D;

import static coursework.mobile.cannonball.utils.Constants.TARGET_COLOR;
import static coursework.mobile.cannonball.utils.Constants.TARGET_PIECE_BORDER;

/**
 * Created by crist on 19/03/2017.
 */

public class TBSprite {
    private Vector2D position;
    private Vector2D velocity;
    private boolean  isBlocker;

    public TBSprite(Vector2D position, Vector2D velocity, boolean isBlocker) {
        setPosition(position);
        setVelocity(velocity);
        this.isBlocker = isBlocker;
    }

    public void update() {
        position.add(velocity);
    }

    public void draw(Canvas canvas, int level, int y, int width, int height) {
        int currX = (int)position.getX();

        for(int i = 0; i < (isBlocker ? 1 : level * 2); i++) {
            drawTargetPiece(canvas, currX, y, isBlocker ? width : width / (level * 2), height);
            currX += width / (level * 2);
        }
    }

    private void drawTargetPiece(Canvas canvas, int x, int y, int width, int height) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(new Rect(x, y, x + width, y + height), paint);

        paint.setColor(isBlocker ? Color.RED : TARGET_COLOR);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(new Rect(x + TARGET_PIECE_BORDER, y + TARGET_PIECE_BORDER,
                        x + width - TARGET_PIECE_BORDER, y + height - TARGET_PIECE_BORDER),
                paint);
    }

    public Vector2D getPosition() {
        return position;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }

    public void invertVelocityX() {
        velocity.setX(-velocity.getX());
    }
}
