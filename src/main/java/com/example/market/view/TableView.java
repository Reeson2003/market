package com.example.market.view;

import com.example.market.controller.TableController;
import com.example.market.model.DataSupplier;
import com.example.market.model.Model;

public interface TableView<M extends Model<M>> {

    void show();

    void setController(TableController<M> controller);

    void setDataSupplier(DataSupplier<M> dataSupplier);
}
