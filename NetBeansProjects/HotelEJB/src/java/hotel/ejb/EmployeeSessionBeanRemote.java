package hotel.ejb;

import hotel.entiry.Employee;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author windsora
 */
@Remote
public interface EmployeeSessionBeanRemote {

    public List<Employee> getEmployees();

    public boolean createEmployee(Employee e);

    public boolean deleteEmployee(int id);

    
}
