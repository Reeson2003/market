package com.example.market.controller;

import com.example.market.model.Model;
import com.example.market.model.Storage;

public abstract class TableControllerImpl<M extends Model<M>>
        implements TableController<M> {

    private final Storage<M> storage;


    public TableControllerImpl(Storage<M> storage) {
        this.storage = storage;
    }

    @Override
    public void create(M model) {
        storage.add(model);
    }

    @Override
    public void delete(long index) {
        storage.delete(index);
    }

    @Override
    public void update(long index, M model) {
        storage.update(index, model);
    }

}
