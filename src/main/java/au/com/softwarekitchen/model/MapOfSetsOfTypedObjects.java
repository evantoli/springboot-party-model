package au.com.softwarekitchen.model;

import au.com.softwarekitchen.domain.Type;

import java.util.*;
import java.util.stream.Stream;

public class MapOfSetsOfTypedObjects<K extends Type, V extends Typed<K>> {

    /**
     * A map of sets of all value objects.
     */
    private SortedMap<K, SortedSet<V>> indexedValues = new TreeMap<>();

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
     * Returns the set of values for the specified <tt>key</tt>. 
     * This method will always return a set even if it is empty.
     *
     * @param key the key
     * @return the set of values for the specified <tt>key</tt>.
     */
    private SortedSet<V> _getValueSet(final K key) {
        SortedSet<V> valueSet = indexedValues.get(key);
        if (valueSet == null) {
            valueSet = new TreeSet<>();
            indexedValues.put(key, valueSet);
        }
        return valueSet;
    }

    /**
     * Get the first value associated to a key. If no values are
     * associated to the specified <tt>key</tt> then <tt>null</tt> is returned.
     *
     * @param key the key.
     * @return the first value associated to the specified <tt>key</tt>.
     */
    public V getFirstValue(final K key) {
        final SortedSet<V> valueSet = _getValueSet(key);
        if (valueSet.isEmpty()) {
            return null;
        } else {
            return valueSet.first();
        }
    }

    /**
     * Get all values associated to a key.
     *
     * @param key the key.
     * @return all values associated to the specified <tt>key</tt>.
     */
    public SortedSet<V> getValues(final K key) {
        final SortedSet<V> result = new TreeSet<>();
        result.addAll(_getValueSet(key));
        return result;
    }

    /**
     * Get all values in this map.
     *
     * @return all values in this map.
     */
    public SortedSet<V> getValues() {
        final SortedSet<V> result = new TreeSet<>();

        keys().forEach(key -> result.addAll(getValues(key)));
        return result;
    }

    /**
     * Process all the Values in the Map of Sets as a Stream.
     * @return
     */
    public Stream<V> stream() {
        return getValues().stream();
    }

    public void add(final V value) {

        if (value == null) return;

        _getValueSet(value.getType()).add(value);
    }

    /**
     * Add a value objects to the set of values associated to the specified <tt>key</tt>.
     *
     * @param values the value objects to store against the specified <tt>key</tt>.
     */
    public void add(final V ... values) {

        for (V value : values) {
            add(value);
        }
    }

    /**
     * Add a value objects to the set of values associated to the specified <tt>key</tt>.
     *
     * @param values the value objects to store against the specified <tt>key</tt>.
     */
    public final void add(final Collection<V> values) {

        if (values == null) return;
        for (V value : values) {
            add(value);
        }
    }

    public void replace(V value) {

        if (value == null) return;

        _getValueSet(value.getType()).clear();
        _getValueSet(value.getType()).add(value);
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
            _getValueSet(value.getType()).clear();
        }

        for (V value : values) {
            _getValueSet(value.getType()).add(value);
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
            _getValueSet(value.getType()).clear();
        }

        for (V value : values) {
            _getValueSet(value.getType()).add(value);
        }
    }

    /**
     * Clear all values against the specified <tt>key</tt>.
     *
     * @param key the key
     */
    public void clear(K key) {
        indexedValues.remove(key);
    }

    /**
     * Remove one value object from the set of value objects associated with the specified <tt>key</tt>.
     *
     * @param value the value object to remove from the property.
     */
    public void remove(V value) {

        if (value == null) {
            return;
        }
        
        final SortedSet<V> valueSet = _getValueSet(value.getType());

        if (valueSet.isEmpty()) {
            return;
        }

        valueSet.remove(value);
    }

    /**
     * Returns the total number of value objects in this map.
     *
     * @return the total number of value objects in this map.
     */
    public int size() {
        int result = 0;
        for (K key : keys()) {
            result += _getValueSet(key).size();
        }
        return result;
    }

    /**
     * Returns the total number of value objects stored against the specified <tt>key</tt>.
     *
     * @return the total number of value objects stored against the specified <tt>key</tt>.
     */
    public int size(K key) {
        return _getValueSet(key).size();
    }

    /**
     * Is the Set Empty
     *
     * @return true is the set is empty
     */
    public boolean isEmpty() {
        for (K key : keys()) {
            if (_getValueSet(key).size() > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Is the set empty for the type
     *
     * @return true if empty
     */
    public boolean isEmpty(final K key) {
        return _getValueSet(key).isEmpty();
    }

    /**
     * Returns true if there is more than one value object stored against the specified <tt>key</tt>.
     *
     * @param key the key
     * @return true if the key has more than one value associated to it, false if single value or null
     */
    public boolean isMultiValued(final K key) {
        return _getValueSet(key).size() > 1;
    }

    public String toString() {
        final StringBuilder buf = new StringBuilder();
        final SortedSet<K> keys = keys();
        for (K key : keys) {
            final SortedSet<V> valueSet = _getValueSet(key);
            for (V value : valueSet) {
                buf.append(key).append("=").append(value.toString()).append(" ");
            }
        }
        return buf.toString();
    }
}
