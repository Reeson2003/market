package com.example.market.model;

import java.util.List;

public interface Model<M> {

    List<PropertyDefinition> getPropertyDefinitions();

    String getPropertyValue(String propertyName);

    void setPropertyValue(String propertyName, String value);
}
