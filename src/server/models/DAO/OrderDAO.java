package server.models.DAO;

import common.classes.Order;
import common.classes.Tour;
import common.classes.User;
import server.models.DAOFactory.DAOFactory;
import server.models.DAOI.OrderDAOInterface;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDAO implements OrderDAOInterface{
    private Connection connection;
    private TourDAO tourDAO;

    public OrderDAO() {
        this.connection = DAOFactory.createConnection();

        tourDAO = new TourDAO();
    }

    @Override
    public boolean addOrder(Order order) {
        boolean isInserted = false;

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String query = "INSERT INTO Orders (user_id, tour_id) " + "VALUES (" + order.getUser().getId() + ", " + order.getTour().getId() + ")";

        System.out.println(query);

        try {
            Statement statement = connection.createStatement();
            isInserted = statement.executeUpdate(query) > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error inserting tour");
        }

        return isInserted;
    }

    @Override
    public boolean deleteOrder(int id) {
        boolean isDeleted = false;
        String query = "DELETE FROM Orders WHERE id = " + id + "";

        System.out.println(query);

        try {
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(query);

            if (result != 0) {
                isDeleted = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error deleting order");
        }

        return isDeleted;
    }

    @Override
    public List<Order> findOrder(Order order) {
        List<Order> searched = new ArrayList<Order>();
        boolean conditionExist = false;

        String query = "SELECT * FROM Orders";

        if (order.getUser() != null) {
            query += conditionExist ? " AND " : " WHERE ";
            query += "user_id = " + order.getUser().getId() + "";
            conditionExist = true;
        }

        System.out.println(query);

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                Tour tour = tourDAO.findTour(new Tour(result.getInt("tour_id"), "", "", "", null, null,  0, 0, "")).get(0);
                searched.add(new Order(
                        result.getInt("id"),
                        new User(result.getInt("user_id"), "", "" ,"", 0, "", ""),
                        tour
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error finding order");
        }

        return searched;
    }

    @Override
    public boolean updateOrder(int id, Order order) {
        boolean isUpdated = false;

        String query = "UPDATE Orders SET " +
                "id = '" + order.getId() + "', " +
                "user_id = '" + order.getUser().getId() + "', " +
                "tour_id = '" + order.getTour().getId() + "', " +
                "WHERE id = '" + id + "';";
        try {
            Statement statement = connection.createStatement();
            isUpdated = statement.executeUpdate(query) > 0;

        } catch (SQLException e) {
            System.out.println("Error updating order");
        }

        return isUpdated;
    }
}
