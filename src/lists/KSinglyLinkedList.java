package lists;

public class KSinglyLinkedList<E> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    public KSinglyLinkedList() {
    }

    /**
     * Get the size of (number of elements in) the list.
     *
     * @return The number of elements in the list.
     */
    public int size() {
        return size;
    }

    /**
     * Check if the list is empty.
     *
     * @return True if empty; false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Get the first element in the list.
     *
     * @return The first element in the list.
     */
    public E first() {
        if (isEmpty()) return null;
        return head.element;
    }

    /**
     * Get the last element in the list.
     *
     * @return The last element in the list.
     */
    public E last() {
        if (isEmpty()) return null;
        return tail.element;
    }

    /**
     * Add an element to the start of the list.
     *
     * @param element The element to be added.
     */
    public void addFirst(E element) {
        head = new Node<>(element, head);
        // A list with one element has the same node for its tail and head.
        if (isEmpty()) {
            tail = head;
        }

        size++;

    }

    /**
     * Add an element to the end of the list.
     *
     * @param element The element to be added.
     */
    public void addLast(E element) {
        Node<E> newElement = new Node<>(element, null);
        if (isEmpty()) {
            head = newElement;
        } else {
            tail.next = newElement;
        }
        tail = newElement;

        size++;
    }

    /**
     * Remove the first element in the list.
     *
     * @return The element that is removed; null if empty.
     */
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }

        // Get the head element and reduce the size of the list.
        E removedElement = head.element;
        size--;
        head = head.next;

        if (isEmpty()) {
            tail = null;
        }

        return removedElement;
    }

    /**
     * Empty the list.
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }
}
