package MyList;

import java.util.*;

public class MyArrayList<T> implements MyList<T> {


    /**
     * Default start length
     */
    private static final int START_LENGTH = 10;

    /**
     * constant for operations add and remove
     */
    private static final int OPERATION_CONCAT = 0;
    private static final int OPERATION_SUBSTRACT = 1;

    /**
     * Default coefficient for calculating capacity of list
     */
    private static final double COEFFICIENT = 1.5d;


    private Object[] array;

    /**
     * Quantity of elements in list
     */
    private int size;

    /**
     * Constructs an empty list with the specified initial capacity.
     */
    public MyArrayList() {
        array = new Object[START_LENGTH];
        size = 0;
    }

    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param length the initial capacity of the list
     */
    public MyArrayList(int length) {
        array = new Object[length];
        size = 0;
    }


    /**
     * Appends the specified element to the end of this list.
     *
     * @param value element to be appended to this list
     * @return {@code true} (as specified by {@link Collection#add})
     */
    @Override
    public boolean add(T value) {
        if (ensureCapacity()) {
            grow();
        }

        size++;
        array[size - 1] = value;
        return true;
    }


    /**
     * Increases the capacity of list
     */
    private void grow() {
        Object[] arrayCopy = new Object[(int) (array.length * COEFFICIENT)];
        System.arraycopy(array, 0, arrayCopy, 0, array.length);
        array = arrayCopy;
    }


    /**
     * Check the essential to increase the capacity of list
     */
    private boolean ensureCapacity() {
        return (size == array.length);
    }

    /**
     * Inserts the specified element at the specified position in this list
     * (optional operation).  Shifts the element currently at that position
     * (if any) and any subsequent elements to the right (adds one to their
     * indices).
     *
     * @param value element to be appended to this list
     * @param index index at which the specified element is to be inserted
     * @return {@code true} (as specified by {@link Collection#add})
     */
    @Override
    public void add(T value, int index) {
        ModifyArray(index, OPERATION_CONCAT);
        size++;
    }


    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index >= size()})
     */
    @Override
    public T get(int index) {
        return (T) array[index];
    }


    /**
     * Removes the element at the specified position in this list (optional
     * operation).  Shifts any subsequent elements to the left (subtracts one
     * from their indices).  Returns the element that was removed from the
     * list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     */
    @Override
    public T remove(int index) {

        Object oldValue = array[index];
        ModifyArray(index + 1, OPERATION_SUBSTRACT);

        size--;
        return (T) oldValue;
    }

    /**
     * Modifying array, when needs to add element to list or remove. Shifts any subsequent elements to the left,
     * when it needs to remove element. Shifts any subsequent elements to the right, when it needs to add element
     * int list with specifying index
     *
     * @param index           index of element, for adding or removing
     * @param typeOfOperation type of operation (add or remove)
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    private void ModifyArray(int index, int typeOfOperation) throws IndexOutOfBoundsException {

        if (index > size) {
            throw new IndexOutOfBoundsException();
        }

        int length = array.length - (typeOfOperation == OPERATION_CONCAT ? index - 1 : index + 1);

        System.arraycopy(array,
                index,
                array,
                (typeOfOperation == OPERATION_CONCAT ? index + 1 : index - 1),
                length);

    }

    /**
     * Removes all of the elements from this list (optional operation).
     * The list will be empty after this call returns.
     */
    @Override
    public void clear() {

        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
        size = 0;

    }


    /**
     * Sorts this list according to the order induced by the specified
     * {@link Comparator}.  The sort is <i>stable</i>: this method must not
     * reorder equal elements.
     */
    @Override
    public void sort(Comparator<? super T> c) {

        quickSort(c, 0, size - 1);

    }


    /**
     * Quick sorting of elements in list
     *
     * @param c          specified  object to compare elements in list {@link Comparator}
     * @param indexStart index of element at the beginning of array, which need to sort
     * @param indexEnd   index of element at the end of array, which need to sort
     */
    private void quickSort(Comparator<? super T> c, int indexStart, int indexEnd) {

        int wallIndex = indexStart;
        T pillarValue = (T) array[indexEnd];

        for (int i = indexStart; i < indexEnd; i++) {
            if (c.compare((T) array[i], pillarValue) <= 0) {
                Object temp = array[i];
                array[i] = array[wallIndex];
                array[wallIndex] = temp;
                wallIndex++;
            }
        }


        Object temp = array[wallIndex];
        array[wallIndex] = array[indexEnd];
        array[indexEnd] = temp;

        if (wallIndex - 1 - indexStart > 0 ) {
            quickSort(c, indexStart, wallIndex -1 );
        }
        if (indexEnd - wallIndex - 1 > 0) {
            quickSort(c, wallIndex + 1, indexEnd);
        }

    }


    /**
     * Get quantity of elements in list
     *
     * @return the size of list
     */
    public int getSize() {
        return size;
    }
}
