package main.PerfectHashing;

import main.Main.Schedule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PerfectHashMap {

    private SecondHashTable[] perfectHashMap;
    private List<Schedule> staticList;
    private int a, b, p, m;

    public PerfectHashMap(List<Schedule> staticList, String searchKey) {
        this.staticList = staticList;

        searchingP(staticList);
        this.a = ValuesGenerator.generateA(p);
        this.b = ValuesGenerator.generateB(p);

        double squareRootOfN = Math.sqrt(staticList.size());
        this.m = (int) (squareRootOfN * Math.sqrt(squareRootOfN));

        List<List<Schedule>> temp = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            temp.add(new ArrayList<>());
        }
        fillingTempHashTable(temp, searchKey);

        creatingSecondFloor(temp, searchKey);
    }

    private void searchingP(List<Schedule> list) {
        int maxHash = 0;

        for (Schedule schedule : list) {
            if (schedule.getDayHash() > maxHash) maxHash = schedule.getDayHash();
        }

        this.p = ValuesGenerator.generateP(maxHash);
    }

    private void fillingTempHashTable(List<List<Schedule>> temp, String searchKey) {
        for (Schedule schedule : staticList) {
            int index = ValuesGenerator.generateUniversalHash(schedule.getDayHash(), a, b, p, m);

            if (schedule.getDay().equals(searchKey)) {
                System.out.println("First floor level: " + index);
            }

            temp.get(index).add(schedule);
        }
    }

    private void creatingSecondFloor(List<List<Schedule>> temp, String searchKey) {
        perfectHashMap = new SecondHashTable[m];
        int i = 0;

        for (List<Schedule> chain : temp) {
            if (chain.size() > 0) {
                createSubHashMap(chain, i, searchKey);
            } else {
                perfectHashMap[i] = null;
            }

            i++;
        }
    }

    private void createSubHashMap(List<Schedule> chain, int i, String searchKey) {
        int subLevelSize = chain.size() * chain.size();
        boolean isCollision;

        do {
            int subLevelA = ValuesGenerator.generateA(p);
            int subLevelB = ValuesGenerator.generateB(p);

            perfectHashMap[i] = new SecondHashTable(subLevelA, subLevelB, subLevelSize);
            isCollision = false;

            for (Schedule schedule : chain) {
                int index = ValuesGenerator.generateUniversalHash(schedule.getDayHash(), subLevelA, subLevelB, p, subLevelSize);

                if (schedule.getDay().equals(searchKey)) {
                    System.out.println("Second floor index: " + (index + 3));
                }

                if (perfectHashMap[i].isSomethingByIndex(index)) {
                    isCollision = true;
                    break;
                } else {
                    perfectHashMap[i].setByIndex(index, schedule);
                }
            }
        } while (isCollision);
    }

    public void getHashTableSize() {
        System.out.println("main.Main.Main HashMap size: " + perfectHashMap.length);

        int i = 0;
        for (SecondHashTable secondHashTable : perfectHashMap) {
            if (secondHashTable != null) {
                System.out.println(i + " chain size: " + secondHashTable.getM());
            } else {
                System.out.println(i + " chain size: 0");
            }

            i++;
        }
    }

    public void printHashTable() {
        System.out.println(Arrays.deepToString(perfectHashMap));
    }

    public Schedule get(String key) {
        int rowIndex = ValuesGenerator.generateUniversalHash(Math.abs(key.hashCode()), a, b, p, m);

        int subLevelSize = perfectHashMap[rowIndex].getM();
        int subLevelA = perfectHashMap[rowIndex].getA();
        int subLevelB = perfectHashMap[rowIndex].getB();
        int columnIndex = ValuesGenerator.generateUniversalHash(Math.abs(key.hashCode()), subLevelA, subLevelB, p, subLevelSize);

        System.out.println("Row index: " + rowIndex);
        System.out.println("Column index: " + (columnIndex + 3));

        return perfectHashMap[rowIndex].get(columnIndex);
    }
}