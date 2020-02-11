package main.Trees;

public class OrderStatisticTree<K extends Comparable<K>, V> extends RedBlackTree<K, V> {

    private OSNode root;
    private final OSNode NIL;

    private class OSNode extends RBNode {
        private int size;
        private OSNode parent, left, right;

        public OSNode(K key, V value) {
            super(key, value);
            parent = left = right = NIL;
        }

        public void incrementSize() {
            this.size++;
        }

        public void decrementSize() {
            this.size--;
        }

        @Override
        public OSNode getParent() {
            return parent;
        }

        @Override
        public void setParent(Node parent) {
            this.parent = (OSNode) parent;
        }

        @Override
        public OSNode getLeft() {
            return left;
        }

        @Override
        public void setLeft(Node left) {
            this.left = (OSNode) left;
        }

        @Override
        public OSNode getRight() {
            return right;
        }

        @Override
        public void setRight(Node right) {
            this.right = (OSNode) right;
        }

        @Override
        Node getRoot() {
            return root;
        }

        @Override
        void setRoot(Node newRoot) {
            root = (OSNode) newRoot;
        }

        @Override
        public String toString() {
            return "" + this.getKey() + "=size:" + this.size;
        }
    }

    public OrderStatisticTree() {
        this.NIL = new OSNode(null, null);
        this.root = NIL;
    }

    @Override
    public V search(K key) {
        return searchNode(this.root, key, this.NIL).getValue();
    }

    @Override
    public void insert(K key, V value) {
        OSNode node = new OSNode(key, value);
        node.incrementSize();
        increaseSizes(key);

        insertAlgorithm(node, this.NIL);
    }

    @Override
    public void delete(K key) {
        decreaseSizes(key);
        deleteAlgorithm(searchNode(this.root, key, this.NIL), this.NIL);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected <T extends RBNode> void deleteAlgorithm(T deletedNode, T border) {
        T x, y;
        boolean yOriginalColor;

        if (deletedNode == null) {
            throw new IllegalArgumentException("This node doesn't exist!");
        } else {
            y = deletedNode;
            yOriginalColor = y.getColor();

            if (deletedNode.getLeft() == border) {
                x = (T) deletedNode.getRight();
                RBTreeTransplant(deletedNode, deletedNode.getRight(), border);

                if (x != border) {
                    ((OSNode) x).size = ((OSNode) x).left.size + ((OSNode) x).right.size + 1;
                }
            } else if (deletedNode.getRight() == border) {
                x = (T) deletedNode.getLeft();
                RBTreeTransplant(deletedNode, deletedNode.getLeft(), border);

                if (x != border) {
                    ((OSNode) x).size = ((OSNode) x).left.size + ((OSNode) x).right.size + 1;
                }
            } else {
                y = treeMinimum((T) deletedNode.getRight(), border);
                yOriginalColor = y.getColor();
                x = (T) y.getRight();

                if (y.getParent() != deletedNode) {
                    RBTreeTransplant(y, y.getRight(), border);
                    y.setRight(deletedNode.getRight());
                    y.getRight().setParent(y);
                } else {
                    x.setParent(y);
                }

                RBTreeTransplant(deletedNode, y, border);
                y.setLeft(deletedNode.getLeft());
                y.getLeft().setParent(y);
                ((OSNode) y).size = ((OSNode) y).left.size + ((OSNode) y).right.size + 1;

                if (deletedNode.getColor()) {
                    y.setColorBlack();
                } else {
                    y.setColorRed();
                }
            }

            if (yOriginalColor) {
                deleteFixup((T) deletedNode.getRoot(), x, border);
            }
        }
    }

    public OSNode selectOST(int rank) {
        return recursiveSelect(this.root, rank);
    }

    private OSNode recursiveSelect(OSNode root, int rank) {
        int temp = root.left.size + 1;

        if (rank == temp) {
            return root;
        } else if (rank < temp) {
            return recursiveSelect(root.getLeft(), rank);
        } else {
            return recursiveSelect(root.getRight(), rank - temp);
        }
    }

    public int getRank(K key) {
        OSNode node = searchNode(this.root, key, this.NIL);

        int rank = node.getLeft().size + 1;

        while (node != this.root) {
            if (node == node.getParent().getRight()) {
                rank += node.getParent().getLeft().size + 1;
            }

            node = node.getParent();
        }

        return rank;
    }

    private void increaseSizes(K key) {
        OSNode temp = this.root;

        while (temp != this.NIL) {
            temp.incrementSize();
            temp = temp.getKey().compareTo(key) < 0 ? temp.getRight() : temp.getLeft();
        }
    }

    private void decreaseSizes(K key) {
        OSNode temp = this.root;

        while (temp != this.NIL) {
            temp.decrementSize();
            temp = temp.getKey().compareTo(key) < 0 ? temp.getRight() : temp.getLeft();
            if (temp.getKey() == key) break;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    protected <T extends Node> void leftRotate(T x, T border) {
        OSNode temp = (OSNode) x;
        OSNode y = temp.getRight();

        super.leftRotate(x, border);

        y.size = temp.size;
        temp.size = temp.left.size + temp.right.size + 1;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected <T extends Node> void rightRotate(T y, T border) {
        OSNode temp = (OSNode) y;
        OSNode x = temp.getLeft();

        super.rightRotate(y, border);

        x.size = temp.size;
        temp.size = temp.left.size + temp.right.size + 1;
    }

    @Override
    public void print() {
        orderStatisticTreePrintMethod(this.root, 0);
    }

    private void orderStatisticTreePrintMethod(OrderStatisticTree<? extends Comparable<?>, ?>.OSNode node, int n) {
        if (node != null) {
            orderStatisticTreePrintMethod(node.right, n + 10);

            for (int i = 0; i < n; i++) {
                System.out.print(" ");
            }
            System.out.println("" + node.getKey() + "=size:" + node.size + "=" + node.getValue());

            orderStatisticTreePrintMethod(node.left, n + 10);
        }
    }
}