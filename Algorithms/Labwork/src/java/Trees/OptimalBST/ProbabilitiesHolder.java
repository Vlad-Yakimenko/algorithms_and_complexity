package Trees.OptimalBST;

import java.util.AbstractMap.SimpleEntry;

public class ProbabilitiesHolder<K extends Comparable<K>, V> implements Comparable<ProbabilitiesHolder<K, V>> {

    private final SimpleEntry<K, V> data;
    private final double nodeProbability, fictiveProbability;

    public ProbabilitiesHolder(SimpleEntry<K, V> data, double nodeProbability, double fictiveProbability) {
        this.data = data;
        this.nodeProbability = nodeProbability;
        this.fictiveProbability = fictiveProbability;
    }

    public SimpleEntry<K, V> getData() {
        return data;
    }

    public double getNodeProbability() {
        return nodeProbability;
    }

    public double getFictiveProbability() {
        return fictiveProbability;
    }

    @Override
    public int compareTo(ProbabilitiesHolder<K, V> probabilitiesHolder) {
        if (this.getData() == null) return -1;
        else if (this.getData().equals(probabilitiesHolder.getData())) return 0;
        else if (probabilitiesHolder.getData() == null) return 1;
        else return this.getData().getKey().compareTo(probabilitiesHolder.getData().getKey());
    }
}