package ru.boostbrain.ejb;

import ru.boostbrain.domain.Order;
import ru.boostbrain.domain.Thing;
import ru.boostbrain.domain.ThingInOrder;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Arif on Нояб., 2018
 */
@Stateless
@LocalBean
public class OrdersManagerBean {

    @PersistenceContext(unitName = "examplePU")
    private EntityManager entityManager;

    public Order createOrder() {
        Order order = new Order();
        entityManager.persist(order);

        return order;
    }

    public Boolean addToOrder(long thingId, long orderId, int quantity){
        Thing thing = entityManager.find(Thing.class, thingId);
        if (thing == null) {
            return false;
        }

        Order order = entityManager.find(Order.class, orderId);
        if (order == null) {
            return false;
        }

        ThingInOrder thingInOrder = new ThingInOrder();
        thingInOrder.setOrder(order);
        thingInOrder.setThing(thing);
        thingInOrder.setQuantity(quantity);
        entityManager.persist(thingInOrder);
        return true;

    }

    public List<Thing> getThingsInOrder(long orderId) {
        Order order = entityManager.find(Order.class, orderId);
        if (order == null) {
            return Collections.emptyList();
        }

        List<ThingInOrder> thingInOrders = order.getThingInOrders();
        List<Thing> result = new ArrayList<>();
        for (ThingInOrder thingInOrder : thingInOrders) {
            result.add(thingInOrder.getThing());
        }
        return result;
    }
}
