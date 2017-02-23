package utils;

/**
 * Created by 650016706 on 23/02/2017.
 */

public class Vector2D {
    private float x;
    private float y;

    public Vector2D() {
        setX(0.0F);
        setY(0.0F);
    }

    public Vector2D(final float x, final float y) {
        this.setX(x);
        this.setY(y);
    }

    public void add(Vector2D u){
        this.x += u.x;
        this.y += u.y;
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
