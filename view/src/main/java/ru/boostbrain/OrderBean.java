package ru.boostbrain;

import ru.boostbrain.domain.Order;
import ru.boostbrain.domain.Thing;
import ru.boostbrain.ejb.OrdersManagerBean;
import ru.boostbrain.ejb.ThingsManagerBean;

import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Created by Arif on Нояб., 2018
 */
@Named
@SessionScoped
public class OrderBean implements Serializable {

    private Order order;
    private Thing thing;
    private String name;
    private int quantity;


    @EJB
    OrdersManagerBean ordersManagerBean;

    @EJB
    ThingsManagerBean thingsManagerBean;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public void createOrder() {
        if (this.order == null) {
            this.order = ordersManagerBean.createOrder();
        }
    }

    public void createThing() {
        this.thing = thingsManagerBean.createThing(name, quantity);
    }

    public List<Thing> getThings() {
        return thingsManagerBean.getThings();
    }

    public void addThing(Thing thing) {
        if (order != null) {

            ordersManagerBean.addToOrder(thing.getId(), order.getId(), 1);
        }
    }

    public List<Thing> getThingsInOrder() {
        if (order == null) {
            return Collections.emptyList();
        }
        return ordersManagerBean.getThingsInOrder(order.getId());
    }
}
