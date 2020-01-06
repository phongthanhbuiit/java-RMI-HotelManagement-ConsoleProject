package hotel.ejb;

import hotel.entiry.Checkout;
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
public class CheckoutSessionBean implements CheckoutSessionBeanRemote {

    @Override
    public boolean createCheckout(Checkout c) {

        String user = "postgres";
        String password = "password";
        DataSource ds;
        Context ctx;
        String url = "java:/PostgresDS";
            String query = "INSERT INTO public.checkout(name, identity_card, phone, address, id_room, date_checkin, date_checkout) VALUES (?, ?, ?, ?, ?, ?, ?)";

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
                pst.setInt(5, c.getIdRoom());
                pst.setString(6, c.getDateCheckin());
                pst.setString(7, c.getDateCheckout());

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
            Logger.getLogger(CheckoutSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public List<Checkout> getCheckOutlist() {

        //String url = "jdbc:postgresql://localhost:5433/postgres";
        String user = "postgres";
        String password = "password";

        DataSource ds;
        Context ctx;
        String url = "java:/PostgresDS";
        List<Checkout> co_lst = new ArrayList<Checkout>();

        try {

            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(url);

            Connection con = ds.getConnection(user, password);
            {

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from public.checkout");

                Checkout checkout;
                while (rs.next()) {
                    checkout = new Checkout();
                    checkout.setIdCheckout(rs.getInt(1));
                    checkout.setName(rs.getString(2));
                    checkout.setIdentityCard(rs.getString(3));
                    checkout.setPhone(rs.getString(4));
                    checkout.setAddress(rs.getString(5));
                    checkout.setIdRoom(rs.getInt(6));
                    checkout.setDateCheckin(rs.getString(7));
                    checkout.setDateCheckout(rs.getString(8));
                    co_lst.add(checkout);
                }
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            Logger.getLogger(CheckoutSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return co_lst;
    }

    @Override
    public boolean deleteCheckout(int id) {
        String user = "postgres";
        String password = "password";
        DataSource ds;
        Context ctx;
        String url = "java:/PostgresDS";
        String query = "DELETE FROM public.checkout WHERE id_checkout = ?";
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
            Logger.getLogger(CheckoutSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

}
