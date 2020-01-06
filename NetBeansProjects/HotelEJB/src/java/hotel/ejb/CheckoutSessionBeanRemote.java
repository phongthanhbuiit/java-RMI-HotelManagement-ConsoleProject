
package hotel.ejb;

import hotel.entiry.Checkout;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author windsora
 */
@Remote
public interface CheckoutSessionBeanRemote {

    public List<Checkout> getCheckOutlist();

    public boolean createCheckout(Checkout c);

    public boolean deleteCheckout(int id);
    
}
