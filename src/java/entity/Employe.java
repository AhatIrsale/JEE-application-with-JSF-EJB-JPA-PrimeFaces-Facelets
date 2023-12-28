/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author asus
 */
@Entity
@Table(name = "employe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employe.findAll", query = "SELECT e FROM Employe e"),
    @NamedQuery(name = "Employe.findById", query = "SELECT e FROM Employe e WHERE e.id = :id"),
    @NamedQuery(name = "Employe.findByNom", query = "SELECT e FROM Employe e WHERE e.nom = :nom"),
    @NamedQuery(name = "Employe.findByPrenom", query = "SELECT e FROM Employe e WHERE e.prenom = :prenom"),
    @NamedQuery(name = "Employe.findBySalaire", query = "SELECT e FROM Employe e WHERE e.salaire = :salaire")})
public class Employe implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "prenom")
    private String prenom;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "salaire")
    private BigDecimal salaire;

    public Employe() {
    }

    public Employe(Integer id) {
        this.id = id;
    }

    public Employe(Integer id, String nom, String prenom, BigDecimal salaire) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.salaire = salaire;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public BigDecimal getSalaire() {
        return salaire;
    }

    public void setSalaire(BigDecimal salaire) {
        this.salaire = salaire;
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
        if (!(object instanceof Employe)) {
            return false;
        }
        Employe other = (Employe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nom;
    }
    
}
