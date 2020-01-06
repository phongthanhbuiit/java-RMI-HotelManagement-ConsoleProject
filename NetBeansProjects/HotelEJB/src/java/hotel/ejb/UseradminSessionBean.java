package hotel.ejb;

import hotel.entiry.Useradmin;
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
public class UseradminSessionBean implements UseradminSessionBeanRemote {

    @Override
    //Ham tra ve list danh sach Useradmin lay duoc tu database
    public List<Useradmin> getUseradmins() {

        //Ket noi databse qua Datasource
        DataSource ds;
        Context ctx;
        String url = "java:/PostgresDS";
        String user = "postgres";
        String password = "password";

        List<Useradmin> useradmins = new ArrayList<Useradmin>();

        try {
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(url);

            //mo mot cong ket noi
            Connection con = ds.getConnection(user, password);
            {

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from public.useradmin");

                Useradmin useradmin;
                while (rs.next()) {
                    useradmin = new Useradmin();
                    useradmin.setIdUser(rs.getInt(1));
                    useradmin.setUsername(rs.getString(2));
                    useradmin.setPassword(rs.getString(3));
                    useradmins.add(useradmin);
                }
            }
            con.close();
        } catch (NamingException ex) {
            Logger.getLogger(UseradminSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UseradminSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return useradmins;
    }

    @Override
    public boolean checkUser(String user, String password) {

        List<Useradmin> useradmins = new ArrayList<Useradmin>();

        useradmins = this.getUseradmins();

        for (Useradmin useradmin : useradmins) {
            if (user.equals(useradmin.getUsername()) && password.equals(useradmin.getPassword())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void changePassword(String newpassword) {
        //Ket noi databse qua Datasource
        DataSource ds;
        Context ctx;
        String url = "java:/PostgresDS";
        String user = "postgres";
        String password = "password";
        String query = "UPDATE public.useradmin SET password = ? WHERE id_user = 1";
        int affectedrows = 0;
        
        try {
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(url);
            
            Connection con = ds.getConnection(user, password);
            {
                //tat autocomit de thuc hien transection
                con.setAutoCommit(false);
                
                //tao doi tuong pst thuc hien query
                PreparedStatement pst = con.prepareStatement(query);
                //them vao doi tuong
                pst.setString(1, newpassword);
                //return int number
                affectedrows = pst.executeUpdate();
                if (affectedrows > 0) {
                    con.commit();
                } else  {
                    con.rollback();
                }
            }
            con.close();
        } catch (NamingException ex) {
            Logger.getLogger(UseradminSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UseradminSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
