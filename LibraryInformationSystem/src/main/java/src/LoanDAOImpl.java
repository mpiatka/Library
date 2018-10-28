package src;

import Interfaces.DAO;

import javax.persistence.EntityManager;
import java.util.List;

public class LoanDAOImpl implements DAO<Loan> {

    private EntityManager entityManager;

    public LoanDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Loan findById(Long id) {
        return entityManager.find(Loan.class, id);
    }

    @Override
    public List<Loan> findAll() {
        return entityManager.createQuery("SELECT e FROM Loan e", Loan.class).getResultList();
    }

    @Override
    public void create(Loan loan) {
        JpaHelper.executeInsideTransaction(entityManager, em -> em.persist(loan));

    }

    @Override
    public void remove(Loan loan) {
        JpaHelper.executeInsideTransaction(entityManager, em -> em.remove(loan));
    }

    @Override

    public void update(Loan loan) {
        JpaHelper.executeInsideTransaction(entityManager, em -> em.merge(loan));
    }
}
