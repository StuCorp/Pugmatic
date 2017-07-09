package com.example.user.pugmatic;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class OptionsActivity extends Activity implements AdapterView.OnItemSelectedListener {

    ArrayList<String> availablePacks;
    Spinner spinner;
    String spinnerChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        //create options for spinner based on pack names
        availablePacks = new ArrayList<>();
        availablePacks.add("FruitPack");
        availablePacks.add("EmojiPeoplePack");

        //find the spinner view and assign to variable
        spinner = (Spinner) findViewById(R.id.packsSpinner);
        //create a new adapter, which will take the array data and a layout file and create the view
        //differs whether you want to create array from resources or from java array
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, availablePacks);
        //for custom item layout use:
//        adapter.setDropDownViewResource();
        //set the adapter on the spinner
        spinner.setAdapter(adapter);
        //telling the spinner who is responsible for handling selections: this activity is.
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView choice = (TextView) view;
        //get string from textview
        spinnerChoice = choice.getText().toString();
        Toast.makeText(this, "You selected " + spinnerChoice, Toast.LENGTH_SHORT);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
