package hotel.ejb;

import hotel.entiry.Tobook;
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
public class TobookSessionBean implements TobookSessionBeanRemote {

    @Override
    public boolean createBook(Tobook b) {
        String user = "postgres";
        String password = "password";
        DataSource ds;
        Context ctx;
        String url = "java:/PostgresDS";
        String query = "INSERT INTO public.tobook(id_customer, id_room, date_checkin, date_checkout) VALUES (?, ?, ?, ?) ";

        int affectedrows = 0;

        try {

            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(url);

            Connection con = ds.getConnection(user, password);
            {
                //tat autocomit de thuc hien transection
                con.setAutoCommit(false);

                PreparedStatement pst = con.prepareStatement(query);

                pst.setInt(1, b.getIdCustomer());
                pst.setInt(2, b.getIdRoom());
                pst.setString(3, b.getDateCheckin());
                pst.setString(4, b.getDateCheckout());

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
            Logger.getLogger(TobookSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public List<Tobook> getTobook() {

        //String url = "jdbc:postgresql://localhost:5433/postgres";
        String user = "postgres";
        String password = "password";

        DataSource ds;
        Context ctx;
        String url = "java:/PostgresDS";
        List<Tobook> tbooks = new ArrayList<Tobook>();

        try {

            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(url);

            Connection con = ds.getConnection(user, password);
            {

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from public.tobook");

                Tobook tb;
                while (rs.next()) {
                    tb = new Tobook();
                    tb.setIdBook(rs.getInt(1));
                    tb.setIdCustomer(rs.getInt(2));
                    tb.setIdRoom(rs.getInt(3));
                    tb.setDateCheckin(rs.getString(4));
                    tb.setDateCheckout(rs.getString(5));
                    tbooks.add(tb);
                }
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            Logger.getLogger(TobookSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tbooks;
    }

    @Override
    public boolean deleteBooked(int id) {
        String user = "postgres";
        String password = "password";
        DataSource ds;
        Context ctx;
        String url = "java:/PostgresDS";
        String query = "DELETE FROM public.tobook WHERE id_book = ?";
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
            Logger.getLogger(TobookSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

}
