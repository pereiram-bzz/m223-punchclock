package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import ch.zli.m223.model.User;

@ApplicationScoped
public class UserService {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public User createUser(User user) {
        entityManager.persist(user);
        return user;
    }

    public List<User> findAll() {
        var query = entityManager.createQuery("FROM User", User.class);
        return query.getResultList();
    }

    @Transactional
    public Response deleteUser(Long id) {
        try {
            var entity = entityManager.find(User.class, id);
            entityManager.remove(entity);
        } catch(Exception e) {
            return Response.status(404).build();
        }
        return Response.noContent().build();
    } 
}
