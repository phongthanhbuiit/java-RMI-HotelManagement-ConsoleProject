package hotel.ejb;

import hotel.entiry.Customerbooked;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author windsora
 */
@Remote
public interface CustomerBookedSessionBeanRemote {

    public boolean createCustomerBooked(Customerbooked cb);

    public List<Customerbooked> getCustomerBookedlist();

    public boolean deleteCustomerBooked(int id);

    
}
