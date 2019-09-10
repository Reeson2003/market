package com.example.market.consoleview;

import com.example.market.controller.ListController;
import com.example.market.model.Model;
import com.example.market.view.TableView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsoleTableView<M extends Model<M>>
        extends ConsoleView
        implements TableView<M> {


    public static final String CREATE = "Create";

    public static final String UPDATE = "Update";

    public static final String DELETE = "Delete";

    private final ListController<M> controller;

    private final ConsoleEditView<M> editView = new ConsoleEditView<>();

    private Map<Long, M> models;

    public ConsoleTableView(ListController<M> controller) {
        this.controller = controller;
    }

    @Override
    public void show(Map<Long, M> models) {
        this.models = models;
        clearScreen();
        showAsTable(models);
        String action;
        if (!models.isEmpty()) {
            action = askForAction(List.of(CREATE, UPDATE, DELETE));
        } else {
            action = askForAction(List.of(CREATE));
        }
        switch (action) {
            case CREATE: {
                final M newModel = editView.edit(controller.newOne());
                controller.create(newModel);
                break;
            }
            case UPDATE: {
                final long number = askForNumber(models.keySet());
                final M model = models.get(number);
                final M edited = editView.edit(model);
                controller.update(number, edited);
                break;
            }
            case DELETE: {
                final long number = askForNumber(models.keySet());
                controller.delete(number);
                break;
            }
        }
    }

    private void showAsTable(Map<Long, M> models) {
        if (!models.isEmpty()) {
            final M next = models.values().iterator().next();
            final List<String> propertyNames = next.getPropertyNames();
            final Map<Long, List<String>> valuesList = new HashMap<>(models.size());
            for (Map.Entry<Long, M> entry : models.entrySet()) {
                final ArrayList<String> values = new ArrayList<>();
                for (String propertyName : propertyNames) {
                    values.add(entry.getValue().getPropertyValue(propertyName));
                }
                valuesList.put(entry.getKey(), values);
            }
            printTable(propertyNames, valuesList);
        }
    }

}
