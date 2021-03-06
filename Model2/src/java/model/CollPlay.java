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
@Table(name = "Coll_Play")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CollPlay.findAll", query = "SELECT c FROM CollPlay c"),
    @NamedQuery(name = "CollPlay.findById", query = "SELECT c FROM CollPlay c WHERE c.id = :id")})
public class CollPlay implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "idp", referencedColumnName = "pid")
    @ManyToOne
    private Play idp;
    @JoinColumn(name = "idu", referencedColumnName = "ID")
    @ManyToOne
    private User idu;

    public CollPlay() {
    }

    public CollPlay(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Play getIdp() {
        return idp;
    }

    public void setIdp(Play idp) {
        this.idp = idp;
    }

    public User getIdu() {
        return idu;
    }

    public void setIdu(User idu) {
        this.idu = idu;
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
        if (!(object instanceof CollPlay)) {
            return false;
        }
        CollPlay other = (CollPlay) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CollPlay[ id=" + id + " ]";
    }
    
}
