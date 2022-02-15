package pl.edu.wszib.kotarba.garbage.management.model;

import java.util.ArrayList;
import java.util.List;

public class Dumpster {
    private List<TruckPosition> truckPositions = new ArrayList<>();

    public List<TruckPosition> getTruckPositions() {
        return truckPositions;
    }

    public double getSum() {
        double sum = 0.0;
        for(TruckPosition truckPosition : this.truckPositions) {
            sum += truckPosition.getQuantity() * truckPosition.getBag().getPrice();
        }
        return Math.round(sum*100)/100.0;
    }

    public void clearTruckPositions() {
        this.truckPositions = new ArrayList<>();
    }
}
