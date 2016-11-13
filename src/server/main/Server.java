package server.main;

import common.classes.Tour;
import common.classes.User;
import server.api.API;
import server.models.DAO.TourDAO;
import server.models.DAO.UserDAO;
import server.models.DAOFactory.DAOFactory;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.ResourceBundle;

public class Server implements API{
    private int PORT;

    private UserDAO userDAO;
    private TourDAO tourDAO;

    private static Server instance;

    private int numberOfUsers;

    public Server() {
        try {
            PORT = 3001;
            API stub = (API) UnicastRemoteObject.exportObject(this, 0);
            Registry registry = LocateRegistry.createRegistry(PORT);
            registry.bind("ServerInterface", stub);

            DAOFactory daoFactory = new DAOFactory();
            userDAO = daoFactory.getUserDAO();
            tourDAO = daoFactory.getTourDAO();

            numberOfUsers = 0;

            System.out.println("Server is ready");
        } catch (RemoteException e) {
            System.out.println("Error exporting stub");
        } catch (AlreadyBoundException e) {
            System.out.println("Error binding registry to stub");
        }

    }

    public static Server getInstance() {
        if (instance == null) {
            synchronized (Server.class) {
                if (instance == null) {
                    instance = new Server();
                }
            }
        }
        return instance;
    }

    @Override
    public User signIn(User user) throws RemoteException {
        List<User> response = userDAO.findUser(user);
        User searchedUser = response.isEmpty()? null : response.get(0);

        if (searchedUser != null) System.out.println("Number of users: " + ++numberOfUsers);

        return searchedUser;
    }

    @Override
    public User signUp(User user) throws RemoteException {
        boolean isExist = !(userDAO.findUser(user).size() == 0);

        if (!isExist) {

            userDAO.addUser(user);
            System.out.println("Number of users: " + ++numberOfUsers);

            return userDAO.findUser(user).get(0);

        } else {
            return null;
        }
    }

    @Override
    public List<Tour> getTours(Tour tour) throws RemoteException {
        return tourDAO.findTour(tour);
    }
}
