/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dataejbsra.facade;

import com.dataejbsra.entity.Company;
import com.dataejbsra.entity.CompanyPerson;
import com.dataejbsra.entity.CompanyPersonPK;
import com.dataejbsra.entity.Person;
import com.dataejbsra.vo.ROb;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario1
 */
@Stateless
public class CompanyPersonFacade extends AbstractFacade<CompanyPerson> {
    @PersistenceContext(unitName = "DataEjbSraPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompanyPersonFacade() {
        super(CompanyPerson.class);
    }
    
    public ROb findByCompaniesPersons(CompanyPerson vo){
        ROb rob = new ROb();
        try{
            CompanyPersonPK primarykey = new CompanyPersonPK();
            primarykey.setCompaniesId(vo.getCompanyPersonPK().getCompaniesId());
            primarykey.setPersonsCedule(vo.getCompanyPersonPK().getPersonsCedule());
            CompanyPerson companyPerson = (CompanyPerson) find(primarykey).getData();
            if(companyPerson!=null){                
                rob.setSuccess(true);
                rob.setData(companyPerson);
            }else{
                rob.setSuccess(false);
                rob.setErr_message("Fail!");
            }
            return rob;
        }catch(Exception e){
            rob.setSuccess(false);
            rob.setErr_message("Fail!");
            return rob;
        }    
    }
    
    public ROb validatePerson (CompanyPerson vo){
        ROb rob = new ROb();
        try{
            rob = findByCompaniesPersons(vo);
            CompanyPerson companyPerson = (CompanyPerson) rob.getData();
            if(companyPerson!= null && vo.getPerson().getPassword().equals(companyPerson.getPerson().getPassword())){                
                rob.setSuccess(true);
                rob.setData(companyPerson);
                return rob;
            }else{
                rob.setSuccess(false);
                rob.setErr_message("Fail!");
                return rob;
            }
        }catch(Exception e){
            rob.setSuccess(false);
            rob.setErr_message("Fail!");
            return rob;
        }     
    }
    
    public ROb registerRelation (Person personVo, Company companyVo, String rolPerson, String passwordCompany, String passwordPerson){
        ROb rob = new ROb();
        try{
            Person person = (Person) new PersonFacade().find(personVo).getData();
            Company company = (Company) new CompanyFacade().find(companyVo).getData();
            if(person!=null && company!=null){
                if(person.getPassword().equals(passwordPerson) && company.getPassword().equals(passwordCompany)){
                    CompanyPerson companyPerson = new CompanyPerson();
                    companyPerson.setCompany(companyVo);
                    companyPerson.setPerson(personVo);
                    companyPerson.setRol(rolPerson);
                    rob.setSuccess(true);
                    rob.setData(companyPerson);
                }
            }
            rob.setSuccess(true);
            rob.setErr_message("Fail!");
            return rob;
        }catch(Exception e){
            rob.setSuccess(false);
            rob.setErr_message("Fail!");
            return rob;
        }     
    }
}
