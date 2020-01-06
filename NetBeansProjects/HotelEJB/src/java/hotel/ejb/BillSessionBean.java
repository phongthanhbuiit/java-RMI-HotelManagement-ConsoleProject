package hotel.ejb;

import hotel.entiry.Bill;
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
public class BillSessionBean implements BillSessionBeanRemote {
    
    @Override
    public boolean createBill(Bill b) {
        
        String user = "postgres";
        String password = "password";        
        DataSource ds;
        Context ctx;
        String url = "java:/PostgresDS";
        String query = "INSERT INTO public.bill(id_checkout, amounts) VALUES (?, ?) ";
        
        int affectedrows = 0;

        try {

            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(url);

            Connection con = ds.getConnection(user, password);
            {
                //tat autocomit de thuc hien transection
                con.setAutoCommit(false);

                PreparedStatement pst = con.prepareStatement(query);

                pst.setInt(1, b.getIdBill());
                pst.setInt(1, b.getAmounts());

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
            Logger.getLogger(BillSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    @Override
    public List<Bill> getBills() {

        //String url = "jdbc:postgresql://localhost:5433/postgres";
        String user = "postgres";
        String password = "password";
        
        DataSource ds;
        Context ctx;
        String url = "java:/PostgresDS";
        List<Bill> bills = new ArrayList<Bill>();

        try {

            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(url);


            Connection con = ds.getConnection(user, password);
            {   

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from public.bill");

                Bill b;
                while (rs.next()) {
                    b = new Bill();
                    b.setIdBill(rs.getInt(1));
                    b.setIdCheckout(rs.getInt(2));
                    b.setAmounts(rs.getInt(3));
                    bills.add(b);
                }
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            Logger.getLogger(BillSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bills;
    }

}
