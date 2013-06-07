/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dataejbsra.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario1
 */
@Entity
@Table(name = "COMPANY_PERSON")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompanyPerson.findAll", query = "SELECT c FROM CompanyPerson c"),
    @NamedQuery(name = "CompanyPerson.findByCompaniesId", query = "SELECT c FROM CompanyPerson c WHERE c.companyPersonPK.companiesId = :companiesId"),
    @NamedQuery(name = "CompanyPerson.findByPersonsCedule", query = "SELECT c FROM CompanyPerson c WHERE c.companyPersonPK.personsCedule = :personsCedule"),
    @NamedQuery(name = "CompanyPerson.findByRol", query = "SELECT c FROM CompanyPerson c WHERE c.rol = :rol")})
public class CompanyPerson implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CompanyPersonPK companyPersonPK;
    @Size(max = 50)
    @Column(name = "ROL")
    private String rol;
    @JoinColumn(name = "PERSONS_CEDULE", referencedColumnName = "CEDULE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Person person;
    @JoinColumn(name = "COMPANIES_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Company company;

    public CompanyPerson() {
    }

    public CompanyPerson(CompanyPersonPK companyPersonPK) {
        this.companyPersonPK = companyPersonPK;
    }

    public CompanyPerson(long companiesId, long personsCedule) {
        this.companyPersonPK = new CompanyPersonPK(companiesId, personsCedule);
    }

    public CompanyPersonPK getCompanyPersonPK() {
        return companyPersonPK;
    }

    public void setCompanyPersonPK(CompanyPersonPK companyPersonPK) {
        this.companyPersonPK = companyPersonPK;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companyPersonPK != null ? companyPersonPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompanyPerson)) {
            return false;
        }
        CompanyPerson other = (CompanyPerson) object;
        if ((this.companyPersonPK == null && other.companyPersonPK != null) || (this.companyPersonPK != null && !this.companyPersonPK.equals(other.companyPersonPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dataejbsra.entity.CompanyPerson[ companyPersonPK=" + companyPersonPK + " ]";
    }
    
}
