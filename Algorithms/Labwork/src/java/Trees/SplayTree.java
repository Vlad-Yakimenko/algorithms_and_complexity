package Trees;

public class SplayTree<K extends Comparable<K>, V> extends AbstractTree<K, V> {

    public SplayTree() {
        this.root = null;
        this.NIL = null;
    }

    @Override
    public V search(K key) {
        Node temp = searchNode(this.root, key);

        if (temp == null) {
            throw new IllegalArgumentException("This key doesn't exist!");
        } else {
            splay(temp);
            return temp.getValue();
        }
    }

    @Override
    public void insert(K key, V value) {
        Node preInsertPlace = null;
        Node insertPlace = root;

        while (insertPlace != null) {
            preInsertPlace = insertPlace;

            if (insertPlace.getKey().compareTo(key) < 0) {
                insertPlace = insertPlace.getRight();
            } else {
                insertPlace = insertPlace.getLeft();
            }
        }

        Node insertElement = new Node(key, value);
        insertElement.setParent(preInsertPlace);

        if (preInsertPlace == null) {
            root = insertElement;
        } else if (preInsertPlace.getKey().compareTo(insertElement.getKey()) < 0) {
            preInsertPlace.setRight(insertElement);
        } else {
            preInsertPlace.setLeft(insertElement);
        }

        splay(insertElement);
    }

    @Override
    public void delete(K key) {
        Node removeElement = searchNode(this.root, key);

        if (removeElement != null) {
            if (removeElement.getRight() == null)
                transplant(removeElement, removeElement.getLeft());
            else if (removeElement.getLeft() == null)
                transplant(removeElement, removeElement.getRight());
            else {
                Node newLocalRoot = treeMinimum(removeElement.getRight());

                if (newLocalRoot.getParent() != removeElement) {

                    transplant(newLocalRoot, newLocalRoot.getRight());

                    newLocalRoot.setRight(removeElement.getRight());
                    newLocalRoot.getRight().setParent(newLocalRoot);
                }

                transplant(removeElement, newLocalRoot);

                newLocalRoot.setLeft(removeElement.getLeft());
                newLocalRoot.getLeft().setParent(newLocalRoot);

                splay(newLocalRoot);
            }
        }
    }

    private void splay(Node node) {
        while (node != root) {
            if (node.getParent() == root) {
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
    public void print() {
        splayTreePrintMethod(this.root, 0);
    }

    private void splayTreePrintMethod(Node node, int n) {
        if (node != this.NIL) {
            splayTreePrintMethod(node.getRight(), n + 10);

            for (int i = 0; i < n; i++) {
                System.out.print(" ");
            }
            System.out.println("" + node.getKey() + node.getValue());

            splayTreePrintMethod(node.getLeft(), n + 10);
        }
    }
}