package br.com.compasso.dao;


import br.com.compasso.factory.EManagerFactory;
import br.com.compasso.models.States;

import javax.persistence.EntityManager;
import java.util.List;

public class StatesDao
{
    private EntityManager em;

    public StatesDao(){
        this.em = EManagerFactory.getEm();
    }

    public List<States> readAll(){
        String jpql = "SELECT s FROM States s";
        return em.createQuery(jpql, States.class).getResultList();
    }
    public States readName(String cod){
        String jpql = "SELECT s FROM States s WHERE s.cod = :cod";
        return em.createQuery(jpql, States.class)
                .setParameter("cod", cod)
                .getSingleResult();
    }

}
