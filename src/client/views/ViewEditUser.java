package client.views;

import client.controllers.Controller;
import common.classes.User;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class ViewEditUser extends Frame {
    private JTextField editNameField;
    private JTextField editSurnametField;
    private JTextField editEmailField;
    private JButton backButton;
    private JButton editButton;
    private JPanel panel;


    public ViewEditUser() {
        panel.setPreferredSize(new Dimension(300, 200));
        Controller controller = new Controller(this, panel);
        backButton.addActionListener(controller);
        editButton.addActionListener(controller);
        backButton.setActionCommand("viewProfile");
        editButton.setActionCommand("EditProfile");
    }
    public void setData(Serializable obj) {
        User user = (User) obj;
        editNameField.setText(user.getUserName());
        editSurnametField.setText(user.getUserSurname());
        editEmailField.setText(user.getEmail());

    }
    public JTextField getNameField() {
        return editNameField;
    }

    public void setNameField(JTextField editNameField) {
        this.editNameField = editNameField;
    }

    public JTextField getSurnameField() {
        return editSurnametField;
    }

    public void setSurnameField(JTextField editSurnametField) {
        this.editSurnametField = editSurnametField;
    }

    public JTextField getEditSurnametField() {
        return editSurnametField;
    }

    public void setEditSurnametField(JTextField editSurnametField) {
        this.editSurnametField = editSurnametField;
    }

    public JTextField getEditNameField() {

        return editNameField;
    }

    public void setEditNameField(JTextField editNameField) {
        this.editNameField = editNameField;
    }

    public JTextField getEditEmailField() {

        return editEmailField;
    }

    public void setEditEmailField(JTextField editEmailField) {
        this.editEmailField = editEmailField;
    }
}
