package client.views;

import client.controllers.Controller;
import common.classes.Tour;
import common.classes.User;

import javax.swing.*;
import java.awt.*;
import java.io.File;
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
    private JButton editButton;
    private JButton deleteButton;
    private JLabel photoLabel;
    private JButton editPhotoButton;
    private File photoFile;

    public File getPhotoFile() {
        return photoFile;
    }

    public void setPhotoFile(File photoFile) {
        this.photoFile = photoFile;
    }

    public JLabel getPhotoLabel() {
        return photoLabel;
    }

    public void setPhotoLabel(JLabel photoLabel) {
        this.photoLabel = photoLabel;
    }

    public TourView() {
        panel.setPreferredSize(new Dimension(900, 900));
        Controller controller = new Controller(this, panel);

        orderButton.addActionListener(controller);
        orderButton.setActionCommand("orderTour");

        backButton.addActionListener(controller);
        backButton.setActionCommand("viewTours");

        editButton.addActionListener(controller);
        editButton.setActionCommand("editTour");
        editButton.setVisible(false);

        deleteButton.addActionListener(controller);
        deleteButton.setActionCommand("deleteTour");
        deleteButton.setVisible(false);

        editPhotoButton.addActionListener(controller);
        editPhotoButton.setActionCommand("editPhoto");
        editPhotoButton.setVisible(false);
    }

    @Override
    public void setData(Serializable obj, Serializable obj2) {
        User user = (User) obj;
        Tour tour = (Tour) obj2;
        countryField.setText(tour.getCountry());
        costField.setText(Integer.toString(tour.getCost()));
        dateField1.setText(tour.getStartDate().toString());
        dateField2.setText(tour.getEndDate().toString());
        countOfDaysField.setText(Integer.toString(tour.getCountOfDays()));

        if (user.getType() == 1) {
            editButton.setVisible(true);
            deleteButton.setVisible(true);
            editPhotoButton.setVisible(true);
            orderButton.setVisible(false);
        }
    }

    public JTextField getCountryField() {
        return countryField;
    }

    public void setCountryField(JTextField countryField) {
        this.countryField = countryField;
    }

    public JTextField getCostField() {
        return costField;
    }

    public void setCostField(JTextField costField) {
        this.costField = costField;
    }

    public JTextField getDateField1() {
        return dateField1;
    }

    public void setDateField1(JTextField dateField1) {
        this.dateField1 = dateField1;
    }

    public JTextField getDateField2() {
        return dateField2;
    }

    public void setDateField2(JTextField dateField2) {
        this.dateField2 = dateField2;
    }

    public JTextField getCountOfDaysField() {
        return countOfDaysField;
    }

    public void setCountOfDaysField(JTextField countOfDaysField) {
        this.countOfDaysField = countOfDaysField;
    }
}
