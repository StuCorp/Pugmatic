package com.example.user.pugmatic;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    TextView textBar;
    ImageView wheel1RowTop;
    ImageView wheel2RowTop;
    ImageView wheel3RowTop;
    ImageView wheel1RowMid;
    ImageView wheel2RowMid;
    ImageView wheel3RowMid;
    ImageView wheel1RowBot;
    ImageView wheel2RowBot;
    ImageView wheel3RowBot;

    ImageView wheel4RowTop;
    ImageView wheel5RowTop;
    ImageView wheel4RowMid;
    ImageView wheel5RowMid;
    ImageView wheel4RowBot;
    ImageView wheel5RowBot;


    Button nudge1;
    Button nudge2;
    Button nudge3;
    Button nudge4;
    Button nudge5;

    Button hold1;
    Button hold2;
    Button hold3;
    Button hold4;
    Button hold5;


    TextView nudgeDisplay;
    TextView holdDisplay;
    TextView winningsDisplay;
    TextView creditDisplay;
    TextView walletDisplay;
    Button addMoney;
    Button spin;
    Button quit;
    int wheelsNum;

    Game game;
    String textMessage = "default";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//game setup
        int packChoice;
        int userMoney;


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras.getBoolean("resume")) {

            packChoice = SharedPreferencesGameState.getStoredInt(this, "symbolPackChoice");
            wheelsNum = SharedPreferencesGameState.getStoredInt(this, "wheelsNum");
            userMoney = SharedPreferencesGameState.getStoredInt(this, "userMoney");

        } else {
            packChoice = extras.getInt("pack");
            wheelsNum = extras.getInt("wheelsNum");
            userMoney = extras.getInt("walletMoney");
        }

        if (wheelsNum > 3) {
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        } else {
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        }

        Player player = new Player(userMoney);
        FruitPack fruitPack = new FruitPack();
        HatPack hatPack = new HatPack();
        WheelSet wheelSet = new WheelSet(packChoice, wheelsNum);
        Machine machine = new Machine(wheelSet);
        Viewer viewer = new Viewer(player, machine);
        game = new Game(player, machine, viewer);


        //fruitmachine text display
        textBar = (TextView) findViewById(R.id.text_bar);

//        drawing fruitmachine wheels
        wheel1RowTop = (ImageView) findViewById(R.id.wheel1_row_top);
        wheel2RowTop = (ImageView) findViewById(R.id.wheel2_row_top);
        wheel3RowTop = (ImageView) findViewById(R.id.wheel3_row_top);
        wheel1RowMid = (ImageView) findViewById(R.id.wheel1_row_mid);
        wheel2RowMid = (ImageView) findViewById(R.id.wheel2_row_mid);
        wheel3RowMid = (ImageView) findViewById(R.id.wheel3_row_mid);
        wheel1RowBot = (ImageView) findViewById(R.id.wheel1_row_bot);
        wheel2RowBot = (ImageView) findViewById(R.id.wheel2_row_bot);
        wheel3RowBot = (ImageView) findViewById(R.id.wheel3_row_bot);

        //extra wheels
        wheel4RowTop = (ImageView) findViewById(R.id.wheel4_row_top);
        wheel5RowTop = (ImageView) findViewById(R.id.wheel5_row_top);
        wheel4RowMid = (ImageView) findViewById(R.id.wheel4_row_mid);
        wheel5RowMid = (ImageView) findViewById(R.id.wheel5_row_mid);
        wheel4RowBot = (ImageView) findViewById(R.id.wheel4_row_bot);
        wheel5RowBot = (ImageView) findViewById(R.id.wheel5_row_bot);

        //nudge and hold buttons
        nudge1 = (Button) findViewById(R.id.nudge1);
        nudge2 = (Button) findViewById(R.id.nudge2);
        nudge3 = (Button) findViewById(R.id.nudge3);

        //extra nudges
        nudge4 = (Button) findViewById(R.id.nudge4);
        nudge5 = (Button) findViewById(R.id.nudge5);

        hold1 = (Button) findViewById(R.id.hold1);
        hold2 = (Button) findViewById(R.id.hold2);
        hold3 = (Button) findViewById(R.id.hold3);

        //extra holds
        hold4 = (Button) findViewById(R.id.hold4);
        hold5 = (Button) findViewById(R.id.hold5);


        nudgeDisplay = (TextView) findViewById(R.id.nudge_display);
        holdDisplay = (TextView) findViewById(R.id.hold_display);
        winningsDisplay = (TextView) findViewById(R.id.winnings_display);
        creditDisplay = (TextView) findViewById(R.id.credit_display);
        walletDisplay = (TextView) findViewById(R.id.wallet_display);


        addMoney = (Button) findViewById(R.id.add_money_button);
        spin = (Button) findViewById(R.id.spin_button);
        quit = (Button) findViewById(R.id.return_button);

        refreshDisplay();

    }

    @Override
    protected void onStop() {
        super.onStop();
        int userMoney = game.player.getMoneyAmount();
        int machineCredit = game.machine.getUserMoney();
        int userWinnings = game.machine.getPayOutTracker();
        int symbolPackChoice = game.machine.getPackNum();
        int wheelsNum = game.machine.getWheelsNum();
        SharedPreferencesGameState.setStoredInt(this, "userMoney", userMoney);
        SharedPreferencesGameState.setStoredInt(this, "machineCredit", machineCredit);
        SharedPreferencesGameState.setStoredInt(this, "userWinnings", userWinnings);
        SharedPreferencesGameState.setStoredInt(this, "symbolPackChoice", symbolPackChoice);
        SharedPreferencesGameState.setStoredInt(this, "wheelsNum", wheelsNum);
//        int winnings = SharedPreferencesGameState.getStoredInt(this, "userWinnings");

    }

    public void whenAddMoneyClicked(View view) {
        Log.d("Pugmatic", "money added");
        if (game.player.hasMoney()) {
            game.addMoney(1);
        } else {
            Toast.makeText(this, "you have no money in your wallet", Toast.LENGTH_SHORT).show();
        }
        refreshDisplay();

    }

    public void whenSpinClicked(View view) {
        Log.d("Pugmatic", "spin clikced");
        if (game.moneyInMachine()) {
            game.pull();
            Log.d("Pugmatic", "pull happening");
            refreshDisplay();
            if (game.machine.checkForWin()) {
                Log.d("PUGMATIC", "win");
                game.winScenario();
                refreshDisplay();
            } else {
                game.calculateNudgesAndHolds();
                refreshDisplay();

            }
        }
    }


    public void whenNudge1Clicked(View view) {
        Log.d("Pugmatic", "nudge1 clicked");
        if (game.isNudges()) {
            game.doNudge(0);
            refreshDisplay();
            if (game.machine.checkForWin()) {
                game.winScenario();
                refreshDisplay();
            }
        }
    }

    public void whenNudge2Clicked(View view) {
        Log.d("Pugmatic", "nudge2 clicked");
        if (game.isNudges()) {
            game.doNudge(1);
            refreshDisplay();
            if (game.machine.checkForWin()) {
                game.winScenario();
                refreshDisplay();
            }
        }
    }


    public void whenNudge3Clicked(View view) {
        Log.d("Pugmatic", "nudge3 clicked");
        if (game.isNudges()) {
            game.doNudge(2);
            refreshDisplay();
            if (game.machine.checkForWin()) {
                game.winScenario();
                refreshDisplay();

            }
        }
    }


    public void whenNudge4Clicked(View view) {
        Log.d("Pugmatic", "nudge4 clicked");
        if (game.isNudges()) {
            game.doNudge(3);
            refreshDisplay();
            if (game.machine.checkForWin()) {
                game.winScenario();
                refreshDisplay();

            }
        }
    }


    public void whenNudge5Clicked(View view) {
        Log.d("Pugmatic", "nudge2 clicked");
        if (game.isNudges()) {
            game.doNudge(4);
            refreshDisplay();
            if (game.machine.checkForWin()) {
                game.winScenario();
                refreshDisplay();

            }
        }
    }



    public void whenHold1Clicked(View view) {
        Log.d("Pugmatic", "hold1 clicked");
        if (game.machine.getWheels().get(0).isHoldOn()) {
            game.machine.getWheels().get(0).setHoldOnOff();
            game.machine.addHold();
        } else if (game.isHolds()) {
            game.machine.getWheels().get(0).setHoldOnOn();
            game.machine.deductHold();
        }
        refreshDisplay();
    }

    public void whenHold2Clicked(View view) {
        Log.d("Pugmatic", "hold2 clicked");
        if (game.machine.getWheels().get(1).isHoldOn()) {
            game.machine.getWheels().get(1).setHoldOnOff();
            game.machine.addHold();
        } else if (game.isHolds()) {
            game.machine.getWheels().get(1).setHoldOnOn();
            game.machine.deductHold();
        }
        refreshDisplay();
    }

    public void whenHold3Clicked(View view) {
        Log.d("Pugmatic", "hold3 clicked");
        if (game.machine.getWheels().get(2).isHoldOn()) {
            game.machine.getWheels().get(2).setHoldOnOff();
            game.machine.addHold();
        } else if (game.isHolds()) {
            game.machine.getWheels().get(2).setHoldOnOn();
            game.machine.deductHold();
        }
        refreshDisplay();
    }

    public void whenHold4Clicked(View view) {
        Log.d("Pugmatic", "hold4 clicked");
        if (game.machine.getWheels().get(3).isHoldOn()) {
            game.machine.getWheels().get(3).setHoldOnOff();
            game.machine.addHold();
        } else if (game.isHolds()) {
            game.machine.getWheels().get(3).setHoldOnOn();
            game.machine.deductHold();
        }
        refreshDisplay();
    }

    public void whenHold5Clicked(View view) {
        Log.d("Pugmatic", "hold5 clicked");
        if (game.machine.getWheels().get(4).isHoldOn()) {
            game.machine.getWheels().get(4).setHoldOnOff();
            game.machine.addHold();
        } else if (game.isHolds()) {
            game.machine.getWheels().get(4).setHoldOnOn();
            game.machine.deductHold();
        }
        refreshDisplay();
    }


    public void refreshDisplay() {

        wheel1RowTop.setImageResource(game.getWheels().get(0).getLastFruit().getImage());
        wheel2RowTop.setImageResource(game.getWheels().get(1).getLastFruit().getImage());
        wheel3RowTop.setImageResource(game.getWheels().get(2).getLastFruit().getImage());
        wheel1RowMid.setImageResource(game.getWheels().get(0).getCurrentFruit().getImage());
        wheel2RowMid.setImageResource(game.getWheels().get(1).getCurrentFruit().getImage());
        wheel3RowMid.setImageResource(game.getWheels().get(2).getCurrentFruit().getImage());
        wheel1RowBot.setImageResource(game.getWheels().get(0).getNextFruit().getImage());
        wheel2RowBot.setImageResource(game.getWheels().get(1).getNextFruit().getImage());
        wheel3RowBot.setImageResource(game.getWheels().get(2).getNextFruit().getImage());

        if (wheelsNum > 3) {
            wheel4RowTop.setImageResource(game.getWheels().get(3).getLastFruit().getImage());
            wheel5RowTop.setImageResource(game.getWheels().get(4).getLastFruit().getImage());
            wheel4RowMid.setImageResource(game.getWheels().get(3).getCurrentFruit().getImage());
            wheel5RowMid.setImageResource(game.getWheels().get(4).getCurrentFruit().getImage());
            wheel4RowBot.setImageResource(game.getWheels().get(3).getNextFruit().getImage());
            wheel5RowBot.setImageResource(game.getWheels().get(4).getNextFruit().getImage());

        } else {
            wheel4RowTop.setVisibility(View.GONE);
            wheel5RowTop.setVisibility(View.GONE);
            wheel4RowMid.setVisibility(View.GONE);
            wheel5RowMid.setVisibility(View.GONE);
            wheel4RowBot.setVisibility(View.GONE);
            wheel5RowBot.setVisibility(View.GONE);

            nudge4.setVisibility(View.GONE);
            nudge5.setVisibility(View.GONE);

            hold4.setVisibility(View.GONE);
            hold5.setVisibility(View.GONE);
        }

        nudgeDisplay.setText("Nudges: " + game.machine.getNudges().toString());
        holdDisplay.setText("Holds: " + game.machine.getHoldsNum().toString());
        creditDisplay.setText("Credits: " + game.machine.getUserMoney().toString());
        winningsDisplay.setText("Winnning: " + game.machine.getPayOutTracker().toString());
        walletDisplay.setText("Wallet: " + game.player.getMoneyAmount().toString());
        textBar.setText(textMessage);

    }

    public void whenOptionsButtonClicked(View view) {
        Intent intent = new Intent(this, OptionsActivity.class);
        startActivity(intent);
    }


}



