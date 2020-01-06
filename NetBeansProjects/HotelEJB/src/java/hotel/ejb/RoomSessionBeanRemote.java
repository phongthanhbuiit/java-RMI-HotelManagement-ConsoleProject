package hotel.ejb;

import hotel.entiry.Room;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author windsora
 */
@Remote
public interface RoomSessionBeanRemote {

    public List<Room> getRoom();
    
}
