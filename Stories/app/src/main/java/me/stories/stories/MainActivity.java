package me.stories.stories;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private ProgressBar progressBar;
    private Button button;
    private TextView storyText;
    private EditText newText;
    private String prevNewString;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        button = (Button) findViewById(R.id.submitButton);
        storyText = (TextView) findViewById(R.id.storyText);
        newText = (EditText) findViewById(R.id.newText);

        newText.addTextChangedListener(new TextWatcher() {

            boolean endText = false;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(s.toString().contains(" ")){
                    newText.setText(prevNewString);
                }else{
                    prevNewString = s.toString();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitText();
            }
        });


    }



    private void submitText() {
        String currentText = storyText.getText().toString();
        String toAdd = newText.getText().toString();

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
