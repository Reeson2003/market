package com.example.market.consoleview.view;

import com.example.market.controller.TableController;
import com.example.market.data.DataSupplier;
import com.example.market.model.Model;
import com.example.market.model.PropDef;
import com.example.market.view.TableView;

import java.util.*;
import java.util.stream.Collectors;

public class ConsoleTableView<M extends Model<M>>
        extends ConsoleView
        implements TableView<M> {


    public static final String CREATE = "Create";

    public static final String UPDATE = "Update";

    public static final String DELETE = "Delete";

    public static final String FINISH = "finish";

    private final ConsoleEditView<M> editView = new ConsoleEditView<>();

    private TableController<M> controller;

    private DataSupplier<M> dataSupplier;

    @Override
    public void show() {
        boolean finish = false;
        while (!finish) {
            clearScreen();
            final Map<Long, M> models = getDataSupplier().getAll();
            showAsTable(models);
            String action;
            if (!models.isEmpty()) {
                action = askForAction(List.of(CREATE, UPDATE, DELETE, FINISH));
            } else {
                action = askForAction(List.of(CREATE, FINISH));
            }
            switch (action) {
                case CREATE: {
                    final M newModel = editView.edit(getController().newOne());
                    getController().create(newModel);
                    break;
                }
                case UPDATE: {
                    final long number = askForNumber(models.keySet());
                    final M model = models.get(number);
                    final M edited = editView.edit(model);
                    getController().update(number, edited);
                    break;
                }
                case DELETE: {
                    final long number = askForNumber(models.keySet());
                    getController().delete(number);
                    break;
                }
                case FINISH: {
                    finish = true;
                    break;
                }
            }
        }
    }

    private TableController<M> getController() {
        return Objects.requireNonNull(controller, "Controller is null");
    }

    @Override
    public void setController(TableController<M> controller) {
        this.controller = controller;
    }

    private DataSupplier<M> getDataSupplier() {
        return Objects.requireNonNull(dataSupplier, "Storage is null");
    }

    @Override
    public void setDataSupplier(DataSupplier<M> dataSupplier) {
        this.dataSupplier = dataSupplier;
    }

    private void showAsTable(Map<Long, M> models) {
        if (!models.isEmpty()) {
            final M next = models.values().iterator().next();
            final List<String> propertyNames = next.getPropDefs().stream()
                    .map(PropDef::getPropertyName)
                    .collect(Collectors.toList());
            final Map<Long, List<String>> valuesList = new HashMap<>(models.size());
            for (Map.Entry<Long, M> entry : models.entrySet()) {
                final ArrayList<String> values = new ArrayList<>();
                for (String propertyName : propertyNames) {
                    values.add(entry.getValue().getPropertyValue(propertyName));
                }
                valuesList.put(entry.getKey(), values);
            }
            final List<String> propertyDisplayedNames = next.getPropDefs().stream()
                    .map(PropDef::getPropertyDisplayedName)
                    .collect(Collectors.toList());
            printTable(propertyDisplayedNames, valuesList);
        }
    }

}
