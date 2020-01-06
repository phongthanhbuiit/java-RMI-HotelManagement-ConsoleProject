package hotel.entiry;

import java.io.Serializable;
/**
 *
 * @author windsora
 */

public class Bill implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idBill;
    private int idCheckout;
    private int amounts;

    public Bill() {
    }

    public Bill(Integer idBill) {
        this.idBill = idBill;
    }

    public Bill(Integer idBill, int idCheckout, int amounts) {
        this.idBill = idBill;
        this.idCheckout = idCheckout;
        this.amounts = amounts;
    }

    public Integer getIdBill() {
        return idBill;
    }

    public void setIdBill(Integer idBill) {
        this.idBill = idBill;
    }

    public int getIdCheckout() {
        return idCheckout;
    }

    public void setIdCheckout(int idCheckout) {
        this.idCheckout = idCheckout;
    }

    public int getAmounts() {
        return amounts;
    }

    public void setAmounts(int amounts) {
        this.amounts = amounts;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBill != null ? idBill.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bill)) {
            return false;
        }
        Bill other = (Bill) object;
        if ((this.idBill == null && other.idBill != null) || (this.idBill != null && !this.idBill.equals(other.idBill))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.entiry.Bill[ idBill=" + idBill + " ]";
    }
    
}
