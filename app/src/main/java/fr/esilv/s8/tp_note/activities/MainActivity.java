package fr.esilv.s8.tp_note.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import fr.esilv.s8.tp_note.R;


public class MainActivity extends AppCompatActivity {

    private static final String RESEARCH = "RESEARCH";
    private EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search = (EditText) findViewById(R.id.search);
    }

    public void NextPage (View v) {
        Intent intent = new Intent(MainActivity.this, ItemsActivity.class);
        intent.putExtra(RESEARCH,search.getText().toString());
        startActivity(intent);
    }
}

