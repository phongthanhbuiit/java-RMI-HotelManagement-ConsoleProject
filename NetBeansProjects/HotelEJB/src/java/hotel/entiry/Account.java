package hotel.entiry;

import java.io.Serializable;

/**
 *
 * @author windsora
 */
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idAccout;
    private String username;
    private String password;

    public Account() {
    }

    public Account(Integer idAccout) {
        this.idAccout = idAccout;
    }

    public Account(Integer idAccout, String username, String password) {
        this.idAccout = idAccout;
        this.username = username;
        this.password = password;
    }

    public Integer getIdAccout() {
        return idAccout;
    }

    public void setIdAccout(Integer idAccout) {
        this.idAccout = idAccout;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAccout != null ? idAccout.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.idAccout == null && other.idAccout != null) || (this.idAccout != null && !this.idAccout.equals(other.idAccout))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.entiry.Account[ idAccout=" + idAccout + " ]";
    }
    
}
