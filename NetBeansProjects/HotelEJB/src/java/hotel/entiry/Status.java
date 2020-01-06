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

public class Status implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idStatus;
    private int idRoom;
    private int status;
    private String dateCheckin;
    private String dateCheckout;

    public Status() {
    }

    public Status(Integer idStatus) {
        this.idStatus = idStatus;
    }

    public Status(Integer idStatus, int idRoom, int status, String dateCheckin, String dateCheckout) {
        this.idStatus = idStatus;
        this.idRoom = idRoom;
        this.status = status;
        this.dateCheckin = dateCheckin;
        this.dateCheckout = dateCheckout;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
        hash += (idStatus != null ? idStatus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Status)) {
            return false;
        }
        Status other = (Status) object;
        if ((this.idStatus == null && other.idStatus != null) || (this.idStatus != null && !this.idStatus.equals(other.idStatus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.entiry.Status[ idStatus=" + idStatus + " ]";
    }
    
}
