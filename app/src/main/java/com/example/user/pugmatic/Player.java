package com.example.user.pugmatic;

/**
 * Created by stuartbryce on 2017-06-30.
 */

public class Player {

    int money;

    public Player(int money){
        this.money = money;
    }

    public void receiveMoney(int payOut){
        this.money += payOut;
    }

    public Integer getMoneyAmount(){
        Integer money = new Integer(this.money);
        return money;
    }

    public void removeMoney(int bettingMoney) {
        this.money -= bettingMoney;
    }
}
