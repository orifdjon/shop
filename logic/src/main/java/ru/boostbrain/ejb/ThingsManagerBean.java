package ru.boostbrain.ejb;

import ru.boostbrain.domain.Thing;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Arif on Нояб., 2018
 */
public class ThingsManagerBean {

    @PersistenceContext(unitName = "examplePU")
    private EntityManager entityManager;

    public Thing createThing(String name, int price) {
        Thing thing = new Thing();
        thing.setName(name);
        thing.setQuantity(price);
        entityManager.persist(thing);

        return thing;
    }

    public List<Thing> getThings() {

        TypedQuery<Thing> query = entityManager.createQuery("select c from Thing  c", Thing.class);
        return query.getResultList();
    }
}
