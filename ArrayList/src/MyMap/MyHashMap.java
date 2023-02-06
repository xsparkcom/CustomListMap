package MyMap;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MyHashMap<K,V> implements MyMap<K,V> {

    private MyEntry<?,?>[] entries;

    /**
     * Current length of entries array
     */
    private int initialCapacity;

    /**
     * Current quantity of elements in entries array
     */
    private int size;

    /**
     * Border value, when entries array need to grow
     */
    private double threshold;

    /**
     * constants for calculate
     */
    private static final float LOADFACTOR = 0.75F;
    private static final int INITIAL_CAPACITY = 16;

    /**
     * Current  loadfactor. Load coefficient of etries array
     */
    private float loadfactor;

    private int count;

    public MyHashMap() {
        this(INITIAL_CAPACITY, LOADFACTOR);
    }

    /**
     * Constructor of map
     *
     * @param initialCapacity index of the element to return
     * @param loadFactor index of the element to return
     * @throws IllegalArgumentException if initialCapacity less than zero and loadfactor less or equals zero
     */
    public MyHashMap(int initialCapacity, float loadFactor) {

        this.initialCapacity = initialCapacity;

        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal Load: "+loadFactor);

        if (initialCapacity==0) {
            this.initialCapacity = INITIAL_CAPACITY;
        }

        this.loadfactor = loadFactor;
        entries = new MyEntry[initialCapacity];
        threshold = initialCapacity * loadfactor;
    }

    public MyHashMap(int initialCapacity) {
        this(initialCapacity, LOADFACTOR);
    }


    /**
     * Returns the number of key-value mappings in this map.
     * @return the number of key-value mappings in this map
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Tests if this hashmaps no keys to values.
     *
     * @return  {@code true} if this hash maps no keys to values;
     *          {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     *         {@code null} if this map contains no mapping for the key
     */
    @Override
    public V get(K key) {
        int index = indexFor(key.hashCode(), entries.length);
        MyEntry<?,?> entry = entries[index];

        while(entry != null) {
            if(entry.getKey().hashCode() == key.hashCode()
                    && entry.getKey().equals(key)) {
                return (V)entry.getValue();
            }
            entry = entry.getNext();
        }

        return null;
    }


    /**
     * Returns the value of index, which was calculated with hash
     *
     * @param hash hashcode of the key
     * @param length length of entries array
     * @return the value to which the specified key is mapped
     */
    private static int indexFor(int hash, int length){

        return (hash & 0x7FFFFFFF) % length;

    }

    /**
     * Maps the specified {@code key} to the specified
     * {@code value} in this map. Neither the key nor the
     * value can be {@code null}.
     *
     *
     * @param      key     the hashmap key
     * @param      value   the value
     * @return     the previous value of the specified key in this map,
     *             or {@code null} if it did not have one
     */
    @Override
    public V put(K key, V value) {

        int index = indexFor(key.hashCode(), entries.length);
        MyEntry<K,V> entry = (MyEntry<K,V>)entries[index];

        if (entry == null) {
            entries[index] = new MyEntry<Object, Object>(key, value);
            size++;
            count++;
            if (count >= threshold) {
                reHash();
            }
            return null;
        } else {
            while (entry.getNext() != null) {
                if (entry.getKey().hashCode() == key.hashCode()
                        && entry.getKey().equals(key)) {
                   return (V) entry.setValue(value);
                }
                entry = entry.getNext();
            }
            if (entry.getKey().hashCode() == key.hashCode()
                    && entry.getKey().equals(key)) {
                return (V) entry.setValue(value);
            } else {
                entry.next = new MyEntry<K, V>(key, value);
                size++;
                return null;
            }
        }

    }


    /**
     * Increases the capacity of and internally reorganizes this
     * hashmap, in order to accommodate and access its entries more
     * efficiently.  This method is called automatically when the
     * number of keys in the hashmap exceeds this hashmap's capacity
     * and load factor.
     */
    private void reHash(){

        int oldCapacity = initialCapacity;
        initialCapacity = (oldCapacity << 1) + 1;
        threshold = initialCapacity * loadfactor;

        MyEntry[] tempArray = entries;
        entries = new MyEntry[initialCapacity];

        for(int i = tempArray.length - 1; i >= 0; i--) {

            while(tempArray[i] != null) {
                MyEntry tempEntry = tempArray[i];

                tempArray[i] = tempEntry.getNext();
                int index = indexFor(tempEntry.getKey().hashCode(), entries.length);
                tempEntry.next = entries[index];
                entries[index] = tempEntry;
            }
        }
    }


    /**
     * Clears this map so that it contains no keys.
     */
    @Override
    public void clear() {
        for (int i = 0; i < entries.length; i++){
            entries[i] = null;
        }
        size = 0;
    }

    /**
     * Removes the key (and its corresponding value) from this
     * hashmap. This method does nothing if the key is not in the hashmap.
     *
     * @param   key   the key that needs to be removed
     * @return removed value
     */
    @Override
    public V remove(K key) {

        int index = indexFor(key.hashCode(), entries.length);
        MyEntry<K,V> entry = (MyEntry<K,V>)entries[index];
        V old = null;

        if (entry == null) {
        } else {
            if (entry.getKey().hashCode() == key.hashCode()
                    && entry.getKey().equals(key)) {
                if (entry.getNext() == null) {
                    old = entry.getValue();
                    entries[index] = null;
                    size--;
                    count--;
                } else {
                    old = entry.getValue();
                    entries[index] = entry.getNext();
                    entry.next = null;
                    size--;
                }

            } else {
                while (entry.getNext() != null) {
                    if (entry.getNext().getKey().hashCode() == key.hashCode()
                            && entry.getNext().getKey().equals(key)) {

                        old = entry.getNext().getValue();
                        MyEntry<K,V> tempEntry = entry.getNext();
                        entry.next = entry.getNext().getNext();
                        tempEntry.next = null;
                        size--;
                    }
                    entry = entry.getNext();
                }

            }
        }
        return old;

    }


    class MyEntry<K,V> implements Map.Entry<K,V> {

        private K key;
        private V value;

        private MyEntry<K,V> next;

        /**
         * Constructs an entry with key and value
         * @param key key of entry
         * @value value of entry
         */
        public MyEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Returns the key corresponding to this entry.
         *
         * @return the key corresponding to this entry
         * */
        @Override
        public K getKey() {
            return key;
        }

        /**
         * Returns the value corresponding to this entry.  If the mapping
         * has been removed from the backing map (by the iterator's
         * {@code remove} operation), the results of this call are undefined.
         *
         * @return the value corresponding to this entry
         **/
        @Override
        public V getValue() {
            return value;
        }


        /**
         * Return link on next element of linked list
         * @return next entry from linked list
         */
        public MyEntry<K, V> getNext() {
            return next;
        }


        /**
         * Replaces the value corresponding to this entry with the specified
         * value (optional operation).  (Writes through to the map.)  The
         * behavior of this call is undefined if the mapping has already been
         * removed from the map (by the iterator's {@code remove} operation).
         *
         * @param value new value to be stored in this entry
         */
        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

    }

}
