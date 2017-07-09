package com.example.user.pugmatic;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class OptionsActivity extends Activity implements AdapterView.OnItemSelectedListener {

    ArrayList<String> availablePacks;
    Spinner spinner;
    String spinnerChoice;
    Switch switchWheel;
    int wheelChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        //create options for spinner based on pack names
        availablePacks = new ArrayList<>();
        availablePacks.add("FruitPack");
        availablePacks.add("EmojiPeoplePack");

        //SYMBOLPACK SPINNER
        //find the spinner view and assign to variable
        spinner = (Spinner) findViewById(R.id.packs_spinner);
        //create a new adapter, which will take the array data and a layout file and create the view
        //differs whether you want to create array from resources or from java array
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, availablePacks);
        //for custom item layout use:
//        adapter.setDropDownViewResource();
        //set the adapter on the spinner
        spinner.setAdapter(adapter);
        //telling the spinner who is responsible for handling selections: this activity is.
        spinner.setOnItemSelectedListener(this);


        //WHEEL NUMBER SWITCH
        //Find the switch
        switchWheel = (Switch) findViewById(R.id.wheel_num_switch);
        //create a listener
        CompoundButton.OnCheckedChangeListener checkListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean switched) {
                if(switched){
                    wheelChoice = 5;
                } else{
                    wheelChoice = 3;
                }
                Toast.makeText(getApplicationContext(), "choice" + wheelChoice, Toast.LENGTH_SHORT).show();
            }
        };
        //set the listener on the switch - handles the listening
        switchWheel.setOnCheckedChangeListener(checkListener);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView choice = (TextView) view;
        //get string from textview
        spinnerChoice = choice.getText().toString();
        Toast.makeText(this, "You selected " + spinnerChoice, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
