package common.classes;

import java.io.Serializable;

public class Order implements Serializable {
    private int id;
    private User user;
    private Tour tour;

    public Order(int id, User user, Tour tour) {
        this.id = id;
        this.user = user;
        this.tour = tour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }
}
