package coursework.mobile.cannonball.gui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import coursework.mobile.cannonball.utils.Vector2D;

import static coursework.mobile.cannonball.utils.Constants.*;

/**
 * Created by 650016706 on 02/03/2017.
 */

public class BallSprite {
    private Vector2D position;
    private Vector2D velocity;

    public BallSprite(Vector2D position, Vector2D velocity) {
        setPosition(position);
        setVelocity(velocity);
    }

    public void update() {
        position.add(velocity);
    }

    public void draw(Canvas canvas, float ballSize) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(position.getX(), position.getY(), ballSize, paint);
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }
}
