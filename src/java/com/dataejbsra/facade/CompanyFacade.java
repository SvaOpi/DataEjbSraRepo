/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dataejbsra.facade;

import com.dataejbsra.entity.Company;
import com.dataejbsra.vo.ROb;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario1
 */
@Stateless
public class CompanyFacade extends AbstractFacade<Company> {
    @PersistenceContext(unitName = "DataEjbSraPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompanyFacade() {
        super(Company.class);
    }
    
    public ROb<Company> registerCompany(String name, String password){
        ROb<Company> rob = new ROb<Company>();
        try{
            Company company = new Company();
            company.setName(name);
            company.setPassword(password);
            create(company);
            List<Company> listCompany =findAll();
            company = listCompany.get(listCompany.size()-1);
            rob.setSuccess(true);
            rob.setData(company);
            return rob;
        }catch(Exception e){
            rob.setSuccess(false);
            rob.setErr_message("Failed transaction!");
            return rob;
        }
    }
    
    public ROb<Company> findById(Long id){
        ROb<Company> rob = new ROb<Company>();
        try{
            Company company = find(id);
            if(company==null){
                rob.setErr_message("Cant Find this Object");
                rob.setSuccess(false);
            } else {
                rob.setData(company);
                rob.setSuccess(true);
            }
            return rob;
        }catch(Exception e){
            rob.setSuccess(false);
            rob.setErr_message("Failed transaction");
            return rob;
        }
    }
    
    public ROb<Company> removeById(Long id){
        ROb<Company> rob = new ROb<Company>();
        try{
            rob = findById(id);
            if(rob.isSuccess()==true){
                Company company = (Company) rob.getData();
                remove(company);
                rob.setSuccess(true);
                rob.setData(null);
            }            
            return rob;
        }catch(Exception e){
            rob.setSuccess(false);
            rob.setErr_message("Failed transaction");
            return rob;
        }
    }
}
