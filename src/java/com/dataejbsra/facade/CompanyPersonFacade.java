/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dataejbsra.facade;

import com.dataejbsra.entity.CompanyPerson;
import com.dataejbsra.entity.CompanyPersonPK;
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
            CompanyPerson companyPerson = find(primarykey);
            if(companyPerson==null){                
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
            CompanyPerson companyPersonVo = (CompanyPerson) rob.getData();
            if(companyPersonVo!= null && vo.getPerson().getPassword().equals(companyPersonVo.getPerson().getPassword())){                
                rob.setSuccess(true);
                rob.setData(companyPersonVo);
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
    
}
