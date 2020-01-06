package hotel.ejb;

import hotel.entiry.Useradmin;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author windsora
 */
@Remote
public interface UseradminSessionBeanRemote {

    public List<Useradmin> getUseradmins();

    public boolean checkUser(String user, String password);

    public void changePassword(String newpassword);
    
}
