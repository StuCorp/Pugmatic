package com.example.user.pugmatic;

import android.app.Activity;
import android.os.Bundle;
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
    Button addMoney;
    Button spin;
    Button quit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textBar = (TextView) findViewById(R.id.text_bar);
        wheel1RowTop = (ImageView) findViewById(R.id.wheel1_row_top);
        wheel2RowTop = (ImageView) findViewById(R.id.wheel2_row_top);
        wheel3RowTop = (ImageView) findViewById(R.id.wheel3_row_top);
        wheel1RowMid = (ImageView) findViewById(R.id.wheel1_row_mid);
        wheel2RowMid = (ImageView) findViewById(R.id.wheel2_row_mid);
        wheel3RowMid = (ImageView) findViewById(R.id.wheel3_row_mid);
        wheel1RowBot = (ImageView) findViewById(R.id.wheel1_row_bot);
        wheel2RowBot = (ImageView) findViewById(R.id.wheel2_row_bot);
        wheel3RowBot = (ImageView) findViewById(R.id.wheel3_row_bot);
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
        addMoney = (Button) findViewById(R.id.add_money_button);
        spin = (Button) findViewById(R.id.spin_button);
        quit = (Button) findViewById(R.id.quit_button);





    }
}
