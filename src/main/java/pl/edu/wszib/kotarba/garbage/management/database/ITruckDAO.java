package pl.edu.wszib.kotarba.garbage.management.database;

import pl.edu.wszib.kotarba.garbage.management.model.Truck;

import java.util.List;

public interface ITruckDAO {
    void addTruck(Truck truck);
    List<Truck> getTrucksByUserId(int userId);
}
