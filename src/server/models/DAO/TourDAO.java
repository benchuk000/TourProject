package server.models.DAO;

import common.classes.Tour;
import server.models.DAOFactory.DAOFactory;
import server.models.DAOI.TourDAOInterface;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by olya on 13.11.16.
 */
public class TourDAO implements TourDAOInterface {
    private Connection connection;

    public TourDAO() {
        this.connection = DAOFactory.createConnection();
    }

    @Override
    public boolean addTour(Tour obj) {
        boolean isInserted = false;

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String startDate = sdf.format(obj.getStartDate());
        String endDate   = sdf.format(obj.getEndDate());

        String query = "INSERT INTO Tour (name, , country, city, startDate, endDate, cost, countOfDays) " +
                "VALUES ('" + obj.getName() + "', '" + obj.getCountry() + "', '" + obj.getCity() + "', '" + startDate + "', '" + endDate + "', '" + obj.getCost() + "', '" + obj.
        getCountOfDays() + "');";

        try {
            Statement statement = connection.createStatement();
            isInserted = statement.executeUpdate(query) > 0;

        } catch (SQLException e) {
            System.err.println("Error inserting tour");
        }

        return isInserted;
    }

    @Override
    public boolean deleteTour(int id) {
        boolean isDeleted = false;
        String query = "DELETE FROM Tour WHERE id = '" + id + "';";

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            if (result.next()) {
                isDeleted = true;
            }
        } catch (SQLException e) {
            System.err.println("Error deleting tour");
        }

        return isDeleted;
    }

    @Override
    public List<Tour> findTour(Tour tour) {
        List<Tour> searched = new ArrayList<Tour>();
        boolean conditionExist = false;

        String query = "SELECT * FROM Tour";

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (!tour.getName().equals("")) {
            query += conditionExist ? " AND " : " WHERE ";
            query += "name LIKE '%" + tour.getName() + "%'";
            conditionExist = true;
        }

        if (!tour.getCountry().equals("")) {
            query += conditionExist ? " AND " : " WHERE ";
            conditionExist = true;
            query += "country LIKE '%" + tour.getCountry() + "%'";
        }

        if (!tour.getCity().equals("")) {
            query += conditionExist ? " AND " : " WHERE ";
            conditionExist = true;
            query += "city LIKE '%" + tour.getCity() + "%'";
        }

        if (tour.getStartDate() != null ) {
            query += conditionExist ? " AND " : " WHERE ";
            conditionExist = true;
            query += "startDate >= '" + sdf.format(tour.getStartDate()) + "'";
        }

        if (tour.getEndDate() != null) {
            query += conditionExist ? " AND " : " WHERE ";
            query += "endDate <= '" + sdf.format(tour.getEndDate()) + "'";
        }

        if (tour.getCost() != 0) {
            query += conditionExist ? " AND " : " WHERE ";
            query += "cost = " + tour.getCost() + "";
        }

        if (tour.getCountOfDays() != 0) {
            query += conditionExist ? " AND " : " WHERE ";
            query += "countOfDays = " + tour.getCountOfDays() + "";
        }

        System.out.println(query);

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                searched.add(new Tour(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("country"),
                        result.getString("city"),
                        result.getDate("startDate"),
                        result.getDate("endDate"),
                        result.getInt("cost"),
                        result.getInt("countOfDays")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error finding tour");
        }

        return searched;
    }

    @Override
    public boolean updateTour(int id, Tour tour) {
        boolean isUpdated = false;

        String query = "UPDATE Tour SET " +
                "id = '" + tour.getId() + "', " +
                "name = '" + tour.getName() + "', " +
                "contry = '" + tour.getCountry() + "', " +
                "city = '" + tour.getCity() + "', " +
                "startDate = '" + tour.getStartDate() + "' " +
                "endDate = '" + tour.getEndDate() + "' " +
                "cost = " + tour.getCost() + ", " +
                "countOfFays = '" + tour.getCountOfDays() + "' " +
                "WHERE id = '" + id + "';";

        try {
            Statement statement = connection.createStatement();
            isUpdated = statement.executeUpdate(query) > 0;

        } catch (SQLException e) {
            System.out.println("Error updating tour");
        }

        return isUpdated;
    }
}
