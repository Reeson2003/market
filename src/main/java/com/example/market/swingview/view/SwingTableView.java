package com.example.market.swingview.view;

import com.example.market.core.controller.TableController;
import com.example.market.core.data.DataSupplier;
import com.example.market.core.model.Model;
import com.example.market.core.model.PropDef;
import com.example.market.core.view.TableView;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class SwingTableView<M extends Model<M>>
        extends JPanel
        implements TableView<M> {

    private List<String> propertyDisplayNames;

    private List<String> propertyNames;

    private TableController<M> controller;

    private DataSupplier<M> dataSupplier;

    private JButton createButton;

    private JButton deleteButton;

    private JButton closeButton;

    private Box contents;

    private AtomicBoolean close;

    public SwingTableView() {
    }

    @SuppressWarnings("deprecation")
    @Override
    public void show() {
        setLayout(new BorderLayout());
        JToolBar toolBar = new JToolBar();
        createButton = new JButton("Add");
        deleteButton = new JButton("Delete");
        closeButton = new JButton("close");
        toolBar.add(createButton);
        toolBar.add(deleteButton);
        toolBar.add(closeButton);
        contents = new Box(BoxLayout.Y_AXIS);
        add(toolBar, BorderLayout.NORTH);
        add(contents, BorderLayout.CENTER);
        close = new AtomicBoolean(false);
        closeButton.addActionListener(e -> close.set(true));
        propertyDisplayNames = controller.newOne()
                                         .getPropDefs()
                                         .stream()
                                         .map(PropDef::getPropertyDisplayedName)
                                         .collect(Collectors.toList());
        propertyNames = controller.newOne()
                                  .getPropDefs()
                                  .stream()
                                  .map(PropDef::getPropertyName)
                                  .collect(Collectors.toList());
        final Map<Long, M> data = dataSupplier.getAll();
        final JTable table = createTable(data);
        contents.add(new JScrollPane(table));
        contents.setVisible(true);
    }

    @Override
    public void setController(TableController<M> controller) {
        this.controller = controller;
    }

    @Override
    public void setDataSupplier(DataSupplier<M> dataSupplier) {
        this.dataSupplier = dataSupplier;
    }

    private JTable createTable(Map<Long, M> data) {
        final JTable table = new JTable(data.size(), propertyDisplayNames.size());
        for (Map.Entry<Long, M> entry : data.entrySet()) {
            final M element = entry.getValue();
            for (int i = 0; i < propertyNames.size(); i++) {
                final String value = element.getPropertyValue(propertyNames.get(i));
                final int key = Math.toIntExact(entry.getKey());
                table.setValueAt(value, key, i);
            }
        }
        return table;
    }

}
