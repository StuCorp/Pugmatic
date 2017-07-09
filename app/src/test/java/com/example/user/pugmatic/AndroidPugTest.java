package com.example.user.pugmatic;

import android.view.View;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 08/07/2017.
 */

public class AndroidPugTest {

    Player player;
    FruitPack fruitPack;
    WheelSet wheelSet;
    Machine machine;
    Viewer viewer;
    Game game;


    @Before
    public void before() {
        player = new Player(20);
        fruitPack = new FruitPack();
        wheelSet = new WheelSet(fruitPack, 3);
        machine = new Machine(wheelSet);
        viewer = new Viewer(player, machine);
        game = new Game(player, machine, viewer);
    }

    @Test
    public void testCheckForWin() {
        ArrayList<Wheel> wheels = wheelSet.getWheels();
        wheels.get(0).setFruitAtZeroIndex(fruitPack.bar);
        wheels.get(1).setFruitAtZeroIndex(fruitPack.bar);
        wheels.get(2).setFruitAtZeroIndex(fruitPack.bar);
        assertEquals(true, machine.checkForWin());
    }

    @Test
    public void testSymbolCollection(){

        SymbolPackCollection symbpk = new SymbolPackCollection();

        assertEquals("fruitpack", symbpk.getNames());
    }
}
