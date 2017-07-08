package com.example.user.pugmatic;

import java.util.ArrayList;

/**
 * Created by stuartbryce on 2017-06-30.
 */

public class Game {


    Player player;
    Machine machine;
    Viewer viewer;
    Boolean game;
    ArrayList<Wheel> wheels;
    Boolean win;
    boolean stayInLoop;
    boolean keepPlaying;


    public Game(Player player, Machine machine, Viewer viewer) {
        this.player = player;
        this.machine = machine;
        this.viewer = viewer;
        this.game = true;
        this.wheels = machine.getWheels();
        this.win = false;
        this.keepPlaying = true;
    }

    public void run() {
        machine.spinAll();
        viewer.welcome();

        while(keepPlaying) {
            while (!moneyInMachine()) {
                askForMoney();
            }
            viewer.thanks();
            viewer.status();
            while (moneyInMachine() && keepPlaying) {
                play();
            }
        }
    }

    private void askForMoney() {
        viewer.status();
        viewer.moneyPlease();
        int bettingMoney = UserInput.putMoneyIn(player);
        machine.addMoney(bettingMoney);
        player.removeMoney(bettingMoney);
    }

    public void play() {
        stayInLoop = true;
        pull();
        win = machine.checkForWin();
        if (win) {
            winScenario();
        }
        machine.calculateNudges();
        machine.calculateHolds();
        while ((machine.getHoldsNum() > 0 || machine.getNudges() > 0) && stayInLoop && !win) {
            nudgeAndHoldMenu();
        }
        viewer.status();
        win = false;
        keepGaming();
    }

    private void nudgeAndHoldMenu() {
        viewer.status();
        viewer.holdsAndNudges();
        viewer.options();
        int choice = UserInput.getUserInt();
        switch (choice) {
            case 1:
                nudge();
                break;
            case 2:
                hold();
                break;
            case 3:
                stayInLoop = false;
                break;
            default:
                stayInLoop = false;
                break;
        }
        win = machine.checkForWin();
        if (win) {
            winScenario();
        }
    }


    private void keepGaming() {
        viewer.keepPlaying();
        String answer = UserInput.getString();
        if (answer.equals("nope")){
            keepPlaying = false;
        }
    }

    private void winScenario() {
        viewer.youWin();
        machine.payPlayer(player);
    }

    public void hold(){
        boolean spin = false;
        ArrayList<Integer> wheelsToBeHeld = new ArrayList<>();
        while(machine.holds > 0 && !spin) {
            viewer.youHaveHold();
            viewer.chooseWheel();
            int wheelToHold = UserInput.getUserInt() - 1;
            if (wheelToHold == -1){
                spin= true;
            } else {
                wheelsToBeHeld.add(wheelToHold);
                machine.deductHold();
            }
        }
        viewer.holdingWheels(wheelsToBeHeld);
        UserInput.getString();
        machine.hold(wheelsToBeHeld);
        viewer.printCurrentPosition();
    }

    public void nudge(){
        viewer.youHaveNudge();
        viewer.printWheelNumbers();
        viewer.chooseWheel();
        int choice = UserInput.getUserInt() - 1;
        machine.nudge(wheels.get(choice));
        machine.deductNudge();
        viewer.printCurrentPosition();
    }

    public void pull() {
//        viewer.printCurrentPosition(wheels);
        viewer.pull();
        String answer = UserInput.getString();
        if (answer.equals("")) {
            machine.spinAll();
            machine.loseCredit();
            viewer.printCurrentPosition();

        } else {
            this.game = false;
        }
    }

    public boolean moneyInMachine(){
        return (machine.getUserMoney() > 0);
    }

}
