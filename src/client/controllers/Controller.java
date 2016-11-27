package client.controllers;

import client.action.Action;
import client.state.State;
import client.views.*;
import common.classes.Order;
import common.classes.Tour;
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
                if(user != null) {
                    //MenuView menuView = new MenuView();
                    //currentState.setCurrentUser(user);
                    SignInView signInView = new SignInView();
                }
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

                break;
            }

            case "viewTour":{
                TourView tourView = new TourView();

                tourView.setData(currentState.getSelectedTour());

                break;
            }
            case "orderTour":{
                addOrder();
                break;
            }
            case "backToMenu":{
                MenuView menuView = new MenuView();
                break;
            }
            case "viewProfile":{
                MyRrofile myRrofile = new MyRrofile();
                myRrofile.setData(currentState.getCurrentUser());
                break;
        }
            case "ViewOrders":{
                OrderView orderView = new OrderView();

                orderView.setData(user, Action.getOrders(new Order(
                        0,
                        currentState.getCurrentUser(),
                        new Tour(0,"","","",null,null, 0, 0)
                )));

                break;
            }
            case "viewSelectedOrder":{
                viewSelectedOrder();
                break;
            }
            case "DeleteSelectOrder":{
                deleteSelectedOrder();
                break;
            }
            case "viewEditProfile":{
                ViewEditUser viewEditUser = new ViewEditUser();

                viewEditUser.setData(currentState.getCurrentUser());
                break;
            }
            case "EditProfile":{
                user = editMyProfile();

                if (user != null){
                    currentState.setCurrentUser(user);
                    ViewEditUser viewEditOrder = new ViewEditUser();
                    viewEditOrder.setData(currentState.getCurrentUser());
                }

                break;
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

    private boolean addOrder() {
        boolean isAdded = Action.addOrder(new Order(0, currentState.getCurrentUser(), currentState.getSelectedTour()));

        if (isAdded) {
            JOptionPane.showMessageDialog(mainFrame, "ЗАказ добавлен!","Сообщеник", JOptionPane.PLAIN_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(mainFrame, "Проверьте введенные данные!","Ошибка", JOptionPane.PLAIN_MESSAGE);
        }

        return isAdded;
    }
    private void viewSelectedOrder(){
        Action.viewSelectedOrder((OrderView) view);
    }
    private boolean deleteSelectedOrder(){
        boolean Removed = Action.DeleteSelectedOrder((OrderView) view);

        if (Removed) {
            JOptionPane.showMessageDialog(mainFrame, "Выбранный заказ удален","Уведомление", JOptionPane.PLAIN_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(mainFrame, "Случилось какая-то неполадка!","Ошибка", JOptionPane.PLAIN_MESSAGE);
        }

        return Removed;
    }
    private User editMyProfile(){
        User user = Action.updateDataUser((ViewEditUser) view, currentState.getCurrentUser());

        if (user == null) {
            JOptionPane.showMessageDialog(mainFrame, "Проверьте введенные данные!","Ошибка", JOptionPane.PLAIN_MESSAGE);
        }
        return user;
    }
}
