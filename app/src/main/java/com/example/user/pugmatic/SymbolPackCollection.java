package com.example.user.pugmatic;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by user on 09/07/2017.
 */

public class SymbolPackCollection {
    FruitPack fruitPack = new FruitPack();
    EmojiPeoplePack peoplePack = new EmojiPeoplePack();
    ArrayList<SymbolPack> pack = new ArrayList<>(Arrays.asList(fruitPack, peoplePack));

    public  ArrayList<SymbolPack> getPack() {
        return pack;
    }

    public ArrayList<String> getNames(){
        ArrayList<String> packNames = new ArrayList<>();
        for (SymbolPack symbolPack : pack){
            packNames.add(symbolPack.getName());
        }
        return  packNames;
    }
}
