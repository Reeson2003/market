package com.example.market.consoleview.model;

import com.example.market.model.BaseModel;
import com.example.market.model.Named;

public class Product
        extends BaseModel<Product> {

    @Named("Имя")
    private String name;

    @Named("Описание")
    private String description;

    @Named("Количество")
    private int amount;

}
