package coursework.mobile.cannonball.controllers;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import coursework.mobile.cannonball.gui.MainView;
import coursework.mobile.cannonball.models.GameModel;


public class GameActivity extends Activity {

    GameModel model;
    MainView view;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        view = new MainView(this);
        model = new GameModel();
        updateView();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(view);
    }

    private void updateView() {
        view.setModel(model);
    }
}
