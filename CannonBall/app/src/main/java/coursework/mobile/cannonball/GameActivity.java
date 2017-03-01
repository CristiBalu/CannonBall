package coursework.mobile.cannonball;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import coursework.mobile.cannonball.gui.MainView;
import coursework.mobile.cannonball.models.GameModel;


public class GameActivity extends AppCompatActivity {

    GameModel model;
    MainView view;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        view = new MainView(this);
        model = new GameModel();

        setContentView(view);
        updateScoreBoard();
    }

    private void updateScoreBoard() {
        view.updateScoreAndTime(model.getTime(), model.getScore());
    }
}
