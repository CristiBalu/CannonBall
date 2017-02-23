package coursework.mobile.cannonball.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by crist on 23/02/2017.
 */

public class MainView extends View {
    private static final int BACKGROUND_COLOR = Color.GREEN;
    private int screenWidth;
    private int screenHeight;

    public MainView(Context context) {
        super(context);
        screenWidth = getWidth();
        screenHeight = getHeight();
    }

    public MainView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        screenWidth = getWidth();
        screenHeight = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(BACKGROUND_COLOR);

        final int offset = 10;
        canvas.drawRect(offset, offset, screenWidth - offset * 2, screenHeight - offset * 2, paint);
    }
}
