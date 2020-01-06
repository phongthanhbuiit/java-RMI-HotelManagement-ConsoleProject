package hotel.ejb;

import hotel.entiry.Customerbooked;
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
public class CustomerBookedSessionBean implements CustomerBookedSessionBeanRemote {

    @Override
    public boolean createCustomerBooked(Customerbooked cb) {

        String user = "postgres";
        String password = "password";
        DataSource ds;
        Context ctx;
        String url = "java:/PostgresDS";
        String query = "INSERT INTO public.customerbooked(name, identity_card, phone, address, id_room, datecheckin, datecheckout) VALUES (?, ?, ?, ?, ?, ?, ?)";

        int affectedrows = 0;

        try {

            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(url);

            Connection con = ds.getConnection(user, password);
            {
                //tat autocomit de thuc hien transection
                con.setAutoCommit(false);

                PreparedStatement pst = con.prepareStatement(query);

                pst.setString(1, cb.getName());
                pst.setString(2, cb.getIdentityCard());
                pst.setString(3, cb.getPhone());
                pst.setString(4, cb.getAddress());
                pst.setInt(5, cb.getIdRoom());
                pst.setString(6, cb.getDatecheckin());
                pst.setString(7, cb.getDatecheckout());

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
            Logger.getLogger(CustomerBookedSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public List<Customerbooked> getCustomerBookedlist() {

        //String url = "jdbc:postgresql://localhost:5433/postgres";
        String user = "postgres";
        String password = "password";

        DataSource ds;
        Context ctx;
        String url = "java:/PostgresDS";
        List<Customerbooked> cb_lst = new ArrayList<Customerbooked>();

        try {

            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(url);

            Connection con = ds.getConnection(user, password);
            {

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from public.customerbooked");

                Customerbooked cb;
                while (rs.next()) {
                    cb = new Customerbooked();
                    cb.setIdCusbooked(rs.getInt(1));
                    cb.setName(rs.getString(2));
                    cb.setIdentityCard(rs.getString(3));
                    cb.setPhone(rs.getString(4));
                    cb.setAddress(rs.getString(5));
                    cb.setDatecheckin(rs.getString(6));
                    cb.setDatecheckout(rs.getString(7));
                    cb_lst.add(cb);
                }
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            Logger.getLogger(CustomerBookedSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cb_lst;
    }

    @Override
    public boolean deleteCustomerBooked(int id) {
        String user = "postgres";
        String password = "password";
        DataSource ds;
        Context ctx;
        String url = "java:/PostgresDS";
        String query = "DELETE FROM public.customerbooked WHERE id_cusbooked = ?";
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
            Logger.getLogger(CustomerBookedSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

}
