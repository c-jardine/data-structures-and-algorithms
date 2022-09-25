package lists;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KSinglyLinkedListTest {

    KSinglyLinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new KSinglyLinkedList<>();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void isEmpty_whenListIsEmpty_shouldReturnTrue() {
        assertTrue(list.isEmpty());
    }

    @Test
    void isEmpty_whenListIsNotEmpty_shouldReturnFalse() {
        list.addFirst(1);
        assertFalse(list.isEmpty());
    }

    @Test
    void addFirst_whenAddingOneElement_shouldAddElement() {
        list.addFirst(5);
        assertEquals(5, list.first());
        assertEquals(1, list.size());
    }

    @Test
    void addFirst_whenAddingMultipleElements_shouldAddElements() {
        for (int i = 1; i <= 5; i++) {
            list.addFirst(i);
            assertEquals(i, list.first());
        }
        assertEquals(5, list.size());
    }

    @Test
    void addLast_whenAddingOneElement_shouldAddElement() {
        list.addLast(5);
        assertEquals(5, list.last());
        assertEquals(1, list.size());
    }

    @Test
    void addLast_whenAddingMultipleElements_shouldAddElements() {
        int counter = 1;
        for (int i = 1; i <= 5; i++) {
            list.addLast(i);
            assertEquals(counter, list.last());
            counter++;
        }
        assertEquals(5, list.size());
    }

    @Test
    void removeFirst_whenListIsEmpty_shouldReturnNull() {
        assertNull(list.removeFirst());
        assertEquals(0, list.size());
    }

    @Test
    void removeFirst_whenListIsNotEmpty_shouldReturnRemovedElements() {
        for (int i = 1; i<= 5; i++) {
            list.addLast(i);
        }

        assertEquals(5, list.size());

        for(int i = 1; i <= 5; i++) {
            assertEquals(i, list.removeFirst());
        }
    }

    @Test
    void clear_whenListIsEmpty_shouldKeepListEmpty() {
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    void clear_whenListIsNotEmpty_shouldEmptyList() {
        for (int i = 1; i <= 5; i++) {
            list.addFirst(i);
        }
        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
    }
}