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
    
    public ROb findByCompaniesPersons(Long personCedule, Long companyId){
        ROb rob = new ROb();
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
                rob.setErr_message("Fail!");
            }
            return rob;
        }catch(Exception e){
            rob.setSuccess(false);
            rob.setErr_message("Fail!");
            return rob;
        }    
    }
    
    public ROb validateRelation (Long personCedule, Long companyId, String personPassword){
        ROb rob = new ROb();
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
    
    public ROb registerRelation (Long personCedule, Long companyId, String rolPerson, String passwordCompany){
        ROb rob = new ROb();
        try{
            Person person = (Person) new PersonFacade().findByCedule(personCedule).getData();
            Company company = (Company) new CompanyFacade().findById(companyId).getData();
            System.out.println(person +" \n" + company);
            if(person!=null && company!=null && person.getDeath()==null){
                System.out.println("bugs bunny");
                if(company.getPassword().equals(passwordCompany)){
                    System.out.println("chan chan chaN!");
                    CompanyPerson companyPerson = new CompanyPerson();
                    companyPerson.setCompany(company);
                    companyPerson.setPerson(person);
                    companyPerson.setRol(rolPerson);
                    create(companyPerson);
                    System.out.println("Created!");
                    rob.setSuccess(true);
                    rob.setData(companyPerson);
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
    
    public ROb removeRelation(Long personCedule, Long companyId, String passwordCompany){
        ROb rob = new ROb();
        try{
            rob = findByCompaniesPersons(personCedule,companyId);
            CompanyPerson companyPerson = (CompanyPerson) rob.getData();  
            if(companyPerson != null && companyPerson.getCompany().getPassword().equals(passwordCompany)){
                remove(companyPerson);
                rob.setSuccess(true);
                rob.setData(companyPerson);
                return rob;
            }           
            rob.setData(null);
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
