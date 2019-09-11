package com.example.market.model;

public interface Storage<M extends Model<M>> {

    void add(M model);

    void delete(long index);

    void update(long index, M model);
}
