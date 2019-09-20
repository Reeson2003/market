package com.example.market.core.data;

import com.example.market.core.model.Model;

public interface Storage<M extends Model<M>> {

    void add(M model);

    void delete(long index);

    void update(M model);

}
