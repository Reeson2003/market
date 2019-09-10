package com.example.market.consoleview;

import com.example.market.controller.ListControllerImpl;
import com.example.market.model.InMemoryStorage;
import com.example.market.model.Storage;

import java.util.Map;

public class Application {

    public static void main(String[] args) {
        final InMemoryStorage<Product> storage = new InMemoryStorage<>();
        final ListControllerImpl<Product> controller = listController(storage);
        final ConsoleTableView<Product> view = new ConsoleTableView<>(controller);
        while (true) {
            final Map<Long, Product> all = storage.getAll();
            view.show(all);
        }
    }

    private static ListControllerImpl<Product> listController(Storage<Product> storage) {
        return new ListControllerImpl<>(storage) {
            @Override
            public Product newOne() {
                return new Product("", "", 0);
            }
        };
    }
}
