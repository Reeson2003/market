package com.example.market.view;

import com.example.market.model.Model;

import java.util.Map;

public interface TableView<M extends Model<M>> {

    void show(Map<Long, M> models);

}
