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
    
    public ROb registerCompany(String name, String password){
        ROb rob = new ROb();
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
    
}
