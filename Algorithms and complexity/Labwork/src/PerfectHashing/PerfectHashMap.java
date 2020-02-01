package PerfectHashing;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PerfectHashMap {

    private SecondHashTable[] perfectHashMap;
    private List<Pair<Integer, Pair<String, ?>>>  hashedKeys;
    private int a, b, p, m;

    public PerfectHashMap(List<Pair<String, List<String>>> staticList, String searchKey) {
        this.hashedKeys = encryptKeys(staticList);
        this.a = ValuesGenerator.generateA(p);
        this.b = ValuesGenerator.generateB(p);

        double squareRootOfN = Math.sqrt(hashedKeys.size());
        this.m = (int) (squareRootOfN * Math.sqrt(squareRootOfN));

        List<List<Pair<Integer, Pair<String, ?>>>> temp = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            temp.add(new ArrayList<>());
        }
        fillingTempHashTable(temp, searchKey);

        creatingSecondFloor(temp, searchKey);
    }

    private List<Pair<Integer, Pair<String, ?>>> encryptKeys(List<Pair<String, List<String>>> list) {
        List<Pair<Integer, Pair<String, ?>>> buffer = new ArrayList<>();
        int maxHash = 0;

        for (Pair<String, ?> pair : list) {
            int hash = Math.abs(pair.getKey().hashCode());

            if (hash > maxHash) maxHash = hash;

            buffer.add(new Pair<>(hash, pair));
        }

        this.p = ValuesGenerator.generateP(maxHash);

        return buffer;
    }

    private void fillingTempHashTable(List<List<Pair<Integer, Pair<String, ?>>>> temp, String searchKey) {
        for (Pair<Integer, Pair<String, ?>> pair : hashedKeys) {
            int index = ValuesGenerator.generateUniversalHash(pair.getKey(), a, b, p, m);

            if (pair.getValue().getKey().equals(searchKey)) {
                System.out.println("First floor level: " + index);
            }

            temp.get(index).add(pair);
        }
    }

    @SuppressWarnings("unchecked")
    private void creatingSecondFloor(List<List<Pair<Integer, Pair<String, ?>>>> temp, String searchKey) {
        perfectHashMap = new SecondHashTable[m];
        int i = 0;

        for (List<Pair<Integer, Pair<String, ?>>> chain : temp) {
            if (chain.size() > 0) {
                createSubHashMap(chain, i, searchKey);
            } else {
                perfectHashMap[i] = null;
            }

            i++;
        }
    }

    @SuppressWarnings("unchecked")
    private void createSubHashMap(List<Pair<Integer, Pair<String, ?>>> chain, int i, String searchKey) {
        int subLevelSize = chain.size() * chain.size();
        boolean isCollision;

        do {
            int subLevelA = ValuesGenerator.generateA(p);
            int subLevelB = ValuesGenerator.generateB(p);

            perfectHashMap[i] = new SecondHashTable(subLevelA, subLevelB, subLevelSize);
            isCollision = false;

            for (Pair<Integer, Pair<String, ?>> pair : chain) {
                int index = ValuesGenerator.generateUniversalHash(pair.getKey(), subLevelA, subLevelB, p, subLevelSize);

                if (pair.getValue().getKey().equals(searchKey)) {
                    System.out.println("Second floor index: " + (index + 3));
                }

                if (perfectHashMap[i].isSomethingByIndex(index)) {
                    isCollision = true;
                    break;
                } else {
                    perfectHashMap[i].setByIndex(index, pair);
                }
            }
        } while (isCollision);
    }

    public void getHashTableSize() {
        System.out.println("Main HashMap size: " + perfectHashMap.length);

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

    public Pair<?, ?> get(String key) {
        int rowIndex = ValuesGenerator.generateUniversalHash(Math.abs(key.hashCode()), a, b, p, m);

        int subLevelSize = perfectHashMap[rowIndex].getM();
        int subLevelA = perfectHashMap[rowIndex].getA();
        int subLevelB = perfectHashMap[rowIndex].getB();
        int columnIndex = ValuesGenerator.generateUniversalHash(Math.abs(key.hashCode()), subLevelA, subLevelB, p, subLevelSize);

        System.out.println("Row index: " + rowIndex);
        System.out.println("Column index: " + (columnIndex + 3));

        return (Pair<?, ?>) (perfectHashMap[rowIndex].get(columnIndex).getValue());
    }
}