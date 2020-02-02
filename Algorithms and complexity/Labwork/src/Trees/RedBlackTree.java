package Trees;

public class RedBlackTree<K extends Comparable<K>, V> extends AbstractTree<K, V> {

    public V search(K value) {
        Node temp = this.root;

        while (temp != this.NIL) {
            if (temp.getKey().equals(value)) {
                return temp.getValue();
            } else if (temp.getKey().compareTo(value) < 0) {
                temp = temp.right;
            } else {
                temp = temp.left;
            }
        }

        return null;
    }

    private Node searchNode(K value) {
        Node temp = this.root;

        while (temp != this.NIL) {
            if (temp.getKey().equals(value)) {
                return temp;
            } else if (temp.getKey().compareTo(value) < 0) {
                temp = temp.right;
            } else {
                temp = temp.left;
            }
        }

        return null;
    }

    @SuppressWarnings("SuspiciousNameCombination")
    public void insert(K key, V value) {
        Node insertedNode = new Node(key, value);

        Node x = this.root, y = this.NIL;

        while (x != this.NIL) {
            y = x;

            if (insertedNode.getKey().compareTo(x.getKey()) < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        insertedNode.parent = y;

        if (y == this.NIL) {
            this.root = insertedNode;
        } else if (insertedNode.getKey().compareTo(y.getKey()) < 0) {
            y.left = insertedNode;
        } else {
            y.right = insertedNode;
        }

        insertedNode.left = this.NIL;
        insertedNode.right = this.NIL;
        insertedNode.setColorRed();

        insertFixup(insertedNode);
    }

    @SuppressWarnings("SuspiciousNameCombination")
    private void insertFixup(Node currentNode) {
        while (!currentNode.parent.getColor()) {
            if (currentNode.parent == currentNode.parent.parent.left) {
                Node y = currentNode.parent.parent.right;

                if (!y.getColor()) {
                    currentNode.parent.setColorBlack();
                    y.setColorBlack();
                    currentNode.parent.parent.setColorRed();
                    currentNode = currentNode.parent.parent;
                } else {
                    if (currentNode == currentNode.parent.right) {
                        currentNode = currentNode.parent;
                        leftRotate(currentNode);
                    }

                    currentNode.parent.setColorBlack();
                    currentNode.parent.parent.setColorRed();
                    rightRotate(currentNode.parent.parent);
                }
            } else {
                Node y = currentNode.parent.parent.left;

                if (!y.getColor()) {
                    currentNode.parent.setColorBlack();
                    y.setColorBlack();
                    currentNode.parent.parent.setColorRed();
                    currentNode = currentNode.parent.parent;
                } else {
                    if (currentNode == currentNode.parent.left) {
                        currentNode = currentNode.parent;
                        rightRotate(currentNode);
                    }

                    currentNode.parent.setColorBlack();
                    currentNode.parent.parent.setColorRed();
                    leftRotate(currentNode.parent.parent);
                }
            }
        }

        this.root.setColorBlack();
    }

    public void delete(K value) {
        Node deletedNode = searchNode(value), x, y;
        boolean yOriginalColor;

        if (deletedNode == null) {
            throw new IllegalArgumentException("This node doesn't exist!");
        } else {
            y = deletedNode;
            yOriginalColor = y.getColor();

            if (deletedNode.left == this.NIL) {
                x = deletedNode.right;
                RBTreeTransplant(deletedNode, deletedNode.right);
            } else if (deletedNode.right == this.NIL) {
                x = deletedNode.left;
                RBTreeTransplant(deletedNode, deletedNode.left);
            } else {
                y = treeMinimum(deletedNode.right);
                yOriginalColor = y.getColor();
                x = y.right;

                if (y.parent == deletedNode) {
                    x.parent = y;
                } else {
                    RBTreeTransplant(y, y.right);
                    y.right = deletedNode.right;
                    y.right.parent = y;
                }

                RBTreeTransplant(deletedNode, y);
                y.left = deletedNode.left;
                y.left.parent = y;

                if (deletedNode.getColor()) {
                    y.setColorBlack();
                } else {
                    y.setColorRed();
                }
            }

            if (yOriginalColor) {
                deleteFixup(x);
            }
        }
    }

    private void deleteFixup(Node value) {
        Node temp;

        while (value != this.root && value.getColor()) {
            if (value == value.parent.left) {
                temp = value.parent.right;

                if (!temp.getColor()) {
                    temp.setColorBlack();
                    value.parent.setColorRed();
                    leftRotate(value.parent);
                    temp = value.parent.right;
                }

                if (temp.left.getColor() && temp.right.getColor()) {
                    temp.setColorRed();
                    value = value.parent;
                } else {
                    if (temp.right.getColor()) {
                        temp.left.setColorBlack();
                        temp.setColorRed();
                        rightRotate(temp);
                        temp = value.parent.right;
                    }

                    if (value.parent.getColor()) {
                        temp.setColorBlack();
                    } else {
                        temp.setColorRed();
                    }

                    value.parent.setColorBlack();
                    temp.right.setColorBlack();
                    leftRotate(value.parent);
                    value = this.root;
                }
            } else {
                temp = value.parent.left;

                if (!temp.getColor()) {
                    temp.setColorBlack();
                    value.parent.setColorRed();
                    leftRotate(value.parent);
                    temp = value.parent.left;
                }

                if (temp.right.getColor() && temp.left.getColor()) {
                    temp.setColorRed();
                    value = value.parent;
                } else {
                    if (temp.left.getColor()) {
                        temp.right.setColorBlack();
                        temp.setColorRed();
                        leftRotate(temp);
                        temp = value.parent.left;
                    }

                    if (value.parent.getColor()) {
                        temp.setColorBlack();
                    } else {
                        temp.setColorRed();
                    }

                    value.parent.setColorBlack();
                    temp.left.setColorBlack();
                    rightRotate(value.parent);
                    value = this.root;
                }
            }
        }

        value.setColorBlack();
    }

    private void RBTreeTransplant(Node first, Node second) {
        if (first.parent == this.NIL) {
            this.root = second;
        } else if (first == first.parent.left) {
            first.parent.left = second;
        } else {
            first.parent.right = second;
        }

        second.parent = first.parent;
    }
}