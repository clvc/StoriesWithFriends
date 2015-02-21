package me.stories.stories;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.couchbase.lite.*;
import com.couchbase.lite.android.AndroidContext;
import com.couchbase.lite.util.Log;

import org.codehaus.jackson.map.deser.SettableBeanProperty;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    private ProgressBar progressBar;
    private Button button;
    private TextView storyText;
    private EditText newText;
    private Manager manager;
    private Database database;
    private String prevNewString;



    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        //AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        //AppEventsLogger.deactivateApp(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createManger();
        initDatabase();


        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        button = (Button) findViewById(R.id.submitButton);
        storyText = (TextView) findViewById(R.id.storyText);
        newText = (EditText) findViewById(R.id.newText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitText();
            }
        });


    }

    private void initDatabase() {
        // create a name for the database and make sure the name is legal
        String dbname = "hello";
        if (!Manager.isValidDatabaseName(dbname)) {
            return;
        }
// create a new database

        try {
            database = manager.getDatabase(dbname);
        } catch (CouchbaseLiteException e) {
            Toast.makeText(this,"DATABASE FUCKING FAILED", Toast.LENGTH_SHORT );
            return;
        }

    }


    private void createManger(){

        try {
            manager = new Manager(new AndroidContext(this), Manager.DEFAULT_OPTIONS);
            Log.d("TAGGGGGG", "Manager created");
        } catch (IOException e) {
            Log.e("TAGGGGGG", "Cannot create manager object");
            e.printStackTrace();
            Toast.makeText(this,"MANAGER FUCKING FAILED", Toast.LENGTH_SHORT );
        }

    }

    private void submitText() {
        String currentText = storyText.getText().toString();
        String toAdd = newText.getText().toString().split(" ")[0];
        newText.setText("");

        toAdd = toAdd.split(" ")[0];
        currentText += " " + toAdd;

        //TODO add sweet animation
        storyText.setText(currentText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
