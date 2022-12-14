package lists;

import java.util.*;

/**
 * Implementation of a generic ArrayList.
 *
 * @param <E> The object type contained in the ArrayList.
 */
public class KArrayList<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private Object[] elements;

    public KArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public int size() {
        return size;
    }

    public void ensureCapacity(int numToIncreaseBy) {
        int remainingSlots = elements.length - size;
        int newSize = -1;
        if (remainingSlots == 0) {
            newSize = elements.length + numToIncreaseBy;
        } else if (remainingSlots < numToIncreaseBy) {
            newSize = elements.length + (numToIncreaseBy - remainingSlots);
        }
        if (newSize != -1) {
            elements = Arrays.copyOf(elements, newSize);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        for (Object element : elements) {
            if (element == o) {
                return true;
            }
        }
        return false;
    }

    public Iterator<E> iterator() {
        return null;
    }

    public boolean add(E e) {
        // Resize if needed
        ensureCapacity(1);
        elements[size] = e;
        size += 1;
        return true;
    }

    public boolean remove(Object o) {
        int index = -1;
        for (int i = 0; i <= size; i++) {
            if (o.equals(elements[i])) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new IllegalArgumentException("Element doesn't exist in the list.");
        } else {
            System.arraycopy(elements, index + 1, elements, index, elements.length - index - 1);
            size -= 1;
            return true;
        }
    }

    public boolean containsAll(Collection<?> c) {
        return false;
    }

    public boolean addAll(Collection<? extends E> c) {
        if (c.size() == 0) {
            return false;
        }
        ensureCapacity(c.size());
        System.arraycopy(c.toArray(), 0, elements, size, c.size());
        size += c.size();
        return true;
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        throwIfOutOfBounds(index);
        if (c.size() == 0) {
            return false;
        }
        ensureCapacity(c.size());

        // Insert and move elements
        int moved = size - index;
        if (moved > 0) {
            System.arraycopy(elements, index, elements, index + c.size(), moved);
        }
        System.arraycopy(c.toArray(), 0, elements, index, c.size());
        size += c.size();
        return true;
    }

    public void clear() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        throwIfOutOfBounds(index);
        return (E) elements[index];
    }

    public E set(int index, E element) {
        throwIfOutOfBounds(index);
        elements[index] = element;
        return element;
    }

    public void add(int index, E element) {
        throwIfOutOfBounds(index);
        ensureCapacity(1);
        System.arraycopy(elements, index, elements, index + 1,
                size - index);
        elements[index] = element;
        size++;
    }

    public E remove(int index) {
        throwIfOutOfBounds(index);
        int atIndex = -1;
        E element = (E) elements[index];
        System.arraycopy(elements, 0, elements, index, size - 1);
        size--;
        return element;
    }

    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                index = i;
            }
        }
        return index;
    }

    public ListIterator<E> listIterator() {
        return null;
    }

    public ListIterator<E> listIterator(int index) {
        return null;
    }

    public List<E> subList(int fromIndex, int toIndex) {
        throwIfOutOfBounds(fromIndex);
        throwIfOutOfBounds(toIndex);
        if (toIndex < fromIndex) {
            throw new IllegalArgumentException("toIndex must be greater than fromIndex");
        }
        Object[] subList = new Object[toIndex - fromIndex];
        int counter = 0;
        for (int i = fromIndex; i < toIndex; i++) {
            subList[counter] = elements[i];
            counter++;
        }
        return (List<E>) Arrays.asList(subList);
    }

    private void throwIfOutOfBounds(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + " is out of bounds.");
        }
    }

    @Override
    public KArrayList<E> clone() {
        try {
            KArrayList clone = (KArrayList) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
