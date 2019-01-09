package selection;

import base.Tour;

public class Fight {
    private Tour tour1;
    private Tour tour2;

    public Fight(Tour tour1, Tour tour2) {
        this.tour1 = tour1;
        this.tour2 = tour2;
    }

    public Tour getTour1() {
        return tour1;
    }

    public void setTour1(Tour tour1) {
        this.tour1 = tour1;
    }

    public Tour getTour2() {
        return tour2;
    }

    public void setTour2(Tour tour2) {
        this.tour2 = tour2;
    }
}
