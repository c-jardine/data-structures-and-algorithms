package lists;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class KArrayListTest {

    private KArrayList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new KArrayList<>();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void constructor_onInitialization_shouldHaveNotNullList() {
        assertNotNull((list));
    }

    @Test
    void constructor_onInitialization_shouldHaveSizeZero() {
        assertEquals(0, list.size());
    }

    @Test
    void constructor_onInitialization_shouldHaveEmptyList() {
        assertTrue(list.isEmpty());
    }

    @Test
    void add_givenElement_shouldAddElementAndIncrementSize() {
        // Initially empty
        assertEquals(0, list.size());

        list.add(5);
        assertTrue(list.contains(5));
        assertEquals(1, list.size());

        // Return false if element isn't found
        assertFalse(list.contains(4));
    }

    @Test
    void add_givenElementWithFullArray_shouldIncreaseArraySizeAndAddElement() {
        // Initial size is 10
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        assertEquals(10, list.size());

        // Trigger overflow
        list.add(100);
        assertEquals(11, list.size());
        assertEquals(100, list.get(10));
    }

    @Test
    void addAtIndex_givenValidInputs_shouldAddElementAndShiftOthers() {
        addFive();
        list.add(1, 5);

        Object[] listShouldBe = {1, 5, 2, 3, 4, 5};
        for (int i = 0; i < list.size(); i++) {
            assertEquals(listShouldBe[i], list.get(i));
        }
        assertEquals(6, list.size());
    }

    @Test
    void addAtIndex_givenIndexNotInBounds_shouldThrowOutOfBoundsException() {
        addFive();
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(6, 5));
        assertEquals(5, list.size());
    }

    @Test
    void remove_givenElementInList_shouldRemoveElementAndDecrementSize() {
        addFive();

        assertTrue(list.contains(3));
        assertTrue(list.remove(Integer.valueOf(3)));
        assertFalse(list.contains(3));
        assertEquals(4, list.size());
    }

    @Test
    void remove_givenElementNotInList_shouldThrowIllegalArgumentException() {
        addFive();

        assertFalse(list.contains(9));
        assertThrows(IllegalArgumentException.class, () -> list.remove(Integer.valueOf(9)));
    }

    @Test
    void get_givenIndexInBounds_shouldReturnElement() {
        addFive();

        for (int i = 1; i <= 5; i++) {
            assertEquals(i, list.get(i - 1));
        }
    }

    @Test
    void get_givenIndexNotInBounds_shouldThrowIndexOutOfBoundsException() {
        addFive();

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(5));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(100));
    }

    @Test
    void set_givenValidParameters_shouldUpdateElementAtGivenIndex() {
        addFive();

        int index = 2;
        assertEquals(3, list.get(index));
        Integer updatedInt = 9;
        assertEquals(updatedInt, list.set(index, updatedInt));
        assertEquals(updatedInt, list.get(index));
    }

    @Test
    void set_givenIndexOutOfBounds_shouldThrowIndexOutOfBoundsException() {
        addFive();

        Integer updatedInt = 9;
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, updatedInt));
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(5, updatedInt));
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(100, updatedInt));
    }

    @Test
    void addAll_whenEmptyAndNoOverflow_givenCollectionOfElements_shouldAddAllElementsAndChangeSize() {
        ArrayList<Integer> itemsToAdd = new ArrayList<>();
        for (int i = 100; i < 108; i++) {
            itemsToAdd.add(i);
        }
        list.addAll(itemsToAdd);
        assertEquals(8, list.size());
    }

    @Test
    void addAll_whenEmptyAndOverflow_givenCollectionOfElements_shouldAddAllElementsAndChangeSize() {
        ArrayList<Integer> itemsToAdd = new ArrayList<>();
        for (int i = 100; i < 175; i++) {
            itemsToAdd.add(i);
        }
        list.addAll(itemsToAdd);
        assertEquals(75, list.size());
    }

    @Test
    void addAll_whenNotEmptyAndNoOverflow_givenCollectionOfElements_shouldAddAllElementsAndChangeSize() {
        addFive();
        ArrayList<Integer> itemsToAdd = new ArrayList<>();
        for (int i = 100; i < 102; i++) {
            itemsToAdd.add(i);
        }
        list.addAll(itemsToAdd);
        assertEquals(7, list.size());
    }

    @Test
    void addAll_whenNotEmptyAndOverflow_givenCollectionOfElements_shouldAddAllElementsAndChangeSize() {
        addFive();

        ArrayList<Integer> itemsToAdd = new ArrayList<>();
        for (int i = 100; i < 107; i++) {
            itemsToAdd.add(i);
        }
        list.addAll(itemsToAdd);
        assertEquals(12, list.size());

        Integer[] listShouldBe = {1, 2, 3, 4, 5, 100, 101, 102, 103, 104, 105, 106};
        for (int i = 0; i < 12; i++) {
            assertEquals(listShouldBe[i], list.get(i));
        }
    }

    @Test
    void addAll_givenEmptyCollection_shouldReturnFalse() {
        addFive();
        assertFalse(list.addAll(new ArrayList<>()));
    }

    @Test
    void addAllAtIndex_whenAddedToBeginning_givenValidInputs_shouldAddAllElementsStartingAtGivenIndex() {
        addFive();
        ArrayList<Integer> itemsToAdd = new ArrayList<>();
        for (int i = 100; i < 107; i++) {
            itemsToAdd.add(i);
        }

        list.addAll(0, itemsToAdd);
        assertEquals(12, list.size());

        Integer[] listShouldBe = {100, 101, 102, 103, 104, 105, 106, 1, 2, 3, 4, 5};
        for (int i = 0; i < 12; i++) {
            assertEquals(listShouldBe[i], list.get(i));
        }
    }

    @Test
    void addAllAtIndex_whenAddedToMiddle_givenValidInputs_shouldAddAllElementsStartingAtGivenIndex() {
        addFive();
        ArrayList<Integer> itemsToAdd = new ArrayList<>();
        for (int i = 100; i < 103; i++) {
            itemsToAdd.add(i);
        }

        list.addAll(3, itemsToAdd);
        assertEquals(8, list.size());

        Integer[] listShouldBe = {1, 2, 3, 100, 101, 102, 4, 5};
        for (int i = 0; i < 8; i++) {
            assertEquals(listShouldBe[i], list.get(i));
        }
    }

    @Test
    void addAllAtIndex_indexNotInBounds_shouldThrowOutOfBoundsException() {
        addFive();
        ArrayList<Integer> itemsToAdd = new ArrayList<>();
        for (int i = 100; i < 105; i++) {
            itemsToAdd.add(i);
        }

        assertThrows(IndexOutOfBoundsException.class, () -> list.addAll(-1, itemsToAdd));
        assertThrows(IndexOutOfBoundsException.class, () -> list.addAll(5, itemsToAdd));
    }

    @Test
    void addAllAtIndex_givenEmptyCollection_shouldReturnFalse() {
        addFive();
        assertFalse(list.addAll(2, new ArrayList<>()));
        assertEquals(5, list.size());
    }

    @Test
    void clear_shouldRemoveAllElements() {
        addFive();
        list.clear();
        assertEquals(0, list.size());
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }

    @Test
    void removeAtIndex_givenIndexInBounds_shouldRemoveElementAtIndexAndReturnElement() {
        addFive();
        assertEquals(2, list.remove(1));
        assertEquals(4, list.size());
    }

    @Test
    void indexOf_givenExistingObject_shouldReturnIndexOfObject() {
        addFive();
        for (int i = 0; i < list.size(); i++) {
            assertEquals(i, list.indexOf(i + 1));
        }
    }

    @Test
    void indexOf_givenNotExistingObject_shouldReturnNotInArray() {
        addFive();
        assertEquals(-1, list.indexOf(9));
    }

    @Test
    void lastIndexOf_givenSingleExistingObject_shouldReturnIndex() {
        addFive();
        for (int i = 0; i < list.size(); i++) {
            assertEquals(i, list.indexOf(i + 1));
        }
    }

    @Test
    void lastIndexOf_givenMultipleExistingObjects_shouldReturnLastIndex() {
        addFive();
        addFive();
        assertEquals(10, list.size());
        assertEquals(5, list.lastIndexOf(1));
        for (int i = 0; i < 5; i++) {
            assertEquals(i + 5, list.lastIndexOf(i + 1));
        }
    }

    @Test
    void subList_givenValidIndices_shouldReturnSubList() {
        addFive();
        List<Integer> subList = list.subList(1, 4);
        List<Integer> listShouldBe = Arrays.asList(2, 3, 4);
        assertEquals(listShouldBe, subList);
    }

    void addFive() {
        for (int i = 1; i <= 5; i++) {
            list.add(i);
        }
    }
}