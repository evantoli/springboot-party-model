package au.com.softwarekitchen.model;

import au.com.softwarekitchen.domain.PhoneNumberType;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class PhoneNumber extends AbstractModel<Long> implements Typed<PhoneNumberType>, Comparable<PhoneNumber> {

    @NotNull
    private PhoneNumberType type;
    
    @NotBlank
    private String number;

    /**
     * Returns <tt>true</tt> if all the internal value fields (disregarding <tt>type</tt>) are blank or
     * the specified <tt>phoneNumber</tt> object is <tt>null</tt>.
     *
     * @param phoneNumber the object to check for blank-ness.
     * @return <tt>true</tt> if all the internal value fields (disregarding <tt>type</tt>) are blank or
     * the specified <tt>phoneNumber</tt> object is <tt>null</tt>.
     */
    public static boolean isBlank(final PhoneNumber phoneNumber) {

        return (
                // either the object is null
                (phoneNumber == null)
                //or
                ||
                // the non-type fields are blank.
                StringUtils.isBlank(phoneNumber.number)
        );
    }

    /**
     * Return the logical opposite of {@link #isBlank(PhoneNumber)}
     * @param phoneNumber the object to check for non blank-ness.
     * @return the logical opposite of {@link #isBlank(PhoneNumber)}
     */
    public static boolean isNotBlank(final PhoneNumber phoneNumber) {
        return !isBlank(phoneNumber);
    }

    public PhoneNumber() {
    }

    public PhoneNumber(final PhoneNumberType type, final String number) {
        setType(type);
        setNumber(number);
    }

    @Override
    public PhoneNumberType getType() {
        return type;
    }

    public void setType(final PhoneNumberType type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(final String number) {
        if (number == null) {
            this.number = null;
        } else {
            this.number = number.replaceAll("//s+", "");
        }
    }

    @Override
    public int compareTo(PhoneNumber o) {

        if (o == null) {
            return 1;
        }

        return new CompareToBuilder()
                .append(this.type, o.type)
                .append(this.number, o.number)
                .toComparison();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneNumber)) return false;

        PhoneNumber that = (PhoneNumber) o;

        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (type != that.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return toString(new StringBuilder());
    }

    public String toString(final StringBuilder sb) {
        sb.append('{');
        sb.append("type:").append(type);
        sb.append(", number:'").append(number).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
