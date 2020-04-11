package Trees.OptimalBST;

import Trees.AbstractBinaryTree;

import java.util.*;
import java.util.AbstractMap.SimpleEntry;

public class OptimalBinarySearchTree<K extends Comparable<K>, V> extends AbstractBinaryTree<K, V> {

    private final List<ProbabilitiesHolder<K, V>> staticList = new ArrayList<>();
    private final int[][] optimalTree;

    public OptimalBinarySearchTree(List<SimpleEntry<K, V>> values,
                                   double[] nodeProbabilities, double[] fictiveProbabilities) {

        staticList.add(new ProbabilitiesHolder<>(null, 0, fictiveProbabilities[0]));

        for (int i = 0; i < values.size(); i++) {
            staticList.add(new ProbabilitiesHolder<>(values.get(i), nodeProbabilities[i], fictiveProbabilities[i + 1]));
        }
        staticList.sort(ProbabilitiesHolder::compareTo);

        this.optimalTree = optimalBST();
        buildOptimalBST(this.root, 1, optimalTree[1][staticList.size() - 1], staticList.size() - 1);
    }

    @Override
    public void insert(K key, V value) {
        throw new UnsupportedOperationException("You can't insert new values in immutable set.");
    }

    @Override
    public V delete(K key) {
        throw new UnsupportedOperationException("You can't delete values from immutable set.");
    }

    private int[][] optimalBST() {
        double[][] expectation = new double[staticList.size() + 1][staticList.size()],
        sumOfProbabilities = new double[staticList.size() + 1][staticList.size()];

        int[][] calculatedTree = new int[staticList.size()][staticList.size()];

        for (int i = 1; i <= staticList.size(); i++) {
            expectation[i][i - 1] = staticList.get(i - 1).getFictiveProbability();
            sumOfProbabilities[i][i - 1] = staticList.get(i - 1).getFictiveProbability();
        }

        for (int i = 1; i <= staticList.size() - 1; i++) {
            for (int j = 1; j <= staticList.size() - i; j++) {
                int index = j + i - 1;
                expectation[j][index] = Integer.MAX_VALUE;
                ProbabilitiesHolder<K, V> valueByIndex = staticList.get(index);
                sumOfProbabilities[j][index] = sumOfProbabilities[j][index - 1] +
                                               valueByIndex.getNodeProbability() +
                                               valueByIndex.getFictiveProbability();

                for (int k = j; k <= index; k++) {
                    double t = expectation[j][k - 1] + expectation[k + 1][index] + sumOfProbabilities[j][index];

                    if (t < expectation[j][index]) {
                        expectation[j][index] = t;
                        calculatedTree[j][index] = k;
                    }
                }
            }
        }

        return calculatedTree;
    }

    private void buildOptimalBST(Node root, int i, int r, int j) {
        if (i > 0) {
            int index = optimalTree[i][j];
            SimpleEntry<K, V> data = staticList.get(index).getData();

            if (root == null) {
                this.root = new Node(data.getKey(), data.getValue());
                root = this.root;
            }

            int leftIndex = optimalTree[i][r - 1];
            if (leftIndex != 0) {
                SimpleEntry<K, V> leftData = staticList.get(leftIndex).getData();
                Node left = new Node(leftData.getKey(), leftData.getValue());
                root.setLeft(left);
                left.setParent(root);
                buildOptimalBST(left, i, leftIndex, r - 1);
            }

            if (r < optimalTree.length - 1) {
                int rightIndex = optimalTree[r + 1][j];
                if (rightIndex != 0) {
                    SimpleEntry<K, V> rightData = staticList.get(rightIndex).getData();
                    Node right = new Node(rightData.getKey(), rightData.getValue());
                    root.setRight(right);
                    right.setParent(root);
                    buildOptimalBST(right, r + 1, rightIndex, j);
                }
            }
        }
    }
}