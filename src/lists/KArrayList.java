package lists;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Implementation of a generic ArrayList.
 *
 * @param <E> The object type contained in the ArrayList.
 */
public class KArrayList<E> implements Serializable, Cloneable, Iterable<E>, Collection<E>, List<E>, RandomAccess {
    private int size = 0;
    private Object[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    public KArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
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

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (Object element : elements) {
            if (element == o) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        // Resize if needed
        ensureCapacity(1);
        elements[size] = e;
        size += 1;
        return true;
    }

    @Override
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

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.size() == 0) {
            return false;
        }
        ensureCapacity(c.size());
        System.arraycopy(c.toArray(), 0, elements, size, c.size());
        size += c.size();
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throwIfOutOfBounds(index);
        if (c.size() == 0) {
            return false;
        }

        ensureCapacity(c.size());

        // If array empty, it can just be set to the array
        if (size == 0) {
            System.arraycopy(c.toArray(), 0, elements, 0, c.size());
            return true;
        }

        // Insert and move elements
        int moved = size - index;
        if (moved > 0) {
            System.arraycopy(elements, index, elements, index + c.size(), moved);
        }
        System.arraycopy(c.toArray(), 0, elements, index, c.size());
        size += c.size();
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        throwIfOutOfBounds(index);

        return (E) elements[index];
    }

    @Override
    public E set(int index, E element) {
        throwIfOutOfBounds(index);
        elements[index] = element;
        return element;
    }

    @Override
    public void add(int index, E element) {
        throwIfOutOfBounds(index);
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
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
