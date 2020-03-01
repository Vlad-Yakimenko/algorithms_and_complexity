package Heaps;

import java.util.ArrayList;
import java.util.List;

public class FibonacciHeap<K extends Comparable<K>, V> {

    private CircularDoublyLinkedList<Node> roots = new CircularDoublyLinkedList<>();
    private int amountOfNodes = 0;
    private Node min = null;
    private K extraMin;

    public class Node {
        private K key;
        private V value;
        private Node parent = null;
        private CircularDoublyLinkedList<Node> children = new CircularDoublyLinkedList<>();
        private int degree = 0;
        private boolean mark = false;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        Node getParent() {
            return parent;
        }

        CircularDoublyLinkedList<Node> getChildren() {
            return children;
        }

        void setParent(Node parent) {
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "" + this.key;
        }
    }

    public FibonacciHeap(K extraMin) {
        this.extraMin = extraMin;
    }

    public Node insert(K key, V value) {
        Node node = new Node(key, value);

        if (this.min == null) {
            this.min = node;
        } else {
            if (node.key.compareTo(this.min.key) < 0) {
                this.min = node;
            }
        }

        roots.add(node);
        this.amountOfNodes++;
        return node;
    }

    public void delete(Node x) {
        decreaseKey(x, this.extraMin);
        extractMin();
    }

    public void decreaseKey(Node x, K newKey) {
        if (newKey.compareTo(x.getKey()) > 0){
            throw new IllegalArgumentException("New key bigger than old key!");
        }

        x.key = newKey;
        Node y = x.getParent();
        if (y != null && x.getKey().compareTo(y.getKey()) < 0) {
            cut(x, y);
            cascadingCut(y);
        }

        if (x.getKey().compareTo(this.min.getKey()) < 0) {
            this.min = x;
        }
    }

    private void cut(Node x, Node y) {
        CircularDoublyLinkedList<Node> yChildren = y.getChildren();
        yChildren.delete(yChildren.indexOf(x));
        y.degree--;
        this.roots.add(x);
        x.setParent(null);
        x.mark = false;
    }

    private void cascadingCut(Node y) {
        Node z = y.getParent();

        if (z != null) {
            if (!y.mark) {
                y.mark = true;
            } else {
                //noinspection SuspiciousNameCombination
                cut(y, z);
                cascadingCut(z);
            }
        }
    }

    public Node extractMin() {
        Node z = this.min;

        if (z != null) {
            CircularDoublyLinkedList<Node> children = z.getChildren();

            for (int i = 0; i < children.getSize(); i++) {
                Node child = children.get(i);
                roots.add(child);
                child.setParent(null);
            }

            roots.delete(roots.indexOf(z));

            if (roots.getSize() == 0) {
                this.min = null;
            } else {
                this.min = roots.get(0);
                consolidate();
            }

            this.amountOfNodes--;
        }

        return z;
    }

    private void consolidate() {
        List<Node> degreesBuffer = new ArrayList<>();

        for (int i = 0; i <= log2(this.amountOfNodes); i++) {
            degreesBuffer.add(null);
        }

        CircularDoublyLinkedList<Node>.ListNode temp = roots.getNode(0);
        int size = roots.getSize();
        int j = 0;

        while (j < size) {
            Node x = temp.getData();
            int d = x.degree;

            while (degreesBuffer.get(d) != null) {
                Node y = degreesBuffer.get(d);

                if (x.key.compareTo(y.key) > 0) {
                    Node buffer = x;
                    //noinspection SuspiciousNameCombination
                    x = y;
                    y = buffer;
                }

                link(y, x);

                degreesBuffer.set(d, null);
                d++;
            }

            degreesBuffer.set(d, x);
            temp = roots.next(temp);
            j++;
        }

        this.min = null;

        for (int i = 0; i <= log2(this.amountOfNodes); i++) {
            Node node = degreesBuffer.get(i);

            if (node != null) {
                if (this.min == null) {
                    this.roots = new CircularDoublyLinkedList<>();
                    roots.add(node);
                    this.min = node;
                } else {
                    this.roots.add(node);

                    if (node.key.compareTo(this.min.key) < 0) {
                        this.min = node;
                    }
                }
            }
        }
    }

    private void link(Node y, Node x) {
        roots.delete(roots.indexOf(y));
        x.children.add(y);
        y.setParent(x);
        x.degree++;
        y.mark = false;
    }

    private static int log2(int n) {
        return (int) (Math.log(n) / Math.log(2));
    }
}