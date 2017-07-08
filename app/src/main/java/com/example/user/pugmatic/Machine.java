package com.example.user.pugmatic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;


/**
 * Created by stuartbryce on 2017-06-30.
 */

public class Machine {

    private WheelSet wheelSet;
    private ArrayList<Wheel> wheels;
    private int nudges;
    public int holds;
    private int cashSupply;
    private int userMoney;
    private int payOutTracker;


    public Machine(WheelSet wheelSet) {
        this.wheelSet = wheelSet;
        this.wheels = wheelSet.getWheels();
        this.nudges = 2;
        this.holds = 2;
        this.cashSupply = 15;
        this.userMoney = 0;
        this.payOutTracker = 0;
    }

    public void spin(Wheel wheel) {
        int spins = getSpinNum();
        Collections.rotate(wheel.getWheel(), spins);
    }

    public int getSpinNum() {

        int arrayLength =  wheels.get(0).getWheel().size();
//        int arrayLength = Fruit.values().length;
        Random rand = new Random();
        int spins = rand.nextInt(arrayLength);
        return spins;
    }

    public void spinAll() {
        for (Wheel wheel : wheels) {
            spin(wheel);
        }
        while (checkForWin() && cashSupply < 10) {
            for (Wheel wheel : wheels) {
                spin(wheel);
            }
        }
    }

    public void nudge(Wheel wheel) {
        Collections.rotate(wheel.getWheel(), 1);
    }

    public boolean checkForWin() {
        if ((wheels.get(0).getCurrentFruit() == wheels.get(1).getCurrentFruit()) && (wheels.get(0).getCurrentFruit() == wheels.get(2).getCurrentFruit())) {
            return true;
        } else {
            return false;
        }
    }

    public void hold(ArrayList<Integer> wheelsToBeHeld) {
        ArrayList<Integer> allWheels = new ArrayList<>(Arrays.asList(0, 1, 2));
        for (Integer indexPop : wheelsToBeHeld) {
            allWheels.remove(indexPop);
        }
        for (Integer index : allWheels) {
            spin(wheels.get(index));
        }
    }

    public ArrayList<Integer> getHolds() {
        ArrayList<Integer> holds = new ArrayList<>();
        while (UserInput.getBoolean()) {
            holds.add(UserInput.getUserInt());
        }
        return holds;
    }

    public ArrayList<Wheel> getWheels() {
        return wheels;
    }

    public void payPlayer(Player player) {
        int amount = wheels.get(0).getCurrentFruit().getJackpot();
        player.receiveMoney(amount);
        this.cashSupply -= amount;
        this.payOutTracker += amount;
    }

    public int getCashSupply() {
        Integer cashSupply = new Integer(this.cashSupply);
        return cashSupply;
    }

    public Integer getUserMoney() {
        Integer userMoneyInMachine = new Integer(this.userMoney);
        return userMoneyInMachine;
    }

    public void addMoney(int moneyAdded) {

        userMoney += moneyAdded;
        cashSupply += moneyAdded;
    }

    public Integer getPayOutTracker() {
        Integer payOutAmount = new Integer(payOutTracker);
        return payOutAmount;
    }

    public Integer getNudges() {
        Integer nudgesNum = new Integer(nudges);
        return nudgesNum;
    }

    public Integer getHoldsNum() {
        Integer holdsNum = new Integer(holds);
        return holdsNum;
    }

    public void loseCredit() {
        this.userMoney--;
    }

    public void deductNudge() {
        this.nudges--;
    }

    public void deductHold() {
        this.holds--;
    }

    public void calculateNudges() {
        Random rand = new Random();
        int nudgeAmount = rand.nextInt(3);
        nudges = nudgeAmount;
    }

    public void calculateHolds() {
        Random rand = new Random();
        int holdAmount = 0;
        if (rand.nextInt(2) == 1) {
            holdAmount = rand.nextInt(2);
        }
        holds = holdAmount * 2;
    }
}
