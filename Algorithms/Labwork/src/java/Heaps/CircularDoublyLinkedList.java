package Heaps;

public class CircularDoublyLinkedList<T> {
    private ListNode start, end;
    private int size;

    class ListNode {
        private T data;
        private ListNode next, previous;

        public ListNode() {
            this.data = null;
            this.next = null;
            this.previous = null;
        }

        public ListNode(T value, ListNode next, ListNode previous) {
            this.data = value;
            this.next = next;
            this.previous = previous;
        }

        public T getData() {
            return data;
        }

        public ListNode getLinkNext() {
            return next;
        }

        public void setLinkNext(ListNode next) {
            this.next = next;
        }

        public ListNode getLinkPrev() {
            return previous;
        }

        public void setLinkPrev(ListNode previous) {
            this.previous = previous;
        }
    }

    public CircularDoublyLinkedList() {
        start = end = null;
        size = 0;
    }

    public boolean isEmpty() {
        return start == null;
    }

    public int getSize() {
        return size;
    }

    public void add(T value) {
        ListNode listNode = new ListNode(value, null, null);

        if (start == null) {
            listNode.setLinkNext(listNode);
            listNode.setLinkPrev(listNode);
            start = listNode;
            end = start;
        } else {
            listNode.setLinkPrev(end);
            end.setLinkNext(listNode);
            start.setLinkPrev(listNode);
            listNode.setLinkNext(start);
            end = listNode;
        }
        size++;
    }

    public T get(int index) {
        this.checkElementIndex(index);
        return this.getNode(index).data;
    }

    public int indexOf(Object o) {
        int index = 0;
        ListNode x;

        if (o == null) {
            for(x = this.start; x != null; x = x.next) {
                if (x.data == null) {
                    return index;
                }

                ++index;
            }
        } else {
            for(x = this.start; x != null; x = x.next) {
                if (o.equals(x.data)) {
                    return index;
                }

                ++index;
            }
        }

        return -1;
    }

    public void delete(int index) {
        if (index == 0) {
            if (size == 1) {
                start = null;
                end = null;
                size = 0;
                return;
            }

            start = start.getLinkNext();
            start.setLinkPrev(end);
            end.setLinkNext(start);
            size--;
            return;
        }

        if (index == size - 1) {
            end = end.getLinkPrev();
            end.setLinkNext(start);
            start.setLinkPrev(end);
            size--;
        }

        ListNode temp = start.getLinkNext();
        for (int i = 1; i < size; i++) {
            if (i == index) {
                ListNode previous = temp.getLinkPrev();
                ListNode next = temp.getLinkNext();

                previous.setLinkNext(next);
                next.setLinkPrev(previous);
                size--;
                return;
            }

            temp = temp.getLinkNext();
        }
    }

    ListNode next(ListNode listNode) {
        return listNode.getLinkNext();
    }

    ListNode getNode(int index) {
        ListNode x;
        int i;
        if (index < this.size >> 1) {
            x = this.start;

            for(i = 0; i < index; ++i) {
                x = x.next;
            }
        } else {
            x = this.end;

            for(i = this.size - 1; i > index; --i) {
                x = x.previous;
            }
        }
        return x;
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < this.size;
    }

    private void checkElementIndex(int index) {
        if (!this.isElementIndex(index)) {
            throw new IndexOutOfBoundsException("Incorrect index!");
        }
    }
}