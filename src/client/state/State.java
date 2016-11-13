package client.state;

import common.classes.Tour;
import common.classes.User;

import javax.swing.*;

public class State {
    private User currentUser;
    private User selectedUser;
    private JFrame currentFrame;
    private Tour selectedTour;
    //TODO: Add order.

    public State() {
        this.currentUser = new User(0, "", "", "", 0, "", "");
        this.selectedUser = new User(0, "", "", "", 0, "", "");
        this.currentFrame = new JFrame();
        //TODO: Initiolize order.
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public JFrame getCurrentFrame() {
        return currentFrame;
    }

    public Tour getSelectedTour() {
        return selectedTour;
    }

    public void setSelectedTour(Tour selectedTour) {
        this.selectedTour = selectedTour;
    }

    public void setCurrentFrame(JFrame currentFrame) {
        this.currentFrame = currentFrame;
    }

    //TODO: Add getters and setters for order.
}
