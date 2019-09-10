package com.example.market.view;

import com.example.market.model.Model;

public interface EditView<M extends Model<M>> {

    M edit(M model);
}
