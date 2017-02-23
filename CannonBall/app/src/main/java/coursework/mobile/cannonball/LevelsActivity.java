package coursework.mobile.cannonball;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LevelsActivity extends AppCompatActivity {
    private static final int FIRST_LEVEL_NO = 1;
    private static final int SECOND_LEVEL   = 2;
    private static final int THIRD_LEVEL    = 3;
    private static final int FOURTH_LEVEL   = 4;
    private static final int LAST_LEVEL_NO   = 5;

    private Button levelOneButton;
    private Button levelTwoButton;
    private Button levelThreeButton;
    private Button levelFourButton;
    private Button levelFiveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.levels_layout);
        setupUi();
    }

    private void setupUi() {
        levelOneButton   = (Button) findViewById(R.id.first_level_button);
        levelTwoButton   = (Button) findViewById(R.id.second_level_button);
        levelThreeButton = (Button) findViewById(R.id.third_level_button);
        levelFourButton  = (Button) findViewById(R.id.fourth_level_button);
        levelFiveButton  = (Button) findViewById(R.id.fifth_level_button);

        levelOneButton.setOnClickListener(new LevelButtonsListener(FIRST_LEVEL_NO));
        levelTwoButton.setOnClickListener(new LevelButtonsListener(SECOND_LEVEL));
        levelThreeButton.setOnClickListener(new LevelButtonsListener(THIRD_LEVEL));
        levelFourButton.setOnClickListener(new LevelButtonsListener(FOURTH_LEVEL));
        levelFiveButton.setOnClickListener(new LevelButtonsListener(LAST_LEVEL_NO));
    }

    private class LevelButtonsListener implements Button.OnClickListener{

        private int buttonIndex;

        public LevelButtonsListener(int buttonIndex) {
            if(buttonIndex > LAST_LEVEL_NO || buttonIndex < FIRST_LEVEL_NO) {
                this.buttonIndex = FIRST_LEVEL_NO;
            } else {
                this.buttonIndex = buttonIndex;
            }
        }

        @Override
        public void onClick(View v) {

        }
    }
}
