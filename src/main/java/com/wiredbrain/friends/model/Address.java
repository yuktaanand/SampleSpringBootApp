package com.wiredbrain.friends.model;

import javax.persistence.*;

// This was for one-to-one
//@Embeddable

    // Now for One-To-Many we need unique Id and entity
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String street;
    private String city;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


}
