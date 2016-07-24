package au.com.softwarekitchen.model;

import au.com.softwarekitchen.domain.NameType;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Names {

    /**
     * A map of sets of all value objects.
     */
    private final MapOfListsOfTypedObjects<NameType, Name> names = new MapOfListsOfTypedObjects<>();

    public boolean isEmpty() {
        return names.isEmpty();
    }

    public int size() {
        return names.size();
    }

    public int size(final NameType type) {
        return names.size(type);
    }

    public boolean isMultiValued(final NameType type) {
        return names.isMultiValued(type);
    }

    public void add(final Name name) {
        this.names.add(name);
    }

    public void add(final Name ... names) {
        this.names.add(names);
    }

    public List<Name> getNames() {
        return names.getValues();
    }

    public List<Name> getNames(final NameType type) {
        return names.getValues(type);
    }

    public Name getFirstByType(final NameType type) {
        return names.getFirstValue(type);
    }

    public Name getSecondByType(final NameType type) {
        return names.getSecondValue(type);
    }

    public List<Name> findNamesContaining(final String fragment) {
        final ArrayList<Name> result = new ArrayList<>();
        for (NameType key : names.keys()) {
            for (Name name : names.getValues(key)) {
                if (StringUtils.containsIgnoreCase(name.getValue(), fragment)) {
                    result.add(name);
                }
            }
        }
        return result;
    }

    public boolean containsNameWithFragment(final String fragment) {
        for (NameType key : names.keys()) {
            for (Name name : names.getValues(key)) {
                if (StringUtils.containsIgnoreCase(name.getValue(), fragment)) {
                    return true;
                }
            }
        }
        return false;
    }


}
