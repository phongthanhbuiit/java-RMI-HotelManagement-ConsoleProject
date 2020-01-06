package hotel.ejb;

import hotel.entiry.Customer;
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
public class CustomerSessionBean implements CustomerSessionBeanRemote {
    
    @Override
    public boolean createCustomer(Customer c) {
        
        String user = "postgres";
        String password = "password";        
        DataSource ds;
        Context ctx;
        String url = "java:/PostgresDS";
        String query = "INSERT INTO public.customer(name, identity_card, phone, address) VALUES (?, ?, ?, ?) ";
        
        int affectedrows = 0;

        try {

            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(url);

            Connection con = ds.getConnection(user, password);
            {
                //tat autocomit de thuc hien transection
                con.setAutoCommit(false);

                PreparedStatement pst = con.prepareStatement(query);

                pst.setString(1, c.getName());
                pst.setString(2, c.getIdentityCard());
                pst.setString(3, c.getPhone());
                pst.setString(4, c.getAddress());

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
            return false;
        } catch (NamingException ex) {
            Logger.getLogger(CustomerSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    @Override
    public List<Customer> getCustomers() {

        //String url = "jdbc:postgresql://localhost:5433/postgres";
        String user = "postgres";
        String password = "password";
        
        DataSource ds;
        Context ctx;
        String url = "java:/PostgresDS";
        List<Customer> customers = new ArrayList<Customer>();

        try {

            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(url);


            Connection con = ds.getConnection(user, password);
            {   

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from public.customer");

                Customer customer;
                while (rs.next()) {
                    customer = new Customer();
                    customer.setIdCustomer(rs.getInt(1));
                    customer.setName(rs.getString(2));
                    customer.setIdentityCard(rs.getString(3));
                    customer.setAddress(rs.getString(4));
                    customer.setPhone(rs.getString(5));
                    customers.add(customer);
                }
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            Logger.getLogger(CustomerSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customers;
    }
    
    @Override
    public boolean deleteCustomer(int id) {
        String user = "postgres";
        String password = "password";
        DataSource ds;
        Context ctx;
        String url = "java:/PostgresDS";
        String query = "DELETE FROM public.customer WHERE id_customer = ?";
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
            Logger.getLogger(CustomerSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
