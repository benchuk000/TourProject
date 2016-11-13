package client.controllers;

import client.action.Action;
import client.state.State;
import client.views.*;
import common.classes.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    public static State currentState = new State();

    private static MainFrame mainFrame = null;

    private Component view;
    private Component panel;

    public Controller(Component view, Component panel) {
        if (this.mainFrame != null) {
            this.mainFrame.dispose();
        }

        this.mainFrame = new MainFrame(panel);
        this.mainFrame.setVisible(true);
        this.view = view;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();

        User user = new User(0, "", "", "", 0, "", "");

        switch (action) {
            case "signIn":
                user = signIn();
                if (user != null) {
                    MenuView menuView = new MenuView();
                    currentState.setCurrentUser(user);
                }
                break;
            case "signUp":
                user = signUp();
                break;
            case "signUpView":
                SignUpView signUpView = new SignUpView();
                break;
            case "signInView":
                SignInView signInView = new SignInView();
                break;
            case "viewTours":{
                ToursView toursView = new ToursView();

                currentState.setCurrentFrame(toursView);

                toursView.setData(user, Action.getTours(toursView, null));
            }
            case "acceptToursFilter":{
                ToursView toursView = (ToursView) currentState.getCurrentFrame();

                toursView.setData(user, Action.getTours(toursView, null));
            }
        }
    }

    private User signIn() {
        User user = Action.signIn((SignInView) view);

        if (user == null) {
            JOptionPane.showMessageDialog(mainFrame,"Проверьте введенные данные!","Ошибка", JOptionPane.PLAIN_MESSAGE);
        }
        return user;
    }

    private User signUp() {
        User user = Action.signUp((SignUpView) view);

        if (user == null) {
            JOptionPane.showMessageDialog(mainFrame, "Проверьте введенные данные!","Ошибка", JOptionPane.PLAIN_MESSAGE);
        }
        return user;
    }
}
