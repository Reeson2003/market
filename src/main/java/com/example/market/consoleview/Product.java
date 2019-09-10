package com.example.market.consoleview;

import com.example.market.model.BaseModel;
import com.example.market.model.Model;

public class Product
        extends BaseModel<Product>
        implements Model<Product> {

    private String name;

    private String description;

    private int amount;

    public Product(String name, String description, int amount) {
        this.name = name;
        this.description = description;
        this.amount = amount;
    }

}
