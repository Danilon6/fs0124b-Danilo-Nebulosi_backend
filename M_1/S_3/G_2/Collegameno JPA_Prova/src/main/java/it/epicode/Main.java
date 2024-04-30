package it.epicode;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main {
    private static final String PERSISTENCE_UNIT = "JPA_Sample";
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        EntityManager em = emf.createEntityManager();

        var persona1 = new Persona(1,"Danilo", "Nebulosi");
        EntityTransaction transaction = em.getTransaction();

        //transaction.begin();

        //em.persist(persona1);

        //transaction.commit();

        var personaDaLeggere = em.find(Persona.class, 1);
        System.out.println(personaDaLeggere.getNome());

        em.close();

        emf.close();





    }
}