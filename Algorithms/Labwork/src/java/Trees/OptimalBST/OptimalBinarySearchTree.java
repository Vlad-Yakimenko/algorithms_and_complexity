package Trees.OptimalBST;

import Trees.AbstractTree;
import javafx.util.Pair;

import java.util.*;

public class OptimalBinarySearchTree<K extends Comparable<K>, V> extends AbstractTree<K, V> {

    private List<DataStructure<K, V>> staticList = new ArrayList<>();
    private int[][] optimalRoot;

    public OptimalBinarySearchTree(List<Pair<K, V>> values, double[] nodeProbabilities, double[] fictiveProbabilities) {
        staticList.add(new DataStructure<>(null, 0, fictiveProbabilities[0]));

        for (int i = 0; i < values.size(); i++) {
            staticList.add(new DataStructure<>(values.get(i), nodeProbabilities[i], fictiveProbabilities[i + 1]));
        }

        staticList.sort(DataStructure::compareTo);

        this.optimalRoot = optimalBST();

        buildOptimalBST(this.root, 1, optimalRoot[1][staticList.size() - 1], staticList.size() - 1);
    }

    @Override
    public V search(K key) {
        return searchNode(this.root, key).getValue();
    }

    @Override
    public void insert(K key, V value) {
        throw new UnsupportedOperationException("You can't insert new values in static set.");
    }

    @Override
    public V delete(K key) {
        throw new UnsupportedOperationException("You can't delete values from static set.");
    }

    private int[][] optimalBST() {
        double[][] expectation = new double[staticList.size() + 1][staticList.size()],
        sumOfProbabilities = new double[staticList.size() + 1][staticList.size()];

        int[][] root = new int[staticList.size()][staticList.size()];

        for (int i = 1; i <= staticList.size(); i++) {
            expectation[i][i - 1] = staticList.get(i - 1).getFictiveProbability();
            sumOfProbabilities[i][i - 1] = staticList.get(i - 1).getFictiveProbability();
        }

        for (int l = 1; l <= staticList.size() - 1; l++) {
            for (int i = 1; i <= staticList.size() - l; i++) {
                int j = i + l - 1;
                expectation[i][j] = Integer.MAX_VALUE;
                DataStructure<K, V> jIndex = staticList.get(j);
                sumOfProbabilities[i][j] = sumOfProbabilities[i][j - 1] + jIndex.getNodeProbability() + jIndex.getFictiveProbability();

                for (int r = i; r <= j; r++) {
                    double t = expectation[i][r - 1] + expectation[r + 1][j] + sumOfProbabilities[i][j];

                    if (t < expectation[i][j]) {
                        expectation[i][j] = t;
                        root[i][j] = r;
                    }
                }
            }
        }

        return root;
    }

    private void buildOptimalBST(Node root, int i, int r, int j) {
        if (i > 0) {
            int index = optimalRoot[i][j];
            Pair<K, V> data = staticList.get(index).getData();

            if (root == null) {
                this.root = new Node(data.getKey(), data.getValue());
                root = this.root;
            }

            int leftIndex = optimalRoot[i][r - 1];
            if (leftIndex != 0) {
                Pair<K, V> leftData = staticList.get(leftIndex).getData();
                Node left = new Node(leftData.getKey(), leftData.getValue());
                root.setLeft(left);
                left.setParent(root);
                buildOptimalBST(left, i, leftIndex, r - 1);
            }

            if (r < optimalRoot.length - 1) {
                int rightIndex = optimalRoot[r + 1][j];
                if (rightIndex != 0) {
                    Pair<K, V> rightData = staticList.get(rightIndex).getData();
                    Node right = new Node(rightData.getKey(), rightData.getValue());
                    root.setRight(right);
                    right.setParent(root);
                    buildOptimalBST(right, r + 1, rightIndex, j);
                }
            }
        }
    }
}