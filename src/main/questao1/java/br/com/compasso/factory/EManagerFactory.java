package br.com.compasso.factory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EManagerFactory
{
    private static final EntityManager em = Persistence.createEntityManagerFactory("prova_3").createEntityManager();

    public static EntityManager getEm(){
        return em;
    }
}
