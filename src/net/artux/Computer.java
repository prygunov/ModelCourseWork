package net.artux;

import java.util.Scanner;

public class Computer {

    BitSet aSet;
    BitSet bSet;
    BitSet cSet;
    BitSet am;
    int ch;

    Scanner scanner;

    public Computer() {
        scanner = new Scanner(System.in);
    }

    public void compute() throws Exception {
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        if (a == 0 || b == 0)
            return;

        aSet = BitSet.fromInt(a, 16);
        bSet = BitSet.fromInt(b, 16);
        cSet = new BitSet(32);
        ch = 15;

        am = new BitSet(32);
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
        System.out.println(cSet.getInt());
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

}
