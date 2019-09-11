package com.example.market.module;

import com.example.market.controller.TableController;
import com.example.market.data.DataSupplier;
import com.example.market.data.Storage;
import com.example.market.model.Model;
import com.example.market.view.TableView;

public class TableModule<M extends Model<M>>
        implements Module {

    private TableView<M> view;

    public TableModule(Storage<M> storage, DataSupplier<M> dataSupplier, TableController<M> controller, TableView<M> view) {
        controller.setStorage(storage);
        view.setController(controller);
        view.setDataSupplier(dataSupplier);
        this.view = view;
    }

    @Override
    public void run() {
        view.show();
    }
}
