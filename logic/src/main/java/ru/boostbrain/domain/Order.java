package ru.boostbrain.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Arif on Нояб., 2018
 */
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany
    private List<ThingInOrder> thingInOrders;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ThingInOrder> getThingInOrders() {
        return thingInOrders;
    }

    public void setThingInOrders(List<ThingInOrder> thingInOrders) {
        this.thingInOrders = thingInOrders;
    }
}
