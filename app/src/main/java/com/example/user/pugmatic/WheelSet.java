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

    public WheelSet(SymbolPack symbolPack, int wheelNum){
        wheel1 = new Wheel(symbolPack);
        wheel2 = new Wheel(symbolPack);
        wheel3 = new Wheel(symbolPack);
        this.wheels = new ArrayList<>(Arrays.asList((Wheel) wheel1, wheel2, wheel3));
    }

    public ArrayList<Wheel> getWheels() {
        return wheels;
    }
}