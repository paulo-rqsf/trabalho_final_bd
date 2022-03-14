package br.com.compasso.dao;


import br.com.compasso.factory.EManagerFactory;
import br.com.compasso.models.Product;

import javax.persistence.EntityManager;
import java.util.List;

public class ProductDao
{
    private EntityManager em;

    public ProductDao(){
        this.em = EManagerFactory.getEm();
    }

    public List<Product> readAll(){
        String jpql = "SELECT s FROM Product s";
        return em.createQuery(jpql, Product.class).getResultList();
    }
    public Product readId(Long id){
        try{
            String jpql = "SELECT p FROM Product p WHERE p.productId = :id";
            return em.createQuery(jpql, Product.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }catch(Exception e){
            return null;
        }
    }
}
