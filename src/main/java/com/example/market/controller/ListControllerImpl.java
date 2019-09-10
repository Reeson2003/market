package com.example.market.controller;

import com.example.market.model.Model;
import com.example.market.model.Storage;

public abstract class ListControllerImpl<M extends Model<M>>
        implements ListController<M> {

    private final Storage<M> storage;


    public ListControllerImpl(Storage<M> storage) {
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
