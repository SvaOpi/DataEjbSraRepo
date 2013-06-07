/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dataejbsra.ws;

import com.dataejbsra.entity.Company;
import com.dataejbsra.entity.CompanyPerson;
import com.dataejbsra.entity.Person;
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
@WebService(serviceName = "CompanyPersonWS")
@Stateless()
public class CompanyPersonWS {
    @EJB
    private CompanyPersonFacade ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "find")
    public CompanyPerson find(@WebParam(name = "id") Object id) {
        return ejbRef.find(id);
    }

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
    public ROb findByCompaniesPersons(@WebParam(name = "vo") CompanyPerson vo) {
        return ejbRef.findByCompaniesPersons(vo);
    }

    @WebMethod(operationName = "validatePerson")
    public ROb validatePerson(@WebParam(name = "vo") CompanyPerson vo) {
        return ejbRef.validatePerson(vo);
    }

    @WebMethod(operationName = "registerRelation")
    public ROb registerRelation(@WebParam(name = "personVo") Person personVo, @WebParam(name = "companyVo") Company companyVo, @WebParam(name = "rolPerson") String rolPerson, @WebParam(name = "passwordCompany") String passwordCompany, @WebParam(name = "passwordPerson") String passwordPerson) {
        return ejbRef.registerRelation(personVo, companyVo, rolPerson, passwordCompany, passwordPerson);
    }

    @WebMethod(operationName = "removeRelation")
    public ROb removeRelation(@WebParam(name = "vo") CompanyPerson vo, @WebParam(name = "passwordCompany") String passwordCompany) {
        return ejbRef.removeRelation(vo, passwordCompany);
    }
    
}
