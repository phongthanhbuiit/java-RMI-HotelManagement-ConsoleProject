package hotel.ejb;

import hotel.entiry.Room;
import java.sql.Connection;
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
public class RoomSessionBean implements RoomSessionBeanRemote {

    @Override
    public List<Room> getRoom() {

        //String url = "jdbc:postgresql://localhost:5433/postgres";
        String user = "postgres";
        String password = "password";

        DataSource ds;
        Context ctx;
        String url = "java:/PostgresDS";
        List<Room> rooms = new ArrayList<Room>();

        try {

            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(url);

            Connection con = ds.getConnection(user, password);
            {

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from public.room");

                Room room;
                while (rs.next()) {
                    room = new Room();
                    room.setIdRoom(rs.getInt(1));
                    room.setType(rs.getInt(2));
                    room.setPrice(rs.getInt(3));
                    rooms.add(room);
                }
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            Logger.getLogger(RoomSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rooms;
    }

}
