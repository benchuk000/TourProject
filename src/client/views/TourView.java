package client.views;

import client.controllers.Controller;
import common.classes.Tour;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class TourView extends Frame{
    private JTextField countryField;
    private JTextField costField;
    private JTextField dateField1;
    private JTextField dateField2;
    private JTextField countOfDaysField;
    private JPanel panel;
    private JButton backButton;
    private JButton orderButton;
    private JLabel Название;

    public TourView() {
        panel.setPreferredSize(new Dimension(500, 500));
        Controller controller = new Controller(this, panel);

        orderButton.addActionListener(controller);
        orderButton.setActionCommand("orderTour");

        backButton.addActionListener(controller);
        backButton.setActionCommand("viewTours");
    }

    @Override
    public void setData(Serializable obj) {
        Tour tour = (Tour) obj;
        countryField.setText(tour.getName());
        costField.setText(Integer.toString(tour.getCost()));
        dateField1.setText(tour.getStartDate().toString());
        dateField2.setText(tour.getEndDate().toString());
        countOfDaysField.setText(Integer.toString(tour.getCountOfDays()));
    }
}
