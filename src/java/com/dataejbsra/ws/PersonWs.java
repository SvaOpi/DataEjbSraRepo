/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dataejbsra.ws;

import com.dataejbsra.entity.Person;
import com.dataejbsra.facade.PersonFacade;
import com.dataejbsra.vo.ROb;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Camilo
 */
@WebService(serviceName = "PersonWs")
@Stateless()
public class PersonWs {
    @EJB
    private PersonFacade ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

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
    public ROb<Person> registerBirth(@WebParam(name = "name") String name) {
        return ejbRef.registerBirth(name);
    }

    @WebMethod(operationName = "registerDeath")
    public ROb<Person> registerDeath(@WebParam(name = "cedule") Long cedule) {
        return ejbRef.registerDeath(cedule);
    }

    @WebMethod(operationName = "registerData")
    public ROb<Person> registerData(@WebParam(name = "vo") Person vo) {
        return ejbRef.registerData(vo);
    }

    @WebMethod(operationName = "findByUserName")
    public ROb<Person> findByUserName(@WebParam(name = "userName") String userName) {
        return ejbRef.findByUserName(userName);
    }

    @WebMethod(operationName = "findByCedule")
    public ROb<Person> findByCedule(@WebParam(name = "cedule") Long cedule) {
        return ejbRef.findByCedule(cedule);
    }
    
}
