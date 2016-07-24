package au.com.softwarekitchen.seed;


import au.com.softwarekitchen.domain.*;
import au.com.softwarekitchen.model.*;

import java.util.Random;

public class PersonGenerator {

    private static final Random deterministicRandom = new Random(922363031L);

    public static Person next(final Long id) {

        final Person person = new Person();
        person.setId(id);

        final boolean hasMiddleName = deterministicRandom.nextBoolean();

        person.setHonorific(HonorificGenerator.next());
        switch (person.getHonorific()) {
            case "Mr":
                person.addName(new Name(NameType.GIVEN_NAME, MaleGivenNameGenerator.next()));
                if (hasMiddleName) {
                    person.addName(new Name(NameType.GIVEN_NAME, MaleGivenNameGenerator.next()));
                }
                break;
            case "Mrs":
            case "Ms":
                if (deterministicRandom.nextBoolean()) {
                    person.addName(new Name(NameType.MAIDEN_NAME, SurnameGenerator.next()));
                }
                // no break - fall-through
            case "Miss":
                person.addName(new Name(NameType.GIVEN_NAME, FemaleGivenNameGenerator.next()));
                if (hasMiddleName) {
                    person.addName(new Name(NameType.GIVEN_NAME, FemaleGivenNameGenerator.next()));
                }
                break;
        }
        person.addName(new Name(NameType.SURNAME, SurnameGenerator.next()));
        person.addName(new Name(NameType.PREFERRED_NAME, person.getFirstGivenNameAsString() + " " + person.getFirstSurnameAsString()));
        person.addName(new Name(NameType.MOTHERS_MAIDEN_NAME, SurnameGenerator.next()));
        person.addLocalDate(new LocalDateValue(LocalDateType.DATE_OF_BIRTH, DateOfBirthGenerator.next()));
        person.addEmailAddress(new EmailAddress(EmailAddressType.PRIMARY, EmailAddressGenerator.next(person.getFirstGivenNameAsString(), person.getFirstSurnameAsString(), null), EmailAddressStatus.CONFIRMED_EXISTS));

        if (deterministicRandom.nextBoolean()) {
            person.addEmailAddress(new EmailAddress(EmailAddressType.ALTERNATE, EmailAddressGenerator.next(person.getFirstGivenNameAsString(), person.getFirstSurnameAsString(), null), EmailAddressStatus.UNCONFIRMED_EXISTS));
        }

        final Address address = AddressGenerator.next();
        address.setType(AddressType.RESIDENTIAL);
        person.addAddress(address);
        
        final Address postalAddress = new Address(AddressType.POSTAL);
        postalAddress.setSameAs(AddressType.RESIDENTIAL);
        person.addAddress(postalAddress);
        
        return person;
    }
}
