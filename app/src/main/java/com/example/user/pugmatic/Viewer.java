package com.example.user.pugmatic;

import java.util.ArrayList;

import static java.util.Arrays.asList;

/**
 * Created by stuartbryce on 2017-06-30.
 */

public class Viewer {

    Player player;
    Machine machine;
    ArrayList<Wheel> wheels;
    ArrayList<String> topRowLever;
    ArrayList<String> midRowLever;
    ArrayList<String> botRowLever;
    String topLever;
    String midLever;
    String botLever;

    public Viewer(Player player, Machine machine) {
        this.player = player;
        this.machine = machine;
        this.wheels = machine.getWheels();
        topRowLever = new ArrayList<>(asList((String) " (___)", "", "" ));
        midRowLever = new ArrayList<>(asList((String) " / /", "(___)", "" ));
        botRowLever = new ArrayList<>(asList((String) "/ /", "/ /", " (___)" ));
        topLever = topRowLever.get(0);
        midLever = midRowLever.get(0);
        botLever = botRowLever.get(0);
    }

    public void printFruitAction() {
        for (int i = 0; i < 3; i++) {
            topLever = topRowLever.get(i);
            midLever = midRowLever.get(i);
            botLever = botRowLever.get(i);
            printCurrentPosition();
        }
    }

    public void printCurrentPosition() {
        System.out.print("| ");
        for (Wheel wheel : wheels) {
            System.out.print(wheel.getLastFruit().getName());
            whiteSpace(wheel.getLastFruit());
            System.out.print(" | ");
        }

        System.out.print(topLever);
        System.out.println();
        System.out.print("| ");

        for (Wheel wheel : wheels) {
            System.out.print(wheel.getCurrentFruit().getName());
            whiteSpace(wheel.getCurrentFruit());
            System.out.print(" | ");
        }
        System.out.print(midLever);
        System.out.println();
        System.out.print("| ");

        for (Wheel wheel : wheels) {
            System.out.print(wheel.getNextFruit().getName());
            whiteSpace(wheel.getNextFruit());
            System.out.print(" | ");
        }

        System.out.print(botLever);
        System.out.println();
    }

    private void whiteSpace(Symbol symbol) {
        int wordLength = symbol.getLength();
        int whiteSpace = 10 - wordLength;
        while (whiteSpace > 0) {
            System.out.print(" ");
            whiteSpace--;
        }
    }

    public void pull() {
        System.out.println();
        System.out.println("Hit enter to pull!");
    }

    public void welcome() {
        System.out.println("welcome fruit salad");
    }

    public void youWin() {
        System.out.println();
        System.out.println("********************************");
        System.out.println(String.format("You've won an £%ds of money!", wheels.get(0).getCurrentFruit().getJackpot()));
        System.out.println("********************************");
    }

    public void moneyPlease() {
        System.out.println("£1 per spin. Enter an amount.");
    }

    public void status() {
        System.out.println();
        System.out.print(String.format("Your Wallet: £%d", player.getMoneyAmount()));
        System.out.print(" | ");
        System.out.print(String.format("In machine: £%d", machine.getUserMoney()));
        System.out.print(" | ");
        System.out.print(String.format("Your earnings so far: £%d", machine.getPayOutTracker()));
        System.out.println(" | ");
    }

    public void holdsAndNudges(){
        System.out.print(String.format("Nudges: %d", machine.getNudges()));
        System.out.print(" | ");
        System.out.print(String.format("Holds: %d", machine.getHoldsNum()));
        System.out.println(" | ");
    }

    public void thanks() {
        System.out.println("Thanks!");
    }

    public void printWheelNumbers() {
        printCurrentPosition();
        System.out.println("Wheel 1 | Wheel 2 | Wheel 3 ");
    }

    public void youHaveNudge() {
        System.out.println("You have a nudge!");
    }

    public void chooseWheel() {
        System.out.println("Please select wheel 1, 2 or 3! Or 0 to spin if you've chosen one");
    }

    public void youHaveHold() {
        System.out.println("You have a hold");
    }

    public void holdingWheels(ArrayList<Integer> wheelsToBeHeld) {
        for (Integer wheelNum : wheelsToBeHeld) {
            System.out.println(String.format("Holding wheel %d", wheelNum + 1));
        }
        System.out.println("Hit enter to spin");
    }


    public void whatYouGot() {
        System.out.print("You have ");
        System.out.print(String.format("Nudges: %d", machine.getNudges()));
        System.out.print(" | ");
        System.out.print(String.format("Holds: %d", machine.getHoldsNum()));
        System.out.println(" | ");
    }

    public void options() {
        System.out.println();
        System.out.print("Your options are ");
        if (machine.getNudges() > 0) {
            System.out.print("1) Nudge");
            System.out.print(" | ");
        }
        if (machine.getHoldsNum() > 0) {
            System.out.print("2) Hold");
            System.out.print(" | ");
        }
        System.out.print("3) Do nothing");
        System.out.println(" | ");

    }

    public void keepPlaying() {
        System.out.println();
        System.out.println("Do you want to keep playing?");
        System.out.println("'nope' to quit or enter to carry on");
    }
}
