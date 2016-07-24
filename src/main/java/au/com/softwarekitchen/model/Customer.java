package au.com.softwarekitchen.model;

import au.com.softwarekitchen.domain.AddressType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Customer extends AbstractModel<Long> {

    private static final Logger log = LoggerFactory.getLogger(Customer.class);

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private Map<AddressType, Address> addresses = new HashMap<>();

    protected Customer() {}

    public Customer(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public Collection<Address> getAddresses() {
        return addresses.values();
    }

    public void setAddresses(final Collection<Address> addresses) {
        addresses.clear();
        Address replaced;
        for (Address address : addresses) {
            replaced = this.addresses.put(address.getType(), address);
            if (replaced != null) {
                log.warn("Multiple addresses of same type added to collection of addresses. Keeping address: {} Losing address: {}",
                        address, replaced);
            }
        }
    }

    public Address getAddress(final AddressType type) {
        return addresses.get(type);
    }

    public Address putAddress(final Address address) {
        return addresses.put(address.getType(), address);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Customer{");
        sb.append("id:").append(id);
        sb.append(", firstName:'").append(firstName).append('\'');
        sb.append(", lastName:'").append(lastName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}