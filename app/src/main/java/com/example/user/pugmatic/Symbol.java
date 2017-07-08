package com.example.user.pugmatic;

/**
 * Created by stuartbryce on 2017-07-08.
 */

public class Symbol {

    private String name;
    private int jackpot;
    private int image;

    public Symbol(String name, int jackpot, int image){
        this.name = name;
        this. jackpot = jackpot;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getJackpot() {
        return jackpot;
    }

    public void setJackpot(int jackpot) {
        this.jackpot = jackpot;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getLength() {
        return this.name.length();
    }
}
