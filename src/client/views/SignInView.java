package client.views;

import client.controllers.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SignInView extends Frame {
    private JPanel panel;
    private JPasswordField passwordField;
    private JTextField loginField;
    private JButton signInButton;
    private JButton signUpViewButton;

    public JTextField getLoginField() {
        return loginField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public SignInView() {
        panel.setPreferredSize(new Dimension(300, 200));
        Controller controller = new Controller(this, panel);

        signInButton.addActionListener(controller);
        signUpViewButton.addActionListener(controller);
        signInButton.setActionCommand("signIn");
        signUpViewButton.setActionCommand("signUpView");
    }


}
