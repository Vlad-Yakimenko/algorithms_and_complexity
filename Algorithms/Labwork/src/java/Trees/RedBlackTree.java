package Trees;

public class RedBlackTree<K extends Comparable<K>, V> extends AbstractTree<K, V> {

    protected class RBNode extends Node {
        private boolean color;
        private RBNode parent, left, right;

        public RBNode(K key, V value) {
            super(key, value);
            this.color = true;
            parent = left = right = (RBNode) NIL;
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

        @Override
        public RBNode getParent() {
            return parent;
        }

        @Override
        public void setParent(Node parent) {
            this.parent = (RBNode) parent;
        }

        @Override
        public RBNode getLeft() {
            return left;
        }

        @Override
        public void setLeft(Node left) {
            this.left = (RBNode) left;
        }

        @Override
        public RBNode getRight() {
            return right;
        }

        @Override
        public void setRight(Node right) {
            this.right = (RBNode) right;
        }

        @Override
        Node getRoot() {
            return root;
        }

        @Override
        void setRoot(Node newRoot) {
            root = newRoot;
        }
    }

    public RedBlackTree() {
        this.NIL = new RBNode(null, null);
        this.root = NIL;
    }

    @Override
    public V search(K key) {
        return searchNode(this.root, key).getValue();
    }

    @Override
    public void insert(K key, V value) {
        insertAlgorithm(new RBNode(key, value));
    }

    @SuppressWarnings("SuspiciousNameCombination, unchecked")
    protected <T extends RBNode> void insertAlgorithm(T insertedNode) {
        T x = (T) insertedNode.getRoot(), y = (T) NIL;

        while (x != NIL) {
            y = x;

            if (insertedNode.getKey().compareTo(x.getKey()) < 0) {
                x = (T) x.getLeft();
            } else {
                x = (T) x.getRight();
            }
        }

        insertedNode.setParent(y);

        if (y == NIL) {
            insertedNode.setRoot(insertedNode);
        } else if (insertedNode.getKey().compareTo(y.getKey()) < 0) {
            y.setLeft(insertedNode);
        } else {
            y.setRight(insertedNode);
        }

        insertedNode.setLeft(NIL);
        insertedNode.setRight(NIL);
        insertedNode.setColorRed();

        insertFixup(insertedNode);
    }

    @SuppressWarnings("unchecked")
    private <T extends RBNode> void insertFixup(T currentNode) {
        while (!currentNode.getParent().getColor()) {
            T y;

            if (currentNode.getParent() == currentNode.getParent().getParent().getLeft()) {
                y = (T) currentNode.getParent().getParent().getRight();

                if (!y.getColor()) {
                    currentNode.getParent().setColorBlack();
                    y.setColorBlack();
                    currentNode.getParent().getParent().setColorRed();
                    currentNode = (T) currentNode.getParent().getParent();
                } else {
                    if (currentNode == currentNode.getParent().getRight()) {
                        currentNode = (T) currentNode.getParent();
                        leftRotate(currentNode);
                    }

                    currentNode.getParent().setColorBlack();
                    currentNode.getParent().getParent().setColorRed();
                    rightRotate(currentNode.getParent().getParent());
                }
            } else {
                y = (T) currentNode.getParent().getParent().getLeft();

                if (!y.getColor()) {
                    currentNode.getParent().setColorBlack();
                    y.setColorBlack();
                    currentNode.getParent().getParent().setColorRed();
                    currentNode = (T) currentNode.getParent().getParent();
                } else {
                    if (currentNode == currentNode.getParent().getLeft()) {
                        currentNode = (T) currentNode.getParent();
                        rightRotate(currentNode);
                    }

                    currentNode.getParent().setColorBlack();
                    currentNode.getParent().getParent().setColorRed();
                    leftRotate(currentNode.getParent().getParent());
                }
            }
        }

        ((RBNode) currentNode.getRoot()).setColorBlack();
    }

    @Override
    public void delete(K key) {
        deleteAlgorithm(searchNode((RBNode) this.root, key));
    }

    @SuppressWarnings("unchecked")
    protected <T extends RBNode> void deleteAlgorithm(T deletedNode) {
        T x, y;
        boolean yOriginalColor;

        if (deletedNode == null) {
            throw new IllegalArgumentException("This node doesn't exist!");
        } else {
            y = deletedNode;
            yOriginalColor = y.getColor();

            if (deletedNode.getLeft() == NIL) {
                x = (T) deletedNode.getRight();
                RBTreeTransplant(deletedNode, deletedNode.getRight());
            } else if (deletedNode.getRight() == NIL) {
                x = (T) deletedNode.getLeft();
                RBTreeTransplant(deletedNode, deletedNode.getLeft());
            } else {
                y = treeMinimum((T) deletedNode.getRight());
                yOriginalColor = y.getColor();
                x = (T) y.getRight();

                if (y.getParent() == deletedNode) {
                    x.setParent(y);
                } else {
                    RBTreeTransplant(y, y.getRight());
                    y.setRight(deletedNode.getRight());
                    y.getRight().setParent(y);
                }

                RBTreeTransplant(deletedNode, y);
                y.setLeft(deletedNode.getLeft());
                y.getLeft().setParent(y);

                if (deletedNode.getColor()) {
                    y.setColorBlack();
                } else {
                    y.setColorRed();
                }
            }

            if (yOriginalColor) {
                deleteFixup((T) deletedNode.getRoot(), x);
            }
        }
    }

    @SuppressWarnings("unchecked")
    protected <T extends RBNode> void deleteFixup(T root, T currentNode) {
        T temp;

        while (currentNode != root && currentNode.getColor()) {
            if (currentNode == currentNode.getParent().getLeft()) {
                temp = (T) currentNode.getParent().getRight();

                if (!temp.getColor()) {
                    temp.setColorBlack();
                    currentNode.getParent().setColorRed();
                    leftRotate(currentNode.getParent());
                    temp = (T) currentNode.getParent().getRight();
                }

                if (temp.getLeft().getColor() && temp.getRight().getColor()) {
                    temp.setColorRed();
                    currentNode = (T) currentNode.getParent();
                } else {
                    if (temp.getRight().getColor()) {
                        temp.getLeft().setColorBlack();
                        temp.setColorRed();
                        rightRotate(temp);
                        temp = (T) currentNode.getParent().getRight();
                    }

                    if (currentNode.getParent().getColor()) {
                        temp.setColorBlack();
                    } else {
                        temp.setColorRed();
                    }

                    currentNode.getParent().setColorBlack();
                    temp.getRight().setColorBlack();
                    leftRotate(currentNode.getParent());
                    currentNode = root;
                }
            } else {
                temp = (T) currentNode.getParent().getLeft();

                if (!temp.getColor()) {
                    temp.setColorBlack();
                    currentNode.getParent().setColorRed();
                    rightRotate(currentNode.getParent());
                    temp = (T) currentNode.getParent().getLeft();
                }

                if (temp.getRight().getColor() && temp.getLeft().getColor()) {
                    temp.setColorRed();
                    currentNode = (T) currentNode.getParent();
                } else {
                    if (temp.getLeft().getColor()) {
                        temp.getRight().setColorBlack();
                        temp.setColorRed();
                        leftRotate(temp);
                        temp = (T) currentNode.getParent().getLeft();
                    }

                    if (currentNode.getParent().getColor()) {
                        temp.setColorBlack();
                    } else {
                        temp.setColorRed();
                    }

                    currentNode.getParent().setColorBlack();
                    temp.getLeft().setColorBlack();
                    rightRotate(currentNode.getParent());
                    currentNode = root;
                }
            }
        }

        currentNode.setColorBlack();
    }

    protected <T extends RBNode> void RBTreeTransplant(T first, T second) {
        if (first.getParent() == NIL) {
            first.setRoot(second);
        } else if (first == first.getParent().getLeft()) {
            first.getParent().setLeft(second);
        } else {
            first.getParent().setRight(second);
        }

        second.setParent(first.getParent());
    }

    @Override
    public void print() {
        redBlackTreePrintMethod((RBNode) this.root, 0);
    }

    private void redBlackTreePrintMethod(RedBlackTree<? extends Comparable<?>, ?>.RBNode node, int n) {
        if (node != null) {
            redBlackTreePrintMethod(node.right, n + 10);

            for (int i = 0; i < n; i++) {
                System.out.print(" ");
            }
            System.out.println("" + node.getKey() + "=" + node.getColor());

            redBlackTreePrintMethod(node.left, n + 10);
        }
    }
}