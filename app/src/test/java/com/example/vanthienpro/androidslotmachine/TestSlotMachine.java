package com.example.vanthienpro.androidslotmachine;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by hayleyprior on 10/11/2017.
 */

public class TestSlotMachine {

    private Symbols lion;
    private Symbols alligator;
    private SlotMachine slotMachine;

    @Before
    public void setUp() throws Exception {
        lion = Symbols.LION;
        alligator = Symbols.ALLIGATOR;
        slotMachine = new SlotMachine(3);
    }

    @Test
    public void canGenerate3Wheels() throws Exception {
        assertEquals(3, slotMachine.countSlots());
    }

    @Test
    public void canGenerate5Wheels() throws Exception {
        SlotMachine fiveSlotMachine = new SlotMachine(5);
        assertEquals(5, fiveSlotMachine.countSlots());
    }

    @Test
    public void playerCanAddMoreMoney() throws Exception {
        slotMachine.setPlayerFunds(10);
        slotMachine.addPlayerFunds(20);
        assertEquals(30, slotMachine.checkPlayerFunds());
    }

    @Test
    public void spinGetsArrayList() throws Exception {
        ArrayList<Symbols> line = slotMachine.spin();
        assertEquals(3, line.size());
    }

    @Test
    public void playFundsDecreaseAfterSpin() throws Exception {
        slotMachine.setPlayerFunds(10);
        slotMachine.spin();
        assertEquals(9, slotMachine.checkPlayerFunds());
    }

    @Test
    public void canGetLineImages() {
        ArrayList<Symbols> line = new ArrayList<>();
        line.add(lion);
        line.add(alligator);
        ArrayList<String> images = slotMachine.getLineImages(line);
        assertEquals("@drawable/lion", images.get(0));
    }

    @Test
    public void canCheckWinTrue() throws Exception {
        ArrayList<Symbols> line = new ArrayList<>();
        line.add(Symbols.RHINO);
        line.add(Symbols.RHINO);
        line.add(Symbols.RHINO);
        assertEquals(true, slotMachine.checkWin(line));
    }

    @Test
    public void canCheckWinFalse() throws Exception {
        ArrayList<Symbols> line = new ArrayList<>();
        line.add(Symbols.RHINO);
        line.add(Symbols.RHINO);
        line.add(Symbols.LION);
        assertEquals(false, slotMachine.checkWin(line));
    }

    @Test
    public void canGetWinValue() throws Exception {
        ArrayList<Symbols> line = new ArrayList<>();
        line.add(Symbols.RHINO);
        line.add(Symbols.RHINO);
        line.add(Symbols.RHINO);
        assertEquals(80, slotMachine.getWinValue(line));
    }

    @Test
    public void canUpdateFundsFromWin() throws Exception {
        slotMachine.setPlayerFunds(5);
        ArrayList<Symbols> line = new ArrayList<>();
        line.add(Symbols.RHINO);
        line.add(Symbols.RHINO);
        line.add(Symbols.RHINO);
        int value = slotMachine.getWinValue(line);
        slotMachine.addPlayerFunds(value);
        assertEquals(85, slotMachine.checkPlayerFunds());
    }

    @Test
    public void canHold() throws Exception {
        slotMachine.getSlots().get(0).setCurrentSymbol(Symbols.LION);
        slotMachine.getSlots().get(0).setPlayerHasHeld(true);
        slotMachine.spin();
        assertEquals(Symbols.LION, slotMachine.getSlots().get(0).getCurrentSymbol());
    }

    @Test
    public void canGetCurrentSymbols() throws Exception {
        for(Wheel wheel : slotMachine.getSlots()){
            wheel.setCurrentSymbol(Symbols.BUFFALO);
        }
        ArrayList<Symbols> line = slotMachine.getCurrentSymbols();
        assertEquals(Symbols.BUFFALO, line.get(0));
        assertEquals(Symbols.BUFFALO, line.get(1));
        assertEquals(Symbols.BUFFALO, line.get(2));

    }
}
