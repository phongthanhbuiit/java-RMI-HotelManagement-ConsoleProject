
package hotel.entiry;

import java.io.Serializable;

/**
 *
 * @author windsora
 */
public class Checkout implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idCheckout;
    private String name;
    private String identityCard;
    private String phone;
    private String address;
    private int idRoom;
    private String dateCheckin;
    private String dateCheckout;

    public Checkout() {
    }

    public Checkout(Integer idCheckout) {
        this.idCheckout = idCheckout;
    }

    public Checkout(Integer idCheckout, String name, String identityCard, String phone, String address, int idRoom, String dateCheckin, String dateCheckout) {
        this.idCheckout = idCheckout;
        this.name = name;
        this.identityCard = identityCard;
        this.phone = phone;
        this.address = address;
        this.idRoom = idRoom;
        this.dateCheckin = dateCheckin;
        this.dateCheckout = dateCheckout;
    }

    public Integer getIdCheckout() {
        return idCheckout;
    }

    public void setIdCheckout(Integer idCheckout) {
        this.idCheckout = idCheckout;
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

    public String getDateCheckin() {
        return dateCheckin;
    }

    public void setDateCheckin(String dateCheckin) {
        this.dateCheckin = dateCheckin;
    }

    public String getDateCheckout() {
        return dateCheckout;
    }

    public void setDateCheckout(String dateCheckout) {
        this.dateCheckout = dateCheckout;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCheckout != null ? idCheckout.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Checkout)) {
            return false;
        }
        Checkout other = (Checkout) object;
        if ((this.idCheckout == null && other.idCheckout != null) || (this.idCheckout != null && !this.idCheckout.equals(other.idCheckout))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.entiry.Checkout[ idCheckout=" + idCheckout + " ]";
    }

    
}
