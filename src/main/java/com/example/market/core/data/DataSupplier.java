package com.example.market.core.data;

import com.example.market.core.model.Model;

import java.util.Collection;

public interface DataSupplier<M extends Model<M>> {

    Collection<M> getAll();
}
