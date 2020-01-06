package hotel.entiry;

import java.io.Serializable;

/**
 *
 * @author windsora
 */
public class Useradmin implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idUser;
    private String username;
    private String password;

    public Useradmin() {
    }

    public Useradmin(Integer idUser) {
        this.idUser = idUser;
    }

    public Useradmin(Integer idUser, String username, String password) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
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
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Useradmin)) {
            return false;
        }
        Useradmin other = (Useradmin) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.entiry.Useradmin[ idUser=" + idUser + " ]";
    }
    
}
