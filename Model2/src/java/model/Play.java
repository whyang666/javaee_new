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
@Table(name = "play")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Play.findAll", query = "SELECT p FROM Play p"),
    @NamedQuery(name = "Play.findByPid", query = "SELECT p FROM Play p WHERE p.pid = :pid"),
    @NamedQuery(name = "Play.findByPname", query = "SELECT p FROM Play p WHERE p.pname = :pname"),
    @NamedQuery(name = "Play.findByPaddress", query = "SELECT p FROM Play p WHERE p.paddress = :paddress"),
    @NamedQuery(name = "Play.findByPtel", query = "SELECT p FROM Play p WHERE p.ptel = :ptel"),
    @NamedQuery(name = "Play.findByPpic", query = "SELECT p FROM Play p WHERE p.ppic = :ppic"),
    @NamedQuery(name = "Play.findByPinfo", query = "SELECT p FROM Play p WHERE p.pinfo = :pinfo")})
public class Play implements Serializable {

    @OneToMany(mappedBy = "idp")
    private Collection<CollPlay> collPlayCollection;


    @OneToMany(mappedBy = "pid")
    private Collection<Pt> ptCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pid")
    private Integer pid;
    @Size(max = 255)
    @Column(name = "Pname")
    private String pname;
    @Size(max = 255)
    @Column(name = "Paddress")
    private String paddress;
    @Size(max = 255)
    @Column(name = "Ptel")
    private String ptel;
    @Size(max = 255)
    @Column(name = "Ppic")
    private String ppic;
    @Size(max = 255)
    @Column(name = "Pinfo")
    private String pinfo;
    @OneToMany(mappedBy = "idp")
    private Collection<Bp> bpCollection;

    public Play() {
    }

    public Play(Integer pid) {
        this.pid = pid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPaddress() {
        return paddress;
    }

    public void setPaddress(String paddress) {
        this.paddress = paddress;
    }

    public String getPtel() {
        return ptel;
    }

    public void setPtel(String ptel) {
        this.ptel = ptel;
    }

    public String getPpic() {
        return ppic;
    }

    public void setPpic(String ppic) {
        this.ppic = ppic;
    }

    public String getPinfo() {
        return pinfo;
    }

    public void setPinfo(String pinfo) {
        this.pinfo = pinfo;
    }

    @XmlTransient
    public Collection<Bp> getBpCollection() {
        return bpCollection;
    }

    public void setBpCollection(Collection<Bp> bpCollection) {
        this.bpCollection = bpCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pid != null ? pid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Play)) {
            return false;
        }
        Play other = (Play) object;
        if ((this.pid == null && other.pid != null) || (this.pid != null && !this.pid.equals(other.pid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Play[ pid=" + pid + " ]";
    }




    @XmlTransient
    public Collection<Pt> getPtCollection() {
        return ptCollection;
    }

    public void setPtCollection(Collection<Pt> ptCollection) {
        this.ptCollection = ptCollection;
    }

    @XmlTransient
    public Collection<CollPlay> getCollPlayCollection() {
        return collPlayCollection;
    }

    public void setCollPlayCollection(Collection<CollPlay> collPlayCollection) {
        this.collPlayCollection = collPlayCollection;
    }
    
}
