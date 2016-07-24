package au.com.softwarekitchen.model;

import au.com.softwarekitchen.domain.LocalDateType;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class LocalDateValue extends AbstractModel<Long> implements Typed<LocalDateType>, Comparable<LocalDateValue> {
    
    @NotNull
    private LocalDateType type;
    
    @NotBlank
    private java.time.LocalDate value;

    /**
     * Returns <tt>true</tt> if all the internal value fields (disregarding <tt>type</tt>) are blank or
     * the specified <tt>value</tt> object is <tt>null</tt>.
     *
     * @param localDateVO the object to check for blank-ness.
     * @return <tt>true</tt> if all the internal value fields (disregarding <tt>type</tt>) are blank or
     * the specified <tt>value</tt> object is <tt>null</tt>.
     */
    public static boolean isBlank(final LocalDateValue localDateVO) {

        return (
                // either the object is null
                (localDateVO == null)
                //or
                ||
                // the non-type fields are null or blank.
                (localDateVO.value == null)
        );
    }

    /**
     * Return the logical opposite of {@link #isBlank(LocalDateValue)}
     * @param localDateVO the object to check for non blank-ness.
     * @return the logical opposite of {@link #isBlank(LocalDateValue)}
     */
    public static boolean isNotBlank(final LocalDateValue localDateVO) {
        return !isBlank(localDateVO);
    }

    public LocalDateValue() {
    }

    public LocalDateValue(final LocalDateType type) {
        setType(type);
    }

    public LocalDateValue(final LocalDateType type, final java.time.LocalDate value) {
        setType(type);
        setValue(value);
    }

    @Override
    public LocalDateType getType() {
        return type;
    }

    public void setType(LocalDateType type) {
        this.type = type;
    }

    public java.time.LocalDate getValue() {
        return value;
    }

    public void setValue(java.time.LocalDate value) {
        this.value = value;
    }

    @Override
    public int compareTo(LocalDateValue o) {

        if (o == null) {
            return 1;
        }

        return new CompareToBuilder()
                .append(this.type, o.type)
                .append(this.value, o.value)
                .toComparison();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LocalDateValue)) return false;

        LocalDateValue localDateVO1 = (LocalDateValue) o;

        if (value != null ? !value.equals(localDateVO1.value) : localDateVO1.value != null) return false;
        if (type != localDateVO1.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return toString(new StringBuilder());
    }

    public String toString(final StringBuilder sb) {
        sb.append('{');
        sb.append("type:").append(type);
        sb.append(", value:").append(value);
        sb.append('}');
        return sb.toString();
    }
}
