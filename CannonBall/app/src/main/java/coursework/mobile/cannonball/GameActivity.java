package coursework.mobile.cannonball;

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
        setContentView(R.layout.game_layout);

        view = (MainView) findViewById(R.id.game_layout);
        model = new GameModel();
        updateScoreBoard();
    }

    private void updateScoreBoard() {
        view.updateScoreAndTime(model.getTime(), model.getScore());
    }
}
