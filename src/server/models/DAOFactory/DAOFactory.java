package server.models.DAOFactory;

import server.models.DAO.OrderDAO;
import server.models.DAO.TourDAO;
import server.models.DAO.UserDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOFactory {
    public static String DB_NAME;
    public static String DB_URL;

    public static Connection createConnection() {
        DB_NAME = "TourDb";
        DB_URL = "jdbc:mysql://localhost" + ":" + 8889 + "/" + DB_NAME;

        Properties prop = new Properties();
        prop.setProperty("user", "root");
        prop.setProperty("password", "root");
        prop.setProperty("useUnicode", "true");
        prop.setProperty("characterEncoding", "UTF8");

        try {
            return DriverManager.getConnection(DB_URL, prop);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error connecting to Database");
        }
        return null;
    }

    public UserDAO getUserDAO() {
        return new UserDAO();
    }
    public TourDAO getTourDAO() {
        return new TourDAO();
    }
    public OrderDAO getOrderDAO() {
        return new OrderDAO();
    }
}
