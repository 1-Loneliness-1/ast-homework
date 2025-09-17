import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CustomHashMap<K, V> {

    private Node<K, V>[] hashTable;
    private int currentSizeOfHashTable = DEFAULT_SIZE_OF_HASHTABLE;

    private static final float LOAD_FACTOR = 0.75f;
    private static final int DEFAULT_SIZE_OF_HASHTABLE = 16;
    private static final int INDEX_OF_BASKET_FOR_NULL_KEY = 0;
    private static final int VALUE_OF_RIGHT_SHIFT = 16;

    /*Так как информация о типах-параметрах стирается на этапе компиляции, то нельзя создать массив объектов
    параметризованного класса. Чтобы это решить можно было использовать коллекции (ArrayList или подобные), но
    ArrayList расширяется автоматически, поэтому, чтобы реализовать автоматическое расширение хэш-таблицы при
     достижении определенного количества "корзин" самому, я выбрал прямой каст к массиву Node.*/
    @SuppressWarnings("unchecked")
    public CustomHashMap() {
        hashTable = (Node<K, V>[]) new Node[DEFAULT_SIZE_OF_HASHTABLE];
    }

    public Object put(K key, V value) {
        int finalHash = getFinalHashCode(key);
        int indexOfBasket = getIndexOfBasket(finalHash);

        if (hashTable[indexOfBasket] == null) {
            hashTable[indexOfBasket] = new Node<>(finalHash, key, value);
        } else {
            Node<K, V> currentNode = hashTable[indexOfBasket];
            Node<K, V> previousNode = null;

            while (currentNode != null) {
                if (finalHash == currentNode.hashCode && key.equals(currentNode.key)) {
                    currentNode.value = value;
                    return currentNode.value;
                }

                previousNode = currentNode;
                currentNode = currentNode.nextNode;
            }

            if (previousNode != null) {
                previousNode.nextNode = new Node<>(finalHash, key, value);
            }
        }

        resizeHashTable();

        return null;
    }

    public Object remove(K key) {
        int indexOfBasket = getIndexOfBasket(getFinalHashCode(key));
        Node<K, V> currentNode = hashTable[indexOfBasket];
        Node<K, V> previousNode = null;

        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                Object objForReturn = currentNode.value;
                if (previousNode == null) {
                    hashTable[indexOfBasket] = currentNode.nextNode;
                } else {
                    previousNode.nextNode = currentNode.nextNode;
                }
                return objForReturn;
            } else {
                previousNode = currentNode;
                currentNode = currentNode.nextNode;
            }
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    private void resizeHashTable() {
        if (hashTable.length * LOAD_FACTOR <= Arrays.stream(hashTable).filter(Objects::nonNull).count()) {
            Node<K, V>[] oldHashTable = hashTable;
            currentSizeOfHashTable *= 2;
            hashTable = (Node<K, V>[])new Node[currentSizeOfHashTable];

            for (Node<K, V> node: oldHashTable) {
                if (node != null) {
                    Node<K, V> currentNode = node;

                    while (currentNode != null) {
                        put(currentNode.key, currentNode.value);
                        currentNode = currentNode.nextNode;
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        List<K> listOfKeysForPrint = new ArrayList<>();

        for (Node<K, V> node: hashTable) {
            if (node != null) {
                Node<K, V> currentNode = node;

                while (currentNode != null) {
                    listOfKeysForPrint.add(currentNode.key);
                    currentNode = currentNode.nextNode;
                }
            }
        }

        return listOfKeysForPrint.toString();
    }

    private int getIndexOfBasket(int hashCode) {
        return (hashTable.length - 1) & hashCode;
    }

    private int getFinalHashCode(K key) {
        int h;
        return (key == null) ? INDEX_OF_BASKET_FOR_NULL_KEY : (h = key.hashCode()) ^ (h >>> VALUE_OF_RIGHT_SHIFT);
    }

    private static class Node<K, V> {
        private final int hashCode;
        private final K key;
        private V value;
        private Node<K, V> nextNode;

        public Node(int hashCode, K key, V value) {
            this.hashCode = hashCode;
            this.key = key;
            this.value = value;
        }
    }

}
