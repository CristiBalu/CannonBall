package coursework.mobile.cannonball.utils;

/**
 * Created by 650016706 on 01/03/2017.
 */

public class Vector2D {
    private float x;
    private float y;

    public Vector2D(float x, float y) {
        setX(x);
        setY(y);
    }

    public Vector2D() {
        setX(0.0F);
        setY(0.0F);
    }

    public void add(Vector2D v) {
        this.x += v.x;
        this.y += v.y;
    }

    public void wrap(float width, float height) {
        this.x = (x + width) % width;
        this.y = (y + height) % height;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
