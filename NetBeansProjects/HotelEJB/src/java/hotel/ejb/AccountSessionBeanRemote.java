/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.ejb;

import hotel.entiry.Account;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author windsora
 */
@Remote
public interface AccountSessionBeanRemote {

    public List<Account> getAccounts();

    public boolean checkUser(String user, String password);

    public boolean createAccount(Account acc);

    public boolean userExist(String user);

    public boolean userExist(int id);

    public boolean deleteAccout(int id);

    public void changePassword(String newpassword, String username);
    
}
