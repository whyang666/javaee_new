/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 昊阳
 */
@Entity
@Table(name = "pt")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pt.findAll", query = "SELECT p FROM Pt p"),
    @NamedQuery(name = "Pt.findByPtid", query = "SELECT p FROM Pt p WHERE p.ptid = :ptid")})
public class Pt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ptid")
    private Integer ptid;
    @JoinColumn(name = "pid", referencedColumnName = "pid")
    @ManyToOne
    private Play pid;
    @JoinColumn(name = "tpname", referencedColumnName = "tpname")
    @ManyToOne
    private Ptag tpname;

    public Pt() {
    }

    public Pt(Integer ptid) {
        this.ptid = ptid;
    }

    public Integer getPtid() {
        return ptid;
    }

    public void setPtid(Integer ptid) {
        this.ptid = ptid;
    }

    public Play getPid() {
        return pid;
    }

    public void setPid(Play pid) {
        this.pid = pid;
    }

    public Ptag getTpname() {
        return tpname;
    }

    public void setTpname(Ptag tpname) {
        this.tpname = tpname;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ptid != null ? ptid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pt)) {
            return false;
        }
        Pt other = (Pt) object;
        if ((this.ptid == null && other.ptid != null) || (this.ptid != null && !this.ptid.equals(other.ptid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Pt[ ptid=" + ptid + " ]";
    }
    
}
