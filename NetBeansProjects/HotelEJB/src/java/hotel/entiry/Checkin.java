package hotel.entiry;

import java.io.Serializable;

/**
 *
 * @author windsora
 */
public class Checkin implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idCheckin;
    private String name;
    private String identityCard;
    private String phone;
    private String address;
    private int idRoom;

    public Checkin() {
    }

    public Checkin(Integer idCheckin) {
        this.idCheckin = idCheckin;
    }

    public Checkin(Integer idCheckin, String name, String identityCard, String phone, String address, int idRoom) {
        this.idCheckin = idCheckin;
        this.name = name;
        this.identityCard = identityCard;
        this.phone = phone;
        this.address = address;
        this.idRoom = idRoom;
    }

    public Integer getIdCheckin() {
        return idCheckin;
    }

    public void setIdCheckin(Integer idCheckin) {
        this.idCheckin = idCheckin;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCheckin != null ? idCheckin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Checkin)) {
            return false;
        }
        Checkin other = (Checkin) object;
        if ((this.idCheckin == null && other.idCheckin != null) || (this.idCheckin != null && !this.idCheckin.equals(other.idCheckin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.entiry.Checkin[ idCheckin=" + idCheckin + " ]";
    }
    
}
