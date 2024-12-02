/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab3.q2.jpa.controllers;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import lab3.q2.jpa.entities.Ticket;
import lab3.q2.jpa.controllers.exceptions.NonexistentEntityException;


public class TicketJpaController implements Serializable {

    public TicketJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ticket ticket) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ticket);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ticket ticket) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ticket = em.merge(ticket);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = ticket.getId();
                if (findTicket(id) == null) {
                    throw new NonexistentEntityException("The ticket with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ticket ticket;
            try {
                ticket = em.getReference(Ticket.class, id);
                ticket.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ticket with id " + id + " no longer exists.", enfe);
            }
            em.remove(ticket);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ticket> findTicketEntities() {
        return findTicketEntities(true, -1, -1);
    }

    public List<Ticket> findTicketEntities(int maxResults, int firstResult) {
        return findTicketEntities(false, maxResults, firstResult);
    }

    private List<Ticket> findTicketEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Ticket as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Ticket findTicket(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ticket.class, id);
        } finally {
            em.close();
        }
    }

    public int getTicketCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Ticket as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
