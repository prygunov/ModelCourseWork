package net.artux;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        // write your code here

        Scanner scanner = new Scanner(System.in);

        int a = scanner.nextInt();
        int b = scanner.nextInt();


        if (a == 0 || b == 0)
            return;

        BitSet aSet = BitSet.fromInt(a, 16);
        BitSet bSet = BitSet.fromInt(b, 16);
        BitSet cSet = new BitSet(32);
        //print(aSet);
        //print(bSet);
        int ch = 15;

        BitSet am = new BitSet(32);
        am.set(31, 30, aSet.get(0));
        for (int i = 0; i < 15; i++) {
            am.set(i, aSet.get(i));
        }
        //занулять не нужно, уже
        if (aSet.get(15))
            am.flip(29, 15);

        if (bSet.get(0)) {
            am.shiftLeft();
        } else {
            am.flip(true);
            cSet = BitSet.binaryAddition(cSet, am);
            cSet = BitSet.binaryAddition(cSet, BitSet.ONE());
            am.flip(true);
            am.shiftLeft();
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
                } else {
                    cSet = BitSet.binaryAddition(cSet, am);
                    am.shiftLeft();
                    bSet.shiftRight();
                    ch--;
                }
            } else {
                am.shiftLeft();
                bSet.shiftRight();
                ch--;
            }
        } while (ch != 0);

        if (cSet.get(31)){
            cSet.flip(29, 0);
            cSet = BitSet.binaryAddition(cSet, BitSet.ONE());
        }

        if (cSet.get(14)){
            //???
            for (int i = 30; i >= 16 ; i--) {
                cSet.set(i, am.get(i-1));
            }
            cSet = BitSet.binaryAddition(cSet, BitSet.ONE());
        }

        if (cSet.get(31)){
            cSet.flip(30, 16);
            cSet = BitSet.binaryAddition(cSet, BitSet.ONE());
        }
        System.out.println(cSet.getInt());
    }
/*
    static BitSet fromInt(int a, int n) throws Exception {
        if (Math.abs(a) > 32767)
            throw new Exception("yap");
        BitSet bitset = new BitSet(n);
        if (a < 0)
            bitset.set(0, true);
        a = Math.abs(a);
        String binary = Integer.toBinaryString(a);
        for (int i = 0; i < binary.length(); i++) {
            bitset.set(n - 1 - i, binary.charAt(i) == '1' ? true : false);
        }

        return bitset;
    }

    public static void print(BitSet bitSet) {
        System.out.println(string(bitSet));
    }

    public static String string(BitSet bitSet) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < bitSet.length(); i++) {
            s.append(bitSet.get(i) == true ? 1 : 0);
        }

        return s.toString();
    }

    public static BitSet addition(BitSet a, BitSet b) {
        return fromString(binaryAddition(string(a), string(b)));
    }

    public static String binaryAddition(String s1, String s2) {
        if (s1 == null || s2 == null) return "";
        int first = s1.length() - 1;
        int second = s2.length() - 1;
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while (first >= 0 || second >= 0) {
            int sum = carry;
            if (first >= 0) {
                sum += s1.charAt(first) - '0';
                first--;
            }
            if (second >= 0) {
                sum += s2.charAt(second) - '0';
                second--;
            }
            carry = sum >> 1;
            sum = sum & 1;
            sb.append(sum == 0 ? '0' : '1');
        }
        if (carry > 0)
            sb.append('1');

        sb.reverse();
        return String.valueOf(sb);
    }

    static BitSet fromString(String s) {
        BitSet set = new BitSet(s.length());
        for (int i = 0; i < s.length(); i++) {
            set.set(i, s.charAt(i) == '1' ? true : false);
        }
        return set;
    }

    public static BitSet shiftLeft(BitSet bitset) {

        return bitset.get(1, bitset.length());
    }

    public static BitSet shiftRight(BitSet bs) {
        for (int i = bs.length() - 1; i >= 0; i = bs.previousSetBit(i - 1)) {
            bs.clear(i);//from  w  w  w.ja  v a2s  .  c  om
            bs.set(i + 1);
        }
        return bs;
    }*/
}
