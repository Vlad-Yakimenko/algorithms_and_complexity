package PerfectHashing;

import javafx.util.Pair;

public class SecondHashTable {

    private int a, b, m;
    private Pair<Integer, Pair<String, ?>>[] hashTable;

    @SuppressWarnings("unchecked")
    SecondHashTable(int a, int b, int m) {
        this.a = a;
        this.b = b;
        this.m = m;
        hashTable = new Pair[m];
    }

    public Pair<?, ?> get(int index) {
        return hashTable[index];
    }

    public void setByIndex(int index, Pair<Integer, Pair<String, ?>> value) {
        hashTable[index] = value;
    }

    public boolean isSomethingByIndex(int index) {
        return hashTable[index] != null;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getM() {
        return m;
    }
}