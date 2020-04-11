package Trees;

public abstract class AbstractBinaryTree<K extends Comparable<K>, V> {

    protected Node root;
    protected Node NIL;

    protected class Node {
        private final K key;
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

        public void setValue(V newValue) {
            this.value = newValue;
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

        @Override
        public String toString() {
            return String.valueOf(this.getKey());
        }
    }

    public abstract V search(K key);

    public abstract void insert(K key, V value);

    public abstract V delete(K key);

    protected Node searchNode(Node root, K key)  {
        Node temp = root;

        while (temp != NIL) {
            if (temp.getKey().equals(key)) {
                return temp;
            } else if (temp.getKey().compareTo(key) < 0) {
                temp = temp.getRight();
            } else {
                temp = temp.getLeft();
            }
        }

        return null;
    }

    protected void leftRotate(Node root) {
        Node newRoot = root.getRight();
        root.setRight(newRoot.getLeft());

        if (newRoot.getLeft() != NIL) {
            newRoot.getLeft().setParent(root);
        }

        newRoot.setParent(root.getParent());

        if (root.getParent() == NIL) {
            this.root = newRoot;
        } else if (root == root.getParent().getLeft()) {
            root.getParent().setLeft(newRoot);
        } else {
            root.getParent().setRight(newRoot);
        }

        newRoot.setLeft(root);
        root.setParent(newRoot);
    }

    protected void rightRotate(Node root) {
        Node newRoot = root.getLeft();
        root.setLeft(newRoot.getRight());

        if (newRoot.getRight() != NIL) {
            newRoot.getRight().setParent(root);
        }

        newRoot.setParent(root.getParent());

        if (root.getParent() == NIL) {
            this.root = newRoot;
        } else if (root == root.getParent().getRight()) {
            root.getParent().setRight(newRoot);
        } else {
            root.getParent().setLeft(newRoot);
        }

        newRoot.setRight(root);
        root.setParent(newRoot);
    }

    protected Node treeMinimum(Node root) {
        while (root.getLeft() != NIL) {
            root = root.getLeft();
        }

        return root;
    }

    protected void transplant(Node replacedNode, Node proxy) {
        if (replacedNode.getParent() == null) {
            this.root = proxy;
        } else if (replacedNode == replacedNode.getParent().getLeft()) {
            replacedNode.getParent().setLeft(proxy);
        } else {
            replacedNode.getParent().setRight(proxy);
        }

        if (proxy != null) {
            proxy.setParent(replacedNode.getParent());
        }
    }

    @Override
    public String toString() {
        StringBuilder treeImagination = new StringBuilder();
        print(treeImagination, this.root, 0);
        return treeImagination.toString();
    }

    public void print(StringBuilder treeImagination, Node node, int n) {
        if (node != null) {
            print(treeImagination, node.right, n + 10);

            treeImagination.append('\n').append(" ".repeat(Math.max(0, n)));
            treeImagination.append(node);

            print(treeImagination, node.left, n + 10);
        }
    }
}