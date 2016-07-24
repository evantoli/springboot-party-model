package au.com.softwarekitchen.seed;

import java.util.Random;

public class HonorificGenerator {
    
    private static final Random deterministicRandom = new Random(89235674L);

    public static String next() {
        return honorifics[deterministicRandom.nextInt(honorifics.length)];
    }

    private static final String[] honorifics = {
        "Mr",
        "Mr",
        "Mr",
        "Ms",
        "Mrs",
        "Miss"
    };
}
