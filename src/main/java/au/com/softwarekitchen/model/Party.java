package au.com.softwarekitchen.model;

import au.com.softwarekitchen.domain.AddressType;
import au.com.softwarekitchen.domain.NameType;
import au.com.softwarekitchen.domain.PartyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlTransient;

public abstract class Party extends AbstractModel<Long> {

    private static final Logger log = LoggerFactory.getLogger(Party.class);

    protected PartyType type;

    protected final Names names = new Names();
    protected final Addresses addresses = new Addresses();
    protected final EmailAddresses emailAddresses = new EmailAddresses();
    protected final PhoneNumbers phoneNumbers = new PhoneNumbers();
    protected final LocalDates localDates = new LocalDates();

    // =====

    protected Party(final PartyType type) {
        this.type = type;
    }
    
    public PartyType getType() {
        return type;
    }

    public void setType(PartyType type) {
        this.type = type;
    }

    public abstract String getDisplayName();

    public Name getPreferredName() {
        return names.getFirstByType(NameType.PREFERRED_NAME);
    }

    public String getPreferredNameAsString() {
        final Name name = getPreferredName();
        return name == null ? null : name.getValue();
    }

    public Names getNames() {
        return names;
    }
    
    public void addName(final Name ... names) {
        this.names.add(names);
    }

    public Addresses getAddresses() {
        return addresses;
    }

    public void addAddress(final Address ... addresses) {
        this.addresses.add(addresses);
    }

    public EmailAddresses getEmailAddresses() {
        return emailAddresses;
    }

    public void addEmailAddress(final EmailAddress ... emailAddresses) {
        this.emailAddresses.add(emailAddresses);
    }
    
    public PhoneNumbers getPhoneNumbers() {
        return phoneNumbers;
    }

    public void addPhoneNumber(final PhoneNumber ... phoneNumbers) {
        this.phoneNumbers.add(phoneNumbers);
    }

    public LocalDates getLocalDates() {
        return localDates;
    }

    public void addLocalDate(final LocalDateValue... localDateVOs) {
        this.localDates.add(localDateVOs);
    }

    /**
     * Gets the first residential address.
     *
     * @return the first residential address.
     */
    @XmlTransient
    public Address getResidentialAddress() {
        return addresses.getFirstValue(AddressType.RESIDENTIAL);
    }

    /**
     * Gets the first postal address.
     *
     * @return the first postal address.
     */
    @XmlTransient
    public Address getPostalAddress() {
        return addresses.getFirstValue(AddressType.POSTAL);
    }

    /**
     * Gets the first physical address.
     *
     * @return the first physical address.
     */
    @XmlTransient
    public Address getPhysicalAddress() {
        return addresses.getFirstValue(AddressType.PHYSICAL);
    }

    /**
     * Gets the first address by type or its equivalent defined by same as
     *
     * @return the address.
     */
    public Address getAddressOrSameAs(AddressType addressType) {
        Address address = addresses.getFirstValue(addressType);
        int indirectionCount = 0;
        while (address.getSameAs() != null && indirectionCount < 5) {
            address = getAddressOrSameAs(address.getSameAs());
        }
        return address;
    }
}
