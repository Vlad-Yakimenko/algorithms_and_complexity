package Trees;

public abstract class AbstractTree<K extends Comparable<K>, V> {

    protected Node root;
    protected final Node NIL;

    protected class Node {
        private K key;
        private V value;
        private boolean color;
        public Node parent, left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.color = true;
            parent = left = right = NIL;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return value;
        }

        public boolean getColor() {
            return this.color;
        }

        public void setColorBlack() {
            this.color = true;
        }

        public void setColorRed() {
            this.color = false;
        }
    }

    public AbstractTree() {
        this.NIL = new Node(null, null);
        this.root = NIL;
    }

    @SuppressWarnings("SuspiciousNameCombination")
    protected void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;

        if (y.left != this.NIL) {
            y.left.parent = x;
        }

        y.parent = x.parent;

        if (x.parent == this.NIL) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }

        y.left = x;
        x.parent = y;
    }

    @SuppressWarnings("SuspiciousNameCombination")
    protected void rightRotate(Node y) {
        Node x = y.left;
        y.left = x.right;

        if (x.right != this.NIL) {
            x.right.parent = y;
        }

        x.parent = y.parent;

        if (y.parent == this.NIL) {
            this.root = x;
        } else if (y == y.parent.right) {
            y.parent.right = x;
        } else {
            y.parent.left = x;
        }

        x.right = y;
        y.parent = x;
    }

    protected Node treeMinimum(Node x) {
        while (x.left != this.NIL) {
            x = x.left;
        }

        return x;
    }

    public void print() {
        Node temp = this.root;

        encapsulatedPrintMethod(temp, 0);
    }

    private void encapsulatedPrintMethod(AbstractTree<? extends Comparable<?>, ?>.Node node, int n) {
        if (node != null) {
            encapsulatedPrintMethod(node.right, n + 10);

            for (int i = 0; i < n; i++) {
                System.out.print(" ");
            }
            System.out.println("" + node.getKey() + "=" + node.getColor());

            encapsulatedPrintMethod(node.left, n + 10);
        }
    }
}