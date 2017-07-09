package com.example.user.pugmatic;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class OptionsActivity extends Activity implements AdapterView.OnItemSelectedListener {

    ArrayList<String> availablePacks;
    Spinner spinner;
    int spinnerChoice;
    Switch switchWheel;
    int wheelChoice = 3;
    NumberPicker moneyPicker;
    int moneyAmount = 1;
    Button submitOptions;

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
                if (switched) {
                    wheelChoice = 5;
                } else {
                    wheelChoice = 3;
                }
                Toast.makeText(getApplicationContext(), "choice" + wheelChoice, Toast.LENGTH_SHORT).show();
            }
        };
        //set the listener on the switch - handles the listening
        switchWheel.setOnCheckedChangeListener(checkListener);


        //USER MONEY NUMBER PICKER
        //set moneypicker to widget id
        moneyPicker = (NumberPicker) findViewById(R.id.money_picker);
        moneyPicker.setMinValue(1);
        moneyPicker.setMaxValue(50);

        //set up value change listener
        NumberPicker.OnValueChangeListener valueChangeListener = new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                Toast.makeText(getApplicationContext(), "£" + newValue, Toast.LENGTH_SHORT);
                moneyAmount = newValue;
                Log.d("Number Picker", "picked" + newValue);
            }
        };
        //set the listener on the number picker
        moneyPicker.setOnValueChangedListener(valueChangeListener);


        //SUBMIT OPTIONS BUTTON
        submitOptions = (Button) findViewById(R.id.submit_options_button);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int arrayIndex, long l) {
        TextView choice = (TextView) view;
        //get string from textview
        spinnerChoice = arrayIndex;
        String packChoice = choice.getText().toString();
        Toast.makeText(this, "You selected " + packChoice, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void whenSubmitButtonClicked(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("pack", spinnerChoice);
        intent.putExtra("wheelsNum", wheelChoice);
        intent.putExtra("walletMoney", moneyAmount);
        startActivity(intent);
    }

}
