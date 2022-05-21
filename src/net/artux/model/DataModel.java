package net.artux.model;

import java.util.Scanner;

public class DataModel {

    public BitSet aSet;
    public BitSet bSet;
    public BitSet cSet;
    public BitSet am;
    public int ch;

    private MainUI mainUI;

    public DataModel(MainUI mainUI) {
        this.mainUI = mainUI;
        reset();
    }

    public void reset(){
        aSet = new BitSet(16);
        bSet = new BitSet(16);
        cSet = new BitSet(16);
        am = new BitSet(32);
    }

    public void compute() throws Exception {
        if (aSet.isNull() || bSet.isNull())
            return;

        ch = 15;
        am.set(31, 30, aSet.get(15));

        for (int i = 0; i < 15; i++) {
            am.set(i, aSet.get(i));
        }
        debug("a0");
        //занулять не нужно, уже
        if (aSet.get(15)) {
            am.flip(29, 15);
            debug("a1");
        }

        if (!bSet.get(0)) {
            am.shiftLeft();
            debug("a2");
        } else {
            am.flip(true);
            cSet = BitSet.binaryAddition(cSet, am);
            cSet = BitSet.binaryAddition(cSet, BitSet.ONE());
            am.flip(true);
            am.shiftLeft();
            debug("a3");
        }
        do {
            if (bSet.get(0) != bSet.get(1)) {
                if (bSet.get(1)) {
                    am.flip(true);
                    cSet = BitSet.binaryAddition(cSet, am);
                    cSet = BitSet.binaryAddition(cSet, BitSet.ONE());
                    am.flip(true);

                    am.shiftLeft();
                    bSet.shiftRight();
                    ch--;
                    debug("a4");
                } else {
                    cSet = BitSet.binaryAddition(cSet, am);
                    am.shiftLeft();
                    bSet.shiftRight();
                    ch--;
                    debug("a5");
                }
            } else {
                am.shiftLeft();
                bSet.shiftRight();
                ch--;
                debug("a6");
            }
        } while (ch != 0);

        if (cSet.get(31)){
            cSet.flip(29, 0);
            cSet = BitSet.binaryAddition(cSet, BitSet.ONE());
            debug("a7");
        }

        if (cSet.get(14)){
            cSet.set(30, 16,
                    BitSet.binaryAddition(cSet.clone(29, 15), BitSet.ONE()));
            debug("a8");
        }

        if (cSet.get(31)){
            BitSet temp = cSet.clone(30, 16);
            temp.flip(true);
            cSet.set(30, 16,
                    BitSet.binaryAddition(temp, BitSet.ONE()));
            debug("a9");
        }

        System.out.println(cSet.clone(15, 0).getInt(true));
    }

    private void debug(String state){
        System.out.println("---------------------");
        System.out.println("State " + state);

        System.out.println("A SET " + aSet);
        System.out.println("B SET " + bSet);
        System.out.println("C SET " + cSet);
        System.out.println("M SET " + am);
        System.out.println("СЧ " + ch);

        System.out.println("---------------------");
    }

    public void flip(int type, int index){
        if (type == 0)
            aSet.flip(index);
        else
            bSet.flip(index);
        mainUI.updateInput(aSet, bSet);
    }

}
