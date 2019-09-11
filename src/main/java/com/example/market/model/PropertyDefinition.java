package com.example.market.model;

public class PropertyDefinition {

    private final String propertyName;

    private final String propertyDisplayedName;

    public PropertyDefinition(String propertyName, String propertyDisplayedName) {
        this.propertyName = propertyName;
        this.propertyDisplayedName = propertyDisplayedName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public String getPropertyDisplayedName() {
        return propertyDisplayedName;
    }
}
