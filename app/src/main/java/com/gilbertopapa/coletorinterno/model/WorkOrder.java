package com.gilbertopapa.coletorinterno.model;

/**
 * Created by Gilbertopapa on 25/07/2016.
 */

public class WorkOrder {

    private Integer id;
    private String description;
    private String order_number;
    private String vehicle_plate;
    private String type_of_order;
    private String work;
    private String client;
    private String work_order_type;
    private Integer user_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public String getVehicle_plate() {
        return vehicle_plate;
    }

    public void setVehicle_plate(String vehicle_plate) {
        this.vehicle_plate = vehicle_plate;
    }

    public String getType_of_order() {
        return type_of_order;
    }

    public void setType_of_order(String type_of_order) {
        this.type_of_order = type_of_order;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getWork_order_type() {
        return work_order_type;
    }

    public void setWork_order_type(String work_order_type) {
        this.work_order_type = work_order_type;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
