package com.example.user.pugmatic;

import java.util.ArrayList;

/**
 * Created by stuartbryce on 2017-06-30.
 */

public class Wheel {

    private ArrayList<Symbol> wheel;
    private ArrayList<Symbol> pack;

    public Wheel(SymbolPack symbolPack){
        this.wheel = new ArrayList<>();
        this.pack = symbolPack.getPack();
        fillWheel();
    }

//    public void fillWheel(){
//        for (Fruit fruit : Fruit.values()){
//            this.wheel.add(fruit);
//        }
//    }
//
    public void fillWheel(){
        for (Symbol symbol: pack){
            this.wheel.add(symbol);
        }
    }

    public Symbol getCurrentFruit(){
        return this.wheel.get(0);
    }

    public Symbol getNextFruit(){
        return this.wheel.get(wheel.size()-1);
    }

    public Symbol getLastFruit(){
        return this.wheel.get(1);
    }


    public ArrayList<Symbol> getWheel() {
        return wheel;
    }

    public void setFruitAtZeroIndex(Symbol symbol){
        this.wheel.set(0,symbol);
    }
}


