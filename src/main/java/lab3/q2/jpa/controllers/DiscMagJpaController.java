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
import lab3.q2.jpa.entities.DiscMag;
import lab3.q2.jpa.controllers.exceptions.NonexistentEntityException;

public class DiscMagJpaController implements Serializable {

    public DiscMagJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DiscMag discMag) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(discMag);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DiscMag discMag) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            discMag = em.merge(discMag);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = discMag.getId();
                if (findDiscMag(id) == null) {
                    throw new NonexistentEntityException("The discMag with id " + id + " no longer exists.");
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
            DiscMag discMag;
            try {
                discMag = em.getReference(DiscMag.class, id);
                discMag.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The discMag with id " + id + " no longer exists.", enfe);
            }
            em.remove(discMag);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DiscMag> findDiscMagEntities() {
        return findDiscMagEntities(true, -1, -1);
    }

    public List<DiscMag> findDiscMagEntities(int maxResults, int firstResult) {
        return findDiscMagEntities(false, maxResults, firstResult);
    }

    private List<DiscMag> findDiscMagEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from DiscMag as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public DiscMag findDiscMag(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DiscMag.class, id);
        } finally {
            em.close();
        }
    }

    public int getDiscMagCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from DiscMag as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
