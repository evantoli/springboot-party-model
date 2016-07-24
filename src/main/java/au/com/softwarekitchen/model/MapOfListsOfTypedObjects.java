package au.com.softwarekitchen.model;

import au.com.softwarekitchen.domain.Type;

import java.util.*;
import java.util.stream.Stream;

public class MapOfListsOfTypedObjects<K extends Type, V extends Typed<K>> {

    /**
     * A map of sets of all value objects.
     */
    private SortedMap<K, ArrayList<V>> indexedValues = new TreeMap<>();

    /**
     * Returns an array of all the map keys.
     *
     * @return the set of map keys in use.
     */
    public SortedSet<K> keys() {
        final SortedSet<K> result = new TreeSet<>();
        result.addAll(indexedValues.keySet());
        return result;
    }

    /**
     * Returns the list of values for the specified <tt>key</tt>. 
     * This method will always return a list even if it is empty.
     *
     * @param key the key
     * @return the list of values for the specified <tt>key</tt>.
     */
    private ArrayList<V> _getValueList(final K key) {
        ArrayList<V> valueList = indexedValues.get(key);
        if (valueList == null) {
            valueList = new ArrayList<>();
            indexedValues.put(key, valueList);
        }
        return valueList;
    }

    /**
     * Get all values in this map.
     *
     * @return all values in this map.
     */
    public List<V> getValues() {
        final List<V> result = new ArrayList<>();
        keys().forEach(key -> result.addAll(getValues(key)));
        return result;
    }

    /**
     * Get all values associated to a key.
     *
     * @param key the key.
     * @return all values associated to the specified <tt>key</tt>.
     */
    public List<V> getValues(final K key) {
        return (List<V>) _getValueList(key).clone();
    }

    /**
     * Get the first value associated to a key. If no values are
     * associated to the specified <tt>key</tt> then <tt>null</tt> is returned.
     *
     * @param key the key.
     * @return the first value associated to the specified <tt>key</tt>.
     */
    public V getFirstValue(final K key) {
        final List<V> valueList = _getValueList(key);
        if (valueList.isEmpty()) {
            return null;
        } else {
            return valueList.get(0);
        }
    }

    /**
     * Get the second value associated to a key. If no such value is
     * associated to the specified <tt>key</tt> then <tt>null</tt> is returned.
     *
     * @param key the key.
     * @return the second value associated to the specified <tt>key</tt>.
     */
    public V getSecondValue(final K key) {
        final List<V> valueList = _getValueList(key);
        if (valueList.size() < 2) {
            return null;
        } else {
            return valueList.get(1);
        }
    }



    /**
     * Process all the Values as a Stream.
     * @return
     */
    public Stream<V> stream() {
        return getValues().stream();
    }

    public void add(final V value) {

        if (value == null) return;

        _getValueList(value.getType()).add(value);
    }

    /**
     * Add value objects to the list of values associated to the specified <tt>key</tt>.
     *
     * @param values the value objects to store against the specified <tt>key</tt>.
     */
    public void add(final V ... values) {

        for (V value : values) {
            add(value);
        }
    }

    public final void add(final Collection<V> values) {

        if (values == null) return;
        for (V value : values) {
            add(value);
        }
    }

    public void replace(V value) {

        if (value == null) return;

        _getValueList(value.getType()).clear();
        _getValueList(value.getType()).add(value);
    }

    /**
     * Associate the specified <tt>values</tt> to the specified
     * property <tt>key</tt>. Any previous values that were associated 
     * to this key are removed.
     *
     * @param values the values to set.
     */
    public void replace(V ... values) {

        for (V value : values) {
            _getValueList(value.getType()).clear();
        }

        for (V value : values) {
            _getValueList(value.getType()).add(value);
        }
    }

    /**
     * Associate the specified <tt>values</tt> to the specified
     * property <tt>key</tt>. Any previous values that were associated
     * to this key are removed.
     *
     * @param values the values to set.
     */
    public void replace(Collection<V> values) {

        if (values == null) return;

        for (V value : values) {
            _getValueList(value.getType()).clear();
        }

        for (V value : values) {
            _getValueList(value.getType()).add(value);
        }
    }

    /**
     * Clear all values against the specified <tt>key</tt>.
     *
     * @param key the key
     */
    public void clear(Type key) {
        indexedValues.remove(key);
    }

    /**
     * Remove one value object from the list of value objects associated with the specified <tt>key</tt>.
     *
     * @param value the value object to remove from the property.
     */
    public void remove(V value) {

        if (value == null) {
            return;
        }
        
        final List<V> valueList = _getValueList(value.getType());

        if (valueList.isEmpty()) {
            return;
        }

        valueList.remove(value);
    }

    /**
     * Returns the total number of value objects in this map.
     *
     * @return the total number of value objects in this map.
     */
    public int size() {
        int result = 0;
        for (K key : keys()) {
            result += _getValueList(key).size();
        }
        return result;
    }

    /**
     * Returns the total number of value objects stored against the specified <tt>key</tt>.
     *
     * @return the total number of value objects stored against the specified <tt>key</tt>.
     */
    public int size(K key) {
        return _getValueList(key).size();
    }

    public boolean isEmpty() {
        for (K key : keys()) {
            if (_getValueList(key).size() > 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isEmpty(final K key) {
        return _getValueList(key).isEmpty();
    }

    /**
     * Returns true if there is more than one value object stored against the specified <tt>key</tt>.
     *
     * @param key the key
     * @return true if the key has more than one value associated to it, false if single value or null
     */
    public boolean isMultiValued(final K key) {
        return _getValueList(key).size() > 1;
    }

    public String toString() {
        final StringBuilder buf = new StringBuilder();
        final SortedSet<K> keys = keys();
        for (K key : keys) {
            final List<V> valueList = _getValueList(key);
            for (V value : valueList) {
                buf.append(key).append("=").append(value.toString()).append(" ");
            }
        }
        return buf.toString();
    }
}
