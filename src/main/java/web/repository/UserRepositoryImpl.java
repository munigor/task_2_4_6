package web.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public void update(User user) {
        Query query = entityManager.createQuery("""
            UPDATE User SET lastName = :lastName, firstName = :firstName, email = :email, age = :age
            WHERE id = :id
            """);
        query
            .setParameter("lastName", user.getLastName())
            .setParameter("firstName", user.getFirstName())
            .setParameter("email", user.getEmail())
            .setParameter("age", user.getAge())
            .setParameter("id", user.getId())
            .executeUpdate();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Query query = entityManager.createQuery("DELETE User WHERE id = :id")
            .setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll(int limit) {
        return entityManager
            .createQuery("from User", User.class)
            .setMaxResults(limit)
            .getResultList();
    }
}
