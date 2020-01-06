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
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idReport;
    private String dateCreate;
    private int amountSalary;
    private int amountBill;

    public Report() {
    }

    public Report(Integer idReport) {
        this.idReport = idReport;
    }

    public Report(Integer idReport, String dateCreate, int amountSalary, int amountBill) {
        this.idReport = idReport;
        this.dateCreate = dateCreate;
        this.amountSalary = amountSalary;
        this.amountBill = amountBill;
    }

    public Integer getIdReport() {
        return idReport;
    }

    public void setIdReport(Integer idReport) {
        this.idReport = idReport;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public int getAmountSalary() {
        return amountSalary;
    }

    public void setAmountSalary(int amountSalary) {
        this.amountSalary = amountSalary;
    }

    public int getAmountBill() {
        return amountBill;
    }

    public void setAmountBill(int amountBill) {
        this.amountBill = amountBill;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReport != null ? idReport.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Report)) {
            return false;
        }
        Report other = (Report) object;
        if ((this.idReport == null && other.idReport != null) || (this.idReport != null && !this.idReport.equals(other.idReport))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hotel.entiry.Report[ idReport=" + idReport + " ]";
    }
    
}
