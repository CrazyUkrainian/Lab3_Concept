package lecture5.jpa.controllers;

import lecture5.jpa.entities.CashTill;
import lecture5.jpa.controllers.exceptions.NonexistentEntityException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class CashTillJpaController {

    private EntityManagerFactory emf;

    public CashTillJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Create CashTill
    public void create(CashTill cashTill) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cashTill);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Edit CashTill
    public void edit(CashTill cashTill) throws NonexistentEntityException, Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            cashTill = em.merge(cashTill);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (findCashTill(cashTill.getId()) == null) {
                throw new NonexistentEntityException("The CashTill with ID " + cashTill.getId() + " no longer exists.");
            }
            throw e;
        } finally {
            em.close();
        }
    }

    // Find CashTill by ID
    public CashTill findCashTill(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CashTill.class, id);
        } finally {
            em.close();
        }
    }
}
