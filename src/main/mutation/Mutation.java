package mutation;

import base.Tour;

public abstract class Mutation {
    public abstract void doMutation(Tour tour);

    public String toString() {
        return getClass().getSimpleName();
    }
}