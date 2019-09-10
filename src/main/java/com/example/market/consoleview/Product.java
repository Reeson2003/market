package com.example.market.consoleview;

import com.example.market.model.Model;

import java.util.List;

public class Product
        implements Model<Product> {

    private static final String NAME = "name";

    private static final String DESCRIPTION = "description";

    private static final String AMOUNT = "amount";

    private String name;

    private String description;

    private int amount;

    public Product(String name, String description, int amount) {
        this.name = name;
        this.description = description;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public List<String> getPropertyNames() {
        return List.of(NAME, DESCRIPTION, AMOUNT);
    }

    @Override
    public String getPropertyValue(String propertyName) {
        switch (propertyName) {
            case NAME:
                return name;
            case DESCRIPTION:
                return description;
            case AMOUNT:
                return String.valueOf(amount);
            default:
                throw new IllegalArgumentException("No property with name: " + propertyName);
        }
    }

    @Override
    public void setPropertyValue(String propertyName, String value) {
        switch (propertyName) {
            case NAME:
                setName(value);
                break;
            case DESCRIPTION:
                setDescription(value);
                break;
            case AMOUNT:
                setAmount(Integer.parseInt(value));
                break;
            default:
                throw new IllegalArgumentException("No property with name: " + propertyName);
        }
    }

}
