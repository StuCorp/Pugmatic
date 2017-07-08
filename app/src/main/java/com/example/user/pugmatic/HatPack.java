package com.example.user.pugmatic;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by stuartbryce on 2017-07-08.
 */

public class HatPack extends SymbolPack {

    ArrayList<Symbol> pack;
    Symbol bunnet;
    Symbol sombrero;
    Symbol bucket;
    Symbol cap;
    Symbol topHat;

    public HatPack(){
        bunnet = new Symbol("bunnet", 10, 12);
        sombrero = new Symbol("sombrero", 7, 12);
        bucket = new Symbol("bucket", 5, 12);
        cap = new Symbol("cap", 20, 12);
        topHat = new Symbol("topHat", 100, 12);
        this.pack = new ArrayList<>(Arrays.asList((Symbol) bunnet, sombrero, bucket, cap, topHat));
    }

    @Override
    public ArrayList<Symbol> getPack() {
        return pack;
    }
}
