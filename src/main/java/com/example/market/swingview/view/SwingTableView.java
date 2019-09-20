package com.example.market.swingview.view;

import com.example.market.core.controller.TableController;
import com.example.market.core.data.DataSupplier;
import com.example.market.core.model.Model;
import com.example.market.core.model.PropDef;
import com.example.market.core.view.TableView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SwingTableView<M extends Model<M>>
        extends JPanel
        implements TableView<M> {

    private static final Logger LOG = LoggerFactory.getLogger(SwingTableView.class);

    private List<String> propertyNames;

    private TableController<M> controller;

    private DataSupplier<M> dataSupplier;

    private DefaultTableModel tableModel;

    @SuppressWarnings("deprecation")
    @Override
    public void show() {
        setLayout(new BorderLayout());
        JToolBar toolBar = new JToolBar();
        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");
        JButton saveButton = new JButton("Save");
        toolBar.add(addButton);
        toolBar.add(deleteButton);
        toolBar.add(saveButton);
        Box contents = new Box(BoxLayout.Y_AXIS);
        add(toolBar, BorderLayout.NORTH);
        add(contents, BorderLayout.CENTER);
        List<String> propertyDisplayNames = controller.newOne()
                                                      .getPropDefs()
                                                      .stream()
                                                      .map(PropDef::getPropertyDisplayedName)
                                                      .collect(Collectors.toList());
        propertyNames = controller.newOne()
                                  .getPropDefs()
                                  .stream()
                                  .map(PropDef::getPropertyName)
                                  .collect(Collectors.toList());
        tableModel = new DefaultTableModel(propertyDisplayNames.toArray(), 0);
        final JTable table = new JTable(tableModel);
        deleteButton.addActionListener(event -> {
            final int[] rows = table.getSelectedRows();
            LOG.debug("Selected rows: {}", Arrays.toString(rows));
            for (int i = 0; i < rows.length; i++) {
                tableModel.removeRow(rows[i] - i);
            }
        });
        addButton.addActionListener(event -> {
            LOG.debug("Add entry event received");
            final M model = controller.newOne();
            SwingUtilities.invokeLater(() -> addNewRow(model));
        });
        contents.add(new JScrollPane(table));
        contents.setVisible(true);
        final Collection<M> data = dataSupplier.getAll();
        fillTable(data);
    }

    @Override
    public void setController(TableController<M> controller) {
        this.controller = controller;
    }

    @Override
    public void setDataSupplier(DataSupplier<M> dataSupplier) {
        this.dataSupplier = dataSupplier;
    }

    private void fillTable(Collection<M> data) {
        data.forEach(this::addNewRow);
    }

    private void addNewRow(M model) {
        final Object[] values = propertyNames.stream()
                                             .map(model::getPropertyValue)
                                             .toArray();
        tableModel.addRow(values);
    }

}
