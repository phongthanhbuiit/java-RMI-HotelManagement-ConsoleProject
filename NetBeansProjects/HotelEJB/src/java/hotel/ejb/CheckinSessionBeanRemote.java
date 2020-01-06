/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.ejb;

import hotel.entiry.Checkin;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author windsora
 */
@Remote
public interface CheckinSessionBeanRemote {

    public boolean createCheckin(Checkin c);

    public List<Checkin> getChekinlist();

    public boolean deleteCheckin(int id);
    
}
