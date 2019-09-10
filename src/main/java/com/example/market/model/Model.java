package com.example.market.model;

import java.util.List;

public interface Model<M> {

    List<String> getPropertyNames();

    String getPropertyValue(String propertyName);

    void setPropertyValue(String propertyName, String value);
}
