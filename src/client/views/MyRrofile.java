package client.views;

import client.controllers.Controller;
import common.classes.Tour;
import common.classes.User;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class MyRrofile extends Frame {
    private JTextField textFieldName;
    private JTextField textFieldMail;
    private JTextField textFieldSurname;
    private JButton buttonBack;
    private JButton buttonEdit;
    private JButton buttonViewOrders;
    private JPanel panel;

    public MyRrofile(){
        panel.setPreferredSize(new Dimension(500, 350));
        Controller controller = new Controller(this, panel);

        buttonBack.addActionListener(controller);
        buttonBack.setActionCommand("backToMenu");

        buttonEdit.addActionListener(controller);
        buttonEdit.setActionCommand("viewEditProfile");

        buttonViewOrders.addActionListener(controller);
        buttonViewOrders.setActionCommand("ViewOrders");

    }
    public void setData(Serializable obj) {
        User user = (User) obj;
        textFieldName.setText(user.getUserName());
        textFieldSurname.setText(user.getUserSurname());
        textFieldMail.setText(user.getEmail());


    }
}

