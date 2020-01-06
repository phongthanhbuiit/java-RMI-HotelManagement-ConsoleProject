package hotel.ejb;

import hotel.entiry.Checkin;
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
public class CheckinSessionBean implements CheckinSessionBeanRemote {
    
    @Override
    public boolean createCheckin(Checkin c) {
        
        String user = "postgres";
        String password = "password";        
        DataSource ds;
        Context ctx;
        String url = "java:/PostgresDS";
        String query = "INSERT INTO public.checkin(name, identity_card, phone, address, id_room) VALUES (?, ?, ?, ?, ?)";
        
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
            Logger.getLogger(CheckinSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    @Override
    public List<Checkin> getChekinlist() {

        //String url = "jdbc:postgresql://localhost:5433/postgres";
        String user = "postgres";
        String password = "password";
        
        DataSource ds;
        Context ctx;
        String url = "java:/PostgresDS";
        List<Checkin> ci_lst = new ArrayList<Checkin>();

        try {

            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(url);


            Connection con = ds.getConnection(user, password);
            {   

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from public.checkin");

                Checkin checkin;
                while (rs.next()) {
                    checkin = new Checkin();
                    checkin.setIdCheckin(rs.getInt(1));
                    checkin.setName(rs.getString(2));
                    checkin.setIdentityCard(rs.getString(3));
                    checkin.setPhone(rs.getString(4));
                    checkin.setAddress(rs.getString(5));
                    checkin.setIdRoom(rs.getInt(6));
                    ci_lst.add(checkin);
                }
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            Logger.getLogger(CheckinSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ci_lst;
    }
    
    @Override
    public boolean deleteCheckin(int id) {
        String user = "postgres";
        String password = "password";
        DataSource ds;
        Context ctx;
        String url = "java:/PostgresDS";
        String query = "DELETE FROM public.checkin WHERE id_room = ?";
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
            Logger.getLogger(CheckinSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

}
