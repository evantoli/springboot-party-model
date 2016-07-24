package au.com.softwarekitchen.model;

import au.com.softwarekitchen.domain.EmailAddressStatus;
import au.com.softwarekitchen.domain.EmailAddressType;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmailAddress extends AbstractModel<Long> implements Typed<EmailAddressType>, Comparable<EmailAddress> {

    @NotNull
    private EmailAddressType type;
    
    @NotNull
    private EmailAddressStatus status = EmailAddressStatus.UNCONFIRMED_EXISTS;

    @NotNull
    @Email
    @Size(max = 200)
    private String value;

    /**
     * Returns <tt>true</tt> if all the internal value fields 
     * (disregarding <tt>type</tt> and <tt>status</tt>) are blank or
     * the specified <tt>emailAddress</tt> object is <tt>null</tt>.
     *
     * @param emailAddress the object to check for blank-ness.
     * @return <tt>true</tt> if all the internal value fields 
     *     (disregarding <tt>type</tt> and <tt>status</tt>) are blank or
     *     the specified <tt>emailAddress</tt> object is <tt>null</tt>.
     */
    public static boolean isBlank(final EmailAddress emailAddress) {

        return (
                // either the object is null
                (emailAddress == null)
                //or
                ||
                // the non-type fields are blank.
                StringUtils.isBlank(emailAddress.value)
        );
    }

    /**
     * Return the logical opposite of {@link #isBlank(EmailAddress)}
     * @param emailAddress the object to check for non blank-ness.
     * @return the logical opposite of {@link #isBlank(EmailAddress)}
     */
    public static boolean isNotBlank(final EmailAddress emailAddress) {
        return !isBlank(emailAddress);
    }

    public EmailAddress() {
    }

    public EmailAddress(final EmailAddressType type) {
        setType(type);
    }

    public EmailAddress(final EmailAddressType type, final String emailString) {
        setType(type);
        setValue(emailString);
    }

    public EmailAddress(final EmailAddressType type, final String emailString, final EmailAddressStatus status) {
        setType(type);
        setValue(emailString);
        setStatus(status);
    }

    @Override
    public EmailAddressType getType() {
        return type;
    }

    public void setType(EmailAddressType type) {
        this.type = type;
    }

    public String getValue() {
        if (value == null) {
            value = "";
        }
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public EmailAddressStatus getStatus() {
        return status;
    }

    public void setStatus(EmailAddressStatus status) {
        this.status = status;
    }

    @Override
    public int compareTo(EmailAddress o) {

        if (o == null) {
            return 1;
        }

        return new CompareToBuilder()
                .append(this.type, o.type)
                .append(this.status, o.status)
                .append(this.value, o.value)
                .toComparison();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmailAddress)) return false;

        EmailAddress that = (EmailAddress) o;

        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        if (status != that.status) return false;
        if (type != that.type) return false;

        return true;
    }

    public boolean equalsEmailAddressValue(@NotBlank String s) {
        return this.value.equals(s);
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return toString(new StringBuilder());
    }

    public String toString(final StringBuilder sb) {
        sb.append("{");
        sb.append("type:").append(type);
        sb.append(", status:").append(status);
        sb.append(", value:'").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
