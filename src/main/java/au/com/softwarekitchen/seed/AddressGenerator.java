package au.com.softwarekitchen.seed;

import au.com.softwarekitchen.model.Address;

import java.util.Random;

public class AddressGenerator {

    private static final Random deterministicRandom = new Random(9268341L);

    public static Address next() {
        
        final Address address = addresses[deterministicRandom.nextInt(addresses.length)];
        
        return new Address(
                address.getType(),
                address.getSubpremise(),
                address.getStreetNumber(),
                address.getRoute(),
                address.getLocality(),
                address.getPostcode(),
                address.getSubdivision(),
                address.getCountry());
    }

    private static final Address[] addresses = {
            new Address(null, null, "28", "Park Street", "Peakhurst", "2210", "NSW", "AU"),
            new Address(null, null, "67", "Clarke Road", "Hornsby", "2077", "NSW", "AU"),
            new Address(null, null, "45", "Clovelly Road", "Randwick", "2031", "NSW", "AU"),
            new Address(null, "2", "130", "Mitchell Road", "Alexandria", "2015", "NSW", "AU"),
            new Address(null, "3", "117-133", "Belmont Street", "Alexandria", "2015", "NSW", "AU"),
            new Address(null, null, "43a", "Prince Street", "Mosman", "2088", "NSW", "AU"),
            new Address(null, null, "26", "Alma Street", "Clontarf", "2093", "NSW", "AU"),
            new Address(null, null, "12", "Margaret Street", "Fairlight", "2094", "NSW", "AU"),
            new Address(null, null, "20", "Springvale Avenue", "Frenchs Forest", "2086", "NSW", "AU"),
            new Address(null, null, "7", "Queens Court", "Castle Hill", "2154", "NSW", "AU"),
            new Address(null, null, "17", "Petunia Avenue", "Bankstown", "2200", "NSW", "AU"),
            new Address(null, null, "171", "E 24th St", "Leith", "7315", "TAS", "AU"),
            new Address(null, null, "22222", "Acoma St", "Proston", "4613", "QLD", "AU"),
            new Address(null, null, "534", "Schoenborn St", "Hamel", "6215", "WA", "AU"),
            new Address(null, null, "69206", "Jackson Ave", "Talmalmo", "2640", "NSW", "AU"),
            new Address(null, null, "808", "Glen Cove Ave", "Lane Cove", "1595", "NSW", "AU"),
            new Address(null, null, "373", "Lafayette St", "Cartmeticup", "6316", "WA", "AU"),
            new Address(null, null, "87", "Sylvan Ave", "Nyamup", "6258", "WA", "AU"),
            new Address(null, null, "60562", "Ky Rt 321", "Bendick Murrell", "2803", "NSW", "AU"),
            new Address(null, null, "70", "S 18th Pl", "Purrawunda", "4356", "QLD", "AU"),
            new Address(null, null, "8839", "Ventura Blvd", "Blanchetown", "5357", "SA", "AU"),
            new Address(null, null, "3684", "N Wacker Dr", "Rockside", "4343", "QLD", "AU"),
            new Address(null, null, "68828", "S 32nd St", "Rosegarland", "7140", "TAS", "AU"),
            new Address(null, null, "43157", "Cypress St", "Ringwood", "4343", "QLD", "AU"),
            new Address(null, null, "6", "S Hanover Ave", "Maylands", "6931", "WA", "AU"),
            new Address(null, null, "27916", "Tarrytown Rd", "Wooloowin", "4030", "QLD", "AU"),
            new Address(null, null, "79620", "Timber Dr", "Arthurville", "2820", "NSW", "AU"),
            new Address(null, null, "387", "Airway Cir", "Mapleton", "4560", "QLD", "AU"),
            new Address(null, null, "570", "W Pine St", "Tuggerawong", "2259", "NSW", "AU"),
            new Address(null, null, "823", "Fishers Ln", "Red Hill", "2603", "AC", "AU"),
            new Address(null, null, "4", "Brookcrest Dr", "Inverlaw", "4610", "QLD", "AU"),
            new Address(null, null, "72", "Wyoming Ave", "Eugowra", "2806", "NSW", "AU"),
            new Address(null, null, "754", "Sammis Ave", "Kotara Fair", "2289", "NSW", "AU"),
            new Address(null, null, "660", "N Green St", "Burpengary", "4505", "QLD", "AU"),
            new Address(null, null, "970", "Waterloo Rd", "Ellis Beach", "4879", "QLD", "AU"),
            new Address(null, null, "4129", "Abbott Dr", "Fish Creek", "3959", "VIC", "AU"),
            new Address(null, null, "1529", "Prince Rodgers Ave", "Kennedy", "4816", "QLD", "AU"),
            new Address(null, null, "2", "N Valley Mills Dr", "Cape Portland", "7264", "TAS", "AU"),
            new Address(null, null, "50968", "Kurtz St", "Warra", "4411", "QLD", "AU"),
            new Address(null, null, "8", "Old County Rd", "Alvie", "3249", "VIC", "AU"),
            new Address(null, null, "13904", "S 35th St", "Wherrol Flat", "2429", "NSW", "AU"),
            new Address(null, null, "6149", "Kapiolani Blvd", "Placid Hills", "4343", "QLD", "AU"),
            new Address(null, null, "95431", "34th Ave", "Nedlands", "6909", "WA", "AU"),
            new Address(null, null, "25", "Swift Ave", "Auchenflower", "4066", "QLD", "AU"),
            new Address(null, "59", "1585", "Salem Church Rd", "Dangar Island", "2083", "NSW", "AU"),
            new Address(null, null, "75962", "E Drinker St", "Sunny Nook", "4605", "QLD", "AU"),
            new Address(null, null, "8978", "W Henrietta Rd", "Minden", "4311", "QLD", "AU"),
            new Address(null, "272", "86", "Worth St", "Tibradden", "6532", "WA", "AU"),
            new Address(null, "191", "15", "Campville Rd", "Clermont", "4721", "QLD", "AU"),
            new Address(null, null, "3", "N Ridge Ave", "Kadina", "5554", "SA", "AU"),
            new Address(null, null, "3713", "Poway Rd", "Sawtell", "2452", "NSW", "AU"),
            new Address(null, null, "1", "E 17th St", "East Gosford", "2250", "NSW", "AU"),
            new Address(null, null, "5", "Liberty Ave", "Fosterville", "3557", "VIC", "AU"),
            new Address(null, null, "404", "Broxton Ave", "Bateau Bay", "2261", "NSW", "AU"),
            new Address(null, "282", "4", "S Main St", "Glenmoral", "4719", "QLD", "AU"),
            new Address(null, "15", "1255", "W Passaic St", "Bolivia", "2372", "NSW", "AU"),
            new Address(null, null, "377", "Excalibur Dr", "East Melbourne", "3002", "VIC", "AU"),
            new Address(null, null, "7", "Wilshire Blvd", "Taringa", "4068", "QLD", "AU"),
            new Address(null, null, "7", "Hugh Wallis Rd", "Koolan Island", "6733", "WA", "AU"),
            new Address(null, null, "7177", "E 14th St", "Maleny", "4552", "QLD", "AU"),
            new Address(null, null, "67765", "W 11th St", "Yelverton", "6280", "WA", "AU"),
            new Address(null, "59", "99968", "Merced St", "Flinders", "2529", "NSW", "AU"),
            new Address(null, null, "40", "E 19th Ave", "Empire Bay", "2257", "NSW", "AU"),
            new Address(null, null, "84826", "Plaza Dr", "Rose Bay North", "2030", "NSW", "AU"),
            new Address(null, null, "20214", "W Main St", "Macks Creek", "3971", "VIC", "AU"),
            new Address(null, null, "8039", "Howard Ave	East", "Toowoomba", "4350", "QLD", "AU"),
            new Address(null, null, "8", "Austin Bluffs Pky", "Bimbijy", "6472", "WA", "AU"),
            new Address(null, null, "96605", "Pioneer Rd", "Kundabung", "2441", "NSW", "AU"),
            new Address(null, "10", "6", "Hope Rd", "Pinnacle", "4741", "QLD", "AU"),
            new Address(null, null, "65898", "E St Nw", "Watsonville", "4887", "QLD", "AU"),
    };
}
