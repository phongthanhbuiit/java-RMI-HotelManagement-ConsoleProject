/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.entiry;

import java.io.Serializable;
/**
 *
 * @author windsora
 */
public class Tobook implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idBook;
    private int idCustomer;
    private int idRoom;
    private String dateCheckin;
    private String dateCheckout;

    public Tobook() {
    }

    public Tobook(Integer idBook) {
        this.idBook = idBook;
    }

    public Tobook(Integer idBook, int idCustomer, int idRoom, String dateCheckin, String dateCheckout) {
        this.idBook = idBook;
        this.idCustomer = idCustomer;
        this.idRoom = idRoom;
        this.dateCheckin = dateCheckin;
        this.dateCheckout = dateCheckout;
    }

    public Integer getIdBook() {
        return idBook;
    }

    public void setIdBook(Integer idBook) {
        this.idBook = idBook;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
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
        hash += (idBook != null ? idBook.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tobook)) {
            return false;
        }
        Tobook other = (Tobook) object;
        if ((this.idBook == null && other.idBook != null) || (this.idBook != null && !this.idBook.equals(other.idBook))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.entiry.Tobook[ idBook=" + idBook + " ]";
    }
    
}
