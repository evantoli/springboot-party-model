package au.com.softwarekitchen.model;

import au.com.softwarekitchen.domain.AddressType;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

/**
 * Created by van on 23/07/2016.
 */
public class Address extends AbstractModel<Long> implements Typed<AddressType>, Comparable<Address> {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    /**
     * The type of address that this is.
     */
    private AddressType type;

    /**
     * The suite, unit or apartment number.
     */
    private AddressType sameAs;

    /**
     * The suite, unit or apartment number.
     */
    @Size(max = 20)
    private String subpremise;

    /**
     * The street number.
     */
    @Size(max = 20)
    private String streetNumber;

    /**
     * The street name.
     */
    @Size(max = 200)
    private String route;

    /**
     * The locality / suburb.
     */
    @Size(max = 100)
    private String locality;

    /**
     * The postal code / zipcode.
     */
    @Size(max = 20)
    private String postcode;

    /**
     * The ISO 3166-2 code for states, territories, provinces.
     */
    @Size(max = 100)
    private String subdivision;


    /**
     * The ISO 3166-1 alpha-2 code for countries.
     */
    @Size(max = 2)
    private String country;

    /**
     * The latitude of the address.
     */
    private Double latitude;

    /**
     * The longitude of the address.
     */
    private Double longitude;

    public static boolean isBlank(final Address address) {
        return (address == null) || (
                address.sameAs == null &&
                StringUtils.isBlank(address.subpremise) &&
                StringUtils.isBlank(address.streetNumber) &&
                StringUtils.isBlank(address.route) &&
                StringUtils.isBlank(address.locality) &&
                StringUtils.isBlank(address.postcode) &&
                StringUtils.isBlank(address.subdivision) &&
                StringUtils.isBlank(address.country) &&
                address.latitude == null &&
                address.longitude == null
        );
    }

    public static boolean isNotBlank(final Address address) {
        return !isBlank(address);
    }

    public Address() {
    }

    public Address(final AddressType type) {
        this.type = type;
    }

    public Address(
            final AddressType type,
            final String subpremise,
            final String streetNumber,
            final String route,
            final String locality,
            final String postcode,
            final String subdivision,
            final String country) {

        this.type = type;
        this.subpremise = subpremise;
        this.streetNumber = streetNumber;
        this.route = route;
        this.locality = locality;
        this.postcode = postcode;
        this.subdivision = subdivision;
        this.country = country;
    }

    public AddressType getType() {
        return type;
    }

    public void setType(final AddressType type) {
        this.type = type;
    }

    public AddressType getSameAs() {
        return sameAs;
    }

    public void setSameAs(final AddressType sameAs) {
        this.sameAs = sameAs;
    }

    public String getSubpremise() {
        return subpremise;
    }

    public void setSubpremise(final String subpremise) {
        this.subpremise = StringUtils.normalizeSpace(subpremise);
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(final String streetNumber) {
        this.streetNumber = StringUtils.normalizeSpace(streetNumber);
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(final String route) {
        this.route = StringUtils.normalizeSpace(route);
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(final String locality) {
        this.locality = StringUtils.normalizeSpace(locality);
    }

    public String getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(final String subdivision) {
        this.subdivision = StringUtils.normalizeSpace(subdivision);
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(final String postcode) {
        this.postcode = StringUtils.normalizeSpace(postcode);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = StringUtils.normalizeSpace(country);
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(final Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(final Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public int compareTo(final Address o) {

        if (o == null) {
            return 1;
        }

        return new CompareToBuilder()
                .append(this.type, o.type)
                .append(this.sameAs, o.sameAs)
                .append(this.subpremise, o.subpremise)
                .append(this.streetNumber, o.streetNumber)
                .append(this.route, o.route)
                .append(this.locality, o.locality)
                .append(this.postcode, o.postcode)
                .append(this.subdivision, o.subdivision)
                .append(this.country, o.country)
                .append(this.latitude, o.latitude)
                .append(this.longitude, o.longitude)
                .toComparison();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        if (country != null ? !country.equals(address.country) : address.country != null) return false;
        if (latitude != null ? !latitude.equals(address.latitude) : address.latitude != null) return false;
        if (locality != null ? !locality.equals(address.locality) : address.locality != null) return false;
        if (longitude != null ? !longitude.equals(address.longitude) : address.longitude != null) return false;
        if (postcode != null ? !postcode.equals(address.postcode) : address.postcode != null) return false;
        if (route != null ? !route.equals(address.route) : address.route != null) return false;
        if (sameAs != address.sameAs) return false;
        if (streetNumber != null ? !streetNumber.equals(address.streetNumber) : address.streetNumber != null) return false;
        if (subdivision != null ? !subdivision.equals(address.subdivision) : address.subdivision != null) return false;
        if (subpremise != null ? !subpremise.equals(address.subpremise) : address.subpremise != null) return false;
        if (type != address.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (sameAs != null ? sameAs.hashCode() : 0);
        result = 31 * result + (subpremise != null ? subpremise.hashCode() : 0);
        result = 31 * result + (streetNumber != null ? streetNumber.hashCode() : 0);
        result = 31 * result + (route != null ? route.hashCode() : 0);
        result = 31 * result + (locality != null ? locality.hashCode() : 0);
        result = 31 * result + (postcode != null ? postcode.hashCode() : 0);
        result = 31 * result + (subdivision != null ? subdivision.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return toString(new StringBuilder());
    }

    public String toString(final StringBuilder sb) {
        sb.append('{');
        sb.append("id:").append(id);
        sb.append(", type:").append(type);
        sb.append(", sameAs:").append(sameAs);
        sb.append(", subpremise:'").append(subpremise).append('\'');
        sb.append(", streetNumber:'").append(streetNumber).append('\'');
        sb.append(", route:'").append(route).append('\'');
        sb.append(", locality:'").append(locality).append('\'');
        sb.append(", postcode:'").append(postcode).append('\'');
        sb.append(", subdivision:'").append(subdivision).append('\'');
        sb.append(", country:'").append(country).append('\'');
        sb.append(", latitude:").append(latitude);
        sb.append(", longitude:").append(longitude);
        sb.append('}');
        return sb.toString();
    }
}
