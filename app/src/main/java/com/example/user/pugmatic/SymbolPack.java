package com.example.user.pugmatic;

import java.util.ArrayList;


/**
 * Created by stuartbryce on 2017-07-08.
 */

public abstract class SymbolPack implements Packable {
    ArrayList<Symbol> pack;
    private String name;


    public ArrayList<Symbol> getPack() {
        return pack;
    }

    public String getName() {
        return name;
    }
}
