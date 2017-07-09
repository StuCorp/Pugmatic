package com.example.user.pugmatic;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by user on 09/07/2017.
 */

public class WrestlerPack extends SymbolPack {
    ArrayList<Symbol> pack;
    Symbol hitman;
    Symbol golddust;
    Symbol hulk;
    Symbol papashango;
    Symbol rock;
    Symbol stonecold;
    Symbol ultimate;
    Symbol undertaker;

    String name = "Wrestler Pack";

    public WrestlerPack() {
        hitman = new Symbol("hitman", 10, R.drawable.wrestlerbrethitman);
        golddust = new Symbol("golddust", 7, R.drawable.wrestlergolddust);
        hulk = new Symbol("hulk", 5, R.drawable.wrestlerhulk);
        papashango = new Symbol("papashango", 20, R.drawable.wrestlerpapashango);
        rock = new Symbol("rock", 20, R.drawable.wrestlerrock);
        stonecold = new Symbol("stonecold", 20, R.drawable.wrestlerstoncold);
        ultimate = new Symbol("ultimate", 20, R.drawable.wrestlerultimatewarrior);
        undertaker= new Symbol("undertaker", 20, R.drawable.wrestlerundertaker);


        this.pack = new ArrayList<>(Arrays.asList((Symbol) hitman, golddust, hulk, papashango, rock, stonecold, ultimate, undertaker));
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
