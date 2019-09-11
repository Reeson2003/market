package com.example.market.controller;

import com.example.market.model.Model;
import com.example.market.model.Storage;

import java.util.Objects;

public abstract class TableControllerImpl<M extends Model<M>>
        implements TableController<M> {

    private Storage<M> storage;

    @Override
    public void create(M model) {
        getStorage().add(model);
    }

    @Override
    public void delete(long index) {
        getStorage().delete(index);
    }

    @Override
    public void update(long index, M model) {
        getStorage().update(index, model);
    }

    private Storage<M> getStorage() {
        return Objects.requireNonNull(storage, "Storage is null");
    }

    @Override
    public void setStorage(Storage<M> storage) {
        this.storage = storage;
    }
}
