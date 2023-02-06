package MyMap;

import java.util.Collection;
import java.util.Set;

public interface MyMap<K,V> {


    /**
     * Returns the number of key-value mappings in this map.
     * @return the number of key-value mappings in this map
     */
    int size();


    /**
     * Tests if this hashmap maps no keys to values.
     *
     * @return  {@code true} if this hashmap no keys to values;
     *          {@code false} otherwise.
     */
    boolean isEmpty();

    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     *         {@code null} if this map contains no mapping for the key
     */
    V get(K key);

    /**
     * Maps the specified {@code key} to the specified
     * {@code value} in this hashmap. Neither the key nor the
     * value can be {@code null}.
     *
     *
     * @param      key     the hashmap key
     * @param      value   the value
     * @return     the previous value of the specified key in this hashmap,
     *             or {@code null} if it did not have one
     */
    V put(K key, V value);

    /**
     * Clears this hashmap so that it contains no keys.
     */
    void clear();


    /**
     * Removes the key (and its corresponding value) from this
     * hashmap. This method does nothing if the key is not in the hashmap.
     *
     * @param   key   the key that needs to be removed
     * @return removed value
     */
    V remove(K key);
}
