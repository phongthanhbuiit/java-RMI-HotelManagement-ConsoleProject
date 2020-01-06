package hotel.ejb;

import hotel.entiry.Account;
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
public class AccountSessionBean implements AccountSessionBeanRemote {

    @Override
    //Ham tra ve list danh sach Useradmin lay duoc tu database
    public List<Account> getAccounts() {

        //Ket noi databse qua Datasource
        DataSource ds;
        Context ctx;
        String url = "java:/PostgresDS";
        String user = "postgres";
        String password = "password";

        List<Account> accounts = new ArrayList<Account>();

        try {
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(url);

            Connection con = ds.getConnection(user, password);
            {

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from public.account");

                Account account;
                while (rs.next()) {
                    account = new Account();
                    account.setIdAccout(rs.getInt(1));
                    account.setUsername(rs.getString(2));
                    account.setPassword(rs.getString(3));
                    accounts.add(account);
                }
            }
            con.close();
        } catch (NamingException ex) {
            Logger.getLogger(AccountSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AccountSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return accounts;
    }

    @Override
    public boolean userExist(String user) {

        List<Account> accounts = new ArrayList<Account>();

        accounts = this.getAccounts();

        for (Account account : accounts) {
            if (user.equals(account.getUsername())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean userExist(int id) {

        List<Account> accounts = new ArrayList<Account>();

        accounts = this.getAccounts();

        for (Account account : accounts) {
            if (id == account.getIdAccout()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkUser(String user, String password) {

        List<Account> accounts = new ArrayList<Account>();

        accounts = this.getAccounts();

        for (Account account : accounts) {
            if (user.equals(account.getUsername()) && password.equals(account.getPassword())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean createAccount(Account acc) {

        String user = "postgres";
        String password = "password";
        DataSource ds;
        Context ctx;
        String url = "java:/PostgresDS";
        String query = "INSERT INTO public.account(username, password) VALUES (?, ?)";

        int affectedrows = 0;

        try {

            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(url);

            Connection con = ds.getConnection(user, password);
            {
                //tat autocomit de thuc hien transection
                con.setAutoCommit(false);

                PreparedStatement pst = con.prepareStatement(query);

                pst.setString(1, acc.getUsername());
                pst.setString(2, acc.getPassword());

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
            Logger.getLogger(AccountSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteAccout(int id) {
        String user = "postgres";
        String password = "password";
        DataSource ds;
        Context ctx;
        String url = "java:/PostgresDS";
        String query = "DELETE FROM public.account WHERE id_accout = ?";
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
            Logger.getLogger(AccountSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public void changePassword(String newpassword, String username) {
        //Ket noi databse qua Datasource
        DataSource ds;
        Context ctx;
        String url = "java:/PostgresDS";
        String user = "postgres";
        String password = "password";
        String query = "UPDATE public.account SET password = ? WHERE username = ?";
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
                pst.setString(2, username);
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
            Logger.getLogger(AccountSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AccountSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
