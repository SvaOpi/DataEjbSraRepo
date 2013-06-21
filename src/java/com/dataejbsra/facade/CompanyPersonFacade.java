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
import javax.ejb.EJB;
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
    @EJB
    private CompanyFacade companyFacade = new CompanyFacade();    
    @EJB
    private PersonFacade personFacade = new PersonFacade();

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompanyPersonFacade() {
        super(CompanyPerson.class);
    }
    
    public ROb<CompanyPerson> findByCompaniesPersons(Long personCedule, Long companyId){
        ROb<CompanyPerson> rob = new ROb<CompanyPerson>();
        try{
            CompanyPersonPK primarykey = new CompanyPersonPK();
            primarykey.setCompaniesId(companyId);
            primarykey.setPersonsCedule(personCedule);
            CompanyPerson companyPerson = find(primarykey);
            if(companyPerson!=null){                
                rob.setSuccess(true);
                rob.setData(companyPerson);
            }else{
                rob.setSuccess(false);
                rob.setErr_message("Cant find that object");
            }
            return rob;
        }catch(Exception e){
            rob.setSuccess(false);
            rob.setErr_message("Fail!");
            return rob;
        }    
    }
    
    public ROb<CompanyPerson> validateRelation (Long personCedule, Long companyId, String personPassword){
        ROb<CompanyPerson> rob = new ROb<CompanyPerson>();
        try{
            rob = findByCompaniesPersons(personCedule,companyId);
            CompanyPerson companyPerson = (CompanyPerson) rob.getData();
            rob.setData(null);
            if(companyPerson!= null && personPassword.equals(companyPerson.getPerson().getPassword())){                
                rob.setSuccess(true);
                rob.setData(companyPerson);
            }else{
                rob.setSuccess(false);
                rob.setErr_message("Cant find that object!");
            }
            return rob;
        }catch(Exception e){
            rob.setSuccess(false);
            rob.setErr_message("Failed transaction!");
            return rob;
        }     
    }
    
    public ROb<CompanyPerson> registerRelation (Long personCedule, Long companyId, String rolPerson, String passwordCompany){
        ROb<CompanyPerson> rob = new ROb<CompanyPerson>();
        try{
            Person person = (Person) personFacade.findByCedule(personCedule).getData();
            Company company = (Company) companyFacade.findById(companyId).getData();
            if(person!=null && company!=null && person.getDeath()==null){
                if(company.getPassword().equals(passwordCompany)){
                    CompanyPerson companyPerson = new CompanyPerson();
                    CompanyPersonPK pk = new CompanyPersonPK();
                    pk.setPersonsCedule(personCedule);
                    pk.setCompaniesId(companyId);
                    companyPerson.setCompanyPersonPK(pk);
                    company.getCompanyPersonCollection().add(companyPerson);
                    companyPerson.setCompany(company);
                    person.getCompanyPersonCollection().add(companyPerson);
                    companyPerson.setPerson(person);
                    companyPerson.setRol(rolPerson);   
                    create(companyPerson);
                    rob.setSuccess(true);
                    //rob.setData(companyPerson);
                    return rob;
                }
            }
            rob.setSuccess(false);
            rob.setErr_message("Cant find that Object!");
            return rob;
        }catch(Exception e){
            e.printStackTrace();
            rob.setSuccess(false);
            rob.setErr_message("Failed transaction!");
            return rob;
        }     
    }
    
    public ROb<CompanyPerson> removeRelation(Long personCedule, Long companyId, String passwordCompany){
        ROb<CompanyPerson> rob = new ROb<CompanyPerson>();
        try{
            rob = findByCompaniesPersons(personCedule,companyId);
            CompanyPerson companyPerson = (CompanyPerson) rob.getData(); 
            rob.setData(null); 
            if(companyPerson != null && companyPerson.getCompany().getPassword().equals(passwordCompany)){
                remove(companyPerson);
                rob.setSuccess(true);
                return rob;
            }           
            rob.setSuccess(false);            
            rob.setErr_message("Cant find that Object!");
            return rob;
        }catch(Exception e){
            rob.setSuccess(false);
            rob.setErr_message("Failed transaction!");
            return rob;
        }
    }    
}
