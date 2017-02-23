package coursework.mobile.cannonball;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import coursework.mobile.cannonball.views.MainView;

public class LevelsActivity extends AppCompatActivity {

    private MainView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new MainView(this);
        setContentView(view);
    }
}
