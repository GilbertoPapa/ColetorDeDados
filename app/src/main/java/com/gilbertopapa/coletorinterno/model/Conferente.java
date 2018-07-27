package com.gilbertopapa.coletorinterno.model;

import java.io.Serializable;

/**
 * Created by Gilbertopapa on 25/07/2016.
 */

public class Conferente implements Serializable {

    private Integer id;
    private String title;
    private String first_name;
    private String last_name;
    private String office;
    private int charge;


    public Conferente(int id, String first_name, String last_name,String office,String title,int charge) {
        this.setId(id);
        this.setFirst_name(first_name);
        this.setLast_name(last_name);
        this.setOffice(office);
        this.setTitle(title);
        this.setCharge(charge);

    }

    public Conferente() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

}
