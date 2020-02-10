package main.PerfectHashing;

import main.Main.Schedule;

public class SecondHashTable {

    private int a, b, m;
    private Schedule[] hashTable;

    SecondHashTable(int a, int b, int m) {
        this.a = a;
        this.b = b;
        this.m = m;
        hashTable = new Schedule[m];
    }

    public Schedule get(int index) {
        return hashTable[index];
    }

    public void setByIndex(int index, Schedule value) {
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