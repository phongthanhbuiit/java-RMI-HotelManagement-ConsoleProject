package hotel.ejb;

import hotel.entiry.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author windsora
 */
@Stateless
public class EmployeeSessionBean implements EmployeeSessionBeanRemote {

    @Override
    public List<Employee> getEmployees() {

        //String url = "jdbc:postgresql://localhost:5433/postgres";
        String user = "postgres";
        String password = "password";

        DataSource ds;
        Context ctx;
        String url = "java:/PostgresDS";
        List<Employee> employees = new ArrayList<Employee>();

        try {

            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(url);

            Connection con = ds.getConnection(user, password);
            {

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from public.employee");

                Employee employee;
                while (rs.next()) {
                    employee = new Employee();
                    employee.setIdEmployee(rs.getInt(1));
                    employee.setName(rs.getString(2));
                    employee.setPhone(rs.getString(3));
                    employee.setPosition(rs.getString(4));
                    employee.setSalary(rs.getInt(5));
                    employee.setDateStart(rs.getString(6));
                    employees.add(employee);
                }
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            Logger.getLogger(EmployeeSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employees;
    }

    @Override
    public boolean createEmployee(Employee e) {

        String user = "postgres";
        String password = "password";
        DataSource ds;
        Context ctx;
        String url = "java:/PostgresDS";
        String query = "INSERT INTO public.employee(name, phone, position, salary, date_start) VALUES(?, ?, ?, ?, ?)";
        int affectedrows = 0;

        try {

            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(url);

            Connection con = ds.getConnection(user, password);
            {
                 //tat autocomit de thuc hien transection
                con.setAutoCommit(false);
                                
                PreparedStatement pst = con.prepareStatement(query);
                
                pst.setString(1, e.getName());
                pst.setString(2, e.getPhone());
                pst.setString(3, e.getPosition());
                pst.setInt(4, e.getSalary());
                pst.setString(5, e.getDateStart());
                affectedrows = pst.executeUpdate();
                
                if (affectedrows > 0) {
                    con.commit();
                } else {
                    con.rollback();
                    return false;
                }
            }    
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            Logger.getLogger(EmployeeSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    @Override
    public boolean deleteEmployee(int id) {

        String user = "postgres";
        String password = "password";
        DataSource ds;
        Context ctx;
        String url = "java:/PostgresDS";
        String query = "DELETE FROM public.employee WHERE id_employee = ?";
        int affectedrows = 0;

        try {

            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(url);

            Connection con = ds.getConnection(user, password);
            {
                 //tat autocomit de thuc hien transection
                con.setAutoCommit(false);
                                
                PreparedStatement pst = con.prepareStatement(query);
                
                pst.setInt(1, id);
                
                affectedrows = pst.executeUpdate();
                
                if (affectedrows > 0) {
                    con.commit();
                } else {
                    con.rollback();
                    return false;
                }
            }    
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            Logger.getLogger(EmployeeSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

}
