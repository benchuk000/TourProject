package server.api;

import common.classes.Order;
import common.classes.Tour;
import common.classes.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface API extends Remote {
    User signIn(User user) throws RemoteException;
    User signUp(User user) throws RemoteException;
    List<Tour> getTours(Tour tour) throws RemoteException;
    boolean addOrder(Order order) throws RemoteException;
    List<Order> getOrders(Order order) throws RemoteException;
    public boolean DeleteOrder(int id) throws RemoteException;
    boolean updateDataUser(int id, User user) throws RemoteException;;
}
