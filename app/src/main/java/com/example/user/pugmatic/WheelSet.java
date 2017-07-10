package com.example.user.pugmatic;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by stuartbryce on 2017-07-02.
 */

public class WheelSet {

    private Wheel wheel1;
    private Wheel wheel2;
    private Wheel wheel3;
    private ArrayList<Wheel> wheels;
    private SymbolPack symbolPack;
    private  int symbolPackNum;
    private int numOfWheels;

    public WheelSet(SymbolPack symbolPack, int wheelNum){
        this.symbolPack = symbolPack;
        this.numOfWheels = wheelNum;
        wheel1 = new Wheel(symbolPack);
        wheel2 = new Wheel(symbolPack);
        wheel3 = new Wheel(symbolPack);
        this.wheels = new ArrayList<>(Arrays.asList((Wheel) wheel1, wheel2, wheel3));
    }

    public WheelSet(int symbolPackChoice, int wheelNum){
        this.symbolPackNum = symbolPackChoice;
        this.numOfWheels = wheelNum;
        SymbolPackCollection symbolPackCollection = new SymbolPackCollection();
        SymbolPack symbolPack = symbolPackCollection.getPack().get(symbolPackChoice);
       this.symbolPack = symbolPack;


        wheel1 = new Wheel(symbolPack);
        wheel2 = new Wheel(symbolPack);
        wheel3 = new Wheel(symbolPack);
        this.wheels = new ArrayList<>(Arrays.asList((Wheel) wheel1, wheel2, wheel3));
    }

    public ArrayList<Wheel> getWheels() {
        return wheels;
    }

    public int getPackNum() {
        return symbolPackNum;
    }

    public int getNumOfWheels() {
        return numOfWheels;
    }
}