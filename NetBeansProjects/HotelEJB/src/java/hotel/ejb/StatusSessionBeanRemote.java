
package hotel.ejb;

import hotel.entiry.Status;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author windsora
 */
@Remote
public interface StatusSessionBeanRemote {

    public List<Status> getStatuss();

    public List<Status> freeRoom();

    public List<Status> freeRoomBook();

    
    public void updateStatus(Status s);

    public boolean deleteStatus(int id);

    public List<Status> NotfreeRoom();

    public boolean createStatus(Status st);
    
}
