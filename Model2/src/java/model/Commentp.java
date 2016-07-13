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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 昊阳
 */
@Entity
@Table(name = "Commentp")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Commentp.findAll", query = "SELECT c FROM Commentp c"),
    @NamedQuery(name = "Commentp.findById", query = "SELECT c FROM Commentp c WHERE c.id = :id"),
    @NamedQuery(name = "Commentp.findByCommments", query = "SELECT c FROM Commentp c WHERE c.commments = :commments")})
public class Commentp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "commments")
    private String commments;
    @JoinColumn(name = "uid", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private User uid;
    @JoinColumn(name = "pid", referencedColumnName = "pid")
    @ManyToOne(optional = false)
    private Play pid;

    public Commentp() {
    }

    public Commentp(Integer id) {
        this.id = id;
    }

    public Commentp(Integer id, String commments) {
        this.id = id;
        this.commments = commments;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommments() {
        return commments;
    }

    public void setCommments(String commments) {
        this.commments = commments;
    }

    public User getUid() {
        return uid;
    }

    public void setUid(User uid) {
        this.uid = uid;
    }

    public Play getPid() {
        return pid;
    }

    public void setPid(Play pid) {
        this.pid = pid;
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
        if (!(object instanceof Commentp)) {
            return false;
        }
        Commentp other = (Commentp) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Commentp[ id=" + id + " ]";
    }
    
}
