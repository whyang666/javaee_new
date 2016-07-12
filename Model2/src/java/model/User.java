/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "User")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByUname", query = "SELECT u FROM User u WHERE u.uname = :uname"),
    @NamedQuery(name = "User.findByUpassword", query = "SELECT u FROM User u WHERE u.upassword = :upassword")})
public class User implements Serializable {

    @OneToMany(mappedBy = "idu")
    private Collection<CollPlay> collPlayCollection;



    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 255)
    @Column(name = "Uname")
    private String uname;
    @Size(max = 255)
    @Column(name = "Upassword")
    private String upassword;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idu")
    private Collection<Coll> collCollection;
    @OneToMany(mappedBy = "idu")
    private Collection<Bp> bpCollection;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    @XmlTransient
    public Collection<Coll> getCollCollection() {
        return collCollection;
    }

    public void setCollCollection(Collection<Coll> collCollection) {
        this.collCollection = collCollection;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.User[ id=" + id + " ]";
    }



    @XmlTransient
    public Collection<CollPlay> getCollPlayCollection() {
        return collPlayCollection;
    }

    public void setCollPlayCollection(Collection<CollPlay> collPlayCollection) {
        this.collPlayCollection = collPlayCollection;
    }
    
}
