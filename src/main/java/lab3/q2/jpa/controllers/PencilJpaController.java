package lab3.q2.jpa.controllers;

import lab3.q2.jpa.entities.Pencil;
import lab3.q2.jpa.controllers.exceptions.NonexistentEntityException;
import lab3.q2.jpa.controllers.exceptions.PreexistingEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class PencilJpaController {

    private EntityManagerFactory emf;

    public PencilJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    public void create(Pencil pencil) throws PreexistingEntityException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(pencil);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPencil(pencil.getId()) != null) {
                throw new PreexistingEntityException("Pencil " + pencil + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }


    public void edit(Pencil pencil) throws NonexistentEntityException, Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            pencil = em.merge(pencil);
            em.getTransaction().commit();
        } catch (Exception ex) {
            Long id = pencil.getId();
            if (findPencil(id) == null) {
                throw new NonexistentEntityException("The pencil with id " + id + " no longer exists.");
            }
            throw ex;
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    // Delete a Pencil by its ID
    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Pencil pencil;
            try {
                pencil = em.getReference(Pencil.class, id);
                pencil.getId();
            } catch (Exception e) {
                throw new NonexistentEntityException("The pencil with id " + id + " no longer exists.", e);
            }
            em.remove(pencil);
            em.getTransaction().commit();
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }


    public Pencil findPencil(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pencil.class, id);
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public List<Pencil> findPencilEntities() {
        return findPencilEntities(true, -1, -1);
    }


    public List<Pencil> findPencilEntities(int maxResults, int firstResult) {
        return findPencilEntities(false, maxResults, firstResult);
    }

    private List<Pencil> findPencilEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            String queryStr = "SELECT p FROM Pencil p";
            TypedQuery<Pencil> query = em.createQuery(queryStr, Pencil.class);
            if (!all) {
                query.setMaxResults(maxResults);
                query.setFirstResult(firstResult);
            }
            return query.getResultList();
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public int getPencilCount() {
        EntityManager em = getEntityManager();
        try {
            String queryStr = "SELECT COUNT(p) FROM Pencil p";
            TypedQuery<Long> query = em.createQuery(queryStr, Long.class);
            return query.getSingleResult().intValue();
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }
}
