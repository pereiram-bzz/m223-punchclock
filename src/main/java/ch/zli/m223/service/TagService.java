package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import ch.zli.m223.model.ModelTag;

@ApplicationScoped
public class TagService {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public ModelTag createTag(ModelTag tag) {
        entityManager.persist(tag);
        return tag;
    }

    public List<ModelTag> findAll() {
        var query = entityManager.createQuery("FROM Tag", ModelTag.class);
        return query.getResultList();
    }

    @Transactional
    public Response deleteTag(Long id) {
        try {
            var entity = entityManager.find(ModelTag.class, id);
            entityManager.remove(entity);
        } catch(Exception e) {
            return Response.status(404).build();
        }
        return Response.noContent().build();
    } 

    @Transactional
    public Response updateTag(Long id, ModelTag updatedTag) {
        try {
            var entity = entityManager.find(ModelTag.class, id);
            entity.setTitle(updatedTag.getTitle());
        } catch(Exception e) {
            return Response.status(404).build();
        }
        return Response.noContent().build();
    } 
}
