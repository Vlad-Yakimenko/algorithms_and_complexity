package Trees;

public abstract class AbstractTree<K extends Comparable<K>, V> {

    private Node root;

    protected class Node {
        private K key;
        private V value;
        public Node parent, left, right;

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
    }

    public abstract V search(K value);

    public abstract void insert(K key, V value);

    public abstract void delete(K value);

    public void print() {
        Node temp = this.root;
        AbstractTreePrintMethod(temp, 0);
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