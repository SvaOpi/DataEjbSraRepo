/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dataejbsra.ws;

import com.dataejbsra.entity.Company;
import com.dataejbsra.facade.CompanyFacade;
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
@WebService(serviceName = "CompanyWS")
@Stateless()
public class CompanyWS {
    @EJB
    private CompanyFacade ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "find")
    public ROb find(@WebParam(name = "id") Object id) {
        return ejbRef.find(id);
    }

    @WebMethod(operationName = "findAll")
    public List<Company> findAll() {
        return ejbRef.findAll();
    }

    @WebMethod(operationName = "findRange")
    public List<Company> findRange(@WebParam(name = "range") int[] range) {
        return ejbRef.findRange(range);
    }

    @WebMethod(operationName = "count")
    public int count() {
        return ejbRef.count();
    }

    @WebMethod(operationName = "registerCompany")
    public ROb registerCompany(@WebParam(name = "name") String name, @WebParam(name = "password") String password) {
        return ejbRef.registerCompany(name, password);
    }
    
}
