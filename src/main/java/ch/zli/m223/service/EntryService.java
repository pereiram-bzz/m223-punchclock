package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.reactive.RestResponse;

import ch.zli.m223.model.Entry;

@ApplicationScoped
public class EntryService {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public Entry createEntry(Entry entry) {
        entityManager.persist(entry);
        return entry;
    }

    public List<Entry> findAll() {
        var query = entityManager.createQuery("FROM Entry", Entry.class);
        return query.getResultList();
    }

    @Transactional
    public Response deleteEntry(Long id) {
        try {
            var entity = entityManager.find(Entry.class, id);
            entityManager.remove(entity);
        } catch(Exception e) {
            return Response.status(404).build();
        }
        return Response.noContent().build();
    } 

    @Transactional
    public Response updateEntry(Long id) {
        try {
            var entity = entityManager.find(Entry.class, id);
            entityManager.merge(entity);
        } catch(Exception e) {
            return Response.status(404).build();
        }
        return Response.noContent().build();
    } 
}
