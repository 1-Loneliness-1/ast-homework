public class CustomHashSet<E> {

    private final CustomHashMap<E, Object> map;
    private static final Object HASHMAP_VALUE_PLACEHOLDER = new Object();

    public CustomHashSet() {
        map = new CustomHashMap<>();
    }

    public boolean add(E elem) {
        return map.put(elem, HASHMAP_VALUE_PLACEHOLDER) == null;
    }

    public boolean remove(E elem) {
        return map.remove(elem) == HASHMAP_VALUE_PLACEHOLDER;
    }

    @Override
    public String toString() {
        return map.toString();
    }
}
