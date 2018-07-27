package com.gilbertopapa.coletorinterno.model;

import java.io.Serializable;

/**
 * Created by Gilbertopapa on 25/07/2016.
 */

public class Item implements Serializable {

    private Integer id;
    private String name;
    private String weight;
    private String order_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getOrderId() {
        return order_id;
    }

    public void setOrderId(String orderId) {
        this.order_id = orderId;
    }
}
