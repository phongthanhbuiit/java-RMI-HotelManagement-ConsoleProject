
package hotel.entiry;

import java.io.Serializable;
/**
 *
 * @author windsora
 */

public class Customerbooked implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idCusbooked;
    private String name;
    private String identityCard;
    private String phone;
    private String address;
    private int idRoom;
    private String datecheckin;
    private String datecheckout;

    public Customerbooked() {
    }

    public Customerbooked(Integer idCusbooked) {
        this.idCusbooked = idCusbooked;
    }

    public Customerbooked(Integer idCusbooked, String name, String identityCard, String phone, String address, int idRoom, String datecheckin, String datecheckout) {
        this.idCusbooked = idCusbooked;
        this.name = name;
        this.identityCard = identityCard;
        this.phone = phone;
        this.address = address;
        this.idRoom = idRoom;
        this.datecheckin = datecheckin;
        this.datecheckout = datecheckout;
    }

    public Integer getIdCusbooked() {
        return idCusbooked;
    }

    public void setIdCusbooked(Integer idCusbooked) {
        this.idCusbooked = idCusbooked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public String getDatecheckin() {
        return datecheckin;
    }

    public void setDatecheckin(String datecheckin) {
        this.datecheckin = datecheckin;
    }

    public String getDatecheckout() {
        return datecheckout;
    }

    public void setDatecheckout(String datecheckout) {
        this.datecheckout = datecheckout;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCusbooked != null ? idCusbooked.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customerbooked)) {
            return false;
        }
        Customerbooked other = (Customerbooked) object;
        if ((this.idCusbooked == null && other.idCusbooked != null) || (this.idCusbooked != null && !this.idCusbooked.equals(other.idCusbooked))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.entiry.Customerbooked[ idCusbooked=" + idCusbooked + " ]";
    }
    
}
