package Trees;

public abstract class AbstractTree<K extends Comparable<K>, V> {

    private Node root;

    protected class Node {
        private K key;
        private V value;
        private Node parent, left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        Node getRoot() {
            return root;
        }

        void setRoot(Node newRoot) {
            root = newRoot;
        }
    }

    public abstract V search(K value);

    public abstract void insert(K key, V value);

    public abstract void delete(K key);

    @SuppressWarnings("unchecked")
    protected <T extends Node> T searchNode(T root, K key, T border)  {
        T temp = root;

        while (temp != border) {
            if (temp.getKey().equals(key)) {
                return temp;
            } else if (temp.getKey().compareTo(key) < 0) {
                temp = (T) temp.getRight();
            } else {
                temp = (T) temp.getLeft();
            }
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    protected <T extends Node> T treeMinimum(T root, T border) {
        while (root.getLeft() != border) {
            root = (T) root.getLeft();
        }

        return root;
    }

    @SuppressWarnings("SuspiciousNameCombination, unchecked")
    protected <T extends Node> void leftRotate(T x, T border) {
        T y = (T) x.getRight();
        x.setRight(y.getLeft());

        if (y.getLeft() != border) {
            y.getLeft().setParent(x);
        }

        y.setParent(x.getParent());

        if (x.getParent() == border) {
            x.setRoot(y);
        } else if (x == x.getParent().getLeft()) {
            x.getParent().setLeft(y);
        } else {
            x.getParent().setRight(y);
        }

        y.setLeft(x);
        x.setParent(y);
    }

    @SuppressWarnings("SuspiciousNameCombination, unchecked")
    protected <T extends Node> void rightRotate(T y, T border) {
        T x = (T) y.getLeft();
        y.setLeft(x.getRight());

        if (x.getRight() != border) {
            x.getRight().setParent(y);
        }

        x.setParent(y.getParent());

        if (y.getParent() == border) {
            y.setRoot(x);
        } else if (y == y.getParent().getRight()) {
            y.getParent().setRight(x);
        } else {
            y.getParent().setLeft(x);
        }

        x.setRight(y);
        y.setParent(x);
    }

    public void print() {
        AbstractTreePrintMethod(this.root, 0);
    }

    private void AbstractTreePrintMethod(AbstractTree<? extends Comparable<?>, ?>.Node node, int n) {
        if (node != null) {
            AbstractTreePrintMethod(node.right, n + 10);

            for (int i = 0; i < n; i++) {
                System.out.print(" ");
            }
            System.out.println(node.getKey());

            AbstractTreePrintMethod(node.left, n + 10);
        }
    }
}