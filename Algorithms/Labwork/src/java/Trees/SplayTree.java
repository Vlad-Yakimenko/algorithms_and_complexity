package Trees;

public class SplayTree<K extends Comparable<K>, V> extends AbstractBinaryTree<K, V> {

    public SplayTree() {
        this.root = null;
        this.NIL = null;
    }

    @Override
    public void insert(K key, V value) {
        Node currentNode = this.root, previous = null;

        while (currentNode != null) {
            previous = currentNode;

            if (currentNode.getKey().compareTo(key) < 0) {
                currentNode = currentNode.getRight();
            } else {
                currentNode = currentNode.getLeft();
            }
        }

        Node insertElement = new Node(key, value);
        insertElement.setParent(previous);

        if (previous == null) {
            this.root = insertElement;
        } else if (previous.getKey().compareTo(insertElement.getKey()) < 0) {
            previous.setRight(insertElement);
        } else {
            previous.setLeft(insertElement);
        }

        splay(insertElement);
    }

    @Override
    public V delete(K key) {
        Node deletedNode = searchNode(this.root, key);

        if (deletedNode != null) {
            if (deletedNode.getRight() == null)
                transplant(deletedNode, deletedNode.getLeft());
            else if (deletedNode.getLeft() == null)
                transplant(deletedNode, deletedNode.getRight());
            else {
                Node newLocalRoot = treeMinimum(deletedNode.getRight());

                if (newLocalRoot.getParent() != deletedNode) {
                    transplant(newLocalRoot, newLocalRoot.getRight());
                    newLocalRoot.setRight(deletedNode.getRight());
                    newLocalRoot.getRight().setParent(newLocalRoot);
                }

                transplant(deletedNode, newLocalRoot);
                newLocalRoot.setLeft(deletedNode.getLeft());
                newLocalRoot.getLeft().setParent(newLocalRoot);

                splay(newLocalRoot);
            }

            return deletedNode.getValue();
        }

        return null;
    }

    private void splay(Node node) {
        while (node != this.root) {
            if (node.getParent() == this.root) {
                if (node == node.getParent().getLeft())
                    rightRotate(node.getParent());
                else if (node == node.getParent().getRight()) {
                    leftRotate(node.getParent());
                }
            } else {
                if (node == node.getParent().getLeft() &&
                        node.getParent() == node.getParent().getParent().getLeft()) {

                    rightRotate(node.getParent().getParent());
                    rightRotate(node.getParent());

                } else if (node == node.getParent().getRight() &&
                        node.getParent() == node.getParent().getParent().getRight()) {

                    leftRotate(node.getParent().getParent());
                    leftRotate(node.getParent());

                } else if (node == node.getParent().getRight() &&
                        node.getParent() == node.getParent().getParent().getLeft()) {

                    leftRotate(node.getParent());
                    rightRotate(node.getParent());

                } else if (node == node.getParent().getLeft() &&
                        node.getParent() == node.getParent().getParent().getRight()) {

                    rightRotate(node.getParent());
                    leftRotate(node.getParent());
                }
            }
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
            print(treeImagination, node.getRight(), n + 10);

            treeImagination.append('\n').append(" ".repeat(Math.max(0, n)));
            treeImagination.append(node);

            print(treeImagination, node.getLeft(), n + 10);
        }
    }
}