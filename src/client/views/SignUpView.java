package client.views;

import client.controllers.Controller;

import javax.swing.*;
import java.awt.*;

public class SignUpView extends Frame{
    private JPanel panel;
    private JTextField loginField;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JTextField emailField;
    private JButton signInViewButton;
    private JButton signUpButton;
    private JTextField nameField;
    private JTextField surnameField;

    public JTextField getLoginField() {
        return loginField;
    }

    public JPasswordField getPasswordField1() {
        return passwordField1;
    }

    public JPasswordField getPasswordField2() {
        return passwordField2;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JTextField getSurnameField() {
        return surnameField;
    }

    public JTextField getNameField() {

        return nameField;
    }

    public SignUpView(){
        panel.setPreferredSize(new Dimension(400, 400));
        Controller controller = new Controller(this, panel);

        signInViewButton.addActionListener(controller);
        signUpButton.addActionListener(controller);
        signInViewButton.setActionCommand("signInView");
        signUpButton.setActionCommand("signUp");
    }

}
