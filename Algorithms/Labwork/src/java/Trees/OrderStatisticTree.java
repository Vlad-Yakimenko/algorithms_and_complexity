package Trees;

public class OrderStatisticTree<K extends Comparable<K>, V> extends RedBlackTree<K, V> {

    class OSNode extends RBNode {
        int size;
        private OSNode parent, left, right;

        public OSNode(K key, V value) {
            super(key, value);
            parent = left = right = (OSNode) NIL;
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
            root = newRoot;
        }

        @Override
        public String toString() {
            return "" + this.getKey() + "=size:" + this.size;
        }
    }

    public OrderStatisticTree() {
        this.NIL = null;
        this.NIL = new OSNode(null, null);
        this.root = NIL;
    }

    @Override
    public V search(K key) {
        OSNode searchNode = searchNode((OSNode) this.root, key);

        if (searchNode != null) {
            return searchNode.getValue();
        } else {
            return null;
        }
    }

    @Override
    public void insert(K key, V value) {
        OSNode node = new OSNode(key, value);
        node.incrementSize();
        increaseSizes(key);

        insertAlgorithm(node);
    }

    @Override
    public V delete(K key) {
        OSNode deletedNode = searchNode((OSNode) this.root, key);

        if (deletedNode != null) {
            decreaseSizes(key);
            deleteAlgorithmOS(deletedNode);
            return deletedNode.getValue();
        } else {
            return null;
        }
    }

    protected void deleteAlgorithmOS(OSNode deletedNode) {
        OSNode x, y;
        boolean yOriginalColor;

        if (deletedNode != null) {
            y = deletedNode;
            yOriginalColor = y.getColor();

            if (deletedNode.getLeft() == NIL) {
                x = deletedNode.getRight();
                RBTreeTransplant(deletedNode, deletedNode.getRight());

                if (x != NIL) {
                    x.size = x.left.size + x.right.size + 1;
                }
            } else if (deletedNode.getRight() == NIL) {
                x = deletedNode.getLeft();
                RBTreeTransplant(deletedNode, deletedNode.getLeft());

                if (x != NIL) {
                    x.size = x.left.size + x.right.size + 1;
                }
            } else {
                y = treeMinimumOS(deletedNode.getRight());
                yOriginalColor = y.getColor();
                x = y.getRight();

                if (y.getParent() != deletedNode) {
                    RBTreeTransplant(y, y.getRight());
                    y.setRight(deletedNode.getRight());
                    y.getRight().setParent(y);
                } else {
                    x.setParent(y);
                }

                RBTreeTransplant(deletedNode, y);
                y.setLeft(deletedNode.getLeft());
                y.getLeft().setParent(y);
                y.size = y.left.size + y.right.size + 1;

                if (deletedNode.getColor()) {
                    y.setColorBlack();
                } else {
                    y.setColorRed();
                }
            }

            if (yOriginalColor) {
                deleteFixup((OSNode) deletedNode.getRoot(), x);
            }
        }
    }

    public OSNode selectOST(int rank) {
        if (rank < 1) {
            throw new IllegalArgumentException("Illegal rank: " + rank);
        }

        return recursiveSelect((OSNode) this.root, rank);
    }

    private OSNode recursiveSelect(OSNode root, int rank) {
        if (root != null && root.left != null) {
            int temp = root.left.size + 1;

            if (rank == temp) {
                return root;
            } else if (rank < temp) {
                return recursiveSelect(root.getLeft(), rank);
            } else {
                return recursiveSelect(root.getRight(), rank - temp);
            }
        } else {
            return null;
        }
    }

    public int getRank(K key) {
        OSNode node = searchNode((OSNode) this.root, key);

        if (node != null) {
            int rank = node.getLeft().size + 1;

            while (node != this.root) {
                if (node == node.getParent().getRight()) {
                    rank += node.getParent().getLeft().size + 1;
                }

                node = node.getParent();
            }

            return rank;
        } else {
            return -1;
        }
    }

    private void increaseSizes(K key) {
        OSNode temp = (OSNode) this.root;

        while (temp != this.NIL) {
            temp.incrementSize();
            temp = temp.getKey().compareTo(key) <= 0 ? temp.getRight() : temp.getLeft();
        }
    }

    private void decreaseSizes(K key) {
        OSNode temp = (OSNode) this.root;

        while (temp != this.NIL) {
            temp.decrementSize();
            if (temp.getKey().equals(key)) break;
            temp = temp.getKey().compareTo(key) <= 0 ? temp.getRight() : temp.getLeft();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    protected <T extends Node> void leftRotate(T root) {
        OSNode temp = (OSNode) root;
        OSNode newRoot = temp.getRight();

        super.leftRotate(root);

        newRoot.size = temp.size;
        temp.size = temp.left.size + temp.right.size + 1;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected <T extends Node> void rightRotate(T root) {
        OSNode temp = (OSNode) root;
        OSNode newRoot = temp.getLeft();

        super.rightRotate(root);

        newRoot.size = temp.size;
        temp.size = temp.left.size + temp.right.size + 1;
    }

    private OSNode treeMinimumOS(OSNode root) {
        while (root.getLeft() != NIL) {
            root.decrementSize();
            root = root.getLeft();
        }

        return root;
    }

    @Override
    public String toString() {
        StringBuilder treeImagination = new StringBuilder();
        print(treeImagination, (OSNode) this.root, 0);
        return treeImagination.toString();
    }

    private void print(StringBuilder treeImagination, OrderStatisticTree<? extends Comparable<?>, ?>.OSNode node, int n) {
        if (node != this.NIL) {
            print(treeImagination, node.right, n + 10);

            treeImagination.append('\n').append(" ".repeat(Math.max(0, n)));
            treeImagination.append(node.getKey()).append("=size:").append(node.size);

            print(treeImagination, node.left, n + 10);
        }
    }
}