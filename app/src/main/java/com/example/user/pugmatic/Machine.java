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
    private boolean holdOn;
    private boolean hold1;
    private boolean hold2;
    private boolean hold3;
    private Symbol wheel1CurrentSymbol;
    private Symbol wheel2CurrentSymbol;
    private Symbol wheel3CurrentSymbol;


    public Machine(WheelSet wheelSet) {
        this.wheelSet = wheelSet;
        this.wheels = wheelSet.getWheels();
        this.nudges = 0;
        this.holds = 0;
        this.cashSupply = 15;
        this.userMoney = 0;
        this.payOutTracker = 0;
        holdOn = false;
        hold1 = false;
        hold2 = false;
        hold3 = false;
        wheel1CurrentSymbol = wheels.get(0).getCurrentFruit();
        wheel2CurrentSymbol = wheels.get(1).getCurrentFruit();
        wheel3CurrentSymbol = wheels.get(2).getCurrentFruit();

    }

    public void spin(Wheel wheel) {
        int spins = getSpinNum();
        Collections.rotate(wheel.getWheel(), spins);
    }

    public int getSpinNum() {

        int arrayLength = wheels.get(0).getWheel().size();
//        int arrayLength = Fruit.values().length;
        Random rand = new Random();
        int spins = rand.nextInt(arrayLength);
        return spins;
    }

    public void spinAll() {
        for (Wheel wheel : wheels) {
            if (!wheel.isHoldOn()) {
                spin(wheel);
            }
            else{
                wheel.setHoldOnOff();
            }
//            while (checkForWin() && cashSupply < 10) {
//                for (Wheel wheel : wheels) {
//                    spin(wheel);
//                }
            }
        }

    public void nudge(Wheel wheel) {
        Collections.rotate(wheel.getWheel(), 1);
    }

    public boolean checkForWin() {
        String symbolToMatch = wheels.get(0).getCurrentFruit().getName();
        for (Wheel wheel : wheels) {
            if (!symbolToMatch.equals(wheel.getCurrentFruit().getName())) {
                return false;
            }
        }
        return true;
    }
//        if ((wheels.get(0).getCurrentFruit().getName()
//                .equals(wheels.get(1).getCurrentFruit().getName())) &&
//                (wheels.get(0).getCurrentFruit().getName()
//                        .equals(wheels.get(2).getCurrentFruit().getName()))) {
//            return true;
//        } else {
//            return false;
//        }


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

    public void setPayOutTracker(int payOutTracker) {
        this.payOutTracker = payOutTracker;
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
public void calculateHolds(){
    if (wheelSet.getNumOfWheels()==3){
        calculateHolds3Set();
    } else {
        calculateHolds5Set();
    }
}

    public void calculateHolds3Set() {
        Random rand = new Random();
        int holdAmount = 0;
        if (rand.nextInt(2) == 1) {
            holdAmount = rand.nextInt(2);
        }
        holds = holdAmount * 2;
    }

    public void calculateHolds5Set() {
        Random rand = new Random();
        int holdAmount = 0;
        if (rand.nextInt(2) == 1) {
            holdAmount = rand.nextInt(2)+3;
        }
        holds = holdAmount;
    }


    public boolean isHoldOnOn() {
        return holdOn;
    }

    public boolean isHold1() {
        return hold1;
    }

    public boolean isHold2() {
        return hold2;
    }

    public boolean isHold3() {
        return hold3;
    }


    public void addHold() {
        this.holds++;
    }

    public int getPackNum(){
        return wheelSet.getPackNum();
    }

    public int getWheelsNum(){
        return wheelSet.getNumOfWheels();
    }

    public void setUserMoney(int userMoney) {
        this.userMoney = userMoney;
    }
}
