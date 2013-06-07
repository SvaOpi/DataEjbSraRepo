/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dataejbsra.facade;

import com.dataejbsra.entity.Person;
import com.dataejbsra.vo.ROb;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario1
 */
@Stateless
public class PersonFacade extends AbstractFacade<Person> {
    @PersistenceContext(unitName = "DataEjbSraPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonFacade() {
        super(Person.class);
    }
    
    public ROb registerBirth(String name){
        ROb rob = new ROb();
        try{
            Person person = new Person();
            person.setName(name);
            person.setBirth(new Date());
            create(person);
            List<Person> listPerson =findAll();
            person = listPerson.get(listPerson.size()-1);
            rob.setSuccess(true);
            rob.setData(person);
            return rob;
        }catch(Exception e){
            rob.setSuccess(false);
            rob.setErr_message("Fail!");
            return rob;
        }
    }
    
    public ROb registerDeath(int cedule, Date death){
        ROb rob = new ROb();
        try{
            Person person = (Person) find(cedule).getData();
            person.setDeath(death);
            edit(person);
            rob.setSuccess(true);
            return rob;
        }catch(Exception e){
            rob.setSuccess(false);
            rob.setErr_message("Failed transaction");
            return rob;
        }
    }
    
    public ROb registerFristTime(Person vo){
        ROb rob = new ROb();
        try{
            Person person = (Person) find(vo.getCedule()).getData();
            person.setAddress(vo.getAddress());
            person.setDtype(vo.getDtype());
            person.setMail(vo.getMail());
            person.setName(vo.getName());
            person.setPassword(vo.getPassword());
            person.setUserName(vo.getUserName());
            edit(person);
            rob.setSuccess(true);
            return rob;
        }catch(Exception e){
            rob.setSuccess(false);
            rob.setErr_message("Failed transaction");
            return rob;
        }
    }
    
    public ROb findByUserName(String userName){
        ROb rob = new ROb();
        try{
            Person person = (Person) getEntityManager().createNamedQuery("Person.findByUserName").setParameter("userName", userName);
            rob.setData(person);
            rob.setSuccess(true);
            return rob;
        }catch(Exception e){
            rob.setSuccess(false);
            rob.setErr_message("Failed transaction");
            return rob;
        }
    }

}