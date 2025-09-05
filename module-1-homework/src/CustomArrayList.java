import java.util.Arrays;
import java.util.Collection;

public class CustomArrayList<T> {

    private Object[] internalArray;
    private int indexOfElementForInsert = 0;

    private static final int BASIC_ARRAY_SIZE = 10;
    private static final int REQUIRED_FREE_SPACE_FOR_ONE_ELEMENT = 1;

    public CustomArrayList() {
        internalArray = new Object[BASIC_ARRAY_SIZE];
    }

    public CustomArrayList(int capacity) {
        if (capacity == 0) {
            internalArray = new Object[]{};
        } else {
            internalArray = new Object[capacity];
        }
    }

    public CustomArrayList(Collection<? extends T> enteredCollection) {
        Object[] collection = enteredCollection.toArray();

        if (enteredCollection.isEmpty()) {
            internalArray = new Object[]{};
        } else {
            internalArray = Arrays.copyOf(collection, collection.length, Object[].class);
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(internalArray);
    }

    public boolean add(T elem) {
        checkFillingOfInternalArray(REQUIRED_FREE_SPACE_FOR_ONE_ELEMENT);
        internalArray[indexOfElementForInsert++] = elem;

        return true;
    }

    public boolean add(int indexForInsert, T elem) {
        checkingIndexInRange(indexForInsert);
        checkFillingOfInternalArray(REQUIRED_FREE_SPACE_FOR_ONE_ELEMENT);

        int countOfElementsAfterInserted = indexOfElementForInsert - indexForInsert;
        System.arraycopy(internalArray, indexForInsert, internalArray, indexForInsert + 1, countOfElementsAfterInserted);
        internalArray[indexForInsert] = elem;
        indexOfElementForInsert++;

        return true;
    }

    public boolean addAll(Collection<? extends T> addedCollection) {
        Object[] collectionForAdd = addedCollection.toArray();

        if (!addedCollection.isEmpty()) {
            checkFillingOfInternalArray(addedCollection.size());
            System.arraycopy(collectionForAdd, 0, internalArray, indexOfElementForInsert, collectionForAdd.length);
            indexOfElementForInsert += addedCollection.size();
        }

        return true;
    }

    public boolean addAll(int indexForInsert, Collection<? extends T> c) {
        Object[] collectionForInsert = c.toArray();

        if (!c.isEmpty()) {
            checkingIndexInRange(indexForInsert);
            checkFillingOfInternalArray(collectionForInsert.length);

            int countOfElementsAfterInserted = indexOfElementForInsert - indexForInsert;
            System.arraycopy(internalArray, indexForInsert, internalArray, indexOfElementForInsert, countOfElementsAfterInserted);
            System.arraycopy(collectionForInsert, 0, internalArray, indexForInsert, collectionForInsert.length);
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkingIndexInRange(index);
        return (T) internalArray[index];
    }

    @SuppressWarnings("unchecked")
    public T remove(int indexOfDeletedElement) {
        checkingIndexInRange(indexOfDeletedElement);
        T oldValue = (T) internalArray[indexOfDeletedElement];
        int countOfElementsAfterRemoved = indexOfElementForInsert - indexOfDeletedElement - 1;
        if (countOfElementsAfterRemoved > 0) {
            System.arraycopy(internalArray, indexOfDeletedElement + 1, internalArray, indexOfDeletedElement, countOfElementsAfterRemoved);
        }
        internalArray[--indexOfElementForInsert] = null;

        return oldValue;
    }

    private void checkingIndexInRange(int index) {
        if (index < 0 || index >= indexOfElementForInsert) {
            throw new IndexOutOfBoundsException("Incorrect index was entered!");
        }
    }

    private void checkFillingOfInternalArray(int requiredSizeForInsertedElements) {
        if (internalArray.length - indexOfElementForInsert < requiredSizeForInsertedElements) {
            increaseSizeOfInternalArray(requiredSizeForInsertedElements);
        }
    }

    private void increaseSizeOfInternalArray(int requiredSizeForInsertedElements) {
        int finalSize = internalArray.length + (internalArray.length >> 1);

        if (finalSize - indexOfElementForInsert < requiredSizeForInsertedElements) {
            finalSize = indexOfElementForInsert + requiredSizeForInsertedElements - 1;
        }

        internalArray = Arrays.copyOf(internalArray, finalSize, Object[].class);
    }

}
