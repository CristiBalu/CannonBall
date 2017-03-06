package coursework.mobile.cannonball.gui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import coursework.mobile.cannonball.utils.Vector2D;

import static coursework.mobile.cannonball.Constants.*;

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
        System.out.println(position.getX() + " " + position.getY());
        position.add(velocity);
        System.out.println(position.getX() + " " + position.getY());
        System.out.println("fmm");
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(position.getX(), position.getY(), BALL_SIZE, paint);
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }
}
