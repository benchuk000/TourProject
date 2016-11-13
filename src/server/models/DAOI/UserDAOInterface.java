package server.models.DAOI;

import common.classes.User;

import java.util.List;

public interface UserDAOInterface {
    public boolean    addUser(User user);
    public boolean    deleteUser(int id);
    public List<User> findUser(User user);
    public boolean    updateUser(int id, User user);
}
