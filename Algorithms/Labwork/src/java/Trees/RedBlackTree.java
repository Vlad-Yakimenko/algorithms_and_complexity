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
        public String toString() {
            return this.getKey() + "=" + this.getColor();
        }
    }

    public RedBlackTree() {
        this.NIL = new RBNode(null, null);
        this.root = NIL;
    }

    @Override
    public V search(K key) {
        Node searchNode = searchNode(this.root, key);

        if (searchNode != null) {
            return searchNode.getValue();
        } else {
            return null;
        }
    }

    @Override
    public void insert(K key, V value) {
        RBNode searchNode = (RBNode) searchNode(this.root, key);

        if (searchNode == null) {
            insertRBNode(new RBNode(key, value));
        } else {
            searchNode.setValue(value);
        }
    }

    protected void insertRBNode(RBNode insertedNode) {
        RBNode currentNode = (RBNode) this.root, previousNode = (RBNode) NIL;

        while (currentNode != NIL) {
            previousNode = currentNode;

            if (insertedNode.getKey().compareTo(currentNode.getKey()) < 0) {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
        }

        insertedNode.setParent(previousNode);

        if (previousNode == NIL) {
            this.root = insertedNode;
        } else if (insertedNode.getKey().compareTo(previousNode.getKey()) < 0) {
            previousNode.setLeft(insertedNode);
        } else {
            previousNode.setRight(insertedNode);
        }

        insertedNode.setLeft(NIL);
        insertedNode.setRight(NIL);
        insertedNode.setColorRed();

        insertFixup(insertedNode);
    }

    private void insertFixup(RBNode currentNode) {
        while (!currentNode.getParent().getColor()) {
            if (currentNode.getParent() == currentNode.getParent().getParent().getLeft()) {
                RBNode rightUncle = currentNode.getParent().getParent().getRight();
                currentNode = fixPropertiesInsert(currentNode, rightUncle, true);
            } else {
                RBNode leftUncle = currentNode.getParent().getParent().getLeft();
                currentNode = fixPropertiesInsert(currentNode, leftUncle, false);
            }
        }

        ((RBNode) this.root).setColorBlack();
    }

    protected RBNode fixPropertiesInsert(RBNode currentNode, RBNode uncle, boolean isParentLeftChild) {
        if (isParentLeftChild) {
            if (!uncle.getColor()) {
                currentNode.getParent().setColorBlack();
                uncle.setColorBlack();
                currentNode.getParent().getParent().setColorRed();
                currentNode = currentNode.getParent().getParent();
            } else {
                if (currentNode == currentNode.getParent().getRight()) {
                    currentNode = currentNode.getParent();
                    leftRotate(currentNode);
                }

                currentNode.getParent().setColorBlack();
                currentNode.getParent().getParent().setColorRed();
                rightRotate(currentNode.getParent().getParent());
            }
        } else {
            if (!uncle.getColor()) {
                currentNode.getParent().setColorBlack();
                uncle.setColorBlack();
                currentNode.getParent().getParent().setColorRed();
                currentNode = currentNode.getParent().getParent();
            } else {
                if (currentNode == currentNode.getParent().getLeft()) {
                    currentNode = currentNode.getParent();
                    rightRotate(currentNode);
                }

                currentNode.getParent().setColorBlack();
                currentNode.getParent().getParent().setColorRed();
                leftRotate(currentNode.getParent().getParent());
            }
        }

        return currentNode;
    }

    @Override
    public V delete(K key) {
        RBNode deletedNode = (RBNode) searchNode(this.root, key);

        if (deletedNode != null) {
            deleteRBNode(deletedNode);
            return deletedNode.getValue();
        } else {
            return null;
        }
    }

    protected void deleteRBNode(RBNode deletedNode) {
        RBNode suspect, suspectsProxy;
        boolean suspectsOriginalColor;

        if (deletedNode != null) {
            suspect = deletedNode;
            suspectsOriginalColor = suspect.getColor();

            if (deletedNode.getLeft() == NIL) {
                suspectsProxy = deletedNode.getRight();
                transplantRBTree(deletedNode, deletedNode.getRight());
            } else if (deletedNode.getRight() == NIL) {
                suspectsProxy = deletedNode.getLeft();
                transplantRBTree(deletedNode, deletedNode.getLeft());
            } else {
                suspect = (RBNode) treeMinimum(deletedNode.getRight());
                suspectsOriginalColor = suspect.getColor();
                suspectsProxy = replaceDeletedNode(deletedNode, suspect);

                if (deletedNode.getColor()) {
                    suspect.setColorBlack();
                } else {
                    suspect.setColorRed();
                }
            }

            if (suspectsOriginalColor) {
                deleteFixup(suspectsProxy);
            }
        }
    }

    protected RBNode replaceDeletedNode(RBNode deletedNode, RBNode suspect) {
        RBNode suspectsProxy = suspect.getRight();

        if (suspect.getParent() == deletedNode) {
            suspectsProxy.setParent(suspect);
        } else {
            transplantRBTree(suspect, suspect.getRight());
            suspect.setRight(deletedNode.getRight());
            suspect.getRight().setParent(suspect);
        }

        transplantRBTree(deletedNode, suspect);
        suspect.setLeft(deletedNode.getLeft());
        suspect.getLeft().setParent(suspect);

        return suspectsProxy;
    }

    protected void deleteFixup(RBNode currentNode) {
        RBNode sibling;

        while (currentNode != this.root && currentNode.getColor()) {
            if (currentNode == currentNode.getParent().getLeft()) {
                sibling = currentNode.getParent().getRight();

                if (!sibling.getColor()) {
                    sibling.setColorBlack();
                    currentNode.getParent().setColorRed();
                    leftRotate(currentNode.getParent());
                    sibling = currentNode.getParent().getRight();
                }

                if (sibling.getLeft().getColor() && sibling.getRight().getColor()) {
                    sibling.setColorRed();
                    currentNode = currentNode.getParent();
                } else {
                    if (sibling.getRight().getColor()) {
                        sibling.getLeft().setColorBlack();
                        sibling.setColorRed();
                        rightRotate(sibling);
                        sibling = currentNode.getParent().getRight();
                    }

                    if (currentNode.getParent().getColor()) {
                        sibling.setColorBlack();
                    } else {
                        sibling.setColorRed();
                    }

                    currentNode.getParent().setColorBlack();
                    sibling.getRight().setColorBlack();
                    leftRotate(currentNode.getParent());
                    currentNode = (RBNode) this.root;
                }
            } else {
                sibling = currentNode.getParent().getLeft();

                if (!sibling.getColor()) {
                    sibling.setColorBlack();
                    currentNode.getParent().setColorRed();
                    rightRotate(currentNode.getParent());
                    sibling = currentNode.getParent().getLeft();
                }

                if (sibling.getRight().getColor() && sibling.getLeft().getColor()) {
                    sibling.setColorRed();
                    currentNode = currentNode.getParent();
                } else {
                    if (sibling.getLeft().getColor()) {
                        sibling.getRight().setColorBlack();
                        sibling.setColorRed();
                        leftRotate(sibling);
                        sibling = currentNode.getParent().getLeft();
                    }

                    if (currentNode.getParent().getColor()) {
                        sibling.setColorBlack();
                    } else {
                        sibling.setColorRed();
                    }

                    currentNode.getParent().setColorBlack();
                    sibling.getLeft().setColorBlack();
                    rightRotate(currentNode.getParent());
                    currentNode = (RBNode) this.root;
                }
            }
        }

        currentNode.setColorBlack();
    }

    protected void transplantRBTree(RBNode replacedNode, RBNode proxy) {
        if (replacedNode.getParent() == NIL) {
            this.root = proxy;
        } else if (replacedNode == replacedNode.getParent().getLeft()) {
            replacedNode.getParent().setLeft(proxy);
        } else {
            replacedNode.getParent().setRight(proxy);
        }

        proxy.setParent(replacedNode.getParent());
    }

    @Override
    public String toString() {
        StringBuilder treeImagination = new StringBuilder();
        print(treeImagination, (RBNode) this.root, 0);
        return treeImagination.toString();
    }

    private void print(StringBuilder treeImagination, RBNode node, int n) {
        if (node != null) {
            print(treeImagination, node.right, n + 10);

            treeImagination.append('\n').append(" ".repeat(Math.max(0, n)));
            treeImagination.append(node);

            print(treeImagination, node.left, n + 10);
        }
    }
}