package client.views;

import client.controllers.Controller;
import common.classes.Tour;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class ToursView extends Frame{
    private JPanel panel;
    private JTable table;
    private JTextField countryField;
    private JTextField cityField;
    private JTextField startDateField;
    private JTextField endDateField;
    private JTextField costField;
    private JTextField countOfDaysField;
    private DefaultTableModel tableModel;
    private JButton buttonAccept;

    public ToursView() {
        panel.setPreferredSize(new Dimension(1200, 700));
        Controller controller = new Controller(this, panel);


        buttonAccept.addActionListener(controller);
        buttonAccept.setActionCommand("acceptToursFilter");

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                int selectedTourIndex = table.rowAtPoint(event.getPoint());

                Tour selectedDeveice = new Tour(
                        0,
                        table.getValueAt(selectedTourIndex, 0).toString(),
                        table.getValueAt(selectedTourIndex, 2).toString(),
                        table.getValueAt(selectedTourIndex, 1).toString(),
                        new Date(table.getValueAt(selectedTourIndex, 3).toString()),
                        new Date(table.getValueAt(selectedTourIndex, 4).toString()),
                        Integer.parseInt(table.getValueAt(selectedTourIndex, 5).toString()),
                        Integer.parseInt(table.getValueAt(selectedTourIndex, 6).toString())
                );

                controller.currentState.setSelectedTour(selectedDeveice);
                controller.actionPerformed(new ActionEvent(controller, ActionEvent.ACTION_PERFORMED, "viewTour"));
            }
        });
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public JTextField getCountryField() {
        return countryField;
    }

    public void setCountryField(JTextField countryField) {
        this.countryField = countryField;
    }

    public JTextField getCityField() {
        return cityField;
    }

    public void setCityField(JTextField cityField) {
        this.cityField = cityField;
    }

    public JTextField getStartDateField() {
        return startDateField;
    }

    public void setStartDateField(JTextField startDateField) {
        this.startDateField = startDateField;
    }

    public JTextField getEndDateField() {
        return endDateField;
    }

    public void setEndDateField(JTextField endDateField) {
        this.endDateField = endDateField;
    }

    public JTextField getCostField() {
        return costField;
    }

    public void setCostField(JTextField costField) {
        this.costField = costField;
    }

    public JTextField getCountOfDaysField() {
        return countOfDaysField;
    }

    public void setCountOfDaysField(JTextField countOfDaysField) {
        this.countOfDaysField = countOfDaysField;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }

    private void createUIComponents() {
        String[] columns = {"Название",
                "Страна",
                "Город",
                "Дата от",
                "до",
                "Цена",
                "Кол-во дней"
        };

        tableModel = new DefaultTableModel();

        for (String column : columns){
            tableModel.addColumn(column);
        }

        table = new JTable(tableModel);
    }

    public void setData(Serializable obj, List list) {
        ArrayList<Tour> tourList = (ArrayList<Tour>) list;

        for (int rowIndex = tableModel.getRowCount() - 1; rowIndex >= 0; rowIndex--) {
            tableModel.removeRow(rowIndex);
        }

        for (Tour tour : tourList) {
            String[] row = {
                    tour.getName(),
                    tour.getCountry(),
                    tour.getCity(),
                    tour.getStartDate().toString(),
                    tour.getEndDate().toString(),
                    Integer.toString(tour.getCost()),
                    Integer.toString(tour.getCountOfDays()),
            };

            tableModel.addRow(row);
        }
    }
}
