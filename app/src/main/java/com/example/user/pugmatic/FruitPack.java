package com.example.user.pugmatic;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by stuartbryce on 2017-07-08.
 */

public class FruitPack extends SymbolPack {
    ArrayList<Symbol> pack;
    Symbol cherry;
    Symbol seven;
    Symbol bar;
    Symbol eggplant;

    public FruitPack(){
        cherry = new Symbol("cherry", 10, R.drawable.cherry);
        seven = new Symbol("seven", 7, R.drawable.seven);
        bar = new Symbol("bar", 5, R.drawable.bar);
        eggplant = new Symbol("eggplant", 20, R.drawable.eggplant);
        this.pack = new ArrayList<>(Arrays.asList((Symbol) cherry, seven, bar, eggplant));
    }

    @Override
    public ArrayList<Symbol> getPack() {
        return pack;
    }
}
