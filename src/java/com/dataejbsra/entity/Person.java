/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dataejbsra.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Usuario1
 */
@Entity
@Table(name = "PERSON")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
    @NamedQuery(name = "Person.findByCedule", query = "SELECT p FROM Person p WHERE p.cedule = :cedule"),
    @NamedQuery(name = "Person.findByDtype", query = "SELECT p FROM Person p WHERE p.dtype = :dtype"),
    @NamedQuery(name = "Person.findByAddress", query = "SELECT p FROM Person p WHERE p.address = :address"),
    @NamedQuery(name = "Person.findByBirth", query = "SELECT p FROM Person p WHERE p.birth = :birth"),
    @NamedQuery(name = "Person.findByDeath", query = "SELECT p FROM Person p WHERE p.death = :death"),
    @NamedQuery(name = "Person.findByMail", query = "SELECT p FROM Person p WHERE p.mail = :mail"),
    @NamedQuery(name = "Person.findByName", query = "SELECT p FROM Person p WHERE p.name = :name"),
    @NamedQuery(name = "Person.findByPassword", query = "SELECT p FROM Person p WHERE p.password = :password")})
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CEDULE")
    private Long cedule;
    @Size(max = 31)
    @Column(name = "DTYPE")
    private String dtype;
    @Size(max = 255)
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "BIRTH")
    @Temporal(TemporalType.DATE)
    private Date birth;
    @Column(name = "DEATH")
    @Temporal(TemporalType.DATE)
    private Date death;
    @Size(max = 255)
    @Column(name = "MAIL")
    private String mail;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Size(max = 255)
    @Column(name = "USERNAME",unique = true)
    private String userName;
    @Size(max = 255)
    @Column(name = "PASSWORD")
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private Collection<CompanyPerson> companyPersonCollection;

    public Person() {
    }

    public Person(Long cedule) {
        this.cedule = cedule;
    }

    public Long getCedule() {
        return cedule;
    }

    public void setCedule(Long cedule) {
        this.cedule = cedule;
    }

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Date getDeath() {
        return death;
    }

    public void setDeath(Date death) {
        this.death = death;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public Collection<CompanyPerson> getCompanyPersonCollection() {
        return companyPersonCollection;
    }

    public void setCompanyPersonCollection(Collection<CompanyPerson> companyPersonCollection) {
        this.companyPersonCollection = companyPersonCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cedule != null ? cedule.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.cedule == null && other.cedule != null) || (this.cedule != null && !this.cedule.equals(other.cedule))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dataejbsra.entity.Person[ cedule=" + cedule + " ]";
    }
    
}
