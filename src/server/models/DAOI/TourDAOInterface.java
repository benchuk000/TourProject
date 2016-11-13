package server.models.DAOI;

import common.classes.Tour;

import java.util.List;

public interface TourDAOInterface {
    public boolean    addTour(Tour tour);
    public boolean    deleteTour(int id);
    public List<Tour> findTour(Tour tour);
    public boolean    updateTour(int id, Tour tour);
}
