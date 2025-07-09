package ch.zli.m223.utils;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Category;
import ch.zli.m223.model.Entry;
import ch.zli.m223.model.ModelTag;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class DataSeeder {
    
    @Inject
    private EntityManager entityManager;

    public ModelTag createTestModelTag(String tagTitle) {
            var tag = new ModelTag();
            tag.setTitle(tagTitle);
            entityManager.persist(tag);
            System.out.print("Created testTag");
            return tag;
    }

    public Category createTestCategory(String categoryTitle) {
            var category = new Category();
            category.setTitle(categoryTitle);
            entityManager.persist(category);
            System.out.print("Created testCategory");
            return category;
    }

    public void createTestEntry(LocalDateTime checkIn, LocalDateTime checkOut, Category category, Set<ModelTag> tags) {
            var entry = new Entry();
            entry.setCheckIn(checkIn);
            entry.setCheckOut(checkOut);
            entry.setCategory(category);
            entry.setTags(tags);
            entityManager.persist(entry);
            System.out.print("Created testEntry");
    }

    @Transactional
    public void onStart(@Observes StartupEvent event) {
        var projX = createTestModelTag("Project X");
        var projY = createTestModelTag("Project Y");
        var projZ = createTestModelTag("Project Z");
        var catA = createTestCategory("Category A");
        var catB = createTestCategory("Category B");
        var time1 = LocalDateTime.parse("2025-07-07T12:20:00");
        var time2 = LocalDateTime.parse("2025-07-07T15:30:00");
        var time3 = LocalDateTime.parse("2025-08-07T08:20:00");
        var time4 = LocalDateTime.parse("2025-08-07T11:30:00");
        Set<ModelTag> tags1 = new HashSet<ModelTag>(); 
        Set<ModelTag> tags2 = new HashSet<ModelTag>(); 
        tags1.add(projX);
        tags1.add(projY);
        tags2.add(projY);
        tags2.add(projZ);
        createTestEntry(time1, time2, catA, tags1);
        createTestEntry(time3, time4, catB, tags2);
    }
}
