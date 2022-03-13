package br.com.compasso.dao;


import br.com.compasso.factory.EManagerFactory;
import br.com.compasso.models.Order;

import javax.persistence.EntityManager;
import java.util.List;

public class OrderDao
{
    private EntityManager em;

    public OrderDao(){
        this.em = EManagerFactory.getEm();
    }

    public void save(Order order){
        em.getTransaction().begin();
        this.em.persist(order);
        em.getTransaction().commit();
    }

    public List<Order> readAll(){
        String jpql = "SELECT s FROM Order s";
        return em.createQuery(jpql, Order.class).getResultList();
    }
}
