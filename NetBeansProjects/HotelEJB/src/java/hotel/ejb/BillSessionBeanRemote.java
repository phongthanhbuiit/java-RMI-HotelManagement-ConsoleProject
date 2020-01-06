package hotel.ejb;

import hotel.entiry.Bill;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author windsora
 */
@Remote
public interface BillSessionBeanRemote {

    public boolean createBill(Bill b);

    public List<Bill> getBills();
    
}
