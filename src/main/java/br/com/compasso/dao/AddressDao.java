package br.com.compasso.dao;


import br.com.compasso.factory.EManagerFactory;
import br.com.compasso.models.Address;

import javax.persistence.EntityManager;
import java.util.List;

public class AddressDao
{
    private EntityManager em;

    public AddressDao(){
        this.em = EManagerFactory.getEm();
    }

    public void save(Address address){
        em.getTransaction().begin();
        this.em.persist(address);
        em.getTransaction().commit();
    }

    public List<Address> readAll(){
        String jpql = "SELECT a FROM Address a";
        return em.createQuery(jpql, Address.class).getResultList();
    }
}
