package client.views;

import client.controllers.Controller;
import common.classes.User;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.Serializable;

public class MenuView extends Frame{
    private JPanel panel;
    private JButton buttonViewTours;
    private JButton buttonProfile;
    private JButton buttonBack;
    private JButton usersButton;

    public MenuView() {
        panel.setPreferredSize(new Dimension(300, 200));
        Controller ctrl = new Controller(this, panel);


        buttonViewTours.addActionListener(ctrl);
        buttonViewTours.setActionCommand("viewTours");

        buttonProfile.addActionListener(ctrl);
        buttonProfile.setActionCommand("viewProfile");

        buttonBack.addActionListener(ctrl);
        buttonBack.setActionCommand("signInView");

        usersButton.addActionListener(ctrl);
        usersButton.setActionCommand("viewUsers");
        usersButton.setVisible(false);
    }

    @Override
    public void setData(Serializable obj) {
        User user = (User) obj;

        if (user.getType() == 1) usersButton.setVisible(true);
    }
}
