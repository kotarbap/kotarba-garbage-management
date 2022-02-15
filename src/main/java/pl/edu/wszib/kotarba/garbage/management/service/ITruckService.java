package pl.edu.wszib.kotarba.garbage.management.service;

import pl.edu.wszib.kotarba.garbage.management.model.Truck;

import java.util.List;

public interface ITruckService {
    void confirmTruck();
    List<Truck> getTrucksForCurrentUser();
}
