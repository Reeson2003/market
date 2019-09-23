package com.example.market.swingview;

import com.example.market.consoleview.model.InMemoryStorage;
import com.example.market.consoleview.model.Product;
import com.example.market.core.controller.TableController;
import com.example.market.core.data.Repository;
import com.example.market.core.module.Module;
import com.example.market.core.module.TableModule;
import com.example.market.swingview.view.SwingTableView;

import javax.swing.*;
import java.awt.*;

public class Application {

    public static void main(String[] args) {
        final JFrame frame = new JFrame("My app");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        final TableController<Product> controller = TableController.newInstance(Product::new);
        final SwingTableView<Product> tableView = new SwingTableView<>(controller.newOne().getPropDefs());
        frame.add(tableView);
        frame.setSize(new Dimension(500, 500));
        frame.setLocation(200, 100);
        final Repository<Product> repository = new InMemoryStorage<>();
        repository.add(new Product("Orange", "Fruit", 7));
        repository.add(new Product("Apple", "Fruit", 7));
        final Module<?> tableModule = new TableModule<Product>(repository, controller, tableView);
        tableModule.run();
        frame.setVisible(true);
    }

}
