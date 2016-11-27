package client.action;

import client.main.Client;
import client.views.*;
import common.classes.Order;
import common.classes.Tour;
import common.classes.User;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Action {
    public static Client rmi = new Client();

    public static User signIn(SignInView view){
        String login = view.getLoginField().getText();
        String password = view.getPasswordField().getText();

        if (login.equals("") || password.equals("")) {
            return null;
        }

        User user = new User(0, login, password, "", 0, "", "");

        try {
            return rmi.getStub().signIn(user);

        } catch (RemoteException e) {
            System.err.println("Error connecting to stub");
            return null;
        }
    }

    public static User signUp(SignUpView view){
        String login = view.getLoginField().getText();
        String password1 = view.getPasswordField1().getText();
        String password2 = view.getPasswordField2().getText();
        String email = view.getEmailField().getText();
        String userName = view.getNameField().getText();
        String userSurname = view.getSurnameField().getText();


        if (login.equals("") || password1.equals("") || !password1.equals(password2) || email.equals("")) {
            return null;
        }

        User user = new User(0, login, password1, email, 0, userName, userSurname);

        try {
            return rmi.getStub().signUp(user);

        } catch (RemoteException e) {
            System.err.println("Error connecting to stub");
            return null;
        }
    }

    public static List<Tour> getTours(ToursView view, Tour tour){
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date1 = null, date2 = null;
        try {
            if (tour == null) {
                if (!view.getStartDateField().getText().equals("")){
                    date1 = sdf.parse(view.getStartDateField().getText());
                }

                if (!view.getEndDateField().getText().equals("")){
                    date2 = sdf.parse(view.getEndDateField().getText());
                }

                tour = new Tour(0,
                        "",
                        view.getCountryField().getText(),
                        view.getCityField().getText(),
                        date1,
                        date2,
                        view.getCostField().getText().equals("")? 0 : Integer.parseInt(view.getCostField().getText()),
                        view.getCountOfDaysField().getText().equals("")? 0 : Integer.parseInt(view.getCountOfDaysField().getText())
                );
            }

            List<Tour> tours = rmi.getStub().getTours(tour);
            return tours;

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean addOrder(Order order){
        try {
            return rmi.getStub().addOrder(order);

        } catch (RemoteException e) {
            System.err.println("Error connecting to stub");
        }

        return false;
    }
    public static List<Order> getOrders(Order order){
        try{
            return rmi.getStub().getOrders(order);
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }

        return null;
    }
    public static void viewSelectedOrder(OrderView view){
        int selectedIndexOrder = view.getListViewOrder().getSelectedIndex();
        Order selectedOrder = view.getOrdersList().get(selectedIndexOrder);

        String DataString =
                "Название: " + selectedOrder.getTour().getName() + '\n' +
                        "Страна: " + selectedOrder.getTour().getCost() + '\n' +
                        "Цена: " + selectedOrder.getTour().getCost() + '\n' +
                        "Город: " + selectedOrder.getTour().getCountry() + '\n' +
                        "Дата начала тура: " + selectedOrder.getTour().getStartDate() + '\n' +
                        "Дата окончания тура: " +selectedOrder.getTour().getEndDate() + '\n' +
                        "Количество дней: " +selectedOrder.getTour().getCountOfDays() +'\n' ;

        view.getTextPane().setText(DataString);

    }
    public static boolean DeleteSelectedOrder(OrderView view){
        int selectedItemIndex = view.getListViewOrder().getSelectedIndex();

        try{
            boolean Removed = rmi.getStub().DeleteOrder(view.getOrdersList().get(selectedItemIndex).getId());

            if (Removed) view.getJlistModel().remove(selectedItemIndex);

            return Removed;
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static User updateDataUser(ViewEditUser view, User currentUser){
        User userUpdata = new User(
                currentUser.getId(),
                currentUser.getLogin(),
                currentUser.getPassword(),
                view.getEditEmailField().getText(),
                currentUser.getType(),
                view.getEditNameField().getText(),
                view.getEditSurnametField().getText()
        );

        try{
            boolean isUpdated = rmi.getStub().updateDataUser(userUpdata.getId(), userUpdata);

            if (isUpdated) return userUpdata;
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }

        return null;

    }
}
