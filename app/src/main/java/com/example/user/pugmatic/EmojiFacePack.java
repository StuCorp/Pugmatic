package com.example.user.pugmatic;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by user on 09/07/2017.
 */

public class EmojiFacePack extends SymbolPack {
    ArrayList<Symbol> pack;
    Symbol cowboy;
    Symbol cry;
    Symbol devil;
    Symbol heart;
    Symbol laugh;
    Symbol money;
    Symbol vom;

    String name = "EmojiFacePack";

    public EmojiFacePack() {
        cowboy = new Symbol("cowboy", 7, R.drawable.facecowboy);
        cry = new Symbol("cry", 3, R.drawable.facecry);
        devil = new Symbol("devil", 5, R.drawable.facedevil);
        heart = new Symbol("hear", 8, R.drawable.faceheart);
        laugh = new Symbol("laugh", 6, R.drawable.facelaugh);
        money = new Symbol("money", 25, R.drawable.facemoney);
        vom = new Symbol("vom", 5, R.drawable.facevom);

        this.pack = new ArrayList<>(Arrays.asList((Symbol) cowboy, cry, devil, heart, laugh, money, vom));
    }

    @Override
    public ArrayList<Symbol> getPack() {
        return pack;
    }

    @Override
    public String getName() {
        return this.name;
    }

}
