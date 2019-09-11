package com.example.market.model;

import java.util.List;

public interface Model<M> {

    List<PropDef> getPropDefs();

    String getPropertyValue(String propertyName);

    void setPropertyValue(String propertyName, String value);
}
