package au.com.softwarekitchen.model;

import au.com.softwarekitchen.domain.EmailAddressType;

import java.util.Set;

public class EmailAddresses {

    private final MapOfSetsOfTypedObjects<EmailAddressType, EmailAddress> emailAddresses = new MapOfSetsOfTypedObjects();

    public boolean isEmpty() {
        return emailAddresses.isEmpty();
    }

    public int size() {
        return emailAddresses.size();
    }

    public int size(final EmailAddressType type) {
        return emailAddresses.size(type);
    }

    public boolean isMultiValued(final EmailAddressType type) {
        return emailAddresses.isMultiValued(type);
    }

    public void add(final EmailAddress emailAddress) {
        this.emailAddresses.add(emailAddress);
    }

    public void add(final EmailAddress ... emailAddresses) {
        this.emailAddresses.add(emailAddresses);
    }

    public Set<EmailAddress> getNames() {
        return emailAddresses.getValues();
    }

    public Set<EmailAddress> getNames(final EmailAddressType type) {
        return emailAddresses.getValues(type);
    }

    public EmailAddress findEmailAddressWithValue(final String value) {
        for (EmailAddressType key : emailAddresses.keys()) {
            for (EmailAddress emailAddress : emailAddresses.getValues(key)) {
                if (value.equalsIgnoreCase(emailAddress.getValue())) {
                    return emailAddress;
                }
            }
        }
        return null;
    }

    public boolean containsEmailAddressWithValue(final String value) {
        return findEmailAddressWithValue(value) == null ? false : true;
    }
}
