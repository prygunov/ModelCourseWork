package net.artux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BitSet {

    private boolean[] content;

    public BitSet(int n) {
        content = new boolean[n];
    }

    public static BitSet fromInt(int a, int n) throws Exception {
        if (Math.abs(a) > 32767)
            throw new Exception("yap");

        BitSet bitset = new BitSet(n);
        if (a < 0) {
            bitset.set(n - 1, true);
        }
        a = Math.abs(a);
        String binary = Integer.toBinaryString(a);
        for (int i = 0; i < binary.length(); i++) {
            bitset.set(i, binary.charAt(binary.length() - 1 - i) == '1' ? true : false);
        }

        return bitset;
    }

    public boolean get(int i){
        if(i >= content.length)
            return false;
        else
            return content[content.length - 1 - i];
    }
    public void set(int i, boolean value){
        if (i >= content.length){
            int dif = i - content.length;
            boolean newarr[] = new boolean[content.length + dif + 1];
            for (int j = 0; j < content.length; j++) {
                newarr[j+dif + 1] = content[j];
            }
            content = newarr;
        }

        content[content.length - 1 - i] = value;
    }

    public void set(int fromIndex, int toIndex, boolean value){
        for (int i = fromIndex; i >= toIndex; i--) {
            set(i, value);
        }
    }

    public void flip(int toIndex, int fromIndex){
        for (int i = fromIndex; i <= toIndex; i++) {
            set(i, !get(i));
        }
    }

    public void flip(boolean all){
        if (all)
            flip(0, content.length - 1);
        else
            flip(0, content.length - 2);
    }

    public void shiftLeft(){
        for (int i = 1; i < content.length; i++) {
            set(content.length - i, get(content.length-i-1));
        }
        set(0, false);
    }

    public void shiftRight(){
        for (int i = 0; i < content.length-1; i++) {
            set(i, get(i+1));
        }
        set(content.length - 1, false);
    }

    public void limit(int n){

    }

    public static BitSet binaryAddition(BitSet first, BitSet second) {
        int n = Math.max(first.content.length, second.content.length);
        BitSet result = new BitSet(n);
        boolean carry = false;
        for (int i = 0; i < n; i++) {
            boolean bit1 = first.get(i);
            boolean bit2 = second.get(i);

            if (bit1 && bit2 && carry) {
                carry = true;
                result.set(i, true);
            } else if ((bit1 && bit2) || ((bit1 || bit2) && carry)) {
                carry = true;
                result.set(i, false);
            } else if (bit1 || bit2 || carry) {
                carry = false;
                result.set(i, true);
            } else {
                carry = false;
                result.set(i, false);
            }
        }
        if (carry)
            result.set(n, true);

        return result;
    }

    public static BitSet minus(BitSet a, BitSet b){
        b.flip(false);
        b = binaryAddition(b, BitSet.ONE());
        BitSet result = binaryAddition(a, b);
        return result;
    }

    public static BitSet ONE(){
        BitSet bitSet = new BitSet(1);
        bitSet.flip(true);
        return bitSet;
    }

    public int getInt(){
        int result = 0;
        for (int i = 0; i < content.length; i++) {
            if (get(i))
                result += Math.pow(2, i);
        }
        return result;
    }

    public String register() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < content.length; i++) {
            s.append(get(i) == true ? 1 : 0);
        }
        return s.toString();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = content.length - 1; i >= 0; i--) {
            s.append(get(i) == true ? 1 : 0);
        }
        return s.toString();
    }
}
