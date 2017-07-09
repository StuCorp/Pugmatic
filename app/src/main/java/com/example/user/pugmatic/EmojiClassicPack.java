package com.example.user.pugmatic;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by user on 09/07/2017.
 */

public class EmojiClassicPack extends SymbolPack {


    ArrayList<Symbol> pack;
    Symbol chick;
    Symbol facepalm;
    Symbol moon;
    Symbol onehundred;
    Symbol partypopper;
    Symbol santa;
    Symbol shruggirl;


    String name = "EmojiClassicPack";

    public EmojiClassicPack() {
        chick = new Symbol("chick", 10, R.drawable.hatchingchick);
        facepalm = new Symbol("facepalm", 7, R.drawable.facepalm);
        moon = new Symbol("moon", 5, R.drawable.moon);
        onehundred = new Symbol("onehundred", 20, R.drawable.onehundred);
        partypopper = new Symbol("partypopper", 20, R.drawable.partypopper);
        santa = new Symbol("santa", 20, R.drawable.santa);
        shruggirl = new Symbol("shruggirl", 20, R.drawable.shruggirl);

        this.pack = new ArrayList<>(Arrays.asList((Symbol) chick, facepalm, moon, onehundred, partypopper, santa, shruggirl));
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
