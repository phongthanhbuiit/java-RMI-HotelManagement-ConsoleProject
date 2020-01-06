/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.ejb;

import hotel.entiry.Customer;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author windsora
 */
@Remote
public interface CustomerSessionBeanRemote {

    public List<Customer> getCustomers();

    public boolean createCustomer(Customer c);

    public boolean deleteCustomer(int id);
    
}
