/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 昊阳
 */
@Entity
@Table(name = "ptag")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ptag.findAll", query = "SELECT p FROM Ptag p"),
    @NamedQuery(name = "Ptag.findByTpid", query = "SELECT p FROM Ptag p WHERE p.tpid = :tpid"),
    @NamedQuery(name = "Ptag.findByTpname", query = "SELECT p FROM Ptag p WHERE p.tpname = :tpname")})
public class Ptag implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tpid")
    private Integer tpid;
    @Size(max = 255)
    @Column(name = "tpname")
    private String tpname;
    @OneToMany(mappedBy = "tpname")
    private Collection<Pt> ptCollection;

    public Ptag() {
    }

    public Ptag(Integer tpid) {
        this.tpid = tpid;
    }

    public Integer getTpid() {
        return tpid;
    }

    public void setTpid(Integer tpid) {
        this.tpid = tpid;
    }

    public String getTpname() {
        return tpname;
    }

    public void setTpname(String tpname) {
        this.tpname = tpname;
    }

    @XmlTransient
    public Collection<Pt> getPtCollection() {
        return ptCollection;
    }

    public void setPtCollection(Collection<Pt> ptCollection) {
        this.ptCollection = ptCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tpid != null ? tpid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ptag)) {
            return false;
        }
        Ptag other = (Ptag) object;
        if ((this.tpid == null && other.tpid != null) || (this.tpid != null && !this.tpid.equals(other.tpid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Ptag[ tpid=" + tpid + " ]";
    }
    
}
