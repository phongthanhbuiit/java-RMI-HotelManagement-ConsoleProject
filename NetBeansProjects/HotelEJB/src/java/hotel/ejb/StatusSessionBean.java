package hotel.ejb;

import hotel.entiry.Status;
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
public class StatusSessionBean implements StatusSessionBeanRemote {

    @Override
    public boolean createStatus(Status st) {

        String user = "postgres";
        String password = "password";
        DataSource ds;
        Context ctx;
        String url = "java:/PostgresDS";
        String query = "INSERT INTO public.status(id_room, status, date_checkin, date_checkout) VALUES (?, ?, ?, ?)";

        int affectedrows = 0;

        try {

            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(url);

            Connection con = ds.getConnection(user, password);
            {
                //tat autocomit de thuc hien transection
                con.setAutoCommit(false);

                PreparedStatement pst = con.prepareStatement(query);

                pst.setInt(1, st.getIdRoom());
                pst.setInt(2, st.getStatus());
                pst.setString(3, st.getDateCheckin());
                pst.setString(4, st.getDateCheckout());

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
            Logger.getLogger(StatusSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public List<Status> getStatuss() {

        //String url = "jdbc:postgresql://localhost:5433/postgres";
        String user = "postgres";
        String password = "password";

        DataSource ds;
        Context ctx;
        String url = "java:/PostgresDS";
        List<Status> status_list = new ArrayList<Status>();

        try {

            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(url);

            Connection con = ds.getConnection(user, password);
            {

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from public.status");

                Status status;
                while (rs.next()) {
                    status = new Status();
                    status.setIdStatus(rs.getInt(1));
                    status.setIdRoom(rs.getInt(2));
                    status.setStatus(rs.getInt(3));
                    status.setDateCheckin(rs.getString(4));
                    status.setDateCheckout(rs.getString(5));
                    status_list.add(status);
                }
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            Logger.getLogger(StatusSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status_list;
    }

    @Override
    public List<Status> NotfreeRoom() {

        List<Status> status_list = new ArrayList<Status>();
        List<Status> status_lst = new ArrayList<Status>();

        status_list = getStatuss();
        for (Status s : status_list) {
            if (s.getStatus() == 1) {
                status_lst.add(s);
            }
        }

        return status_lst;
    }

    @Override
    public List<Status> freeRoom() {

        List<Status> status_list = new ArrayList<Status>();
        List<Status> status_lst = new ArrayList<Status>();

        status_list = getStatuss();
        for (Status s : status_list) {
            if (s.getStatus() == 0) {
                status_lst.add(s);
            }
        }

        return status_lst;
    }

    @Override
    public List<Status> freeRoomBook() {

        List<Status> status_list = new ArrayList<Status>();
        List<Status> status_lst = new ArrayList<Status>();

        status_list = getStatuss();

        for (Status s : status_list) {
            if (s.getStatus()== 2) {
                status_lst.add(s);
            }
        }

        return status_lst;
    }

    @Override
    public void updateStatus(Status s) {
        //Ket noi databse qua Datasource
        DataSource ds;
        Context ctx;
        String url = "java:/PostgresDS";
        String user = "postgres";
        String password = "password";
        String query = "UPDATE public.status SET status = ?, date_checkin = ?, date_checkout = ? WHERE id_status = ?";

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
                pst.setInt(1, s.getStatus());
                pst.setString(2, s.getDateCheckin());
                pst.setString(3, s.getDateCheckout());
                pst.setInt(4, s.getIdStatus());
                //return int number
                affectedrows = pst.executeUpdate();
                if (affectedrows > 0) {
                    con.commit();
                } else {
                    con.rollback();
                }
            }
            con.close();
        } catch (NamingException ex) {
            Logger.getLogger(StatusSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(StatusSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public boolean deleteStatus(int id) {
        String user = "postgres";
        String password = "password";
        DataSource ds;
        Context ctx;
        String url = "java:/PostgresDS";
        String query = "DELETE FROM public.status WHERE id_status = ?";
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
            Logger.getLogger(StatusSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
