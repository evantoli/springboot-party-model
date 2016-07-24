package au.com.softwarekitchen.seed;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class EmailAddressGenerator {

    private static final Logger log = LoggerFactory.getLogger(EmailAddressGenerator.class);

    private static final Random deterministicRandom = new Random(285444617293L);

    private static final Set<String> ALREADY_USED = new HashSet<>();

    public static String next(final String givenName, final String surname, String domainName) {

        if (StringUtils.isBlank(domainName)) {
            // The first 30 email addresses will be @fake.com
            if (ALREADY_USED.size() < 30) {
                domainName = domainNames[0];
            } else {
                domainName = domainNames[deterministicRandom.nextInt(domainNames.length)];
            }
        }
        
        String value = givenName.toLowerCase() + "@" + domainName;
        if (ALREADY_USED.contains(value)) {
            value = givenName.toLowerCase() + '.' + surname.toLowerCase() + "@" + domainName;
            int i = 100;
            while (ALREADY_USED.contains(value)) {
                value = givenName.toLowerCase() + "0" + (++i) + "@" + domainName;
            }
        }
            
        ALREADY_USED.add(value);
        return value;
    }
    
    private static String [] domainNames = {
            "fake.com",
            "faux.com",
            "fiction.com",
            "false.com",
            "whatever.com",
            "dummy.com",
            "mock.com",
            "pretend.com",
            "fanciful.com",
            "dream.com",
            "fake.com.au",
            "faux.com.au",
            "fiction.com.au",
            "false.com.au",
            "whatever.com.au",
            "dummy.com.au",
            "mock.com.au",
            "pretend.com.au",
            "fanciful.com.au",
            "dream.com.au",
            "fake.net",
            "faux.net",
            "fiction.net",
            "false.net",
            "whatever.net",
            "dummy.net",
            "mock.net",
            "pretend.net",
            "fanciful.net",
            "dream.net",
            "fake.org",
            "faux.org",
            "fiction.org",
            "false.org",
            "whatever.org",
            "dummy.org",
            "mock.org",
            "pretend.org",
            "fanciful.org",
            "dream.org"

    };
}
