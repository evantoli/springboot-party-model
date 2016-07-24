package au.com.softwarekitchen.model;

import au.com.softwarekitchen.domain.NameType;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class Name extends AbstractModel<Long> implements Typed<NameType>, Comparable<Name> {
    
    @NotNull
    private NameType type;
    
    @NotBlank
    private String value;

    /**
     * Returns <tt>true</tt> if all the internal value fields (disregarding <tt>type</tt>) are blank or
     * the specified <tt>name</tt> object is <tt>null</tt>. 
     *  
     * @param name the object to check for blank-ness. 
     * @return <tt>true</tt> if all the internal value fields (disregarding <tt>type</tt>) are blank or
     * the specified <tt>name</tt> object is <tt>null</tt>.
     */
    public static boolean isBlank(final Name name) {
        
        return (
                // either the object is null 
                (name == null)
                //or        
                ||
                // the non-type fields are blank.   
                StringUtils.isBlank(name.value)
        );
    }

    /**
     * Return the logical opposite of {@link #isBlank(Name)}
     * @param name the object to check for non blank-ness.
     * @return the logical opposite of {@link #isBlank(Name)}
     */
    public static boolean isNotBlank(final Name name) {
        return !isBlank(name);
    }

    public Name() {
    }

    public Name(final NameType type) {
        setType(type);
    }

    public Name(final NameType type, final String value) {
        setType(type);
        setValue(value);
    }

    @Override
    public NameType getType() {
        return type;
    }

    public void setType(final NameType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = StringUtils.normalizeSpace(value);
    }

    @Override
    public int compareTo(Name o) {

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
        if (!(o instanceof Name)) return false;

        Name name = (Name) o;

        if (type != name.type) return false;
        if (value != null ? !value.equals(name.value) : name.value != null) return false;

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
        sb.append(", value:'").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
