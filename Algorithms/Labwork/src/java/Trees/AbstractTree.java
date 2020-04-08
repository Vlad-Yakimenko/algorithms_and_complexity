package Trees;

public abstract class AbstractTree<K extends Comparable<K>, V> {

    protected Node root;
    protected Node NIL;

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

    public abstract V search(K key);

    public abstract void insert(K key, V value);

    public abstract V delete(K key);

    @SuppressWarnings("unchecked")
    protected <T extends Node> T searchNode(T root, K key)  {
        T temp = root;

        while (temp != NIL) {
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
    protected <T extends Node> T treeMinimum(T root) {
        while (root.getLeft() != NIL) {
            root = (T) root.getLeft();
        }

        return root;
    }

    @SuppressWarnings("unchecked")
    protected <T extends Node> void leftRotate(T root) {
        T newRoot = (T) root.getRight();
        root.setRight(newRoot.getLeft());

        if (newRoot.getLeft() != NIL) {
            newRoot.getLeft().setParent(root);
        }

        newRoot.setParent(root.getParent());

        if (root.getParent() == NIL) {
            root.setRoot(newRoot);
        } else if (root == root.getParent().getLeft()) {
            root.getParent().setLeft(newRoot);
        } else {
            root.getParent().setRight(newRoot);
        }

        newRoot.setLeft(root);
        root.setParent(newRoot);
    }

    @SuppressWarnings("unchecked")
    protected <T extends Node> void rightRotate(T root) {
        T newRoot = (T) root.getLeft();
        root.setLeft(newRoot.getRight());

        if (newRoot.getRight() != NIL) {
            newRoot.getRight().setParent(root);
        }

        newRoot.setParent(root.getParent());

        if (root.getParent() == NIL) {
            root.setRoot(newRoot);
        } else if (root == root.getParent().getRight()) {
            root.getParent().setRight(newRoot);
        } else {
            root.getParent().setLeft(newRoot);
        }

        newRoot.setRight(root);
        root.setParent(newRoot);
    }

    protected <T extends Node> void transplant(T first, T second) {
        if (first.getParent() == null)
            this.root = second;
        else if (first == first.getParent().getLeft())
            first.getParent().setLeft(second);
        else
            first.getParent().setRight(second);

        if (second != null)
            second.setParent(first.getParent());
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