
package hotel.ejb;

import hotel.entiry.Tobook;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author windsora
 */
@Remote
public interface TobookSessionBeanRemote {

    public boolean createBook(Tobook b);

    public List<Tobook> getTobook();

    public boolean deleteBooked(int id);
    
}
