package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import ch.zli.m223.model.Category;

@ApplicationScoped
public class CategoryService {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public Category createCategory(Category category) {
        entityManager.persist(category);
        return category;
    }

    public List<Category> findAll() {
        var query = entityManager.createQuery("FROM Category", Category.class);
        return query.getResultList();
    }

    @Transactional
    public Response deleteCategory(Long id) {
        try {
            var entity = entityManager.find(Category.class, id);
            entityManager.remove(entity);
        } catch(Exception e) {
            return Response.status(404).build();
        }
        return Response.noContent().build();
    } 

    @Transactional
    public Response updateCategory(Long id, Category updatedCategory) {
        try {
            var entity = entityManager.find(Category.class, id);
            entity.setTitle(updatedCategory.getTitle());
        } catch(Exception e) {
            return Response.status(404).build();
        }
        return Response.noContent().build();
    } 
}
