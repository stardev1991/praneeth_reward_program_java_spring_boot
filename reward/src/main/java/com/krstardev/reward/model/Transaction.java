package com.krstardev.reward.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Transaction {

    private final Long id;

    @NotNull
    private final Long user;

    @NotNull
    private final double price;

    private final Date date;

    public Transaction(@JsonProperty("id") Long id,
                       @JsonProperty("user") Long user,
                       @JsonProperty("price") double price,
                       @JsonProperty("date") Date date) {
        this.id = id;
        this.user = user;
        this.price = price;

        if(date == null) {
            this.date = new Date();
        } else {
            this.date = date;
        }
    }

    public Long getId() {
        return id;
    }

    public Long getUser() {
        return user;
    }

    public double getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }
}
