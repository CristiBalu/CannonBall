package coursework.mobile.cannonball;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class LevelsActivity extends AppCompatActivity {

    AlertDialog instructionsDialog;
    AlertDialog authorDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels_layout);

        instructionsDialog = null;
        authorDialog = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.info_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            case R.id.menu_author_item:
                authorDialog = createDialogBuilder(getResources().getString(R.string.author),
                                                   getResources().getString(R.string.author_label))
                               .create();
                authorDialog.show();
                return true;
            case R.id.menu_howtoplay_item:
                authorDialog = createDialogBuilder(getResources().getString(R.string.how_to_play),
                                                   getResources().getString(R.string.htp_label))
                               .create();
                authorDialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    private AlertDialog.Builder createDialogBuilder(final String message, final String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(message);
        builder.setTitle(title);

        return builder;
    }
}
