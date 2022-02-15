package pl.edu.wszib.kotarba.garbage.management.database;

import pl.edu.wszib.kotarba.garbage.management.model.Bag;

import java.util.List;
import java.util.Optional;

public interface IBagDAO {
    List<Bag> getBags();
    Optional<Bag> getBagById(int bagId);
    Optional<Bag> getBagByColor(String color);
    void updateBag(Bag bag);
}
