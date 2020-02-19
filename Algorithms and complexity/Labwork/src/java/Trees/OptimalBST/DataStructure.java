package Trees.OptimalBST;

import javafx.util.Pair;

public class DataStructure<K extends Comparable<K>, V> implements Comparable<DataStructure<K, V>> {

    private Pair<K, V> data;
    private double nodeProbability, fictiveProbability;

    public DataStructure(Pair<K, V> data, double nodeProbability, double fictiveProbability) {
        this.data = data;
        this.nodeProbability = nodeProbability;
        this.fictiveProbability = fictiveProbability;
    }

    public Pair<K, V> getData() {
        return data;
    }

    public double getNodeProbability() {
        return nodeProbability;
    }

    public double getFictiveProbability() {
        return fictiveProbability;
    }

    @Override
    public int compareTo(DataStructure<K, V> kvDataStructure) {
        if (this.getData() == null) return -1;
        else if (this.getData().equals(kvDataStructure.getData())) return 0;
        else if (kvDataStructure.getData() == null) return 1;
        else return this.getData().getKey().compareTo(kvDataStructure.getData().getKey());
    }
}