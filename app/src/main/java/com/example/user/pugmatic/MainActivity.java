package com.example.user.pugmatic;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
    Button nudge1;
    Button nudge2;
    Button nudge3;
    Button hold1;
    Button hold2;
    Button hold3;
    TextView nudgeDisplay;
    TextView holdDisplay;
    TextView winningsDisplay;
    TextView creditDisplay;
    TextView walletDisplay;
    Button addMoney;
    Button spin;
    Button quit;

    Game game;
    String textMessage = "default";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//game setup
        Player player = new Player(20);
        FruitPack fruitPack = new FruitPack();
        HatPack hatPack = new HatPack();
        WheelSet wheelSet = new WheelSet(fruitPack, 3);
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

        //nudge and hold buttons
        nudge1 = (Button) findViewById(R.id.nudge1);
        nudge2 = (Button) findViewById(R.id.nudge2);
        nudge3 = (Button) findViewById(R.id.nudge3);
        hold1 = (Button) findViewById(R.id.hold1);
        hold2 = (Button) findViewById(R.id.hold2);
        hold3 = (Button) findViewById(R.id.hold3);

        nudgeDisplay = (TextView) findViewById(R.id.nudge_display);
        holdDisplay = (TextView) findViewById(R.id.hold_display);
        winningsDisplay = (TextView) findViewById(R.id.winnings_display);
        creditDisplay = (TextView) findViewById(R.id.credit_display);
        walletDisplay = (TextView) findViewById(R.id.wallet_display);


        addMoney = (Button) findViewById(R.id.add_money_button);
        spin = (Button) findViewById(R.id.spin_button);
        quit = (Button) findViewById(R.id.quit_button);

        refreshDisplay();

//        game.run();
    }

    public void whenAddMoneyClicked(View view) {
        Log.d("Pugmatic", "money added");
        game.addMoney(1);
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
        Log.d("Pugmatic", "nudge2 clicked");
        if (game.isNudges()) {
            game.doNudge(2);
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

        nudgeDisplay.setText("Nudges: " + game.machine.getNudges().toString());
        holdDisplay.setText("Holds: " + game.machine.getHoldsNum().toString());
        creditDisplay.setText("Credits: " + game.machine.getUserMoney().toString());
        winningsDisplay.setText("Winnning: " + game.machine.getPayOutTracker().toString());
        walletDisplay.setText("Wallet: " + game.player.getMoneyAmount().toString());
        textBar.setText(textMessage);

    }


}



