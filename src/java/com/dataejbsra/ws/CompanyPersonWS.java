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
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Usuario1
 */
@WebService(serviceName = "CompanyPersonWS")
@Stateless()
public class CompanyPersonWS {
    @EJB
    private CompanyPersonFacade ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "create")
    @Oneway
    public void create(@WebParam(name = "entity") CompanyPerson entity) {
        ejbRef.create(entity);
    }

    @WebMethod(operationName = "edit")
    @Oneway
    public void edit(@WebParam(name = "entity") CompanyPerson entity) {
        ejbRef.edit(entity);
    }

    @WebMethod(operationName = "remove")
    @Oneway
    public void remove(@WebParam(name = "entity") CompanyPerson entity) {
        ejbRef.remove(entity);
    }

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
    
}
