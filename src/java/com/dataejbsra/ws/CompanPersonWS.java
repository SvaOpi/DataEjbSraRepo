/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dataejbsra.ws;

import com.dataejbsra.entity.CompanyPerson;
import com.dataejbsra.facade.CompanyPersonFacade;
import com.dataejbsra.vo.ROb;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author kmilo_000
 */
@WebService(serviceName = "CompanPersonWS")
@Stateless()
public class CompanPersonWS {
    @EJB
    private CompanyPersonFacade ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "findAll")
    public List<CompanyPerson> findAll() {
        return ejbRef.findAll();
    }

    @WebMethod(operationName = "findRange")
    public List<CompanyPerson> findRange(@WebParam(name = "range") int[] range) {
        return ejbRef.findRange(range);
    }

    @WebMethod(operationName = "count")
    public int count() {
        return ejbRef.count();
    }

    @WebMethod(operationName = "findByCompaniesPersons")
    public ROb findByCompaniesPersons(@WebParam(name = "personCedule") Long personCedule, @WebParam(name = "companyId") Long companyId) {
        return ejbRef.findByCompaniesPersons(personCedule, companyId);
    }

    @WebMethod(operationName = "validateRelation")
    public ROb validateRelation(@WebParam(name = "personCedule") Long personCedule, @WebParam(name = "companyId") Long companyId, @WebParam(name = "personPassword") String personPassword) {
        return ejbRef.validateRelation(personCedule, companyId, personPassword);
    }

    @WebMethod(operationName = "registerRelation")
    public ROb registerRelation(@WebParam(name = "personCedule") Long personCedule, @WebParam(name = "companyId") Long companyId, @WebParam(name = "rolPerson") String rolPerson, @WebParam(name = "passwordCompany") String passwordCompany) {
        return ejbRef.registerRelation(personCedule, companyId, rolPerson, passwordCompany);
    }

    @WebMethod(operationName = "removeRelation")
    public ROb removeRelation(@WebParam(name = "personCedule") Long personCedule, @WebParam(name = "companyId") Long companyId, @WebParam(name = "passwordCompany") String passwordCompany) {
        return ejbRef.removeRelation(personCedule, companyId, passwordCompany);
    }
    
}
