/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Bruger
 */
public class MakeTestData {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        String[] actors1 = {"Torben", "Chris"};
        String[] actors2 = {"Ib", "Bo"};
        Movie movie1 = new Movie(2004, "T&C", actors1);
        Movie movie2 = new Movie(1999, "I&B", actors2);
        
        try {
            em.getTransaction().begin();
            em.persist(movie1);
            em.persist(movie2);
            em.getTransaction().commit();
            //Verify that BankCustomers are managed and has been given a database id
            System.out.println(movie1.getName()+""+ movie1.getYear() +""+ movie1.getActors() +" "+ movie1.getId());
            System.out.println(movie2.getName()+""+ movie2.getYear() +""+ movie2.getActors() +" "+ movie2.getId());

        } finally {
            em.close();
        }
    }

}
