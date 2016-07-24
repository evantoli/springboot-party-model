package au.com.softwarekitchen.model;

import au.com.softwarekitchen.domain.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Person extends Party {

    private static final Logger log = LoggerFactory.getLogger(Person.class);

    private String honorific;

    public Person() {
        super(PartyType.PERSON);
    }

    public Person(String preferredName, String primaryEmail) {
        this();

        addName(new Name(NameType.PREFERRED_NAME, preferredName));
        addEmailAddress(new EmailAddress(EmailAddressType.PRIMARY, primaryEmail, EmailAddressStatus.UNCONFIRMED_EXISTS));
    }

    public String getHonorific() {
        return honorific;
    }

    public void setHonorific(String honorific) {
        this.honorific = honorific;
    }

    public String getDisplayName() {
        
        // If we have a preferred name then just return that.
        final Name preferredName = getPreferredName();
        if (Name.isNotBlank(preferredName)) {
            return preferredName.getValue();
        }
            
        // Otherwise, create a display name from the first given name and surname. 
        final Name givenName = getFirstGivenName();
        final Name surname = getFirstSurname();
        
        String result = "";
        
        if (Name.isNotBlank(givenName)) {
            result += givenName.getValue();
        }

        if (Name.isNotBlank(surname)) {
            if (StringUtils.isNotBlank(result)) {
                result += " ";
            }
            result += surname.getValue();
        }

        return result;        
    }

    public List<Name> getGivenNames() {
        return names.getNames(NameType.GIVEN_NAME);
    }

    public List<String> getGivenNamesAsStrings() {
        return names.getNames(NameType.GIVEN_NAME).stream().map(Name::getValue).collect(Collectors.toList());
    }

    public Name getFirstGivenName() {
        return names.getFirstByType(NameType.GIVEN_NAME);
    }

    public String getFirstGivenNameAsString() {
        final Name name = getFirstGivenName();
        return name == null ? null : name.getValue();
    }

    public String getFirstGivenNameInitialAsString() {
        final Name name = getFirstGivenName();
        return name == null || name.getValue().length() == 0 ? null : name.getValue().substring(0, 1);
    }

    public Name getSecondGivenName() {
        return names.getSecondByType(NameType.GIVEN_NAME);
    }

    public String getSecondGivenNameAsString() {
        final Name name = getSecondGivenName();
        return name == null ? null : name.getValue();
    }

    public String getSecondGivenNameInitialAsString() {
        final Name name = getSecondGivenName();
        return name == null || name.getValue().length() == 0 ? null : name.getValue().substring(0, 1);
    }

    public List<Name> getSurnames() {
        return names.getNames(NameType.SURNAME);
    }

    public List<String> getSurnamesAsStrings() {
        return names.getNames(NameType.SURNAME).stream().map(Name::getValue).collect(Collectors.toList());
    }

    public Name getFirstSurname() {
        return names.getFirstByType(NameType.SURNAME);
    }

    public String getFirstSurnameAsString() {
        final Name name = getFirstSurname();
        return name == null ? null : name.getValue();
    }

    public Name getMaidenName() {
        return names.getFirstByType(NameType.MAIDEN_NAME);
    }

    public String getMaidenNameAsString() {
        final Name name = getMaidenName();
        return name == null ? null : name.getValue();
    }

    public Name getMothersMaidenName() {
        return names.getFirstByType(NameType.MOTHERS_MAIDEN_NAME);
    }

    public String getMothersMaidenNameAsString() {
        final Name name = getMothersMaidenName();
        return name == null ? null : name.getValue();
    }

    public LocalDateValue getDateOfBirth() {
        return localDates.getFirstValue(LocalDateType.DATE_OF_BIRTH);
    }

    public LocalDate getDateOfBirthAsLocalDate() {
        final LocalDateValue localDateVO = localDates.getFirstValue(LocalDateType.DATE_OF_BIRTH);
        return localDateVO == null ? null : localDateVO.getValue();
    }

    public String getFullName(){

        // combine all the names in a single list instance
        List<String> nameList = getGivenNamesAsStrings();
        nameList.addAll(getSurnamesAsStrings());

        return String.join(" ", nameList);
    }

}
