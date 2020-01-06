package hoteltester;

import java.text.ParseException;

/**
 *
 * @author windsora
 */
public class Tester {
    
    public static void main (String[] args) throws ParseException {
        HotelTester ejb = new HotelTester();
        ejb.testStatelessEJB();
    }
}
