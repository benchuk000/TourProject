package server.models.DAOI;

import common.classes.Order;

import java.util.List;

public interface OrderDAOInterface {
    public boolean     addOrder(Order order);
    public boolean     deleteOrder(int id);
    public List<Order> findOrder(Order order);
    public boolean     updateOrder(int id, Order order);
}
