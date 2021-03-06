package com.example.user.pugmatic;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
    boolean resume = false;
    boolean previousSelection = false;
    int packChoice;
    int wheelsNum;
    int userMoney;
    MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        setContentView(R.layout.activity_options);

//        Intent intent = getIntent();
//        Bundle extras = intent.getExtras();
//
//            previousSelection = true;
//            packChoice = extras.getInt("pack");
//            wheelsNum = extras.getInt("wheelsNum");
//            userMoney = extras.getInt("walletMoney");
//


        //create options for spinner based on pack names
        availablePacks = new SymbolPackCollection().getNames();
//
//        availablePacks.add("FruitPack");
//        availablePacks.add("EmojiPeoplePack");

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
        switchWheel.setTextOff("3");
        switchWheel.setTextOn("5");
        switchWheel.setBackgroundColor(Color.rgb(255, 64, 169));
        switchWheel.setTextColor(Color.RED);
        //create a listener
        CompoundButton.OnCheckedChangeListener checkListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean switched) {
                if (switched) {
                    wheelChoice = 5;
                } else {
                    wheelChoice = 3;
                }
//                Toast.makeText(getApplicationContext(), "choice" + wheelChoice, Toast.LENGTH_SHORT).show();
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


        mp = MediaPlayer.create(this, R.raw.dog);


        //SUBMIT OPTIONS BUTTON
        submitOptions = (Button) findViewById(R.id.submit_options_button);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int arrayIndex, long l) {
        TextView choice = (TextView) view;
        //get string from textview
        ((TextView) adapterView.getChildAt(0)).setTextColor(Color.rgb(255, 223, 15));
//        ((TextView) adapterView.getChildAt(0)).setTextSize(5);
        spinnerChoice = arrayIndex;
//        String packChoice = choice.getText().toString();
//        Toast.makeText(this, "You selected " + packChoice, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void whenSubmitButtonClicked(View view){
        dogBark();
        this.resume = false;
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("resume", resume);
        intent.putExtra("pack", spinnerChoice);
        intent.putExtra("wheelsNum", wheelChoice);
        intent.putExtra("walletMoney", moneyAmount);
        startActivity(intent);
    }

    public void whenResumeButtonClicked(View view){
        dogBark();
        this.resume = true;
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("resume", resume);
        startActivity(intent);
    }


    public void dogBark(){

        mp.start();
    }
}
