package pl.edu.wszib.kotarba.garbage.management.service;

import pl.edu.wszib.kotarba.garbage.management.model.Bag;

import java.util.List;

public interface IBagService {
    List<Bag> getAllBags();
    void addBagQuantity(String color, int quantity);
}
