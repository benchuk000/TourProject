package client.views;

import client.controllers.Controller;

import javax.swing.*;
import java.awt.*;

public class MenuView extends Frame{
    private JPanel panel;
    private JButton buttonViewTours;
    private JButton buttonProfile;
    private JButton buttonBack;

    public MenuView() {
        panel.setPreferredSize(new Dimension(300, 200));
        Controller ctrl = new Controller(this, panel);

        buttonViewTours.addActionListener(ctrl);
        buttonViewTours.setActionCommand("viewTours");

        buttonProfile.addActionListener(ctrl);
        buttonProfile.setActionCommand("viewProfile");

        buttonBack.addActionListener(ctrl);
        buttonBack.setActionCommand("signInView");
    }
}
