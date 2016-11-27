package server.models.DAO;

import common.classes.User;
import server.models.DAOFactory.DAOFactory;
import server.models.DAOI.UserDAOInterface;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements UserDAOInterface{
    private Connection connection;

    public UserDAO() {
        this.connection = DAOFactory.createConnection();
    }

    @Override
    public boolean addUser(User obj) {
        boolean isInserted = false;

        String query = "INSERT INTO Users (login, password, email, userName, userSurname) " +
                "VALUES ('" + obj.getLogin() + "', '" + obj.getPassword() + "', '" + obj.getEmail() + "', '" + obj.getUserName() + "', '" + obj.getUserSurname() + "');";

        try {
            Statement statement = connection.createStatement();
            isInserted = statement.executeUpdate(query) > 0;

        } catch (SQLException e) {
            System.err.println("Error inserting user");
        }

        return isInserted;
    }

    @Override
    public boolean deleteUser(int id) {
        boolean isDeleted = false;
        String query = "DELETE FROM Users WHERE id = '" + id + "';";

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            if (result.next()) {
                isDeleted = true;
            }
        } catch (SQLException e) {
            System.err.println("Error deleting user");
        }

        return isDeleted;
    }

    @Override
    public List<User> findUser(User user) {
        List<User> searched = new ArrayList<User>();
        boolean conditionExist = false;

        String query = "SELECT * FROM Users";

        if (!user.getLogin().equals("")) {
            query += conditionExist ? " AND " : " WHERE ";
            query += "login = '" + user.getLogin() + "'";
            conditionExist = true;
        }

        if (!user.getPassword().equals("")) {
            query += conditionExist ? " AND " : " WHERE ";
            conditionExist = true;
            query += "password = '" + user.getPassword() + "'";
        }

        if (!user.getEmail().equals("")) {
            query += conditionExist ? " AND " : " WHERE ";
            conditionExist = true;
            query += "email = '" + user.getEmail() + "'";
        }

        if (user.getId() != 0) {
            query += conditionExist ? " AND " : " WHERE ";
            conditionExist = true;
            query += "id = '" + user.getId() + "'";
        }

        if (user.getType() != 0) {
            query += conditionExist ? " AND " : " WHERE ";
            query += "type = '" + user.getType() + "'";
        }

        if (!user.getUserName().equals("")) {
            query += conditionExist ? " AND " : " WHERE ";
            query += "type = '" + user.getUserName() + "'";
        }

        if (!user.getUserSurname().equals("")) {
            query += conditionExist ? " AND " : " WHERE ";
            query += "type = '" + user.getUserSurname() + "'";
        }

        System.out.println(query);

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                searched.add(new User(
                        result.getInt("id"),
                        result.getString("login"),
                        result.getString("password"),
                        result.getString("email"),
                        result.getInt("type"),
                        result.getString("userName"),
                        result.getString("userSurname")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error finding user");
        }

        return searched;
    }

    @Override
    public boolean updateUser(int id, User user) {
        boolean isUpdated = false;

        String query = "UPDATE Users SET " +
                "id = '" + user.getId() + "', " +
                "login = '" + user.getLogin() + "', " +
                "password = '" + user.getPassword() + "', " +
                "email = '" + user.getEmail() + "', " +
                "type = '" + user.getType() + "', " +
                "userName = '" + user.getUserName() + "', " +
                "userSurname = '" + user.getUserSurname() + "' " +
                "WHERE id = '" + id + "';";

        System.out.println(query);

        try {
            Statement statement = connection.createStatement();
            isUpdated = statement.executeUpdate(query) > 0;

        } catch (SQLException e) {
            System.out.println("Error updating user");
        }

        return isUpdated;
    }
}
