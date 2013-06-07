/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dataejbsra.ws;

import com.dataejbsra.entity.Person;
import com.dataejbsra.facade.PersonFacade;
import com.dataejbsra.vo.ROb;
import java.util.Date;
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
@WebService(serviceName = "PersonWS")
@Stateless()
public class PersonWS {
    @EJB
    private PersonFacade ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "create")
    @Oneway
    public void create(@WebParam(name = "entity") Person entity) {
        ejbRef.create(entity);
    }

    @WebMethod(operationName = "edit")
    @Oneway
    public void edit(@WebParam(name = "entity") Person entity) {
        ejbRef.edit(entity);
    }

    @WebMethod(operationName = "remove")
    @Oneway
    public void remove(@WebParam(name = "entity") Person entity) {
        ejbRef.remove(entity);
    }

    @WebMethod(operationName = "find")
    public Person find(@WebParam(name = "id") Object id) {
        return ejbRef.find(id);
    }

    @WebMethod(operationName = "findAll")
    public List<Person> findAll() {
        return ejbRef.findAll();
    }

    @WebMethod(operationName = "findRange")
    public List<Person> findRange(@WebParam(name = "range") int[] range) {
        return ejbRef.findRange(range);
    }

    @WebMethod(operationName = "count")
    public int count() {
        return ejbRef.count();
    }

    @WebMethod(operationName = "registerBirth")
    public ROb registerBirth(@WebParam(name = "name") String name) {
        return ejbRef.registerBirth(name);
    }

    @WebMethod(operationName = "registerDeath")
    public ROb registerDeath(@WebParam(name = "cedule") int cedule, @WebParam(name = "death") Date death) {
        return ejbRef.registerDeath(cedule, death);
    }
    
}
